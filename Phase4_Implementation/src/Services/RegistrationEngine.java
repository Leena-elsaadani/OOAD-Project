package Services;

import UserManagement.Student;
import CourseManagement.*;
import RegistrationManagement.*;
import java.util.*;

/**
 * RegistrationEngine service class handling registration logic.
 * Centralized validation and processing for course registration.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class RegistrationEngine {
    private static RegistrationEngine instance;
    private NotificationService notificationService;
    private SISAdapter sisAdapter;

    /**
     * Private constructor for singleton pattern
     */
    private RegistrationEngine() {
        this.notificationService = NotificationService.getInstance();
        this.sisAdapter = SISAdapter.getInstance();
    }

    /**
     * Get singleton instance
     * @return RegistrationEngine instance
     */
    public static RegistrationEngine getInstance() {
        if (instance == null) {
            instance = new RegistrationEngine();
        }
        return instance;
    }

    /**
     * Validate prerequisites for a student and section
     * @param student Student attempting registration
     * @param section CourseSection to register for
     * @param course Course object with prerequisites
     * @return true if prerequisites are satisfied
     */
    public boolean validatePrerequisites(Student student, CourseSection section, Course course) {
        if (course == null) {
            return true; // No prerequisites
        }
        
        List<String> prerequisites = course.getPrerequisites();
        if (prerequisites == null || prerequisites.isEmpty()) {
            return true;
        }
        
        List<String> completedCourses = student.getCompletedCourses();
        for (String prereq : prerequisites) {
            if (!completedCourses.contains(prereq)) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Detect schedule conflicts between new section and student's enrolled sections
     * @param student Student attempting registration
     * @param newSection New section to check
     * @param enrolledSections List of sections student is already enrolled in
     * @return true if conflict exists
     */
    public boolean detectScheduleConflicts(Student student, CourseSection newSection, 
                                          List<CourseSection> enrolledSections) {
        if (enrolledSections == null || enrolledSections.isEmpty()) {
            return false;
        }
        
        Schedule newSchedule = newSection.getSchedule();
        for (CourseSection enrolled : enrolledSections) {
            if (newSchedule.overlaps(enrolled.getSchedule())) {
                return true; // Conflict found
            }
        }
        
        return false;
    }

    /**
     * Process entire enrollment cart for a student
     * @param student Student submitting registration
     * @param cart EnrollmentCart with selected sections
     * @param courseMap Map of course codes to Course objects
     * @param enrolledSections Student's currently enrolled sections
     * @return List of Registration records
     */
    public List<Registration> processCart(Student student, EnrollmentCart cart, 
                                         Map<String, Course> courseMap,
                                         List<CourseSection> enrolledSections) {
        List<Registration> registrations = new ArrayList<>();
        
        if (cart == null || cart.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return registrations;
        }
        
        // Check for holds
        if (student.hasHolds()) {
            System.out.println("Error: Student has active holds. Registration blocked.");
            for (CourseSection section : cart.getItems()) {
                Registration reg = new Registration(
                    generateRegistrationId(), 
                    student.getStudentId(), 
                    section.getSectionId()
                );
                reg.markFailed("Active hold on student account");
                registrations.add(reg);
            }
            return registrations;
        }
        
        // Process each section in cart
        for (CourseSection section : cart.getItems()) {
            Registration reg = processSection(student, section, courseMap, enrolledSections);
            registrations.add(reg);
            
            // If successful, add to enrolled sections for conflict checking
            if (reg.isSuccessful()) {
                enrolledSections.add(section);
            }
        }
        
        // Send summary notification
        notificationService.sendRegistrationSummary(student, registrations);
        
        // Sync with SIS
        for (Registration reg : registrations) {
            sisAdapter.syncEnrollment(reg);
        }
        
        return registrations;
    }

    /**
     * Process a single section registration
     * @param student Student registering
     * @param section Section to register for
     * @param courseMap Map of courses
     * @param enrolledSections Currently enrolled sections
     * @return Registration record
     */
    private Registration processSection(Student student, CourseSection section, 
                                       Map<String, Course> courseMap,
                                       List<CourseSection> enrolledSections) {
        String regId = generateRegistrationId();
        Registration reg = new Registration(regId, student.getStudentId(), section.getSectionId());
        
        // Get course for prerequisite checking
        Course course = courseMap.get(section.getCourseCode());
        
        // Step 1: Validate prerequisites
        if (!validatePrerequisites(student, section, course)) {
            reg.markFailed("Prerequisites not met: " + 
                          (course != null ? course.getPrerequisitesString() : ""));
            return reg;
        }
        
        // Step 2: Check schedule conflicts
        if (detectScheduleConflicts(student, section, enrolledSections)) {
            reg.markFailed("Schedule conflict with enrolled courses");
            return reg;
        }
        
        // Step 3: Check capacity
        if (section.isFull()) {
            // Try to add to waitlist
            if (section.addToWaitlist(student)) {
                reg.markWaitlisted();
                System.out.println("Added to waitlist: " + section.getSectionId());
            } else {
                reg.markFailed("Section full and waitlist unavailable");
            }
            return reg;
        }
        
        // Step 4: Enroll student
        if (section.enrollStudent(student)) {
            reg.markEnrolled();
            System.out.println("Successfully enrolled in: " + section.getSectionId());
        } else {
            reg.markFailed("Enrollment failed - unknown error");
        }
        
        return reg;
    }

    /**
     * Handle waitlist processing when a seat opens
     * @param section Section with open seat
     * @return true if waitlist student enrolled
     */
    public boolean handleWaitlist(CourseSection section) {
        if (section.isFull() || section.getWaitlist().isEmpty()) {
            return false;
        }
        
        // Try to enroll first student on waitlist
        Student waitlistStudent = section.getWaitlist().get(0);
        if (section.enrollStudent(waitlistStudent)) {
            section.getWaitlist().remove(0);
            notificationService.notifyStudent(
                waitlistStudent.getStudentId(),
                "You have been enrolled from the waitlist for section: " + section.getSectionId()
            );
            return true;
        }
        
        return false;
    }

    /**
     * Generate unique registration ID
     * @return Registration ID string
     */
    private String generateRegistrationId() {
        return "REG-" + System.currentTimeMillis() + "-" + 
               (int)(Math.random() * 1000);
    }

    /**
     * Calculate total credit hours for sections
     * @param sections List of course sections
     * @param courseMap Map of courses
     * @return Total credit hours
     */
    public int calculateTotalCredits(List<CourseSection> sections, Map<String, Course> courseMap) {
        int total = 0;
        for (CourseSection section : sections) {
            Course course = courseMap.get(section.getCourseCode());
            if (course != null) {
                total += course.getCreditHours();
            }
        }
        return total;
    }

    /**
     * Validate credit load doesn't exceed maximum
     * @param student Student to check
     * @param newSections Sections being added
     * @param enrolledSections Currently enrolled sections
     * @param courseMap Map of courses
     * @param maxCredits Maximum allowed credits
     * @return true if within limit
     */
    public boolean validateCreditLoad(Student student, List<CourseSection> newSections,
                                     List<CourseSection> enrolledSections,
                                     Map<String, Course> courseMap, int maxCredits) {
        List<CourseSection> allSections = new ArrayList<>(enrolledSections);
        allSections.addAll(newSections);
        
        int totalCredits = calculateTotalCredits(allSections, courseMap);
        return totalCredits <= maxCredits;
    }
}
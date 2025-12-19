package Data;

import UserManagement.*;
import CourseManagement.*;
import RegistrationManagement.*;
import java.time.*;
import java.util.*;

/**
 * DataStore class manages all system data in memory.
 * Provides centralized access to users, courses, sections, and registrations.
 * Acts as an in-memory database for the prototype.
 */
public class DataStore {
    private static DataStore instance;
    
    // Data collections
    private Map<String, User> users;
    private Map<String, Student> students;
    private Map<String, Faculty> faculty;
    private Map<String, Administrator> administrators;
    private Map<String, Course> courses;
    private Map<String, CourseSection> courseSections;
    private Map<String, Semester> semesters;
    private Map<String, Registration> registrations;
    private List<ExceptionRequest> exceptionRequests;
    
    // Current logged-in user
    private User currentUser;
    
    /**
     * Private constructor - initializes with sample data
     */
    private DataStore() {
        users = new HashMap<>();
        students = new HashMap<>();
        faculty = new HashMap<>();
        administrators = new HashMap<>();
        courses = new HashMap<>();
        courseSections = new HashMap<>();
        semesters = new HashMap<>();
        registrations = new HashMap<>();
        exceptionRequests = new ArrayList<>();
        
        initializeSampleData();
    }
    
    /**
     * Get singleton instance
     */
    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }
    
    /**
     * Initialize system with sample data
     */
    private void initializeSampleData() {
        // Create semester
        Semester fall2025 = new Semester(
            "FALL2025", 
            "Fall 2025",
            LocalDate.of(2025, 9, 1),
            LocalDate.of(2025, 12, 15)
        );
        fall2025.setRegistrationStart(LocalDateTime.now().minusDays(5));
        fall2025.setRegistrationEnd(LocalDateTime.now().plusDays(30));
        semesters.put(fall2025.getTermId(), fall2025);
        
        // Create sample students
        Student student1 = new Student("U001", "Ahmed Ali", "ahmed@university.edu", 
                                      "pass123", "S12345", "Computer Science");
        student1.addCompletedCourse("CS101");
        student1.addCompletedCourse("MATH101");
        students.put(student1.getStudentId(), student1);
        users.put(student1.getUserId(), student1);
        
        Student student2 = new Student("U002", "Sara Mohamed", "sara@university.edu",
                                      "pass123", "S12346", "Business Administration");
        students.put(student2.getStudentId(), student2);
        users.put(student2.getUserId(), student2);
        
        // Create sample faculty
        Faculty faculty1 = new Faculty("U003", "Dr. Omar Hassan", "omar@university.edu",
                                      "pass123", "F001", "Computer Science");
        faculty.put(faculty1.getFacultyId(), faculty1);
        users.put(faculty1.getUserId(), faculty1);
        
        Faculty faculty2 = new Faculty("U004", "Dr. Layla Ibrahim", "layla@university.edu",
                                      "pass123", "F002", "Mathematics");
        faculty.put(faculty2.getFacultyId(), faculty2);
        users.put(faculty2.getUserId(), faculty2);
        
        // Create administrator
        Administrator admin1 = new Administrator("U005", "Admin User", "admin@university.edu",
                                                "admin123", "A001");
        administrators.put(admin1.getAdminId(), admin1);
        users.put(admin1.getUserId(), admin1);
        
        // Create sample courses
        Course cs201 = new Course("CS201", "Data Structures", 3, 
                                 "Advanced programming and data structures");
        cs201.addPrerequisite("CS101");
        courses.put(cs201.getCourseCode(), cs201);
        
        Course cs301 = new Course("CS301", "Object-Oriented Analysis", 3,
                                 "Software design and OOP principles");
        cs301.addPrerequisite("CS201");
        courses.put(cs301.getCourseCode(), cs301);
        
        Course math201 = new Course("MATH201", "Calculus II", 4,
                                   "Integral calculus and applications");
        math201.addPrerequisite("MATH101");
        courses.put(math201.getCourseCode(), math201);
        
        Course bus101 = new Course("BUS101", "Introduction to Business", 3,
                                  "Fundamentals of business administration");
        courses.put(bus101.getCourseCode(), bus101);
        
        Course econ101 = new Course("ECON101", "Principles of Economics", 3,
                                   "Microeconomics and macroeconomics basics");
        courses.put(econ101.getCourseCode(), econ101);
        
        // Create course sections with schedules
        Set<DayOfWeek> mwf = new HashSet<>(Arrays.asList(
            DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        Set<DayOfWeek> tr = new HashSet<>(Arrays.asList(
            DayOfWeek.TUESDAY, DayOfWeek.THURSDAY));
        
        Schedule schedule1 = new Schedule(mwf, LocalTime.of(9, 0), 
                                         LocalTime.of(10, 0), "Room 101");
        CourseSection section1 = new CourseSection("CS201-01", "CS201", 
                                                   fall2025, 30, schedule1);
        section1.assignInstructor("F001");
        faculty1.assignSection(section1);
        courseSections.put(section1.getSectionId(), section1);
        
        Schedule schedule2 = new Schedule(tr, LocalTime.of(11, 0),
                                         LocalTime.of(12, 30), "Room 102");
        CourseSection section2 = new CourseSection("CS301-01", "CS301",
                                                   fall2025, 25, schedule2);
        section2.assignInstructor("F001");
        faculty1.assignSection(section2);
        courseSections.put(section2.getSectionId(), section2);
        
        Schedule schedule3 = new Schedule(mwf, LocalTime.of(13, 0),
                                         LocalTime.of(14, 0), "Room 201");
        CourseSection section3 = new CourseSection("MATH201-01", "MATH201",
                                                   fall2025, 35, schedule3);
        section3.assignInstructor("F002");
        faculty2.assignSection(section3);
        courseSections.put(section3.getSectionId(), section3);
        
        Schedule schedule4 = new Schedule(tr, LocalTime.of(9, 30),
                                         LocalTime.of(11, 0), "Room 105");
        CourseSection section4 = new CourseSection("BUS101-01", "BUS101",
                                                   fall2025, 40, schedule4);
        courseSections.put(section4.getSectionId(), section4);
        
        Schedule schedule5 = new Schedule(mwf, LocalTime.of(10, 30),
                                         LocalTime.of(11, 30), "Room 106");
        CourseSection section5 = new CourseSection("ECON101-01", "ECON101",
                                                   fall2025, 3, schedule5); // Small capacity for testing
        courseSections.put(section5.getSectionId(), section5);
        
        System.out.println("Sample data initialized successfully.");
        System.out.println("- " + students.size() + " students");
        System.out.println("- " + faculty.size() + " faculty");
        System.out.println("- " + administrators.size() + " administrators");
        System.out.println("- " + courses.size() + " courses");
        System.out.println("- " + courseSections.size() + " course sections");
    }
    
    // User management
    public User authenticateUser(String userId, String password) {
        User user = users.get(userId);
        if (user != null && user.authenticate(password)) {
            currentUser = user;
            return user;
        }
        return null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void logout() {
        currentUser = null;
    }
    
    // Data access methods
    public Map<String, User> getUsers() { return users; }
    public Map<String, Student> getStudents() { return students; }
    public Map<String, Faculty> getFaculty() { return faculty; }
    public Map<String, Administrator> getAdministrators() { return administrators; }
    public Map<String, Course> getCourses() { return courses; }
    public Map<String, CourseSection> getCourseSections() { return courseSections; }
    public Map<String, Semester> getSemesters() { return semesters; }
    public Map<String, Registration> getRegistrations() { return registrations; }
    public List<ExceptionRequest> getExceptionRequests() { return exceptionRequests; }
    
    // Add methods
    public void addUser(User user) { 
        users.put(user.getUserId(), user);
        if (user instanceof Student) {
            students.put(((Student) user).getStudentId(), (Student) user);
        } else if (user instanceof Faculty) {
            faculty.put(((Faculty) user).getFacultyId(), (Faculty) user);
        } else if (user instanceof Administrator) {
            administrators.put(((Administrator) user).getAdminId(), (Administrator) user);
        }
    }
    
    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
    }
    
    public void addCourseSection(CourseSection section) {
        courseSections.put(section.getSectionId(), section);
    }
    
    public void addRegistration(Registration registration) {
        registrations.put(registration.getRegistrationId(), registration);
    }
    
    public void addExceptionRequest(ExceptionRequest request) {
        exceptionRequests.add(request);
    }
    
    // Find methods
    public Student findStudentById(String studentId) {
        return students.get(studentId);
    }
    
    public Faculty findFacultyById(String facultyId) {
        return faculty.get(facultyId);
    }
    
    public Course findCourseByCode(String courseCode) {
        return courses.get(courseCode);
    }
    
    public CourseSection findSectionById(String sectionId) {
        return courseSections.get(sectionId);
    }
    
    public List<CourseSection> findSectionsByCourse(String courseCode) {
        List<CourseSection> sections = new ArrayList<>();
        for (CourseSection section : courseSections.values()) {
            if (section.getCourseCode().equals(courseCode)) {
                sections.add(section);
            }
        }
        return sections;
    }
    
    public List<Registration> findRegistrationsByStudent(String studentId) {
        List<Registration> studentRegs = new ArrayList<>();
        for (Registration reg : registrations.values()) {
            if (reg.getStudentId().equals(studentId) && 
                reg.getStatus() == RegistrationStatus.ENROLLED) {
                studentRegs.add(reg);
            }
        }
        return studentRegs;
    }
    
    public List<CourseSection> getEnrolledSections(String studentId) {
        List<CourseSection> sections = new ArrayList<>();
        List<Registration> regs = findRegistrationsByStudent(studentId);
        for (Registration reg : regs) {
            CourseSection section = findSectionById(reg.getSectionId());
            if (section != null) {
                sections.add(section);
            }
        }
        return sections;
    }
}
package CourseManagement;

import UserManagement.Student;
import java.util.*;

/**
 * CourseSection class representing a specific offering of a course.
 * Contains section-specific details like schedule, capacity, and enrollment.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class CourseSection {
    private String sectionId;
    private String courseCode;
    private Semester term;
    private int capacity;
    private int enrolledCount;
    private Schedule schedule;
    private String instructorId;
    private List<Student> enrolledStudents;
    private List<Student> waitlist;

    /**
     * Constructor for CourseSection
     * @param sectionId Unique section identifier
     * @param courseCode Course code this section belongs to
     * @param term Semester for this section
     * @param capacity Maximum enrollment capacity
     * @param schedule Meeting schedule
     */
    public CourseSection(String sectionId, String courseCode, Semester term, 
                        int capacity, Schedule schedule) {
        this.sectionId = sectionId;
        this.courseCode = courseCode;
        this.term = term;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledCount = 0;
        this.enrolledStudents = new ArrayList<>();
        this.waitlist = new ArrayList<>();
    }

    /**
     * Get number of available seats
     * @return Available seats (capacity - enrolled)
     */
    public int getAvailableSeats() {
        return capacity - enrolledCount;
    }

    /**
     * Check if section is at full capacity
     * @return true if no seats available
     */
    public boolean isFull() {
        return enrolledCount >= capacity;
    }

    /**
     * Assign an instructor to this section
     * @param facultyId Faculty member's ID
     */
    public void assignInstructor(String facultyId) {
        this.instructorId = facultyId;
    }

    /**
     * Enroll a student in this section
     * @param student Student to enroll
     * @return true if enrollment successful
     */
    public boolean enrollStudent(Student student) {
        if (isFull()) {
            return false;
        }
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            enrolledCount++;
            return true;
        }
        return false;
    }

    /**
     * Add student to waitlist
     * @param student Student to add
     * @return true if added successfully
     */
    public boolean addToWaitlist(Student student) {
        if (!waitlist.contains(student) && !enrolledStudents.contains(student)) {
            waitlist.add(student);
            return true;
        }
        return false;
    }

    /**
     * Remove student from section (withdrawal)
     * @param student Student to remove
     * @return true if removal successful
     */
    public boolean removeStudent(Student student) {
        if (enrolledStudents.remove(student)) {
            enrolledCount--;
            // Try to enroll from waitlist
            if (!waitlist.isEmpty()) {
                Student waitlistStudent = waitlist.remove(0);
                enrollStudent(waitlistStudent);
            }
            return true;
        }
        return false;
    }

    /**
     * Get list of enrolled students
     * @return List of Student objects
     */
    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents);
    }

    /**
     * Get section information as formatted string
     * @return Formatted section details
     */
    public String getSectionInfo() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%-15s | %-10s | %s | Seats: %d/%d",
                sectionId, courseCode, schedule.toString(), enrolledCount, capacity));
        if (!waitlist.isEmpty()) {
            info.append(" | Waitlist: ").append(waitlist.size());
        }
        return info.toString();
    }

    // Getters and Setters
    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Semester getTerm() {
        return term;
    }

    public void setTerm(Semester term) {
        this.term = term;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public void setEnrolledCount(int enrolledCount) {
        this.enrolledCount = enrolledCount;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public List<Student> getWaitlist() {
        return new ArrayList<>(waitlist);
    }

    @Override
    public String toString() {
        return getSectionInfo();
    }
}
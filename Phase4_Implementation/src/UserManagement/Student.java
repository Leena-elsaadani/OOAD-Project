package UserManagement;

import CourseManagement.Semester;
import RegistrationManagement.EnrollmentCart;
import AcademicPlanning.AcademicPlan;
import java.util.*;

/**
 * Student class representing a student user in the system.
 * Extends User and adds student-specific attributes and behaviors.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class Student extends User {
    private String studentId;
    private String program;
    private List<String> completedCourses;
    private Set<String> holds;
    private EnrollmentCart activeCart;
    private List<AcademicPlan> academicPlans;

    /**
     * Constructor for Student
     * @param userId Unique user identifier
     * @param name Student's full name
     * @param email Student's email
     * @param password Student's password
     * @param studentId Student ID number
     * @param program Degree program (e.g., "Computer Science")
     */
    public Student(String userId, String name, String email, String password, 
                   String studentId, String program) {
        super(userId, name, email, password, "Student");
        this.studentId = studentId;
        this.program = program;
        this.completedCourses = new ArrayList<>();
        this.holds = new HashSet<>();
        this.academicPlans = new ArrayList<>();
        this.activeCart = new EnrollmentCart(studentId);
    }

    /**
     * Get eligible terms for registration based on student status
     * @return List of eligible semesters
     */
    public List<Semester> getEligibleTerms() {
        // In a real system, this would check academic standing, holds, etc.
        List<Semester> eligibleTerms = new ArrayList<>();
        // Return current and future terms if no holds
        return eligibleTerms;
    }

    /**
     * Check if student has completed a prerequisite course
     * @param courseCode Course code to check
     * @return true if prerequisite is satisfied
     */
    public boolean hasPrerequisite(String courseCode) {
        return completedCourses.contains(courseCode);
    }

    /**
     * Get the student's active enrollment cart
     * @return EnrollmentCart object
     */
    public EnrollmentCart getActiveCart() {
        return activeCart;
    }

    /**
     * Create a new academic plan for multi-term planning
     * @param name Name for the academic plan
     * @return Newly created AcademicPlan
     */
    public AcademicPlan createAcademicPlan(String name) {
        AcademicPlan plan = new AcademicPlan(
            "PLAN-" + studentId + "-" + System.currentTimeMillis(),
            studentId,
            name
        );
        academicPlans.add(plan);
        return plan;
    }

    /**
     * Add a course to completed courses list
     * @param courseCode Course code to add
     */
    public void addCompletedCourse(String courseCode) {
        if (!completedCourses.contains(courseCode)) {
            completedCourses.add(courseCode);
        }
    }

    /**
     * Check if student has any active holds
     * @return true if holds exist
     */
    public boolean hasHolds() {
        return !holds.isEmpty();
    }

    /**
     * Add a hold to student account
     * @param holdType Type of hold (e.g., "FINANCIAL", "ADVISING")
     */
    public void addHold(String holdType) {
        holds.add(holdType);
    }

    /**
     * Remove a hold from student account
     * @param holdType Type of hold to remove
     */
    public void removeHold(String holdType) {
        holds.remove(holdType);
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public List<String> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<String> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public Set<String> getHolds() {
        return holds;
    }

    public void setHolds(Set<String> holds) {
        this.holds = holds;
    }

    public List<AcademicPlan> getAcademicPlans() {
        return academicPlans;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + getName() + '\'' +
                ", program='" + program + '\'' +
                ", completedCourses=" + completedCourses.size() +
                ", holds=" + holds.size() +
                '}';
    }
}
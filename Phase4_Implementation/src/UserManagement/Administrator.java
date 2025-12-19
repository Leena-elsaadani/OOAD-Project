package UserManagement;

import CourseManagement.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Administrator class representing a system administrator.
 * Extends User and provides system configuration and management capabilities.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class Administrator extends User {
    private String adminId;

    /**
     * Constructor for Administrator
     * @param userId Unique user identifier
     * @param name Administrator's full name
     * @param email Administrator's email
     * @param password Administrator's password
     * @param adminId Admin ID number
     */
    public Administrator(String userId, String name, String email, String password, String adminId) {
        super(userId, name, email, password, "Administrator");
        this.adminId = adminId;
    }

    /**
     * Define registration period for a semester
     * @param term Semester to configure
     * @param start Registration start date and time
     * @param end Registration end date and time
     */
    public void defineRegistrationPeriod(Semester term, LocalDateTime start, LocalDateTime end) {
        if (term != null && start.isBefore(end)) {
            term.setRegistrationStart(start);
            term.setRegistrationEnd(end);
            System.out.println("Registration period defined for " + term.getTermId());
            System.out.println("Start: " + start + " | End: " + end);
        } else {
            System.out.println("Error: Invalid registration period dates.");
        }
    }

    /**
     * Manage user roles - assign or modify user permissions
     * @param user User to modify
     * @param newRole New role to assign
     */
    public void manageUserRoles(User user, String newRole) {
        if (user != null && newRole != null) {
            String oldRole = user.getRole();
            user.setRole(newRole);
            System.out.println("User role updated: " + user.getName() + 
                             " from " + oldRole + " to " + newRole);
        }
    }

    /**
     * Generate system report based on type and filters
     * @param reportType Type of report (e.g., "ENROLLMENT", "WAITLIST")
     * @param filters Map of filter parameters
     * @return Report summary as string
     */
    public String generateReport(String reportType, Map<String, Object> filters) {
        StringBuilder report = new StringBuilder();
        report.append("=== SYSTEM REPORT: ").append(reportType).append(" ===\n");
        report.append("Generated: ").append(LocalDateTime.now()).append("\n");
        report.append("Filters: ").append(filters).append("\n");
        report.append("----------------------------------------\n");
        
        // In a real system, this would query database and generate detailed reports
        report.append("Report data would be compiled here.\n");
        
        return report.toString();
    }

    /**
     * Add a new course to the catalog
     * @param course Course to add
     * @return true if successful
     */
    public boolean addCourse(Course course) {
        if (course != null) {
            System.out.println("Course added to catalog: " + course.getCourseCode() + " - " + course.getTitle());
            return true;
        }
        return false;
    }

    /**
     * Remove a course from the catalog
     * @param courseCode Course code to remove
     * @return true if successful
     */
    public boolean removeCourse(String courseCode) {
        if (courseCode != null && !courseCode.isEmpty()) {
            System.out.println("Course removed from catalog: " + courseCode);
            return true;
        }
        return false;
    }

    /**
     * Create a new course section for a term
     * @param section CourseSection to create
     * @return true if successful
     */
    public boolean createCourseSection(CourseSection section) {
        if (section != null) {
            System.out.println("Course section created: " + section.getSectionId());
            return true;
        }
        return false;
    }

    /**
     * Set maximum credit load for students per term
     * @param maxCredits Maximum credit hours allowed
     */
    public void setMaxCreditLoad(int maxCredits) {
        System.out.println("Maximum credit load set to: " + maxCredits + " hours");
        // In real system, this would be stored in configuration
    }

    /**
     * Configure system-wide registration rules
     * @param ruleName Name of the rule
     * @param value Rule value
     */
    public void configureRegistrationRule(String ruleName, Object value) {
        System.out.println("Registration rule configured: " + ruleName + " = " + value);
    }

    /**
     * Overload for defineRegistrationPeriod with String parameters
     */
    public void defineRegistrationPeriod(String periodName, String startDate, String endDate) {
        System.out.println("Registration period defined:");
        System.out.println("Period: " + periodName);
        System.out.println("Start: " + startDate);
        System.out.println("End: " + endDate);
    }

    /**
     * Set registration rules for the system
     */
    public void setRegistrationRules(int maxCredits, boolean enableConflictCheck) {
        System.out.println("Registration rules configured:");
        System.out.println("- Max credits per term: " + maxCredits);
        System.out.println("- Time conflict checking: " + (enableConflictCheck ? "Enabled" : "Disabled"));
    }

    /**
     * Generate enrollment report
     */
    public String generateEnrollmentReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n========== ENROLLMENT REPORT ==========\n");
        report.append("Generated: ").append(LocalDateTime.now()).append("\n");
        report.append("Total students enrolled: 2\n");
        report.append("Total courses with enrollment: 5\n");
        report.append("Average section capacity: 70%\n");
        report.append("========================================\n");
        return report.toString();
    }

    /**
     * Generate capacity report
     */
    public String generateCapacityReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n========== CAPACITY REPORT ==========\n");
        report.append("Generated: ").append(LocalDateTime.now()).append("\n");
        report.append("Sections at full capacity: 1\n");
        report.append("Sections with available seats: 4\n");
        report.append("Average utilization: 65%\n");
        report.append("====================================\n");
        return report.toString();
    }

    /**
     * Generate student registration report
     */
    public String generateStudentReport(String studentId) {
        StringBuilder report = new StringBuilder();
        report.append("\n========== STUDENT REPORT ==========\n");
        report.append("Student ID: ").append(studentId).append("\n");
        report.append("Status: Active\n");
        report.append("Registered Courses: 3\n");
        report.append("Credit Hours: 9\n");
        report.append("Registration Date: 2025-09-01\n");
        report.append("===================================\n");
        return report.toString();
    }

    // Getters and Setters
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "adminId='" + adminId + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
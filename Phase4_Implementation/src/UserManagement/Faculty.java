package UserManagement;

import CourseManagement.CourseSection;
import RegistrationManagement.ExceptionRequest;
import java.util.*;

/**
 * Faculty class representing an instructor in the system.
 * Extends User and manages course sections and student interactions.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class Faculty extends User {
    private String facultyId;
    private String department;
    private List<CourseSection> taughtSections;

    /**
     * Constructor for Faculty
     * @param userId Unique user identifier
     * @param name Faculty member's full name
     * @param email Faculty member's email
     * @param password Faculty member's password
     * @param facultyId Faculty ID number
     * @param department Academic department
     */
    public Faculty(String userId, String name, String email, String password, 
                   String facultyId, String department) {
        super(userId, name, email, password, "Faculty");
        this.facultyId = facultyId;
        this.department = department;
        this.taughtSections = new ArrayList<>();
    }

    /**
     * Get list of course sections taught by this faculty member
     * @return List of CourseSection objects
     */
    public List<CourseSection> getTaughtSections() {
        return taughtSections;
    }

    /**
     * Assign a course section to this faculty member
     * @param section CourseSection to assign
     */
    public void assignSection(CourseSection section) {
        if (!taughtSections.contains(section)) {
            taughtSections.add(section);
            section.assignInstructor(this.facultyId);
        }
    }

    /**
     * Remove a course section from faculty's teaching load
     * @param section CourseSection to remove
     */
    public void removeSection(CourseSection section) {
        taughtSections.remove(section);
    }

    /**
     * Approve an exception request (prerequisite or capacity override)
     * @param request ExceptionRequest to approve
     */
    public void approveException(ExceptionRequest request) {
        if (request != null) {
            request.approve();
            System.out.println("Exception request approved by " + getName());
        }
    }

    /**
     * Reject an exception request with a comment
     * @param request ExceptionRequest to reject
     * @param comment Reason for rejection
     */
    public void rejectException(ExceptionRequest request, String comment) {
        if (request != null) {
            request.reject(comment);
            System.out.println("Exception request rejected by " + getName());
        }
    }

    /**
     * Get roster of students for a specific course section
     * @param section CourseSection to get roster for
     * @return List of Student objects enrolled in the section
     */
    public List<Student> getRoster(CourseSection section) {
        if (taughtSections.contains(section)) {
            return section.getEnrolledStudents();
        }
        return new ArrayList<>();
    }

    /**
     * Post an announcement to a course section
     * @param section CourseSection to post to
     * @param announcement Announcement text
     */
    public void postAnnouncement(CourseSection section, String announcement) {
        if (taughtSections.contains(section)) {
            System.out.println("Announcement posted to " + section.getSectionId() + ": " + announcement);
            // In a real system, this would persist and notify students
        } else {
            System.out.println("Error: You are not assigned to this section.");
        }
    }

    // Getters and Setters
    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTaughtSections(List<CourseSection> taughtSections) {
        this.taughtSections = taughtSections;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId='" + facultyId + '\'' +
                ", name='" + getName() + '\'' +
                ", department='" + department + '\'' +
                ", sectionsTeaching=" + taughtSections.size() +
                '}';
    }
}
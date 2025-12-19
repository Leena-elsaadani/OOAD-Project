package CourseManagement;

import java.util.*;

/**
 * Course class representing a catalog-level course.
 * Contains metadata about courses including prerequisites.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class Course {
    private String courseCode;
    private String title;
    private int creditHours;
    private List<String> prerequisites;
    private String description;

    /**
     * Constructor for Course
     * @param courseCode Unique course identifier (e.g., "CS101")
     * @param title Course title
     * @param creditHours Number of credit hours
     * @param description Course description
     */
    public Course(String courseCode, String title, int creditHours, String description) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.description = description;
        this.prerequisites = new ArrayList<>();
    }

    /**
     * Check if prerequisites are satisfied by completed courses
     * @param completedCourses List of course codes the student has completed
     * @return true if all prerequisites are met
     */
    public boolean isPrerequisiteSatisfied(List<String> completedCourses) {
        if (prerequisites.isEmpty()) {
            return true;
        }
        
        for (String prereq : prerequisites) {
            if (!completedCourses.contains(prereq)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a prerequisite course
     * @param prereqCourseCode Course code of prerequisite
     */
    public void addPrerequisite(String prereqCourseCode) {
        if (!prerequisites.contains(prereqCourseCode)) {
            prerequisites.add(prereqCourseCode);
        }
    }

    /**
     * Remove a prerequisite course
     * @param prereqCourseCode Course code of prerequisite to remove
     */
    public void removePrerequisite(String prereqCourseCode) {
        prerequisites.remove(prereqCourseCode);
    }

    /**
     * Get formatted prerequisite string
     * @return Prerequisites as comma-separated string
     */
    public String getPrerequisitesString() {
        if (prerequisites.isEmpty()) {
            return "None";
        }
        return String.join(", ", prerequisites);
    }

    // Getters and Setters
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-40s | %d credits | Prerequisites: %s",
                courseCode, title, creditHours, getPrerequisitesString());
    }

    /**
     * Get detailed course information
     * @return Formatted course details
     */
    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n=== COURSE DETAILS ===\n");
        info.append("Code: ").append(courseCode).append("\n");
        info.append("Title: ").append(title).append("\n");
        info.append("Credits: ").append(creditHours).append("\n");
        info.append("Prerequisites: ").append(getPrerequisitesString()).append("\n");
        info.append("Description: ").append(description).append("\n");
        info.append("====================\n");
        return info.toString();
    }
}
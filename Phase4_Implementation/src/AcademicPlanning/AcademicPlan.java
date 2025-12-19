package AcademicPlanning;

import CourseManagement.Semester;
import java.util.*;

/**
 * AcademicPlan class for multi-term course planning.
 * Allows students to plan future course sequences.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class AcademicPlan {
    private String planId;
    private String studentId;
    private String name;
    private Map<String, List<String>> terms; // termId -> list of course codes

    /**
     * Constructor for AcademicPlan
     * @param planId Unique plan identifier
     * @param studentId Student who owns this plan
     * @param name Plan name/description
     */
    public AcademicPlan(String planId, String studentId, String name) {
        this.planId = planId;
        this.studentId = studentId;
        this.name = name;
        this.terms = new HashMap<>();
    }

    /**
     * Add a course to a specific term in the plan
     * @param termId Term identifier
     * @param courseCode Course code to add
     */
    public void addCourse(String termId, String courseCode) {
        terms.computeIfAbsent(termId, k -> new ArrayList<>()).add(courseCode);
        System.out.println("Added " + courseCode + " to " + termId + " in plan: " + name);
    }

    /**
     * Remove a course from a term
     * @param termId Term identifier
     * @param courseCode Course code to remove
     */
    public void removeCourse(String termId, String courseCode) {
        List<String> courses = terms.get(termId);
        if (courses != null) {
            courses.remove(courseCode);
        }
    }

    /**
     * Get courses planned for a specific term
     * @param termId Term identifier
     * @return List of course codes
     */
    public List<String> getCoursesForTerm(String termId) {
        return terms.getOrDefault(termId, new ArrayList<>());
    }

    /**
     * Validate plan against degree requirements
     * @param auditEngine DegreeAuditEngine to use
     * @return PlanValidationResult
     */
    public PlanValidationResult validatePlan(DegreeAuditEngine auditEngine) {
        return auditEngine.evaluatePlan(this);
    }

    /**
     * Get total number of courses in plan
     * @return Total course count
     */
    public int getTotalCourses() {
        int total = 0;
        for (List<String> courses : terms.values()) {
            total += courses.size();
        }
        return total;
    }

    /**
     * Display plan contents
     * @return Formatted plan display
     */
    public String displayPlan() {
        StringBuilder display = new StringBuilder();
        display.append("\n=== ACADEMIC PLAN: ").append(name).append(" ===\n");
        display.append("Plan ID: ").append(planId).append("\n");
        display.append("Student ID: ").append(studentId).append("\n");
        display.append("--------------------------------------\n");
        
        if (terms.isEmpty()) {
            display.append("No courses planned yet.\n");
        } else {
            for (Map.Entry<String, List<String>> entry : terms.entrySet()) {
                display.append("\n").append(entry.getKey()).append(":\n");
                for (String courseCode : entry.getValue()) {
                    display.append("  - ").append(courseCode).append("\n");
                }
            }
        }
        
        display.append("--------------------------------------\n");
        display.append("Total courses: ").append(getTotalCourses()).append("\n");
        
        return display.toString();
    }

    // Getters and Setters
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<String>> getTerms() {
        return terms;
    }

    public void setTerms(Map<String, List<String>> terms) {
        this.terms = terms;
    }

    @Override
    public String toString() {
        return "AcademicPlan{" +
                "planId='" + planId + '\'' +
                ", name='" + name + '\'' +
                ", totalCourses=" + getTotalCourses() +
                '}';
    }
}

/**
 * Helper class for plan validation results
 */
class PlanValidationResult {
    private boolean valid;
    private List<String> issues;
    private List<String> warnings;

    public PlanValidationResult() {
        this.issues = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.valid = true;
    }

    public void addIssue(String issue) {
        issues.add(issue);
        valid = false;
    }

    public void addWarning(String warning) {
        warnings.add(warning);
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getIssues() {
        return issues;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plan Validation: ").append(valid ? "VALID" : "INVALID").append("\n");
        
        if (!issues.isEmpty()) {
            sb.append("\nIssues:\n");
            for (String issue : issues) {
                sb.append("  - ").append(issue).append("\n");
            }
        }
        
        if (!warnings.isEmpty()) {
            sb.append("\nWarnings:\n");
            for (String warning : warnings) {
                sb.append("  - ").append(warning).append("\n");
            }
        }
        
        return sb.toString();
    }
}
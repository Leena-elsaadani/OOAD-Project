package AcademicPlanning;

import java.util.*;

/**
 * Helper class to track degree progress
 */
public class DegreeProgress {
    private String studentId;
    private String program;
    private int completedRequirements;
    private int totalRequirements;
    private List<String> remainingCourses;

    public DegreeProgress(String studentId, String program, int completed, int total, List<String> remaining) {
        this.studentId = studentId;
        this.program = program;
        this.completedRequirements = completed;
        this.totalRequirements = total;
        this.remainingCourses = remaining;
    }

    public String displayProgress() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== DEGREE PROGRESS ==========\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Program: ").append(program).append("\n");
        sb.append("Progress: ").append(completedRequirements).append("/").append(totalRequirements).append(" requirements completed\n");
        
        int percentage = (completedRequirements * 100) / totalRequirements;
        sb.append("Completion: ").append(percentage).append("%\n");
        
        if (!remainingCourses.isEmpty()) {
            sb.append("\nRemaining Courses:\n");
            for (String course : remainingCourses) {
                sb.append("  - ").append(course).append("\n");
            }
        } else {
            sb.append("\n✓ Degree completed!\n");
        }
        
        sb.append("=====================================\n");
        return sb.toString();
    }

    public int getCompletedRequirements() {
        return completedRequirements;
    }

    public int getTotalRequirements() {
        return totalRequirements;
    }

    public List<String> getRemainingCourses() {
        return remainingCourses;
    }

    public boolean isDegreeComplete() {
        return completedRequirements >= totalRequirements;
    }
}

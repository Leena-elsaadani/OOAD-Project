package AcademicPlanning;

import java.util.*;

/**
 * DegreeAuditEngine service for evaluating academic plans.
 * Validates plans against degree requirements.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class DegreeAuditEngine {
    private static DegreeAuditEngine instance;
    private Map<String, DegreeRequirement> programRequirements;

    /**
     * Private constructor for singleton pattern
     */
    private DegreeAuditEngine() {
        this.programRequirements = new HashMap<>();
        initializeRequirements();
    }

    /**
     * Get singleton instance
     * @return DegreeAuditEngine instance
     */
    public static DegreeAuditEngine getInstance() {
        if (instance == null) {
            instance = new DegreeAuditEngine();
        }
        return instance;
    }

    /**
     * Initialize sample degree requirements
     */
    private void initializeRequirements() {
        // Computer Science degree requirements (sample)
        DegreeRequirement csRequirement = new DegreeRequirement("Computer Science");
        csRequirement.addRequiredCourse("CS101");
        csRequirement.addRequiredCourse("CS201");
        csRequirement.addRequiredCourse("MATH101");
        csRequirement.addRequiredCourse("MATH201");
        csRequirement.setMinimumCredits(120);
        programRequirements.put("Computer Science", csRequirement);

        // Business Administration degree requirements (sample)
        DegreeRequirement baRequirement = new DegreeRequirement("Business Administration");
        baRequirement.addRequiredCourse("BUS101");
        baRequirement.addRequiredCourse("ECON101");
        baRequirement.addRequiredCourse("ACCT101");
        baRequirement.setMinimumCredits(120);
        programRequirements.put("Business Administration", baRequirement);
    }

    /**
     * Evaluate an academic plan
     * @param plan AcademicPlan to evaluate
     * @return PlanValidationResult with findings
     */
    public PlanValidationResult evaluatePlan(AcademicPlan plan) {
        PlanValidationResult result = new PlanValidationResult();
        
        // Get all planned courses
        Set<String> plannedCourses = new HashSet<>();
        for (List<String> termCourses : plan.getTerms().values()) {
            plannedCourses.addAll(termCourses);
        }
        
        // For now, just check if plan has courses
        if (plannedCourses.isEmpty()) {
            result.addIssue("Plan has no courses");
        } else {
            result.addWarning("Plan contains " + plannedCourses.size() + " courses");
        }
        
        return result;
    }

    /**
     * Check degree completion progress for a student
     * @param studentId Student ID
     * @param program Degree program
     * @param completedCourses List of completed course codes
     * @return DegreeProgress object
     */
    public DegreeProgress checkDegreeCompletion(String studentId, String program, 
                                               List<String> completedCourses) {
        DegreeRequirement requirement = programRequirements.get(program);
        
        if (requirement == null) {
            return new DegreeProgress(studentId, program, 0, 0, new ArrayList<>());
        }
        
        // Calculate progress
        List<String> requiredCourses = requirement.getRequiredCourses();
        List<String> remainingCourses = new ArrayList<>();
        
        int completed = 0;
        for (String required : requiredCourses) {
            if (completedCourses.contains(required)) {
                completed++;
            } else {
                remainingCourses.add(required);
            }
        }
        
        int total = requiredCourses.size();
        
        return new DegreeProgress(studentId, program, completed, total, remainingCourses);
    }

    /**
     * Add or update degree requirement
     * @param program Program name
     * @param requirement DegreeRequirement object
     */
    public void addProgramRequirement(String program, DegreeRequirement requirement) {
        programRequirements.put(program, requirement);
    }
}

/**
 * Helper class for degree requirements
 */
class DegreeRequirement {
    private String programName;
    private List<String> requiredCourses;
    private int minimumCredits;

    public DegreeRequirement(String programName) {
        this.programName = programName;
        this.requiredCourses = new ArrayList<>();
        this.minimumCredits = 0;
    }

    public void addRequiredCourse(String courseCode) {
        requiredCourses.add(courseCode);
    }

    public String getProgramName() {
        return programName;
    }

    public List<String> getRequiredCourses() {
        return requiredCourses;
    }

    public int getMinimumCredits() {
        return minimumCredits;
    }

    public void setMinimumCredits(int minimumCredits) {
        this.minimumCredits = minimumCredits;
    }
}
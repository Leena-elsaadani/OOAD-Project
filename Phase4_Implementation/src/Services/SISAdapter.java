package Services;

import RegistrationManagement.Registration;

/**
 * SISAdapter provides integration with Student Information System.
 * Acts as a facade for external system synchronization.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class SISAdapter {
    private static SISAdapter instance;

    /**
     * Private constructor for singleton pattern
     */
    private SISAdapter() {
    }

    /**
     * Get singleton instance
     * @return SISAdapter instance
     */
    public static SISAdapter getInstance() {
        if (instance == null) {
            instance = new SISAdapter();
        }
        return instance;
    }

    /**
     * Synchronize enrollment with external SIS
     * @param registration Registration to sync
     * @return true if sync successful
     */
    public boolean syncEnrollment(Registration registration) {
        // Simulate SIS API call
        System.out.println("[SIS SYNC] Syncing registration: " + registration.getRegistrationId());
        
        try {
            // Simulate network delay
            Thread.sleep(100);
            
            // In production, this would make actual API calls to SIS
            System.out.println("[SIS SYNC] Successfully synced: " + 
                             registration.getSectionId() + " - " + 
                             registration.getStatus());
            return true;
            
        } catch (InterruptedException e) {
            System.err.println("[SIS SYNC] Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Fetch student record from SIS
     * @param studentId Student ID to fetch
     * @return StudentRecord object (simulated)
     */
    public StudentRecord fetchStudentRecord(String studentId) {
        System.out.println("[SIS FETCH] Fetching student record for: " + studentId);
        
        // In production, this would query the SIS database
        // For now, return a simulated record
        return new StudentRecord(studentId, "Active", "Good Standing");
    }

    /**
     * Update withdrawal in SIS
     * @param studentId Student ID
     * @param sectionId Section ID
     * @return true if update successful
     */
    public boolean updateWithdrawal(String studentId, String sectionId) {
        System.out.println("[SIS UPDATE] Processing withdrawal for student " + 
                         studentId + " from section " + sectionId);
        
        try {
            Thread.sleep(100);
            System.out.println("[SIS UPDATE] Withdrawal processed successfully");
            return true;
        } catch (InterruptedException e) {
            System.err.println("[SIS UPDATE] Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verify student eligibility for registration
     * @param studentId Student ID to verify
     * @return true if eligible
     */
    public boolean verifyEligibility(String studentId) {
        System.out.println("[SIS VERIFY] Checking eligibility for: " + studentId);
        
        StudentRecord record = fetchStudentRecord(studentId);
        return record != null && "Active".equals(record.getStatus());
    }

    /**
     * Update student's enrolled credit hours in SIS
     * @param studentId Student ID
     * @param creditHours Total enrolled credit hours
     * @return true if update successful
     */
    public boolean updateCreditHours(String studentId, int creditHours) {
        System.out.println("[SIS UPDATE] Updating credit hours for " + 
                         studentId + " to " + creditHours);
        return true;
    }
}

/**
 * Helper class representing a student record from SIS
 */
class StudentRecord {
    private String studentId;
    private String status;
    private String academicStanding;

    public StudentRecord(String studentId, String status, String academicStanding) {
        this.studentId = studentId;
        this.status = status;
        this.academicStanding = academicStanding;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStatus() {
        return status;
    }

    public String getAcademicStanding() {
        return academicStanding;
    }

    @Override
    public String toString() {
        return "StudentRecord{" +
                "studentId='" + studentId + '\'' +
                ", status='" + status + '\'' +
                ", academicStanding='" + academicStanding + '\'' +
                '}';
    }
}
package RegistrationManagement;

import java.time.LocalDateTime;

/**
 * Registration class representing a registration transaction.
 * Tracks the lifecycle of a student's registration attempt.
 * Aligned with Phase 3 Design Class Diagram and State Machine.
 */
public class Registration {
    private String registrationId;
    private String studentId;
    private String sectionId;
    private RegistrationStatus status;
    private LocalDateTime timestamp;
    private String failureReason;

    /**
     * Constructor for Registration
     * @param registrationId Unique registration identifier
     * @param studentId ID of student registering
     * @param sectionId ID of section being registered for
     */
    public Registration(String registrationId, String studentId, String sectionId) {
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.sectionId = sectionId;
        this.status = RegistrationStatus.PENDING;
        this.timestamp = LocalDateTime.now();
        this.failureReason = null;
    }

    /**
     * Get the current status of this registration
     * @return RegistrationStatus enum value
     */
    public RegistrationStatus getStatus() {
        return status;
    }

    /**
     * Check if registration was successful
     * @return true if status is ENROLLED
     */
    public boolean isSuccessful() {
        return status == RegistrationStatus.ENROLLED;
    }

    /**
     * Mark registration as enrolled
     */
    public void markEnrolled() {
        this.status = RegistrationStatus.ENROLLED;
        this.failureReason = null;
    }

    /**
     * Mark registration as waitlisted
     */
    public void markWaitlisted() {
        this.status = RegistrationStatus.WAITLISTED;
        this.failureReason = "Section at full capacity";
    }

    /**
     * Mark registration as failed with reason
     * @param reason Failure reason
     */
    public void markFailed(String reason) {
        this.status = RegistrationStatus.FAILED;
        this.failureReason = reason;
    }

    /**
     * Mark registration as withdrawn
     */
    public void markWithdrawn() {
        if (this.status == RegistrationStatus.ENROLLED) {
            this.status = RegistrationStatus.WITHDRAWN;
        }
    }

    /**
     * Get formatted registration information
     * @return Formatted registration details
     */
    public String getRegistrationInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Registration ID: ").append(registrationId).append("\n");
        info.append("Section: ").append(sectionId).append("\n");
        info.append("Status: ").append(status.getDescription()).append("\n");
        info.append("Timestamp: ").append(timestamp).append("\n");
        if (failureReason != null) {
            info.append("Reason: ").append(failureReason).append("\n");
        }
        return info.toString();
    }

    // Getters and Setters
    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s: %s", 
                registrationId, sectionId, status.getDescription(),
                failureReason != null ? failureReason : "");
    }
}
package RegistrationManagement;

/**
 * Enum representing the status of a registration attempt.
 * Aligned with Phase 3 Design Class Diagram and State Machine.
 */
public enum RegistrationStatus {
    PENDING("Pending Processing"),
    ENROLLED("Successfully Enrolled"),
    WAITLISTED("Added to Waitlist"),
    FAILED("Registration Failed"),
    WITHDRAWN("Withdrawn by Student"),
    DROPPED("Dropped");

    private final String description;

    RegistrationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
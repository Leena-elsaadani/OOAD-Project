package RegistrationManagement;

/**
 * Enum for exception request types
 */
public enum ExceptionType {
    PREREQUISITE_OVERRIDE("Prerequisite Override"),
    CAPACITY_OVERRIDE("Capacity Override");

    private final String description;

    ExceptionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

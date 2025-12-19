package CourseManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Semester class representing an academic term.
 * Contains term dates and registration windows.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class Semester {
    private String termId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime registrationStart;
    private LocalDateTime registrationEnd;
    private String termName;

    /**
     * Constructor for Semester
     * @param termId Unique term identifier (e.g., "FALL2025")
     * @param termName Readable term name (e.g., "Fall 2025")
     * @param startDate Term start date
     * @param endDate Term end date
     */
    public Semester(String termId, String termName, LocalDate startDate, LocalDate endDate) {
        this.termId = termId;
        this.termName = termName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Check if registration is currently open
     * @return true if within registration window
     */
    public boolean isRegistrationOpen() {
        if (registrationStart == null || registrationEnd == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(registrationStart) && now.isBefore(registrationEnd);
    }

    /**
     * Check if the term is currently active
     * @return true if current date is within term dates
     */
    public boolean isActive() {
        LocalDate now = LocalDate.now();
        return !now.isBefore(startDate) && !now.isAfter(endDate);
    }

    /**
     * Get formatted term information
     * @return Formatted string with term details
     */
    public String getTermInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Term: ").append(termName).append(" (").append(termId).append(")\n");
        info.append("Dates: ").append(startDate).append(" to ").append(endDate).append("\n");
        if (registrationStart != null && registrationEnd != null) {
            info.append("Registration: ").append(registrationStart).append(" to ").append(registrationEnd).append("\n");
        }
        return info.toString();
    }

    // Getters and Setters
    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getRegistrationStart() {
        return registrationStart;
    }

    public void setRegistrationStart(LocalDateTime registrationStart) {
        this.registrationStart = registrationStart;
    }

    public LocalDateTime getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(LocalDateTime registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    @Override
    public String toString() {
        return termName + " (" + termId + ")";
    }
}
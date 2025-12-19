package CourseManagement;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

/**
 * Schedule class representing meeting times for a course section.
 * Handles time conflict detection.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class Schedule {
    private Set<DayOfWeek> meetingDays;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    /**
     * Constructor for Schedule
     * @param meetingDays Set of days the class meets
     * @param startTime Class start time
     * @param endTime Class end time
     * @param location Room/building location
     */
    public Schedule(Set<DayOfWeek> meetingDays, LocalTime startTime, LocalTime endTime, String location) {
        this.meetingDays = meetingDays;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    /**
     * Check if this schedule overlaps with another schedule
     * @param other Another Schedule object to compare
     * @return true if schedules conflict
     */
    public boolean overlaps(Schedule other) {
        if (other == null) {
            return false;
        }

        // Check if any days overlap
        boolean daysOverlap = false;
        for (DayOfWeek day : this.meetingDays) {
            if (other.meetingDays.contains(day)) {
                daysOverlap = true;
                break;
            }
        }

        if (!daysOverlap) {
            return false;
        }

        // Check if times overlap
        // Times overlap if: (start1 < end2) AND (end1 > start2)
        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime);
    }

    /**
     * Get formatted schedule string
     * @return Human-readable schedule
     */
    public String getScheduleString() {
        StringBuilder sb = new StringBuilder();
        
        // Format days
        List<String> dayAbbrev = new ArrayList<>();
        for (DayOfWeek day : meetingDays) {
            dayAbbrev.add(getDayAbbreviation(day));
        }
        Collections.sort(dayAbbrev);
        sb.append(String.join("", dayAbbrev));
        
        // Format times
        sb.append(" ").append(startTime.toString()).append("-").append(endTime.toString());
        
        return sb.toString();
    }

    /**
     * Get day abbreviation
     * @param day DayOfWeek enum
     * @return Single letter abbreviation
     */
    private String getDayAbbreviation(DayOfWeek day) {
        switch (day) {
            case MONDAY: return "M";
            case TUESDAY: return "T";
            case WEDNESDAY: return "W";
            case THURSDAY: return "R";
            case FRIDAY: return "F";
            case SATURDAY: return "S";
            case SUNDAY: return "U";
            default: return "";
        }
    }

    // Getters and Setters
    public Set<DayOfWeek> getMeetingDays() {
        return meetingDays;
    }

    public void setMeetingDays(Set<DayOfWeek> meetingDays) {
        this.meetingDays = meetingDays;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return getScheduleString() + " @ " + location;
    }
}
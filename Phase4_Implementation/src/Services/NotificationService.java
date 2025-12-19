package Services;

import UserManagement.Student;
import RegistrationManagement.Registration;
import java.util.List;

/**
 * NotificationService handles all system notifications.
 * Simulates sending emails, SMS, and in-app notifications.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class NotificationService {
    private static NotificationService instance;

    /**
     * Private constructor for singleton pattern
     */
    private NotificationService() {
    }

    /**
     * Get singleton instance
     * @return NotificationService instance
     */
    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    /**
     * Notify student with a message
     * @param studentId Student ID to notify
     * @param message Notification message
     */
    public void notifyStudent(String studentId, String message) {
        System.out.println("\n[NOTIFICATION to Student " + studentId + "]");
        System.out.println(message);
        System.out.println("------------------------");
    }

    /**
     * Notify faculty member with a message
     * @param facultyId Faculty ID to notify
     * @param message Notification message
     */
    public void notifyFaculty(String facultyId, String message) {
        System.out.println("\n[NOTIFICATION to Faculty " + facultyId + "]");
        System.out.println(message);
        System.out.println("------------------------");
    }

    /**
     * Send registration summary to student
     * @param student Student who registered
     * @param registrations List of registration attempts
     */
    public void sendRegistrationSummary(Student student, List<Registration> registrations) {
        StringBuilder summary = new StringBuilder();
        summary.append("\n========== REGISTRATION SUMMARY ==========\n");
        summary.append("Student: ").append(student.getName()).append("\n");
        summary.append("------------------------------------------\n");
        
        int enrolled = 0;
        int waitlisted = 0;
        int failed = 0;
        
        for (Registration reg : registrations) {
            summary.append(reg.getSectionId()).append(": ")
                   .append(reg.getStatus().getDescription());
            
            if (reg.getFailureReason() != null) {
                summary.append(" (").append(reg.getFailureReason()).append(")");
            }
            summary.append("\n");
            
            switch (reg.getStatus()) {
                case ENROLLED:
                    enrolled++;
                    break;
                case WAITLISTED:
                    waitlisted++;
                    break;
                case FAILED:
                    failed++;
                    break;
            }
        }
        
        summary.append("------------------------------------------\n");
        summary.append("Summary: ").append(enrolled).append(" enrolled, ")
               .append(waitlisted).append(" waitlisted, ")
               .append(failed).append(" failed\n");
        summary.append("==========================================\n");
        
        notifyStudent(student.getStudentId(), summary.toString());
    }

    /**
     * Send withdrawal confirmation
     * @param studentId Student ID
     * @param sectionId Section withdrawn from
     */
    public void sendWithdrawalConfirmation(String studentId, String sectionId) {
        String message = "You have successfully withdrawn from section: " + sectionId +
                        "\nThe section has been removed from your schedule.";
        notifyStudent(studentId, message);
    }

    /**
     * Send exception request update
     * @param studentId Student ID
     * @param sectionId Section ID
     * @param approved Whether request was approved
     * @param comments Reviewer comments
     */
    public void sendExceptionUpdate(String studentId, String sectionId, 
                                   boolean approved, String comments) {
        StringBuilder message = new StringBuilder();
        message.append("Exception Request Update for Section: ").append(sectionId).append("\n");
        message.append("Status: ").append(approved ? "APPROVED" : "REJECTED").append("\n");
        
        if (comments != null && !comments.isEmpty()) {
            message.append("Comments: ").append(comments).append("\n");
        }
        
        if (approved) {
            message.append("\nYou may now register for this section.");
        }
        
        notifyStudent(studentId, message.toString());
    }

    /**
     * Send course announcement to all enrolled students
     * @param enrolledStudents List of enrolled students
     * @param sectionId Section ID
     * @param announcement Announcement text
     */
    public void sendCourseAnnouncement(List<Student> enrolledStudents, 
                                      String sectionId, String announcement) {
        for (Student student : enrolledStudents) {
            String message = "Course Announcement for " + sectionId + ":\n\n" + announcement;
            notifyStudent(student.getStudentId(), message);
        }
    }

    /**
     * Send registration reminder
     * @param studentId Student ID
     * @param registrationStart Registration start date/time
     */
    public void sendRegistrationReminder(String studentId, String registrationStart) {
        String message = "Reminder: Your registration window opens on " + registrationStart +
                        "\nPrepare your course selections in advance!";
        notifyStudent(studentId, message);
    }

    /**
     * Send hold notification
     * @param studentId Student ID
     * @param holdType Type of hold placed
     */
    public void sendHoldNotification(String studentId, String holdType) {
        String message = "IMPORTANT: A " + holdType + " hold has been placed on your account.\n" +
                        "This may affect your ability to register for courses.\n" +
                        "Please contact the appropriate office to resolve this hold.";
        notifyStudent(studentId, message);
    }
}
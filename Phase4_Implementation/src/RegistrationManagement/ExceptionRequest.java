package RegistrationManagement;

import java.time.LocalDateTime;

/**
 * ExceptionRequest class for handling prerequisite and capacity overrides.
 * Tracks approval workflow for registration exceptions.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class ExceptionRequest {
    private String requestId;
    private String studentId;
    private String sectionId;
    private ExceptionType type;
    private RequestStatus status;
    private String reason;
    private String reviewerComments;
    private LocalDateTime requestDate;
    private LocalDateTime reviewDate;

    /**
     * Constructor for ExceptionRequest
     * @param requestId Unique request identifier
     * @param studentId ID of student requesting exception
     * @param sectionId Section ID for the request
     * @param type Type of exception (PREREQUISITE or CAPACITY)
     * @param reason Student's reason for request
     */
    public ExceptionRequest(String requestId, String studentId, String sectionId, 
                           ExceptionType type, String reason) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.sectionId = sectionId;
        this.type = type;
        this.reason = reason;
        this.status = RequestStatus.PENDING;
        this.requestDate = LocalDateTime.now();
    }

    /**
     * Approve the exception request
     */
    public void approve() {
        this.status = RequestStatus.APPROVED;
        this.reviewDate = LocalDateTime.now();
        System.out.println("Exception request " + requestId + " has been APPROVED.");
    }

    /**
     * Reject the exception request with comments
     * @param comment Reviewer's comments for rejection
     */
    public void reject(String comment) {
        this.status = RequestStatus.REJECTED;
        this.reviewerComments = comment;
        this.reviewDate = LocalDateTime.now();
        System.out.println("Exception request " + requestId + " has been REJECTED.");
    }

    /**
     * Check if request is pending
     * @return true if status is PENDING
     */
    public boolean isPending() {
        return status == RequestStatus.PENDING;
    }

    /**
     * Check if request is approved
     * @return true if status is APPROVED
     */
    public boolean isApproved() {
        return status == RequestStatus.APPROVED;
    }

    /**
     * Get formatted request information
     * @return Formatted request details
     */
    public String getRequestInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n=== EXCEPTION REQUEST ===\n");
        info.append("Request ID: ").append(requestId).append("\n");
        info.append("Student ID: ").append(studentId).append("\n");
        info.append("Section ID: ").append(sectionId).append("\n");
        info.append("Type: ").append(type).append("\n");
        info.append("Status: ").append(status).append("\n");
        info.append("Request Date: ").append(requestDate).append("\n");
        info.append("Student Reason: ").append(reason).append("\n");
        
        if (reviewDate != null) {
            info.append("Review Date: ").append(reviewDate).append("\n");
        }
        if (reviewerComments != null) {
            info.append("Reviewer Comments: ").append(reviewerComments).append("\n");
        }
        info.append("========================\n");
        
        return info.toString();
    }

    // Getters and Setters
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public ExceptionType getType() {
        return type;
    }

    public void setType(ExceptionType type) {
        this.type = type;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s for %s (Status: %s)",
                requestId, type, studentId, sectionId, status);
    }
}

/**
 * Enum for request status
 */
enum RequestStatus {
    PENDING("Pending Review"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String description;

    RequestStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
package UserManagement;

/**
 * Abstract base class representing a system user.
 * Implements common attributes and behaviors for all user types.
 * Part of User Management package from Phase 3 design.
 */
public abstract class User {
    private String userId;
    private String name;
    private String email;
    private String passwordHash;
    private String role;

    /**
     * Constructor for User
     * @param userId Unique identifier for the user
     * @param name Full name of the user
     * @param email Email address
     * @param passwordHash Hashed password for authentication
     * @param role User role (Student, Faculty, Administrator)
     */
    public User(String userId, String name, String email, String passwordHash, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    /**
     * Get contact information for the user
     * @return Formatted contact information string
     */
    public String getContactInfo() {
        return "Name: " + name + "\nEmail: " + email + "\nUser ID: " + userId;
    }

    /**
     * Authenticate user credentials
     * @param password Password to verify
     * @return true if authentication successful, false otherwise
     */
    public boolean authenticate(String password) {
        // Simple authentication - in production, use proper hashing
        return this.passwordHash.equals(password);
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
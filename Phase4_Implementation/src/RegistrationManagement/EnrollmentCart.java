package RegistrationManagement;

import CourseManagement.CourseSection;
import java.util.*;

/**
 * EnrollmentCart class representing a student's temporary course selections.
 * Holds sections before final registration submission.
 * Aligned with Phase 3 Design Class Diagram.
 */
public class EnrollmentCart {
    private String cartId;
    private String studentId;
    private List<CourseSection> items;

    /**
     * Constructor for EnrollmentCart
     * @param studentId ID of student who owns this cart
     */
    public EnrollmentCart(String studentId) {
        this.cartId = "CART-" + studentId + "-" + System.currentTimeMillis();
        this.studentId = studentId;
        this.items = new ArrayList<>();
    }

    /**
     * Add a course section to the cart
     * @param section CourseSection to add
     * @return true if added successfully, false if already in cart
     */
    public boolean addItem(CourseSection section) {
        if (section == null) {
            return false;
        }
        
        // Check if already in cart
        for (CourseSection item : items) {
            if (item.getSectionId().equals(section.getSectionId())) {
                System.out.println("Section already in cart.");
                return false;
            }
        }
        
        items.add(section);
        return true;
    }

    /**
     * Remove a section from cart by section ID
     * @param sectionId Section ID to remove
     */
    public void removeItem(String sectionId) {
        items.removeIf(section -> section.getSectionId().equals(sectionId));
    }

    /**
     * Validate cart contents for basic issues
     * @return ValidationResult with any warnings
     */
    public ValidationResult validateCart() {
        ValidationResult result = new ValidationResult();
        
        if (items.isEmpty()) {
            result.addError("Cart is empty");
            return result;
        }
        
        // Check for schedule conflicts within cart
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                CourseSection section1 = items.get(i);
                CourseSection section2 = items.get(j);
                
                if (section1.getSchedule().overlaps(section2.getSchedule())) {
                    result.addError("Schedule conflict: " + section1.getSectionId() + 
                                  " and " + section2.getSectionId());
                }
            }
        }
        
        // Check for full sections
        for (CourseSection section : items) {
            if (section.isFull()) {
                result.addWarning("Section " + section.getSectionId() + 
                                " is full - will be waitlisted");
            }
        }
        
        return result;
    }

    /**
     * Clear all items from cart
     */
    public void clear() {
        items.clear();
    }

    /**
     * Get number of items in cart
     * @return Cart size
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Check if cart is empty
     * @return true if no items
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Display cart contents
     * @return Formatted cart display
     */
    public String displayCart() {
        if (items.isEmpty()) {
            return "Cart is empty.";
        }
        
        StringBuilder display = new StringBuilder();
        display.append("\n=== ENROLLMENT CART ===\n");
        display.append(String.format("%-15s | %-10s | %-30s | %s\n", 
                "Section ID", "Course", "Schedule", "Seats"));
        display.append("-------------------------------------------------------------------------\n");
        
        for (CourseSection section : items) {
            display.append(section.getSectionInfo()).append("\n");
        }
        
        display.append("-------------------------------------------------------------------------\n");
        display.append("Total sections: ").append(items.size()).append("\n");
        
        return display.toString();
    }

    // Getters and Setters
    public String getCartId() {
        return cartId;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<CourseSection> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public String toString() {
        return "EnrollmentCart{" +
                "cartId='" + cartId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", items=" + items.size() +
                '}';
    }
}
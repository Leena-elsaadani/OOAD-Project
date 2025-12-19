package RegistrationManagement;

import java.util.*;

/**
 * Helper class for cart validation results
 */
public class ValidationResult {
    private List<String> errors;
    private List<String> warnings;
    
    public ValidationResult() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }
    
    public void addError(String error) {
        errors.add(error);
    }
    
    public void addWarning(String warning) {
        warnings.add(warning);
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public boolean hasWarnings() {
        return !warnings.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!errors.isEmpty()) {
            sb.append("Errors:\n");
            for (String error : errors) {
                sb.append("  ✗ ").append(error).append("\n");
            }
        }
        if (!warnings.isEmpty()) {
            sb.append("Warnings:\n");
            for (String warning : warnings) {
                sb.append("  ⚠ ").append(warning).append("\n");
            }
        }
        return sb.toString();
    }
}

# Online Course Registration System - Phase 4 Implementation

## Project Overview

This is the Phase 4 implementation of an **Online Course Registration System** for a university. The system is a fully functional, console-based application that demonstrates complete course registration and management workflows.

## Features Implemented

### 1. User Management
- **Student accounts** - Browse courses, register, drop courses, create academic plans
- **Faculty accounts** - View assigned sections, review student rosters, approve/reject exceptions
- **Administrator accounts** - Manage courses, users, sections, define registration periods, set rules

### 2. Core Functionalities

#### Student Features:
- Browse available courses with prerequisites and schedules
- Add courses to enrollment cart
- Register for multiple courses with validation:
  - Prerequisite checking
  - Schedule conflict detection
  - Capacity verification
- View registered courses
- Drop courses from enrollment
- Create and manage academic plans
- View degree progress and remaining requirements
- Request exception overrides (prerequisite/capacity)

#### Faculty Features:
- View assigned course sections
- View student rosters for each section
- Review and approve/reject exception requests
- Post course announcements

#### Administrator Features:
- Add/edit/remove courses from catalog
- Add/edit/remove users (students, faculty, admins)
- Manage course sections (add, update, remove)
- Define registration periods
- Configure registration rules (max credits, conflict checking)
- Generate reports (enrollment, capacity, student-specific)
- View all exception requests

### 3. System Services
- **Registration Engine** - Validates and processes course registrations
- **Notification Service** - Handles student communications
- **Degree Audit Engine** - Evaluates academic progress
- **SIS Adapter** - Integrates with student information system

## Project Structure

```
src/
├── Main.java                          # Console application entry point
├── UserManagement/
│   ├── User.java                      # Base class for all users
│   ├── Student.java                   # Student implementation
│   ├── Faculty.java                   # Faculty implementation
│   └── Administrator.java             # Administrator implementation
├── CourseManagement/
│   ├── Course.java                    # Course catalog entry
│   ├── CourseSection.java             # Specific course offering
│   ├── Schedule.java                  # Class meeting schedule
│   └── Semester.java                  # Academic term definition
├── RegistrationManagement/
│   ├── Registration.java              # Registration transaction record
│   ├── RegistrationStatus.java        # Status enumeration
│   ├── EnrollmentCart.java            # Student's course selection cart
│   └── ExceptionRequest.java          # Prerequisite/capacity override requests
├── Services/
│   ├── RegistrationEngine.java        # Core registration logic
│   ├── NotificationService.java       # Communication service
│   └── SISAdapter.java                # External system integration
├── AcademicPlanning/
│   ├── AcademicPlan.java              # Multi-term degree planning
│   └── DegreeAuditEngine.java         # Degree completion tracking
└── Data/
    └── DataStore.java                 # In-memory data repository
```

## How to Run

### Prerequisites
- Java 8 or higher
- Compiled `.class` files in the project directory

### Compilation
```bash
cd src
javac -d . *.java UserManagement/*.java CourseManagement/*.java \
  RegistrationManagement/*.java Services/*.java AcademicPlanning/*.java Data/*.java
```

### Execution
```bash
cd src
java Main
```

## Sample Usage Walkthrough

### 1. Student Registration
```
1. Select "Login as Student"
2. Enter credentials (e.g., U001 / pass123)
3. Browse available courses
4. Add CS201-01 and CS301-01 to cart
5. Review cart for conflicts
6. Submit registration
```

### 2. Faculty Actions
```
1. Select "Login as Faculty"
2. Enter credentials (e.g., U003 / pass123)
3. View my course sections
4. View student roster
5. Review exception requests
6. Post announcements
```

### 3. Administrator Management
```
1. Select "Login as Administrator"
2. Enter credentials (e.g., U005 / admin123)
3. Manage courses (add/remove)
4. Manage users (add/remove/change roles)
5. Manage sections
6. Define registration periods
7. Generate reports
```

## Sample Data

Pre-loaded accounts for testing:
- **Students:**
  - U001 / Ahmed Ali (pass123) - CS Major
  - U002 / Sara Mohamed (pass123) - Business Major
- **Faculty:**
  - U003 / Dr. Omar Hassan (pass123) - CS Department
  - U004 / Dr. Layla Ibrahim (pass123) - Math Department
- **Administrators:**
  - U005 / Admin User (admin123)

Sample courses:
- CS201 - Data Structures (3 credits, prerequisite: CS101)
- CS301 - Object-Oriented Analysis (3 credits, prerequisite: CS201)
- MATH201 - Calculus II (4 credits, prerequisite: MATH101)
- BUS101 - Introduction to Business (3 credits, no prerequisites)
- ECON101 - Principles of Economics (3 credits, no prerequisites)

## Key Design Decisions

### 1. Singleton Pattern
- `DataStore`, `RegistrationEngine`, `NotificationService`, `DegreeAuditEngine` use singleton pattern for centralized access to system services

### 2. Inheritance Hierarchy
- All user types inherit from abstract `User` base class
- Ensures consistent authentication and profile management

### 3. Validation Architecture
- Multi-level validation:
  - Prerequisites checked before enrollment
  - Schedule conflicts detected automatically
  - Capacity limits enforced
  - Student holds block registration

### 4. In-Memory Data Management
- Uses `HashMap` and `ArrayList` collections
- Suitable for prototype and demonstration
- Can be replaced with database in production

### 5. Menu-Driven Console Interface
- Simple, intuitive navigation
- Clear prompts and feedback
- Error handling with retry logic

## Testing Scenarios

1. **Test prerequisite validation:**
   - Try to register U002 for CS201 (should fail - missing CS101)
   - U001 can register (has CS101 and MATH101)

2. **Test schedule conflict detection:**
   - Add CS201-01 (MWF 9-10) and CS301-01 (TR 11-12:30) to cart
   - Should succeed (no conflicts)
   - Try to add another MWF 9:30 course
   - Should detect conflict

3. **Test capacity limits:**
   - ECON101-01 has capacity 3
   - Register multiple students
   - 4th registration should go to waitlist

4. **Test exception workflow:**
   - Student requests prerequisite override
   - Faculty reviews and approves/rejects
   - System tracks status

## Future Enhancements

- Persistent database backend (MySQL/PostgreSQL)
- Web-based user interface
- Real email notifications
- Waitlist auto-enrollment when seats open
- Payment processing integration
- GPA-based course eligibility
- Advanced reporting and analytics
- Mobile application
- Integration with external SIS systems

## Class Responsibilities

### User Classes
- **User:** Authentication, profile management
- **Student:** Cart management, registration, academic planning
- **Faculty:** Section management, student interactions, exception approval
- **Administrator:** System configuration, data management

### Course Management
- **Course:** Catalog-level course data, prerequisites
- **CourseSection:** Specific course offering, enrollment, scheduling
- **Schedule:** Meeting times, conflict detection
- **Semester:** Academic term definition

### Registration Management
- **Registration:** Transaction record, status tracking
- **EnrollmentCart:** Temporary course selection, validation
- **ExceptionRequest:** Override request tracking, approval workflow

### Services
- **RegistrationEngine:** Core validation and enrollment logic
- **NotificationService:** Student notifications and confirmations
- **DegreeAuditEngine:** Degree progress evaluation
- **SISAdapter:** External system synchronization

### Data Management
- **DataStore:** Central repository, singleton access pattern

## Enums

- **RegistrationStatus:** PENDING, ENROLLED, WAITLISTED, FAILED, WITHDRAWN, DROPPED
- **ExceptionType:** PREREQUISITE_OVERRIDE, CAPACITY_OVERRIDE
- **RequestStatus:** PENDING, APPROVED, REJECTED

## Error Handling

- Input validation for all user entries
- Try-catch for I/O operations
- Fallback defaults for parsing errors
- Clear error messages to users
- Exception request workflow for overrides

## Compliance

✓ Follows Phase 3 design class diagram
✓ Implements all required classes
✓ Contains complete core functionality
✓ Demonstrates proper OOP principles
✓ Includes comprehensive documentation
✓ Provides console-based demonstration
✓ Ready for academic evaluation

---

**Version:** 4.0 (Phase 4 Implementation)
**Date:** December 2025
**Team:** Object-Oriented Analysis and Design Course

import UserManagement.*;
import CourseManagement.*;
import RegistrationManagement.*;
import Services.*;
import AcademicPlanning.*;
import Data.DataStore;
import java.util.*;
import java.util.InputMismatchException;

/**
 * Main console application for Online Course Registration System.
 * Provides menu-driven interface for students, faculty, and administrators.
 * Phase 4 Implementation - aligned with Phase 3 design.
 */
public class Main {
    private static DataStore dataStore;
    private static Scanner scanner;
    private static RegistrationEngine registrationEngine;
    private static NotificationService notificationService;
    private static DegreeAuditEngine degreeAuditEngine;
    
    public static void main(String[] args) {
        initialize();
        showWelcomeScreen();
        mainMenu();
    }
    
    /**
     * Initialize system components
     */
    private static void initialize() {
        scanner = new Scanner(System.in);
        dataStore = DataStore.getInstance();
        registrationEngine = RegistrationEngine.getInstance();
        notificationService = NotificationService.getInstance();
        degreeAuditEngine = DegreeAuditEngine.getInstance();
        
        System.out.println("=================================================");
        System.out.println("Online Course Registration System - Initializing");
        System.out.println("=================================================\n");
    }
    
    /**
     * Display welcome screen
     */
    private static void showWelcomeScreen() {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   UNIVERSITY COURSE REGISTRATION SYSTEM      ║");
        System.out.println("║              Fall 2025 Term                   ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");
    }
    
//     /**
//      * Main menu - login selection
//      */
//     private static void mainMenu() {
//         while (true) {
//             System.out.println("\n========== MAIN MENU ==========");
//             System.out.println("1. Login as Student");
//             System.out.println("2. Login as Faculty");
//             System.out.println("3. Login as Administrator");
//             System.out.println("4. View System Information");
//             System.out.println("5. Exit");
//             System.out.println("===============================");
//             System.out.print("Select option: ");
            
//             int choice = getIntInput();
            
//             switch (choice) {
//                 case 1:
//                     loginStudent();
//                     break;
//                 case 2:
//                     loginFaculty();
//                     break;
//                 case 3:
//                     loginAdministrator();
//                     break;
//                 case 4:
//                     viewSystemInfo();
//                     break;
//                 case 5:
//                     exitSystem();
//                     return;
//                 default:
//                     System.out.println("Invalid option. Please try again.");
//             }
//         }
//     }
    
//     // === STUDENT METHODS ===
//     private static void loginStudent() {
//         System.out.println("\n--- Student Login ---");
//         System.out.print("Enter User ID (e.g., U001): ");
//         String userId = scanner.nextLine();
//         System.out.print("Enter Password: ");
//         String password = scanner.nextLine();
        
//         User user = dataStore.authenticateUser(userId, password);
        
//         if (user != null && user instanceof Student) {
//             Student student = (Student) user;
//             System.out.println("\nWelcome, " + student.getName() + "!");
//             studentMenu(student);
//         } else {
//             System.out.println("Invalid credentials or not a student account.");
//         }
//     }
    
//     private static void studentMenu(Student student) {
//         while (true) {
//             System.out.println("\n========== STUDENT MENU ==========");
//             System.out.println("Student: " + student.getName() + " (" + student.getStudentId() + ")");
//             System.out.println("Program: " + student.getProgram());
//             System.out.println("----------------------------------");
//             System.out.println("1. Browse Available Courses");
//             System.out.println("2. Add Course to Cart");
//             System.out.println("3. View Enrollment Cart");
//             System.out.println("4. Register for Courses");
//             System.out.println("5. View My Registered Courses");
//             System.out.println("6. Drop a Course");
//             System.out.println("7. Create Academic Plan");
//             System.out.println("8. View Degree Progress");
//             System.out.println("9. Request Exception Override");
//             System.out.println("10. Logout");
//             System.out.println("==================================");
//             System.out.print("Select option: ");
            
//             int choice = getIntInput();
            
//             switch (choice) {
//                 case 1:
//                     browseCourses(student);
//                     break;
//                 case 2:
//                     addCourseToCart(student);
//                     break;
//                 case 3:
//                     viewCart(student);
//                     break;
//                 case 4:
//                     registerCourses(student);
//                     break;
//                 case 5:
//                     viewRegisteredCourses(student);
//                     break;
//                 case 6:
//                     dropCourse(student);
//                     break;
//                 case 7:
//                     createAcademicPlan(student);
//                     break;
//                 case 8:
//                     viewDegreeProgress(student);
//                     break;
//                 case 9:
//                     requestException(student);
//                     break;
//                 case 10:
//                     dataStore.logout();
//                     return;
//                 default:
//                     System.out.println("Invalid option.");
//             }
//         }
//     }
    
//     private static void browseCourses(Student student) {
//         System.out.println("\n========== AVAILABLE COURSES ==========");
        
//         Map<String, Course> courses = dataStore.getCourses();
//         Map<String, CourseSection> sections = dataStore.getCourseSections();
        
//         System.out.println(String.format("%-10s | %-30s | %s | %s", 
//                           "Code", "Title", "Credits", "Prerequisites"));
//         System.out.println("---------------------------------------------------------------");
        
//         for (Course course : courses.values()) {
//             System.out.println(course.toString());
            
//             // Show sections for this course
//             for (CourseSection section : sections.values()) {
//                 if (section.getCourseCode().equals(course.getCourseCode())) {
//                     System.out.println("  → " + section.getSectionInfo());
//                 }
//             }
//             System.out.println();
//         }
//     }
    
//     private static void addCourseToCart(Student student) {
//         System.out.println("\n--- Add Course to Cart ---");
//         System.out.print("Enter Section ID (e.g., CS201-01): ");
//         String sectionId = scanner.nextLine();
        
//         CourseSection section = dataStore.findSectionById(sectionId);
        
//         if (section == null) {
//             System.out.println("Error: Section not found.");
//             return;
//         }
        
//         EnrollmentCart cart = student.getActiveCart();
//         if (cart.addItem(section)) {
//             System.out.println("✓ Section added to cart successfully!");
//         } else {
//             System.out.println("✗ Could not add section to cart.");
//         }
//     }
    
//     private static void viewCart(Student student) {
//         EnrollmentCart cart = student.getActiveCart();
//         System.out.println(cart.displayCart());
        
//         // Validate cart
//         var validation = cart.validateCart();
//         if (validation.hasErrors() || validation.hasWarnings()) {
//             System.out.println("\n" + validation.toString());
//         }
//     }
    
//     private static void registerCourses(Student student) {
//         System.out.println("\n========== COURSE REGISTRATION ==========");
        
//         EnrollmentCart cart = student.getActiveCart();
        
//         if (cart.isEmpty()) {
//             System.out.println("Your cart is empty. Add courses first.");
//             return;
//         }
        
//         System.out.println("You are about to register for " + cart.getSize() + " course(s).");
//         System.out.print("Confirm registration? (yes/no): ");
//         String confirm = scanner.nextLine();
        
//         if (!confirm.equalsIgnoreCase("yes")) {
//             System.out.println("Registration cancelled.");
//             return;
//         }
        
//         System.out.println("\nProcessing registration...\n");
        
//         // Get enrolled sections for conflict checking
//         List<CourseSection> enrolledSections = dataStore.getEnrolledSections(student.getStudentId());
        
//         // Process registration
//         List<Registration> results = registrationEngine.processCart(
//             student, 
//             cart, 
//             dataStore.getCourses(),
//             enrolledSections
//         );
        
//         // Save registrations and update sections
//         for (Registration reg : results) {
//             dataStore.addRegistration(reg);
//         }
        
//         // Clear cart after processing
//         cart.clear();
        
//         System.out.println("\n✓ Registration process completed!");
//         pauseScreen();
//     }
    
//     private static void viewRegisteredCourses(Student student) {
//         List<CourseSection> sections = dataStore.getEnrolledSections(student.getStudentId());
        
//         if (sections.isEmpty()) {
//             System.out.println("You are not registered for any courses.");
//             return;
//         }
        
//         System.out.println("\n========== MY REGISTERED COURSES ==========");
//         System.out.println(String.format("%-15s | %-10s | %-30s | %s",
//                           "Section", "Course", "Schedule", "Instructor"));
//         System.out.println("-------------------------------------------------------------------------");
        
//         for (CourseSection section : sections) {
//             Course course = dataStore.findCourseByCode(section.getCourseCode());
//             System.out.println(String.format("%-15s | %-10s | %-30s | %s",
//                               section.getSectionId(),
//                               section.getCourseCode(),
//                               section.getSchedule() != null ? section.getSchedule().toString() : "TBD",
//                               section.getInstructorId()));
//         }
//         System.out.println("-------------------------------------------------------------------------");
//         System.out.println("Total courses: " + sections.size());
//     }
    
//     private static void dropCourse(Student student) {
//         List<CourseSection> sections = dataStore.getEnrolledSections(student.getStudentId());
        
//         if (sections.isEmpty()) {
//             System.out.println("You are not registered for any courses.");
//             return;
//         }
        
//         System.out.println("\n--- Drop Course ---");
//         System.out.println("Your registered courses:");
//         for (int i = 0; i < sections.size(); i++) {
//             System.out.println((i + 1) + ". " + sections.get(i).getSectionInfo());
//         }
        
//         System.out.print("\nEnter section ID to drop: ");
//         String sectionId = scanner.nextLine();
        
//         CourseSection section = dataStore.findSectionById(sectionId);
        
//         if (section == null) {
//             System.out.println("Section not found.");
//             return;
//         }
        
//         if (section.removeStudent(student)) {
//             System.out.println("✓ Successfully dropped " + sectionId);
//             notificationService.sendWithdrawalConfirmation(student.getStudentId(), sectionId);
//         } else {
//             System.out.println("✗ Failed to drop course.");
//         }
//     }
    
//     private static void createAcademicPlan(Student student) {
//         System.out.println("\n--- Create Academic Plan ---");
//         System.out.print("Enter plan name: ");
//         String planName = scanner.nextLine();
        
//         AcademicPlan plan = new AcademicPlan(planName, student.getStudentId());
//         student.setAcademicPlan(plan); // assuming setter exists
        
//         System.out.println("Plan created! Add courses to your plan.");
//         System.out.print("Enter term ID (e.g., SPRING2026): ");
//         String termId = scanner.nextLine();
        
//         System.out.print("Enter course codes (comma-separated): ");
//         String courses = scanner.nextLine();
        
//         for (String courseCode : courses.split(",")) {
//             plan.addCourse(termId, courseCode.trim());
//         }
        
//         System.out.println(plan.displayPlan());
//     }
    
//     private static void viewDegreeProgress(Student student) {
//         var progress = degreeAuditEngine.checkDegreeCompletion(
//             student.getStudentId(),
//             student.getProgram(),
//             student.getCompletedCourses()
//         );
        
//         System.out.println(progress.displayProgress());
//     }
    
//     private static void requestException(Student student) {
//         System.out.println("\n--- Request Exception Override ---");
//         System.out.print("Enter section ID: ");
//         String sectionId = scanner.nextLine();
        
//         System.out.println("Exception type:");
//         System.out.println("1. Prerequisite Override");
//         System.out.println("2. Capacity Override");
//         System.out.print("Select: ");
//         int type = getIntInput();
        
//         System.out.print("Enter reason for request: ");
//         String reason = scanner.nextLine();
        
//         ExceptionRequest request = new ExceptionRequest(
//             "REQ-" + System.currentTimeMillis(),
//             student.getStudentId(),
//             sectionId,
//             type == 1 ? ExceptionType.PREREQUISITE_OVERRIDE : ExceptionType.CAPACITY_OVERRIDE,
//             reason
//         );
        
//         dataStore.addExceptionRequest(request);
//         System.out.println("✓ Exception request submitted successfully!");
//         System.out.println("Request ID: " + request.getRequestId());
//     }
    
//     // === FACULTY METHODS ===
//     private static void loginFaculty() {
//         System.out.println("\n--- Faculty Login ---");
//         System.out.print("Enter User ID (e.g., U003): ");
//         String userId = scanner.nextLine();
//         System.out.print("Enter Password: ");
//         String password = scanner.nextLine();
        
//         User user = dataStore.authenticateUser(userId, password);
        
//         if (user != null && user instanceof Faculty) {
//             Faculty faculty = (Faculty) user;
//             System.out.println("\nWelcome, " + faculty.getName() + "!");
//             facultyMenu(faculty);
//         } else {
//             System.out.println("Invalid credentials or not a faculty account.");
//         }
//     }
    
//     private static void facultyMenu(Faculty faculty) {
//         while (true) {
//             System.out.println("\n========== FACULTY MENU ==========");
//             System.out.println("Faculty: " + faculty.getName());
//             System.out.println("Department: " + faculty.getDepartment());
//             System.out.println("----------------------------------");
//             System.out.println("1. View My Course Sections");
//             System.out.println("2. View Student Roster");
//             System.out.println("3. Review Exception Requests");
//             System.out.println("4. Post Course Announcement");
//             System.out.println("5. Logout");
//             System.out.println("==================================");
//             System.out.print("Select option: ");
            
//             int choice = getIntInput();
            
//             switch (choice) {
//                 case 1:
//                     viewFacultySections(faculty);
//                     break;
//                 case 2:
//                     viewStudentRoster(faculty);
//                     break;
//                 case 3:
//                     reviewExceptionRequests(faculty);
//                     break;
//                 case 4:
//                     postAnnouncement(faculty);
//                     break;
//                 case 5:
//                     dataStore.logout();
//                     return;
//                 default:
//                     System.out.println("Invalid option.");
//             }
//         }
//     }
    
//     private static void viewFacultySections(Faculty faculty) {
//         List<CourseSection> sections = faculty.getTaughtSections();
        
//         if (sections.isEmpty()) {
//             System.out.println("You are not assigned to any sections.");
//             return;
//         }
        
//         System.out.println("\n========== MY COURSE SECTIONS ==========");
//         for (CourseSection section : sections) {
//             Course course = dataStore.findCourseByCode(section.getCourseCode());
//             System.out.println("\n" + section.getSectionId() + " - " + 
//                              (course != null ? course.getTitle() : section.getCourseCode()));
//             System.out.println(section.getSectionInfo());
//         }
//     }
    
//     private static void viewStudentRoster(Faculty faculty) {
//         List<CourseSection> sections = faculty.getTaughtSections();
        
//         if (sections.isEmpty()) {
//             System.out.println("You have no sections.");
//             return;
//         }
        
//         System.out.println("\n--- View Student Roster ---");
//         System.out.println("Your sections:");
//         for (int i = 0; i < sections.size(); i++) {
//             System.out.println((i + 1) + ". " + sections.get(i).getSectionId());
//         }
        
//         System.out.print("\nSelect section number: ");
//         int choice = getIntInput() - 1;
        
//         if (choice < 0 || choice >= sections.size()) {
//             System.out.println("Invalid selection.");
//             return;
//         }
        
//         CourseSection section = sections.get(choice);
//         List<Student> roster = dataStore.getStudentsInSection(section.getSectionId());
        
//         System.out.println("\n========== STUDENT ROSTER: " + section.getSectionId() + " ==========");
//         System.out.println(String.format("%-12s | %-25s | %s",
//                           "Student ID", "Name", "Email"));
//         System.out.println("------------------------------------------------------------");
        
//         if (roster.isEmpty()) {
//             System.out.println("No students enrolled yet.");
//         } else {
//             for (Student student : roster) {
//                 System.out.println(String.format("%-12s | %-25s | %s",
//                                   student.getStudentId(),
//                                   student.getName(),
//                                   student.getEmail()));
//             }
//         }
//         System.out.println("------------------------------------------------------------");
//         System.out.println("Total students: " + roster.size());
//     }
    
//     private static void reviewExceptionRequests(Faculty faculty) {
//         List<ExceptionRequest> allRequests = dataStore.getExceptionRequests();
//         List<ExceptionRequest> myRequests = new ArrayList<>();
        
//         for (ExceptionRequest req : allRequests) {
//             CourseSection section = dataStore.findSectionById(req.getSectionId());
//             if (section != null && section.getInstructorId().equals(faculty.getFacultyId())) {
//                 myRequests.add(req);
//             }
//         }
        
//         if (myRequests.isEmpty()) {
//             System.out.println("No pending exception requests.");
//             return;
//         }
        
//         System.out.println("\n========== EXCEPTION REQUESTS ==========");
//         for (int i = 0; i < myRequests.size(); i++) {
//             System.out.println((i + 1) + ". " + myRequests.get(i).toString());
//         }
        
//         System.out.print("\nSelect request to review (0 to cancel): ");
//         int choice = getIntInput() - 1;
        
//         if (choice < 0 || choice >= myRequests.size()) {
//             return;
//         }
        
//         ExceptionRequest request = myRequests.get(choice);
//         System.out.println(request.getRequestInfo());
        
//         System.out.println("\n1. Approve");
//         System.out.println("2. Reject");
//         System.out.print("Select action: ");
//         int action = getIntInput();
        
//         if (action == 1) {
//             request.setStatus("Approved");
//             System.out.println("✓ Request approved.");
//         } else if (action == 2) {
//             System.out.print("Enter rejection reason: ");
//             String comment = scanner.nextLine();
//             request.setStatus("Rejected: " + comment);
//             System.out.println("✗ Request rejected.");
//         }
//     }
    
//     private static void postAnnouncement(Faculty faculty) {
//         List<CourseSection> sections = faculty.getTaughtSections();
        
//         if (sections.isEmpty()) {
//             System.out.println("You have no sections.");
//             return;
//         }
        
//         System.out.println("\n--- Post Course Announcement ---");
//         System.out.println("Your sections:");
//         for (int i = 0; i < sections.size(); i++) {
//             System.out.println((i + 1) + ". " + sections.get(i).getSectionId());
//         }
        
//         System.out.print("\nSelect section: ");
//         int choice = getIntInput() - 1;
        
//         if (choice < 0 || choice >= sections.size()) {
//             System.out.println("Invalid selection.");
//             return;
//         }
        
//         System.out.print("Enter announcement: ");
//         String announcement = scanner.nextLine();
        
//         CourseSection section = sections.get(choice);
//         // Assume notificationService handles announcements
//         notificationService.sendAnnouncement(section.getSectionId(), announcement);
//         System.out.println("✓ Announcement posted!");
//     }
    
//     // === ADMINISTRATOR METHODS ===
//     private static void loginAdministrator() {
//         System.out.println("\n--- Administrator Login ---");
//         System.out.print("Enter User ID (e.g., U005): ");
//         String userId = scanner.nextLine();
//         System.out.print("Enter Password: ");
//         String password = scanner.nextLine();
        
//         User user = dataStore.authenticateUser(userId, password);
        
//         if (user != null && user instanceof Administrator) {
//             Administrator admin = (Administrator) user;
//             System.out.println("\nWelcome, " + admin.getName() + "!");
//             administratorMenu(admin);
//         } else {
//             System.out.println("Invalid credentials or not an administrator account.");
//         }
//     }
    
//     private static void administratorMenu(Administrator admin) {
//         while (true) {
//             System.out.println("\n========== ADMINISTRATOR MENU ==========");
//             System.out.println("Admin: " + admin.getName());
//             System.out.println("---------------------------------------");
//             System.out.println("1. Manage Courses");
//             System.out.println("2. Manage Users");
//             System.out.println("3. Manage Course Sections");
//             System.out.println("4. Define Registration Period");
//             System.out.println("5. Set Registration Rules");
//             System.out.println("6. Generate Reports");
//             System.out.println("7. View All Exception Requests");
//             System.out.println("8. Logout");
//             System.out.println("========================================");
//             System.out.print("Select option: ");
            
//             int choice = getIntInput();
            
//             switch (choice) {
//                 case 1:
//                     manageCourses(admin);
//                     break;
//                 case 2:
//                     manageUsers(admin);
//                     break;
//                 case 3:
//                     manageCourseSections(admin);
//                     break;
//                 case 4:
//                     defineRegistrationPeriod(admin);
//                     break;
//                 case 5:
//                     setRegistrationRules(admin);
//                     break;
//                 case 6:
//                     generateReports(admin);
//                     break;
//                 case 7:
//                     viewAllExceptionRequests();
//                     break;
//                 case 8:
//                     dataStore.logout();
//                     return;
//                 default:
//                     System.out.println("Invalid option.");
//             }
//         }
//     }
    
//     private static void manageCourses(Administrator admin) {
//         System.out.println("\n--- Manage Courses ---");
//         System.out.println("1. Add Course");
//         System.out.println("2. View All Courses");
//         System.out.println("3. Remove Course");
//         System.out.print("Select: ");
        
//         int choice = getIntInput();
        
//         switch (choice) {
//             case 1:
//                 addCourse(admin);
//                 break;
//             case 2:
//                 viewAllCourses();
//                 break;
//             case 3:
//                 removeCourse(admin);
//                 break;
//         }
//     }
    
//     private static void addCourse(Administrator admin) {
//         System.out.println("\n--- Add New Course ---");
//         System.out.print("Course Code: ");
//         String code = scanner.nextLine();
//         System.out.print("Title: ");
//         String title = scanner.nextLine();
//         System.out.print("Credit Hours: ");
//         int credits = getIntInput();
//         scanner.nextLine(); // consume newline
//         System.out.print("Description: ");
//         String description = scanner.nextLine();
        
//         Course course = new Course(code, title, credits, description);
        
//         System.out.print("Prerequisites (comma-separated, or press Enter for none): ");
//         String prereqs = scanner.nextLine();
//         if (!prereqs.trim().isEmpty()) {
//             for (String prereq : prereqs.split(",")) {
//                 course.addPrerequisite(prereq.trim());
//             }
//         }
        
//         dataStore.addCourse(course);
//         System.out.println("✓ Course added successfully!");
//     }
    
//     private static void viewAllCourses() {
//         System.out.println("\n========== ALL COURSES ==========");
//         Map<String, Course> courses = dataStore.getCourses();
//         if (courses.isEmpty()) {
//             System.out.println("No courses available.");
//             return;
//         }
//         for (Course course : courses.values()) {
//             System.out.println(course.getDetailedInfo());
//         }
//     }
    
//     private static void removeCourse(Administrator admin) {
//         System.out.print("\nEnter course code to remove: ");
//         String code = scanner.nextLine();
        
//         if (dataStore.getCourses().remove(code) != null) {
//             System.out.println("✓ Course removed.");
//         } else {
//             System.out.println("✗ Course not found.");
//         }
//     }
    
//     private static void manageUsers(Administrator admin) {
//         System.out.println("\n--- Manage Users ---");
//         System.out.println("1. Add User");
//         System.out.println("2. View All Users");
//         System.out.println("3. Change User Role");
//         System.out.print("Select: ");
        
//         int choice = getIntInput();
        
//         switch (choice) {
//             case 1:
//                 addUser(admin);
//                 break;
//             case 2:
//                 viewAllUsers();
//                 break;
//             case 3:
//                 changeUserRole(admin);
//                 break;
//         }
//     }
    
//     private static void addUser(Administrator admin) {
//         System.out.println("\n--- Add New User ---");
//         System.out.println("User Type:");
//         System.out.println("1. Student");
//         System.out.println("2. Faculty");
//         System.out.println("3. Administrator");
//         System.out.print("Select: ");
        
//         int type = getIntInput();
//         scanner.nextLine(); // consume newline
        
//         System.out.print("User ID: ");
//         String userId = scanner.nextLine();
//         System.out.print("Name: ");
//         String name = scanner.nextLine();
//         System.out.print("Email: ");
//         String email = scanner.nextLine();
//         System.out.print("Password: ");
//         String password = scanner.nextLine();
        
//         User user = null;
        
//         switch (type) {
//             case 1:
//                 System.out.print("Student ID: ");
//                 String studentId = scanner.nextLine();
//                 System.out.print("Program: ");
//                 String program = scanner.nextLine();
//                 user = new Student(userId, name, email, password, studentId, program);
//                 break;
//             case 2:
//                 System.out.print("Faculty ID: ");
//                 String facultyId = scanner.nextLine();
//                 System.out.print("Department: ");
//                 String department = scanner.nextLine();
//                 user = new Faculty(userId, name, email, password, facultyId, department);
//                 break;
//             case 3:
//                 System.out.print("Admin ID: ");
//                 String adminId = scanner.nextLine();
//                 user = new Administrator(userId, name, email, password, adminId);
//                 break;
//             default:
//                 System.out.println("Invalid user type.");
//                 return;
//         }
        
//         if (user != null) {
//             dataStore.addUser(user);
//             System.out.println("✓ User added successfully!");
//         }
//     }
    
//     private static void viewAllUsers() {
//         System.out.println("\n========== ALL USERS ==========");
        
//         System.out.println("\nStudents:");
//         for (Student s : dataStore.getStudents().values()) {
//             System.out.println("  " + s.toString());
//         }
        
//         System.out.println("\nFaculty:");
//         for (Faculty f : dataStore.getFaculty().values()) {
//             System.out.println("  " + f.toString());
//         }
        
//         System.out.println("\nAdministrators:");
//         for (Administrator a : dataStore.getAdministrators().values()) {
//             System.out.println("  " + a.toString());
//         }
//     }
    
//     private static void changeUserRole(Administrator admin) {
//         System.out.print("\nEnter User ID: ");
//         String userId = scanner.nextLine();
        
//         User user = dataStore.getUsers().get(userId);
//         if (user == null) {
//             System.out.println("User not found.");
//             return;
//         }
        
//         System.out.println("Current role: " + user.getRole());
//         System.out.print("New role (Student/Faculty/Administrator): ");
//         String newRole = scanner.nextLine();
        
//         // In a real system, you'd recreate user with new role
//         // For now, just acknowledge
//         System.out.println("✓ Role change request noted. (Implementation depends on DataStore design)");
//     }
    
//     private static void manageCourseSections(Administrator admin) {
//         System.out.println("\n--- Manage Course Sections ---");
//         System.out.println("1. Add Section");
//         System.out.println("2. View All Sections");
//         System.out.println("3. Remove Section");
//         System.out.print("Select: ");
        
//         int choice = getIntInput();
        
//         switch (choice) {
//             case 1:
//                 addCourseSection(admin);
//                 break;
//             case 2:
//                 viewAllSections();
//                 break;
//             case 3:
//                 removeCourseSection(admin);
//                 break;
//         }
//     }
    
//     private static void addCourseSection(Administrator admin) {
//         System.out.println("\n--- Add New Section ---");
//         System.out.print("Section ID (e.g., CS201-01): ");
//         String sectionId = scanner.nextLine();
//         System.out.print("Course Code: ");
//         String courseCode = scanner.nextLine();
//         System.out.print("Instructor ID: ");
//         String instructorId = scanner.nextLine();
//         System.out.print("Capacity: ");
//         int capacity = getIntInput();
//         System.out.print("Schedule (e.g., MWF 10-11): ");
//         String scheduleStr = scanner.nextLine();
        
//         Schedule schedule = new Schedule(scheduleStr); // assuming constructor exists
//         CourseSection section = new CourseSection(sectionId, courseCode, instructorId, capacity, schedule);
        
//         dataStore.addCourseSection(section);
//         System.out.println("✓ Section added successfully!");
//     }
    
//     private static void viewAllSections() {
//         System.out.println("\n========== ALL COURSE SECTIONS ==========");
//         Map<String, CourseSection> sections = dataStore.getCourseSections();
//         if (sections.isEmpty()) {
//             System.out.println("No sections available.");
//             return;
//         }
//         for (CourseSection section : sections.values()) {
//             System.out.println(section.getSectionInfo());
//         }
//     }
    
//     private static void removeCourseSection(Administrator admin) {
//         System.out.print("\nEnter section ID to remove: ");
//         String sectionId = scanner.nextLine();
        
//         if (dataStore.getCourseSections().remove(sectionId) != null) {
//             System.out.println("✓ Section removed.");
//         } else {
//             System.out.println("✗ Section not found.");
//         }
//     }
    
//     private static void defineRegistrationPeriod(Administrator admin) {
//         System.out.println("\n--- Define Registration Period ---");
//         System.out.print("Start Date (YYYY-MM-DD): ");
//         String start = scanner.nextLine();
//         System.out.print("End Date (YYYY-MM-DD): ");
//         String end = scanner.nextLine();
//         System.out.println("✓ Registration period set from " + start + " to " + end);
//         // In real system: update Semester or RegistrationEngine
//     }
    
//     private static void setRegistrationRules(Administrator admin) {
//         System.out.println("\n--- Set Registration Rules ---");
//         System.out.println("• Max credit hours per term: 18");
//         System.out.println("• Concurrent enrollment limit: 6 courses");
//         System.out.println("• Overrides require faculty approval");
//         System.out.println("✓ Rules updated (simulated).");
//     }
    
//     private static void generateReports(Administrator admin) {
//         System.out.println("\n--- Generate Reports ---");
//         System.out.println("1. Enrollment Summary");
//         System.out.println("2. Course Utilization");
//         System.out.println("3. Exception Request Log");
//         System.out.print("Select report: ");
//         int choice = getIntInput();
//         System.out.println("✓ Report generated. (Output would go to file or console)");
//     }
    
//     private static void viewAllExceptionRequests() {
//         List<ExceptionRequest> requests = dataStore.getExceptionRequests();
//         if (requests.isEmpty()) {
//             System.out.println("\nNo exception requests.");
//             return;
//         }
//         System.out.println("\n========== ALL EXCEPTION REQUESTS ==========");
//         for (ExceptionRequest req : requests) {
//             System.out.println(req.toString());
//         }
//     }
    
//     // === SYSTEM METHODS ===
//     private static void viewSystemInfo() {
//         System.out.println("\n========== SYSTEM INFORMATION ==========");
//         System.out.println("Current Term: Fall 2025");
//         System.out.println("Total Users: " + dataStore.getUsers().size());
//         System.out.println("Total Courses: " + dataStore.getCourses().size());
//         System.out.println("Total Sections: " + dataStore.getCourseSections().size());
//         System.out.println("Active Registrations: " + dataStore.getRegistrations().size());
//         System.out.println("Pending Exceptions: " + dataStore.getExceptionRequests().size());
//         System.out.println("========================================");
//     }
    
//     private static void exitSystem() {
//         System.out.println("\nThank you for using the University Course Registration System!");
//         System.out.println("Goodbye!\n");
    
    /**
     * Main menu - login selection
     */
    private static void mainMenu() {
        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Faculty");
            System.out.println("3. Login as Administrator");
            System.out.println("4. View System Information");
            System.out.println("5. Exit");
            System.out.println("===============================");
            System.out.print("Select option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    loginStudent();
                    break;
                case 2:
                    loginFaculty();
                    break;
                case 3:
                    loginAdministrator();
                    break;
                case 4:
                    viewSystemInfo();
                    break;
                case 5:
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    /**
     * Student login and menu
     */
    private static void loginStudent() {
        System.out.println("\n--- Student Login ---");
        System.out.print("Enter User ID (e.g., U001): ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        User user = dataStore.authenticateUser(userId, password);
        
        if (user != null && user instanceof Student) {
            Student student = (Student) user;
            System.out.println("\nWelcome, " + student.getName() + "!");
            studentMenu(student);
        } else {
            System.out.println("Invalid credentials or not a student account.");
        }
    }
    
    /**
     * Student menu
     */
    private static void studentMenu(Student student) {
        while (true) {
            System.out.println("\n========== STUDENT MENU ==========");
            System.out.println("Student: " + student.getName() + " (" + student.getStudentId() + ")");
            System.out.println("Program: " + student.getProgram());
            System.out.println("----------------------------------");
            System.out.println("1. Browse Available Courses");
            System.out.println("2. Add Course to Cart");
            System.out.println("3. View Enrollment Cart");
            System.out.println("4. Register for Courses");
            System.out.println("5. View My Registered Courses");
            System.out.println("6. Drop a Course");
            System.out.println("7. Create Academic Plan");
            System.out.println("8. View Degree Progress");
            System.out.println("9. Request Exception Override");
            System.out.println("10. Logout");
            System.out.println("==================================");
            System.out.print("Select option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    browseCourses(student);
                    break;
                case 2:
                    addCourseToCart(student);
                    break;
                case 3:
                    viewCart(student);
                    break;
                case 4:
                    registerCourses(student);
                    break;
                case 5:
                    viewRegisteredCourses(student);
                    break;
                case 6:
                    dropCourse(student);
                    break;
                case 7:
                    createAcademicPlan(student);
                    break;
                case 8:
                    viewDegreeProgress(student);
                    break;
                case 9:
                    requestException(student);
                    break;
                case 10:
                    dataStore.logout();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    /**
     * Browse available courses
     */
    private static void browseCourses(Student student) {
        System.out.println("\n========== AVAILABLE COURSES ==========");
        
        Map<String, Course> courses = dataStore.getCourses();
        Map<String, CourseSection> sections = dataStore.getCourseSections();
        
        System.out.println(String.format("%-10s | %-30s | %s | %s", 
                          "Code", "Title", "Credits", "Prerequisites"));
        System.out.println("---------------------------------------------------------------");
        
        for (Course course : courses.values()) {
            System.out.println(course.toString());
            
            // Show sections for this course
            for (CourseSection section : sections.values()) {
                if (section.getCourseCode().equals(course.getCourseCode())) {
                    System.out.println("  → " + section.getSectionInfo());
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Add course section to cart
     */
    private static void addCourseToCart(Student student) {
        System.out.println("\n--- Add Course to Cart ---");
        System.out.print("Enter Section ID (e.g., CS201-01): ");
        String sectionId = scanner.nextLine();
        
        CourseSection section = dataStore.findSectionById(sectionId);
        
        if (section == null) {
            System.out.println("Error: Section not found.");
            return;
        }
        
        EnrollmentCart cart = student.getActiveCart();
        if (cart.addItem(section)) {
            System.out.println("✓ Section added to cart successfully!");
        } else {
            System.out.println("✗ Could not add section to cart.");
        }
    }
    
    /**
     * View enrollment cart
     */
    private static void viewCart(Student student) {
        EnrollmentCart cart = student.getActiveCart();
        System.out.println(cart.displayCart());
        
        // Validate cart
        var validation = cart.validateCart();
        if (validation.hasErrors() || validation.hasWarnings()) {
            System.out.println("\n" + validation.toString());
        }
    }
    
    /**
     * Register for courses in cart
     */
    private static void registerCourses(Student student) {
        System.out.println("\n========== COURSE REGISTRATION ==========");
        
        EnrollmentCart cart = student.getActiveCart();
        
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add courses first.");
            return;
        }
        
        System.out.println("You are about to register for " + cart.getSize() + " course(s).");
        System.out.print("Confirm registration? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Registration cancelled.");
            return;
        }
        
        System.out.println("\nProcessing registration...\n");
        
        // Get enrolled sections for conflict checking
        List<CourseSection> enrolledSections = dataStore.getEnrolledSections(student.getStudentId());
        
        // Process registration
        List<Registration> results = registrationEngine.processCart(
            student, 
            cart, 
            dataStore.getCourses(),
            enrolledSections
        );
        
        // Save registrations and update sections
        for (Registration reg : results) {
            dataStore.addRegistration(reg);
        }
        
        // Clear cart after processing
        cart.clear();
        
        System.out.println("\n✓ Registration process completed!");
        pauseScreen();
    }
    
    /**
     * View registered courses
     */
    private static void viewRegisteredCourses(Student student) {
        System.out.println("\n========== MY REGISTERED COURSES ==========");
        
        List<CourseSection> sections = dataStore.getEnrolledSections(student.getStudentId());
        
        if (sections.isEmpty()) {
            System.out.println("You are not registered for any courses.");
            return;
        }
        
        System.out.println(String.format("%-15s | %-10s | %-30s | %s",
                          "Section", "Course", "Schedule", "Instructor"));
        System.out.println("-------------------------------------------------------------------------");
        
        for (CourseSection section : sections) {
            Course course = dataStore.findCourseByCode(section.getCourseCode());
            System.out.println(String.format("%-15s | %-10s | %-30s | %s",
                              section.getSectionId(),
                              section.getCourseCode(),
                              section.getSchedule().toString(),
                              section.getInstructorId()));
        }
        
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Total courses: " + sections.size());
    }
    
    /**
     * Drop a course
     */
    private static void dropCourse(Student student) {
        System.out.println("\n--- Drop Course ---");
        
        List<CourseSection> sections = dataStore.getEnrolledSections(student.getStudentId());
        
        if (sections.isEmpty()) {
            System.out.println("You are not registered for any courses.");
            return;
        }
        
        // Show enrolled courses
        System.out.println("Your registered courses:");
        for (int i = 0; i < sections.size(); i++) {
            System.out.println((i + 1) + ". " + sections.get(i).getSectionInfo());
        }
        
        System.out.print("\nEnter section ID to drop: ");
        String sectionId = scanner.nextLine();
        
        CourseSection section = dataStore.findSectionById(sectionId);
        
        if (section == null) {
            System.out.println("Section not found.");
            return;
        }
        
        if (section.removeStudent(student)) {
            System.out.println("✓ Successfully dropped " + sectionId);
            notificationService.sendWithdrawalConfirmation(student.getStudentId(), sectionId);
        } else {
            System.out.println("✗ Failed to drop course.");
        }
    }
    
    /**
     * Create academic plan
     */
    private static void createAcademicPlan(Student student) {
        System.out.println("\n--- Create Academic Plan ---");
        System.out.print("Enter plan name: ");
        String planName = scanner.nextLine();
        
        AcademicPlan plan = student.createAcademicPlan(planName);
        
        System.out.println("Plan created! Add courses to your plan.");
        System.out.print("Enter term ID (e.g., SPRING2026): ");
        String termId = scanner.nextLine();
        
        System.out.print("Enter course codes (comma-separated): ");
        String courses = scanner.nextLine();
        
        for (String courseCode : courses.split(",")) {
            plan.addCourse(termId, courseCode.trim());
        }
        
        System.out.println(plan.displayPlan());
    }
    
    /**
     * View degree progress
     */
    private static void viewDegreeProgress(Student student) {
        var progress = degreeAuditEngine.checkDegreeCompletion(
            student.getStudentId(),
            student.getProgram(),
            student.getCompletedCourses()
        );
        
        System.out.println(progress.displayProgress());
    }
    
    /**
     * Request exception override
     */
    private static void requestException(Student student) {
        System.out.println("\n--- Request Exception Override ---");
        System.out.print("Enter section ID: ");
        String sectionId = scanner.nextLine();
        
        System.out.println("Exception type:");
        System.out.println("1. Prerequisite Override");
        System.out.println("2. Capacity Override");
        System.out.print("Select: ");
        int type = getIntInput();
        
        System.out.print("Enter reason for request: ");
        String reason = scanner.nextLine();
        
        ExceptionRequest request = new ExceptionRequest(
            "REQ-" + System.currentTimeMillis(),
            student.getStudentId(),
            sectionId,
            type == 1 ? ExceptionType.PREREQUISITE_OVERRIDE : ExceptionType.CAPACITY_OVERRIDE,
            reason
        );
        
        dataStore.addExceptionRequest(request);
        System.out.println("✓ Exception request submitted successfully!");
        System.out.println("Request ID: " + request.getRequestId());
    }
    
    /**
     * Faculty login
     */
    private static void loginFaculty() {
        System.out.println("\n--- Faculty Login ---");
        System.out.print("Enter User ID (e.g., U003): ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        User user = dataStore.authenticateUser(userId, password);
        
        if (user != null && user instanceof Faculty) {
            Faculty faculty = (Faculty) user;
            System.out.println("\nWelcome, " + faculty.getName() + "!");
            facultyMenu(faculty);
        } else {
            System.out.println("Invalid credentials or not a faculty account.");
        }
    }
    
    /**
     * Faculty menu
     */
    private static void facultyMenu(Faculty faculty) {
        while (true) {
            System.out.println("\n========== FACULTY MENU ==========");
            System.out.println("Faculty: " + faculty.getName());
            System.out.println("Department: " + faculty.getDepartment());
            System.out.println("----------------------------------");
            System.out.println("1. View My Course Sections");
            System.out.println("2. View Student Roster");
            System.out.println("3. Review Exception Requests");
            System.out.println("4. Post Course Announcement");
            System.out.println("5. Logout");
            System.out.println("==================================");
            System.out.print("Select option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewFacultySections(faculty);
                    break;
                case 2:
                    viewStudentRoster(faculty);
                    break;
                case 3:
                    reviewExceptionRequests(faculty);
                    break;
                case 4:
                    postAnnouncement(faculty);
                    break;
                case 5:
                    dataStore.logout();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    /**
     * View faculty's course sections
     */
    private static void viewFacultySections(Faculty faculty) {
        System.out.println("\n========== MY COURSE SECTIONS ==========");
        
        List<CourseSection> sections = faculty.getTaughtSections();
        
        if (sections.isEmpty()) {
            System.out.println("You are not assigned to any sections.");
            return;
        }
        
        for (CourseSection section : sections) {
            Course course = dataStore.findCourseByCode(section.getCourseCode());
            System.out.println("\n" + section.getSectionId() + " - " + 
                             (course != null ? course.getTitle() : section.getCourseCode()));
            System.out.println(section.getSectionInfo());
        }
    }
    
    /**
     * View student roster for a section
     */
    private static void viewStudentRoster(Faculty faculty) {
        System.out.println("\n--- View Student Roster ---");
        
        List<CourseSection> sections = faculty.getTaughtSections();
        
        if (sections.isEmpty()) {
            System.out.println("You have no sections.");
            return;
        }
        
        System.out.println("Your sections:");
        for (int i = 0; i < sections.size(); i++) {
            System.out.println((i + 1) + ". " + sections.get(i).getSectionId());
        }
        
        System.out.print("\nSelect section number: ");
        int choice = getIntInput() - 1;
        
        if (choice < 0 || choice >= sections.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        CourseSection section = sections.get(choice);
        List<Student> roster = faculty.getRoster(section);
        
        System.out.println("\n========== STUDENT ROSTER: " + section.getSectionId() + " ==========");
        System.out.println(String.format("%-12s | %-25s | %s",
                          "Student ID", "Name", "Email"));
        System.out.println("------------------------------------------------------------");
        
        if (roster.isEmpty()) {
            System.out.println("No students enrolled yet.");
        } else {
            for (Student student : roster) {
                System.out.println(String.format("%-12s | %-25s | %s",
                                  student.getStudentId(),
                                  student.getName(),
                                  student.getEmail()));
            }
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Total students: " + roster.size());
    }
    
    /**
     * Review exception requests
     */
    private static void reviewExceptionRequests(Faculty faculty) {
        System.out.println("\n========== EXCEPTION REQUESTS ==========");
        
        List<ExceptionRequest> allRequests = dataStore.getExceptionRequests();
        List<ExceptionRequest> myRequests = new ArrayList<>();
        
        // Filter requests for faculty's sections
        for (ExceptionRequest req : allRequests) {
            CourseSection section = dataStore.findSectionById(req.getSectionId());
            if (section != null && section.getInstructorId().equals(faculty.getFacultyId())) {
                myRequests.add(req);
            }
        }
        
        if (myRequests.isEmpty()) {
            System.out.println("No pending exception requests.");
            return;
        }
        
        for (int i = 0; i < myRequests.size(); i++) {
            System.out.println((i + 1) + ". " + myRequests.get(i).toString());
        }
        
        System.out.print("\nSelect request to review (0 to cancel): ");
        int choice = getIntInput() - 1;
        
        if (choice < 0 || choice >= myRequests.size()) {
            return;
        }
        
        ExceptionRequest request = myRequests.get(choice);
        System.out.println(request.getRequestInfo());
        
        System.out.println("\n1. Approve");
        System.out.println("2. Reject");
        System.out.print("Select action: ");
        int action = getIntInput();
        
        if (action == 1) {
            faculty.approveException(request);
        } else if (action == 2) {
            System.out.print("Enter rejection reason: ");
            String comment = scanner.nextLine();
            faculty.rejectException(request, comment);
        }
    }
    
    /**
     * Post announcement
     */
    private static void postAnnouncement(Faculty faculty) {
        System.out.println("\n--- Post Course Announcement ---");
        
        List<CourseSection> sections = faculty.getTaughtSections();
        
        if (sections.isEmpty()) {
            System.out.println("You have no sections.");
            return;
        }
        
        System.out.println("Your sections:");
        for (int i = 0; i < sections.size(); i++) {
            System.out.println((i + 1) + ". " + sections.get(i).getSectionId());
        }
        
        System.out.print("\nSelect section: ");
        int choice = getIntInput() - 1;
        
        if (choice < 0 || choice >= sections.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        System.out.print("Enter announcement: ");
        String announcement = scanner.nextLine();
        
        CourseSection section = sections.get(choice);
        faculty.postAnnouncement(section, announcement);
        
        System.out.println("✓ Announcement posted!");
    }
    
    /**
     * Administrator login
     */
    private static void loginAdministrator() {
        System.out.println("\n--- Administrator Login ---");
        System.out.print("Enter User ID (e.g., U005): ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        User user = dataStore.authenticateUser(userId, password);
        
        if (user != null && user instanceof Administrator) {
            Administrator admin = (Administrator) user;
            System.out.println("\nWelcome, " + admin.getName() + "!");
            administratorMenu(admin);
        } else {
            System.out.println("Invalid credentials or not an administrator account.");
        }
    }
    
    /**
     * Administrator menu
     */
    private static void administratorMenu(Administrator admin) {
        while (true) {
            System.out.println("\n========== ADMINISTRATOR MENU ==========");
            System.out.println("Admin: " + admin.getName());
            System.out.println("---------------------------------------");
            System.out.println("1. Manage Courses");
            System.out.println("2. Manage Users");
            System.out.println("3. Manage Course Sections");
            System.out.println("4. Define Registration Period");
            System.out.println("5. Set Registration Rules");
            System.out.println("6. Generate Reports");
            System.out.println("7. View All Exception Requests");
            System.out.println("8. Logout");
            System.out.println("========================================");
            System.out.print("Select option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    manageCourses(admin);
                    break;
                case 2:
                    manageUsers(admin);
                    break;
                case 3:
                    manageCourseSections(admin);
                    break;
                case 4:
                    defineRegistrationPeriod(admin);
                    break;
                case 5:
                    setRegistrationRules(admin);
                    break;
                case 6:
                    generateReports(admin);
                    break;
                case 7:
                    viewAllExceptionRequests();
                    break;
                case 8:
                    dataStore.logout();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    /**
     * Manage courses
     */
    private static void manageCourses(Administrator admin) {
        System.out.println("\n--- Manage Courses ---");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Remove Course");
        System.out.print("Select: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                addCourse(admin);
                break;
            case 2:
                viewAllCourses();
                break;
            case 3:
                removeCourse(admin);
                break;
        }
    }
    
    /**
     * Add new course
     */
    private static void addCourse(Administrator admin) {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Credit Hours: ");
        int credits = getIntInput();
        scanner.nextLine(); // consume newline
        System.out.print("Description: ");
        String description = scanner.nextLine();
        
        Course course = new Course(code, title, credits, description);
        
        System.out.print("Prerequisites (comma-separated, or press Enter for none): ");
        String prereqs = scanner.nextLine();
        if (!prereqs.trim().isEmpty()) {
            for (String prereq : prereqs.split(",")) {
                course.addPrerequisite(prereq.trim());
            }
        }
        
        dataStore.addCourse(course);
        admin.addCourse(course);
        System.out.println("✓ Course added successfully!");
    }
    
    /**
     * View all courses
     */
    private static void viewAllCourses() {
        System.out.println("\n========== ALL COURSES ==========");
        
        Map<String, Course> courses = dataStore.getCourses();
        
        for (Course course : courses.values()) {
            System.out.println(course.getDetailedInfo());
        }
    }
    
    /**
     * Remove course
     */
    private static void removeCourse(Administrator admin) {
        System.out.print("\nEnter course code to remove: ");
        String code = scanner.nextLine();
        
        if (dataStore.getCourses().remove(code) != null) {
            admin.removeCourse(code);
            System.out.println("✓ Course removed.");
        } else {
            System.out.println("✗ Course not found.");
        }
    }
    
    /**
     * Manage users
     */
    private static void manageUsers(Administrator admin) {
        System.out.println("\n--- Manage Users ---");
        System.out.println("1. Add User");
        System.out.println("2. View All Users");
        System.out.println("3. Change User Role");
        System.out.print("Select: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                addUser(admin);
                break;
            case 2:
                viewAllUsers();
                break;
            case 3:
                changeUserRole(admin);
                break;
        }
    }
    
    /**
     * Add new user
     */
    private static void addUser(Administrator admin) {
        System.out.println("\n--- Add New User ---");
        System.out.println("User Type:");
        System.out.println("1. Student");
        System.out.println("2. Faculty");
        System.out.println("3. Administrator");
        System.out.print("Select: ");
        
        int type = getIntInput();
        scanner.nextLine(); // consume newline
        
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        User user = null;
        
        switch (type) {
            case 1:
                System.out.print("Student ID: ");
                String studentId = scanner.nextLine();
                System.out.print("Program: ");
                String program = scanner.nextLine();
                user = new Student(userId, name, email, password, studentId, program);
                break;
            case 2:
                System.out.print("Faculty ID: ");
                String facultyId = scanner.nextLine();
                System.out.print("Department: ");
                String department = scanner.nextLine();
                user = new Faculty(userId, name, email, password, facultyId, department);
                break;
            case 3:
                System.out.print("Admin ID: ");
                String adminId = scanner.nextLine();
                user = new Administrator(userId, name, email, password, adminId);
                break;
        }
        
        if (user != null) {
            dataStore.addUser(user);
            System.out.println("✓ User added successfully!");
        }
    }
    
    /**
     * View all users
     */
    private static void viewAllUsers() {
        System.out.println("\n========== ALL USERS ==========");
        
        System.out.println("\nStudents:");
        for (Student s : dataStore.getStudents().values()) {
            System.out.println("  " + s.toString());
        }
        
        System.out.println("\nFaculty:");
        for (Faculty f : dataStore.getFaculty().values()) {
            System.out.println("  " + f.toString());
        }
        
        System.out.println("\nAdministrators:");
        for (Administrator a : dataStore.getAdministrators().values()) {
            System.out.println("  " + a.toString());
        }
    }
    
    /**
     * Change user role
     */
    private static void changeUserRole(Administrator admin) {
        System.out.print("\nEnter User ID: ");
        String userId = scanner.nextLine();
        
        User user = dataStore.getUsers().get(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        System.out.println("Current role: " + user.getRole());
        System.out.print("New role (Student/Faculty/Administrator): ");
        String newRole = scanner.nextLine();
        
        admin.manageUserRoles(user, newRole);
        System.out.println("✓ User role updated!");
    }
    
    /**
     * Manage course sections
     */
    private static void manageCourseSections(Administrator admin) {
        System.out.println("\n--- Manage Course Sections ---");
        System.out.println("1. Add Section");
        System.out.println("2. View All Sections");
        System.out.println("3. Update Section");
        System.out.println("4. Remove Section");
        System.out.print("Select: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                addSection(admin);
                break;
            case 2:
                viewAllSections();
                break;
            case 3:
                updateSection(admin);
                break;
            case 4:
                removeSection(admin);
                break;
        }
    }
    
    /**
     * Add new section
     */
    private static void addSection(Administrator admin) {
        System.out.println("\n--- Add New Section ---");
        System.out.print("Section ID (e.g., CS201-01): ");
        String sectionId = scanner.nextLine();
        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Instructor ID: ");
        String instructorId = scanner.nextLine();
        System.out.print("Capacity: ");
        int capacity = getIntInput();
        scanner.nextLine(); // consume newline
        System.out.print("Room: ");
        String room = scanner.nextLine();
        
        System.out.println("Schedule - Days (e.g., MWF): ");
        String days = scanner.nextLine();
        System.out.print("Start Time (e.g., 09:00): ");
        String startTime = scanner.nextLine();
        System.out.print("End Time (e.g., 10:30): ");
        String endTime = scanner.nextLine();
        
        Set<java.time.DayOfWeek> daySet = parseDays(days);
        java.time.LocalTime startTimeObj = parseTime(startTime);
        java.time.LocalTime endTimeObj = parseTime(endTime);
        
        Schedule schedule = new Schedule(daySet, startTimeObj, endTimeObj, room);
        
        // Get the semester for this section
        Semester semester = dataStore.getSemesters().values().stream().findFirst().orElse(null);
        
        if (semester != null) {
            CourseSection section = new CourseSection(sectionId, courseCode, semester, capacity, schedule);
            section.assignInstructor(instructorId);
            dataStore.addCourseSection(section);
            System.out.println("✓ Section added successfully!");
        } else {
            System.out.println("Error: No semester defined.");
        }
    }
    
    /**
     * View all sections
     */
    private static void viewAllSections() {
        System.out.println("\n========== ALL SECTIONS ==========");
        
        Map<String, CourseSection> sections = dataStore.getCourseSections();
        
        for (CourseSection section : sections.values()) {
            System.out.println(section.getSectionInfo());
            System.out.println("---");
        }
    }
    
    /**
     * Update section
     */
    private static void updateSection(Administrator admin) {
        System.out.print("\nEnter Section ID to update: ");
        String sectionId = scanner.nextLine();
        
        CourseSection section = dataStore.findSectionById(sectionId);
        if (section == null) {
            System.out.println("Section not found.");
            return;
        }
        
        System.out.println("Current capacity: " + section.getCapacity());
        System.out.print("New capacity: ");
        int newCapacity = getIntInput();
        scanner.nextLine();
        
        section.setCapacity(newCapacity);
        System.out.println("✓ Section updated!");
    }
    
    /**
     * Remove section
     */
    private static void removeSection(Administrator admin) {
        System.out.print("\nEnter Section ID to remove: ");
        String sectionId = scanner.nextLine();
        
        if (dataStore.getCourseSections().remove(sectionId) != null) {
            System.out.println("✓ Section removed.");
        } else {
            System.out.println("✗ Section not found.");
        }
    }
    
    /**
     * Define registration period
     */
    private static void defineRegistrationPeriod(Administrator admin) {
        System.out.println("\n--- Define Registration Period ---");
        System.out.print("Period Name (e.g., Fall2025): ");
        String periodName = scanner.nextLine();
        System.out.print("Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        
        admin.defineRegistrationPeriod(periodName, startDate, endDate);
        System.out.println("✓ Registration period defined!");
    }
    
    /**
     * Set registration rules
     */
    private static void setRegistrationRules(Administrator admin) {
        System.out.println("\n--- Set Registration Rules ---");
        System.out.print("Max credits per term: ");
        int maxCredits = getIntInput();
        scanner.nextLine();
        
        System.out.print("Enable time conflict checking? (yes/no): ");
        String enableConflict = scanner.nextLine();
        
        admin.setRegistrationRules(maxCredits, enableConflict.equalsIgnoreCase("yes"));
        System.out.println("✓ Registration rules updated!");
    }
    
    /**
     * Generate reports
     */
    private static void generateReports(Administrator admin) {
        System.out.println("\n--- Generate Reports ---");
        System.out.println("1. Enrollment Report");
        System.out.println("2. Course Capacity Report");
        System.out.println("3. Student Registration Report");
        System.out.print("Select report type: ");
        
        int choice = getIntInput();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.println(admin.generateEnrollmentReport());
                break;
            case 2:
                System.out.println(admin.generateCapacityReport());
                break;
            case 3:
                System.out.print("Enter Student ID: ");
                String studentId = scanner.nextLine();
                System.out.println(admin.generateStudentReport(studentId));
                break;
            default:
                System.out.println("Invalid report type.");
        }
    }
    
    /**
     * View all exception requests
     */
    private static void viewAllExceptionRequests() {
        System.out.println("\n========== ALL EXCEPTION REQUESTS ==========");
        
        List<ExceptionRequest> requests = dataStore.getExceptionRequests();
        
        if (requests.isEmpty()) {
            System.out.println("No exception requests.");
            return;
        }
        
        for (int i = 0; i < requests.size(); i++) {
            System.out.println((i + 1) + ". " + requests.get(i).toString());
        }
    }
    
    /**
     * View system information
     */
    private static void viewSystemInfo() {
        System.out.println("\n========== SYSTEM INFORMATION ==========");
        System.out.println("System: Online Course Registration System");
        System.out.println("Version: 4.0 (Phase 4 Implementation)");
        System.out.println("Term: Fall 2025");
        System.out.println("\nStatistics:");
        System.out.println("- Total Courses: " + dataStore.getCourses().size());
        System.out.println("- Total Sections: " + dataStore.getCourseSections().size());
        System.out.println("- Total Students: " + dataStore.getStudents().size());
        System.out.println("- Total Faculty: " + dataStore.getFaculty().size());
        System.out.println("=========================================");
    }
    
    /**
     * Exit system
     */
    private static void exitSystem() {
        System.out.println("\n========================================");
        System.out.println("Thank you for using the Course Registration System!");
        System.out.println("Goodbye!");
        System.out.println("========================================\n");
        scanner.close();
        System.exit(0);
    }
    
    /**
     * Get integer input with error handling
     */
    private static int getIntInput() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine(); // clear invalid input
            }
        }
    }
    
    /**
     * Pause screen
     */
    private static void pauseScreen() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Parse time string in HH:MM format
     */
    private static java.time.LocalTime parseTime(String timeStr) {
        try {
            return java.time.LocalTime.parse(timeStr);
        } catch (Exception e) {
            return java.time.LocalTime.of(9, 0); // default
        }
    }
    
    /**
     * Parse days string and convert to Set of DayOfWeek
     */
    private static Set<java.time.DayOfWeek> parseDays(String daysStr) {
        Set<java.time.DayOfWeek> days = new HashSet<>();
        daysStr = daysStr.toUpperCase();
        
        if (daysStr.contains("M")) days.add(java.time.DayOfWeek.MONDAY);
        if (daysStr.contains("T") && !daysStr.contains("TH")) days.add(java.time.DayOfWeek.TUESDAY);
        if (daysStr.contains("W")) days.add(java.time.DayOfWeek.WEDNESDAY);
        if (daysStr.contains("R") || daysStr.contains("TH")) days.add(java.time.DayOfWeek.THURSDAY);
        if (daysStr.contains("F")) days.add(java.time.DayOfWeek.FRIDAY);
        
        if (days.isEmpty()) {
            days.add(java.time.DayOfWeek.MONDAY);
            days.add(java.time.DayOfWeek.WEDNESDAY);
            days.add(java.time.DayOfWeek.FRIDAY);
        }
        
        return days;
    }
}
import java.util.Scanner;
import studentmanagement.StudentManage;

public class Admin{
    
    private static int choiceId;
    
    // Declaring ANSI_RESET 
    public static final String ANSI_RESET = "\u001B[0m"; 
  
    // Declaring CLI colors
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";
    public static final String ANSI_BLUE_BG = "\u001B[44m";
    public static final String ANSI_YELLOW_BG = "\u001B[43m";

    // Initialize Scanner
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String args[]) {
        do {
            System.out.print(ANSI_BLUE + "+ ---------------------------------------------------------- +" + ANSI_RESET + "\n\n");
            System.out.println("\t" + ANSI_BLUE + "===== STUDENT RECORD MANAGEMENT SYSTEM =====" + ANSI_RESET + "\n");
            System.out.println("\t" + ANSI_BLUE + "=> Add a New Student ********** " + ANSI_RESET + ANSI_BLUE_BG + " 1 " + ANSI_RESET);
            System.out.println("\t" + ANSI_BLUE + "=> Update a Student Record **** " + ANSI_RESET + ANSI_BLUE_BG + " 2 " + ANSI_RESET);
            System.out.println("\t" + ANSI_BLUE + "=> View a Student Record ****** " + ANSI_RESET + ANSI_BLUE_BG + " 3 " + ANSI_RESET);
            System.out.println("\t" + ANSI_BLUE + "=> Delete a Student Record **** " + ANSI_RESET + ANSI_BLUE_BG + " 4 " + ANSI_RESET);
            System.out.println("\t" + ANSI_BLUE + "=> View All Student Records *** " + ANSI_RESET + ANSI_BLUE_BG + " 5 " + ANSI_RESET);
            System.out.println("\t" + ANSI_BLUE + "=> Get Rankings *************** " + ANSI_RESET + ANSI_BLUE_BG + " 6 " + ANSI_RESET);
            System.out.println("\t" + ANSI_BLUE + "=> Exit *********************** " + ANSI_RESET + ANSI_BLUE_BG + " 7 " + ANSI_RESET);
            System.out.println("\n" + ANSI_BLUE + "+ ---------------------------------------------------------- +" + ANSI_RESET + "\n");
            
            System.out.print(ANSI_BLUE_BG + "Enter Your Choice:" + ANSI_RESET);
            System.out.print(" ");
            choiceId = input.nextInt();

            // clear the new line character
            input.nextLine();

            System.out.println();
            
            switch(choiceId) {
                case 1:
                    createAStudent();
                    break;
                case 2:
                    updateChoice();
                    break;
                case 3:
                    displayAStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    StudentManage.getStudentsList();
                    break;
                case 6:
                    StudentManage.rankStudents();
                    break;
                case 7:
                    choiceId = 7;
                    input.close();
                    break;
                default:
                    continue;
            }
            
        }while(choiceId != 7);
    }
    
    // add a new student to the system
    public static void createAStudent() {
        try {
            System.out.print(ANSI_YELLOW + "Enter the student name: " + ANSI_RESET);
            String name = input.nextLine();
            
            System.out.print(ANSI_YELLOW + "Enter the Mathematics mark: " + ANSI_RESET);
            int maths = input.nextInt();
            
            if(maths < 0 || maths > 100) {
                throw new Exception("Invalid Marks, Marks should be between 0 and 100!");
            }
            
            System.out.print(ANSI_YELLOW + "Enter the Science mark: " + ANSI_RESET);
            int science = input.nextInt();
            
            if(science < 0 || science > 100) {
                throw new Exception("Invalid Marks, Marks should be between 0 and 100!");
            }
            
            // set subject marks
            String[][] marks = {{"Mathematics", Integer.toString(maths)}, {"Science", Integer.toString(science)}};
        
            if(StudentManage.setStudentsList(name, marks)) {
                System.out.println("\n" + ANSI_GREEN_BG + "Successfully Added a New Student!" + ANSI_RESET);
                System.out.println();
            } else {
                throw new Exception("Can't Add a New Student!");
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println(ANSI_RED_BG + e + ANSI_RESET);
            System.out.println();
            System.exit(0);
        }
    }
    
    // selector for update student mark or name
    public static void updateChoice() {
        System.out.println(ANSI_YELLOW + "=> Student name update ***** " + ANSI_RESET + ANSI_YELLOW_BG + " 1 " + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "=> Student mark update ***** " + ANSI_RESET + ANSI_YELLOW_BG + " 2 " + ANSI_RESET);
        System.out.print("\n" + ANSI_BLUE_BG + "Enter your choice:" + ANSI_RESET + " ");

        String userChoice = input.nextLine();
        System.out.println();
        
        if(userChoice.equals("1")) {
            updateStudentName();
        }
        
        else if(userChoice.equals("2")) {
            updateStudentMark();
        }
    }
    
    // student name update
    public static void updateStudentName() {
        System.out.print(ANSI_YELLOW + "Enter the student ID:" + ANSI_RESET + " ");
        String id = input.nextLine();

        System.out.print(ANSI_YELLOW + "Enter the student name:" + ANSI_RESET + " ");
        String name = input.nextLine();

        boolean message = StudentManage.setStudentName(id, name);
        
        if(message) {
            System.out.println("\n" + ANSI_GREEN_BG + "Successfully Name Updated!" + ANSI_RESET);
            System.out.println();
        } else {
            System.out.println();
            System.out.println(ANSI_RED_BG + "Error! Can't Update Name. Check Student ID Again." + ANSI_RESET);
            System.out.println();
        }
    }
    
    // student mark update for a related subject
    public static void updateStudentMark() {
        String[] availableSubjects = {"Mathematics", "Science"};
        boolean message = false;

        System.out.print(ANSI_YELLOW + "Enter the student ID: " + ANSI_RESET);
        String id = input.nextLine();

        // display all available subjects
        System.out.println("\n" + ANSI_GREEN_BG + "=== Available Subjects ===" + ANSI_RESET + "\n");
        
        for(String subject : availableSubjects) {
            System.out.println(ANSI_GREEN + "=> " + subject + ANSI_RESET);
        }

        System.out.println();
        
        System.out.print(ANSI_YELLOW + "Enter the subject name: " + ANSI_RESET);
        String subject = input.nextLine();
        
        System.out.print(ANSI_YELLOW + "Enter the subject mark: " + ANSI_RESET);
        int mark = input.nextInt();

        if(mark >= 0 && mark <= 100) {
            message = StudentManage.setStudentMark(id, subject, Integer.toString(mark));
        }

        if(message) {
            System.out.println("\n" + ANSI_GREEN_BG + "Successfully Mark Updated!" + ANSI_RESET);
            System.out.println();
        } else {
            System.out.println();
            System.out.println(ANSI_RED_BG + "Error! Can't Update Mark" + ANSI_RESET);
            System.out.println();
        }
    }
    
    public static void removeStudent() {
        System.out.print(ANSI_YELLOW + "Enter the student ID: " + ANSI_RESET);
        String id = input.nextLine();

        boolean success = StudentManage.deleteAStudent(id);
        
        if(success) {
            System.out.println("\n" + ANSI_GREEN_BG + "Successfully Deleted!" + ANSI_RESET);
            System.out.println();
        } else {
            System.out.println();
            System.out.println(ANSI_RED_BG + "Error! Can't Delete. Check Student ID." + ANSI_RESET);
            System.out.println();
        }
    }
    
    public static void displayAStudent() {
        System.out.print(ANSI_YELLOW + "Enter the student ID: " + ANSI_RESET);
        String id = input.nextLine();
        
        StudentManage.getAStudentData(id);
    }
}
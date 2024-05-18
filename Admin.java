import java.util.Scanner;
import studentmanagement.StudentManage;

public class Admin{
    
    private static int choiceId;
    
    // Declaring Reset
    public static final String Reset = "\u001B[0m";
  
    // Declaring CLI colors
    public static final String Green = "\u001B[32m";
    public static final String Yellow = "\u001B[33m";
    public static final String Blue = "\u001B[34m";
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String Green_BG = "\u001B[42m";
    public static final String Blue_BG = "\u001B[44m";
    public static final String Yellow_BG = "\u001B[43m";

    // Initialize Scanner
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String args[]) {
        do {
            System.out.print(Blue + "+ ---------------------------------------------------------- +" + Reset + "\n\n");
            System.out.println("\t" + Blue + "===== STUDENT RECORD MANAGEMENT SYSTEM =====" + Reset + "\n");
            System.out.println("\t" + Blue + "=> Add a New Student ********** " + Reset + Blue_BG + " 1 " + Reset);
            System.out.println("\t" + Blue + "=> Update a Student Record **** " + Reset + Blue_BG + " 2 " + Reset);
            System.out.println("\t" + Blue + "=> View a Student Record ****** " + Reset + Blue_BG + " 3 " + Reset);
            System.out.println("\t" + Blue + "=> Delete a Student Record **** " + Reset + Blue_BG + " 4 " + Reset);
            System.out.println("\t" + Blue + "=> View All Student Records *** " + Reset + Blue_BG + " 5 " + Reset);
            System.out.println("\t" + Blue + "=> Get Rankings *************** " + Reset + Blue_BG + " 6 " + Reset);
            System.out.println("\t" + Blue + "=> Exit *********************** " + Reset + Blue_BG + " 7 " + Reset);
            System.out.println("\n" + Blue + "+ ---------------------------------------------------------- +" + Reset + "\n");
            
            System.out.print(Blue_BG + "Enter Your Choice:" + Reset);
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
            System.out.print(Yellow + "Enter the student name: " + Reset);
            String name = input.nextLine();
            
            System.out.print(Yellow + "Enter the Mathematics mark: " + Reset);
            int maths = input.nextInt();
            
            if(maths < 0 || maths > 100) {
                throw new Exception("Invalid Marks, Marks should be between 0 and 100!");
            }
            
            System.out.print(Yellow + "Enter the Science mark: " + Reset);
            int science = input.nextInt();
            
            if(science < 0 || science > 100) {
                throw new Exception("Invalid Marks, Marks should be between 0 and 100!");
            }
            
            // set subject marks
            String[][] marks = {{"Mathematics", Integer.toString(maths)}, {"Science", Integer.toString(science)}};
        
            if(StudentManage.setStudentsList(name, marks)) {
                System.out.println("\n" + Green_BG + "Successfully Added a New Student!" + Reset);
                System.out.println();
            } else {
                throw new Exception("Can't Add a New Student!");
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println(ANSI_RED_BG + e + Reset);
            System.out.println();
            System.exit(0);
        }
    }
    
    // selector for update student mark or name
    public static void updateChoice() {
        System.out.println(Yellow + "=> Student name update ***** " + Reset + Yellow_BG + " 1 " + Reset);
        System.out.println(Yellow + "=> Student mark update ***** " + Reset + Yellow_BG + " 2 " + Reset);
        System.out.print("\n" + Blue_BG + "Enter your choice:" + Reset + " ");

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
        System.out.print(Yellow + "Enter the student ID:" + Reset + " ");
        String id = input.nextLine();

        System.out.print(Yellow + "Enter the student name:" + Reset + " ");
        String name = input.nextLine();

        boolean message = StudentManage.setStudentName(id, name);
        
        if(message) {
            System.out.println("\n" + Green_BG + "Successfully Name Updated!" + Reset);
            System.out.println();
        } else {
            System.out.println();
            System.out.println(ANSI_RED_BG + "Error! Can't Update Name. Check Student ID Again." + Reset);
            System.out.println();
        }
    }
    
    // student mark update for a related subject
    public static void updateStudentMark() {
        String[] availableSubjects = {"Mathematics", "Science"};
        boolean message = false;

        System.out.print(Yellow + "Enter the student ID: " + Reset);
        String id = input.nextLine();

        // display all available subjects
        System.out.println("\n" + Green_BG + "=== Available Subjects ===" + Reset + "\n");
        
        for(String subject : availableSubjects) {
            System.out.println(Green + "=> " + subject + Reset);
        }

        System.out.println();
        
        System.out.print(Yellow + "Enter the subject name: " + Reset);
        String subject = input.nextLine();
        
        System.out.print(Yellow + "Enter the subject mark: " + Reset);
        int mark = input.nextInt();

        if(mark >= 0 && mark <= 100) {
            message = StudentManage.setStudentMark(id, subject, Integer.toString(mark));
        }

        if(message) {
            System.out.println("\n" + Green_BG + "Successfully Mark Updated!" + Reset);
            System.out.println();
        } else {
            System.out.println();
            System.out.println(ANSI_RED_BG + "Error! Can't Update Mark" + Reset);
            System.out.println();
        }
    }
    
    public static void removeStudent() {
        System.out.print(Yellow + "Enter the student ID: " + Reset);
        String id = input.nextLine();

        boolean success = StudentManage.deleteAStudent(id);
        
        if(success) {
            System.out.println("\n" + Green_BG + "Successfully Deleted!" + Reset);
            System.out.println();
        } else {
            System.out.println();
            System.out.println(ANSI_RED_BG + "Error! Can't Delete. Check Student ID." + Reset);
            System.out.println();
        }
    }
    
    public static void displayAStudent() {
        System.out.print(Yellow + "Enter the student ID: " + Reset);
        String id = input.nextLine();
        
        StudentManage.getAStudentData(id);
    }
}
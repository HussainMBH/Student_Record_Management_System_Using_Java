package studentmanagement;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentManage{
    public static ArrayList<Student> students = new ArrayList<Student>();

    // Declaring RESET
    public static final String RESET = "\u001B[0m";

    // Declaring CLI colors
    public static final String YELLOW = "\u001B[33m";
    public static final String YELLOW_BG = "\u001B[43m";
    
    // rank
    public static void rankStudents() {
        int rankCounter = 0;

        String[] rankings = new String[students.size()];
        System.out.println(YELLOW + "====== Student Rankings ======" + RESET + "\n");
        
        for(int i = 0; i < students.size(); i++) {
            String rankData = "Average: " + Double.toString(students.get(i).getAStudentAverage()) + " - Name: " + students.get(i).getStudentName();
            rankings[i] = rankData;
        }
        
        Arrays.sort(rankings);
        
        for(int i = rankings.length - 1; i >= 0 ; i--) {
            rankCounter++;
            System.out.println(YELLOW + rankings[i] + " - " + " Rank: " + RESET + YELLOW_BG + " " + rankCounter + " " + RESET);
        }

        System.out.println();
    }

    // data formatter
    private static String dataFormatter(int i) {
        String resultFormat = YELLOW + "[ Name: " + RESET + YELLOW_BG + " " + students.get(i).getStudentName() + " " + RESET + YELLOW +", ID: " + RESET + YELLOW_BG + " " + students.get(i).getStudentID() + " " + RESET + YELLOW + ", Mathematics: " + RESET + YELLOW_BG + " " + students.get(i).getSubjectMark(0) + " " + RESET + YELLOW + ", Science: " + RESET + YELLOW_BG + " " + students.get(i).getSubjectMark(1) + " " + RESET + YELLOW + ", Total Marks: " + YELLOW_BG + " " + Integer.toString(students.get(i).getAStudentTotal()) + " " + RESET + YELLOW +", Average: " + RESET + YELLOW_BG + " " + Double.toString(students.get(i).getAStudentAverage()) + " " + RESET + YELLOW + " ]" + RESET;

        return resultFormat;
    }

    // get all students
    public static void getStudentsList() {
        System.out.println("\t" + "\u001B[33m" + "====== Student Records Book ======" + "\n");
        
        for(int i = 0; i < students.size(); i++) {
            System.out.println(dataFormatter(i));
        }
        
        System.out.println();
    }
    
    // get a student
    public static void getAStudentData(String id) {
        System.out.println();

        System.out.println("\t" + "\u001B[33m" + "====== Student Details ======" + "\n");
        
        for(int i = 0; i < students.size(); i++) {
            
            if(students.get(i).getStudentID().equals(id)) {
                System.out.println(dataFormatter(i));
            }
        }
        
        System.out.println();
    }
    
    // add a new student
    public static boolean setStudentsList(String name, String[][] marks) {
        int id;
        
        if(students.size() == 0) {
            id = students.size() + 1;
        }
            
        else {
            id = Integer.parseInt(students.get(students.size() - 1).getStudentID()) + 1;
        }
            
        Student student = new Student(name, Integer.toString(id), marks);
            
        if(students.add(student)) {
            return true;
        } else {
            return false;
        }
    }
    
    // set a student name
    public static boolean setStudentName(String id, String name) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getStudentID().equals(id)) {
                boolean success = students.get(i).setName(name);
                if(success) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // set a student mark
    public static boolean setStudentMark(String id, String subject, String mark) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getStudentID().equals(id)) {
                boolean success = students.get(i).setMark(subject, mark);
                if(success) {
                    return true;
                }
            }
        }

        return false;
    }
    
    // delete a student
    public static boolean deleteAStudent(String id) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getStudentID().equals(id)) {
                students.remove(i);
                return true;
            }
        }
        
        return false;
    }
}
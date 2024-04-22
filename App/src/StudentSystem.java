import java.util.ArrayList;
import java.util.Scanner;


public class StudentSystem {
    private static final String ADD_STUDENT = "1";
    private static final String REMOVE_STUDENT = "2";
    private static final String REVISE_STUDENT = "3";
    private static final String QUERY_STUDENT = "4";
    private static final String PRINT_ALL = "5";
    private static final String EXIT = "6";
    static ArrayList<Student> studList = new ArrayList<>();
    static {
        studList.add(new Student("koukau", "236E0502", 23, "japan"));

    }
    public static void startStudentSystem() {

        Scanner sc = new Scanner(System.in);
        loop: while (true){
            System.out.println("----------Welcome to Student Management System-----------");
            System.out.println("1: Add student");
            System.out.println("2: Remove student");
            System.out.println("3: Revise student");
            System.out.println("4: Query student");
            System.out.println("5: Print all student");
            System.out.println("6: Exit");
            System.out.println("Please input your selection");
            String choose = sc.next();
            switch (choose){
                case ADD_STUDENT -> Student.add(studList);
                case REMOVE_STUDENT -> Student.remove(studList);
                case REVISE_STUDENT -> Student.revise(studList);
                case QUERY_STUDENT -> Student.query(studList);
                case PRINT_ALL -> Student.printAll(studList);
                case EXIT -> {
                    System.out.println("Exit");
                    break loop;
                }
                default -> System.out.println("No this selection");
            }
        }
    }
}

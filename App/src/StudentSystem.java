import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void startStudentSystem() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> studList = new ArrayList<>();
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
                case "1" -> Student.add(studList);
                case "2" -> Student.remove(studList);
                case "3" -> Student.revise(studList);
                case "4" -> Student.query(studList);
                case "5" -> Student.printAll(studList);
                case "6" -> {
                    System.out.println("Exit");
                    break loop;
                }
                default -> System.out.println("No this selection");
            }
        }
    }
}

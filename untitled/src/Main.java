import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Student Management System");
        System.out.println("Please choose 1: Log in; 2: Register; 3: Forget password");
        Scanner sc = new Scanner(System.in);
        String selection = sc.next();
        switch (selection){
            case "1" -> System.out.println("Log in");
            case "2" -> System.out.println("Register");
            case "3" -> System.out.println("Forget password");
            default -> System.out.println("No this selection");
        }















        ArrayList<Student> list = new ArrayList<>();
        loop:while (true){
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
                case "1" -> Student.add(list);
                case "2" -> Student.remove(list);
                case "3" -> Student.revise(list);
                case "4" -> Student.query(list);
                case "5" -> Student.printAll(list);
                case "6" -> {
                    System.out.println("Exit");
                    break loop;
                }
                default -> System.out.println("No this selection");
            }
        }
    }
}
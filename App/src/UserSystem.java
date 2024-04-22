import java.util.ArrayList;
import java.util.Scanner;



public class UserSystem {
    static ArrayList<User> userList = new ArrayList<>();
    static {
        userList.add(new User("koukau", "ppyy541880", "koukau77@gmail.com", "07020345635"));

    }
    public static void startUserSystem() {
        Scanner sc = new Scanner(System.in);
        loopUser: while (true){
            System.out.println("Welcome to Student Management System");
            System.out.println("Please choose 1: Log in 2: Register 3: Forget password");
            String selection = sc.next();
            switch (selection){
                case "1" -> User.login(userList);
                case "2" -> User.register(userList);
                case "3" -> User.forgetPassword(userList);
                default -> System.out.println("No this selection");
            }
        }
    }
}

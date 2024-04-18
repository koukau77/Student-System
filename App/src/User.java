import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class User {
    private String Username;
    private String Password;
    private String Email;
    private String MobileNum;


    public User() {
    }

    public User(String Username, String Password, String Email, String MobileNum) {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.MobileNum = MobileNum;
    }

    /**
     * 获取
     * @return Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * 设置
     * @param Username
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     * 获取
     * @return Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * 设置
     * @param Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * 获取
     * @return Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * 设置
     * @param Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * 获取
     * @return MobileNum
     */
    public String getMobileNum() {
        return MobileNum;
    }

    /**
     * 设置
     * @param MobileNum
     */
    public void setMobileNum(String MobileNum) {
        this.MobileNum = MobileNum;
    }

    // register
    public static void register(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        String email;
        String mobilNum;
        // input username
        while (true) {
            System.out.println("Please input username");
            username = sc.next();
            // determine username's form is correct.
            boolean formFlag = checkUsername(username);
            if (! formFlag){
                // form is correct
                System.out.println("Username's format is wrong. Please input again");
                continue;
            }
            // determine if this username is only one in userlist .
            boolean numFlag = contains(list, username);
            if (numFlag){
                System.out.println("Username" + username + "exits, please input again");
            }else {
                break;
            }
        }
        // input password
        // input twice and must be same
        while (true){
            System.out.println("Please input password");
            password = sc.next();
            System.out.println("Please input password again");
            String passwordAgain = sc.next();
            boolean flag = checkPassword(password, passwordAgain);
            if (flag){
                break;
            }else {
                System.out.println("The two passwords do not match");
            }
        }
        // input email
        while (true){
            System.out.println("Please input email");
            email = sc.next();
            boolean flag = checkEmail(email);
            if (flag){
                break;
            }else {
                System.out.println("Email's format is wrong, please input email again");
            }
        }
        // input mobile number
        while (true){
            System.out.println("Please input mobile number");
            mobilNum = sc.next();
            boolean flag = checkMobileNum(mobilNum);
            if (flag){
                break;
            }else {
                System.out.println("Mobile number's format is wrong");
            }
        }

        User u = new User(username, password, email, mobilNum);
        list.add(u);
        System.out.println("Register successfully");
        printUserInfo(list);
    }

    // login in
    public static void login(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("You have 3 times to input");
        for (int i = 0; i < 3; i++) {
            System.out.println("Please input your username");
            String username = sc.next();
            System.out.println("Please input your password");
            String password = sc.next();
            // check verification code
            while (true) {
                String rightVeriCode = getCode();
                System.out.println("Verification code: " + rightVeriCode);
                System.out.println("Please input verification code");
                String veriCode = sc.next();
                if (veriCode.equalsIgnoreCase(rightVeriCode)) {
                    break;
                } else {
                    System.out.println("Verification code is wrong, please input again");
                }
            }
            // check username and password
            if (!contains(list, username)) {
                System.out.println("Username doesn't exit, please register");
            }
            User useInfo = new User(username, password, null, null);
            boolean flag = checkUserInfo(list, useInfo);
            if (flag) {
                System.out.println("Login successfully!!");
                StudentSystem startStudentSystem = new StudentSystem();
                startStudentSystem.startStudentSystem();
                break;
            }else {
                System.out.println("Login fail!!");
                if (i == 2){
                    System.out.println("This account has been frozen");
                    return;
                }else {
                    System.out.println((2 - i) + " times left");
                }
            }
        }
    }

    // forget password
    public static void forgetPassword(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input your username");
        String username = sc.next();
        boolean flag = contains(list, username);
        if (! flag){
            System.out.println("This username doesn't exit, please register!");
            return;
        }
        System.out.println("Please input your email");
        String email = sc.next();
        System.out.println("Please input your mobile numebr");
        String mobileNum = sc.next();

        int index = findIndex(list, username);
        User userInfo = list.get(index);
        String rightEmail = userInfo.getEmail();
        String rightMobileNum = userInfo.getMobileNum();
        if (! (email.equals(rightEmail) && mobileNum.equals(rightMobileNum))){
            System.out.println("Email or Mobile number is wrong, you can't revise!");
            return;
        }
        // passed all check
        String newPassword;
        while (true) {
            System.out.println("Please input new password");
            newPassword = sc.next();
            System.out.println("Please input new password again");
            String newPasswordAgain = sc.next();
            if (newPassword.equals(newPasswordAgain)){
                break;
            }else {
                System.out.println("The two passwords do not match");
            }
        }
        list.get(index).setPassword(newPassword);
        System.out.println("New password established successfully");
    }

    public static boolean checkUsername(String username){
        // length: username[3,15]
        // must be number + alphabet
        // can't be all number

        // check if length belongs to [3,15]
        int len = username.length();
        if (len < 3 || len > 15){
            return false;
        }
        // must be number + alphabet
        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))){
                return false;
            }
        }
        // can't be all number
        int count = 0;
        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                count++;
                break;
            }
        }
        return count > 0;
    }

    public static boolean checkPassword(String password1, String password2){
        if (password1.equals(password2)){
            return true;
        }
        return false;
    }
    public static boolean checkEmail(String email){
        // the last 4 chat muse be .com
        String com = ".com";
        int j = com.length();
        for (int i = email.length() - 1; i > email.length() - 5 ; i--) {
            if (email.charAt(i) != com.charAt(j - 1)){
                return false;
            }
            j--;
        }
        return true;
    }

    public static boolean checkMobileNum(String mobileNum){
        // length must be equal to 11
        // the first chat must be 0
        // all must be number

        // length must be equal to 11
        if (mobileNum.length() != 11){
            return false;
        }
        // the first chat must be 0
        if (! mobileNum.startsWith("0")){
            return false;
        }
        // all must be number
        for (int i = 0; i < mobileNum.length(); i++) {
            char c = mobileNum.charAt(i);
            if (! ((c >= '0') && (c <= '9'))){
                return false;
            }
        }
        return true;
    }

    public static int findIndex(ArrayList<User> list, String username){
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightName = user.getUsername();
            if (username.equals(rightName)){
                return i;
            }
        }
        return -1;
    }
    // determine if username is in list
    public static boolean contains(ArrayList<User> list, String username) {
        return findIndex(list, username) != -1;
    }

    // determine if password is true
    public static boolean checkUserInfo(ArrayList<User> list, User userInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightName = user.getUsername();
            String rightPassword = user.getPassword();
            if (userInfo.getUsername().equals(rightName) && userInfo.getPassword().equals(rightPassword)){
                return true;
            }
        }
        return false;
    }

    public static void printUserInfo(ArrayList<User> list){
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            System.out.println(
                    "Username: " + u.getUsername() + ", " +
                    "Password: " + u.getPassword() + ", " +
                    "Email: " + u.getEmail() + ", " +
                    "Mobile number: " + u.getMobileNum()
            );
        }
    }

    // generate a verification code
    public static String getCode(){
        // add a~z and A~Z
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add((char)('A' + i));
        }

        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }
        // add one number in the end of sb
        int number = r.nextInt(10);
        sb.append(number);
        char[] arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(arr.length);
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;

        return new String(arr);
    }
}


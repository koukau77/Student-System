import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private String name;
    private String id;
    private int age;
    private String homeAddress;


    public Student() {
    }

    public Student(String name, String id, int age, String homeAddress) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.homeAddress = homeAddress;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     * @return homeAddress
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * 设置
     * @param homeAddress
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    // add student information.
    public static void add(ArrayList<Student> list){
        Student stud = new Student();
        Scanner sc = new Scanner(System.in);
        String id = null;
        while (true){
            System.out.println("Please input student's id");
            id = sc.next();
            boolean flag = contains(list, id);
            if (flag){
                System.out.println("Id exits, please input again");
            }else {
                stud.setId(id);
                break;
            }
        }
        System.out.println("Please input student's name");
        String name = sc.next();
        stud.setName(name);
        System.out.println("Please input student's age");
        int age = sc.nextInt();
        stud.setAge(age);
        System.out.println("Please input student's home address");
        String homeAddress = sc.next();
        stud.setHomeAddress(homeAddress);

        list.add(stud);

        System.out.println("Add successfully");
    }

    // delete student information using student's id.
    public static void remove(ArrayList<Student> list){
        if (list.isEmpty()){
            System.out.println("No students information, please add.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Please student's id who you want to remove");
        String id = sc.next();
        int index = getIndex(list, id);
        if (index != -1){
            list.remove(index);
            System.out.println("Has removed the " + list.get(index).getName() + " whose id is " + id);
        }else {
            System.out.println("Id doesn't exit, back to initial menu");
        }
    }

    // revise student's id.
    public static void revise(ArrayList<Student> list){
        if (list.isEmpty()){
            System.out.println("No students information, please add.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Please student's id who you want to revise");
        String id = sc.next();
        int index = getIndex(list, id);
        if (index != -1){
            list.get(index).setId(id);
            System.out.println("Please student's name that you want to revise to");
            String name = sc.next();
            list.get(index).setName(name);
            System.out.println("Please student's age that you want to revise to");
            int age = sc.nextInt();
            list.get(index).setAge(age);
            System.out.println("Please student's home address that you want to revise to");
            String homeAddress = sc.next();
            list.get(index).setHomeAddress(homeAddress);
        }else {
            System.out.println("Id doesn't exit, back to initial menu");
        }
    }

    // inquire student information
    // print  information of a student whose id is xxx
    public static void query(ArrayList<Student> list){
        if (list.isEmpty()){
            System.out.println("No students information, please add.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Please student's id who you want to query");
        String id = sc.next();
        int index = getIndex(list, id);
        if (index != -1){
        // print table's title
        System.out.println("Name\t\tId\tAge\tHomeAddress");
                System.out.println(
                        list.get(index).getName() + "\t" +
                        list.get(index).getId() + "\t" +
                        list.get(index).getAge() + "\t" +
                        list.get(index).getHomeAddress()
                );
        }else {
            System.out.println("Id doesn't exit");
        }

    }


    // print all students' information
    public static void printAll(ArrayList<Student> list){
        if (list.isEmpty()){
            System.out.println("No students information, please add.");
            return;
        }
        // print table's title
        System.out.println("Name\t\tId\tAge\tHomeAddress");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(
                    list.get(i).getName() + "\t" +
                    list.get(i).getId() + "\t" +
                    list.get(i).getAge() + "\t" +
                    list.get(i).getHomeAddress()
            );
        }
    }

    // determine if student's id exists
    public static boolean contains(ArrayList<Student> list, String id){
        return getIndex(list, id) != -1;
    }

    // determine if student's id exists.
    // if exists, return the index of this id
    // if not exist, return -1
    public static int getIndex(ArrayList<Student> list, String id){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)){
                return i;
            }
        }
        // -1 presents not exist.
        return -1;
    }


}

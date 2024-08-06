package riwi.filtro.entities;

public class student {
    private static int id;
    private static String name;
    private static String email;

    public student(){}

    public student(int id, String name, String email){
        student.id = id;
        student.name = name;
        student.email = email;
    }

    public student(String name, String email){
        student.name = name;
        student.email = email;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        student.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        student.email = email;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

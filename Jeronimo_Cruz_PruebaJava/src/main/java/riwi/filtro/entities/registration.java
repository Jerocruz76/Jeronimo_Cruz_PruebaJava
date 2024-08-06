package riwi.filtro.entities;

public class registration {
    private int id;
    private static int student_id;
    private static int course_id;
    public registration(){}
    public registration(int id, int student_id, int course_id){
        this.id = id;
        registration.student_id = student_id;
        registration.course_id = course_id;
    }

    public registration(int student_id, int course_id){
        registration.student_id = student_id;
        registration.course_id = course_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        registration.student_id = student_id;
    }

    public static int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        registration.course_id = course_id;
    }
}

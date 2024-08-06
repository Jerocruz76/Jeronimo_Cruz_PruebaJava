package riwi.filtro.entities;

public class grade {
    private int id;
    private static int grade;
    private static String description;
    private static int course_id;
    public grade(){}
    public grade(int id, int grade, String description, int course_id){
        this.id = id;
        riwi.filtro.entities.grade.grade = grade;
        riwi.filtro.entities.grade.description = description;
        riwi.filtro.entities.grade.course_id = course_id;
    }

    public grade(int grade, String description, int course_id){
        riwi.filtro.entities.grade.grade = grade;
        riwi.filtro.entities.grade.description = description;
        riwi.filtro.entities.grade.course_id = course_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        riwi.filtro.entities.grade.grade = grade;
    }

    public static String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        riwi.filtro.entities.grade.description = description;
    }

    public static int getCourse_id() {
        return course_id;
    }

    public static void setCourse_id(int course_id) {
        riwi.filtro.entities.grade.course_id = course_id;
    }

    @Override
    public String toString() {
        return "grade{" +
                "id=" + id +
                "grade=" + grade +
                "description=" + description +
                '}';
    }
}

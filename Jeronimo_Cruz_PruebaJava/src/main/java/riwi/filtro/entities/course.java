package riwi.filtro.entities;

public class course {
    private static int id;
    private static String name;
    private static int capacity;

    public course(){}
    public course(int id, String name, int capacity){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }
    public course(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public static void setId(int id) {
        course.id = id;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        course.name = name;
    }

    public static int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        course.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

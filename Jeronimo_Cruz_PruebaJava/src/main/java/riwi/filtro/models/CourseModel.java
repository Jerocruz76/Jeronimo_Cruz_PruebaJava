package riwi.filtro.models;

import riwi.filtro.entities.course;
import riwi.filtro.models.interfaces.ICourseModel;
import riwi.filtro.persistence.database.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseModel implements ICourseModel {
    private final JDBC jdbc;
    public CourseModel(JDBC jdbc){
        this.jdbc = jdbc;
    }
    @Override
    public Optional<course> create(course createCourse) {
        var connection = jdbc.getConnection();
        String sql = "INSERT INTO courses (name, capacity) VALUES (?,?);";
        Optional<course> createdCourse = Optional.empty();
        try{
            var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1,course.getName());
            statement.setInt(2, course.getCapacity());
            statement.execute();
            var resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                var courseId = resultSet.getInt(1);
                course.setId(courseId);
                createdCourse = Optional.of(createCourse);
            }else throw new SQLException("Couldn't create course");
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return createdCourse;
    }

    @Override
    public List<course> findById(int idToFind) {
        var connection = jdbc.getConnection();
        String sql = "SELECT * FROM courses WHERE id = ?;";
        var courseList= new ArrayList<course>();
        try{
            var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                var course = new course(id, name, capacity);
                courseList.add(course);
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return courseList.stream().toList();
    }

    @Override
    public boolean update(int id, course courseToUpdate) {
        var connection = jdbc.getConnection();
        String sql = "UPDATE courses SET name = ?, capacity = ? WHERE id = ?;";
        boolean update = false;
        try{
            var statement = connection.prepareStatement(sql);
            statement.setString(1, course.getName());
            statement.setInt(2, course.getCapacity());
            statement.setInt(4, id);
            var affectedRows = statement.executeUpdate();
            if (affectedRows == 1) update = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return update;
    }

    @Override
    public boolean delete(int idToDelete) {
        var connection = jdbc.getConnection();
        String sql = "DELETE FROM courses WHERE id = ?;";
        boolean delete = false;
        try{
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, idToDelete);
            var affectedRows = statement.executeUpdate();
            statement.close();
            if (affectedRows == 1) delete = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return delete;
    }
}

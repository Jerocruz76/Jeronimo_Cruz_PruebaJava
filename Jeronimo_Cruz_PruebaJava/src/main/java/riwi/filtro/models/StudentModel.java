package riwi.filtro.models;
import riwi.filtro.entities.student;
import riwi.filtro.models.interfaces.IStudentModel;
import riwi.filtro.persistence.database.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentModel implements IStudentModel {
    private final JDBC jdbc;

    public StudentModel(JDBC jdbc) {
        this.jdbc = jdbc;
    }
    @Override
    public Optional<student> create(student baseStudent) {
        var connection = jdbc.getConnection();
        String sql = "INSERT INTO students (name, email) VALUES (?,?);";
        Optional<student> createdStudent = Optional.empty();
        try{
            var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.execute();
            var resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                var studentId = resultSet.getInt(1);
                student.setId(studentId);
                createdStudent = Optional.of(baseStudent);
            }else throw new SQLException("Couldn't create student");
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return createdStudent;
    }

    @Override
    public List<student> findAll() {
        var connection = jdbc.getConnection();
        String sql = "SELECT * FROM students;";
        var studentList = new ArrayList<student>();
        try{
            var statement = connection.prepareStatement(sql);
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                var student = new student(id, name, email);
                studentList.add(student);
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return studentList.stream().toList();
    }

    @Override
    public List<student> findByID(int idToFindStudent) {
        var connection = jdbc.getConnection();
        String sql = "SELECT * FROM students WHERE id = ?;";
        var studentList = new ArrayList<student>();
        try{
            var statement = connection.prepareStatement(sql);
            statement.setInt(1,idToFindStudent);
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                var student = new student(id, name, email);
                studentList.add(student);
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return studentList.stream().toList();
    }

    @Override
    public List<student> findByEmail(String emailStudentToFind) {
        var connection = jdbc.getConnection();
        String sql = "SELECT * FROM students WHERE id = ?;";
        var studentList = new ArrayList<student>();
        try{
            var statement = connection.prepareStatement(sql);
            statement.setString(1,emailStudentToFind);
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                var student = new student(id, name, email);
                studentList.add(student);
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return studentList.stream().toList();
    }

    @Override
    public List<student> findActive(boolean active) {
        return null;
    }

    @Override
    public boolean update(int id, student studentToUpdate) {
        var connection = jdbc.getConnection();
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?;";
        boolean update = false;
        try{
            var statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, id);
            var affectedRows = statement.executeUpdate();
            if (affectedRows == 1) update = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return update;
    }
}

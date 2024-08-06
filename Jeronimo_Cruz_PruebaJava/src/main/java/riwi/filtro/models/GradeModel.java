package riwi.filtro.models;

import riwi.filtro.entities.course;
import riwi.filtro.entities.grade;
import riwi.filtro.models.interfaces.IGradeModel;
import riwi.filtro.persistence.database.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeModel implements IGradeModel {
    private final JDBC jdbc;

    public GradeModel(JDBC jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<grade> create(grade grade) {
        var connection = jdbc.getConnection();
        String sql = "INSERT INTO (grade,description,course_id) VALUES (?,?,?);";
        Optional<grade> createdGrade = Optional.empty();
        try{
            var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, riwi.filtro.entities.grade.getGrade());
            statement.setString(2, riwi.filtro.entities.grade.getDescription());
            statement.setInt(3,riwi.filtro.entities.grade.getCourse_id());
            statement.execute();
            var resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                var gradeId = resultSet.getInt(1);
                course.setId(gradeId);
                createdGrade = Optional.of(grade);
            }else throw new SQLException("Couldn't create grade");
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return createdGrade;
    }

    @Override
    public List<grade> findById(int idToFind) {
        var connection = jdbc.getConnection();
        String sql = "SELECT * FROM grades WHERE id = ?;";
        var gradeList = new ArrayList<grade>();
        try{
            var statement = connection.prepareStatement(sql);
            statement.setInt(1,idToFind);
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int grade_number = resultSet.getInt("grade");
                String description = resultSet.getString("description");
                int course_id = resultSet.getInt("course_id");
                var grade = new grade(id,grade_number,description, course_id);
                gradeList.add(grade);
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return gradeList;
    }

    @Override
    public boolean update(int id, grade gradeToUpdate) {
        var connection = jdbc.getConnection();
        String sql = "UPDATE grades SET grade = ?, description = ?, course_id = ?, WHERE id = ?";
        boolean update = false;
        try{
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, grade.getGrade());
            statement.setString(2,grade.getDescription());
            statement.setInt(3,grade.getCourse_id());
            statement.setInt(4,id);
            var affectedRows = statement.executeUpdate();
            if (affectedRows == 1) update = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return update;
    }
}

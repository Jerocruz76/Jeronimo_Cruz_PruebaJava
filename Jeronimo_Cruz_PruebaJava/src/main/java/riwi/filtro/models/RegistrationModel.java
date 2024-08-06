package riwi.filtro.models;

import riwi.filtro.entities.registration;
import riwi.filtro.models.interfaces.IRegistrationModel;
import riwi.filtro.persistence.database.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class RegistrationModel implements IRegistrationModel {
    private final JDBC jdbc;

    public RegistrationModel(JDBC jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<registration> create(registration registration) {
        var connection = jdbc.getConnection();
        String sql = "INSERT INTO registrations (student_id,course_id) VALUES (?,?);";
        Optional<registration> createdRegistration = Optional.empty();
        try{
            var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, riwi.filtro.entities.registration.getStudent_id());
            statement.setInt(2, riwi.filtro.entities.registration.getCourse_id());
            statement.execute();
            var resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                var registrationId = resultSet.getInt(1);
                registration.setId(registrationId);
                createdRegistration = Optional.of(registration);
            }else throw new SQLException("Couldn't create registration");
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return createdRegistration;
    }

    @Override
    public boolean update(int id, registration registrationToUpdate) {
        var connection = jdbc.getConnection();
        String sql = "UPDATE registrations SET student_id = ?, course_id = ? WHERE id = ?;";
        boolean update = false;
        try{
            var statement = connection.prepareStatement(sql);
            statement.setInt(1,registration.getStudent_id());
            statement.setInt(2,registration.getCourse_id());
            statement.setInt(3,id);
            var affectedRows = statement.executeUpdate();
            if (affectedRows == 1) update = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return update;
    }

    @Override
    public boolean delete(int id) {
        var connection = jdbc.getConnection();
        String sql = "DELETE FROM registrations WHERE id = ?;";
        boolean delete = false;
        try{
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var affectedRows = statement.executeUpdate();
            if (affectedRows == 1) delete = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        jdbc.disconnect();
        return delete;
    }
}

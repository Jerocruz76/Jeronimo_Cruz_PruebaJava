package riwi.filtro.models.interfaces;

import riwi.filtro.entities.student;

import java.util.List;
import java.util.Optional;

public interface IStudentModel {
    Optional<student> create(student createStudent);
    List<student> findAll();
    List<student> findByID(int idToFindStudent);
    List<student> findByEmail(String emailStudentToFind);
    List<student> findActive(boolean active);
    boolean update(int id, student studentToUpdate);
}

package riwi.filtro.models.interfaces;

import riwi.filtro.entities.course;

import java.util.List;
import java.util.Optional;

public interface ICourseModel {
    Optional<course> create(course createCourse);
    List<course> findById(int idToFind);
    boolean update(int id, course courseToUpdate);
    boolean delete(int idToDelete);
}

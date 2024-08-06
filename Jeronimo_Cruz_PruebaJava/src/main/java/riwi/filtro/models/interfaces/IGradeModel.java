package riwi.filtro.models.interfaces;

import riwi.filtro.entities.grade;

import java.util.List;
import java.util.Optional;

public interface IGradeModel {
    Optional<grade> create(grade grade);
    List<grade> findById(int idToFind);
    boolean update(int id, grade gradeToUpdate);
}

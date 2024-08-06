package riwi.filtro.controllers;

import riwi.filtro.entities.grade;
import riwi.filtro.models.interfaces.IGradeModel;

import java.util.List;
import java.util.Optional;

public class gradesController {
    private final IGradeModel gradeModel;

    public gradesController(IGradeModel gradeModel) {
        this.gradeModel = gradeModel;
    }
    public Optional<grade> create(grade grade){
        return this.gradeModel.create(grade);
    }
    public List<grade> findById(int idToFind){
        return this.gradeModel.findById(idToFind);
    }
    public boolean update(int id, grade gradeToUpdate){
        return this.gradeModel.update(id, gradeToUpdate);
    }
}

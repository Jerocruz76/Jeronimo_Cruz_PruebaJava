package riwi.filtro.controllers;

import riwi.filtro.entities.student;
import riwi.filtro.models.interfaces.IStudentModel;

import java.util.List;
import java.util.Optional;

public class studentsController {
    private final IStudentModel studentModel;

    public studentsController(IStudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public Optional<student> create(student createStudent){
        return this.studentModel.create(createStudent);
    }
    public List<student> findAll(){
        return this.studentModel.findAll();
    }
    public List<student> findById(int idToFindStudent){
        return this.studentModel.findByID(idToFindStudent);
    }
    public List<student> findByEmail(String emailStudentToFind){
        return this.studentModel.findByEmail(emailStudentToFind);
    }
    public boolean update(int id,student studentToUpdate){
        return this.studentModel.update(id, studentToUpdate);
    }
}

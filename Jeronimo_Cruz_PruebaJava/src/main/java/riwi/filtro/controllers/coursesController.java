package riwi.filtro.controllers;

import riwi.filtro.entities.course;
import riwi.filtro.models.interfaces.ICourseModel;

import java.util.List;
import java.util.Optional;

public class coursesController {
    private final ICourseModel courseModel;

    public coursesController(ICourseModel courseModel) {
        this.courseModel = courseModel;
    }

    public Optional<course> create(course createCourse){
        return this.courseModel.create(createCourse);
    }
    public List<course> findById(int idToFind){
        return this.courseModel.findById(idToFind);
    }
    public boolean update(int id, course courseToUpdate){
        return this.courseModel.update(id, courseToUpdate);
    }
    public boolean delete(int idToDelete){
        return this.courseModel.delete(idToDelete);
    }
}

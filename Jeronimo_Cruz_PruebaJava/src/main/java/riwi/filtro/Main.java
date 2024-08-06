package riwi.filtro;

import riwi.filtro.controllers.coursesController;
import riwi.filtro.controllers.gradesController;
import riwi.filtro.controllers.registrationsController;
import riwi.filtro.controllers.studentsController;
import riwi.filtro.entities.course;
import riwi.filtro.entities.grade;
import riwi.filtro.entities.registration;
import riwi.filtro.entities.student;
import riwi.filtro.models.CourseModel;
import riwi.filtro.models.GradeModel;
import riwi.filtro.models.RegistrationModel;
import riwi.filtro.models.StudentModel;
import riwi.filtro.models.interfaces.ICourseModel;
import riwi.filtro.models.interfaces.IGradeModel;
import riwi.filtro.models.interfaces.IRegistrationModel;
import riwi.filtro.models.interfaces.IStudentModel;
import riwi.filtro.persistence.database.JDBC;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        var JDBC = new JDBC();
        JDBC.getConnection();
        IStudentModel studentModel = new StudentModel(JDBC);
        ICourseModel courseModel = new CourseModel(JDBC);
        IGradeModel gradeModel = new GradeModel(JDBC);
        IRegistrationModel registrationModel = new RegistrationModel(JDBC);

        studentsController studentsController = new studentsController(studentModel);
        coursesController coursesController = new coursesController(courseModel);
        gradesController gradesController = new gradesController(gradeModel);
        registrationsController registrationsController = new registrationsController(registrationModel);

        boolean flag = true;
        while (flag){
            var menu = "****************** ¡¡¡ Welcome to riwiAcademy !!! ***********************\n 1.Create student \n 2.Create Courses \n 3.Create registration \n  4.Create grade \n 5.Find student by id \n 6.Find student by email \n 7.Update student \n 8.Find course by id \n 9.Update course \n 10.Delete course \n 11.Update registration \n 12.Delete registration \n 13.Find grade by id \n 14.Update grade \n 15.Exit";
            var options = JOptionPane.showInputDialog(menu);
            if (options == null) return;
            switch (options){
                case "1":
                    String name = JOptionPane.showInputDialog("Enter student's name: ");
                    String email = JOptionPane.showInputDialog("Enter student's email: ");
                    var createStudent = new student(name,email);
                    var createdStudent = studentsController.create(createStudent);
                    break;
                case "2":
                    String course_name = JOptionPane.showInputDialog("Enter course's name: ");
                    int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter capacity: "));
                    var createCourse = new course(course_name,capacity);
                    var createdCourse = coursesController.create(createCourse);
                    break;
                case "3":
                    int student_id = Integer.parseInt(JOptionPane.showInputDialog("Enter student's id: "));
                    int course_id = Integer.parseInt(JOptionPane.showInputDialog("Enter course's id: "));
                    var createRegistration = new registration(student_id,course_id);
                    var createdRegistration = registrationsController.create(createRegistration);
                    break;
                case "4":
                    int grade_number = Integer.parseInt(JOptionPane.showInputDialog("Enter grade from 0 to 100: "));
                    String description = JOptionPane.showInputDialog("Enter grade's description: ");
                    int createCourse_id = Integer.parseInt(JOptionPane.showInputDialog("Enter the course id"));
                    var createGrade = new grade(grade_number,description,createCourse_id);
                    var createdGrade = gradesController.create(createGrade);
                    break;
                case "5":
                    int idToFind = Integer.parseInt(JOptionPane.showInputDialog("Enter student's id to find: "));
                    var searchedStudent = studentsController.findById(idToFind);
                    var studentListById = searchedStudent.stream().map(student::toString).toList();
                    JOptionPane.showMessageDialog(null, studentListById);
                    break;
                case "6":
                    String emailToFind = JOptionPane.showInputDialog("Enter student's email to find: ");
                    var searchedStudentByEmail = studentsController.findByEmail(emailToFind);
                    var studentListByEmail = searchedStudentByEmail.stream().map(student::toString).toList();
                    JOptionPane.showMessageDialog(null, studentListByEmail);
                    break;
                case "7":
                    int idToUpdateStudent = Integer.parseInt(JOptionPane.showInputDialog("Enter student's id to update"));
                    String nameToUpdateStudent = JOptionPane.showInputDialog("Enter student's name to update");
                    String emailToUpdateStudent = JOptionPane.showInputDialog("Enter student's email to update: ");
                    var createStudentToUpdate = new student(nameToUpdateStudent,emailToUpdateStudent);
                    studentsController.update(idToUpdateStudent, createStudentToUpdate);
                    break;
                case "8":
                    int idToFindCourse = Integer.parseInt(JOptionPane.showInputDialog("Enter the course's id to find: "));
                    var courseById = coursesController.findById(idToFindCourse);
                    var courseListById = courseById.stream().map(course::toString).toList();
                    JOptionPane.showMessageDialog(null, courseListById);
                    break;
                case "9":
                    int idToUpdateCourse = Integer.parseInt(JOptionPane.showInputDialog("Enter the course's id you want to update"));
                    String nameCourseToUpdate = JOptionPane.showInputDialog("Enter the course's name to update: ");
                    int capacityToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the new capacity: "));
                    var createCourseToUpdate = new course(nameCourseToUpdate, capacityToUpdate);
                    coursesController.update(idToUpdateCourse,createCourseToUpdate);
                    break;
                case "10":
                    int idToDeleteCourse = Integer.parseInt(JOptionPane.showInputDialog("Enter the course's id to delete: "));
                    coursesController.delete(idToDeleteCourse);
                    break;
                case "11":
                    int idRegistrationToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the registration's id to update: "));
                    int idStudentToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the new student's id:"));
                    int idCourseToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the new course's id:"));
                    var createRegistrationToUpdate = new registration(idStudentToUpdate,idCourseToUpdate);
                    registrationsController.update(idRegistrationToUpdate,createRegistrationToUpdate);
                    break;
                case "12":
                    int idRegistrationToDelete = Integer.parseInt(JOptionPane.showInputDialog("Enter the registration's id to delete: "));
                    registrationsController.delete(idRegistrationToDelete);
                    break;
                case "13":
                    int idToFindGrade = Integer.parseInt(JOptionPane.showInputDialog("Enter the grade's id you are looking for:"));
                    var gradeById = gradesController.findById(idToFindGrade);
                    var gradeList = gradeById.stream().map(grade::toString).toList();
                    JOptionPane.showMessageDialog(null, gradeList);
                    break;
                case "14":
                    int idToUpdateGrade = Integer.parseInt(JOptionPane.showInputDialog("Enter the grade's id you want to update:"));
                    int newGrade = Integer.parseInt(JOptionPane.showInputDialog("Enter the new grade: "));
                    String newDescription = JOptionPane.showInputDialog("Enter the new description: ");
                    int courseIdToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the course's id you want to update: "));
                    var createGradeToUpdate = new grade(newGrade,newDescription,courseIdToUpdate);
                    gradesController.update(idToUpdateGrade,createGradeToUpdate);
                    break;
                case "15":
                    flag = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Please, enter a válid option.");
            }
        }
    }
}
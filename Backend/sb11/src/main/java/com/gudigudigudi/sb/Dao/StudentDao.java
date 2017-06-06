package com.gudigudigudi.sb.Dao;

import com.gudigudigudi.sb.Model.Student;

import java.util.Collection;

public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentById(int id);

    void updateStudentById(Student student);

    void insertStudentToDb(Student student);
}

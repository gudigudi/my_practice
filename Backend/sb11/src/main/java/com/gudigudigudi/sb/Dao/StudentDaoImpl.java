package com.gudigudigudi.sb.Dao;

import com.gudigudigudi.sb.Model.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("LocalData")
public class StudentDaoImpl implements StudentDao {

    private static Map<Integer, Student> students;

    static {
        students = new HashMap<Integer, Student>() {
            {
                put(1, new Student(1, "Said", "Computer Science"));
                put(2, new Student(2, "Alex U", "Finance"));
                put(3, new Student(3, "Anna", "Maths"));
            }
        };
    }

    @Override
    public Collection<Student> getAllStudents() {
        return students.values();
    }

    @Override
    public Student getStudentById(int id) {
        return students.get(id);
    }

    @Override
    public void removeStudentById(int id) {
        students.remove(id);
    }

    @Override
    public void updateStudentById(Student student) {
        Student student1 = students.get(student.getId());

        student1.setCourse(student.getCourse());
        student1.setName(student.getName());

        students.put(student.getId(), student1);
    }

    @Override
    public void insertStudentToDb(Student student) {
        students.put(student.getId(),student);
    }
}
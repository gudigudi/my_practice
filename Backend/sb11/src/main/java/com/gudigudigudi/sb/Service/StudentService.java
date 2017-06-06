package com.gudigudigudi.sb.Service;

import com.gudigudigudi.sb.Dao.StudentDao;
import com.gudigudigudi.sb.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    @Autowired
//    @Qualifier("LocalData")
    @Qualifier("MysqlData")
    private StudentDao studentDao;

    public Collection<Student> getAllStudents(){
        return studentDao.getAllStudents();
    }

    public Student getStudentById(int id){
        return studentDao.getStudentById(id);
    }

    public void deleteStudentById(int id) {
        studentDao.removeStudentById(id);
    }

    public void updateStudent(Student student){
        this.studentDao.updateStudentById(student);
    }

    public void insertStudent(Student student) {
        studentDao.insertStudentToDb(student);
    }
}

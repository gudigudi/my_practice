package com.gudigudigudi.sb.Dao;

import com.gudigudigudi.sb.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository
@Qualifier("MysqlData")
public class MysqlStudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setCourse(rs.getString("course"));

            return student;
        }
    }

    @Override
    public Collection<Student> getAllStudents() {

        final String sql = "SELECT id, name, course FROM students";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        final String sql = "SELECT id, name, course FROM students WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
        return student;
    }

    @Override
    public void removeStudentById(int id) {
        jdbcTemplate.update("DELETE FROM students WHERE id = ?",id);
    }

    @Override
    public void updateStudentById(Student student) {
        final int id = student.getId();
        final String name = student.getName();
        final String course=student.getCourse();

        jdbcTemplate.update("UPDATE students SET name = ?,course = ? WHERE id = ?",
                new Object[]{name,course,id});
    }

    @Override
    public void insertStudentToDb(Student student) {
        final String name = student.getName();
        final String course=student.getCourse();

        jdbcTemplate.update("INSERT INTO students (name,course) VALUES (?,?)",
                new Object[]{name,course});

    }
}

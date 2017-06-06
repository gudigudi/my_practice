package com.gudigudigudi.sb.Dao;

import com.gudigudigudi.sb.Model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String>{

    public List<Course> findByTopicId(String topicId);
    public List<Course> findByName(String name);
    public List<Course> findByDescription(String description);
}
package com.gudigudigudi.sb.Controller;

import com.gudigudigudi.sb.Model.Course;
import com.gudigudigudi.sb.Model.Topic;
import com.gudigudigudi.sb.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/topics/{id}/courses")
    public List<Course> getAllCourses(@PathVariable String topicId){
        return courseService.getAllCourses(topicId);
    }

    @RequestMapping(value = "/topics/{topicId}/courses/{id}")
    public Course getCourse(@PathVariable String id){
        return courseService.getCourseById(id);
    }

    @RequestMapping(value = "/topic/{topicId}/courses{id}", method = RequestMethod.POST)
    public void addCourse(@RequestBody Course course, @PathVariable String topicId) {
        course.setTopic(new Topic(topicId,"",""));
        courseService.addCourse(course);
    }

    @RequestMapping(value = "/topics/{topicId}/cources/{id}",method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course, @PathVariable String id, @PathVariable String topicId){
        course.setTopic(new Topic(topicId,"",""));
        courseService.updateCourse(course);
    }

    @RequestMapping(value = "/topics/{topicId}/cources/{id}",method = RequestMethod.DELETE)
    public void deleteCource(@PathVariable String id){
        courseService.deleteCourse(id);
    }



}

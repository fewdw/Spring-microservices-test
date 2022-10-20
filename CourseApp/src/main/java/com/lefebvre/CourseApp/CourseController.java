package com.lefebvre.CourseApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    //GET LIST OF ALL COURSES
    @RequestMapping("/")
    public String getCoursesAppHome() {
        return "course app home";
    }

    //GET LIST OF ALL COURSES
    @RequestMapping("/courses")
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    //GET ONLY ONE COURSE
    @RequestMapping("courses/id/{id}")
    public Course getSpecificCourse(@PathVariable("id") BigInteger id){
        return courseRepository.getOne(id);
    }

    //POST
    @RequestMapping(method = RequestMethod.POST, value="/courses")
    public void saveCourse(@RequestBody Course course){
        courseRepository.save(course);

        //return all courses when new course is added
        //return courseRepository.findAll();
    }

    //DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/courses/id/{id}")
    public void deleteCourse(@PathVariable BigInteger id){
        courseRepository.deleteById(id);

        //return list of courses when course is deleted
        //return courseRepository.findAll();
    }

}
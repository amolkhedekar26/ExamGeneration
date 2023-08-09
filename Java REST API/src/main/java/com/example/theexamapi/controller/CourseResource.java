package com.example.theexamapi.controller;


import com.example.theexamapi.entity.TCourseEntity;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.CourseService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Course resource (exposed at "courses" path)
 */
@Slf4j
@Path("courses")
public class CourseResource {

    private CourseService courseService;

    /**
     * @param courseService
     */
    @Inject
    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TCourseEntity> getCoursesList() {
        return courseService.findAll();
    }

    @GET
    @Path("{courseUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TCourseEntity getCourse(@PathParam(value = "courseUid") String courseUid) {
        try {
            TCourseEntity tCourse= courseService.findById(courseUid).orElseThrow(() -> new ResourceNotFoundException("courseUid " + courseUid + " not found"));
            return tCourse;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("courseUid " + courseUid + " not found");
        }
    }

    @POST
    public String createCourse(@Valid TCourseEntity tCourse) {
        courseService.save(tCourse);
        return "Course added";
    }

    @PUT
    @Path("{courseUid}")
    public String updateCourse(@PathParam(value = "courseUid") String courseUid, @Valid TCourseEntity tCourse) {
        try {
            return courseService.findById(courseUid).map(s -> {
                if(tCourse.getTitle()!=null){
                    s.setTitle(tCourse.getTitle());
                }
                if(tCourse.getDetails()!=null){
                    s.setDetails(tCourse.getDetails());
                }

                courseService.update(s);
                return "Course updated";
            }).orElseThrow(() -> new ResourceNotFoundException("courseUid " + courseUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("courseUid " + courseUid + " not found");
        }
    }

    @DELETE
    @Path("{courseUid}")
    public String deleteCourse(@PathParam(value = "courseUid") String courseUid) {
        try {
            return courseService.findById(courseUid).map(p -> {
                courseService.deleteById(courseUid);
                return "Course deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("courseUid " + courseUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("courseUid " + courseUid + " not found");
        }

    }
}

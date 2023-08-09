package com.example.theexamapi.controller;


import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TCourseService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Course resource (exposed at "tcourses" path)
 */
@Slf4j
@Path("tcourses")
public class TCourseResource {

    private TCourseService courseService;

    /**
     * @param courseService
     */
    @Inject
    public TCourseResource(TCourseService courseService) {
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
    public List<TCourse> getCoursesList() {
        return courseService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tcourses-by-stream")
    public List<TCourse> getCoursesByStream(@QueryParam("streamUid") String streamUid) {
        TStream tStream = new TStream();
        tStream.setUidStream(streamUid);
        return courseService.findByStream(tStream);
    }

    @GET
    @Path("{courseUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TCourse getCourse(@PathParam(value = "courseUid") String courseUid) {
        try {
            TCourse tCourse= courseService.findById(courseUid).orElseThrow(() -> new ResourceNotFoundException("courseUid " + courseUid + " not found"));
            return tCourse;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("courseUid " + courseUid + " not found");
        }
    }

    @POST
    public String createCourse(@Valid TCourse tCourse) {
        courseService.save(tCourse);
        return "Course added";
    }

    @PUT
    @Path("{courseUid}")
    public String updateCourse(@PathParam(value = "courseUid") String courseUid, @Valid TCourse tCourse) {
        try {
            return courseService.findById(courseUid).map(s -> {
                if(tCourse.getStitle()!=null){
                    s.setStitle(tCourse.getStitle());
                }
                if(tCourse.getSdetails()!=null){
                    s.setSdetails(tCourse.getSdetails());
                }
                if(tCourse.getUidStreamFk()!=null){
                    s.setUidStreamFk(tCourse.getUidStreamFk());
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

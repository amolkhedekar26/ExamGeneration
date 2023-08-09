package com.example.theexamapi.controller;


import com.example.theexamapi.entity.TExam;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TExamService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Exam resource (exposed at "texams" path)
 */
@Slf4j
@Path("texams")
public class TExamResource {

    private TExamService examService;

    /**
     * @param examService
     */
    @Inject
    public TExamResource(TExamService examService) {
        this.examService = examService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TExam> getExamsList() {
        return examService.findAll();
    }
    
    @GET
    @Path("{examUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TExam getExam(@PathParam(value = "examUid") String examUid) {
        try {
            TExam tExam= examService.findById(examUid).orElseThrow(() -> new ResourceNotFoundException("examUid " + examUid + " not found"));
            return tExam;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("examUid " + examUid + " not found");
        }
    }

    @POST
    public String createCourse(@Valid TExam tExam) {
        examService.save(tExam);
        return "Exam added";
    }

    @PUT
    @Path("{examUid}")
    public String updateCourse(@PathParam(value = "examUid") String examUid, @Valid TExam tExam) {
        try {
            return examService.findById(examUid).map(s -> {
                if(tExam.getStitle()!=null){
                    s.setStitle(tExam.getStitle());
                }
                if(tExam.getSdetails()!=null){
                    s.setSdetails(tExam.getSdetails());
                }
                if(tExam.getUidSubjectFk()!=null){
                    s.setUidSubjectFk(tExam.getUidSubjectFk());
                }
                examService.update(s);
                return "Exam updated";
            }).orElseThrow(() -> new ResourceNotFoundException("examUid " + examUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("examUid " + examUid + " not found");
        }
    }

    @DELETE
    @Path("{examUid}")
    public String deleteCourse(@PathParam(value = "examUid") String examUid) {
        try {
            return examService.findById(examUid).map(p -> {
                examService.deleteById(examUid);
                return "Exam deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("examUid " + examUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("examUid " + examUid + " not found");
        }

    }
}

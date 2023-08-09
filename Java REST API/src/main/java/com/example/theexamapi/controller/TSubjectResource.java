package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TDepartmentClassMapSemesterMapping;
import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.entity.TSubject;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TSubjectService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Class resource (exposed at "tsubjects" path)
 */
@Slf4j
@Path("tsubjects")
public class TSubjectResource {

    private TSubjectService subjectService;

    /**
     * @param subjectService
     */
    @Inject
    public TSubjectResource(TSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TSubject> getSubjectsList() {
        return subjectService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tsubjects-by-semester")
    public List<TSubject> getCoursesByStream(@QueryParam("semesterUid") String semesterUid) {
        TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping = new TDepartmentClassMapSemesterMapping();
        tDepartmentClassMapSemesterMapping.setUidDepartmentClassMapSemesterMapping(semesterUid);
        return subjectService.findBySemester(tDepartmentClassMapSemesterMapping);
    }

    @GET
    @Path("{subjectUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TSubject getSubject(@PathParam(value = "subjectUid") String subjectUid) {
        try {
            TSubject tSubject= subjectService.findById(subjectUid).orElseThrow(() -> new ResourceNotFoundException("subjectUid " + subjectUid + " not found"));
            return tSubject;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("subjectUid " + subjectUid + " not found");
        }
    }

    @POST
    public String createSubject(@Valid TSubject tSubject) {
        subjectService.save(tSubject);
        return "Subject added";
    }

    @PUT
    @Path("{subjectUid}")
    public String updateSubject(@PathParam(value = "subjectUid") String subjectUid, @Valid TSubject tSubject) {
        try {
            return subjectService.findById(subjectUid).map(s -> {
                if(tSubject.getStitle()!=null){
                    s.setStitle(tSubject.getStitle());
                }
                if(tSubject.getSdetails()!=null){
                    s.setSdetails(tSubject.getSdetails());
                }

                subjectService.update(s);
                return "Subject updated";
            }).orElseThrow(() -> new ResourceNotFoundException("subjectUid " + subjectUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("subjectUid " + subjectUid + " not found");
        }
    }

    @DELETE
    @Path("{subjectUid}")
    public String deleteSubject(@PathParam(value = "subjectUid") String subjectUid) {
        try {
            return subjectService.findById(subjectUid).map(p -> {
                subjectService.deleteById(subjectUid);
                return "Subject deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("subjectUid " + subjectUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("subjectUid " + subjectUid + " not found");
        }
    }
}

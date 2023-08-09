package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TSemester;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TSemesterService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Class resource (exposed at "tsemesters" path)
 */
@Slf4j
@Path("tsemesters")
public class TSemesterResource {

    private TSemesterService tSemesterService;

    /**
     * @param tSemesterService
     */
    @Inject
    public TSemesterResource(TSemesterService tSemesterService) {
        this.tSemesterService = tSemesterService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TSemester> getSemestersList() {
        return tSemesterService.findAll();
    }

    @GET
    @Path("{semesterUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TSemester getSemester(@PathParam(value = "semesterUid") String semesterUid) {
        try {
            TSemester tClass= tSemesterService.findById(semesterUid).orElseThrow(() -> new ResourceNotFoundException("semesterUid " + semesterUid + " not found"));
            return tClass;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("semesterUid " + semesterUid + " not found");
        }
    }

    @POST
    public String createSemester(@Valid TSemester tClass) {
        tSemesterService.save(tClass);
        return "Semester added";
    }

    @PUT
    @Path("{semesterUid}")
    public String updateSemester(@PathParam(value = "semesterUid") String semesterUid, @Valid TSemester tClass) {
        try {
            return tSemesterService.findById(semesterUid).map(s -> {
                if(tClass.getStitle()!=null){
                    s.setStitle(tClass.getStitle());
                }
                if(tClass.getSdetails()!=null){
                    s.setSdetails(tClass.getSdetails());
                }

                tSemesterService.update(s);
                return "Semester updated";
            }).orElseThrow(() -> new ResourceNotFoundException("semesterUid " + semesterUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("semesterUid " + semesterUid + " not found");
        }
    }

    @DELETE
    @Path("{semesterUid}")
    public String deleteSemester(@PathParam(value = "semesterUid") String semesterUid) {
        try {
            return tSemesterService.findById(semesterUid).map(p -> {
                tSemesterService.deleteById(semesterUid);
                return "Semester deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("semesterUid " + semesterUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("semesterUid " + semesterUid + " not found");
        }
    }
}

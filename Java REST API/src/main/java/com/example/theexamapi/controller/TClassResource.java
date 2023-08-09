package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TClass;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TClassService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Class resource (exposed at "tclasses" path)
 */
@Slf4j
@Path("tclasses")
public class TClassResource {

    private TClassService classService;

    /**
     * @param classService
     */
    @Inject
    public TClassResource(TClassService classService) {
        this.classService = classService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TClass> getClassesList() {
        return classService.findAll();
    }

    @GET
    @Path("{classUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TClass getClass(@PathParam(value = "classUid") String classUid) {
        try {
            TClass tClass= classService.findById(classUid).orElseThrow(() -> new ResourceNotFoundException("classUid " + classUid + " not found"));
            return tClass;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("classUid " + classUid + " not found");
        }
    }

    @POST
    public String createClass(@Valid TClass tClass) {
        classService.save(tClass);
        return "Class added";
    }

    @PUT
    @Path("{classUid}")
    public String updateClass(@PathParam(value = "classUid") String classUid, @Valid TClass tClass) {
        try {
            return classService.findById(classUid).map(s -> {
                if(tClass.getStitle()!=null){
                    s.setStitle(tClass.getStitle());
                }
                if(tClass.getSdetails()!=null){
                    s.setSdetails(tClass.getSdetails());
                }

                classService.update(s);
                return "Class updated";
            }).orElseThrow(() -> new ResourceNotFoundException("classUid " + classUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("classUid " + classUid + " not found");
        }
    }

    @DELETE
    @Path("{classUid}")
    public String deleteClass(@PathParam(value = "classUid") String classUid) {
        try {
            return classService.findById(classUid).map(p -> {
                classService.deleteById(classUid);
                return "Class deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("classUid " + classUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("classUid " + classUid + " not found");
        }
    }
}

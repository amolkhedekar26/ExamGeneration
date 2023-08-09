package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TClassEntity;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.ClassService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Class resource (exposed at "classes" path)
 */
@Slf4j
@Path("classes")
public class ClassResource {

    private ClassService classService;

    /**
     * @param classService
     */
    @Inject
    public ClassResource(ClassService classService) {
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
    public List<TClassEntity> getClassesList() {
        return classService.findAll();
    }

    @GET
    @Path("{classUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TClassEntity getClass(@PathParam(value = "classUid") String classUid) {
        try {
            TClassEntity tClass= classService.findById(classUid).orElseThrow(() -> new ResourceNotFoundException("classUid " + classUid + " not found"));
            return tClass;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("classUid " + classUid + " not found");
        }
    }

    @POST
    public String createClass(@Valid TClassEntity tClass) {
        classService.save(tClass);
        return "Class added";
    }

    @PUT
    @Path("{classUid}")
    public String updateClass(@PathParam(value = "classUid") String classUid, @Valid TClassEntity tClass) {
        try {
            return classService.findById(classUid).map(s -> {
                if(tClass.getTitle()!=null){
                    s.setTitle(tClass.getTitle());
                }
                if(tClass.getDetails()!=null){
                    s.setDetails(tClass.getDetails());
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

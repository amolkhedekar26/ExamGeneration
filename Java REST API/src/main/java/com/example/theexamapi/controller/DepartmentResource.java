package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TDepartmentEntity;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Department resource (exposed at "departments" path)
 */
@Slf4j
@Path("departments")
public class DepartmentResource {

    private DepartmentService departmentService;

    /**
     * @param departmentService
     */
    @Inject
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TDepartmentEntity> getDepartmentsList() {
        return departmentService.findAll();
    }

    @GET
    @Path("{departmentUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TDepartmentEntity getDepartment(@PathParam(value = "departmentUid") String departmentUid) {
        try {
            TDepartmentEntity tDepartment= departmentService.findById(departmentUid).orElseThrow(() -> new ResourceNotFoundException("departmentUid " + departmentUid + " not found"));
            return tDepartment;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("departmentUid " + departmentUid + " not found");
        }
    }

    @POST
    public String createDepartment(@Valid TDepartmentEntity tDepartment) {
        departmentService.save(tDepartment);
        return "Department added";
    }

    @PUT
    @Path("{departmentUid}")
    public String updateDepartment(@PathParam(value = "departmentUid") String departmentUid, @Valid TDepartmentEntity tDepartment) {
        try {
            return departmentService.findById(departmentUid).map(s -> {
                if(tDepartment.getTitle()!=null){
                    s.setTitle(tDepartment.getTitle());
                }
                if(tDepartment.getDetails()!=null){
                    s.setDetails(tDepartment.getDetails());
                }

                departmentService.update(s);
                return "Department updated";
            }).orElseThrow(() -> new ResourceNotFoundException("departmentUid " + departmentUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("departmentUid " + departmentUid + " not found");
        }
    }

    @DELETE
    @Path("{departmentUid}")
    public String deleteDepartment(@PathParam(value = "departmentUid") String departmentUid) {
        try {
            return departmentService.findById(departmentUid).map(p -> {
                departmentService.deleteById(departmentUid);
                return "Department deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("departmentUid " + departmentUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("departmentUid " + departmentUid + " not found");
        }
    }
}

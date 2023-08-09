package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.entity.TDepartmentClassMapping;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TDepartmentClassMappingService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Class resource (exposed at "tdepartmentclassmappings" path)
 */
@Slf4j
@Path("tdepartmentclassmappings")
public class TDepartmentClassMappingResource {

    private TDepartmentClassMappingService tDepartmentClassMappingService;

    /**
     * @param tDepartmentClassMappingService
     */
    @Inject
    public TDepartmentClassMappingResource(TDepartmentClassMappingService tDepartmentClassMappingService) {
        this.tDepartmentClassMappingService = tDepartmentClassMappingService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TDepartmentClassMapping> getClassesList() {
        return tDepartmentClassMappingService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tclasses-by-department")
    public List<TDepartmentClassMapping> getClassesByDepartment(@QueryParam("departmentUid") String departmentUid) {
        TDepartment tDepartment = new TDepartment();
        tDepartment.setUidDepartment(departmentUid);
        return tDepartmentClassMappingService.findByDepartment(tDepartment);
    }

    @GET
    @Path("{departmentClassMappingUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TDepartmentClassMapping getDepartmentClassMapping(@PathParam(value = "departmentClassMappingUid") String departmentClassMappingUid) {
        try {
            TDepartmentClassMapping tDepartmentClassMapping= tDepartmentClassMappingService.findById(departmentClassMappingUid).orElseThrow(() -> new ResourceNotFoundException("departmentClassMappingUid " + departmentClassMappingUid + " not found"));
            return tDepartmentClassMapping;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("departmentClassMappingUid " + departmentClassMappingUid + " not found");
        }
    }

    @POST
    public String createDepartmentClassMapping(@Valid TDepartmentClassMapping tDepartmentClassMapping) {
        tDepartmentClassMappingService.save(tDepartmentClassMapping);
        return "DepartmentClassMapping added";
    }

    @PUT
    @Path("{departmentClassMappingUid}")
    public String updateDepartmentClassMapping(@PathParam(value = "departmentClassMappingUid") String departmentClassMappingUid, @Valid TDepartmentClassMapping tDepartmentClassMapping) {
        try {
            return tDepartmentClassMappingService.findById(departmentClassMappingUid).map(s -> {
                if(tDepartmentClassMapping.getUidDepartmentFk()!=null){
                    s.setUidDepartmentFk(tDepartmentClassMapping.getUidDepartmentFk());
                }
                if(tDepartmentClassMapping.getUidClassFk()!=null){
                    s.setUidClassFk(tDepartmentClassMapping.getUidClassFk());
                }

                tDepartmentClassMappingService.update(s);
                return "DepartmentClassMapping updated";
            }).orElseThrow(() -> new ResourceNotFoundException("departmentClassMappingUid " + departmentClassMappingUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("departmentClassMappingUid " + departmentClassMappingUid + " not found");
        }
    }

    @DELETE
    @Path("{departmentClassMappingUid}")
    public String deleteDepartmentClassMapping(@PathParam(value = "departmentClassMappingUid") String departmentClassMappingUid) {
        try {
            return tDepartmentClassMappingService.findById(departmentClassMappingUid).map(p -> {
                tDepartmentClassMappingService.deleteById(departmentClassMappingUid);
                return "DepartmentClassMapping deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("departmentClassMappingUid " + departmentClassMappingUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("departmentClassMappingUid " + departmentClassMappingUid + " not found");
        }
    }
}

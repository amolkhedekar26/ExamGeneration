package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.entity.TDepartmentClassMapSemesterMapping;
import com.example.theexamapi.entity.TDepartmentClassMapping;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TDepartmentClassMapSemesterMappingService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Class resource (exposed at "tdepartmentclassmapsemestermappings" path)
 */
@Slf4j
@Path("tdepartmentclassmapsemestermappings")
public class TDepartmentClassMapSemesterMappingResource {

    private TDepartmentClassMapSemesterMappingService tDepartmentClassMapSemesterMappingService;

    /**
     * @param tDepartmentClassMapSemesterMappingService
     */
    @Inject
    public TDepartmentClassMapSemesterMappingResource(TDepartmentClassMapSemesterMappingService tDepartmentClassMapSemesterMappingService) {
        this.tDepartmentClassMapSemesterMappingService = tDepartmentClassMapSemesterMappingService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TDepartmentClassMapSemesterMapping> getDepartmentClassMapSemesterMappingsList() {
        return tDepartmentClassMapSemesterMappingService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tsemesters-by-class")
    public List<TDepartmentClassMapSemesterMapping> getSemestersByClass(@QueryParam("departmentClassMapUid") String departmentClassMapUid) {
        TDepartmentClassMapping tDepartmentClassMapping = new TDepartmentClassMapping();
        tDepartmentClassMapping.setUidDepartmentClassMapping(departmentClassMapUid);
        return tDepartmentClassMapSemesterMappingService.findByClass(tDepartmentClassMapping);
    }

    @GET
    @Path("{departmentClassMapSemesterMappingUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TDepartmentClassMapSemesterMapping getDepartmentClassMapSemesterMapping(@PathParam(value = "departmentClassMapSemesterMappingUid") String departmentClassMapSemesterMappingUid) {
        try {
            TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping = tDepartmentClassMapSemesterMappingService.findById(departmentClassMapSemesterMappingUid).orElseThrow(() -> new ResourceNotFoundException("departmentClassMapSemesterMappingUid " + departmentClassMapSemesterMappingUid + " not found"));
            return tDepartmentClassMapSemesterMapping;
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("departmentClassMapSemesterMappingUid " + departmentClassMapSemesterMappingUid + " not found");
        }
    }

    @POST
    public String createDepartmentClassMapSemesterMapping(@Valid TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping) {
        tDepartmentClassMapSemesterMappingService.save(tDepartmentClassMapSemesterMapping);
        return "DepartmentClassMapSemesterMapping added";
    }

    @PUT
    @Path("{departmentClassMapSemesterMappingUid}")
    public String updateDepartmentClassMapSemesterMapping(@PathParam(value = "departmentClassMapSemesterMappingUid") String departmentClassMapSemesterMappingUid, @Valid TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping) {
        try {
            return tDepartmentClassMapSemesterMappingService.findById(departmentClassMapSemesterMappingUid).map(s -> {
                if (tDepartmentClassMapSemesterMapping.getUidDepartmentClassMappingFk() != null) {
                    s.setUidDepartmentClassMappingFk(tDepartmentClassMapSemesterMapping.getUidDepartmentClassMappingFk());
                }
                if (tDepartmentClassMapSemesterMapping.getUidSemesterFk() != null) {
                    s.setUidSemesterFk(tDepartmentClassMapSemesterMapping.getUidSemesterFk());
                }

                tDepartmentClassMapSemesterMappingService.update(s);
                return "DepartmentClassMapSemesterMapping updated";
            }).orElseThrow(() -> new ResourceNotFoundException("departmentClassMapSemesterMappingUid " + departmentClassMapSemesterMappingUid + " not found"));
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("departmentClassMapSemesterMappingUid " + departmentClassMapSemesterMappingUid + " not found");
        }
    }

    @DELETE
    @Path("{departmentClassMapSemesterMappingUid}")
    public String deleteDepartmentClassMapSemesterMapping(@PathParam(value = "departmentClassMapSemesterMappingUid") String departmentClassMapSemesterMappingUid) {
        try {
            return tDepartmentClassMapSemesterMappingService.findById(departmentClassMapSemesterMappingUid).map(p -> {
                tDepartmentClassMapSemesterMappingService.deleteById(departmentClassMapSemesterMappingUid);
                return "DepartmentClassMapSemesterMapping deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("departmentClassMapSemesterMappingUid " + departmentClassMapSemesterMappingUid + " not found"));
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("departmentClassMapSemesterMappingUid " + departmentClassMapSemesterMappingUid + " not found");
        }
    }
}

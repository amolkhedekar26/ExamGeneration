package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TDepartmentService;
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
@Path("tdepartments")
public class TDepartmentResource {

    private TDepartmentService departmentService;

    /**
     * @param departmentService
     */
    @Inject
    public TDepartmentResource(TDepartmentService departmentService) {
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
    public List<TDepartment> getDepartmentsList() {
        return departmentService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tdepartments-by-course")
    public List<TDepartment> getCoursesByStream(@QueryParam("courseUid") String courseUid) {
        TCourse tCourse = new TCourse();
        tCourse.setUidCourse(courseUid);
        return departmentService.findByCourse(tCourse);
    }

    @GET
    @Path("{departmentUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TDepartment getDepartment(@PathParam(value = "departmentUid") String departmentUid) {
        try {
            TDepartment tDepartment = departmentService.findById(departmentUid).orElseThrow(() -> new ResourceNotFoundException("departmentUid " + departmentUid + " not found"));
            return tDepartment;
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("departmentUid " + departmentUid + " not found");
        }
    }

    @POST
    public String createDepartment(@Valid TDepartment tDepartment) {
        departmentService.save(tDepartment);
        return "Department added";
    }

    @PUT
    @Path("{departmentUid}")
    public String updateDepartment(@PathParam(value = "departmentUid") String departmentUid, @Valid TDepartment tDepartment) {
        try {
            return departmentService.findById(departmentUid).map(s -> {
                if (tDepartment.getStitle() != null) {
                    s.setStitle(tDepartment.getStitle());
                }
                if (tDepartment.getSdetails() != null) {
                    s.setSdetails(tDepartment.getSdetails());
                }

                departmentService.update(s);
                return "Department updated";
            }).orElseThrow(() -> new ResourceNotFoundException("departmentUid " + departmentUid + " not found"));
        } catch (NoResultException noResultException) {
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
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("departmentUid " + departmentUid + " not found");
        }
    }
}

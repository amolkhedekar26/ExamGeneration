package com.example.theexamapi.controller;


import com.example.theexamapi.entity.TExam;
import com.example.theexamapi.entity.TExamQuestionMapping;
import com.example.theexamapi.entity.TMcqOption;
import com.example.theexamapi.entity.TQuestion;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TExamQuestionMappingService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Exam resource (exposed at "texamquestionmapping" path)
 */
@Slf4j
@Path("texamquestionmapping")
public class TExamQuestionMappingResource {

    private TExamQuestionMappingService tExamQuestionMappingService;

    /**
     * @param tExamQuestionMappingService
     */
    @Inject
    public TExamQuestionMappingResource(TExamQuestionMappingService tExamQuestionMappingService) {
        this.tExamQuestionMappingService = tExamQuestionMappingService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TExamQuestionMapping> getExamsList() {
        return tExamQuestionMappingService.findAll();
    }

    @GET
    @Path("{tExamQuestionMappingUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TExamQuestionMapping getExam(@PathParam(value = "tExamQuestionMappingUid") String tExamQuestionMappingUid) {
        try {
            TExamQuestionMapping tExamQuestionMapping= tExamQuestionMappingService.findById(tExamQuestionMappingUid).orElseThrow(() -> new ResourceNotFoundException("tExamQuestionMappingUid " + tExamQuestionMappingUid + " not found"));
            return tExamQuestionMapping;
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("tExamQuestionMappingUid " + tExamQuestionMappingUid + " not found");
        }
    }

    @POST
    public String createExamQuestionMapping(@Valid TExamQuestionMapping tExamQuestionMapping) {
        tExamQuestionMappingService.save(tExamQuestionMapping);
        return "ExamQuestionMapping added";
    }

    @POST
    @Path("save-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createExamQuestionMappings(@Valid JsonObject examQuestionMappingJsonObject) {
        System.out.println(examQuestionMappingJsonObject.getJsonArray("questions"));
        JsonArray questions = examQuestionMappingJsonObject.getJsonArray("questions");
        TExam tExam = new TExam();
        tExam.setUidExam(examQuestionMappingJsonObject.getString("uidExam"));
        for (int i = 0; i < questions.size(); i++) {
            JsonObject question = questions.getJsonObject(i);
            TQuestion tQuestion = new TQuestion();
            tQuestion.setUidQuestion(question.getString("uidQuestion"));

            TExamQuestionMapping tExamQuestionMapping = new TExamQuestionMapping();
            tExamQuestionMapping.setUidExamFk(tExam);
            tExamQuestionMapping.setUidQuestionFk(tQuestion);
            tExamQuestionMappingService.save(tExamQuestionMapping);
        }
        return Response.ok()
                .entity("Questions added to exam").build();
    }

    @PUT
    @Path("{tExamQuestionMappingUid}")
    public String updateCourse(@PathParam(value = "tExamQuestionMappingUid") String tExamQuestionMappingUid, @Valid TExamQuestionMapping tExamQuestionMapping) {
        try {
            return tExamQuestionMappingService.findById(tExamQuestionMappingUid).map(s -> {
                if(tExamQuestionMapping.getUidExamFk()!=null){
                    s.setUidExamFk(tExamQuestionMapping.getUidExamFk());
                }
                if(tExamQuestionMapping.getUidQuestionFk()!=null){
                    s.setUidQuestionFk(tExamQuestionMapping.getUidQuestionFk());
                }
                tExamQuestionMappingService.update(s);
                return "ExamQuestionMapping updated";
            }).orElseThrow(() -> new ResourceNotFoundException("tExamQuestionMappingUid " + tExamQuestionMappingUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("tExamQuestionMappingUid " + tExamQuestionMappingUid + " not found");
        }
    }

    @DELETE
    @Path("{tExamQuestionMappingUid}")
    public String deleteCourse(@PathParam(value = "tExamQuestionMappingUid") String tExamQuestionMappingUid) {
        try {
            return tExamQuestionMappingService.findById(tExamQuestionMappingUid).map(p -> {
                tExamQuestionMappingService.deleteById(tExamQuestionMappingUid);
                return "ExamQuestionMapping deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("tExamQuestionMappingUid " + tExamQuestionMappingUid + " not found"));
        }catch (NoResultException noResultException){
            throw new ResourceNotFoundException("tExamQuestionMappingUid " + tExamQuestionMappingUid + " not found");
        }

    }
}

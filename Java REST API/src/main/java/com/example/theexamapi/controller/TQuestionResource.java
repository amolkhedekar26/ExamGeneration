package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TQuestion;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TQuestionService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Question resource (exposed at "tquestions" path)
 */
@Slf4j
@Path("tquestions")
public class TQuestionResource {

    private TQuestionService questionService;

    /**
     * @param questionService
     */
    @Inject
    public TQuestionResource(TQuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionsList() {
        return Response.ok()
                .entity(questionService.findAll()).build();
    }

    @GET
    @Path("{questionUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TQuestion getQuestion(@PathParam(value = "questionUid") String questionUid) {
        try {
            TQuestion tQuestion = questionService.findById(questionUid).orElseThrow(() -> new ResourceNotFoundException("questionUid " + questionUid + " not found"));
            return tQuestion;
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("questionUid " + questionUid + " not found");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createQuestion(@Valid TQuestion tQuestion) {
        TQuestion question = questionService.save(tQuestion);
        return Response.ok()
                .entity(question).build();
    }

    @PUT
    @Path("{questionUid}")
    public String updateQuestion(@PathParam(value = "questionUid") String questionUid, @Valid TQuestion tQuestion) {
        try {
            return questionService.findById(questionUid).map(s -> {
                if (tQuestion.getSquestionText() != null) {
                    s.setSquestionText(tQuestion.getSquestionText());
                }
                questionService.update(s);
                return "Question updated";
            }).orElseThrow(() -> new ResourceNotFoundException("questionUid " + questionUid + " not found"));
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("questionUid " + questionUid + " not found");
        }
    }

    @DELETE
    @Path("{questionUid}")
    public String deleteQuestion(@PathParam(value = "questionUid") String questionUid) {
        try {
            return questionService.findById(questionUid).map(p -> {
                questionService.deleteById(questionUid);
                return "Question deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("questionUid " + questionUid + " not found"));
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("questionUid " + questionUid + " not found");
        }
    }
}

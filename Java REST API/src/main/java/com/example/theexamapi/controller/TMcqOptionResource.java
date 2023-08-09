package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TMcqOption;
import com.example.theexamapi.entity.TQuestion;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TMcqOptionService;
import jdk.nashorn.api.scripting.JSObject;
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
 * McqOption resource (exposed at "tmcqoptions" path)
 */
@Slf4j
@Path("tmcqoptions")
public class TMcqOptionResource {

    private TMcqOptionService mcqOptionService;

    /**
     * @param mcqOptionService
     */
    @Inject
    public TMcqOptionResource(TMcqOptionService mcqOptionService) {
        this.mcqOptionService = mcqOptionService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMcqOptionsList() {
        return Response.ok()
                .entity(mcqOptionService.findAll()).build();
    }
    @GET
    @Path("mcq-option-by-question")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMcqOptionByQuestion(@QueryParam(value = "uidQuestion") String uidQuestion) {
        try {
            TQuestion tQuestion = new TQuestion();
            tQuestion.setUidQuestion(uidQuestion);
            List<TMcqOption> tMcqOptions = mcqOptionService.findByQuestion(tQuestion);
            return Response.ok()
                    .entity(tMcqOptions).build();
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("uidQuestion " + uidQuestion + " not found");
        }
    }

    @GET
    @Path("{mcqOptionUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TMcqOption getMcqOption(@PathParam(value = "mcqOptionUid") String mcqOptionUid) {
        try {
            TMcqOption tMcqOption = mcqOptionService.findById(mcqOptionUid).orElseThrow(() -> new ResourceNotFoundException("mcqOptionUid " + mcqOptionUid + " not found"));
            return tMcqOption;
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("mcqOptionUid " + mcqOptionUid + " not found");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMcqOption(@Valid TMcqOption tMcqOption) {
        mcqOptionService.save(tMcqOption);
        return Response.ok()
                .entity("McqOption added").build();
    }

    @POST
    @Path("save-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMcqOptionS(@Valid JsonObject mcqOptionJson) {
        System.out.println(mcqOptionJson.getJsonArray("options"));
        JsonArray options = mcqOptionJson.getJsonArray("options");
        TQuestion tQuestion = new TQuestion();
        tQuestion.setUidQuestion(mcqOptionJson.getString("uidQuestion"));
        for (int i = 0; i < options.size(); i++) {
            JsonObject option = options.getJsonObject(i);
            TMcqOption tMcqOption = new TMcqOption();
            tMcqOption.setScontent(option.getString("scontent"));
            tMcqOption.setUidQuestionFk(tQuestion);
            mcqOptionService.save(tMcqOption);
        }
        return Response.ok()
                .entity(mcqOptionJson).build();
    }

    @PUT
    @Path("{mcqOptionUid}")
    public String updateMcqOption(@PathParam(value = "mcqOptionUid") String mcqOptionUid, @Valid TMcqOption tMcqOption) {
        try {
            return mcqOptionService.findById(mcqOptionUid).map(s -> {
                if (tMcqOption.getScontent()!= null) {
                    s.setScontent(tMcqOption.getScontent());
                }
                mcqOptionService.update(s);
                return "McqOption updated";
            }).orElseThrow(() -> new ResourceNotFoundException("mcqOptionUid " + mcqOptionUid + " not found"));
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("mcqOptionUid " + mcqOptionUid + " not found");
        }
    }

    @DELETE
    @Path("{mcqOptionUid}")
    public String deleteMcqOption(@PathParam(value = "mcqOptionUid") String mcqOptionUid) {
        try {
            return mcqOptionService.findById(mcqOptionUid).map(p -> {
                mcqOptionService.deleteById(mcqOptionUid);
                return "McqOption deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("mcqOptionUid " + mcqOptionUid + " not found"));
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("mcqOptionUid " + mcqOptionUid + " not found");
        }
    }
}

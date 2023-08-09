package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TStreamEntity;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.StreamService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Stream resource (exposed at "streams" path)
 */
@Slf4j
@Path("streams")
public class StreamResource {

    private StreamService streamService;

    /**
     * @param streamService
     */
    @Inject
    public StreamResource(StreamService streamService) {
        this.streamService = streamService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "application/json" media type.
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStreamsList() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(streamService.findAll()).build();
    }

    @GET
    @Path("{streamUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TStreamEntity getStream(@PathParam(value = "streamUid") String streamUid) {
        try {
            TStreamEntity tStream = streamService.findById(streamUid).orElseThrow(() -> new ResourceNotFoundException("streamUid " + streamUid + " not found"));
            return tStream;
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("streamUid " + streamUid + " not found");
        }
    }

    @POST
    public String createStream(@Valid TStreamEntity tStream) {
        streamService.save(tStream);
        return "Stream added";
    }

    @PUT
    @Path("{streamUid}")
    public String updateStream(@PathParam(value = "streamUid") String streamUid, @Valid TStreamEntity tStream) {
        try {
            return streamService.findById(streamUid).map(s -> {
                if (tStream.getTitle() != null) {
                    s.setTitle(tStream.getTitle());
                }
                if (tStream.getDetails() != null) {
                    s.setDetails(tStream.getDetails());
                }

                streamService.update(s);
                return "Stream updated";
            }).orElseThrow(() -> new ResourceNotFoundException("streamUid " + streamUid + " not found"));
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("streamUid " + streamUid + " not found");
        }
    }

    @DELETE
    @Path("{streamUid}")
    public String deleteStream(@PathParam(value = "streamUid") String streamUid) {
        try {
            return streamService.findById(streamUid).map(p -> {
                streamService.deleteById(streamUid);
                return "Stream deleted";
            }).orElseThrow(() -> new ResourceNotFoundException("streamUid " + streamUid + " not found"));
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("streamUid " + streamUid + " not found");
        }
    }
}

package com.example.theexamapi.controller;

import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.exception.ResourceNotFoundException;
import com.example.theexamapi.service.TStreamService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Stream resource (exposed at "tstreams" path)
 */
@Slf4j
@Path("tstreams")
public class TStreamResource {

    private TStreamService streamService;

    /**
     * @param streamService
     */
    @Inject
    public TStreamResource(TStreamService streamService) {
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
                .entity(streamService.findAll()).build();
    }

    @GET
    @Path("{streamUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TStream getStream(@PathParam(value = "streamUid") String streamUid) {
        try {
            TStream tStream = streamService.findById(streamUid).orElseThrow(() -> new ResourceNotFoundException("streamUid " + streamUid + " not found"));
            return tStream;
        } catch (NoResultException noResultException) {
            throw new ResourceNotFoundException("streamUid " + streamUid + " not found");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStream(@Valid TStream tStream) {
        streamService.save(tStream);
        return Response.ok()
                .entity("Stream added").build();
    }

    @PUT
    @Path("{streamUid}")
    public String updateStream(@PathParam(value = "streamUid") String streamUid, @Valid TStream tStream) {
        try {
            return streamService.findById(streamUid).map(s -> {
                if (tStream.getStitle()!= null) {
                    s.setStitle(tStream.getStitle());
                }
                if (tStream.getSdetails() != null) {
                    s.setSdetails(tStream.getSdetails());
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

package com.fiserv.fs.helix.rest.client;

import com.fiserv.fs.helix.model.Party;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/PartyDirectoryClient")
public class PartyDirectoryResource {

    @Inject
    @RestClient
    PartyDirectoryService partyDirectoryService;

    @GET
    @Path("/t-id/{id}")
    @Blocking
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Party> id(@PathParam("id") String id) {
        return partyDirectoryService.getById(id);
    }

    @GET
    @Path("/t-async/{id}")
    public CompletionStage<Set<Party>> idAsync(@PathParam("id") String id) {
        return partyDirectoryService.getByIdAsync(id);
    }

    @GET
    @Path("/t-uni/{id}")
    public Uni<Set<Party>> idMutiny(@PathParam("id") String id) {
        return partyDirectoryService.getByIdAsUni(id);
    }
}
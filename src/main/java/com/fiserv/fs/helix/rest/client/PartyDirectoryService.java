package com.fiserv.fs.helix.rest.client;

import com.fiserv.fs.helix.model.Party;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/PartyDirectory")
@RegisterRestClient(configKey="PartyDirectory-api")
public interface PartyDirectoryService {

    @GET
    @Path("/{id}")
    Set<Party> getById(@PathParam("id") String id);

    @GET
    @Path("/async/{id}")
    CompletionStage<Set<Party>> getByIdAsync(@PathParam("id") String id);

    @GET
    @Path("/uni/{id}")
    Uni<Set<Party>> getByIdAsUni(@PathParam("id") String id);
}

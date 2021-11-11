package org.acme.rest.json;

import java.util.Set;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.quarkus.security.Authenticated;

@Path("/expenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class ExpenseResource {
    @Inject
    public ExpenseService expenseService;

    @GET
    @RolesAllowed("read")
    public Set<Expense> list() {
        return expenseService.list();
    }

    @POST
    @RolesAllowed("modify")
    public Expense create(Expense expense) {
        return expenseService.create(expense);
    }

    @DELETE
    @Path("{uuid}")
    @RolesAllowed("delete")
    public Set<Expense> delete(@PathParam("uuid") UUID uuid) {
        if (!expenseService.delete(uuid)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return expenseService.list();
    }

    @PUT
    @RolesAllowed("modify")
    public void update(Expense expense) {
        expenseService.update(expense);
    }
}

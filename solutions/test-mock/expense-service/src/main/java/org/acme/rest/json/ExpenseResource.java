package org.acme.rest.json;

import java.util.List;
import java.util.UUID;

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


@Path("/expenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpenseResource {
    @Inject
    public ExpenseService expenseService;

    @GET
    public List<Expense> list() {
        return expenseService.list();
    }

    @POST
    public Expense create(Expense expense) {
        return expenseService.create(expense);
    }

    @DELETE
    @Path("{uuid}")
    public List<Expense> delete(@PathParam("uuid") UUID uuid) {
        return expenseService.delete(uuid);
    }

    @PUT
    public void update(Expense expense) {
        if (expenseService.exists(expense.uuid)) {
            expenseService.update(expense);
        }
        else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
package org.acme.rest.json;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


@ApplicationScoped
public class ExpenseService {

    public List<Expense> list() {
        return Expense.listAll();
    }

    public Expense create(Expense expense) {
        Expense newExpense = Expense.of(expense.name, expense.paymentMethod, expense.amount.toString());
        newExpense.persist();

        return newExpense;
    }

    public List<Expense> delete(UUID uuid) {
        long numExpensesDeleted = Expense.delete("uuid", uuid);

        if (numExpensesDeleted == 0) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Expense.listAll();
    }

    public void update(Expense expense) {
        Expense.update(expense);
    }

    public boolean exists(UUID uuid) {
        return Expense.find("uuid", uuid).count() == 1;
    }
}
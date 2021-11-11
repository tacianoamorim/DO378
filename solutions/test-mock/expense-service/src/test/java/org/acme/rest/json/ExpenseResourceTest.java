package org.acme.rest.json;

import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import org.mockito.Mockito;


@QuarkusTest
public class ExpenseResourceTest {
    @Inject
    ExpenseResource expenseResource;

    @Test
    public void testUpdateExistingExpense() {
        UUID existingUuid = UUID.fromString(
            "effbfe08-0d71-46e7-87af-1132b1b94d87");
        Expense expense = new Expense();
        expense.uuid = existingUuid;
        Assertions.assertDoesNotThrow(() -> expenseResource.update(expense));
    }

    @Test
    public void testUpdateNotFoundExpense() {
        UUID notFoundUuid = UUID.fromString(
            "896bca62-b865-4e04-bc45-d028c44b381a");
        Expense expense = new Expense();
        expense.uuid = notFoundUuid;

        ExpenseService expenseServiceMock = Mockito.mock(ExpenseService.class);
        Mockito.when(expenseServiceMock.exists(notFoundUuid)).thenReturn(false);

        ExpenseResource expenseResourceNotFound = new ExpenseResource();
        expenseResourceNotFound.expenseService = expenseServiceMock;

        Assertions.assertThrows(
            WebApplicationException.class,
            () -> expenseResourceNotFound.update(expense));
    }
}

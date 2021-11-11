package org.acme.rest.json;

import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.panache.mock.PanacheMock;


@QuarkusTest
public class ExpenseServiceTest {

    @Test
    public void testDeleteExistingExpense() {
        UUID existingUuid = UUID.fromString(
            "effbfe08-0d71-46e7-87af-1132b1b94d87");
        PanacheMock.mock(Expense.class);
        Mockito.when(Expense.delete("uuid", existingUuid)).thenReturn(1L);
        ExpenseService expenseService = new ExpenseService();
        Assertions.assertDoesNotThrow(() -> expenseService.delete(existingUuid));
    }

    @Test
    public void testDeleteNotFoundExpense() {
        UUID notFoundUuid = UUID.fromString(
            "896bca62-b865-4e04-bc45-d028c44b381a");
        PanacheMock.mock(Expense.class);
        Mockito.when(Expense.delete("uuid", notFoundUuid)).thenReturn(0L);
        ExpenseService expenseService = new ExpenseService();
        Assertions.assertThrows(
            WebApplicationException.class,
            () -> expenseService.delete(notFoundUuid));
    }
}

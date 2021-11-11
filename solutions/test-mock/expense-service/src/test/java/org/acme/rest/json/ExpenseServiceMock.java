package org.acme.rest.json;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped
public class ExpenseServiceMock extends ExpenseService {
    @Override
    public void update(Expense expense) {}

    @Override
    public boolean exists(UUID uuid) {
        return uuid.equals(
            UUID.fromString("effbfe08-0d71-46e7-87af-1132b1b94d87"));
    }
}

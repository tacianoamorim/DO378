package org.acme.conference.session;

import static org.acme.conference.session.SessionFakeFactory.DEFAULT_SCHEDULE;
import static org.acme.conference.session.SessionFakeFactory.composeSession;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

/**
 * SessionStore
 */
@QuarkusTest
public class SessionStoreTest {

    @Inject
    private SessionStore store;

    @InjectMock
    SpeakerService speakerService;

    @BeforeEach
    public void setup() {
        Mockito.when(speakerService.listAll()).thenReturn(List.of(new SpeakerFromService("s-0-0","Jordi","Sola")));
        Mockito.when(speakerService.search(anyString(), anyString())).thenReturn(List.of(new SpeakerFromService("s-0-0","Jordi","Sola")));
    }

    @Test
    public void testPersist () {
        final String PERSIST_ID = "Store.persist_id";
        Session result = null;
        Session session = composeSession();
        session.id=PERSIST_ID;

        result = store.save(session);

        assertNotNull(result);
        assertEquals(PERSIST_ID, result.id);
    }

    @Test
    public void testUpdateById () {
        final String SESSION_ID = "s-1-1";
        Session result = null;
        Session session = composeSession();

        // given
        session.schedule=DEFAULT_SCHEDULE;

        result = store.updateById(SESSION_ID, session)
                .get();

        assertNotNull(result);
        assertEquals(DEFAULT_SCHEDULE, session.schedule);
    }

    @Test
    public void testFindById () {
        final String SESSION_ID = "s-1-1";
        Session result = null;

        result = store.findById(SESSION_ID)
                .get();

        assertNotNull(result);
        assertEquals(SESSION_ID, result.id);
    }

    @Test
    public void testDeleteById () {
        final String DELETE_ID = "s-2-1";
        Session result = null;

        result = store.deleteById(DELETE_ID)
                .get();

        assertNotNull(result);
        assertEquals(DELETE_ID, result.id);
    }

}

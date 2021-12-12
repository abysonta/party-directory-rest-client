package com.fiserv.fs.helix.rest.client;

import com.fiserv.fs.helix.model.Party;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@QuarkusTest
public class PartyDirectoryServiceTest {

    @Inject
    @RestClient
    PartyDirectoryService partyDirectoryService;

    @Test
    public void testPartyDirectoryRestClientService() {
        Set<Party> partySet = partyDirectoryService.getById("1");

        Assertions.assertEquals(1, partySet.size());
        for (Party party : partySet) {
            Assertions.assertEquals("1", party.id);
            Assertions.assertEquals("Aby", party.name);
            Assertions.assertEquals(40, party.age);
        }
    }

    private Set<Party> asyncPartySet;
    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void testPartyDirectoryAsyncRestClientService() throws InterruptedException {
        final CompletionStage<Set<Party>> stagePartySet = partyDirectoryService.getByIdAsync("1");

        stagePartySet.thenAccept(
                x -> {asyncPartySet = x;lock.countDown();}
        );
        boolean b = lock.await(2000, TimeUnit.MILLISECONDS);

        Assertions.assertEquals(1, asyncPartySet.size());
        for (Party party : asyncPartySet) {
            Assertions.assertEquals("1", party.id);
            Assertions.assertEquals("Aby", party.name);
            Assertions.assertEquals(40, party.age);
        }
    }

    @Test
    public void testPartyDirectoryUniRestClientService() {
        final Uni<Set<Party>> uniPartySet = partyDirectoryService.getByIdAsUni("1");
        Set<Party> partySet = uniPartySet.await().atMost(Duration.ofSeconds(5));

        Assertions.assertEquals(1, partySet.size());
        for (Party party : partySet) {
            Assertions.assertEquals("1", party.id);
            Assertions.assertEquals("Aby", party.name);
            Assertions.assertEquals(40, party.age);
        }
    }
}

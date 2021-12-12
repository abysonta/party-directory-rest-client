package com.fiserv.fs.helix.rest.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Collections;
import java.util.Map;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class PartyDirectoryWireMock implements QuarkusTestResourceLifecycleManager {
    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        stubFor(get(urlEqualTo("/PartyDirectory/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "[{" +
                                        "\"id\": \"1\"," +
                                        "\"name\": \"Aby\"," +
                                        "\"age\": 40" +
                                        "}]"
                        )));
        stubFor(get(urlEqualTo("/PartyDirectory/async/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "[{" +
                                        "\"id\": \"1\"," +
                                        "\"name\": \"Aby\"," +
                                        "\"age\": 40" +
                                        "}]"
                        )));
        stubFor(get(urlEqualTo("/PartyDirectory/uni/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                "[{" +
                                        "\"id\": \"1\"," +
                                        "\"name\": \"Aby\"," +
                                        "\"age\": 40" +
                                        "}]"
                        )));
        stubFor(get(urlMatching(".*")).atPriority(10).willReturn(aResponse().proxiedFrom("http://localhost:8080/helix/v1")));

        return Collections.singletonMap("quarkus.rest-client.PartyDirectory-api.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}

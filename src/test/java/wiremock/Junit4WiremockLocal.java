package wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import utils.Stuff;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class Junit4WiremockLocal {
    private static Logger log = Logger.getLogger(Junit4WiremockLocal.class);

    @Rule
    public WireMockRule wireMockRule;


    @Before
    public void setUp() throws Exception {
        wireMockRule = new WireMockRule(1234);
        wireMockRule.stubFor(get(
                urlEqualTo("/blah"))
                .willReturn(
                        aResponse()
                                .withBody(Stuff.getLoremIpsum())
                ));
        wireMockRule.start();
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(1000); // without this line the browser will open after Wiremock server is closed
        wireMockRule.stop();
    }

    @Test
    public void testJunit4() {
        openUrlInBrowser(wireMockRule.baseUrl() + "/blah");
    }

    private void openUrlInBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            log.error("Can't open url", e);
        }
    }
}

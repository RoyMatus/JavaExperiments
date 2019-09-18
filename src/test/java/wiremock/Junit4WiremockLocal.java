package wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import utils.BrowserUtils;
import utils.Stuff;

import static com.codeborne.selenide.Selenide.$;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Slf4j
public class Junit4WiremockLocal {

    private final String LOREM_IPSUM = Stuff.getLoremIpsum();

    @Rule
    public WireMockRule wireMockRule;

    @Before
    public void setUp() throws Exception {
        wireMockRule = new WireMockRule(1234);
        wireMockRule.stubFor(get(
                urlEqualTo("/blah"))
                .willReturn(
                        aResponse()
                                .withBody(LOREM_IPSUM)
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
        BrowserUtils.openUrlInChrome(wireMockRule.baseUrl() + "/blah");
        Assertions.assertThat(
                $(By.tagName("body")).getText())
                .isEqualTo(LOREM_IPSUM);
        // TODO: 17.09.2019 Add assertion here
    }
}

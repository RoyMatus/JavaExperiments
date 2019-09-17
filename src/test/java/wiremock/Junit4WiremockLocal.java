package wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import utils.BrowserUtils;
import utils.Stuff;

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
        openUrlInBrowser(wireMockRule.baseUrl() + "/blah");
        // TODO: 17.09.2019 Add assertion here
    }

    private void openUrlInBrowser(String url) {
        WebDriver driver = new EdgeDriver();
        BrowserUtils.openUrlInBrowser(url);
//        driver.get(url);
//        driver.switchTo().window("localhost");


//        Assertions.assertThat(driver.findElement(By.tagName("body")).getText())
//                .isEqualTo(LOREM_IPSUM);
        System.out.printf("");
    }

}

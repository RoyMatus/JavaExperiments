package wiremock;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;

import static utils.Stuff.openUrlInBrowser;

@Log4j
public class Junit4WiremockemoteInDocker {

    @Before
    public void setUp() throws Exception {
//        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
//        List<Container> containers = dockerClient.listContainersCmd().exec();
        Process p = Runtime.getRuntime().exec("docker run -it --rm -p 8443:8443 rodolpheche/wiremock --https-port 8443 --verbose");
//        Thread.sleep(1000);
        System.out.printf("");
    }

    @Test
    public void testJunit4() {

        openUrlInBrowser("http://localhost:8443/blah");
        System.out.printf("");
    }

}

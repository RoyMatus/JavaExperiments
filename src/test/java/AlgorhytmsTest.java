import algorhytms.Algorhytms;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class AlgorhytmsTest {

    Logger logger = Logger.getLogger(this.getClass().getName());

    Algorhytms algorhytms;

    @Before
    public void setUp() throws Exception {
        algorhytms = new Algorhytms();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void stringRevert() {
        String original = "ABCDEF";
        logger.info("Original string: " + original);
        String result = algorhytms.stringRevert(original);
        logger.info("The result is: " + result);
    }

    @Test
    public void stringRevert2() {
        String original = "ABCDEF";
        logger.info("Original string: " + original);
        String result = algorhytms.stringRevert2(original);
        logger.info("The result is: " + result);

    }

    @Test
    public void fibo() {
        int iterations = 20;
        StringBuilder fibo = new StringBuilder("Fibonacci: ");
        for (int i = 0; i < iterations; i++) {
            fibo.append(algorhytms.fibo(i));
            if (i < iterations - 1) fibo.append(", ");
            else fibo.append(".");
        }
        logger.info(fibo.toString());
    }
}
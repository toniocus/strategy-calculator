package ar.com.exeo.calc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class MultihtreadTest {

    @BeforeClass
    public static void init() {
        StrategyCalculatorApplication.main(new String[0]);
    }

    @AfterClass
    public static void reset() {
        StrategyCalculatorApplication.shutdown();
    }

    private RestTemplate rt = new RestTemplate();
    private final static String ADD_URL = "http://localhost:8080/ok/add/10/11";
    private final static String DIV_URL = "http://localhost:8080/ok/div/233.0000/4.75";
    private final static String MULT_URL = "http://localhost:8080/ok/mult/31/14";

    private final static String ADD_URL_BUG = "http://localhost:8080/buggy/add/10/11";
    private final static String DIV_URL_BUG = "http://localhost:8080/buggy/div/233.0000/4.75";
    private final static String MULT_URL_BUG = "http://localhost:8080/buggy/mult/31/14";


    @Test
    @Ignore("Just to instropect Spring @Autowire")
    public void testSingle() {
        System.out.println("Call Division");
        this.rt.getForEntity(DIV_URL, BigDecimal.class, Collections.emptyMap());
        System.out.println("End Call Division");
    }

    // ===================================================================
    //   ### - TEST OK
    // ===================================================================

    @Test
    public void testRandomOk() throws InterruptedException {

        Random random = new Random(System.currentTimeMillis());

        Runnable run = () -> runRandomOk(random);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            executorService.execute(run);
            executorService.execute(run);
            executorService.execute(run);

            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        }
        catch (Exception ex) {
           System.err.println("Error processing:" + ex.toString());
        }

    }

    public void runRandomOk(final Random random) {

        for (int i = 1; i < 101; i++) {

            System.out.println("====================================");
            System.out.println("TEST [" + Thread.currentThread().getName() + "]: " + i);
            System.out.println("====================================");

            try {
                switch(random.nextInt(3)) {
                case 0:
                    this.rt.getForEntity(DIV_URL, BigDecimal.class, Collections.emptyMap());
                    break;

                case 1:
                    this.rt.getForEntity(MULT_URL, BigDecimal.class, Collections.emptyMap());
                    break;

                default:
                    this.rt.getForEntity(ADD_URL, BigDecimal.class, Collections.emptyMap());
                }
            }
            catch (Exception ex) {
                System.err.println("Error processing:" + ex.toString());
                break;
            }
        }

        System.out.println("Ending thread: " + Thread.currentThread().getName());
    }



    // ===================================================================
    //   ### - TEST BUGGY
    // ===================================================================

    @Test
    public void testRandomBuggy() throws InterruptedException {

        Random random = new Random(System.currentTimeMillis());

        Runnable run = () -> runRandomBuggy(random);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            executorService.execute(run);
            executorService.execute(run);
            executorService.execute(run);

            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        }
        catch (Exception ex) {
           System.err.println("Error processing:" + ex.toString());
        }

    }

    public void runRandomBuggy(final Random random) {

        for (int i = 1; i < 101; i++) {

            System.out.println("====================================");
            System.out.println("TEST [" + Thread.currentThread().getName() + "]: " + i);
            System.out.println("====================================");

            try {
                switch(random.nextInt(3)) {
                case 0:
                    this.rt.getForEntity(DIV_URL_BUG, BigDecimal.class, Collections.emptyMap());
                    break;

                case 1:
                    this.rt.getForEntity(MULT_URL_BUG, BigDecimal.class, Collections.emptyMap());
                    break;

                default:
                    this.rt.getForEntity(ADD_URL_BUG, BigDecimal.class, Collections.emptyMap());
                }
            }
            catch (Exception ex) {
                System.err.println("Error processing:" + ex.toString());
                break;
            }
        }

        System.out.println("Ending thread: " + Thread.currentThread().getName());
    }


}

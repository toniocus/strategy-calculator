package ar.com.exeo.calc;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Testing starting a simple context, no Rest/Web.
 * @author tonioc
 *
 */
public class SimpleSpringAppTest {

    private static MathOperService service;

    @BeforeClass
    public static void start() throws InterruptedException {

        ApplicationContext context =
                new AnnotationConfigApplicationContext("ar.com.exeo.calc");

        service = context.getBean(MathOperService.class);

    }

    @Test
    public void testOk() throws InterruptedException {
        testRandom(service, true);
    }

    @Test
    public void testBuggy() throws InterruptedException {
        testRandom(service, false);
    }

    // ===================================================================
    //   ### - Test Random OK
    // ===================================================================

    public void testRandom(final MathOperService service, final boolean ok) throws InterruptedException {

        Random random = new Random(System.currentTimeMillis());

        Runnable run = null;
        if (ok) {
            run = () -> runRandomOk(random, service);
        }
        else {
            run = () -> runRandomBuggy(random, service);
        }

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

    /**
     * Run random ok.
     *
     * @param random the random
     * @param service the service
     */
    public void runRandomOk(final Random random, final MathOperService service) {

        for (int i = 1; i < 101; i++) {

            System.out.println("====================================");
            System.out.println("TEST [" + Thread.currentThread().getName() + "]: " + i);
            System.out.println("====================================");

            try {
                switch(random.nextInt(3)) {
                case 0:
                    service.okOperation(MathOperEnum.DIVIDE, BigDecimal.TEN, BigDecimal.ONE.add(BigDecimal.ONE));
                    break;

                case 1:
                    service.okOperation(MathOperEnum.DIVIDE, BigDecimal.TEN, BigDecimal.TEN);
                    break;

                default:
                    service.okOperation(MathOperEnum.ADD, BigDecimal.TEN, BigDecimal.ONE);
                }
            }
            catch (Exception ex) {
                System.err.println("Error processing:" + ex.toString());
                break;
            }
        }

        System.out.println("Ending thread: " + Thread.currentThread().getName());
    }

    /**
     * Run random buggy.
     *
     * @param random the random
     * @param service the service
     */
    public void runRandomBuggy(final Random random, final MathOperService service) {

        for (int i = 1; i < 101; i++) {

            System.out.println("====================================");
            System.out.println("TEST [" + Thread.currentThread().getName() + "]: " + i);
            System.out.println("====================================");

            try {
                switch(random.nextInt(3)) {
                case 0:
                    service.buggyOperation(MathOperEnum.DIVIDE, BigDecimal.TEN, BigDecimal.ONE.add(BigDecimal.ONE));
                    break;

                case 1:
                    service.buggyOperation(MathOperEnum.DIVIDE, BigDecimal.TEN, BigDecimal.TEN);
                    break;

                default:
                    service.buggyOperation(MathOperEnum.ADD, BigDecimal.TEN, BigDecimal.ONE);
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

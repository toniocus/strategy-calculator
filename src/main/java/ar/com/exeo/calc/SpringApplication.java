package ar.com.exeo.calc;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ar.com.exeo.calc.service.Stat;

/**
 * Testing starting a simple context, no Rest/Web.
 * @author tonioc
 *
 */
public class SpringApplication {


    private static MathOperService service;

    public static void main(final String[] args) throws InterruptedException {

        ApplicationContext context = new AnnotationConfigApplicationContext("ar.com.exeo.calc");
        service = context.getBean(MathOperService.class);
        new SpringApplication().run();
    }

    // ===================================================================
    //   ### - Run
    // ===================================================================


    public void run() throws InterruptedException {

        /*
         *  Creating the beans once before starting, will it affect the result ? (seems it won't)
         *
        MathOperStrategyFactory factory = context.getBean(MathOperStrategyFactory.class);

        factory.getStrategy(MathOperEnum.ADD);
        factory.getStrategy(MathOperEnum.DIVIDE);
        factory.getStrategy(MathOperEnum.MULTIPLY);
        */

        // Starting buggy 1st. will make OK fail too
        // (actually it won't call a single setter in that case).

        String ok = testRandom(service, true);
        String buggy = testRandom(service, false);


        System.out.println("=================================================");
        System.out.println("Synchronized");
        System.out.println("=================================================");
        System.out.println(ok);

        System.out.println("=================================================");
        System.out.println("Buggy");
        System.out.println("=================================================");
        System.out.println(buggy);

    }


    // ===================================================================
    //   ### - Call randomly one of the implementations.
    // ===================================================================

    private static final int MAX_RUNS = 11;

    public String testRandom(final MathOperService service, final boolean ok) throws InterruptedException {

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

            String stats = Stat.printAll();
            Stat.resetAll();
            return stats;
        }
        catch (Exception ex) {
           System.err.println("Error processing:" + ex.toString());
           throw ex;
        }

    }

    /**
     * Run random ok.
     *
     * @param random the random
     * @param service the service
     */
    public void runRandomOk(final Random random, final MathOperService service) {

        for (int i = 1; i < MAX_RUNS; i++) {

            System.out.println("====================================");
            System.out.println("TEST OK [" + Thread.currentThread().getName() + "]: " + i);
            System.out.println("====================================");

            try {
                switch(random.nextInt(3)) {
                case 0:
                    service.okOperation(MathOperEnum.DIVIDE, BigDecimal.TEN, BigDecimal.ONE.add(BigDecimal.ONE));
                    break;

                case 1:
                    service.okOperation(MathOperEnum.MULTIPLY, BigDecimal.TEN, BigDecimal.TEN);
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

        for (int i = 1; i < MAX_RUNS; i++) {

            System.out.println("====================================");
            System.out.println("TEST BUG [" + Thread.currentThread().getName() + "]: " + i);
            System.out.println("====================================");

            try {
                switch(random.nextInt(3)) {
                case 0:
                    service.buggyOperation(MathOperEnum.DIVIDE, BigDecimal.TEN, BigDecimal.ONE.add(BigDecimal.ONE));
                    break;

                case 1:
                    service.buggyOperation(MathOperEnum.MULTIPLY, BigDecimal.TEN, BigDecimal.TEN);
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

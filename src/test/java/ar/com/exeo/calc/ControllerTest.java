package ar.com.exeo.calc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class ControllerTest {

    public void init() {
        StrategyCalculatorApplication.main(new String[0]);
    }

    public void reset() {
        StrategyCalculatorApplication.shutdown();
    }

    private RestTemplate rt = new RestTemplate();
    private final static String ADD_URL = "http://localhost:8080/add/10/11";
    private final static String DIV_URL = "http://localhost:8080/div/233.0000/4.75";
    private final static String MULT_URL = "http://localhost:8080/mult/31/14";

    @Test
    public void testRestarting() {

        IntStream.range(1, 11).forEach(i -> {


            System.out.println("====================================");
            System.out.println("TEST: " + i);
            System.out.println("====================================");

            init();

            this.rt.getForEntity(ADD_URL, BigDecimal.class, Collections.emptyMap());
            this.rt.getForEntity(DIV_URL, BigDecimal.class, Collections.emptyMap());
            this.rt.getForEntity(MULT_URL, BigDecimal.class, Collections.emptyMap());

            reset();
        });
    }


    @Test
    public void testNoRestarting() {

        init();

        IntStream.range(1, 101).forEach(i -> {

            System.out.println("====================================");
            System.out.println("TEST: " + i);
            System.out.println("====================================");

            this.rt.getForEntity(ADD_URL, BigDecimal.class, Collections.emptyMap());
            this.rt.getForEntity(DIV_URL, BigDecimal.class, Collections.emptyMap());
            this.rt.getForEntity(MULT_URL, BigDecimal.class, Collections.emptyMap());


        });

        reset();
    }

}

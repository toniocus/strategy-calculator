package ar.com.exeo.calc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class DockerClient {


    private RestTemplate rt = new RestTemplate();
    private final static String ADD_URL = "http://localhost:9080/add/10/11";
    private final static String DIV_URL = "http://localhost:9080/div/233.0000/4.75";
    private final static String MULT_URL = "http://localhost:9080/mult/31/14";

    @Test
    public void testRandom() {

        Random random = new Random(System.currentTimeMillis());

        IntStream.range(1, 101).forEach(i -> {

            System.out.println("====================================");
            System.out.println("TEST: " + i);
            System.out.println("====================================");

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
        });
    }

}

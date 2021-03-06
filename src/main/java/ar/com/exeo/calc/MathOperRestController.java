package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
@RestController
public class MathOperRestController {

    @Autowired
    MathOperStrategyFactory factory;

    @Autowired
    ObjectProvider<MathOperProcessor> provider;

    @GetMapping("/ok/{operation}/{x}/{y}")
    public ResponseEntity<BigDecimal> okOperation(
            @PathVariable("operation") final String operation
            , @PathVariable("x") final BigDecimal x
            , @PathVariable("y") final BigDecimal y
            ) {

        try {

            BigDecimal result = this.factory
                    .getStrategy(MathOperEnum.findByKey(operation))
                    .execute(x, y);

            System.out.format("MathOper: %s x:%s y:%s => %s%n", operation, x, y, result);

            return ResponseEntity.ok(result);
        }
        catch (NullPointerException ex) {

            System.out.println(ex.toString());
            int counter = 0;
            for (StackTraceElement traceElement : ex.getStackTrace()) {
                counter++;
                System.out.println("\tat " + traceElement);
                if (counter >= 3) {
                    break;
                }
            }

            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(BigDecimal.ONE.negate());
        }
    }


    @GetMapping("/buggy/{operation}/{x}/{y}")
    public ResponseEntity<BigDecimal> buggyOperation(
            @PathVariable("operation") final String operation
            , @PathVariable("x") final BigDecimal x
            , @PathVariable("y") final BigDecimal y
            ) {

        try {
            BigDecimal result = this.provider
                    .getObject(MathOperEnum.findByKey(operation))
                    .execute(x, y);

            System.out.format("MathOper: %s x:%s y:%s => %s%n", operation, x, y, result);

            return ResponseEntity.ok(result);
        }
        catch (NullPointerException ex) {

            System.out.println(ex.toString());
            int counter = 0;
            for (StackTraceElement traceElement : ex.getStackTrace()) {
                counter++;
                System.out.println("\tat " + traceElement);
                if (counter >= 3) {
                    break;
                }
            }

            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(BigDecimal.ONE.negate());
        }
    }

}

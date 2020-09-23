package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
@Service
public class MathOperService {

    @Autowired
    MathOperStrategyFactory factory;

    @Autowired
    ObjectProvider<MathOperProcessor> provider;

    public BigDecimal okOperation(
            final MathOperEnum operation
            , final BigDecimal x
            , final BigDecimal y
            ) {

        try {

            BigDecimal result = this.factory
                    .getStrategy(operation)
                    .execute(x, y);

            System.out.format("MathOper: %s x:%s y:%s => %s%n", operation, x, y, result);

            return result;
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

            return BigDecimal.ONE.negate();
        }
    }


    public BigDecimal buggyOperation(
            final MathOperEnum operation
            , final BigDecimal x
            , final BigDecimal y
            ) {

        try {
            BigDecimal result = this.provider
                    .getObject(operation)
                    .execute(x, y);

            System.out.format("MathOper: %s x:%s y:%s => %s%n", operation, x, y, result);

            return result;
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

            return BigDecimal.ONE.negate();
        }
    }

}

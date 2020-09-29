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

    /**
     * Ok operation, use {@link MathOperStrategyFactory} to get Strategy implementation.
     *
     * @param operation the operation
     * @param x the x
     * @param y the y
     * @return the big decimal
     */
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


    /**
     * Buggy operation, use ObjectProvider to get the Strategy implementation.
     *
     * @param operation the operation
     * @param x the x
     * @param y the y
     * @return the big decimal
     */
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

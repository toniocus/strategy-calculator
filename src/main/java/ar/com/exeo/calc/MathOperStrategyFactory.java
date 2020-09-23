package ar.com.exeo.calc;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
@Component
public class MathOperStrategyFactory {

    @Autowired
    ObjectProvider<MathOperProcessor> provider;


    public synchronized MathOperProcessor getStrategy(final MathOperEnum operation) {
        return this.provider.getObject(operation);
    }

}

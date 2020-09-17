package ar.com.exeo.calc;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * DOCUMENT .
 * @author angel
 *
 */
@Configuration
public class MathOperSpringConfiguration {

    /**
     * Gets the nokia processor.
     *
     * @param orderItemName String
     * @param action String
     * @param orchItemInfo OrchItemInfo
     * @param orderInfo OrderInfo
     * @return NokiaSelectorProcessor processor
     * @throws InstantiationException exception
     * @throws IllegalAccessException exception
     * @throws InvocationTargetException exception
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException exception
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MathOperProcessor getNokiaProcessor(final MathOperEnum mathOper)
            throws InstantiationException, IllegalAccessException, InvocationTargetException
                , NoSuchMethodException {

        return mathOper.getProcessorClass().getConstructor(String.class, long.class)
                .newInstance("user", 101L);
    }
}

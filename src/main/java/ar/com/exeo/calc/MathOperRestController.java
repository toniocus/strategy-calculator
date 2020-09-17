package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
    ObjectProvider<MathOperProcessor> provider;

    @GetMapping("/{operation}/{x}/{y}")
    public BigDecimal add(
            @PathVariable("operation") final String operation
            , @PathVariable("x") final BigDecimal x
            , @PathVariable("y") final BigDecimal y
            ) {

        BigDecimal result = this.provider.getObject(MathOperEnum.findByKey(operation)).execute(x, y);

        System.out.format("MathOper: %s x:%s y:%s => %s%n", operation, x, y, result);

        return result;
    }
}

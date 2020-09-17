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

    @GetMapping("/add/{x}/{y}")
    public BigDecimal add(@PathVariable("x") final BigDecimal x, @PathVariable("y") final BigDecimal y) {
        return this.provider.getObject(MathOperEnum.findByKey("add")).execute(x, y);
    }

    @GetMapping("/mult/{x}/{y}")
    public BigDecimal multiply(@PathVariable("x") final BigDecimal x, @PathVariable("y") final BigDecimal y) {
        return this.provider.getObject(MathOperEnum.findByKey("mult")).execute(x, y);
    }

    @GetMapping("/div/{x}/{y}")
    public BigDecimal divide(@PathVariable("x") final BigDecimal x, @PathVariable("y") final BigDecimal y) {
        return this.provider.getObject(MathOperEnum.findByKey("div")).execute(x, y);
    }

}

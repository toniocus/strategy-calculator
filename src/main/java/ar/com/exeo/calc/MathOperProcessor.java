package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.exeo.calc.service.OtherService;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public abstract class MathOperProcessor {

    private OtherService other;

    @Autowired
    public final void setOther(final OtherService other) {
        this.other = other;
    }

    public abstract BigDecimal execute(BigDecimal n1, BigDecimal n2);
}

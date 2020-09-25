package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.exeo.calc.service.OtherService;
import ar.com.exeo.calc.service.Stat;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public abstract class MathOperProcessor {

    public static final Stat stat = new Stat(MathOperProcessor.class.getSimpleName());

    static {
        stat.other = 0;
    }

    private OtherService other;

    public MathOperProcessor() {
        stat.created++;
    }

    @Autowired
    public final void setOther(final OtherService other) {
        this.other = other;
        stat.other++;
    }

    public abstract BigDecimal execute(BigDecimal n1, BigDecimal n2);
}

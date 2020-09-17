package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.exeo.calc.service.MathRepository;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class MathOperMultiply implements MathOperProcessor {

    private static final Logger log = LoggerFactory.getLogger(MathOperMultiply.class);

    private MathRepository repo;

    @Autowired
    public void setRepo(final MathRepository repo) {
        log.info("Setting repo: {}", repo);
        this.repo = repo;
    }


    public MathOperMultiply(final String user, final long id) {
    }

    @Override
    public BigDecimal execute(final BigDecimal n1, final BigDecimal n2) {

        this.repo.registerOperation();

        return n1.multiply(n2);
    }

}

package ar.com.exeo.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.exeo.calc.service.MathRepository;
import ar.com.exeo.calc.service.MathStatistics;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class MathOperDivide extends MathOperProcessor {

    private static final Logger log = LoggerFactory.getLogger(MathOperDivide.class);

    private MathRepository repo;
    private MathStatistics stat;

    @Autowired
    public void setRepo(final MathRepository repo) {
        log.info("Setting repo: {}", repo);
        this.repo = repo;
    }

    @Autowired
    public void setStat(final MathStatistics stat) {
        log.info("Setting stat: {}", stat);
        this.stat = stat;
    }

    public MathOperDivide(final String user, final long id) {
    }

    @Override
    public BigDecimal execute(final BigDecimal n1, final BigDecimal n2) {

        this.repo.registerOperation();
        this.stat.addOperation();

        return n1.divide(n2, RoundingMode.HALF_EVEN);
    }

}

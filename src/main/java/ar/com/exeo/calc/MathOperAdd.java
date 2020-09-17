package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.exeo.calc.service.InnerService;
import ar.com.exeo.calc.service.MathRepository;
import ar.com.exeo.calc.service.MathStatistics;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class MathOperAdd extends MathOperProcessor {

    private static final Logger log = LoggerFactory.getLogger(MathOperAdd.class);

    private MathRepository repo;
    private MathStatistics stat;
    private InnerService inner;

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

    @Autowired
    public void setInner(final InnerService inner) {
        log.info("Setting inner: {}", inner);
        this.inner = inner;
    }

    public MathOperAdd(final String user, final long id) {
    }

    @Override
    public BigDecimal execute(final BigDecimal n1, final BigDecimal n2) {

        this.inner.doNothing();
        this.repo.registerOperation();
        this.stat.addOperation();

        return n1.add(n2);
    }


}

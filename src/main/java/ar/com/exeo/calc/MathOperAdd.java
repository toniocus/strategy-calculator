package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.exeo.calc.service.InnerService;
import ar.com.exeo.calc.service.MathRepository;
import ar.com.exeo.calc.service.MathStatistics;
import ar.com.exeo.calc.service.Stat;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class MathOperAdd extends MathOperProcessor {

    public static final Stat stat = new Stat(MathOperAdd.class.getSimpleName());

    static {
        stat.repo = stat.stat = stat.inner = 0;
    }


    private static final Logger log = LoggerFactory.getLogger(MathOperAdd.class);

    private MathRepository repo;
    private MathStatistics stats;
    private InnerService inner;

    @Autowired
    public void setRepo(final MathRepository repo) {
        log.info("Setting repo: {}", repo);
        this.repo = repo;
        stat.repo++;
    }

    @Autowired
    public void setStat(final MathStatistics stats) {
        log.info("Setting stat: {}", stats);
        this.stats = stats;
        stat.stat++;
    }

    @Autowired
    public void setInner(final InnerService inner) {
        log.info("Setting inner: {}", inner);
        this.inner = inner;
        stat.inner++;
    }

    public MathOperAdd(final String user, final long id) {
        stat.created++;
    }

    @Override
    public BigDecimal execute(final BigDecimal n1, final BigDecimal n2) {

        this.inner.doNothing();
        this.repo.registerOperation();
        this.stats.addOperation();

        return n1.add(n2);
    }


}

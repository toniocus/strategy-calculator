package ar.com.exeo.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.exeo.calc.service.CommonService;
import ar.com.exeo.calc.service.MathRepository;
import ar.com.exeo.calc.service.MathStatistics;
import ar.com.exeo.calc.service.Stat;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class MathOperDivide extends MathOperProcessor {

    public static final Stat stat = new Stat(MathOperDivide.class.getSimpleName());

    static {
        stat.repo = stat.stat = stat.common = 0;
    }

    private static final Logger log = LoggerFactory.getLogger(MathOperDivide.class);

    private MathRepository repo;
    private MathStatistics stats;
    private CommonService common;

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
    public void setCommon(final CommonService common) {
        log.info("Setting common: {}", this.stats);
        this.common = common;
        stat.common++;
    }

    public MathOperDivide(final String user, final long id) {
        stat.created++;
    }

    @Override
    public BigDecimal execute(final BigDecimal n1, final BigDecimal n2) {

        this.repo.registerOperation();
        this.stats.addOperation();
        this.common.doNothing();

        return n1.divide(n2, RoundingMode.HALF_EVEN);
    }

}

package ar.com.exeo.calc;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.exeo.calc.service.MathRepository;
import ar.com.exeo.calc.service.MathStatistics;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class MathOperAdd implements MathOperProcessor {

    private MathRepository repo;
    private MathStatistics stat;

    @Autowired
    public void setRepo(final MathRepository repo) {
        this.repo = repo;
    }

    @Autowired
    public void setStat(final MathStatistics stat) {
        this.stat = stat;
    }

    public MathOperAdd(final String user, final long id) {
    }

    @Override
    public BigDecimal execute(final BigDecimal n1, final BigDecimal n2) {

        this.repo.registerOperation();
        this.stat.addOperation();

        return n1.add(n2);
    }


}

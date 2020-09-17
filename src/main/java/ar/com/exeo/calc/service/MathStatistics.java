package ar.com.exeo.calc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
@Service
public class MathStatistics {

    private InnerService service;

    @Autowired
    public MathStatistics(final InnerService service) {
        this.service = service;
    }


    public void addOperation() {
        this.service.doNothing();
        System.out.println("Operation Added");
    }

}

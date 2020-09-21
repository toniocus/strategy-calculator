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
    private CommonService common;

    @Autowired
    public MathStatistics(final InnerService service, final CommonService common) {
        this.service = service;
        this.common = common;
    }


    public void addOperation() {
        this.service.doNothing();
        this.common.doNothing();
        System.out.println("Operation Added");
    }

}

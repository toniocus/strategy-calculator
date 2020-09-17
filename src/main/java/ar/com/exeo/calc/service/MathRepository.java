package ar.com.exeo.calc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
@Repository
public class MathRepository {

    private InnerService service;

    @Autowired
    public MathRepository(final InnerService service) {
        this.service = service;
    }


    public void registerOperation() {
        this.service.doNothing();
        System.out.println("Operation Registered");
    }

}

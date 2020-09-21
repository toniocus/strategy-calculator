package ar.com.exeo.calc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
@Service
public class CommonService {

    private static final Logger log = LoggerFactory.getLogger(CommonService.class);

    public void doNothing() {
        log.info("CommonService.doNothing()");
    }
}

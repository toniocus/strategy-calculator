package ar.com.exeo.calc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StrategyCalculatorApplication {

    private static SpringApplicationBuilder builder;

    public static void main(final String[] args) {

        builder = new SpringApplicationBuilder(StrategyCalculatorApplication.class);
        builder.run(args);

    }

    public static void shutdown() {
        SpringApplication.exit(builder.context(), () -> 0);
    }
}

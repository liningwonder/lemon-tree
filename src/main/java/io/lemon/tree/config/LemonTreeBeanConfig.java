package io.lemon.tree.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class LemonTreeBeanConfig {

    @Bean
    public MetricRegistry metrics() {
        return new MetricRegistry();
    }

    @Bean
    public ConsoleReporter consoleReporter(MetricRegistry metricRegistry) {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(30, TimeUnit.SECONDS);
        return reporter;
    }

/*    @Bean
    public Slf4jReporter slf4jReporter() {
        Slf4jReporter reporter = Slf4jReporter.forRegistry(metrics())
                .outputTo(LoggerFactory.getLogger("io.lemon.tree"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10, TimeUnit.MINUTES);
        return reporter;
    }*/

}

package io.lemon.tree.actuator;

import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "metrics")
public class MetricsEndpoint {

    @Autowired
    private MetricRegistry registry;

    @ReadOperation
    public Object metrics() {
        return registry.getTimers();
    }

}

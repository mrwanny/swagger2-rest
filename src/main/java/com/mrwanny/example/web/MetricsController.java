package com.mrwanny.example.web;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.beans.ConstructorProperties;

@Controller
@RequestMapping("/metrics")
public class MetricsController extends ServletDelegatingController<MetricsServlet> {
    private final MetricRegistry metricRegistry;

    @Autowired
    @ConstructorProperties("metricRegistry")
    public MetricsController(MetricRegistry metricRegistry) {
        super(new MetricsServlet(metricRegistry), "metrics");
        this.metricRegistry = metricRegistry;
    }
}

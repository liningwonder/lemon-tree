package io.lemon.tree.interceptor;

import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LemonTreeInterceptor implements HandlerInterceptor {

    private static final String REQUEST_ID = "REQUEST_ID";

    //private Map<String, Meter> metricMap = new HashMap<>();

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        MetricRegistry metrics = getAppClass(MetricRegistry.class, request);
        String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        /*String path = request.getServletPath();
        if (metricMap.containsKey(path)) {
            metricMap.get(path).mark();
        } else {
            Meter meter = metrics.meter(path);
            meter.mark();
            metricMap.put(path, meter);
        }*/
/*        if (histogramMap.containsKey(path)) {
            metricMap.get(path).mark();
        } else {
            Histogram histogram = metrics.histogram(path);
            histogram.update(request.getContentLengthLong());
            histogramMap.put(path, histogram);
        }*/
/*        if (timerMap.containsKey(path)) {
            timerMap.get(path).mark();
        } else {
            Histogram histogram = metrics.histogram(path);
            histogram.update(request.getContentLengthLong());
            histogramMap.put(path, histogram);
        }*/
        return true;
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
    }


    private <T> T getAppClass(Class<T> clazz, HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }

}

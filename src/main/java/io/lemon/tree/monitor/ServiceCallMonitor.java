package io.lemon.tree.monitor;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lemon.tree.logger.ServiceCallMonitorLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class ServiceCallMonitor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MetricRegistry metricRegistry;

    private Map<String, Timer> timerMap = new HashMap<>();

    @Before("execution(* io.lemon.tree.service.impl.*.*(..))")
    public void before(JoinPoint joinPoint) {
        //类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //参数
        Object[] args = joinPoint.getArgs();
        //方法签名
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        ServiceCallMonitorLogger.getLogger().info(" [Service Call Monitor] start call method {}.{}: ", className, methodName);
        try {
            ServiceCallMonitorLogger.getLogger().info("[Service Call Monitor] request json :  {}", objectMapper.writeValueAsString(args));
        } catch (JsonProcessingException e) {
            ServiceCallMonitorLogger.getLogger().info("[Service Call Monitor] parse object args as json string error");
        }
    }


    @AfterReturning(pointcut = "execution(* io.lemon.tree.service.impl.*.*(..))", returning="returnValue")
    public void after(JoinPoint joinPoint, Object returnValue) {
        //类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //参数
        Object[] args = joinPoint.getArgs();
        //方法签名
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        ServiceCallMonitorLogger.getLogger().info("[Service Call Monitor] End call method {}.{}: ", className, methodName);
        try {
            ServiceCallMonitorLogger.getLogger().info("[Service Call Monitor] response json :  {}", objectMapper.writeValueAsString(returnValue));
        } catch (JsonProcessingException e) {
            ServiceCallMonitorLogger.getLogger().info("[Service Call Monitor] parse object args as json string error");
        }
    }

    @Pointcut("execution(*  io.lemon.tree.service.impl.*.*(..)) ")
    public void servicePointCut() {
    }

    @Around("servicePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //参数
        Object[] args = joinPoint.getArgs();
        //方法签名
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        long startTime = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        ServiceCallMonitorLogger.getLogger().info("[Service Call Monitor] {}.{}call time {} ms", className, methodName, System.currentTimeMillis() - startTime);
        return obj;
    }

    @Around("execution(*  io.lemon.tree.web.*.*(..)) ")
    public Object timer(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String className = joinPoint.getTarget().getClass().getName();
/*        Annotation[] annotations = joinPoint.getTarget().getClass().getAnnotations();
        for (Annotation annotation : annotations) {
                *//*String name = annotation.annotationType().getSimpleName();
                if (GetMapping.class.getSimpleName().equals(name)
                        || PostMapping.class.getSimpleName().equals(name)
                        || PutMapping.class.getSimpleName().equals(name)
                        || DeleteMapping.class.getSimpleName().equals(name)
                || ) {

                }*//*
        }*/
        Signature signature = joinPoint.getSignature();
        String key = request.getServletPath();
        Timer timer = null;
        if (timerMap.containsKey(key)) {
            timer = timerMap.get(key);
        } else {
            timer = metricRegistry.timer(key);
            timerMap.put(key, timer);
        }
        Timer.Context context = timer.time();
        Object obj = joinPoint.proceed();
        context.stop();
        return obj;
    }


}

package de.ait_tr.g_40_1_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {
    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    //   pointcut
    @Pointcut("execution(* de.ait_tr.g_40_1_shop.service.ProductServiceImpl.save(..))")
    public void saveProduct(){}

    // advice
    @Before("saveProduct()")
    public void beforeSavingProduct(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        logger.info("Method save of the class ProductServiceImpl called {}",args[0]);
    }

}

package com.myhome.aop;

import org.aspectj.lang.ProceedingJoinPoint;
/**
 * http://www.jtips.info/?title=Spring/Annotations
 * @author glevi
 *
 */
public class SysoutInterceptor {
    public Object doLog(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getTarget().getClass().getSimpleName() + "." + point.getSignature().getName();
        System.out.println("==> Début méthode : " + methodName);
        Object obj = point.proceed();
        System.out.println("<== Fin méthode : " + methodName);

        return obj;
    }
}

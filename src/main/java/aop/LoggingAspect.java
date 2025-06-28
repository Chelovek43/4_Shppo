package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    /*
     * Pointcut выражение определяет, к каким методам применяется advice.
     *
     * execution(* domain.products.ProductFactory.createProduct(..)) означает:
     * * - любой возвращаемый тип
     * domain.products.ProductFactory - класс, содержащий метод
     * createProduct - имя метода
     * (..) - любые параметры
     */
    @Before("execution(* domain.products.ProductFactory.createProduct(..))")
    public void logProductCreation(JoinPoint jp) {
        Object[] args = jp.getArgs();
        System.out.printf(
                "[AOP] Create prod: %s (%.1f ccal, P:%.1fg, F:%.1fg, C:%.1fg)\n",
                args[0], args[1], args[2], args[3], args[4]
        );
    }
}
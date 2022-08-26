package Course.aspect;


import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Aspect
@Component
public class CommonLogger {

    @Pointcut("within(Course.service..*)")
    public void services(){
    }

    @Pointcut("within(Course.aspect.GlobalExceptionHandler)")
    public void exceptions(){
    }

    @Before("services()")
    public void beforeExecuting(JoinPoint joinPoint){
        log.info("*****************************************");
        log.info("Before Executing",
                kv("methodName",joinPoint.getSignature().getName()),
                kv("parameters",Arrays.toString(joinPoint.getArgs()))
        );
    }

    @AfterThrowing(value = "services()",throwing="ex")
    public void afterThrowingError(JoinPoint joinPoint,Exception ex){
        log.info("ERROR IN SERVICE");
        ex.printStackTrace();
    }



//    @AfterReturning(value = "exceptions()", returning = "response")
//    public void afterReturningError(JoinPoint joinPoint, Object response){
//        log.info("Response",kv("response",response));
//        log.info(response.toString());
//    }

    @After("services()")
    public void afterExecuting(JoinPoint joinPoint){
        log.info("After Executing",kv("methodName",joinPoint.getSignature().getName()));
        log.info("*****************************************");
    }
}

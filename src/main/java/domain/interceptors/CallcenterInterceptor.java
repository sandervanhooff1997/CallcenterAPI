package domain.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

public class CallcenterInterceptor {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        logger.info("Logging BEFORE calling method :" + context.getMethod().getName());

        Object result = context.proceed();

        logger.info("Logging AFTER calling method :" + context.getMethod().getName());

        return result;
    }
}

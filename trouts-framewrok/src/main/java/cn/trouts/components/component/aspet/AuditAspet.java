package cn.trouts.components.component.aspet;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import cn.trouts.entitys.framework.TroutsAudit;
import cn.trouts.framework.context.TroutsContext;
import cn.trouts.framework.utils.TroutsLogUtils;

@Aspect
@Component
public class AuditAspet {
	private static final Logger LOGGER = TroutsLogUtils
			.getLogger(AuditAspet.class);

	@Pointcut("execution(* cn.trouts.components.services..*.*(..))")
	public void pointCut() {
	}

//	@Before("pointCut()")
//	public void doBefore(JoinPoint joinPoint) {
//		System.out.println("AOP Before Advice...");
//	}
//
//	@After("pointCut()")
//	public void doAfter(JoinPoint joinPoint) {
//		System.out.println("AOP After Advice...");
//	}
//
//	@AfterReturning(pointcut = "pointCut()", returning = "returnVal")
//	public void afterReturn(JoinPoint joinPoint, Object returnVal) {
//		System.out.println("AOP AfterReturning Advice:" + returnVal);
//	}
//
//	@AfterThrowing(pointcut = "pointCut()", throwing = "error")
//	public void afterThrowing(JoinPoint joinPoint, Throwable error) {
//		System.out.println("AOP AfterThrowing Advice..." + error);
//		System.out.println("AfterThrowing...");
//	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint pjp) {
		TroutsAudit log = new TroutsAudit();
        try {
            log.setOperator(TroutsContext.getCurrentUser());
            String mname = pjp.getSignature().getName();
            log.setOperName(mname);
            
            //方法参数,本例中是User user
            Object[] args = pjp.getArgs();
            log.setOperParams(Arrays.toString(args));
            
            //执行目标方法，返回的是目标方法的返回值，本例中 void
            Object obj = pjp.proceed();
            if(obj != null){
                log.setResultMsg(obj.toString());
            }else{
                log.setResultMsg(null);
            }
            
            log.setOperResult("success");
            log.setOperTime(new Date());
            System.out.println(log);
            return obj;
        } catch (Throwable e) {
            log.setOperResult("failure");
            log.setResultMsg(e.getMessage());
        } finally{
//            logService.saveLog(log);
        	TroutsLogUtils.printLog(log.toString());
        }
        System.out.println(log);
        return null;
    }
	

}

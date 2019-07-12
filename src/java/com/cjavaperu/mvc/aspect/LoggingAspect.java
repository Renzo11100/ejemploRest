package com.cjavaperu.mvc.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private Long tx;
	
	@Around("execution(* com.cjavaperu.mvc.controller.*Controller*.init(..)) || " +
			"execution(* com.cjavaperu.mvc.controller.*Controller*.registrar(..)) || " +
			"execution(* com.cjavaperu.mvc.controller.*Controller*.actualizar(..)) || " +
			"execution(* com.cjavaperu.mvc.controller.*Controller*.cargar(..)) || " +
			"execution(* com.cjavaperu.mvc.controller.*Controller*.eliminar(..)) ")
	public Object logTiempoTranscurrido(ProceedingJoinPoint joinPoint) throws Throwable {

		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		Object result =  null;
		Long currTime = System.currentTimeMillis();
		tx = System.currentTimeMillis();
		logger.info("tx["+tx+"] --- INICIO DEL REQUEST ---");
		String metodo = "tx["+tx+"] - " + joinPoint.getSignature().getName();
		logger.info(metodo + "()");
		if(!joinPoint.getSignature().getName().equals("init"))
			if(joinPoint.getArgs().length > 0)
				logger.info(metodo + "() INPUT:" + Arrays.toString(joinPoint.getArgs()));
		try {
			result = joinPoint.proceed();
        } catch (Throwable e) {
        	logger.error(e.getMessage());
        }
		logger.info(metodo + "(): tiempo transcurrido " + (System.currentTimeMillis() - currTime) + " ms.");
		logger.info("tx["+tx+"] --- FIN DEL REQUEST ---");
		return result;


	}

	@Around("execution(* com.cjavaperu.mvc.service.*Service*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		Object result =  null;
		Long currTime = System.currentTimeMillis();
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		String metodo = "tx[" + tx + "] - " + joinPoint.getSignature().getName();
		logger.info(metodo + "()");
		if(joinPoint.getArgs().length > 0)
			logger.info(metodo + "() INPUT:" + Arrays.toString(joinPoint.getArgs()));
		try {
			result = joinPoint.proceed();
        } catch (Throwable e) {
        	logger.error(e.getMessage());
        }
		logger.info(metodo + "(): tiempo transcurrido " + (System.currentTimeMillis() - currTime) + " ms.");
		return result;

	}

}

package com.bjtu.bookstore.utils.logAspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bjtu.bookstore.utils.resultUtils.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: bookstore-back-end
 * @description: log process before controller
 * @author: CodingLiOOT
 * @create: 2021-07-09 10:04
 * @version: 1.0
 **/
@Component
@Slf4j
@Aspect
public class ControllerAspect {

    @Pointcut("execution(* com.bjtu.bookstore.controller..*.*(..))")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint jpt) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        MethodSignature ms = (MethodSignature) jpt.getSignature();
        //获取请求参数类型
        String[] parameterNames = ms.getParameterNames();
        //获取请求参数值
        Object[] parameterValues = jpt.getArgs();
        StringBuilder sb = new StringBuilder();
        //组合请求参数，进行日志打印
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals("bindingResult")) {
                    break;
                }
                if ((parameterValues[i] instanceof HttpServletRequest) || (parameterValues[i] instanceof HttpServletResponse)) {
                    sb.
                            append("[").
                            append(parameterNames[i]).append("=").append(parameterValues[i])
                            .append("]");
                } else {
                    sb.
                            append("[").
                            append(parameterNames[i]).append("=")
                            .append(JSON.toJSONString(parameterValues[i], SerializerFeature.WriteDateUseDateFormat))
                            .append("]");
                }
            }
        }
        Object result = jpt.proceed();
        log.info("URL : " + request.getRequestURL().toString()
                + ", HTTP_METHOD : " + request.getMethod()
                + ", IP : " + request.getRemoteAddr()
                + ", CLASS_METHOD : " + jpt.getSignature().getDeclaringTypeName()
                + "." + jpt.getSignature().getName()
                + ", ARGS-JSON : " + sb.toString()
                + ", RESPONSE_BODY : " + JSON.toJSONString(Result.success(result)));
        return result;
    }

}

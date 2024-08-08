//  개별적으로 log.info() 를 호출하지 않아도 자동적으로 양식에 맞게 로그를 남겨주는 service

package com.beyond.board.log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//Aspect : aop 코드임을 명시

@Aspect
@Component
@Slf4j
public class AopLogService {

    //  aop 의 대상(공통화의 대상)이 되는 controller, service 등의 위치를 명시
    //  모든 컨트롤 어노테이션을 대상으로 함.
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controllerPointCut() {

    }

    //  방법1. around 를 통해 controller 와 걸쳐져 있는 사용 패턴
    @Around("controllerPointCut()")
    //  JointPoint 는 사용자가 실행하려고 하는 코드를 의미하고, 위에서 정의한 PointCut 을 의미한다.
    public Object controllerLogger(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("aop start");
        log.info("Method 명 : " + proceedingJoinPoint.getSignature().getName());

        //  직접 HttpServletRequest 객체를 꺼내는 방법
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();
        log.info("HTTP Method " + request.getMethod());
        Map<String, String[]> parameterMap = request.getParameterMap();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.valueToTree(parameterMap);
        log.info("user inputs : " + objectNode);

        Object result;
        try {
            //  사용자가 실행하고자 하는 코드 실행부
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        log.info("aop end");
        return result;
    }


//    // 사용 방법 2. Before, After 어노테이션 사용
//    @Before("controllerPointCut()")
//    public void beforeController(JoinPoint joinPoint) {
//        log.info("aop start");
//        log.info("Method 명 : " + joinPoint.getSignature().getName());
//
//        //  직접 HttpServletRequest 객체를 꺼내는 방법
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//        HttpServletRequest request = attributes.getRequest();
//        log.info("HTTP Method " + request.getMethod());
//
//    }
//
//    @After("controllerPointCut()")
//    public void afterController() {
//        log.info("aop end");
//    }
}

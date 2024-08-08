//  @Slf4j 를 사용하지 않고 직접 Logger 를 생성하여 개별적으로 로거를 통해 로그를 남길 수 있다.
package com.beyond.board.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//  기존의 로그 방식 System.out.println
//  1. 성능이 좋지 않음
//  2. 로그 분류 작업 불가
//  System.out.println("println 로그입니다.");

//  로그를 남기는 두가지 방법
@Slf4j
@RestController
public class LogController {
    //  방법 1. 로거 직접선언방법
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("log/test1")
    public String logTest1() {
        //  Logger 직접선언
        //  logger 직접 선언
        logger.debug("debug 로그입니다.");
        logger.info("info 로그입니다.");
        logger.error("error 로그입니다.");
        return "OK";
    }

    //  방법 2. slf4j 어노테이션
    @GetMapping("log/test2")
    public String logTest2() {
        //  @Slf4j 를 사용하면 다음 코드처럼 로거를 사용하지 않고 자동적으로 생성된 Logger log 를 사용할 수 있다.
        log.info("slfj4를 통한 로그입니다.");
        log.error("slfj4를 통한 로그입니다.");
        return "OK";
    }
}

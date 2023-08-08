package com.security.study.特定知识点测试和总结.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 代码执行工具
 */
@Component
public class RunUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(RunUtils.class);
    
    
    /**
     * 计时器运行一段代码,有异常就抛出.
     *
     * @param runnable 运行代码
     * @param tip      提示
     */
    public static void runWithStopWatch(Runnable runnable, String tip) {
        StopWatch stopWatch = new StopWatch(tip);
        try {
            stopWatch.start("task1");
            runnable.run();
        } finally {
            stopWatch.stop();
            logger.info(" 【{}】 代码块运行时长 {} 秒", stopWatch.getId(), stopWatch.getTotalTimeSeconds());
        }
    }
    
    
    /**
     * 计时器运行一段代码,有异常处理掉异常.
     *
     * @param runnable 运行代码
     * @param tip      提示
     */
    public static void runWithStopWatchWithoutException(Runnable runnable, String tip) {
        StopWatch stopWatch = new StopWatch(tip);
        try {
            stopWatch.start("task1");
            runnable.run();
        } catch (Exception e) {
            logger.error(" {} 代码块执行异常: ", tip, e);
        } finally {
            stopWatch.stop();
            logger.info(" 【{}】 代码块运行时长 {} 秒", stopWatch.getId(), stopWatch.getTotalTimeSeconds());
        }
    }
    
    
    
    /**
     * 异步并计时器运行一段代码
     * @param runnable  运行代码
     * @param tip  提示
     */
    @Async
    public void runByAsync(Runnable runnable,String tip){
        runWithStopWatch(runnable, tip);
    }
    
}

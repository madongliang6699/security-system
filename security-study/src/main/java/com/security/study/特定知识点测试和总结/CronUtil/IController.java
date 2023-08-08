package com.security.study.特定知识点测试和总结.CronUtil;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.Scheduler;
import cn.hutool.cron.TaskTable;
import cn.hutool.cron.task.Task;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IController {
    
    /**
     * 要通过jvm检测工具，查看相关的定时任务，在remove之后，还占不占内存。
     *
     * 另外，怎么防止其他人调用stop命令，一下子给关闭了。
     */
    
    @GetMapping("add")
    public void add(String aa){
        CronUtil.schedule(aa, "0/5 * * * * ?", new Task() {
            @Override
            public void execute() {
                Scheduler scheduler = CronUtil.getScheduler();
                // scheduler.getTask()
                System.out.println("执行业务逻辑--->"+aa+aa);
            }
        });
        CronUtil.setMatchSecond(true);
        Scheduler scheduler = CronUtil.getScheduler();
        if(!scheduler.isStarted()){
            CronUtil.start();
        }
    }
    
    @GetMapping("add1")
    public void add1(String aa, String cron){
        CronUtil.schedule(aa, cron, new Task() {
            @Override
            public void execute() {
                Scheduler scheduler = CronUtil.getScheduler();
                // scheduler.getTask()
                System.out.println("执行业务逻辑--->"+aa+aa);
                CronUtil.remove(aa);
            }
        });
        CronUtil.setMatchSecond(true);
        Scheduler scheduler = CronUtil.getScheduler();
        if(!scheduler.isStarted()){
            // CronUtil.start(true); //如果想让执行的作业同定时任务线程同时结束，可以将定时任务设为守护线程，需要注意的是，此模式下会在调用stop时立即结束所有作业线程，请确保你的作业可以被中断.
            CronUtil.start();
        }
    }
    
    @GetMapping("get")
    public void get(String aa){
        Scheduler scheduler = CronUtil.getScheduler();
        Task task = scheduler.getTask(aa);
        System.out.println("task:"+ JSON.toJSONString(task));
        TaskTable taskTable = scheduler.getTaskTable();
        Task task1 = taskTable.getTask(aa);
        System.out.println("task1:"+JSON.toJSONString(task1));
        System.out.println("taskTable:"+JSON.toJSONString(taskTable));
        System.out.println("scheduler:"+JSON.toJSONString(scheduler));
    }
    
    @GetMapping("stop")
    public void stop(String aa){
        Scheduler scheduler = CronUtil.getScheduler();
        CronUtil.stop();
    }
    
    @GetMapping("remove")
    public void remove(String aa){
        Scheduler scheduler = CronUtil.getScheduler();
        CronUtil.remove(aa);
    }
    
    
}

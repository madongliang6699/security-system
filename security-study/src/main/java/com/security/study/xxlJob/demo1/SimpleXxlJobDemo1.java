package com.security.study.xxlJob.demo1;

import com.alibaba.fastjson.JSONObject;
import com.security.study.xxlJob.config.XxlJobConfig;
import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.XxlJobRemotingUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SimpleXxlJobDemo1 {

    /**
     * 实测，xxl job 不需要停止任务直接修改cron表达式保存，几秒后就能立即生效。
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        String jobParam = XxlJobHelper.getJobParam();

        JSONObject jsonObject = JSONObject.parseObject(jobParam);
        System.out.println("name:" + jsonObject.get("name"));
        System.out.println("code:" + jsonObject.get("code"));

        System.out.println("执行定时任务,执行时间:" + new Date());

        XxlJobHelper.handleResult(1212, "我执行完了---");
        XxlJobHelper.handleFail("故意失败了，，，，，");
        long jobId = XxlJobHelper.getJobId();
        System.out.println("jobId:"+jobId);

//        XxlJobContext.getXxlJobContext().
//        XxlJobExecutor.registJobHandler().

//        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.

    }


}

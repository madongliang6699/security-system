package com.security.multisupport.appswitch;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import com.security.multisupport.utils.RegularUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class AppSwitchCondition implements RequestCondition<AppSwitchCondition> {
//    private static final Logger logger = LoggerFactory.getLogger(AppSwitchCondition.class);
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d{1,5}$");
    public static final String APPID_PROP_NAME = "appid";
    public static final int DEFAULT_APPID = 0;
    public static final String DEFAULT_APPID_STR = "0";
    private int appId;

    public AppSwitchCondition(int appId) {
        this.appId = appId;
    }

    public AppSwitchCondition combine(AppSwitchCondition other) {
        return new AppSwitchCondition(0);
    }

    public AppSwitchCondition getMatchingCondition(HttpServletRequest request) {
        Integer version = matchingAppid(request);
//        if (logger.isDebugEnabled()) {
//            logger.debug("Url：[ {} ] thisAppid [ {} ] AppID Value:[ {} ]", new Object[]{request.getRequestURL().toString(), this.appId, version});
//        }

        if (version == null) {
            return null;
        } else {
            return version == this.appId ? this : null;
        }
    }

    public int compareTo(AppSwitchCondition other, HttpServletRequest request) {
        return other.getAppId() == 0 ? 0 : other.getAppId() - this.appId;
    }

    public int getAppId() {
        return this.appId;
    }

    public static boolean isAppidRange(String appid) {
        return StringUtils.isEmpty(appid) ? false : RegularUtil.regMatched(NUMBER_PATTERN, appid);
    }

    public static Integer matchingAppid(HttpServletRequest request) {
        String tmpAppID = null;
        String tmpHeadAppID = request.getHeader("appid");
        String tmpQueryAppID = request.getParameter("appid");
        String tmpUrl = request.getRequestURL().toString();
        tmpHeadAppID = isAppidRange(tmpHeadAppID) ? tmpHeadAppID : null;
        tmpQueryAppID = isAppidRange(tmpQueryAppID) ? tmpQueryAppID : null;
        tmpAppID = !StringUtils.isEmpty(tmpHeadAppID) ? tmpHeadAppID : tmpQueryAppID;
        tmpAppID = !StringUtils.isEmpty(tmpAppID) ? tmpAppID : "0";
//        if (logger.isDebugEnabled()) {
//            logger.debug("Url：[ {} ] Header Value:[ {} ] Query Value:[ {} ] AppID Value:[ {} ]", new Object[]{tmpUrl, request.getHeader("appid"), request.getParameter("appid"), tmpAppID});
//        }

        return RegularUtil.regMatched(NUMBER_PATTERN, tmpAppID) ? Integer.valueOf(tmpAppID) : null;
    }
}

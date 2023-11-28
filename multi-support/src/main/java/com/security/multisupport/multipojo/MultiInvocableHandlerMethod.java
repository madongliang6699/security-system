//package com.security.multisupport.multipojo;
//
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.net.URLDecoder;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.ServletInputStream;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.security.multisupport.appswitch.AppSwitchCondition;
//import com.security.multisupport.db.RespInfo;
//import com.security.multisupport.db.ServiceErrorCodeEnum;
//import com.security.multisupport.db.TokenInfo;
//import com.security.multisupport.multipojo.aop.MultiHandle;
//import com.security.multisupport.utils.JsonUtil;
//import com.security.multisupport.utils.RespInfoUtil;
//import com.security.multisupport.utils.StringUtil;
//import com.security.multisupport.utils.TokenUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.DefaultParameterNameDiscoverer;
//import org.springframework.core.MethodParameter;
//import org.springframework.core.ParameterNameDiscoverer;
//import org.springframework.lang.Nullable;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
//
//public class MultiInvocableHandlerMethod extends ServletInvocableHandlerMethod {
//    private static final Logger logger = LoggerFactory.getLogger(MultiInvocableHandlerMethod.class);
//    private static final Object[] EMPTY_ARGS = new Object[0];
//    private static final String TOKEN_NAME = "token";
//    private static final String DEPLOY_TOKEN_NAME = "deploy-token";
//    private static final String TENANT_ID = "tenantId";
//    private static final String TENANT_NAME = "tenantName";
//    private static final String APPID_NAME = "appId";
//    private static final String BASE_TYPE = "base";
//    private static final String POJO_TYPE = "pojo";
//    private static final String LIST_TYPE = "list";
//    private static final String MAP_TYPE = "map";
//    private static final List<Class<?>> SYS_PARAM_TYPE = new ArrayList<Class<?>>(5) {
//        {
//            this.add(HttpServletRequest.class);
//            this.add(HttpServletResponse.class);
//            this.add(ServletRequest.class);
//            this.add(ServletResponse.class);
//            this.add(HttpSession.class);
//        }
//    };
//    private static final RespInfo TOKEN_IS_MISSING_STR = RespInfoUtil.tokenIsMissing();
//    private static final RespInfo TOKEN_ERROR_STR;
//    private static final RespInfo AUTH_FAIL_STR;
//    private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
//    private static final Map<Class, Class> NORMAL_CLASS_GEN;
//
//    public MultiInvocableHandlerMethod(Object handler, Method method) {
//        super(handler, method);
//    }
//
//    public MultiInvocableHandlerMethod(HandlerMethod handlerMethod) {
//        super(handlerMethod);
//    }
//
//    public void invokeAndHandle(ServletWebRequest webRequest, ModelAndViewContainer mavContainer, Object... providedArgs) throws Exception {
//        MultiHandle multiHandle = (MultiHandle)this.getMethodAnnotation(MultiHandle.class);
//        boolean tmpTokenValid = multiHandle == null ? false : multiHandle.tokenValid();
//        boolean tmpCustomerResponse = multiHandle == null ? false : multiHandle.customerResponse();
//        Object tmpResult = null;
//        if (multiHandle == null) {
//            try {
//                super.invokeAndHandle(webRequest, mavContainer, providedArgs);
//            } catch (Exception var10) {
//                logger.error("执行错误", var10);
//            }
//
//        } else {
//            if (tmpTokenValid) {
//                tmpResult = this.validateToken(webRequest);
//            }
//
//            if (tmpResult != null) {
//                this.handleResponse(webRequest, mavContainer, tmpResult);
//            } else {
//                try {
//                    tmpResult = this.invokeForRequest(webRequest, mavContainer, providedArgs);
//                } catch (Exception var11) {
//                    tmpResult = RespInfoUtil.normalError(var11.getMessage());
//                    String tmpErrorInfo = String.format("视图名称：%s , 执行控制器调用流程出错", mavContainer.getViewName());
//                    logger.error(tmpErrorInfo, var11);
//                }
//
//                if (!tmpCustomerResponse) {
//                    this.handleResponse(webRequest, mavContainer, tmpResult);
//                } else {
//                    HttpServletResponse httpServletResponse = webRequest.getResponse();
//                    if (!httpServletResponse.isCommitted()) {
//                        httpServletResponse.flushBuffer();
//                    }
//
//                    webRequest.requestCompleted();
//                    mavContainer.setRequestHandled(true);
//                }
//
//            }
//        }
//    }
//
//    public Object invokeForRequest(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer, Object... providedArgs) throws Exception {
//        Object[] args;
//        try {
//            args = this.getMethodArgumentValues(request, mavContainer, providedArgs);
//            this.genPrintAbleArgs(args);
//            String paramResult = this.fieldAssert(args);
//            if (!StringUtil.isEmpty(paramResult)) {
//                return RespInfoUtil.requestFormatError(paramResult);
//            }
//        } catch (Exception var13) {
//            return RespInfoUtil.getRespInfoFromCode("参数错误", "", ServiceErrorCodeEnum.ParamterFormatError);
//        }
//
//        if (logger.isTraceEnabled()) {
//            logger.trace("Arguments: " + Arrays.toString(args));
//        }
//
//        try {
//            Object tmpExecResult = this.doInvoke(args);
//            return tmpExecResult;
//        } catch (Exception var12) {
//            String tmpParamStr = "";
//
//            String tmpMsg;
//            try {
//                tmpParamStr = JSON.toJSONString(args);
//            } catch (Exception var11) {
//                tmpMsg = String.format("控制器执行失败，控制器类：【%s】 函数：【%s】 请求参数格式化失败", this.getBeanType().getTypeName(), this.getMethod().getName());
//                logger.error(tmpMsg, var11);
//                tmpParamStr = "请求参数格式化失败，无法展示";
//            }
//
//            String tmpErrorInfo = String.format("控制器执行失败，控制器类：【%s】 函数：【%s】 请求参数：【%s】\n", this.getBeanType().getTypeName(), this.getMethod().getName(), tmpParamStr);
//            logger.error(tmpErrorInfo, var12);
//            tmpMsg = ServiceErrorCodeEnum.SysError.getErrorStr();
//            if (var12.getMessage() != null && var12.getMessage() != "") {
//                tmpMsg = String.format("%s:%s", ServiceErrorCodeEnum.SysError.getErrorStr(), var12.getMessage());
//            }
//
//            return RespInfoUtil.getRespInfoFromCode(tmpMsg, var12.toString(), ServiceErrorCodeEnum.SysError);
//        }
//    }
//
//    private Object[] genPrintAbleArgs(Object[] args) {
//        if (args != null && args.length > 0) {
//            List<Object> tmpNormalArgList = new ArrayList(args.length);
//            Object[] var3 = args;
//            int var4 = args.length;
//
//            for(int var5 = 0; var5 < var4; ++var5) {
//                Object tmp = var3[var5];
//                if (tmp != null) {
//                    Class<?>[] interfaces = tmp.getClass().getInterfaces();
//                    boolean tmpPrintAble = true;
//                    Class[] var9 = interfaces;
//                    int var10 = interfaces.length;
//
//                    for(int var11 = 0; var11 < var10; ++var11) {
//                        Class<?> tmpInterface = var9[var11];
//                        if (!SYS_PARAM_TYPE.contains(tmpInterface)) {
//                            break;
//                        }
//
//                        tmpPrintAble = false;
//                    }
//
//                    if (tmpPrintAble) {
//                        tmpNormalArgList.add(tmp);
//                    }
//                }
//            }
//
//            return tmpNormalArgList.toArray();
//        } else {
//            return args;
//        }
//    }
//
//    private String fieldAssert(Object[] args) {
//        MethodParameter[] parameters = this.getMethodParameters();
//        StringBuilder stringBuilder = new StringBuilder();
//        if (args != null && args.length != 0 && parameters.length != 0) {
//            for(int i = 0; i < parameters.length; ++i) {
//                MethodParameter parameter = parameters[i];
//                Object param = args[i];
////                ParaName paraName = (ParaName)parameter.getParameterAnnotation(ParaName.class);
////                if (paraName != null && !StringUtil.isEmpty(paraName.businessGroup())) {
////                    RespInfo respInfo = FieldAssert.paramAssert(paraName, param);
////                    if (respInfo.getCode() != ServiceErrorCodeEnum.SUCCESS.getErrorCode()) {
////                        stringBuilder.append(respInfo.getMsg());
////                    }
////                }
//            }
//        }
//
//        return stringBuilder.toString();
//    }
//
//    private void handleResponse(ServletWebRequest webRequest, ModelAndViewContainer mavContainer, Object respInfo) throws Exception {
//        String tmpRespStr = "";
//        if (null != respInfo) {
//            Class<?> resultClass = respInfo.getClass();
//            if (RespInfo.class.equals(resultClass)) {
//                tmpRespStr = JsonUtil.pojo2Str(respInfo);
//            } else {
//                tmpRespStr = JsonUtil.pojo2Str(RespInfoUtil.success(respInfo));
//            }
//        }
//
//        HttpServletResponse httpServletResponse = webRequest.getResponse();
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setHeader("Content-Type", "application/json; charset=utf-8");
//        httpServletResponse.setStatus(200);
//        PrintWriter printWriter = httpServletResponse.getWriter();
//        printWriter.print(tmpRespStr);
//        httpServletResponse.flushBuffer();
//        webRequest.requestCompleted();
//        mavContainer.setRequestHandled(true);
//    }
//
//    protected Object[] getMethodArgumentValues(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer, Object... providedArgs) throws Exception {
//        boolean isMultiSupport = false;
//        MultiHandle multiHandle = (MultiHandle)this.getMethodAnnotation(MultiHandle.class);
//        isMultiSupport = multiHandle == null ? isMultiSupport : multiHandle.value();
//        if (!isMultiSupport) {
//            return super.getMethodArgumentValues(request, mavContainer, providedArgs);
//        } else {
//            boolean hasRequestArgument = multiHandle == null ? false : multiHandle.customerRequest();
//            MethodParameter[] parameters = this.getMethodParameters();
//            if (ObjectUtils.isEmpty(parameters)) {
//                return EMPTY_ARGS;
//            } else {
//                HashMap<Class, Object> tmpSysArgumentsMap = this.getSysArguments(request);
//                HttpServletRequest httpServletRequest = (HttpServletRequest)tmpSysArgumentsMap.get(HttpServletRequest.class);
//                Object[] args = new Object[parameters.length];
//
//                for(int i = 0; i < parameters.length; ++i) {
//                    MethodParameter parameter = parameters[i];
//                    parameter.initParameterNameDiscovery(this.parameterNameDiscoverer);
//                }
//
//                HashMap<String, String> tmpRealParaMap = this.readRequest(httpServletRequest, hasRequestArgument);
//
//                for(int i = 0; i < parameters.length; ++i) {
//                    MethodParameter parameter = parameters[i];
//                    Class<?> tmpParameterType = parameter.getParameter().getType();
//                    if (tmpSysArgumentsMap.containsKey(tmpParameterType)) {
//                        args[i] = tmpSysArgumentsMap.get(parameter.getParameter().getType());
//                    } else {
//                        args[i] = this.genRealPara(tmpRealParaMap, parameter);
//                    }
//                }
//
//                return args;
//            }
//        }
//    }
//
//    public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
//        this.parameterNameDiscoverer = parameterNameDiscoverer;
//    }
//
//    private RespInfo validateToken(ServletWebRequest webRequest) {
//        String tmpTokenStr = webRequest.getHeader("token");
//        if (StringUtil.isEmpty(tmpTokenStr)) {
//            tmpTokenStr = webRequest.getParameter("token");
//        }
//
//        if (StringUtil.isEmpty(tmpTokenStr)) {
//            tmpTokenStr = webRequest.getHeader("deploy-token");
//        }
//
//        HttpServletRequest httpServletRequest = webRequest.getRequest();
//        Integer tmpAppId = AppSwitchCondition.matchingAppid(httpServletRequest);
//        TokenInfo tokenInfo = null;
//        RespInfo tmpResult = null;
//        if (StringUtil.isEmpty(tmpTokenStr)) {
//            tmpResult = TOKEN_IS_MISSING_STR;
//            return tmpResult;
//        } else {
//            try {
//                tokenInfo = TokenUtils.parseJWTtoTokenInfo(tmpTokenStr);
//            } catch (Exception var8) {
//                logger.error("格式化Token失败");
//                tmpResult = TOKEN_ERROR_STR;
//                return tmpResult;
//            }
//
//            if (StringUtil.isEmpty(tokenInfo.getUserCode())) {
//                tmpResult = AUTH_FAIL_STR;
//                return tmpResult;
//            } else if (tmpAppId == null) {
//                tmpResult = RespInfoUtil.getRespInfoFromCode(ServiceErrorCodeEnum.AppIdIsMissing);
//                return tmpResult;
//            } else if (!tmpAppId.equals(tokenInfo.getAppId())) {
//                tmpResult = RespInfoUtil.normalError("请求头的AppId和token的AppId不一致");
//                return tmpResult;
//            } else {
//                return tmpResult;
//            }
//        }
//    }
//
//    private Object genRealPara(HashMap<String, String> tmpRealParaMap, MethodParameter parameter) throws Exception {
//        Object argumentObject = null;
//        if (tmpRealParaMap != null && tmpRealParaMap.size() != 0) {
//            String tmpParaType = this.paraType(parameter);
//            String tmpParaName = parameter.getParameterName();
//            Class<?> tmpParameterType = parameter.getParameterType();
//            if (JSONObject.class.equals(tmpParameterType)) {
//                tmpParaType = "pojo";
//            }
//
//            List<Class<?>> tmpGenericParameterClassList = new ArrayList();
//            Type[] tmpTypeArguments = null;
//            String jsonData;
//            if ("list".equals(tmpParaType) || "map".equals(tmpParaType)) {
//                ParameterizedType tmpParameterizedType = null;
//
//                try {
//                    tmpParameterizedType = (ParameterizedType)parameter.getGenericParameterType();
//                    tmpTypeArguments = tmpParameterizedType.getActualTypeArguments();
//                } catch (Exception var18) {
//                    jsonData = String.format("形参名称:[ %s ] 泛型读取出错 : %s", tmpParaName, var18.getMessage());
//                    logger.error(jsonData, var18);
//                    throw new Exception(jsonData);
//                }
//
//                if (tmpTypeArguments != null) {
//                    tmpGenericParameterClassList = tmpTypeArguments == null ? null : new ArrayList(tmpTypeArguments.length);
//                    Type[] var10 = tmpTypeArguments;
//                    int var21 = tmpTypeArguments.length;
//
//                    for(int var12 = 0; var12 < var21; ++var12) {
//                        Type tmpType = var10[var12];
//                        String tmpListTypeName = tmpType.getTypeName();
//
//                        try {
//                            tmpGenericParameterClassList.add(Class.forName(tmpListTypeName));
//                        } catch (ClassNotFoundException var17) {
//                            String tmpErrorStr = String.format("加载形参类：[ %s ] 出错,不能找到该类 : %s", tmpListTypeName, var17.getMessage());
//                            logger.error(tmpErrorStr, var17);
//                            throw new Exception(tmpErrorStr);
//                        }
//                    }
//                }
//            }
//
//            String tmpParameterName = parameter.getParameterName();
////            ParaName annotation = (ParaName)parameter.getParameterAnnotation(ParaName.class);
////            if (annotation != null && !StringUtil.isEmpty(annotation.name())) {
////                tmpParameterName = annotation.name();
////            }
//
//            jsonData = (String)tmpRealParaMap.get(tmpParameterName);
//            switch (tmpParaType) {
//                case "base":
//                    argumentObject = this.genBaseTypeRealPara(jsonData, tmpParameterType, tmpParaName);
//                    break;
//                case "pojo":
//                    argumentObject = this.genPojoTypeRealPara(jsonData, tmpParameterType, tmpParaName);
//                    break;
//                case "list":
//                    argumentObject = this.genListTypeRealPara(jsonData, tmpGenericParameterClassList, tmpParaName);
//                    break;
//                case "map":
//                    argumentObject = this.genMapTypeRealPara(jsonData, tmpGenericParameterClassList, tmpParaName);
//                    break;
//                default:
//                    argumentObject = this.genPojoTypeRealPara(jsonData, tmpParameterType, tmpParaName);
//            }
//
//            return argumentObject;
//        } else {
//            return argumentObject;
//        }
//    }
//
//    private Object genBaseTypeRealPara(String jsonData, Class<?> paraType, String paraName) throws Exception {
//        String tmpErrorStr;
//        try {
//            return StringUtil.isEmpty(jsonData) ? this.genParaByDefault(paraType) : StringUtil.baseTypeConvert(paraType, jsonData);
//        } catch (IllegalAccessException var6) {
//            tmpErrorStr = String.format("形参名称:[ %s ] 类型：[ %s ] 默认构造初始化错误：[%s]", paraName, paraType.getName(), var6.getMessage());
//            logger.error(tmpErrorStr, var6);
//            throw new Exception(tmpErrorStr);
//        } catch (InstantiationException var7) {
//            tmpErrorStr = String.format("形参名称:[ %s ] 类型：[ %s ] 默认构造非法访问错误：[%s]", paraName, paraType.getName(), var7.getMessage());
//            logger.error(tmpErrorStr, var7);
//            throw new Exception(tmpErrorStr);
//        } catch (Exception var8) {
//            tmpErrorStr = String.format("形参名称:[ %s ] 类型：[ %s ] Json数据格式化错误：[%s]", paraName, paraType.getName(), var8.getMessage());
//            logger.error(tmpErrorStr, var8);
//            throw new Exception(tmpErrorStr);
//        }
//    }
//
//    private Object genPojoTypeRealPara(String jsonData, Class<?> paraType, String paraName) throws Exception {
//        String tmpErrorStr;
//        try {
//            return StringUtil.isEmpty(jsonData) ? this.genParaByDefault(paraType) : JSON.parseObject(jsonData, paraType);
//        } catch (IllegalAccessException var6) {
//            tmpErrorStr = String.format("形参名称:[ %s ] 类型：[ %s ] 默认构造初始化错误：[%s]", paraName, paraType.getName(), var6.getMessage());
//            logger.error(tmpErrorStr, var6);
//            throw new Exception(tmpErrorStr);
//        } catch (InstantiationException var7) {
//            tmpErrorStr = String.format("形参名称:[ %s ] 类型：[ %s ] 默认构造非法访问错误：[%s]", paraName, paraType.getName(), var7.getMessage());
//            logger.error(tmpErrorStr, var7);
//            throw new Exception(tmpErrorStr);
//        } catch (Exception var8) {
//            tmpErrorStr = String.format("形参名称:[ %s ] 类型：[ %s ] Json数据格式化错误：[%s]", paraName, paraType.getName(), var8.getMessage());
//            logger.error(tmpErrorStr, var8);
//            throw new Exception(tmpErrorStr);
//        }
//    }
//
//    private Object genListTypeRealPara(String jsonData, List<Class<?>> paraTypeList, String paraName) throws Exception {
//        String tmpJson = StringUtil.isEmpty(jsonData) ? "[]" : jsonData;
//        if (paraTypeList != null && paraTypeList.size() >= 1) {
//            Class<?> paraType = (Class)paraTypeList.get(0);
//
//            try {
//                return JSON.parseArray(tmpJson, paraType);
//            } catch (Exception var8) {
//                String tmpErrorStr = String.format("形参名称:[ %s ] 类型：[ %s ] Json数据:[ %s ] 默认构造初始化错误 ： %s", paraName, paraType.getName(), tmpJson, var8.getMessage());
//                logger.error(tmpErrorStr, var8);
//                throw new Exception(tmpErrorStr);
//            }
//        } else {
//            String tmpErrorStr = String.format("List 形参名称:[ %s ] 泛型声明缺失 Json数据:[ %s ] 默认构造初始化错误", paraName, jsonData);
//            logger.error(tmpErrorStr);
//            throw new Exception(tmpErrorStr);
//        }
//    }
//
//    private Object genMapTypeRealPara(String jsonData, List<Class<?>> paraTypeList, String paraName) throws Exception {
//        if (paraTypeList != null && paraTypeList.size() >= 2) {
//            Class<?> keyType = (Class)paraTypeList.get(0);
//            Class<?> valueType = (Class)paraTypeList.get(1);
//            String tmpJson;
//            if (keyType != null && valueType != null) {
//                tmpJson = StringUtil.isEmpty(jsonData) ? "{}" : jsonData;
//
//                try {
//                    return JsonUtil.json2Map(tmpJson, keyType, valueType);
//                } catch (Exception var9) {
//                    String tmpErrorStr = String.format("形参名称:[ %s ] Key类型：[ %s ] Value类型：[ %s ] Json数据:[ %s ] 默认构造初始化错误: %s", paraName, keyType.getName(), valueType.getName(), tmpJson, var9.getMessage());
//                    logger.error(tmpErrorStr, var9);
//                    throw new Exception(tmpErrorStr);
//                }
//            } else {
//                tmpJson = String.format("Map 形参名称:[ %s ] 泛型声明缺失 Json数据:[ %s ] 默认构造初始化错误", paraName, jsonData);
//                logger.error(tmpJson);
//                throw new Exception(tmpJson);
//            }
//        } else {
//            String tmpErrorStr = String.format("Map 形参名称:[ %s ] 泛型声明缺失 Json数据:[ %s ] 默认构造初始化错误", paraName, jsonData);
//            logger.error(tmpErrorStr);
//            throw new Exception(tmpErrorStr);
//        }
//    }
//
//    private String paraType(MethodParameter parameter) {
//        Class<?> tmpParameterType = parameter.getParameterType();
//        if (StringUtil.isBaseType(tmpParameterType)) {
//            return "base";
//        } else {
//            Class<?> paraClass = parameter.getParameterType();
//            if (List.class.equals(paraClass)) {
//                return "list";
//            } else if (Map.class.equals(paraClass)) {
//                return "map";
//            } else {
//                Class<?>[] interfaceArr = paraClass.getInterfaces();
//                if (interfaceArr != null && interfaceArr.length != 0) {
//                    Class[] var5 = interfaceArr;
//                    int var6 = interfaceArr.length;
//
//                    for(int var7 = 0; var7 < var6; ++var7) {
//                        Class<?> tmpInterface = var5[var7];
//                        if (List.class.equals(tmpInterface)) {
//                            return "list";
//                        }
//
//                        if (Map.class.equals(tmpInterface)) {
//                            return "map";
//                        }
//                    }
//
//                    return "pojo";
//                } else {
//                    return "pojo";
//                }
//            }
//        }
//    }
//
//    private Object genParaByDefault(Class<?> parameterType) throws IllegalAccessException, InstantiationException {
//        if (parameterType == null) {
//            return null;
//        } else {
//            Constructor[] tmpConstructors = parameterType.getConstructors();
//            if (tmpConstructors != null && tmpConstructors.length != 0) {
//                Constructor[] var3 = tmpConstructors;
//                int var4 = tmpConstructors.length;
//
//                for(int var5 = 0; var5 < var4; ++var5) {
//                    Constructor tmp = var3[var5];
//                    if (tmp.getParameterCount() <= 0) {
//                        return parameterType.newInstance();
//                    }
//                }
//
//                return null;
//            } else {
//                return NORMAL_CLASS_GEN.containsKey(parameterType) ? ((Class)NORMAL_CLASS_GEN.get(parameterType)).newInstance() : null;
//            }
//        }
//    }
//
//    private HashMap<String, String> readRequest(HttpServletRequest httpServletRequest, boolean hasRequestArgument) throws Exception {
//        StringBuilder tmpBodyParameterStr = new StringBuilder();
//        StringBuilder tmpQueryParameterStr = new StringBuilder();
//        Map<String, String[]> queryParaMap = httpServletRequest.getParameterMap();
//        HashMap<String, String> tmpJsonParameter = new HashMap(queryParaMap.size());
//        Iterator tmpBodyParamMap = queryParaMap.keySet().iterator();
//
//        while(tmpBodyParamMap.hasNext()) {
//            String key = (String)tmpBodyParamMap.next();
//            String[] tmpValues = (String[])queryParaMap.get(key);
//            tmpJsonParameter.put(key, tmpValues != null && tmpValues.length > 0 ? tmpValues[0] : "");
//        }
//
//        tmpQueryParameterStr.append(JSON.toJSONString(tmpJsonParameter));
//        String tmpErrorStr;
//        if (!hasRequestArgument) {
//            BufferedReader br = null;
//            boolean var18 = false;
//
//            try {
//                var18 = true;
//                ServletInputStream servletInputStream = httpServletRequest.getInputStream();
//                br = new BufferedReader(new InputStreamReader(servletInputStream, "utf-8"));
//
//                while(true) {
//                    if ((tmpErrorStr = br.readLine()) == null) {
//                        var18 = false;
//                        break;
//                    }
//
//                    tmpBodyParameterStr.append(tmpErrorStr);
//                }
//            } catch (IOException var22) {
//                tmpErrorStr = String.format("Body体流数据读取错误，Uri:[ %s ] 错误：%s", httpServletRequest.getRequestURI(), var22.getMessage());
//                logger.error(tmpErrorStr, var22);
//                throw new Exception(tmpErrorStr);
//            } finally {
//                if (var18) {
//                    if (br != null) {
//                        try {
//                            if (br.markSupported()) {
//                                br.mark(0);
//                            }
//
//                            br.reset();
//                        } catch (IOException var19) {
//                            tmpErrorStr = String.format("Body体流数据重置或关闭错误，Uri:[ %s ] 错误：%s", httpServletRequest.getRequestURI(), var19.getMessage());
//                            logger.error(tmpErrorStr, var19);
//                            throw new Exception(tmpErrorStr);
//                        }
//                    }
//
//                }
//            }
//
//            if (br != null) {
//                try {
//                    if (br.markSupported()) {
//                        br.mark(0);
//                    }
//
//                    br.reset();
//                } catch (IOException var21) {
//                    tmpErrorStr = String.format("Body体流数据重置或关闭错误，Uri:[ %s ] 错误：%s", httpServletRequest.getRequestURI(), var21.getMessage());
//                    logger.error(tmpErrorStr, var21);
//                    throw new Exception(tmpErrorStr);
//                }
//            }
//        }
//
//        tmpBodyParamMap = null;
//
//        HashMap tmpbodyparammap;
//        try {
//            tmpbodyparammap = this.splitJson(tmpBodyParameterStr.toString());
//        } catch (Exception var20) {
//            tmpErrorStr = String.format("Body体数据拆分错误，Uri:[ %s ] bodyData:[ %s ] 错误 : %s", httpServletRequest.getRequestURI(), tmpBodyParameterStr.toString(), var20.getMessage());
//            logger.error(tmpErrorStr, var20);
//            throw new Exception(tmpErrorStr);
//        }
//
//        Iterator var27 = tmpbodyparammap.keySet().iterator();
//
//        while(var27.hasNext()) {
//            tmpErrorStr = (String)var27.next();
//            String tmpValue = (String)tmpbodyparammap.get(tmpErrorStr);
//            tmpJsonParameter.put(tmpErrorStr, tmpValue);
//        }
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Uri:[ {} ] queryData: [ {} ] bodyData:[ {} ] activeData:{}", new Object[]{httpServletRequest.getRequestURI(), tmpQueryParameterStr.toString(), tmpBodyParameterStr.toString(), JSON.toJSONString(tmpJsonParameter)});
//        }
//
//        return tmpJsonParameter;
//    }
//
//    private HashMap<Class, Object> getSysArguments(NativeWebRequest nativeWebRequest) {
//        HttpServletRequest httpServletRequest = (HttpServletRequest)nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//        HttpServletResponse httpServletResponse = (HttpServletResponse)nativeWebRequest.getNativeResponse(HttpServletResponse.class);
//        ServletRequest servletRequest = (ServletRequest)nativeWebRequest.getNativeRequest(ServletRequest.class);
//        ServletResponse servletResponse = (ServletResponse)nativeWebRequest.getNativeResponse(ServletResponse.class);
//        HttpSession httpSession = httpServletRequest.getSession();
//        HashMap<Class, Object> tmpSysArgumentsMap = new HashMap(5);
//        tmpSysArgumentsMap.put(HttpServletRequest.class, httpServletRequest);
//        tmpSysArgumentsMap.put(HttpServletResponse.class, httpServletResponse);
//        tmpSysArgumentsMap.put(ServletRequest.class, servletRequest);
//        tmpSysArgumentsMap.put(ServletResponse.class, servletResponse);
//        tmpSysArgumentsMap.put(HttpSession.class, httpSession);
//        String tmpToken = httpServletRequest.getHeader("token");
//        if (StringUtil.isEmpty(tmpToken)) {
//            tmpToken = httpServletRequest.getParameter("token");
//        }
//
//        if (StringUtil.isEmpty(tmpToken)) {
//            tmpToken = httpServletRequest.getHeader("deploy-token");
//        }
//
//        Integer tmpAppId = AppSwitchCondition.matchingAppid(httpServletRequest);
//        String tmpTenantId = httpServletRequest.getHeader("tenantId");
//        String tmpTenantName = httpServletRequest.getHeader("tenantName");
//
//        try {
//            tmpTenantName = StringUtil.isEmpty(tmpTenantName) ? null : URLDecoder.decode(tmpTenantName, StandardCharsets.UTF_8.name());
//        } catch (UnsupportedEncodingException var15) {
//            logger.error("构造系统参数出错", var15);
//        }
//
//        TokenInfo tokenInfo = new TokenInfo();
//
//        try {
//            tokenInfo = TokenUtils.parseJWTtoTokenInfo(tmpToken);
//        } catch (Exception var14) {
//            logger.error("Token解析失败，原始值：{}", tmpToken, var14);
//        }
//
//        tokenInfo.setTenantId(tmpTenantId);
//        tokenInfo.setTenantName(tmpTenantName);
//        tokenInfo.setHeaderAppId(tmpAppId);
//        tmpSysArgumentsMap.put(TokenInfo.class, tokenInfo);
//        return tmpSysArgumentsMap;
//    }
////
////    public HashMap<String, String> splitJson(String s) {
////        if (StringUtil.isEmpty(s)) {
////            return new HashMap(0);
////        } else {
////            s = s.replace("\n", "").replace("\t", "");
////            HashMap<String, String> rtn = new HashMap(0);
////            HashMap<String, String> jobj = null;
////            jobj = (HashMap)JSON.parseObject(s, HashMap.class);
////            Iterator var4 = jobj.entrySet().iterator();
////
////            while(var4.hasNext()) {
////                Map.Entry<String, String> entrySet = (Map.Entry)var4.next();
////                String key = (String)entrySet.getKey();
////                Object value = entrySet.getValue();
////                if (value == null) {
////                    rtn.put(key, (Object)null);
////                } else if (String.class.equals(value.getClass())) {
////                    rtn.put(key, (String)value);
////                } else {
////                    rtn.put(key, JSON.toJSONString(value));
////                }
////            }
////
////            return rtn;
////        }
////    }
//
//    static {
//        TOKEN_ERROR_STR = RespInfoUtil.getRespInfoFromCode(ServiceErrorCodeEnum.TokenParseError);
//        AUTH_FAIL_STR = RespInfoUtil.getRespInfoFromCode(ServiceErrorCodeEnum.AuthFail);
//        NORMAL_CLASS_GEN = new HashMap<Class, Class>(2) {
//            {
//                this.put(List.class, ArrayList.class);
//                this.put(Map.class, HashMap.class);
//            }
//        };
//    }
//}

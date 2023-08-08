package com.security.study.spring.事件机制;

/**
 * 发代办事件
 */
public class DaiBanEvent /*extends ApplicationEvent*/ {

    String daiBanBody;

    ///**
    // * Create a new ApplicationEvent.
    // *
    // * @param source the object on which the event initially occurred (never {@code null})
    // */
    //public DaiBanEvent(Object source, String daiBanBody) {
    //    super(source);
    //    this.daiBanBody = daiBanBody;
    //}


    public String getDaiBanBody() {
        return daiBanBody;
    }

    public void setDaiBanBody(String daiBanBody) {
        this.daiBanBody = daiBanBody;
    }
}

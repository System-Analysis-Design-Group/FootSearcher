package io.github.foodsearcher.model;

/**
 * Created by AAAAA on 2018-02-19.
 */

public class StatusMsg {
    String code;
    String message;

    Object obj;

    static public StatusMsg returnOkWithObj(Object obj) {
        StatusMsg statusMsg = new StatusMsg(Message.OK_CODE, Message.OK, obj);
        return statusMsg;
    }

    // 不应当将系统错误暴露出本系统
    @Deprecated
    static public StatusMsg returnErrorWithObj(Object obj) {
        StatusMsg statusMsg = new StatusMsg(Message.ERROR_CODE, Message.ERROR, obj);
        return statusMsg;
    }

    static public StatusMsg returnOk() {
        StatusMsg statusMsg = new StatusMsg();
        statusMsg.setMessage(Message.OK);
        statusMsg.setCode(Message.OK_CODE);
        return statusMsg;
    }

    static public StatusMsg returnError() {
        StatusMsg statusMsg = new StatusMsg();
        statusMsg.setMessage(Message.ERROR);
        statusMsg.setCode(Message.ERROR_CODE);
        return statusMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusMsg() {
    }

    public StatusMsg(String code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}

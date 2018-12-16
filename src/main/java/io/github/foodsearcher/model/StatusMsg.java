package io.github.foodsearcher.model;

/**
 * Created by AAAAA on 2018-02-19.
 */

public class StatusMsg {
    String code;
    String message;

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
}

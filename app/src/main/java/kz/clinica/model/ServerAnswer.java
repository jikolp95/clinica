package kz.clinica.model;


public class ServerAnswer {

    private String message;
    private int code;

    public ServerAnswer() {
    }

    public ServerAnswer(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "{" + "code:" + code + "message" + message + "}";
    }
}

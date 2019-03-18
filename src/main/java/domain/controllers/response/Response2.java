package domain.controllers.response;

public class Response2 {
    public Response2() {
    }

    public Response2(boolean success) {
        this.success = success;
    }

    public Response2(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private boolean success;
    private Object data;


}

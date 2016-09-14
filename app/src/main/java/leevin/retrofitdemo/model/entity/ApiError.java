package leevin.retrofitdemo.model.entity;

/**
 * Created by  Leevin
 * on 2016/9/12 ,17:03.
 */

public class ApiError {

    /**
     * message : Not Found
     * documentation_url : https://developer.github.com/v3
     */

    private String message;
    private String documentation_url;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentation_url() {
        return documentation_url;
    }

    public void setDocumentation_url(String documentation_url) {
        this.documentation_url = documentation_url;
    }
}

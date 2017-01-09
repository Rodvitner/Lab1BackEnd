package com.viewmodels.resultviews;


/**
 * Created by archer on 2016-11-20.
 */
public class CreatePostResult extends Result {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreatePostResult(boolean success, String message, String id) {
        super(success, message);
        this.id = id;
    }

    public CreatePostResult(boolean success, String message) {
        super(success, message);
    }

    public CreatePostResult() {
    }
}

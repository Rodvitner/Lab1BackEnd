package com.viewmodels.resultviews;

import com.google.appengine.api.datastore.Key;

/**
 * Created by archer on 2016-11-20.
 */
public class CreatePostResult extends Result {

    private Key id;

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public CreatePostResult(boolean success, String message, Key id) {
        super(success, message);
        this.id = id;
    }

    public CreatePostResult(boolean success, String message) {
        super(success, message);
    }

    public CreatePostResult() {
    }
}

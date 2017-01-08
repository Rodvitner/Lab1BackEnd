package com.viewmodels.resultviews;

import com.google.appengine.api.datastore.Key;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class CreateChatRoomResult extends Result {
    Key id;

    public CreateChatRoomResult(boolean success, String message, Key id) {
        super(success, message);
        this.id = id;
    }

    public CreateChatRoomResult() {
    }

    public Key getId() {

        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }
}

package com.viewmodels.resultviews;


/**
 * Created by simonlundstrom on 24/11/16.
 */
public class CreateChatRoomResult extends Result {
    String id;

    public CreateChatRoomResult(boolean success, String message, String id) {
        super(success, message);
        this.id = id;
    }

    public CreateChatRoomResult() {
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

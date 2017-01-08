package com.viewmodels.generalviews;

import com.google.appengine.api.datastore.Key;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatroomMetaData {
    private Key roomId;
    private String roomName;

    public ChatroomMetaData(Key roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public ChatroomMetaData() {
    }

    public Key getRoomId() {
        return roomId;
    }

    public void setRoomId(Key roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

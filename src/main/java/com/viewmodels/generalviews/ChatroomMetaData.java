package com.viewmodels.generalviews;


/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatroomMetaData {
    private String roomId;
    private String roomName;

    public ChatroomMetaData(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public ChatroomMetaData() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

package com.viewmodels.generalviews;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatroomMetaData {
    private int roomId;
    private String roomName;

    public ChatroomMetaData(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public ChatroomMetaData() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

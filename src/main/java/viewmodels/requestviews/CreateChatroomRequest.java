package viewmodels.requestviews;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class CreateChatroomRequest {
    String ownerEmail, inviteeEmail, roomName;

    public CreateChatroomRequest(String ownerEmail, String inviteeEmail, String roomName) {
        this.ownerEmail = ownerEmail;
        this.inviteeEmail = inviteeEmail;
        this.roomName = roomName;
    }

    public CreateChatroomRequest() {
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getInviteeEmail() {
        return inviteeEmail;
    }

    public void setInviteeEmail(String inviteeEmail) {
        this.inviteeEmail = inviteeEmail;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

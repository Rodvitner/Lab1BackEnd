package bo.translators;

import model.Chatroom;
import viewmodels.generalviews.ChatroomMetaData;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatroomMetaDataMapper extends Translator<Chatroom,ChatroomMetaData>{
    @Override
    public ChatroomMetaData translateFromA(Chatroom chatroom) {
        return new ChatroomMetaData(chatroom.getId(),chatroom.getName());
    }

    @Override
    public Chatroom translateFromB(ChatroomMetaData chatroomMetaData) {
        throw new UnsupportedOperationException("Cannot convert from Metadata to actual Chatroom!!");
        // TODO: 24/11/16
    }
}

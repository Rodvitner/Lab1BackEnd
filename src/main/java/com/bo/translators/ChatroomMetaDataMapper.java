package com.bo.translators;

import com.google.appengine.api.datastore.KeyFactory;
import com.model.Chatroom;
import com.viewmodels.generalviews.ChatroomMetaData;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatroomMetaDataMapper extends Translator<Chatroom,ChatroomMetaData>{
    @Override
    public ChatroomMetaData translateFromA(Chatroom chatroom) {
        return new ChatroomMetaData(KeyFactory.keyToString(chatroom.getId()),chatroom.getName());
    }

    @Override
    public Chatroom translateFromB(ChatroomMetaData chatroomMetaData) {
        throw new UnsupportedOperationException("Cannot convert from Metadata to actual Chatroom!!");
        // TODO: 24/11/16
    }
}

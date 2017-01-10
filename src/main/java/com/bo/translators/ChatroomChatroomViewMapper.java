package com.bo.translators;

import com.bo.ChatLogic;
import com.google.appengine.api.datastore.KeyFactory;
import com.model.ChatMessage;
import com.model.Chatroom;
import com.model.User;
import com.viewmodels.generalviews.ChatMessageView;
import com.viewmodels.generalviews.ChatRoomView;
import com.viewmodels.generalviews.UserView;

import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatroomChatroomViewMapper extends Translator<Chatroom,ChatRoomView>{
    @Override
    public ChatRoomView translateFromA(Chatroom chatroom) {
        List<User> members = chatroom.getMembers();
        List<ChatMessage> messages = chatroom.getMessages();

        List<UserView> memberViews = new UserUserViewMapper().translateListOfA(members);

        List<ChatMessageView> messageViews = new ChatmessageChatmessageViewMapper().translateListOfA(messages);

        return new ChatRoomView(chatroom.getName(), KeyFactory.keyToString(chatroom.getId()),memberViews,messageViews);
        //TODO FIX THIS SHIT!!!
       // return null;
    }

    @Override
    public Chatroom translateFromB(ChatRoomView chatRoomView) {
        throw new UnsupportedOperationException("I strictly refuse to make a Chatroom out of a view!");
    }
}

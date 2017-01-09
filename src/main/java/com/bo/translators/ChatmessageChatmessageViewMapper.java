package com.bo.translators;

import com.google.appengine.api.datastore.KeyFactory;
import com.model.ChatMessage;
import com.viewmodels.generalviews.ChatMessageView;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class ChatmessageChatmessageViewMapper extends Translator<ChatMessage, ChatMessageView> {
    @Override
    public ChatMessageView translateFromA(ChatMessage chatMessage) {
        return new ChatMessageView(chatMessage.getText(),
                chatMessage.getPoster(),
                chatMessage.getDate(),
                KeyFactory.keyToString(chatMessage.getRoom().getId()),
                KeyFactory.keyToString(chatMessage.getId())
        );
    }

    @Override
    public ChatMessage translateFromB(ChatMessageView chatMessageView) {
        throw new UnsupportedOperationException("I refuse to translate a ChatmessageView to a Chatmessage!");
    }
}

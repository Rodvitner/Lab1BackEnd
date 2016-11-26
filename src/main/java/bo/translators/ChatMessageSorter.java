package bo.translators;

import model.ChatMessage;

import java.util.Comparator;

/**
 * Created by simonlundstrom on 26/11/16.
 */
public class ChatMessageSorter implements Comparator<ChatMessage>{
    @Override
    public int compare(ChatMessage o1, ChatMessage o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}

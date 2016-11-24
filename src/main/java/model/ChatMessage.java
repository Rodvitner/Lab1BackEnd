package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
@Entity
public class ChatMessage {
    @Id
    private int id;

    private String text;
    private Date date;

    @ManyToOne
    private Chatroom room;

    @ManyToOne
    private User poster;
}

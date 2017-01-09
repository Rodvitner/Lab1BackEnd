package com.bo.translators;

import com.google.appengine.api.datastore.KeyFactory;
import com.model.Comment;
import com.viewmodels.generalviews.CommentView;


/**
 * Created by simonlundstrom on 24/11/16.
 */
public class CommentCommentViewMapper extends Translator<Comment, CommentView> {
    @Override
    public CommentView translateFromA(Comment comment) {
        return new CommentView(comment.getText(),
                comment.getDate().toString(),
                KeyFactory.keyToString(comment.getId()),
                KeyFactory.keyToString(comment.getPost().getId()),
                comment.getUsername()
        );
    }

    @Override
    public Comment translateFromB(CommentView commentView) {
        return null;
    }
}
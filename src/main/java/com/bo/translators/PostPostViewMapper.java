package com.bo.translators;

import com.model.Post;
import com.viewmodels.generalviews.PostView;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class PostPostViewMapper extends Translator<Post,PostView>{
    @Override
    public PostView translateFromA(Post post) {
        return new PostView(post.getText(),post.getDate().toString(),post.getUser().getEmail(),post.getId());
    }

    @Override
    public Post translateFromB(PostView postView) {
        throw new UnsupportedOperationException("Cannot translate from PostView to Post.");
        // TODO: 23/11/16  do something about this?
    }
}

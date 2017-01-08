package com.viewmodels.resultviews;

import com.viewmodels.generalviews.CommentView;

import java.util.List;

/**
 * Created by simonlundstrom on 24/11/16.
 */
public class CommentListResult extends Result {
    List<CommentView> list;

    public List<CommentView> getList() {
        return list;
    }

    public void setList(List<CommentView> list) {
        this.list = list;
    }

    public CommentListResult(boolean success, String message, List<CommentView> list) {
        super(success, message);
        this.list = list;
    }

    public CommentListResult() {
    }
}

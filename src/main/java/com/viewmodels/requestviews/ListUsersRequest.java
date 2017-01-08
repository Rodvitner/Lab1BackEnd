package com.viewmodels.requestviews;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public class ListUsersRequest {
    String query;
    int startAt,amountOf;

    public ListUsersRequest(String query, int startAt, int amountOf) {
        this.query = query;
        this.startAt = startAt;
        this.amountOf = amountOf;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public int getAmountOf() {
        return amountOf;
    }

    public void setAmountOf(int amountOf) {
        this.amountOf = amountOf;
    }
}

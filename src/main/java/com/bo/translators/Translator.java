package com.bo.translators;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by simonlundstrom on 23/11/16.
 */
public abstract class Translator<A,B> {
    public abstract B translateFromA(A a);

    public abstract A translateFromB(B b);

    public List<B> translateListOfA(List<A> list) {
        List<B> ret = new LinkedList<B>();
        for (A a : list) ret.add(translateFromA(a));
        return ret;
    }
    public List<A> translateListOfB(List<B> list) {
        List<A> ret = new LinkedList<A>();
        for (B b : list) ret.add(translateFromB(b));
        return ret;
    }
}

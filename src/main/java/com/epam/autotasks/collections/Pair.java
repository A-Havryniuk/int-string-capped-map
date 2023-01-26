package com.epam.autotasks.collections;

import java.util.Collections;
import java.util.List;

public class Pair<T1, T2> {
    public final T1 first;
    public final T2 second;
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        if(!first.equals(pair.first))
            return false;
        return second.equals(pair.second);
    }

}

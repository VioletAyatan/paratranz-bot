package com.para.tranzai.mirai.core;

import java.util.Objects;

public class IKey {
    private final String mark;

    private IKey(String mark) {
        this.mark = mark;
    }

    public static IKey create(String mark) {
        return new IKey(mark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IKey)) return false;

        IKey iKey = (IKey) o;

        return Objects.equals(mark, iKey.mark) || mark.contains(iKey.mark);
    }

    @Override
    public int hashCode() {
        return mark != null ? mark.hashCode() : 0;
    }
}

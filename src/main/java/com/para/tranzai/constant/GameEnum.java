package com.para.tranzai.constant;

import lombok.Getter;

@Getter
public enum GameEnum {
    STELLARIS("stellaris"),
    ;

    private final String name;

    GameEnum(String name) {
        this.name = name;
    }
}

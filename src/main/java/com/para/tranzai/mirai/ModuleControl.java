package com.para.tranzai.mirai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleControl {
    /**
     * 影响系统中有关paraTranz相关服务的启停
     */
    private boolean paraActive;
    /**
     * 影响系统中有关mirai相关服务的启停
     */
    private boolean miraiActive;
}

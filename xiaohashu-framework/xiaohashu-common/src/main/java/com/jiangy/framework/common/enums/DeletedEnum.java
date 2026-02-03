package com.jiangy.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author 32185
 */

@Getter
@AllArgsConstructor
public enum DeletedEnum {

    YES(true),
    NO(false);

    private final Boolean value;
}

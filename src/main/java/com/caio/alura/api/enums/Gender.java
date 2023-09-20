package com.caio.alura.api.enums;

import com.caio.alura.api.utils.StringUtil;

public enum Gender {
    
    MASCULINO,
    FEMININO,
    OUTRO;

    public static Gender fromValue(String label) {
        label = StringUtil.removeAccents(label);
        return Gender.valueOf(label.toUpperCase());
    }

}

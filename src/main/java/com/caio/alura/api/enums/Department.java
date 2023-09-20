package com.caio.alura.api.enums;

import com.caio.alura.api.utils.StringUtil;

public enum Department {
    
    MATEMATICA,
    PORTUGUES,
    GEOGRAFIA,
    HISTORIA,
    FISICA,
    QUIMICA,
    BIOLOGIA;

    public static Department fromValue(String label) {
        label = StringUtil.removeAccents(label);
        return Department.valueOf(label.toUpperCase());
    }

}

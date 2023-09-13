package com.alura.api.degree.enums;

public enum Gender {
    
    MASCULINO,
    FEMININO,
    OUTRO;

    public static Gender fromValue(String label) {
        return Gender.valueOf(label.toUpperCase());
    }

}

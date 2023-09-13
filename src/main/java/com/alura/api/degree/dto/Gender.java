package com.alura.api.degree.dto;

public enum Gender {
    
    MASCULINO,
    FEMININO,
    OUTRO;

    public static Gender fromValue(String label) {
        return Gender.valueOf(label.toUpperCase());
    }

}

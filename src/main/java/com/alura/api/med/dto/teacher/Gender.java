package com.alura.api.med.dto.teacher;

public enum Gender {
    
    MASCULINO,
    FEMININO,
    OUTRO;

    public static Gender fromValue(String label) {
        return Gender.valueOf(label.toUpperCase());
    }

}

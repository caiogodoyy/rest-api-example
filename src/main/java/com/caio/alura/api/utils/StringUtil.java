package com.caio.alura.api.utils;

import java.text.Normalizer;

public class StringUtil {
    
    public static String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}

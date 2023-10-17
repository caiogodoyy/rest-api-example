package com.caio.alura.api.utils;

import java.text.Normalizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

    private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);
    
    public static String removeAccents(String str) {
        logger.info(".removeAccents: Removing accents from string " + str);
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}

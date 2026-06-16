package org.example;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public class ContadorPalabras {
    public int Count(String text) {
        //No es lo mismo colocar en la misma cabecera del if:
        //text == null || text.isBlank(): primero valida su existencia y luego en caso exista valida si es vacio o tiene espacio ne blanco
        //text.isBlank() || text == null: El primero asume que ya existe y luego verifica si es nulo (indica la existencia). El orden correcto es el del primer caso
        if(text == null || StringUtils.isBlank(text) ) {
            return 0;
        }

        int words = 0;
        String[] tokens = text.split("\\s+");
        for(String token : tokens){
            if(StringUtils.isAlphanumeric(token)) {
                words++;
            }
            else
            {
                String noAlfanumRegex = "[^a-zA-Z0-9ñÑ]";
                if(!RegExUtils.removeAll(token, noAlfanumRegex).isBlank()) {
                    words++;
                }
            }
        }
        return words;
    }
}

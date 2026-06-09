package org.example;

public class NormalizarTexto {

    public static final char[] TILDES = new char[]{'Á', 'É', 'Í', 'Ó', 'Ú', '.'};
    public static final char[] NORMAL = new char[]{'A', 'E', 'I', 'O', 'U', '*'};

    public String normalizarAMayusculas(String text) {
        if (text == null || "".equals(text)) {
            return "";
        }
        String entrada = text;
        entrada = entrada.toUpperCase();
        entrada = entrada.trim();

        for(int i = 0; i < TILDES.length; i++){
            entrada = entrada.replace(TILDES[i], NORMAL[i]);
        }
        return entrada;
    }

}

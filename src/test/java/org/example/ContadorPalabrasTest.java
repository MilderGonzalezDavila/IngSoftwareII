package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class ContadorPalabrasTest {

    private ContadorPalabras contadorPalabras;

    @BeforeEach
    public void setUp() {
        contadorPalabras = new ContadorPalabras();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testContarCeroPalabras_emptyTest(String text){
        int result = contadorPalabras.Count(text);
        assertNumWords(0, result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'Hola que tal, me llamo Arturo', 6",
            "'      ', 0",
            "'', 0",
            "'...', 0",
            "'.$%#', 0",
            "'2026', 1",
            "'a. B... ñÑ', 3",
            "'¿Hola, que tal ? quiero esto ...&%$# y aquello . etc.?', 8"
    })
    void testContarPalabrasCompleto(String text, int nroWordsExpected){
        int result = contadorPalabras.Count(text);
        assertNumWords(nroWordsExpected, result);
    }

    public static void assertNumWords(int numWordsExpected, int result){
        assertEquals(numWordsExpected, result, "El numero de palabras no es el esperado");
    }
}
package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.junit.jupiter.api.Assertions.*;

public class NormalizarTextoTest {
    private NormalizarTexto nt;

    @BeforeEach
    void Inicializar(){
        nt = new NormalizarTexto();
    }

    @ParameterizedTest
    @CsvSource({
            "patata, PATATA",
            "hola soledad, HOLA SOLEDAD",
            "'EsPErando el AmOr...', 'ESPERANDO EL AMOR***'",
            "'lucas, el pato', 'LUCAS, EL PATO'",
            "'  ', ''",
            ", ''"
    })
    public void testNormalizarTexto(String txtNormalizado, String resultadoEsperado){
        String cadenaActual = nt.normalizarAMayusculas(txtNormalizado);
        assertEquals(resultadoEsperado, cadenaActual);
    }

    //Test que recibe parámetros (@NullAndEmpty define el tipo de paráemtro que van entrar)
    @ParameterizedTest
    @NullAndEmptySource
    public void testNormallizarTexto_NullAndVacio(String txt){
        String cadenaActual = nt.normalizarAMayusculas(txt);
        assertEquals("", cadenaActual);
    }


    //@Test
    public void probarNormalizarCadenaVacia(){
       String cadenaVacia= "";
       String cadenaActual = nt.normalizarAMayusculas(cadenaVacia);
       assertEquals("", cadenaActual);
    }

    //@Test
    public void probarNormalizarNulo(){
        String cadenaVacia= null;
        String cadenaActual = nt.normalizarAMayusculas(cadenaVacia);
        assertEquals("", cadenaActual);
    }

    //@Test
    public void probarNormalizarAMayusculas(){
        String text = "MamÁ y PapÁ me Aman demasiado...   ";
        String cadenaActual = nt.normalizarAMayusculas(text);
        assertEquals("MAMA Y PAPA ME AMAN DEMASIADO***", cadenaActual);
    }

}

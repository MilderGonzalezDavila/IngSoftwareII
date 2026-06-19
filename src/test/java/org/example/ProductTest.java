package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
/*
    @ParameterizedTest
    @MethodSource("ObtenerValores")
    void testCrearProduct(){

    }

    void valores

    @ParameterizedTest
    @CsvSource(" 'Producto 1', 99.9, 18")
    void testCrearProduct(String dsc, double bp, int imp){
        Product p = new Product(dsc, bp, imp);
        assertNotNull(p);
        assertEquals(dsc, p.getDescription());
        assertEquals(bp, p.getBasePrice());
        assertEquals(imp, p.getVat());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "",
            "   "
    })
    void testFallaCreaProductoPorDescripcion(String dsc){
        double bp = 99.99;
        int imp = 18;
        Executable exe = () -> new Product(dsc, bp, imp);
        assertThrowsIllegalArgumentException(exe, "Descripcion no puede ser vacia");
    }

    private static void assertThrowsIllegalArgumentException(Executable exe, String msg){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,  exe);
        assertEquals(msg, e.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "-0.01, 18, 'Precio base no puede ser negativo'",
            "-100, 18, 'Precio base no puede ser negativo'",
            "100, -1, 'El impuesto no puede ser negativo'",
            "100, -100, 'El impuesto no puede ser negativo'",
            "-10, -5, 'Precio base no puede ser negativo'"
    })
    void testFallaCrearProduct_bpNegativo(double bp, int imp, String mensajeEsperado){
        String dsc = "Producto 1";
        Executable exe = () -> new Product(dsc, bp, imp);
        assertThrowsIllegalArgumentException(exe, mensajeEsperado);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01d, -100.0d})
    void testFallaCrearProduct_bpNegativo(double bp){
        String dsc = "Producto 1";
        int imp = 18;
        Executable exe = () -> new Product(dsc, bp, imp);
        assertThrowsIllegalArgumentException(exe, "Precio base no puede ser negativo");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void testFallaCrearProduct_bpNegativo(int imp){
        String dsc = "Producto 1";
        double bp =99.99d;
        Executable exe = () -> new Product(dsc, bp, imp);
        assertThrowsIllegalArgumentException(exe, "El impuesto no puede ser negativo");
    }

*/

}

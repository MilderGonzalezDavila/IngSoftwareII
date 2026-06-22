package org.example;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product CreateValidProduct(){
        return new Product("Primer producto", 100, 18 ,1, LocalDate.now().plusDays(10));
    }

    //Crear un producto valido
    @ParameterizedTest
    @CsvSource({
            " 'Primer producto', 99.9, 18, 1, 2026-12-31",
            " 'Segundo producto', 60.0, 18, 5, 2026-11-23",
            " 'Tercer producto', 55.50, 18, 10, 2027-12-15"
    })
    void Crear_ProductValoresValidos(String descripcion, double preciobase, int impuesto, int unidades, String fecha){
        LocalDate fechaVencimiento =  LocalDate.parse(fecha);
        Product product = new Product(descripcion, preciobase, impuesto, unidades, fechaVencimiento);

        assertNotNull(product);
        assertEquals(descripcion, product.getDescripcion());
        assertEquals(preciobase, product.getPrecioBase());
        assertEquals(impuesto, product.getImpuesto());
        assertEquals(unidades, product.getUnidades());
        assertEquals(fechaVencimiento,  product.getFechaVencimiento());
    }

    //Descripcion
    @ParameterizedTest
    @NullSource
    @EmptySource
    public void Crear_DescripcionNullOVacia_LanzaExcepcion_FirstWay(String descripcion){
        Executable exe = () -> new Product(descripcion,100, 18 ,4, LocalDate.now().plusDays(2));
        assertThrowsIllegalArgumentException(exe, "Descripcion no puede ser null o vacia");
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void Crear_DescripcionNullOVacia_LanzaExcepcion_SecondWay(String descripcion){
        Executable exe = () -> new Product(descripcion,100, 18 ,4, LocalDate.now().plusDays(2));
        assertThrowsIllegalArgumentException(exe, "Descripcion no puede ser null o vacia");
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "     "})
    public void Crear_DescripcionInvalida_LanzaExcepcion(String descripcion){
        Executable exe = () -> new Product(descripcion,100, 18 ,4, LocalDate.now().plusDays(2));
        assertThrowsIllegalArgumentException(exe, "Descripcion no puede ser null o vacia");
    }

    // Precio base
    @ParameterizedTest
    @ValueSource(doubles = {-0.01d, -100.0d})
    void Crear_PrecioBaseNegativo_LanzaExcepcion(double precioBase){
        Executable exe = () -> new Product("Primero Producto", precioBase, 18, 5, LocalDate.now().plusDays(2));
        assertThrowsIllegalArgumentException(exe, "Precio base no puede ser negativo");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void Crear_ImpuestoNeativo_LaznaExcepcion(int impuesto){
        Executable exe = () -> new Product("Primero Producto", 60.0, impuesto, 5, LocalDate.now().plusDays(2));
        assertThrowsIllegalArgumentException(exe, "El impuesto no puede ser negativo");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,-100, 0})
    void Crear_UnidadesNegativas_LanzaExcepcion(int unidades){
        Executable exe = () -> new Product("Primero producto", 60.0, 18, unidades, LocalDate.now().plusDays(2));
        assertThrowsIllegalArgumentException(exe, "Las unidades deben ser positivas");
    }

    @ParameterizedTest
    @NullSource
    void Crear_FechaNull_LanzaExcepcion(LocalDate fechaVencimiento){
        Executable e = () -> new Product("Primero producto", 60.0, 18, 1, fechaVencimiento);
        assertThrowsIllegalArgumentException(e, "Fecha de vencimiento no puede ser null");
    }

    //Fecha de vencimiento
    @ParameterizedTest
    @ValueSource(ints = {-1, -100, 0})
    void Crear_FechasInvalidas_LaznaExcepcion(int dias){
        LocalDate fecha = LocalDate.now().plusDays(dias);
        Executable exe = () -> new Product("Primero producto", 60.0, 18, 1, fecha);
        assertThrowsIllegalArgumentException(exe, "La fecha de vencimiento debe ser futura");
    }

    //Precio base e impuestos
    @ParameterizedTest
    @CsvSource({
            "-0.01, 18, 'Precio base no puede ser negativo'",
            "-100, 18, 'Precio base no puede ser negativo'",
            "100, -1, 'El impuesto no puede ser negativo'",
            "100, -100, 'El impuesto no puede ser negativo'",
            "-10, -5, 'Precio base no puede ser negativo'"
    })
    void testFallaCrearProduct_bpNegativo(double precioBase , int impuesto, String mensaje){
        Executable exe = () -> new Product("Primero producto", precioBase, impuesto, 18, LocalDate.now().plusDays(2));
        assertThrowsIllegalArgumentException(exe, mensaje);
    }

    //No valida valores nulos de los strings o de objetos
    //En la parte de la fehca podemos agregar un valor entero negativo, y dentor del metodo con LocalDate.now().PlusDays(valor), crearemos las fechas
    //Es mucho más flexible, sobretodo al cambio de la fecha actual fallará el test
    @ParameterizedTest
    @CsvSource({
            "'', 100.0, 18 ,1, '2026-12-15', 'Descripcion no puede ser null o vacia'",
            "'     ', 100.0, 18 ,1, '2026-12-15', 'Descripcion no puede ser null o vacia'",
            "'Primer producto', -1.0, 18 , 1, '2026-12-15', 'Precio base no puede ser negativo'",
            "'Primer producto', -100.0, 18 , 1, '2026-12-15', 'Precio base no puede ser negativo'",
            "'Primer producto', 100.0, -1 , 1, '2026-12-15', 'El impuesto no puede ser negativo'",
            "'Primer producto', 100.0, -100 , 1, '2026-12-15', 'El impuesto no puede ser negativo'",
            "'Primer producto', 100.0, 18 , -1, '2026-12-15', 'Las unidades deben ser positivas'",
            "'Primer producto', 100.0, 18 , -100, '2026-12-15', 'Las unidades deben ser positivas'",
            "'Primer producto', 100.0, 18 , 0, '2026-12-15', 'Las unidades deben ser positivas'",
            "'Primer producto', 100.0, 18 ,1, '2026-06-21', 'La fecha de vencimiento debe ser futura'",
            "'Primer producto', 100.0, 18 ,1, '2025-12-15', 'La fecha de vencimiento debe ser futura'",
            "'Primer producto', 100.0, 18 ,1, '2026-06-22', 'La fecha de vencimiento debe ser futura'",
    })
    public void crearProducto_AtributoInvalido_RetornaExcepcion_PrimeraForma(String descripcion, double precioBase, int impuesto, int unidades, String fecha, String mensaje){
        LocalDate fechaVencimiento = LocalDate.parse(fecha);
        Executable exe = () -> new Product(descripcion, precioBase, impuesto, unidades, fechaVencimiento);
        assertThrowsIllegalArgumentException(exe, mensaje);
    }



    public static Stream<Arguments> ProductosInvalidos(){
        return Stream.of(
                Arguments.of(null, 100.0, 18 ,1, 20, "Descripcion no puede ser null o vacia"),
                Arguments.of("", 100.0, 18 ,1, 20, "Descripcion no puede ser null o vacia"),
                Arguments.of("     ", 100.0, 18 ,1, 3, "Descripcion no puede ser null o vacia"),
                Arguments.of("Primer producto", -1.0, 18 , 1, 2, "Precio base no puede ser negativo"),
                Arguments.of("Primer producto", -100.0, 18 , 1, 2, "Precio base no puede ser negativo"),
                Arguments.of("Primer producto", 100.0, -1 , 1, 2, "El impuesto no puede ser negativo"),
                Arguments.of("Primer producto", 100.0, -100 , 1, 2, "El impuesto no puede ser negativo"),
                Arguments.of("Primer producto", 100.0, 18 , -1, 2, "Las unidades deben ser positivas"),
                Arguments.of("Primer producto", 100.0, 18 , -100, 2, "Las unidades deben ser positivas"),
                Arguments.of("Primer producto", 100.0, 18 , 0, 2, "Las unidades deben ser positivas"),
                Arguments.of("Primer producto", 100.0, 18 , 2, -1, "La fecha de vencimiento debe ser futura"),
                Arguments.of("Primer producto", 100.0, 18 , 2, -100, "La fecha de vencimiento debe ser futura"),
                Arguments.of("Primer producto", 100.0, 18 , 2, 0, "La fecha de vencimiento debe ser futura")
        );
    }

    @ParameterizedTest
    @MethodSource("ProductosInvalidos")
    public void crearProducto_AtributosInvalido_RetornaExcepcion_SegundaForma(String descripcion, double precioBase, int impuesto, int unidades, int days, String mensaje){
        LocalDate fechaVencimiento = LocalDate.now().plusDays(days);
        Executable exe = () -> new Product(descripcion, precioBase, impuesto, unidades, fechaVencimiento);
        assertThrowsIllegalArgumentException(exe, mensaje);
    }


    //Util
    private static void assertThrowsIllegalArgumentException(Executable exe, String mensaje){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,  exe);
        assertEquals(mensaje, e.getMessage());
    }

    //@ValueSource solo admite tipos de datos primitivos y soporta solo un único parametro, por ende solo podemos  pasarle un unico argumento
}

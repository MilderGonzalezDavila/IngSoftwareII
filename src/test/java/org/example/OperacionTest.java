package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperacionTest {

    @Test
    public void probarSuma(){
        int x = 10;
        int y = 25;
        Operacion op = new Operacion();

        int result = op.Sumar(x,y);

        assertEquals(35, result);
    }


    @Test
    public void probarRestar(){
        int x =  10;
        int y = 5;
        Operacion op = new Operacion();

        assertEquals(5, op.Restar(x, y));
    }


    @Test
    public void probarMultiplicar(){
        int x =  10;
        int y = 5;
        Operacion op = new Operacion();

        assertEquals(50, op.Multiplicar(x, y));
    }


    @Test
    public void probarDividir(){
        int x = 10;
        int y = 5;
        Operacion op = new Operacion();

        int result = op.Dividir(x,y);

        assertEquals(2, result);
    }

    @Test
    public void probarDivisionCero(){
        int x = 10;
        int y = 0;
        Operacion op = new Operacion();
        assertThrows(ArithmeticException.class, () -> op.Dividir(x,y));
    }


}

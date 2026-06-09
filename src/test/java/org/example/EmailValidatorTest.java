package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {

    private EmailValidator emailValido;

    @BeforeEach
    void Inicializar() {
        emailValido = new EmailValidator();
    }

    //@Test
    void testValidarEmailCorrecto() {
        String email = "recorcholis@gmail.com";
        boolean valido = emailValido.isValid(email);
        assertValido(valido);
    }

    //@Test
    void testValidarEmail_UnSoloCaracter() {
        String email = "s@gmail.com";
        boolean valido = emailValido.isValid(email);
        assertValido(valido);
    }

    //@Test
    void testValidarEmail_DominioVariosPuntos() {
        String email = "usuario@unc.edu.pe";
        boolean valido = emailValido.isValid(email);
        assertValido(valido);
    }

    @ParameterizedTest
    @ValueSource(strings = {"recorcholis@gmail.com", "s@gmail.com", "usuario@unc.edu.pe"})
    void testValidateEmail(String email) {
        boolean valido = emailValido.isValid(email);
        assertValido(valido);
    }

    @ParameterizedTest
    @MethodSource("testValidateEmailArguments")
    void testValidatesEmails(String email, boolean expectedResult) {
        boolean result = emailValido.isValid(email);
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> testValidateEmailArguments() {
        return Stream.of(Arguments.of(
                "usuario@email.com", true),
                Arguments.of("a@email.com", true),
                Arguments.of("a@b.com", false),
                Arguments.of("    ", false)
                );
    }

    //@Test
    void testValidarEmail_null(){
        String email = null;
        boolean valido = emailValido.isValid(email);
        assertNotValido(valido);
    }

    @Test
    void testValidarEmail_Vacio(){
        String email = "";
        boolean valido = emailValido.isValid(email);
        assertNotValido(valido);
    }

    @Test
    void testValidarEmail_emailError(){
        String email = "usuariounc.edu.pe";
        boolean valido = emailValido.isValid(email);
        assertNotValido(valido);
    }

    //Reemplaza a
    //testValidateEmail
    //testNotValidateEmail

    @Test
    void testThrowIllegalArgumentException_emailNull_TryCatchIdiom(){
        String emailNull = null;
        try{
            emailValido.isValid(emailNull);
            fail("Should throw IllegalArgumentException");
        }
        catch (IllegalArgumentException e){
            assertPreconditionMessage(e);
        }
    }

    @Test
    void testThrowIllegalArgumentException_emailEmpty_assertThrowsMethod(){
        String emailNull = null;
        Executable exe = () -> emailValido.isValid(emailNull);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, exe);
        assertPreconditionMessage(e);
    }

    private void assertPreconditionMessage(IllegalArgumentException e){
        assertEquals(
                "The email to validate cannot to be null",
                e.getMessage(),
                "The message of the exception is not the expected"
        );
    }

    private void assertValido(boolean valido){
        assertTrue(valido, "El email debe ser valido");
    }

    private void assertNotValido(boolean valido){
        assertFalse(valido, "El email no debe ser valido");
    }

}

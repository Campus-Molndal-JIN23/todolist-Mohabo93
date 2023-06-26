package org.campusmolndal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MenyTest {
    private Meny meny;
    private TodoFacade todoFacade;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        todoFacade = mock(TodoFacade.class);
        meny = new Meny(todoFacade);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void displayMenu_shouldPrintMenuOptions() {
        // Arrange
        String expectedOutput = "============ TODO MENY ============\n" +
                "1. Lägg till en Todo\n" +
                "2. Visa alla Todos\n" +
                "3. Visa en specifik Todo\n" +
                "4. Uppdatera en Todos text\n" +
                "5. Uppdatera en Todos status (done)\n" +
                "6. Ta bort en Todo\n" +
                "0. Avsluta\n" +
                "===================================";

        // Act
        meny.displayMeny();
        String actualOutput = outputStream.toString().trim();

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }
}
package muon.app.updater;

import org.junit.Test;

import static org.junit.Assert.*;

public class VersionEntryTest {

    @Test
    public void testGetNumericValue() {
        String version = "v1.0.5";
        VersionEntry ve = new VersionEntry(version);
        assertEquals(105,ve.getNumericValue());
    }

    //Mutation TESTS
    @Test
    public void testGetNumericValue_MutateBoundaryCondition() {
        VersionEntry entry = new VersionEntry("v1.2.0"); // Include a trailing zero

        // Simulate mutating the loop condition ('>=' to '>')
        int value = 0;
        int multiplier = 1;
        String arr[] = entry.getTag_name().substring(1).split("\\.");
        for (int i = arr.length - 1; i > 0; i--) { // Modified condition
            value += Integer.parseInt(arr[i]) * multiplier;
            multiplier *= 10;
        }

        assertNotEquals(value, entry.getNumericValue()); // Expect handling of last component
    }

    @Test
    public void testGetNumericValue_MutateErrorHandling() {
        VersionEntry entry = new VersionEntry("v1.x.3"); // Non-numeric component

        assertThrows(NumberFormatException.class, () -> entry.getNumericValue());
    }

    @Test
    public void testGetNumericValue_MutateArrayContent() {
        VersionEntry entry = new VersionEntry("v1.2.3"); 

        // Simulate introducing a non-numeric element
        String arr[] = entry.getTag_name().substring(1).split("\\.");
        arr[0] = "xyz";  

        // This depends on how you want getNumericValue to handle this
        assertThrows(NumberFormatException.class, () -> entry.getNumericValue()); 
    }

    @Test
    public void testGetNumericValue_MutateReturnValue() {
        VersionEntry entry = new VersionEntry("v1.5.2"); 

        // Simulate a mutation where the return value is always wrong
        // String arr[] = entry.getTag_name().substring(1).split("\\.");
        int value = 0; 
        // ... (rest of the calculation)
        value = 9999; // Mutation! Force a wrong value

        assertNotEquals(value, entry.getNumericValue()); 
    }
}
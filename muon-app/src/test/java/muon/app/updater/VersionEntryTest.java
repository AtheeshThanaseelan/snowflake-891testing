package muon.app.updater;

import org.junit.Test;

import static org.junit.Assert.*;

public class VersionEntryTest {

    @Test
    public void testGetNumericValue1() {
        String version = "v1.0.5";
        VersionEntry ve = new VersionEntry(version);
        assertEquals(105,ve.getNumericValue());
    }
    @Test
    public void testGetNumericValue2() {
        String version = "v";
        VersionEntry ve = new VersionEntry(version);
        assertEquals(0,ve.getNumericValue());
    }
    @Test
    public void testGetNumericValue3() {
        String version = "v5";
        VersionEntry ve = new VersionEntry(version);
        assertEquals(5,ve.getNumericValue());
    }
}
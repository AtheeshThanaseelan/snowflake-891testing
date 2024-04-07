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
}
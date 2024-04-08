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

    @Test
    public void getNumericValue_ISP_Test() {
        // Test for valid input
        VersionEntry versionEntry1 = new VersionEntry("v1.2.3");
        assertEquals(123, versionEntry1.getNumericValue());

        // Test for input with single-digit segments
        VersionEntry versionEntry2 = new VersionEntry("v9.8.7");
        assertEquals(987, versionEntry2.getNumericValue());

        // Test for input with multi-digit segments
        VersionEntry versionEntry3 = new VersionEntry("v12.34.567");
        assertEquals(1234567, versionEntry3.getNumericValue());
    }

    @Test
    public void getNumericValue_BVA_Test() {
        // Test for minimum segment values
        VersionEntry versionEntry1 = new VersionEntry("v0.0.0");
        assertEquals(0, versionEntry1.getNumericValue());

        // Test for maximum segment values
        VersionEntry versionEntry2 = new VersionEntry("v9.99.999");
        assertEquals(9999999, versionEntry2.getNumericValue());

        // Test for minimum number of segments
        VersionEntry versionEntry3 = new VersionEntry("v1");
        assertEquals(1, versionEntry3.getNumericValue());

        // Test for maximum number of segments
        VersionEntry versionEntry4 = new VersionEntry("v9.9.9.9.9.9.9.9.9");
        assertEquals(999999999, versionEntry4.getNumericValue());
    }
}
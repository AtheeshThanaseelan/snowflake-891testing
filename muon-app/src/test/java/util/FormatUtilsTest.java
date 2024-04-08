package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormatUtilsTest {

    @Test
    public void humanReadableByteCount() {
        String bc = FormatUtils.humanReadableByteCount(65432,false);
        assertEquals("63.9 Ki",bc);
    }

    @Test
    public void testHumanReadableByteCount_ValidInput() {
        // Valid input: bytes >= 0
        assertEquals("0 B", FormatUtils.humanReadableByteCount(0, true));
        assertEquals("1.0 KiB", FormatUtils.humanReadableByteCount(1024, true));
        assertEquals("1.0 MiB", FormatUtils.humanReadableByteCount(1024 * 1024, true));
        assertEquals("1.0 GiB", FormatUtils.humanReadableByteCount(1024 * 1024 * 1024, true));
        assertEquals("1.0 TiB", FormatUtils.humanReadableByteCount(1024L * 1024 * 1024 * 1024, true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHumanReadableByteCount_InvalidInput() {
        // Invalid input: bytes < 0
        FormatUtils.humanReadableByteCount(-1, true);
    }

    @Test
    public void testHumanReadableByteCount_SIvsBinary() {
        // SI vs. Binary units
        assertEquals("1.0 KiB", FormatUtils.humanReadableByteCount(1024, false));
        assertEquals("1.0 KB", FormatUtils.humanReadableByteCount(1000, true));
    }

    @Test
    public void testHumanReadableByteCount_BoundaryValues() {
        // Boundary value tests
        assertEquals("1023 B", FormatUtils.humanReadableByteCount(1023, true)); // Boundary: bytes = 1023 (Just below 1 KiB)
        assertEquals("1.0 KiB", FormatUtils.humanReadableByteCount(1024, true)); // Boundary: bytes = 1024 (1 KiB)
        assertEquals("1023.0 KiB", FormatUtils.humanReadableByteCount(1023 * 1024, true)); // Boundary: bytes = 1023 * 1024 (Just below 1 MiB)
        assertEquals("1.0 MiB", FormatUtils.humanReadableByteCount(1024 * 1024, true)); // Boundary: bytes = 1024 * 1024 (1 MiB)
        assertEquals("1023.0 MiB", FormatUtils.humanReadableByteCount(1023 * 1024 * 1024, true)); // Boundary: bytes = 1023 * 1024 * 1024 (Just below 1 GiB)
        assertEquals("1.0 GiB", FormatUtils.humanReadableByteCount(1024 * 1024 * 1024, true)); // Boundary: bytes = 1024 * 1024 * 1024 (1 GiB)
        assertEquals("1023.0 GiB", FormatUtils.humanReadableByteCount(1023L * 1024 * 1024 * 1024, true)); // Boundary: bytes = 1023L * 1024 * 1024 * 1024 (Just below 1 TiB)
        assertEquals("1.0 TiB", FormatUtils.humanReadableByteCount(1024L * 1024 * 1024 * 1024, true)); // Boundary: bytes = 1024L * 1024 * 1024 * 1024 (1 TiB)
        assertEquals("1023.0 TiB", FormatUtils.humanReadableByteCount(1023L * 1024 * 1024 * 1024 * 1024, true)); // Boundary: bytes = 1023L * 1024 * 1024 * 1024 * 1024 (Just below 1 PiB)
        assertEquals("1.0 PiB", FormatUtils.humanReadableByteCount(1024L * 1024 * 1024 * 1024 * 1024, true)); // Boundary: bytes = 1024L * 1024 * 1024 * 1024 * 1024 (1 PiB)
        assertEquals("1023.0 PiB", FormatUtils.humanReadableByteCount(1023L * 1024 * 1024 * 1024 * 1024 * 1024, true)); // Boundary: bytes = 1023L * 1024 * 1024 * 1024 * 1024 * 1024 (Just below 1 EiB)
        assertEquals("1.0 EiB", FormatUtils.humanReadableByteCount(1024L * 1024 * 1024 * 1024 * 1024 * 1024, true)); // Boundary: bytes = 1024L * 1024 * 1024 * 1024 * 1024 * 1024 (1 EiB)
        assertEquals("1023.0 EiB", FormatUtils.humanReadableByteCount(1023L * 1024 * 1024 * 1024 * 1024 * 1024 * 1024, true)); // Boundary: bytes = 1023L * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 (Just below 1 ZiB)
        assertEquals("1.0 ZiB", FormatUtils.humanReadableByteCount(1024L * 1024 * 1024 * 1024 * 1024 * 1024 * 1024, true)); // Boundary: bytes = 1024L * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 (1 ZiB)
        // Max long value
        assertEquals("8.0 EiB", FormatUtils.humanReadableByteCount(Long.MAX_VALUE, true)); // Boundary: bytes = Long.MAX_VALUE
    }
}

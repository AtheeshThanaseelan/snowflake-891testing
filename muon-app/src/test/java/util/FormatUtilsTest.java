package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormatUtilsTest {

    @Test
    public void humanReadableByteCount() {
        String bc = FormatUtils.humanReadableByteCount(65432,false);
        assertEquals("63.9 Ki",bc);
    }

    //Mutation TESTS
    @Test
    public void testHumanReadableByteCount() {
        // Test with bytes less than 1 KB
        assertEquals("0 B", FormatUtils.humanReadableByteCount(0, true));
        assertEquals("999 B", FormatUtils.humanReadableByteCount(999, true));
        assertEquals("0 B", FormatUtils.humanReadableByteCount(0, false));
        assertEquals("1023 B", FormatUtils.humanReadableByteCount(1023, false));

        // Test with bytes equal to 1 KB
        assertEquals("1.0 kB", FormatUtils.humanReadableByteCount(1000, true));
        assertEquals("1.0 KiB", FormatUtils.humanReadableByteCount(1024, false));

        // Test with bytes greater than 1 KB
        assertEquals("1.0 kB", FormatUtils.humanReadableByteCount(1001, true));
        assertEquals("1.0 KiB", FormatUtils.humanReadableByteCount(1025, false));

        // Test with larger values
        assertEquals("1.0 MB", FormatUtils.humanReadableByteCount(1000000, true));
        assertEquals("1.0 MiB", FormatUtils.humanReadableByteCount(1048576, false));
    }

    // Mutation tests
    @Test
    public void testMutation_si() {
        // Mutation: Change the si flag value
        assertNotEquals("1.0 kB", FormatUtils.humanReadableByteCount(1000, false));
    }

    @Test
    public void testMutation_exp() {
        // Mutation: Change the exp value
        assertNotEquals("1.0 kB", FormatUtils.humanReadableByteCount(1024, true));
    }

    @Test
    public void testMutation_pre() {
        // Mutation: Change the pre value
        assertNotEquals("1.0 kB", FormatUtils.humanReadableByteCount(1000000, false));
    }
}
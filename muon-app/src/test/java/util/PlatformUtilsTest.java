package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlatformUtilsTest {

    @Test
    public void testOpenFolder2() {
        PlatformUtils.openFolder2("/bin");
    }
}
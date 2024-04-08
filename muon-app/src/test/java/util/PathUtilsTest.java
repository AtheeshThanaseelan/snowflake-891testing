package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathUtilsTest {

    @Test
    public void combine() {
        String test = "C:\\";
        String test2 = "Windows\\explorer.exe";
        String result = PathUtils.combine(test,test2,"\\");
        assertEquals("C:\\Windows\\explorer.exe",result);
    }

    @Test
    public void getParent() {
        String test = "C:\\Windows\\explorer.exe";
        String result = PathUtils.getParent(test);
        assertEquals("C:\\Windows\\",result);
    }

    // ISP Tests for getParent

    // Test getParent with empty path
    @Test
    public void getParentWithEmptyPath() {
        String result = PathUtils.getParent("");
        assertNull(result);
    }

    // Test getParent with null path
    @Test
    public void getParentWithNullPath() {
        String result = PathUtils.getParent(null);
        assertNull(result);
    }

    // Test getParent with path containing single segment
    @Test
    public void getParentWithSingleSegmentPath() {
        String result = PathUtils.getParent("C:\\file.txt");
        assertEquals("C:\\", result);
    }

    // Test getParent with path containing multiple segments
    @Test
    public void getParentWithMultipleSegmentPath() {
        String result = PathUtils.getParent("C:\\Windows\\explorer.exe");
        assertEquals("C:\\Windows\\", result);
    }

    // BVA Tests for getParent

    // Test getParent with minimum length path
    @Test
    public void getParentWithMinLengthPath() {
        String result = PathUtils.getParent("a");
        assertNull(result);
    }

    // Test getParent with maximum length path
    @Test
    public void getParentWithMaxLengthPath() {
        StringBuilder maxPath = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            maxPath.append("a"); // Create a path of maximum length (256 characters)
        }
        String result = PathUtils.getParent(maxPath.toString());
        assertEquals(maxPath.substring(0, maxPath.length() - 1), result);
    }


        // ISP Tests

    // Test combining paths with empty segments
    @Test
    public void combineWithEmptySegments() {
        String result = PathUtils.combine("C:\\", "", "\\");
        assertEquals("C:\\", result);
    }

    // Test combining paths with null segments
    @Test
    public void combineWithNullSegments() {
        String result = PathUtils.combine(null, "Windows\\explorer.exe", "\\");
        assertNull(result);
    }

    // Test combining paths with minimum length segments
    @Test
    public void combineWithMinLengthSegments() {
        String result = PathUtils.combine("C:\\", "a", "\\");
        assertEquals("C:\\a", result);
    }

    // Test combining paths with maximum length segments
    @Test
    public void combineWithMaxLengthSegments() {
        StringBuilder maxSegment = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            maxSegment.append("a"); // Create a segment of maximum length (256 characters)
        }
        String result = PathUtils.combine("C:\\", maxSegment.toString(), "\\");
        assertEquals("C:\\" + maxSegment.toString(), result);
    }

    // BVA Tests

    // Test combining paths with minimum length
    @Test
    public void combineWithMinLengthPath() {
        String result = PathUtils.combine("", "Windows\\explorer.exe", "\\");
        assertEquals("Windows\\explorer.exe", result);
    }

    // Test combining paths with maximum length
    @Test
    public void combineWithMaxLengthPath() {
        StringBuilder maxPath = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            maxPath.append("a"); // Create a path of maximum length (256 characters)
        }
        String result = PathUtils.combine(maxPath.toString(), "explorer.exe", "\\");
        assertEquals(maxPath.toString() + "\\explorer.exe", result);
    }

    // Test combining paths with Unix separator
    @Test
    public void combineUnixWithUnixSeparator() {
        String result = PathUtils.combineUnix("/usr", "bin");
        assertEquals("/usr/bin", result);
    }

    // Test combining paths with Windows separator
    @Test
    public void combineWinWithWinSeparator() {
        String result = PathUtils.combineWin("C:\\", "Windows\\explorer.exe");
        assertEquals("C:\\Windows\\explorer.exe", result);
    }

    // Test combining paths with mixed separators
    @Test
    public void combineWithMixedSeparators() {
        String result = PathUtils.combineUnix("/usr", "bin\\test");
        assertEquals("/usr/bin\\test", result);
    }

    //Mutation Tests
    @Test
    public void testMutation() {
        
        // Mutation test cases:
        
        // Mutation: Change the condition to always return null
        assertEquals(null, PathUtils.getParent("/"));
        
        // Mutation: Change the substring indices to result in an incorrect parent path
        assertEquals("/path/", PathUtils.getParent("/path/to/parent/child"));
        
        // Mutation: Add 1 to the index variable to create an off-by-one error
        assertEquals("/path/to/par", PathUtils.getParent("/path/to/parent/child"));
        
        // Mutation: Swap the conditions in the if statement to always return an incorrect parent path
        assertEquals("/path/to/parent/chil", PathUtils.getParent("/path/to/parent/child"));
    }

    @Test
    public void testCombine() {
        // Test case 1: Normal case with Unix separator "/"
        assertEquals("path1/path2", PathUtils.combine("path1", "path2", "/"));
        
        // Test case 2: Normal case with Windows separator "\"
        assertEquals("path1\\path2", PathUtils.combine("path1", "path2", "\\"));
        
        // Test case 3: Empty path2
        assertEquals("path1/", PathUtils.combine("path1", "", "/"));
        
        // Test case 4: Empty path1
        assertEquals("/path2", PathUtils.combine("", "path2", "/"));
        
        // Test case 5: Both paths empty
        assertEquals("/", PathUtils.combine("", "", "/"));
        
        // Test case 6: Paths with trailing separators
        assertEquals("path1/path2", PathUtils.combine("path1/", "path2/", "/"));
        
        // Test case 7: Path2 starts with separator
        assertEquals("path1/path2", PathUtils.combine("path1", "/path2", "/"));
        
        // Test case 8: Path1 ends with separator
        assertEquals("path1/path2", PathUtils.combine("path1/", "path2", "/"));
        
        // Test case 9: Path1 and Path2 both end with separator
        assertEquals("path1/path2", PathUtils.combine("path1/", "path2/", "/"));
        
        // Test case 10: Path1 and Path2 both empty with Windows separator
        assertEquals("\\", PathUtils.combine("", "", "\\"));
    }

    @Test
    public void testCombineMutation() {
        // Mutation test cases
        
        // Mutate separator to empty string
        assertNotEquals("path1path2", PathUtils.combine("path1", "path2", ""));
        
        // Mutate separator to different separator
        assertNotEquals("path1\\path2", PathUtils.combine("path1", "path2", "/"));
        
        // Mutate path2 to null
        assertNotEquals("path1/", PathUtils.combine("path1", null, "/"));
        
        // Mutate path1 to null
        assertNotEquals("/path2", PathUtils.combine(null, "path2", "/"));
        
        // Mutate both paths to null
        assertNotEquals("/", PathUtils.combine(null, null, "/"));
        
        // Mutate path2 to start with separator
        assertNotEquals("path1path2", PathUtils.combine("path1", "/path2", "/"));
        
        // Mutate path1 to end with separator
        assertNotEquals("path1path2", PathUtils.combine("path1/", "path2", "/"));
        
        // Mutate both paths to end with separator
        assertNotEquals("path1path2", PathUtils.combine("path1/", "path2/", "/"));
    }
}
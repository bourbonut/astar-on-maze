import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HuntAndKillTest {

    @ParameterizedTest
    @CsvSource({
        "10, 10",
        "10, 11",
        "11, 10",
        "11, 11",
    })
    public void widthAndHeight(int width, int height) throws Exception {
        HuntAndKill obj = new HuntAndKill(width, height);
        assertEquals(width - width % 2 + 1, obj.getWidth());
        assertEquals(height - height % 2 + 1, obj.getHeight());
    }

    @ParameterizedTest
    @CsvSource({
        "10, 10",
        "10, 11",
        "11, 10",
        "11, 11",
    })
    public void mazeDims(int width, int height) throws Exception {
        HuntAndKill obj = new HuntAndKill(width, height);
        int[][] maze = obj.getMaze();
        assertEquals(width - width % 2 + 1, maze[0].length);
        assertEquals(height - height % 2 + 1, maze[1].length);
    }

    @ParameterizedTest
    @CsvSource({
        "5, 5",
        "10, 10",
        "25, 25",
        "100, 100",
        "250, 250",
    })
    public void openingAndEnding(int width, int height) throws Exception {
        HuntAndKill obj = new HuntAndKill(width, height);
        int[][] maze = obj.getMaze();
        width = obj.getWidth();
        height = obj.getHeight();
        assertEquals(0, maze[0][1]);
        assertEquals(0, maze[1][1]);
        assertEquals(0, maze[height - 1][width - 2]);
    }
}

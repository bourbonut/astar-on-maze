import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
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
        assertEquals(width - width % 2, obj.getWidth());
        assertEquals(height - height % 2, obj.getHeight());
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
}

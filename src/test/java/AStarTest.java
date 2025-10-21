import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AStarTest {

    @Test
    public void init() throws Exception {
        HuntAndKill mazeObj = new HuntAndKill(10, 10);
        int rows = mazeObj.getHeight();
        int cols = mazeObj.getWidth();
        int[][] maze = mazeObj.getMaze();
        AStar pathFinder = new AStar(mazeObj);

        Field searchAreaField = AStar.class.getDeclaredField("searchArea");
        searchAreaField.setAccessible(true);
        Node[][] searchArea = (Node[][]) searchAreaField.get(pathFinder);

        Field startField = AStar.class.getDeclaredField("start");
        startField.setAccessible(true);
        Node start = (Node) startField.get(pathFinder);

        Field targetField = AStar.class.getDeclaredField("target");
        targetField.setAccessible(true);
        Node target = (Node) targetField.get(pathFinder);

        assertEquals(0, start.getRow());
        assertEquals(1, start.getCol());

        assertEquals(rows - 1, target.getRow());
        assertEquals(cols - 2, target.getCol());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                assertTrue((maze[i][j] == 1) ? searchArea[i][j].isBlock() : !searchArea[i][j].isBlock());
            }
        }
    }

    @ParameterizedTest
    @CsvSource({ "5, 5", "10, 10", "25, 25", "100, 100", "250, 250", })
    public void findPath(int width, int height) throws Exception {
        HuntAndKill mazeObj = new HuntAndKill(width, height);
        AStar pathFinder = new AStar(mazeObj);

        List<Node> path = pathFinder.findPath();
        assertTrue(path.size() > 0);
    }
}

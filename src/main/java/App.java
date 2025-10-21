import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        HuntAndKill mazeObj = new HuntAndKill(10, 10);
        System.out.println(mazeObj);
        AStar pathFinder = new AStar(mazeObj);
        List<Node> path = pathFinder.findPath();
        if (path.size() == 0) {
            System.out.println("No path found.");
        } else {
            int[][] maze = mazeObj.getMaze();
            for (Node node : path) {
                maze[node.getRow()][node.getCol()] = 2;
            }
            System.out.println("\n" + mazeObj.toString());
        }
    }
}

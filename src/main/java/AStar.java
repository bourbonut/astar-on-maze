// Inspired by: https://github.com/marcelo-s/A-Star-Java-Implementation/blob/master/src/com/ai/astar/AStar.java

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {

    private static int DEFAULT_HV_COST = 10; // Horizontal - Vertical Cost
    private static int DEFAULT_DIAGONAL_COST = 14;

    private int hvCost;
    // private int diagonalCost;
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private Node start;
    private Node target;

    AStar(HuntAndKill mazeObj, Node start, Node target, int hvCost, int _diagonalCost) {
        int rows = mazeObj.getHeight();
        int cols = mazeObj.getWidth();
        int[][] maze = mazeObj.getMaze();
        this.hvCost = hvCost;
        // this.diagonalCost = diagonalCost;
        this.start = start;
        this.target = target;
        this.searchArea = new Node[rows][cols];
        this.closedSet = new HashSet<>();
        this.openList = new PriorityQueue<Node>((a, b) -> Integer.compare(a.getFinalCost(), b.getFinalCost()));
        this.setNodes();
        this.setBlocks(maze, rows, cols);
    }

    AStar(HuntAndKill mazeObj) {
        int rows = mazeObj.getHeight();
        int cols = mazeObj.getWidth();
        int[][] maze = mazeObj.getMaze();
        this.hvCost = DEFAULT_HV_COST;
        // this.diagonalCost = diagonalCost;
        this.start = new Node(0, 1);
        this.target = new Node(rows - 1, cols - 2);
        this.searchArea = new Node[rows][cols];
        this.closedSet = new HashSet<>();
        this.openList = new PriorityQueue<Node>((a, b) -> Integer.compare(a.getFinalCost(), b.getFinalCost()));
        this.setNodes();
        this.setBlocks(maze, rows, cols);
    }

    private void setNodes() {
        int targetRow = target.getRow();
        int targetCol = target.getCol();
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                searchArea[i][j] = new Node(i, j, targetRow, targetCol);
            }
        }
    }

    private void setBlocks(int[][] maze, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 1) {
                    this.searchArea[i][j].setBlock(true);
                }
            }
        }
    }

    public List<Node> findPath() {
        openList.add(start);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (currentNode.equals(target)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<>();
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<>();
        path.add(currentNode);
        Node parent = currentNode.getParent();
        while (parent != null) {
            path.add(0, parent);
            currentNode = parent;
            parent = currentNode.getParent();
        }
        return path;
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }

    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = searchArea[row][col];
        if (!adjacentNode.isBlock() && !closedSet.contains(adjacentNode)) {
            if (!openList.contains(adjacentNode)) {
                adjacentNode.setData(currentNode, cost);
                openList.add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    // Remove and Add the changed node, so that the
                    // PriorityQueue can sort again its contents with the modified
                    // "finalCost" value of the modified node
                    openList.remove(adjacentNode);
                    openList.add(adjacentNode);
                }
            }
        }
    }

    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < searchArea.length) {
            // if (col - 1 >= 0) {
            // // Comment this if diagonal movements are not allowed
            // checkNode(currentNode, col - 1, lowerRow, diagonalCost);
            // }
            // if (col + 1 <= searchArea[0].length) {
            // // Comment this if diagonal movements are not allowed
            // checkNode(currentNode, col + 1, lowerRow, diagonalCost);
            // }
            checkNode(currentNode, col, lowerRow, hvCost);
        }
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int middleRow = row;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, hvCost);
        }
        if (col + 1 < searchArea[0].length) {
            checkNode(currentNode, col + 1, middleRow, hvCost);
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {
            // if (col - 1 >= 0) {
            // // Comment this if diagonal movements are not allowed
            // checkNode(currentNode, col - 1, upperRow, diagonalCost);
            // }
            // if (col + 1 < searchArea[0].length) {
            // // Comment this if diagonal movements are not allowed
            // checkNode(currentNode, col + 1, upperRow, diagonalCost);
            // }
            checkNode(currentNode, col, upperRow, hvCost);
        }
    }
}

// Inspired by: https://github.com/marcelo-s/A-Star-Java-Implementation/blob/master/src/com/ai/astar/Node.java

public class Node {

    private int cost;
    private int heuristicValue;
    private int finalCost;

    private int row;
    private int col;
    private boolean isBlock;
    private Node parent;

    Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    Node(int row, int col, int targetNodeRow, int targetNodeCol) {
        this.row = row;
        this.col = col;
        this.calculateHeuristic(targetNodeRow, targetNodeCol);
    }

    private void calculateHeuristic(int otherRow, int otherCol) {
        this.heuristicValue = Math.abs(otherRow - row) + Math.abs(otherCol - col);
    }

    public void setData(Node currentNode, int cost) {
        this.cost = currentNode.getCost() + cost;
        this.parent = currentNode;

        // Calculate final cost
        this.finalCost = this.cost + this.heuristicValue;
    }

    public boolean checkBetterPath(Node currentNode, int cost) {
        if (currentNode.getCost() + cost < this.cost) {
            this.setData(currentNode, cost);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        Node other = (Node) obj;
        return row == other.getRow() && col == other.getCol();
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getCost() {
        return cost;
    }

    public int getFinalCost() {
        return finalCost;
    }

    public Node getParent() {
        return parent;
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    @Override
    public String toString() {
        String formatString = "Node{'row': %d, 'col': %d, 'isBlock': %b, 'heuristicValue': %d, 'cost': %d, 'finalCost': %d}";
        return String.format(formatString, row, col, isBlock, heuristicValue, cost, finalCost);
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NodeTest {

    @Test
    public void initNoTarget() {
        Node node = new Node(10, 12);
        assertEquals(10, node.getRow());
        assertEquals(12, node.getCol());
        assertEquals(0, node.getCost());
        assertEquals(0, node.getFinalCost());
        assertEquals(null, node.getParent());
    }

    @Test
    public void initTarget() {
        Node node = new Node(10, 12, 0, 0);
        assertEquals(10, node.getRow());
        assertEquals(12, node.getCol());
        assertEquals(0, node.getCost());
        assertEquals(0, node.getFinalCost());
        assertEquals(null, node.getParent());
    }

    @Test
    public void finalCostNoTargetNullCost() {
        Node currentNode = new Node(1, 1);
        Node node = new Node(10, 12);
        assertEquals(0, node.getCost());
        assertEquals(0, node.getFinalCost());
        node.setData(currentNode, 10);
        assertEquals(10, node.getCost());
        assertEquals(10, node.getFinalCost());
    }

    @Test
    public void finalCostTargetNullCost() {
        Node currentNode = new Node(1, 1);
        Node node = new Node(10, 12, 2, 2);
        assertEquals(0, node.getCost());
        assertEquals(0, node.getFinalCost());
        node.setData(currentNode, 10);
        assertEquals(10, node.getCost());
        assertEquals(10 + (10 - 2) + (12 - 2), node.getFinalCost());
    }

    @Test
    public void finalCostNoTargetNonNullCost() {
        Node currentNode = new Node(1, 1);
        currentNode.setData(currentNode, 15);
        assertEquals(15, currentNode.getCost());
        Node node = new Node(10, 12);
        assertEquals(0, node.getCost());
        assertEquals(0, node.getFinalCost());
        node.setData(currentNode, 10);
        assertEquals(25, node.getCost());
        assertEquals(25, node.getFinalCost());
    }

    @Test
    public void finalCostTargetNonNullCost() {
        Node currentNode = new Node(1, 1);
        currentNode.setData(currentNode, 15);
        assertEquals(15, currentNode.getCost());
        Node node = new Node(10, 12, 2, 2);
        assertEquals(0, node.getCost());
        assertEquals(0, node.getFinalCost());
        node.setData(currentNode, 10);
        assertEquals(25, node.getCost());
        assertEquals(25 + (10 - 2) + (12 - 2), node.getFinalCost());
    }

    @Test
    public void checkBetterPathFalse() {
        Node currentNode = new Node(1, 1);
        currentNode.setData(currentNode, 15);
        assertEquals(15, currentNode.getCost());
        Node node = new Node(10, 12);
        assertEquals(0, node.getCost());
        assertEquals(0, node.getFinalCost());
        assertTrue(!node.checkBetterPath(currentNode, 10));
        assertEquals(0, node.getCost());
        assertEquals(0, node.getFinalCost());
    }

    @Test
    public void checkBetterPathTrue() {
        Node currentNode = new Node(1, 1);
        currentNode.setData(currentNode, 5);
        assertEquals(5, currentNode.getCost());
        Node node = new Node(10, 12);
        node.setData(node, 100);
        assertEquals(100, node.getCost());
        assertEquals(100, node.getFinalCost());
        assertTrue(node.checkBetterPath(currentNode, 10));
        assertEquals(15, node.getCost());
        assertEquals(15, node.getFinalCost());
    }

    @Test
    public void equals() {
        Node node = new Node(1, 2);
        Node target = new Node(1, 2, 3, 4);
        assertTrue(node.equals(target));
        assertTrue(node != target);
    }
}

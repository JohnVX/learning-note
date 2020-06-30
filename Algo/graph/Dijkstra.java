package graph;

import java.util.*;

/**
 * 单源最短路径方法
 * Let the node at which we are starting be called the initial node. Let the distance of node Y be the distance from the initial node to Y.
 * Dijkstra's algorithm will assign some initial distance values and will try to improve them step by step.
 *
 * 1.Mark all nodes unvisited. Create a set of all the unvisited nodes called the unvisited set.
 *
 * 2.Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other nodes. Set the initial node as current.
 *
 * 3.For the current node, consider all of its unvisited neighbours and calculate their tentative distances through the current node.
 * Compare the newly calculated tentative distance to the current assigned value and assign the smaller one.
 * For example, if the current node A is marked with a distance of 6, and the edge connecting it with a neighbour B has length 2, then the distance to B through A will be 6 + 2 = 8.
 * If B was previously marked with a distance greater than 8 then change it to 8. Otherwise, the current value will be kept.
 *
 * 4.When we are done considering all of the unvisited neighbours of the current node, mark the current node as visited and remove it from the unvisited set. A visited node will never be checked again.
 *
 * 5.If the destination node has been marked visited (when planning a route between two specific nodes)
 * or if the smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal;
 * occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. The algorithm has finished.
 *
 * 6.Otherwise, select the unvisited node that is marked with the smallest tentative distance, set it as the new "current node", and go back to step 3.
 */
public class Dijkstra {
    private static class Graph{
        private static class Node{
            private String vertex;
            private Edge firstEdge;
            private Node(String vertex){
                this.vertex = vertex;
                this.firstEdge = null;
            }
            @Override
            public String toString() {
                return "Node{" +
                        "vertex='" + vertex + '\'' +
                        ", firstEdge=" + firstEdge +
                        '}';
            }
        }
        private static class Edge{
            private Node node;
            private int value;
            private Edge nextEdge;
            Edge(Node node, int value, Edge nextEdge) {
                this.node = node;
                this.value = value;
                this.nextEdge = nextEdge;
            }
            @Override
            public String toString() {
                return "Edge{" +
                        "node=" + node.vertex +
                        ", value=" + value +
                        ", nextEdge=" + nextEdge +
                        '}';
            }
        }
        private List<Node> nodes;
        private Node initialNode;
        private Node destinationNode;
        private Map<Node, Integer> findShortestPath(){
            Map<Node, Integer> map = new HashMap<>(nodes.size());
            Map<Node, Integer> unVisitedMap = new HashMap<>(nodes.size());
            for(Node node: nodes){
                if(node == initialNode){
                    map.put(node, 0);
                }else {
                    map.put(node, Integer.MAX_VALUE);
                }
            }
            unVisitedMap.putAll(map);
            Node currentNode = initialNode;
            while (!unVisitedMap.isEmpty()){
                Edge neighbourEdge = currentNode.firstEdge;
                while (neighbourEdge != null){
                    if(unVisitedMap.keySet().contains(neighbourEdge.node)){
                        int newDistance = map.get(currentNode) + neighbourEdge.value;
                        if(newDistance < map.get(neighbourEdge.node)){
                            unVisitedMap.put(neighbourEdge.node, newDistance);
                            map.put(neighbourEdge.node, newDistance);
                        }
                    }
                    neighbourEdge = neighbourEdge.nextEdge;
                }
                unVisitedMap.remove(currentNode);
                Node minUnVisitedNode = null;
                for(Node node: unVisitedMap.keySet()){
                    if(minUnVisitedNode == null || unVisitedMap.get(node) < unVisitedMap.get(minUnVisitedNode)) {
                        minUnVisitedNode = node;
                    }
                }
                if(minUnVisitedNode == null || currentNode == destinationNode || unVisitedMap.get(minUnVisitedNode) >= Integer.MAX_VALUE){
                    return map;
                }
                currentNode = minUnVisitedNode;
            }
            return map;
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.nodes = new ArrayList<>(7);

        Graph.Node nodeA = new Graph.Node("A");
        Graph.Node nodeB = new Graph.Node("B");
        Graph.Node nodeC = new Graph.Node("C");
        Graph.Node nodeD = new Graph.Node("D");
        Graph.Node nodeE = new Graph.Node("E");
        Graph.Node nodeF = new Graph.Node("F");
        Graph.Node nodeG = new Graph.Node("G");

        nodeA.firstEdge = new Graph.Edge(nodeB, 1, new Graph.Edge(nodeD, 2, new Graph.Edge(nodeE, 3, null)));
        nodeB.firstEdge = new Graph.Edge(nodeA, 1, new Graph.Edge(nodeC, 2, null));
        nodeC.firstEdge = new Graph.Edge(nodeB, 2, new Graph.Edge(nodeG, 1 , null));
        nodeD.firstEdge = new Graph.Edge(nodeA, 2, new Graph.Edge(nodeE, 6, new Graph.Edge(nodeF, 2, new Graph.Edge(nodeG, 4, null))));
        nodeE.firstEdge = new Graph.Edge(nodeA, 3, new Graph.Edge(nodeD, 6, new Graph.Edge(nodeF, 3, null)));
        nodeF.firstEdge = new Graph.Edge(nodeE, 3, new Graph.Edge(nodeD, 2, new Graph.Edge(nodeG, 2, null)));
        nodeG.firstEdge = new Graph.Edge(nodeC, 1, new Graph.Edge(nodeD, 4, new Graph.Edge(nodeF, 2, null)));

        graph.nodes.add(0, nodeA);graph.nodes.add(1, nodeB);graph.nodes.add(2, nodeC);
        graph.nodes.add(3, nodeD);graph.nodes.add(4, nodeE);graph.nodes.add(5, nodeF);
        graph.nodes.add(6, nodeG);

        graph.initialNode = graph.nodes.get(0);
        graph.destinationNode = null;
        Map<Graph.Node, Integer> pathMap = graph.findShortestPath();
        System.out.println(pathMap);
    }
}

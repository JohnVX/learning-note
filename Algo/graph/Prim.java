package graph;

import java.util.*;

/**
/**
 * 搜索最小生成树
 * 切分定理：
 * 在一幅连通加权无向图中，给定任意的切分，如有一条横切边的权值严格小于所有其他横切边，则这条边必然属于图的最小生成树
 */
public class Prim {
    static class Vertex{
        String key;
        Vertex(String key){
            this.key = key;
        }
        @Override
        public String toString(){
            return this.key;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return key.equals(vertex.key);
        }
        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
    static class Edge{
        Vertex v1;
        Vertex v2;
        int weight;
        Edge(Vertex start, Vertex end, int weight){
            this.v1 = start;
            this.v2 = end;
            this.weight = weight;
        }
    }
    private List<Vertex> vertexList = new ArrayList<>();
    private List<Edge> edgeQueue = new ArrayList<>();
    private List<Vertex> newVertex = new ArrayList<>();
    private List<Edge> newEdge = new ArrayList<>();
    private void addEdge(Vertex a, Vertex b, int weight){
        Edge edge = new Edge(a, b, weight);
        this.edgeQueue.add(edge);
    }
    private void buildGraph(){
        Vertex v1 = new Vertex("a");
        this.vertexList.add(v1);
        Vertex v2 = new Vertex("b");
        this.vertexList.add(v2);
        Vertex v3 = new Vertex("c");
        this.vertexList.add(v3);
        Vertex v4 = new Vertex("d");
        this.vertexList.add(v4);
        Vertex v5 = new Vertex("e");
        this.vertexList.add(v5);
//        addEdge(v1, v2, 6);
//        addEdge(v1, v3, 7);
//        addEdge(v2, v5, 4);
//        addEdge(v3, v4, 3);
//        addEdge(v3, v5, 9);
//        addEdge(v5, v4, 7);
//        addEdge(v4, v2, 2);

        addEdge(v1, v2, 6);
        addEdge(v1, v3, 7);
        addEdge(v2, v5, 4);
        addEdge(v3, v4, 3);
        addEdge(v3, v5, 1);
        addEdge(v5, v4, 7);
        addEdge(v4, v2, 2);
    }
    public void primTree(){
        buildGraph();
        Vertex start = vertexList.get(0);
        newVertex.add(start);
        LinkedList<Edge> edgeList = new LinkedList<>(this.edgeQueue);
        edgeList.sort(Comparator.comparingInt(e -> e.weight));
        for(int n = 0; n < vertexList.size() - 1; n++){
            Vertex tempVertex = new Vertex(start.key);
            Edge tempEdge = new Edge(start, start, Integer.MAX_VALUE);
            for (Edge e : edgeList) {
                if ((newVertex.contains(e.v1) && !newVertex.contains(e.v2)) || (newVertex.contains(e.v2) && !newVertex.contains(e.v1))) {
                    if (!newVertex.contains(e.v2)) {
                        tempVertex = e.v2;
                    } else if (!newVertex.contains(e.v1)) {
                        tempVertex = e.v1;
                    }
                    tempEdge = e;
                    break;
                }
            }
            edgeList.remove(tempEdge);
            newVertex.add(tempVertex);
            newEdge.add(tempEdge);
        }
        for (Edge edge : newEdge) {
            System.out.println(edge.v1 + "-" + edge.v2 + ": " + edge.weight);
        }
    }
}

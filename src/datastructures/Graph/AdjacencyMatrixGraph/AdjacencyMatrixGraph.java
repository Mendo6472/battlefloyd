package datastructures.Graph.AdjacencyMatrixGraph;

import datastructures.Graph.AdjacencyListGraph.AdjacencyListGraph;
import datastructures.Graph.Graph.*;
import datastructures.NaryTree.NaryTree;
import datastructures.NaryTree.Node;
import datastructures.PriorityQueue.Heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AdjacencyMatrixGraph<V> implements IGraph<V> {

    ArrayList<Vertex<V>> vertexList;

    ArrayList<ArrayList<Double>> adjacencyMatrixGraph;

    boolean addressed;
    boolean weighted;
    int time;


    public AdjacencyMatrixGraph(boolean addressed, boolean weighted){
        vertexList = new ArrayList<>();
        adjacencyMatrixGraph = new ArrayList<>();
        this.addressed = addressed;
        this.weighted = weighted;
    }


    @Override
    public Vertex<V> insertVertex(Vertex<V> vertex) {
        if(!vertexList.contains(vertex)){
            vertexList.add(vertex);
            int vertexPos = vertexList.indexOf(vertex);
            adjacencyMatrixGraph.add(new ArrayList<Double>());
            if(vertexPos == 0){
                adjacencyMatrixGraph.get(0).add(Double.MAX_VALUE);
                return vertex;
            }
            for(int i = 0; i < vertexPos; i++){
                adjacencyMatrixGraph.get(i).add(Double.MAX_VALUE);
                adjacencyMatrixGraph.get(vertexPos).add(Double.MAX_VALUE);
            }
            adjacencyMatrixGraph.get(vertexPos).add(Double.MAX_VALUE);
            return vertex;
        }
        return null;
    }

    @Override
    public boolean insertEdge(Vertex<V> v1, Vertex<V> v2, double weight) {
        if(!vertexList.contains(v1) || !vertexList.contains(v2)) return false;
        int v1Pos = vertexList.indexOf(v1);
        int v2Pos = vertexList.indexOf(v2);
        if(addressed){
            if(adjacencyMatrixGraph.get(v1Pos).get(v2Pos) < Double.MAX_VALUE) return false;
            adjacencyMatrixGraph.get(v1Pos).set(v2Pos, weight);
        } else {
            if(adjacencyMatrixGraph.get(v1Pos).get(v2Pos) < Double.MAX_VALUE || adjacencyMatrixGraph.get(v2Pos).get(v1Pos) < Double.MAX_VALUE) return false;
            adjacencyMatrixGraph.get(v1Pos).set(v2Pos, weight);
            adjacencyMatrixGraph.get(v2Pos).set(v1Pos, weight);
        }
        return true;
    }

    @Override
    public boolean deleteVertex(Vertex<V> vertex) {
        if(!vertexList.contains(vertex)) return false;
        int vertexPos = vertexList.indexOf(vertex);
        vertexList.remove(vertexPos);
        adjacencyMatrixGraph.remove(vertexPos);
        for (ArrayList<Double> doubles : adjacencyMatrixGraph) {
            doubles.remove(vertexPos);
        }
        return true;
    }

    @Override
    public boolean deleteEdge(Vertex<V> v1, Vertex<V> v2) {
        if(!vertexList.contains(v1) || !vertexList.contains(v2)) return false;
        int v1Pos = vertexList.indexOf(v1);
        int v2Pos = vertexList.indexOf(v2);
        if(addressed){
            adjacencyMatrixGraph.get(v1Pos).set(v2Pos, Double.MAX_VALUE);
            return true;
        } else {
            adjacencyMatrixGraph.get(v1Pos).set(v2Pos, Double.MAX_VALUE);
            adjacencyMatrixGraph.get(v2Pos).set(v1Pos, Double.MAX_VALUE);
            return true;
        }
    }

    @Override
    public NaryTree<V> BFS(Vertex<V> s) {
        NaryTree<V> naryTree = new NaryTree<>();
        naryTree.setRoot(new Node<>(s.value));
        Queue<Vertex<V>> queue = new LinkedList<>();

        for (Vertex<V> u :vertexList) {
            u.setColor(Color.WHITE);
            u.setDistance(Integer.MAX_VALUE);
            u.setPredecessor(null);
        }
        s.setColor(Color.GRAY);
        s.setDistance(0);
        s.setPredecessor(null);
        queue.add(s);
        while(!queue.isEmpty()){
            Vertex<V> u = queue.poll();
            int uPos = vertexList.indexOf(u);
            for(int i = 0; i < adjacencyMatrixGraph.get(uPos).size(); i++){
                if(adjacencyMatrixGraph.get(uPos).get(i) < Double.MAX_VALUE){
                    if(vertexList.get(i).getColor() == Color.WHITE){
                        vertexList.get(i).setColor(Color.GRAY);
                        vertexList.get(i).setDistance(u.getDistance()+1);
                        vertexList.get(i).setPredecessor(u);
                        naryTree.insertNode(vertexList.get(i).getValue(), vertexList.get(i).getPredecessor().getValue());
                        queue.add(vertexList.get(i));
                    }
                }
            }
            u.setColor(Color.BLACK);
        }
        return naryTree;
    }

    @Override
    public ArrayList<NaryTree<V>> DFS() {
        ArrayList<NaryTree<V>> naryTrees = new ArrayList<>();
        for (Vertex<V> u: vertexList) {
            u.setColor(Color.WHITE);
            u.setPredecessor(null);
        }
        time = 0;
        for (Vertex<V> u: vertexList) {
            if (u.getColor() == Color.WHITE){
                NaryTree<V> naryTree = new NaryTree<>();
                naryTree.setRoot(new Node<V>(u.getValue()));
                DFSV(u, naryTree);
                naryTrees.add(naryTree);
            }
        }

        return naryTrees;
    }

    public void DFSV(Vertex<V> u, NaryTree<V> n) {
        time += 1;
        u.setDistance(time);
        u.setColor(Color.GRAY);
        int uPos = vertexList.indexOf(u);
        for(int i = 0; i < adjacencyMatrixGraph.get(uPos).size(); i++) {
            if(adjacencyMatrixGraph.get(uPos).get(i) < Double.MAX_VALUE) {
                if(vertexList.get(i).getColor() == Color.WHITE){
                    vertexList.get(i).setPredecessor(u);
                    n.insertNode(vertexList.get(i).getValue(), u.getValue());
                    DFSV(vertexList.get(i),n);
                }
            }
        }
        u.setColor(Color.BLACK);
        time = time + 1;
    }

    @Override
    public DijkstraResult<V> dijkstra (Vertex<V> source){
        ArrayList<Vertex<V>> previous = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();
        Heap<Double, Vertex<V>> queue = new Heap<>();
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i) != source){
                distances.add(Double.MAX_VALUE);
            }else{
                distances.add(0.0);
            }
            previous.add(null);
            queue.insert(distances.get(i),vertexList.get(i));
        }
        queue.buildMinHeap();

        while (queue.getHeapSize()>0){
            Vertex<V> u = queue.extractMin().getValue();
            int uPos = vertexList.indexOf(u);
            for(int i = 0; i < vertexList.size(); i++){
                if(adjacencyMatrixGraph.get(uPos).get(i) < Double.MAX_VALUE){
                    double alt = distances.get(uPos) + adjacencyMatrixGraph.get(uPos).get(i);
                    if(alt < distances.get(i)){
                        distances.set(i, alt);
                        previous.set(i, u);
                        queue.decreaseKey(queue.searchByValue(vertexList.get(i)), alt);
                    }
                    queue.buildMinHeap();
                }

            }
        }
        return new DijkstraResult<V>(previous, distances);
    }

    public void showMatrix(){
        for(int i = 0; i < vertexList.size(); i++){
            for(int j = 0; j < vertexList.size(); j++){
                if(adjacencyMatrixGraph.get(i).get(j) == Double.MAX_VALUE){
                    System.out.print("[inf]");
                } else {
                    System.out.print("[" + adjacencyMatrixGraph.get(i).get(j)+ "]");
                }
            }
            System.out.println(" ");
        }
    }
}

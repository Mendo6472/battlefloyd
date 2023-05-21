package datastructures.Graph;

import datastructures.NaryTree.NaryTree;
import datastructures.NaryTree.Node;
import datastructures.PriorityQueue.Heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<V> implements IGraph<V>{
    boolean addressed;
    boolean weighted;
    ArrayList<Vertex<V>> vertexList;
    
    int time;

    public Graph(boolean addressed, boolean weighted) {
        vertexList = new ArrayList<>();
        this.addressed = addressed;
        this.weighted = weighted;
    }


    @Override
    public Vertex<V> insertVertex(Vertex<V> vertex) {
        if(!vertexList.contains(vertex)){
            vertexList.add(vertex);
            return vertex;
        }
        return null;
    }

    @Override
    public boolean insertEdge(Vertex<V> v1, Vertex<V> v2, double weight) {
        Pair<V> pairV1 = new Pair<>(v1,weight);
        Pair<V> pairV2 = new Pair<>(v2,weight);

        if(addressed){
            if(!searchVertexInAdjencyList(v1,v2)){
                v2.getAdjacencyList().add(pairV1);
                return true;
            }
        }else{
            if(!searchVertexInAdjencyList(v1,v2) && !searchVertexInAdjencyList(v2,v1) ){
                v1.getAdjacencyList().add(pairV2);
                v2.getAdjacencyList().add(pairV1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteVertex(Vertex<V> vertex) {
        if (vertexList.contains(vertex)){
            for (Vertex<V> v: vertexList) {
                if (v != vertex){
                    if(searchVertexInAdjencyList(v, vertex)){
                        v.getAdjacencyList().remove(searchPosVertexInAdjencyList(v,vertex));
                    }
                }
            }
            vertexList.remove(vertex);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean deleteEdge(Vertex<V> v1, Vertex<V> v2) {
        if(addressed){
            if(searchVertexInAdjencyList(v1,v2)){
                v1.getAdjacencyList().remove(searchPosVertexInAdjencyList(v1,v2));
                return true;
            }else{
                return false;
            }
            
        }else{
            if(searchVertexInAdjencyList(v1,v2) && searchVertexInAdjencyList(v1,v2)){
                v1.getAdjacencyList().remove(searchPosVertexInAdjencyList(v1,v2));
                v2.getAdjacencyList().remove(searchPosVertexInAdjencyList(v2,v1));
                return true;
            }else{
                return false;
            }
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
            for (Pair<V> p : u.getAdjacencyList()){
                if(p.getVertex().getColor() == Color.WHITE){
                    p.getVertex().setColor(Color.GRAY);
                    p.getVertex().setDistance(u.getDistance()+1);
                    p.getVertex().setPredecessor(u);
                    naryTree.insertNode(p.getVertex().getValue(),p.getVertex().getPredecessor().getValue());
                    queue.add(p.getVertex());
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
    @Override
    public void DFSV(Vertex<V> u, NaryTree<V> n) {
        time += 1;
        u.setDistance(time);
        u.setColor(Color.GRAY);
        for (Pair<V> p: u.getAdjacencyList()){
            if(p.getVertex().getColor() == Color.WHITE){
                p.getVertex().setPredecessor(u);
                n.insertNode(p.getVertex().getValue(), u.getValue());
                DFSV(p.getVertex(),n);
            }
        }
        u.setColor(Color.BLACK);
        time = time + 1;
    }

    boolean searchVertexInAdjencyList(Vertex<V> vertex, Vertex<V> vertexToSearch){
        for (Pair<V> pair: vertex.adjacencyList) {
            if( pair.getVertex() == vertexToSearch){
                return true;
            }
        }
        return false;
    }

    int searchPosVertexInAdjencyList(Vertex<V> vertex, Vertex<V> vertexToSearch){
        for (int i = 0; i < vertex.adjacencyList.size(); i++) {
            if( vertex.getAdjacencyList().get(i).getVertex() == vertexToSearch){
                return i;
            }
        }
        return -1;
    }

    public class DijkstraResult {
        private final ArrayList<Vertex<V>> previous;
        private final ArrayList<Double> distances;

        public DijkstraResult(ArrayList<Vertex<V>>previous, ArrayList<Double> distances) {
            this.previous = previous;
            this.distances = distances;
        }

        public  ArrayList<Vertex<V>> getPrevious() {
            return previous;
        }

        public ArrayList<Double>  getDistances() {
            return distances;
        }
    }

    public DijkstraResult dijkstra (Vertex<V> source){
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
            for (Pair<V> pair : u.getAdjacencyList()) {
                double alt = distances.get(vertexList.indexOf(u)) + pair.getWeight();
                if (alt < distances.get(vertexList.indexOf(pair.getVertex()))){
                    distances.set(vertexList.indexOf(pair.getVertex()),alt);
                    previous.set(vertexList.indexOf(pair.getVertex()),u);
                    queue.decreaseKey(queue.searchByValue(pair.getVertex()),alt);
                }
                queue.buildMinHeap();

            }
        }
        return new DijkstraResult(previous, distances);
    }



}

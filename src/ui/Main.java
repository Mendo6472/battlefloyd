package ui;

import java.util.ArrayList;

import datastructures.Graph.AdjacencyMatrixGraph.AdjacencyMatrixGraph;
import datastructures.Graph.Graph.IGraph;
import datastructures.Graph.Graph.Vertex;
import datastructures.NaryTree.NaryTree;

public class Main {
    public static void main(String[] args) {
        IGraph<String> adjacencyListGraph = new AdjacencyMatrixGraph<>(false, false);
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        Vertex<String> vertex4 = new Vertex<>("D");
        Vertex<String> vertex5 = new Vertex<>("E");
        Vertex<String> vertex6 = new Vertex<>("F");
        Vertex<String> vertex7 = new Vertex<>("G");
        Vertex<String> vertex8 = new Vertex<>("H");

        adjacencyListGraph.insertVertex(vertex1);
        adjacencyListGraph.insertVertex(vertex2);
        adjacencyListGraph.insertVertex(vertex3);
        adjacencyListGraph.insertVertex(vertex4);
        adjacencyListGraph.insertVertex(vertex5);
        adjacencyListGraph.insertVertex(vertex6);
        adjacencyListGraph.insertVertex(vertex7);
        adjacencyListGraph.insertVertex(vertex8);

        adjacencyListGraph.insertEdge(vertex1, vertex2, 0);
        adjacencyListGraph.insertEdge(vertex1, vertex3, 0);
        adjacencyListGraph.insertEdge(vertex2, vertex4, 0);
        adjacencyListGraph.insertEdge(vertex2, vertex5, 0);
        adjacencyListGraph.insertEdge(vertex3, vertex6, 0);
        adjacencyListGraph.insertEdge(vertex3, vertex7, 0);

        NaryTree<String> naryTreeBFS = adjacencyListGraph.BFS(vertex3);
        String preorder;
        preorder = naryTreeBFS.preorder(naryTreeBFS.getRoot());
        System.out.println("Preorder of BFS tree: " + preorder);

        ArrayList<NaryTree<String>> naryTreeDFS = adjacencyListGraph.DFS();
        for (NaryTree<String> naryTree : naryTreeDFS) {
            preorder = naryTree.preorder(naryTree.getRoot());
            System.out.println("Preorder of DFS tree: " + preorder);
        }

        


    }
}
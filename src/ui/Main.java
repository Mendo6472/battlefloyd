package ui;

import java.util.ArrayList;

import datastructures.Graph.Graph;
import datastructures.Graph.Vertex;
import datastructures.NaryTree.NaryTree;
import datastructures.NaryTree.Node;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>(false, false);
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        Vertex<String> vertex4 = new Vertex<>("D");
        Vertex<String> vertex5 = new Vertex<>("E");
        Vertex<String> vertex6 = new Vertex<>("F");
        Vertex<String> vertex7 = new Vertex<>("G");
        Vertex<String> vertex8 = new Vertex<>("H");

        graph.insertVertex(vertex1);
        graph.insertVertex(vertex2);
        graph.insertVertex(vertex3);
        graph.insertVertex(vertex4);
        graph.insertVertex(vertex5);
        graph.insertVertex(vertex6);
        graph.insertVertex(vertex7);
        graph.insertVertex(vertex8);

        graph.insertEdge(vertex1, vertex2, 0);
        graph.insertEdge(vertex1, vertex3, 0);
        graph.insertEdge(vertex2, vertex4, 0);
        graph.insertEdge(vertex2, vertex5, 0);
        graph.insertEdge(vertex3, vertex6, 0);
        graph.insertEdge(vertex3, vertex7, 0);

        NaryTree<String> naryTreeBFS = graph.BFS(vertex3);
        String preorder;
        preorder = naryTreeBFS.preorder(naryTreeBFS.getRoot());
        System.out.println("Preorder of BFS tree: " + preorder);

        ArrayList<NaryTree<String>> naryTreeDFS = graph.DFS();
        for (NaryTree<String> naryTree : naryTreeDFS) {
            preorder = naryTree.preorder(naryTree.getRoot());
            System.out.println("Preorder of DFS tree: " + preorder);
        }

        


    }
}
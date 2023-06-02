package ui;

import datastructures.Graph.*;
import datastructures.Graph.AdjacencyListGraph.AdjacencyListGraph;
import datastructures.Graph.AdjacencyMatrixGraph.AdjacencyMatrixGraph;
import datastructures.Graph.Graph.DijkstraResult;
import datastructures.Graph.Graph.IGraph;
import datastructures.Graph.Graph.Vertex;
import datastructures.NaryTree.NaryTree;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(false, true);
        Vertex<String> vertexP = new Vertex<>("C");
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex4 = new Vertex<>("D");
        Vertex<String> vertex5 = new Vertex<>("E");
        Vertex<String> vertex6 = new Vertex<>("F");
        Vertex<String> vertex7 = new Vertex<>("G");
        Vertex<String> vertex8 = new Vertex<>("H");

        graph.insertVertex(vertex1);
        graph.insertVertex(vertex2);
        graph.insertVertex(vertexP);
        graph.insertVertex(vertex4);
        graph.insertVertex(vertex5);
        graph.insertVertex(vertex6);
        graph.insertVertex(vertex7);
        graph.insertVertex(vertex8);

        graph.insertEdge(vertex1, vertex2, 3);
        graph.insertEdge(vertex1, vertexP, 5);
        graph.insertEdge(vertex2, vertex4, 1);
        graph.insertEdge(vertex2, vertex5, 8);
        graph.insertEdge(vertexP, vertex6, 12);
        graph.insertEdge(vertexP, vertex7, 5);

        NaryTree<Vertex<String>> result = graph.prim(vertexP);
        ArrayList<Vertex<String>> resultPreOrder = result.preorderList(result.getRoot()) ;
        for (Vertex<String> v: resultPreOrder) {
            System.out.println(v.getValue());
        }



    }
}
package ui;

import datastructures.Graph.*;
import datastructures.Graph.AdjacencyListGraph.AdjacencyListGraph;
import datastructures.Graph.AdjacencyMatrixGraph.AdjacencyMatrixGraph;
import datastructures.Graph.Graph.DijkstraResult;
import datastructures.Graph.Graph.IGraph;
import datastructures.Graph.Graph.Vertex;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>(false, true);
        Vertex<String> vertexA = new Vertex<>("A");
        Vertex<String> vertexB = new Vertex<>("B");
        Vertex<String> vertexC = new Vertex<>("C");
        Vertex<String> vertexD = new Vertex<>("D");


        graph.insertVertex(vertexA);
        graph.insertVertex(vertexB);
        graph.insertVertex(vertexC);
        graph.insertVertex(vertexD);


        graph.insertEdge(vertexA, vertexB, 10);
        graph.insertEdge(vertexA, vertexC, 7);
        graph.insertEdge(vertexC, vertexB, 5);
        graph.insertEdge(vertexB, vertexD, 3);

        ArrayList<Vertex<String>[]> paths = graph.kruskal();
        for (Vertex<String>[] path: paths) {
            for (Vertex<String> vertex: path) {
                System.out.print(vertex.getValue() + " ");
            }
            System.out.println();
        }



    }
}
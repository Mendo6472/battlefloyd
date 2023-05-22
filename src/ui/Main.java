package ui;

import datastructures.Graph.Graph;

import datastructures.Graph.Vertex;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>(false, true);
        Vertex<String> vertexA = new Vertex<>("A");
        Vertex<String> vertexB = new Vertex<>("B");
        Vertex<String> vertexC = new Vertex<>("C");
        Vertex<String> vertexD = new Vertex<>("D");
        Vertex<String> vertexE = new Vertex<>("E");
        Vertex<String> vertexZ = new Vertex<>("Z");

        graph.insertVertex(vertexA);
        graph.insertVertex(vertexB);
        graph.insertVertex(vertexC);
        graph.insertVertex(vertexD);
        graph.insertVertex(vertexE);
        graph.insertVertex(vertexZ);

        graph.insertEdge(vertexA, vertexB, 4);
        graph.insertEdge(vertexA, vertexC, 2);
        graph.insertEdge(vertexB, vertexD, 5);
        graph.insertEdge(vertexB, vertexC, 1);
        graph.insertEdge(vertexC, vertexD, 8);
        graph.insertEdge(vertexC, vertexE, 10);
        graph.insertEdge(vertexD, vertexE, 2);
        graph.insertEdge(vertexD, vertexZ, 6);
        graph.insertEdge(vertexE, vertexZ, 3);

        Graph<String>.DijkstraResult resultDijkstra = graph.dijkstra(vertexA);
        for (Vertex<String> vertex : resultDijkstra.getPrevious()) {
            if ( vertex != null ) {
                System.out.println(vertex.getValue());
            }else{
                System.out.println("null");
            }
        }
        for (Double distance: resultDijkstra.getDistances()) {
            System.out.println(distance);
        }
        double[][] resultFloydWarshall = graph.floydWarshall();
        for (double[] doubles : resultFloydWarshall) {
            for (int j = 0; j < resultFloydWarshall.length; j++) {
                if ( doubles[j] == Double.MAX_VALUE ) {
                    System.out.print("[inf]");
                } else {
                    System.out.print("[" + doubles[j] + "]");
                }
            }
            System.out.println(" ");
        }



    }
}
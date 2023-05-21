package test.datastructures;

import datastructures.Graph.AdjacencyMatrixGraph.AdjacencyMatrixGraph;
import datastructures.Graph.Graph.IGraph;
import org.junit.Test;

import static org.junit.Assert.*;

import datastructures.Graph.Graph.Vertex;
import datastructures.NaryTree.NaryTree;

import java.util.ArrayList;


public class GraphTest {

    public IGraph<String> adjacencyListGraph;
    public Vertex<String> vertexP = new Vertex<>("C");


    public void setupStage1() {
        adjacencyListGraph = new AdjacencyMatrixGraph<>(false, false);
    }

    public void setupStage2() {
        adjacencyListGraph = new AdjacencyMatrixGraph<>(false, false);

        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex4 = new Vertex<>("D");
        Vertex<String> vertex5 = new Vertex<>("E");
        Vertex<String> vertex6 = new Vertex<>("F");
        Vertex<String> vertex7 = new Vertex<>("G");
        Vertex<String> vertex8 = new Vertex<>("H");
        adjacencyListGraph.insertVertex(vertex1);
        adjacencyListGraph.insertVertex(vertex2);
        adjacencyListGraph.insertVertex(vertexP);
        adjacencyListGraph.insertVertex(vertex4);
        adjacencyListGraph.insertVertex(vertex5);
        adjacencyListGraph.insertVertex(vertex6);
        adjacencyListGraph.insertVertex(vertex7);
        adjacencyListGraph.insertVertex(vertex8);
    }

    public void setupStage3() {
        adjacencyListGraph = new AdjacencyMatrixGraph<>(false, false);

        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex4 = new Vertex<>("D");
        Vertex<String> vertex5 = new Vertex<>("E");
        Vertex<String> vertex6 = new Vertex<>("F");
        Vertex<String> vertex7 = new Vertex<>("G");
        Vertex<String> vertex8 = new Vertex<>("H");

        adjacencyListGraph.insertVertex(vertex1);
        adjacencyListGraph.insertVertex(vertex2);
        adjacencyListGraph.insertVertex(vertexP);
        adjacencyListGraph.insertVertex(vertex4);
        adjacencyListGraph.insertVertex(vertex5);
        adjacencyListGraph.insertVertex(vertex6);
        adjacencyListGraph.insertVertex(vertex7);
        adjacencyListGraph.insertVertex(vertex8);

        adjacencyListGraph.insertEdge(vertex1, vertex2, 0);
        adjacencyListGraph.insertEdge(vertex1, vertexP, 0);
        adjacencyListGraph.insertEdge(vertex2, vertex4, 0);
        adjacencyListGraph.insertEdge(vertex2, vertex5, 0);
        adjacencyListGraph.insertEdge(vertexP, vertex6, 0);
        adjacencyListGraph.insertEdge(vertexP, vertex7, 0);

    }

    // Insert vertex
    @Test
    public void testInsertVertex1() {
        setupStage1();
        Vertex<String> vertex = new Vertex<>("A");
        Vertex<String> v = adjacencyListGraph.insertVertex(vertex);
        assertEquals(v, vertex);
    }

    @Test
    public void testInsertVertex2() {
        setupStage1();
        Vertex<String> vertex = new Vertex<>("A");

        adjacencyListGraph.insertVertex(vertex);
        Vertex<String> v2 = adjacencyListGraph.insertVertex(vertex);

        assertNull(v2);
    }

    @Test
    public void testInsertVertex3() {
        setupStage2();
        Vertex<String> vertex = new Vertex<>("Z");
        Vertex<String> v = adjacencyListGraph.insertVertex(vertex);
        assertEquals(v, vertex);
    }

    // Insert edge
    @Test
    public void testInsertEdge1() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("D");
        assertFalse(adjacencyListGraph.insertEdge(vertex1, vertex2, 0));
    }

    @Test
    public void testInsertEdge2() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        adjacencyListGraph.insertVertex(vertex1);
        adjacencyListGraph.insertVertex(vertex2);
        assertTrue(adjacencyListGraph.insertEdge(vertex1, vertex2, 0));
    }

    @Test
    public void testInsertEdge3() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        adjacencyListGraph.insertVertex(vertex1);
        adjacencyListGraph.insertVertex(vertex2);
        assertTrue(adjacencyListGraph.insertEdge(vertex1, vertex2, 0));
        assertFalse(adjacencyListGraph.insertEdge(vertex2, vertex1, 0));
    }

    // Delete vertex

    @Test
    public void testDeleteVertex1() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        adjacencyListGraph.insertVertex(vertex1);
        assertTrue(adjacencyListGraph.deleteVertex(vertex1));
    }

    @Test
    public void testDeleteVertex2() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        adjacencyListGraph.insertVertex(vertex1);
        adjacencyListGraph.insertVertex(vertex2);
        assertTrue(adjacencyListGraph.deleteVertex(vertex1));
        assertFalse(adjacencyListGraph.deleteVertex(vertex1));
    }

    @Test
    public void testDeleteVertex3() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        adjacencyListGraph.insertVertex(vertex1);
        assertFalse(adjacencyListGraph.deleteVertex(vertex2));
    }

    // Delete edge

    @Test
    public void testDeleteEdge1() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        assertFalse(adjacencyListGraph.deleteEdge(vertex1, vertex2));
    }

    @Test
    public void testDeleteEdge2() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        adjacencyListGraph.insertVertex(vertex1);
        adjacencyListGraph.insertVertex(vertex2);
        adjacencyListGraph.insertEdge(vertex1, vertex2, 0);
        assertTrue(adjacencyListGraph.deleteEdge(vertex1, vertex2));
    }

    @Test
    public void testDeleteEdge3() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        adjacencyListGraph.insertVertex(vertex1);
        assertFalse(adjacencyListGraph.deleteEdge(vertex1, vertex2));
    }


    // BFS
    @Test
    public void testBFS1() {
        setupStage3();
        NaryTree<String> naryTreeBFS = adjacencyListGraph.BFS(vertexP);
        assertEquals("CABDEFG",naryTreeBFS.preorder(naryTreeBFS.getRoot()));
    }

    @Test
    public void testBFS2() {
        setupStage3();
        NaryTree<String> naryTreeBFS = adjacencyListGraph.BFS(vertexP);
        assertNotEquals("CABDEFGH",naryTreeBFS.preorder(naryTreeBFS.getRoot()));
    }

    @Test
    public void testBFS3() {
        setupStage2();
        NaryTree<String> naryTreeBFS = adjacencyListGraph.BFS(vertexP);
        assertEquals("C",naryTreeBFS.preorder(naryTreeBFS.getRoot()));

    }
    //DFS

    @Test
    public void testDFS1() {
        setupStage3();
        ArrayList<NaryTree<String>> naryTreeDFS = adjacencyListGraph.DFS();
        assertEquals("ABDECFG",naryTreeDFS.get(0).preorder(naryTreeDFS.get(0).getRoot()));
        assertEquals("H",naryTreeDFS.get(1).preorder(naryTreeDFS.get(1).getRoot()));
    }

    @Test
    public void testDFS2() {
        setupStage3();
        ArrayList<NaryTree<String>> naryTreeDFS = adjacencyListGraph.DFS();
        assertNotEquals("ABDECFGH",naryTreeDFS.get(0).preorder(naryTreeDFS.get(0).getRoot()));
    }

    @Test
    public void testDFS3() {
        setupStage2();
        ArrayList<NaryTree<String>> naryTreeDFS = adjacencyListGraph.DFS();
        assertEquals("A",naryTreeDFS.get(0).preorder(naryTreeDFS.get(0).getRoot()));
        assertEquals("B",naryTreeDFS.get(1).preorder(naryTreeDFS.get(1).getRoot()));
        assertEquals("C",naryTreeDFS.get(2).preorder(naryTreeDFS.get(2).getRoot()));
        assertEquals("D",naryTreeDFS.get(3).preorder(naryTreeDFS.get(3).getRoot()));
        assertEquals("E",naryTreeDFS.get(4).preorder(naryTreeDFS.get(4).getRoot()));
        assertEquals("F",naryTreeDFS.get(5).preorder(naryTreeDFS.get(5).getRoot()));
        assertEquals("G",naryTreeDFS.get(6).preorder(naryTreeDFS.get(6).getRoot()));
        assertEquals("H",naryTreeDFS.get(7).preorder(naryTreeDFS.get(7).getRoot()));

    }
   
}
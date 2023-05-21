package test.datastructures;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import datastructures.Graph.Graph;
import datastructures.Graph.Color;
import datastructures.Graph.IGraph;
import datastructures.Graph.Vertex;
import datastructures.NaryTree.NaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class GraphTest {

    public Graph<String> graph;
    public Vertex<String> vertexP = new Vertex<>("C");


    public void setupStage1() {
        graph = new Graph<>(false, false);
    }

    public void setupStage2() {
        graph = new Graph<>(false, false);

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
    }

    public void setupStage3() {
        graph = new Graph<>(false, false);

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

        graph.insertEdge(vertex1, vertex2, 0);
        graph.insertEdge(vertex1, vertexP, 0);
        graph.insertEdge(vertex2, vertex4, 0);
        graph.insertEdge(vertex2, vertex5, 0);
        graph.insertEdge(vertexP, vertex6, 0);
        graph.insertEdge(vertexP, vertex7, 0);

    }

    // Insert vertex
    @Test
    public void testInsertVertex1() {
        setupStage1();
        Vertex<String> vertex = new Vertex<>("A");
        Vertex<String> v = graph.insertVertex(vertex);
        assertEquals(v, vertex);
    }

    @Test
    public void testInsertVertex2() {
        setupStage1();
        Vertex<String> vertex = new Vertex<>("A");

        graph.insertVertex(vertex);
        Vertex<String> v2 = graph.insertVertex(vertex);

        assertNull(v2);
    }

    @Test
    public void testInsertVertex3() {
        setupStage2();
        Vertex<String> vertex = new Vertex<>("Z");
        Vertex<String> v = graph.insertVertex(vertex);
        assertEquals(v, vertex);
    }

    // Insert edge
    @Test
    public void testInsertEdge1() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("D");
        assertTrue(graph.insertEdge(vertex1, vertex2, 0));
    }

    @Test
    public void testInsertEdge2() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        graph.insertEdge(vertex1, vertex2, 0);
        assertFalse(graph.insertEdge(vertex1, vertex2, 0));
    }

    @Test
    public void testInsertEdge3() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        Vertex<String> vertex3 = new Vertex<>("C");
        graph.insertEdge(vertex1, vertex2, 0);
        graph.insertEdge(vertex1, vertex3, 0);
        assertFalse(graph.insertEdge(vertex3, vertex1, 0));
    }

    // Delete vertex

    @Test
    public void testDeleteVertex1() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        graph.insertVertex(vertex1);
        assertTrue(graph.deleteVertex(vertex1));
    }

    @Test
    public void testDeleteVertex2() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        graph.insertVertex(vertex1);
        graph.insertVertex(vertex2);
        assertTrue(graph.deleteVertex(vertex1));
        assertFalse(graph.deleteVertex(vertex1));
    }

    @Test
    public void testDeleteVertex3() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        graph.insertVertex(vertex1);
        assertFalse(graph.deleteVertex(vertex2));
    }

    // Delete edge

    @Test
    public void testDeleteEdge1() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        assertFalse(graph.deleteEdge(vertex1, vertex2));
    }

    @Test
    public void testDeleteEdge2() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        graph.insertVertex(vertex1);
        graph.insertVertex(vertex2);
        graph.insertEdge(vertex1, vertex2, 0);
        assertTrue(graph.deleteEdge(vertex1, vertex2));
    }

    @Test
    public void testDeleteEdge3() {
        setupStage1();
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("B");
        graph.insertVertex(vertex1);
        assertFalse(graph.deleteEdge(vertex1, vertex2));
    }


    // BFS
    @Test
    public void testBFS1() {
        setupStage3();
        NaryTree<String> naryTreeBFS = graph.BFS(vertexP);
        assertEquals("CABDEFG",naryTreeBFS.preorder(naryTreeBFS.getRoot()));
    }

    @Test
    public void testBFS2() {
        setupStage3();
        NaryTree<String> naryTreeBFS = graph.BFS(vertexP);
        assertNotEquals("CABDEFGH",naryTreeBFS.preorder(naryTreeBFS.getRoot()));
    }

    @Test
    public void testBFS3() {
        setupStage2();
        NaryTree<String> naryTreeBFS = graph.BFS(vertexP);
        assertEquals("C",naryTreeBFS.preorder(naryTreeBFS.getRoot()));

    }
    //DFS

    @Test
    public void testDFS1() {
        setupStage3();
        ArrayList<NaryTree<String>> naryTreeDFS = graph.DFS();
        assertEquals("ABDECFG",naryTreeDFS.get(0).preorder(naryTreeDFS.get(0).getRoot()));
        assertEquals("H",naryTreeDFS.get(1).preorder(naryTreeDFS.get(1).getRoot()));
    }

    @Test
    public void testDFS2() {
        setupStage3();
        ArrayList<NaryTree<String>> naryTreeDFS = graph.DFS();
        assertNotEquals("ABDECFGH",naryTreeDFS.get(0).preorder(naryTreeDFS.get(0).getRoot()));
    }

    @Test
    public void testDFS3() {
        setupStage2();
        ArrayList<NaryTree<String>> naryTreeDFS = graph.DFS();
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
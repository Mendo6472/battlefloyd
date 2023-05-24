package datastructures.Graph.Graph;

import datastructures.NaryTree.NaryTree;
import datastructures.NaryTree.Node;

import java.util.ArrayList;

public class UnionFind<V> {

    ArrayList<NaryTree<V>> sets;

    public UnionFind(){
        sets = new ArrayList<>();
    }

    public void makeset(V v){
        Node<V> node = new Node<>(v);
        sets.add(new NaryTree<>(node));
    }

    public NaryTree<V> find(V v){
        return null;
    }

    public void union (V x, V y){

    }

}

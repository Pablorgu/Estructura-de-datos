
/**
 * Huffman trees and codes.
 *
 * Data Structures, Grado en Informatica. UMA.
 *
 *
 * Student's name:
 * Student's group:
 */

import dataStructures.dictionary.AVLDictionary;
import dataStructures.dictionary.Dictionary;
import dataStructures.list.ArrayList;
import dataStructures.list.LinkedList;
import dataStructures.list.List;
import dataStructures.priorityQueue.BinaryHeapPriorityQueue;
import dataStructures.priorityQueue.PriorityQueue;
import dataStructures.priorityQueue.WBLeftistHeapPriorityQueue;
import dataStructures.searchTree.AVL;
import dataStructures.set.LinkedListSet;
import dataStructures.set.Set;
import dataStructures.tuple.Tuple2;

import javax.print.CancelablePrintJob;
import java.io.CharArrayReader;

public class Huffman {

    // Exercise 1
    public static Dictionary<Character, Integer> weights(String s) {
    	//to do
        Dictionary<Character, Integer> dict= new AVLDictionary<>();
        for(int i=0; i<s.length(); i++){
            if(dict.isDefinedAt(s.charAt(i))) {
                dict.insert(s.charAt(i),dict.valueOf(s.charAt(i))+1);
            } else {
                dict.insert(s.charAt(i),1);
            }
        }
        return dict;
    }
    // Exercise 2.a
    public static PriorityQueue<WLeafTree<Character>> huffmanLeaves(String s) {
    	//to do 
        Dictionary<Character, Integer> dict= new AVLDictionary<>();
        dict = weights(s);
        PriorityQueue<WLeafTree<Character>> pq = new WBLeftistHeapPriorityQueue<>();
        for (Tuple2<Character, Integer> t : dict.keysValues()) {
            WLeafTree<Character> tree= new WLeafTree(t._1(),t._2());
            pq.enqueue(tree);
        }
        return pq;
    }

    private static boolean mayorque2 (String s) {
        boolean mayorque2 = false;
        Set<Character> set = new LinkedListSet<>();
        for(int i=0; i<s.length(); i++){
            set.insert(s.charAt(i));
        }
        if(set.size()>2) {
            mayorque2 = true;
        }
        return mayorque2;
    }
    // Exercise 2.b
    public static WLeafTree<Character> huffmanTree(String s) {
    	//to do
        if(!mayorque2(s)) {
            throw new HuffmanException("La cadena no tiene mas de dos caracteres diferentes");
        }
        PriorityQueue<WLeafTree<Character>> pq = huffmanLeaves(s);
        WLeafTree<Character> tree = pq.first();
        pq.dequeue();
        while (!pq.isEmpty()) {
            WLeafTree<Character> right = pq.first();
            pq.dequeue();
            tree = new WLeafTree<>(tree, right);
        }
        return tree;
    }

    // Exercise 3.a
    public static Dictionary<Character, List<Integer>> joinDics(Dictionary<Character, List<Integer>> d1, Dictionary<Character, List<Integer>> d2) {
        //to do
        for (Tuple2<Character, List<Integer>> t : d1.keysValues()) {
            d2.insert(t._1(),t._2());
        }
        return d2;
    }

    // Exercise 3.b
    public static Dictionary<Character, List<Integer>> prefixWith(int i, Dictionary<Character, List<Integer>> d) {
        //to do
        Dictionary<Character, List<Integer>> dict = new AVLDictionary<>();
    	for(Tuple2<Character, List<Integer>> t : d.keysValues()) {
            t._2().prepend(i);
            dict.insert(t._1(), t._2());
        }
        return dict;
    }

    // Exercise 3.c
    public static Dictionary<Character, List<Integer>> huffmanCode(WLeafTree<Character> ht) {
        //to do
        Dictionary<Character, List<Integer>> dictl = new AVLDictionary<>();
        Dictionary<Character, List<Integer>> dictr = new AVLDictionary<>();
    	if(ht.leftChild()!=null) {
            dictl = huffmanCode(ht.leftChild());
            dictl = prefixWith(0, dictl);
        }
        if (ht.rightChild()!=null){
            dictr = huffmanCode(ht.rightChild());
            dictr = prefixWith(1, dictr);
        }
        if(ht.isLeaf()){
            List<Integer> l = new ArrayList<>();
            dictl.insert(ht.elem(), l);
        } else {
            dictl = joinDics(dictr,dictl);
        }
    return dictl;
    }

    // Exercise 4
    public static List<Integer> encode(String s, Dictionary<Character, List<Integer>> hc) {
        //to do 
    	return null;
    }

    // Exercise 5
    public static String decode(List<Integer> bits, WLeafTree<Character> ht) {
        //to do 
    	return null;
    }
}

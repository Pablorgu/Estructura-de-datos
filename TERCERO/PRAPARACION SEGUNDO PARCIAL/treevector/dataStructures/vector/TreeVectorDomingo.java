/******************************************************************************
 * Student's name:
 * Student's group:
 * Data Structures. Grado en Informática. UMA.
******************************************************************************/
/* 
package dataStructures.vector;

import dataStructures.list.ArrayList;
import dataStructures.list.List;

import java.lang.reflect.Array;

public class TreeVector<T> {

    private int size;
    private Tree<T> root;

    private interface Tree<E> {
        E get(int index);

        void set(int index, E x);

        List<E> toList();
    }

    private static class Leaf<E> implements Tree<E> {
        private E value;

        public Leaf(E x) {
            value = x;
        }

        @Override
        public E get(int i) {
            return value;
        }

        @Override
        public void set(int i, E x) {
        	value = x;
        }

        @Override
        public List<E> toList() {
            ArrayList<E> list = new ArrayList<E>(1);
            list.append(value);
            return list;
        }
    }

    private static class Node<E> implements Tree<E> {
        private Tree<E> left;
        private Tree<E> right;

        public Node(Tree<E> l, Tree<E> r) {
            left = l;
            right = r;
        }

        @Override
        public E get(int i) {
            if(i % 2 == 0){
                return left.get(i/2);
            }
            else{
                return right.get(i/2);
            }
        }

        @Override
        public void set(int i, E x) {
            if(i % 2 == 0){
                left.set(i/2, x);
            }
            else{
                right.set(i/2, x);
            }
        }

        @Override
        public List<E> toList() {
            return intercalate(left.toList(), right.toList());
        }
    }

    public TreeVector(int n, T value) {
    	if(n < 0) throw new VectorException("n negativo");
        this.size = 2^n;
        if(n == 0){
            root = new Leaf<>(value);
        }
        else if(n == 1){
            root = new Node<>(new Leaf<>(value), new Leaf<>(value));
        }
        else{
            TreeVector tl, tr;
            tl = new TreeVector(n-1, value);
            tr = new TreeVector(n-1, value);
            root = new Node<>(tl.root, tr.root);
        }
    }

    public int size() {
        return size;
    }

    public T get(int i) {
        if(i < 0 || i >= size){
            throw new VectorException("Índice fuera de ranago");
        }
        return root.get(i);
    }

    public void set(int i, T x) {
    	if(i < 0 || i >= size){
            throw new VectorException("Índice fuera de ranago");
        }
        root.set(i, x);
    }

    public List<T> toList() {
        return root.toList();
    }

    protected static <E> List<E> intercalate(List<E> xs, List<E> ys) {
        ArrayList<E> intercalados = new ArrayList<>(Math.min(xs.size(), ys.size()) * 2);
        int cntPar = 0;
        int cntImpar = 0;
        for (int i = 0; i < intercalados.size(); i++) {
            if(i % 2 == 0){
                intercalados.set(i, xs.get(cntPar));
                cntPar++;
            }
            else{
                intercalados.set(i, ys.get(cntImpar));
                cntImpar++;
            }
        }
        return intercalados;
    }

    static protected boolean isPowerOfTwo(int n) {
        if(n == 1){
            return true;
        }
        else if(n > 1){
            isPowerOfTwo(n/2);
        }
        return false;
    }

    public static <E> TreeVector<E> fromList(List<E> l) {
    	if(!isPowerOfTwo(l.size())){
            throw new VectorException("No es potencia de dos");
        }

        return null;
    }
}

*/
/******************************************************************************
 * Student's name:
 * Student's group:
 * Data Structures. Grado en Inform�tica. UMA.
******************************************************************************/

package dataStructures.vector;

import java.lang.reflect.Array;

import dataStructures.list.ArrayList;
import dataStructures.list.List;

public class TreeVector<T> {

    private final int size;
    private final Tree<T> root;

    private interface Tree<E> {
        E get(int index);

        void set(int index, E x);

        List<E> toList();
    }

    private static class Leaf<E> implements Tree<E> {
        private E value;

        private Leaf(E x) {
            value = x;
        }

        @Override
        public E get(int index) {
        	//to do
            return value;
        }

        @Override
        public void set(int i, E x) {
        	//to do
            value=x;
        }

        @Override
        public List<E> toList() {
        	//to do
            List<E> lista = new ArrayList<E>(1);
            lista.append(value);
            return lista;
        }
    }

    private static class Node<E> implements Tree<E> {
        private Tree<E> left;
        private Tree<E> right;

        private Node(Tree<E> l, Tree<E> r) {
            left = l;
            right = r;
        }

        @Override
        public E get(int index) {
        	//to do
            if(index%2==0){
                return left.get(index/2);
            }else {
                return right.get(index/2);
            }
        }

        @Override
        public void set(int index, E x) {
        	//to do
            if(index%2==0) {
                left.set(index/2, x);
            } else {
                right.set(index/2, x);
            }
        }

        @Override
        public List<E> toList() {
        	//to do
            return intercalate(left.toList(), right.toList());
        }
    }

    public TreeVector(int n, T value) {
    	//to do
        size = (int) Math.pow(2, n);
        if(size==0){
            root = new Leaf<>(value);
        } else {
            TreeVector<T> r,l;
            l=new TreeVector<T> ((n-1), value);
            r=new TreeVector<T> ((n-1), value);
            root=new Node<>(l.root, r.root);
        }
    }

    public int size() {
    	//to do
        return size;
    }

    public T get(int i) {
    	//to do
        if(i<0) {
            throw new VectorException();
        } else {
            return root.get(i);
        }
    }

    public void set(int i, T x) {
    	//to do
        if(i<0){
        throw new VectorException();
        } else {
        root.set(i, x);
        }
    }

    public List<T> toList() {
    	//to do
        return root.toList();
    }

    protected static <E> List<E> intercalate(List<E> xs, List<E> ys) {
    	//to do
        List<E> intercalados = new ArrayList<E>(Math.min(xs.size(), ys.size())*2); 
        int contimpar=0;
        int contpar=0;
        for(int i=0; i<intercalados.size(); i++){
            if(i%2==0){
                intercalados.append(xs.get(contpar));
                contpar++;
            } else {
                intercalados.append(ys.get(contimpar));
                contimpar++;
            }
        }
        return intercalados;
    }

    
    // Only for students not taking continuous assessment

    static protected boolean isPowerOfTwo(int n) {
    	//to do
        return false;
    }

    public static <E> TreeVector<E> fromList(List<E> l) {
    	//to do
        return null;
    }
}
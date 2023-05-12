/******************************************************************************
 * Student's name:
 * Student's group:
 * Data Structures. Grado en Informática. UMA.
******************************************************************************/

package dataStructures.vector;

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
        	List<E> list = new ArrayList<E>(1);
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
            }else{
                return right.get(i/2);
            }
        }

        @Override
        public void set(int i, E x) {
        	if(i % 2 == 0){
                left.set((i/2), x);
            }else{
                right.set((i/2), x);
            }
        }

        @Override
        public List<E> toList() {
        	return intercalate(left.toList(), right.toList());
        }
    }

    public TreeVector(int n, T value) {
        if(n < 0){
            throw new VectorException("numero negativo");
        }
        this.size = (int) (Math.pow(2, n));

        if(n == 0){
            root = new Leaf<>(value);
        }else{
            TreeVector<T> r, l;
            r = new TreeVector<T>((n-1), value);
            l = new TreeVector<T>((n-1), value);
            root = new Node<T>(l.root, r.root);
        }
    }

    public int size() {

        return this.size;
    }

    public T get(int i) {
    	
        if(i < 0){
            throw new VectorException("indice negativo");
        }else{
            return root.get(i);
        }
    }

    public void set(int i, T x) {
    	if(i < 0){
            throw new VectorException("indice negativo");
        }else{
            root.set(i, x);
        }
    }

    public List<T> toList() {

        return root.toList();
    }

    protected static <E> List<E> intercalate(java.util.List<E> xs, List<E> ys) {
    	//to do
        List<E> intercalados = new ArrayList<E>(Math.min(xs.size(), ys.size())*2);
        int cntPar=0;
        int cntimpar=0;
        for(int i=0, i<intercalados.size(); i++){
            if(i%2==1) {
                intercalados.set(i, xs.get(cntimpar));
                cntimpar++;
            } else {
                intercalados.set(i, ys.get(cntPar));
                cntPar++;
            }
        }
        return intercalados;
    }

    static protected boolean isPowerOfTwo(Double n) {
        if(n == 1){
            return true;
        }
        else if(n > 1){
            isPowerOfTwo(n/2);
        }
        return false;
    }

    public static <E> TreeVector<E> fromList(List<E> l) {
    	//to do
        return null;
    }
}

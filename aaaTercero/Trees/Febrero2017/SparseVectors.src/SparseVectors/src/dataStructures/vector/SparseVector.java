/******************************************************************************
 * Student's name:
 * Student's group:
 * Data Structures. Grado en Informática. UMA.
******************************************************************************/

package dataStructures.vector;

import java.util.Iterator;

public class SparseVector<T> implements Iterable<T> {

    protected interface Tree<T> {

        T get(int sz, int i);

        Tree<T> set(int sz, int i, T x);
    }

    // Unif Implementation

    protected static class Unif<T> implements Tree<T> {

        private T elem;

        public Unif(T e) {
            elem = e;
        }

        @Override
        public T get(int sz, int i) {
            // TODO
            return elem;
        }

        @Override
        public Tree<T> set(int sz, int i, T x) {
            // TODO
            if(elem.equals(x)) return this;
            if(sz==1){
                return new Unif<T> (x);
            } else{
                if(i<sz/2){
                    return new Node<T> (set(sz/2, i, x), this);
                } else {
                    return new Node<T> (this, set(sz/2, i-(sz/2), x));
                }
            }
        }

        @Override
        public String toString() {
            return "Unif(" + elem + ")";
        }
    }

    // Node Implementation

    protected static class Node<T> implements Tree<T> {

        private Tree<T> left, right;

        public Node(Tree<T> l, Tree<T> r) {
            left = l;
            right = r;
        }

        @Override
        public T get(int sz, int i) {
            // TODO
            if(i<sz/2) {
                return left.get(sz/2, i);
            } else {
                return right.get(sz/2, i-(sz/2));
            }
        }

        @Override
        public Tree<T> set(int sz, int i, T x) {
            // TODO
            if(i<sz/2) {
                left.set(sz/2, i, x);
            } else {
                right.set(sz/2, i-sz/2, x);
            }
            simplify();
            return this;
        }

        protected Tree<T> simplify() {
            // TODO
            if(left instanceof Unif<?> && right instanceof Unif<?> && left.get(1,0) == right.get(1,0)){
                return (Unif<T>) left;
            }
            return this;
        }

        @Override
        public String toString() {
            return "Node(" + left + ", " + right + ")";
        }
    }

    // SparseVector Implementation

    private int size;
    private Tree<T> root;

    public SparseVector(int n, T elem) {
        // TODO
        if(n<0){
            throw new VectorException("Numero negativo");
        }
        size=(int)Math.pow(2, n);
        root=new Unif<T>(elem);
    }

    public int size() {
        // TODO
        return size;
    }

    public T get(int i) {
        // TODO
        if(i<0) {
            throw new VectorException("El indice es negativo");
        } 
        return root.get(size, i);
    }

    public void set(int i, T x) {
        // TODO
        if(i<0) {
            throw new VectorException("El indice es negativo");
        } 
        root.set(size, i, x);
    }

    @Override
    public Iterator<T> iterator() {
        // TODO
        for(i=0; i<size; i++){
            return get(i);
        }
    }

    @Override
    public String toString() {
        return "SparseVector(" + size + "," + root + ")";
    }
}

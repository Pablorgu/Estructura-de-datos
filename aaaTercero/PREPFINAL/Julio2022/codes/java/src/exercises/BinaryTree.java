/* ------------------------------------------------------------------------------
   -- Student's name:
   -- Student's group:
   -- Identity number (DNI if Spanish/passport if Erasmus):
   --
   -- Data Structures. Grado en Informática. UMA.
   -------------------------------------------------------------------------------
*/

package exercises;
import dataStructures.tuple.Tuple2;

public class BinaryTree<T extends Comparable<? super T>> {

    private static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        public Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(E value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    public BinaryTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insertBST(T value) {
        root = insertBSTRec(root, value);
    }

    private Node<T> insertBSTRec(Node<T> node, T elem) {
        if (node == null) {
            node = new Node<>(elem);
        } else if (elem.compareTo(node.value) < 0)
            node.left = insertBSTRec(node.left, elem);
        else if (elem.compareTo(node.value) > 0)
            node.right = insertBSTRec(node.right, elem);
        else
            node.value = elem;
        return node;
    }

    /**
     * Returns representation of tree as a String.
     */
    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        return className + "(" + toStringRec(this.root) + ")";
    }

    private static String toStringRec(Node<?> node) {
        return node == null ? "null" : "Node<" + toStringRec(node.left) + ","
                + node.value + "," + toStringRec(node.right) + ">";
    }

// -- ESCRIBE TU SOLUCIÓN DEBAJO ----------------------------------------------
// -- WRITE YOUR SOLUTION BELOW  ----------------------------------------------
// -- EXERCISE 2

    private Tuple2<Integer, Boolean> buscararbol(T min , T max, Node<T> root) {
        Tuple2<Integer, Boolean> tupla = new Tuple2<>(0,true);
        if(root!=null) {
            if (root.left == null && root.right == null) {
                if (root.value.compareTo(min) >= 0 && root.value.compareTo(max) <= 0) {
                    tupla = new Tuple2<>(1, true);
                }else {
                    tupla = new Tuple2<>(0, false);
                }
            } else if (root.value.compareTo(min) < 0) {
                tupla = buscararbol(min, max, root.right);
                tupla = new Tuple2<>(tupla._1(), false);
            } else if (root.value.compareTo(max) > 0) {
                tupla = buscararbol(min, max, root.left);
                tupla = new Tuple2<>(tupla._1(), false);
            } else {
                Tuple2<Integer, Boolean> tuplal = buscararbol(min,max, root.left);
                Tuple2<Integer, Boolean> tuplar = buscararbol(min,max,root.right);
                if (tuplal._2()==true && tuplar._2()==true) {
                   tupla = new Tuple2<>(1 + tuplar._1()+ tuplal._1() ,true);
                } else {
                    tupla = new Tuple2<>(tuplar._1()+ tuplal._1(), false);
                }
            }
        }
        return tupla;
    }

    public int subTreesInRange(T min, T max) {
        //TODO
        int total = 0;
       if(!isEmpty()) {
           total = buscararbol(min,max,root)._1();
       }
       return total;
    }
}

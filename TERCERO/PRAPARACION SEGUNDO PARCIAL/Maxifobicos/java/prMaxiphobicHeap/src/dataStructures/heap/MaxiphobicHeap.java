/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Maxiphobic Heaps
 */

package dataStructures.heap;

public class MaxiphobicHeap<T extends Comparable<? super T>> implements	Heap<T> {

  // A node for an augmented binary tree
  private static class Node<E> {
    private E elem;        // the element
    private int size;      // the weight of tree rooted at this node
    private Node<E> left;  // left child (null if no left child)
    private Node<E> right; // right child  (null if no right child)
  }

  // Attribute for MaxiphobicHeap class
  private Node<T> root; // reference to root node of this Maxiphobic heap.
                        // null is heap is empty


  // Returns number of elements in tree stored at node
  private static int size(Node<?> node) {
    // todo
    return node.size();
  }

  // Merges two heaps. Returns merged heap.
  // Parameters are references to roots of heaps that should be merged.
  // Result should be a reference to root of resulting merged heap.
  private static <T extends Comparable<? super T>>
          Node<T> merge(Node<T> h1, Node<T> h2) {
    // todo
      if(h1 == null) {
        return h2;
      }else if (h2 == null){
        return h1;
      }else {
        T nodeRoot;
        Tree<T> hm,lhl, rhl;
        Tree<T> winner;
        if(h1.elem.compareTo(h2.elem)<0) {
          nodeRoot = h1.elem;
          lhl=h1.left;
          rhl=h1.right;
          winner=h2;
        }else {
          nodeRoot = h2.elem;
          lhl=h2.left;
          rhl=h2.right;
          winner=h1;
        }
        obtenerWinner(lhl,rhl,winner); //obtiene el winner una vez tomada la raiz
        return new Tree<T>(nodeRoot,winner, merge(lhl, rhl)); //ahora debo de generar la funcion obetenerwinner
      }
    
  }

  private static <T> void obtenerWinner(Tree<T> l, Tree<T> r, Tree<T> w){
    Tree<T> auxTree = null;
    if(l != null && r != null){
      if(w.size<l.size || w.size<r.size) {
        auxTree = w;
        if(l.size < r.size){
          w=l;
          l=auxTree;
        } else {
          w=r;
          r=auxTree;
        }
      }
    }
  }

  // Constructor for MaxiphobicHeap class. Creates an empty Maxiphobic heap
  public MaxiphobicHeap() {
    root = null;
  }

  // Returns true if this Maxiphobic heap is empty
  public boolean isEmpty() {
    return root == null;
  }

  // Returns total number of elements in this Maxiphobic heap
  public int size() {
    // todo
    return root == null ? 0 : root.size;
  }

  // Returns minimum element in this Maxiphobic heap
  public T minElem() {
    // todo
    if(isEmpty()){
      throw new EmptyHeapException("minelem on empty heap");
    }else {
      return root.elem;
    }
  }

  // Removes minimum element from this Maxiphobic heap
  public void delMin() {
    // todo  
    if (isEmpty())
            throw new EmptyHeapException("delMin on empty heap");
        else
            root = merge(root.left, root.right);
  }

  // insert new element in this Maxiphobic heap
  public void insert(T elem) {
    // todo
    Tree<T> newHeap = new Tree<>(value, null, null);
        root = merge(root, newHeap);
  }

  public void clear() {
    root = null;
  }


  /**
   * Returns representation of this Maxiphobic heap as a String.
   */
  @Override public String toString() {
    String className = getClass().getSimpleName();
    StringBuilder sb = new StringBuilder();
    sb.append(className);
    sb.append("(");
    toStringRec(sb, root);
    sb.append(")");

    return sb.toString();
  }

  private static void toStringRec(StringBuilder sb, Node<?> node) {
    if(node == null) {
      sb.append("null");
    } else {
      sb.append("Node(");
      toStringRec(sb, node.left);
      sb.append(", ");
      sb.append(node.elem);
      sb.append(", ");
      toStringRec(sb, node.right);
      sb.append(")");
    }
  }
}
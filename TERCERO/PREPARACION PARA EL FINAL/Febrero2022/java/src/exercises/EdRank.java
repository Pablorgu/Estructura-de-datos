/* ------------------------------------------------------------------------------
   -- Student's name:
   -- Student's group:
   -- Identity number (DNI if Spanish/passport if Erasmus):
   --
   -- Data Structures. Grado en Informática. UMA.
   -------------------------------------------------------------------------------
*/

package exercises;

import dataStructures.dictionary.AVLDictionary;
import dataStructures.dictionary.Dictionary;
import dataStructures.graph.DiGraph;
import dataStructures.set.Set;

public class EdRank<T extends Comparable<? super T>> {
    private static final double THRESHOLD = 0.0001;
    private DiGraph<T> diGraph;
    private Dictionary<T, Double> rankDict;

    public EdRank(DiGraph<T> g) {
        diGraph=g;
        rankDict=new AVLDictionary<>();
        for(T v: diGraph.vertices()){
            rankDict.insert(v,0.0);
        }
    }

    public void edRank(double rank) {
        //TODO
        for(T v: diGraph.vertices()) {
            distribute(v, rank);
        }
    }

    private void distribute(T node, double rank) {
        //TODO
        if(rank>THRESHOLD) {
            double rankby2 = rank/2.0;
            rankDict.insert(node, rankDict.valueOf(node)+rankby2);
            double sumageneral = rankby2 / diGraph.successors(node).size();
            for(T v : diGraph.successors(node)) {
                distribute(v,  sumageneral);
            }
        }
    }

    public Dictionary<T, Double> edRank() {
        return rankDict;
    }
}

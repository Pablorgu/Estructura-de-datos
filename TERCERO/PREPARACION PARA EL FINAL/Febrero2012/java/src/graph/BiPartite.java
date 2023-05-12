/**
 * @author Blas Ruiz, Data Structures, Grado en Inform�tica. UMA.
 *
 * Control 2. 13-Febrero-2012
 * Estudio de grafos bipartitos por coloreado con b�squeda en profundidad
 */

package graph;

import dictionary.Dictionary;
import dictionary.HashDictionary;
import stack.Stack;
import stack.StackList;


public class BiPartite<V> {
	
	public static enum Color {Red, Blue;
	}

	private static Color nextColor(Color c) {
		return (c == Color.Blue) ?Color.Red:Color.Blue; 
	}
	
	private Stack<Pair<V,Color>> stack; // stack with pair of vertex and color
	private Dictionary<V,Color> dict; // dictionary: Vertices -> Color
	private boolean isBiColored;

	public BiPartite(Graph<V> graph) {
		dict      = new HashDictionary<V, Color>();
		stack = new StackList<Pair<V,Color>>();
		isBiColored       = true;
		if (graph.numVertices() == 0)
			return; 

		V src = graph.vertices().iterator().next(); // initial vertex
		
		stack.push(new Pair<V,Color>(src,Color.Red));
		
		while (!stack.isEmpty()) {
			Pair<V,Color> vColor = stack.top();
			stack.pop();
         	// completad desde aqu�
			if(!isDefinedAt(vColor.first(), dict)){
				dict.insert(vColor.first(), vColor.second());
				for(V ver: graph.successors(vColor.first())) {
					if(!isDefinedAt(ver, dict)) {
						stack.push(new Pair<>(ver, nextColor(vColor.second())));
					}
				}
			} else if (isDefinedAt(vColor.first(), dict)){
				if (dict.valueOf(vColor.first()) != vColor.second()) {
					isBiColored=false;
				}
			}
		}
	}

	private boolean isDefinedAt(V fst, Dictionary<V,Color> dict){
		boolean esta=false;
		for (V v :dict.keys()) {
			if(v == fst) esta = true;
		}
		return esta;
	}

	public Dictionary<V,Color> biColored() {
		return dict;
	}
	
	public boolean isBicolored() {
		return isBiColored;
	}
	
}

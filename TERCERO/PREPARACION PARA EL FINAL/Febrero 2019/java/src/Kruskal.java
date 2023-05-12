/**----------------------------------------------
 * -- Estructuras de Datos.  2018/19
 * -- 2º Curso del Grado en Ingeniería [Informática | del Software | de Computadores].
 * -- Escuela Técnica Superior de Ingeniería en Informática. UMA
 * --
 * -- Examen 4 de febrero de 2019
 * --
 * -- ALUMNO/NAME:
 * -- GRADO/STUDIES:
 * -- NÚM. MÁQUINA/MACHINE NUMBER:
 * --
 * ----------------------------------------------
 */

import dataStructures.graph.DictionaryWeightedGraph;
import dataStructures.graph.WeightedGraph;
import dataStructures.graph.WeightedGraph.WeightedEdge;

import dataStructures.dictionary.Dictionary;
import dataStructures.dictionary.HashDictionary;
import dataStructures.priorityQueue.PriorityQueue;
import dataStructures.priorityQueue.LinkedPriorityQueue;
import dataStructures.set.Set;
import dataStructures.set.HashSet;

public class Kruskal {
	public static <V,W> Set<WeightedEdge<V,W>> kruskal(WeightedGraph<V,W> g) {
		// COMPLETAR
		Set<WeightedEdge<V,W>> s= new HashSet<>();
		PriorityQueue<WeightedEdge<V,W>> PQ = new LinkedPriorityQueue<>();
		Dictionary<V,V> dicrep = new HashDictionary<>();
		for(V v : g.vertices()) {
			dicrep.insert(v,v);
		}
		for(WeightedEdge<V,W> w : g.edges()) {
			PQ.enqueue(w);
		}
		WeightedEdge<V,W> w;
		while(!PQ.isEmpty()) {
			w = PQ.first();
			PQ.dequeue();
			if(comprobarrep(dicrep,w.source()) != comprobarrep(dicrep, w.destination())){
				dicrep.insert(comprobarrep(dicrep, w.destination()), w.source());
				s.insert(w);
			}
		}
		return s;
	}

	private static <V>V comprobarrep(Dictionary<V,V> dict, V ver) {
		while(dict.valueOf(ver) != ver) {
			ver = dict.valueOf(ver);
		}
		return  ver;
	}

	// Sólo para evaluación continua / only for part time students
	public static <V,W> Set<Set<WeightedEdge<V,W>>> kruskals(WeightedGraph<V,W> g) {

		// COMPLETAR
		
		return null;
	}
}

package operazioni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import automi.Automa;
import automi.State;

public class SimpleNFAGeneralized {
	public SimpleNFAGeneralized() {

    }

    // algoritmo 4.2.1 dell'articolo di Tobias Marschall 
	public Automa compute(ArrayList<String> alfabeto, ArrayList<String> stringhe) {
	    Automa automa = new Automa();
	    //inizializzazione livelli del grafo
	    int graph_levels = stringhe.get(0).split("#").length;
	    int q = graph_levels;
	    ArrayList<String> indici = new ArrayList<String>();
	    for(int y =0; y<stringhe.size(); y++) {
	    	indici.add("" + y);
	    }
	    
	    //inizializzazione stato dell'ultimo livello
	    ArrayList<State> stati = new ArrayList<>();
	    State end = new State(graph_levels, null);
	    end.setLevel(graph_levels);
	    end.setAccept(true);   
	    
	    end.setStringhe(indici);
	    stati.add(end);
	    
	    // Loop 3 algoritmo 4.2.1 Marschall
	    for (int k = graph_levels - 1; k >= 0; k--) { //costruzione dall'ultimo livello fino al livello 0
	        ArrayList<State> next_states = new ArrayList<>();
	        for (State cs : stati) { //per ogni nodo creato (H', k+1)
	            for (String c : alfabeto) { // per ogni simbolo nell'alfabeto            
	                ArrayList<String> h = new ArrayList<String>();
	                for (String str : cs.getStringhe()) { //partendo dalle stringhe associate ad ongi nodo
	                    int index = Integer.parseInt(str); //prendine l'indice
	                    if (index < stringhe.size() && stringhe.get(index).split("#")[q - 1].contains(c)) {
	                        h.add("" + index);	                        
	                    }
	                }
	                if (!h.isEmpty()) { //se h non è vuota allora aggiungi il nodo agli stati appartententi al livello k
	                    State newState = new State(k, h);
	                    Map<String, State> in = automa.getTransitionMap().getOrDefault(cs, new HashMap<>());
	                    in.put(c, newState);
	                    automa.getTransitionMap().put(cs, in); //aggiungi la transizione
	                    next_states.add(newState);
	                }
	            }
	        }
	        stati = next_states;
	        q--;
	    }	    	  	    	
	    //aggiungere self-transition (passo 4 dell'algoritmo
	    for(State s : stati) {
	    	if(s.getLevel()==0) {
	    		s.setSelf_transition(true);
	    	}
	    }	    
	    automa.setStates(stati);	    
	    return automa;
	}

}

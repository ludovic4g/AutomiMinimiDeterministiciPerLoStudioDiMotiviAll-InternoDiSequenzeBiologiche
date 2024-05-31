package operazioni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import automi.Automa;
import automi.State;

public class SimpleNFAGeneralized_NewVersion {
	public SimpleNFAGeneralized_NewVersion() {
		
	}
	public Automa compute(ArrayList<String> alfabeto, ArrayList<String> stringhe) {
	    Automa automa = new Automa();
	  //inizializzazione livelli del grafo
	    int graph_levels = 0;
	    for(String g: stringhe) {
	    	if(g.split("#").length>graph_levels) graph_levels=g.split("#").length;
	    }
	    
	    int q = graph_levels;
	    ArrayList<String> indici = new ArrayList<String>();
	    for(int y =0; y<stringhe.size(); y++) {
	    	indici.add("" + y);
	    }
	    
	    //porre le lunghezze uguali tra loro
	    ArrayList<String> new_stringhe = new ArrayList<String>();
	    boolean ctrl = false;
	    while(!ctrl) {
	    	ctrl=true;
	    	for(String g: stringhe) {
	    		if(g.split("#").length!=graph_levels) {
	    			String temp = "3#" + g;
	    			new_stringhe.add(temp);
	    			ctrl=false;
	    		}
	    		else {
	    			new_stringhe.add(g);
	    		}
	    	}  
	    	
	    	stringhe.clear();
	        stringhe.addAll(new_stringhe);
	        new_stringhe.clear();
	    }
	    
	   //inizializzazione stato dell'ultimo livello
	    ArrayList<State> stati = new ArrayList<>();
	    State end = new State(graph_levels, null);
	    end.setLevel(graph_levels);
	    end.setAccept(true);   
	    
	    end.setStringhe(indici);
	    stati.add(end);
	    ArrayList<State> states = new ArrayList<State>();
	    states.add(end);
	    
	    // Loop 3 algoritmo 4.2.1 Marschall
	    for (int k = graph_levels - 1; k >= 0; k--) { //costruzione dall'ultimo livello fino al livello 0
	    
	    
	        ArrayList<State> next_states = new ArrayList<>();
	        for (State cs : stati) { //per ogni nodo creato (H', k+1)
	            for (String c : alfabeto) { // per ogni simbolo nell'alfabeto            
	                ArrayList<String> h = new ArrayList<String>();
	                for (String str : cs.getStringhe()) { //partendo dalle stringhe associate ad ogni nodo
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
	                   states.add(newState); 
	                   if(newState.getLevel()==0) {
	                    	Map<String, State> last = automa.getTransitionMap().getOrDefault(newState, new HashMap<>());
	                    	last.put("a, b, c", newState);
	                    	automa.getTransitionMap().put(newState, last);
	                    	states.add(newState);
	                    }
	                  
	                }
	            }
	        }
	        stati= next_states;
	        q--;
	    }	    	  	 
	    
	    
	    //aggiungere self-transition (passo 4 dell'algoritmo
	    for(State s : stati) {
	    	if(s.getLevel()==0) {
	    		s.setSelf_transition(true);
	    	}
	    }	    
	    
	   //rimuovi duplicati
	    Set<State> set = new LinkedHashSet<>();
        set.addAll(states);
        states.clear();
        states.addAll(set);
        
        Map<State, Map<String, State>> nuova_hashmap = new HashMap<>();

        for (Map.Entry<State, Map<String, State>> entry : automa.getTransitionMap().entrySet()) {
            State key = entry.getKey();
            Map<String, State> in = entry.getValue();

            if (!containsMap(nuova_hashmap, in)) {
            	nuova_hashmap.put(key, in);
            }
        }
        
        automa.getTransitionMap().clear();
        for(Map.Entry<State, Map<String, State>> entry : nuova_hashmap.entrySet()) {
        	State key = entry.getKey();
            Map<String, State> in = entry.getValue();
            automa.getTransitionMap().put(key, in);
        }
        
        /*
        //accorpare stati uguali
        Map<State, ArrayList<String>> filter = new HashMap<State, ArrayList<String>>();
        Map<String, State> new_filter = new HashMap<>();
        for (Map.Entry<State, Map<String, State>> entry : automa.getTransitionMap().entrySet()) {
        	State state = entry.getKey();
        	Map<String, State> in = entry.getValue();
        	
        	for(Map.Entry<String, State> m : in.entrySet()) {
        		 String key = m.getKey();
                 State value = m.getValue();
                 if (filter.containsKey(value)) {
                	 if(!filter.get(value).contains(key)) {
                     filter.get(value).add(key);
                	 }
                 }else {
                	 ArrayList<String> keysList = new ArrayList<>();
                     keysList.add(key);
                     filter.put(value, keysList);
                 }
        	}
        }
        
      /*  System.out.println("filter:");
        for (Map.Entry<State, ArrayList<String>> entry : filter.entrySet()) {
		      System.out.println(entry.getKey() + ": " + entry.getValue() + "\n");
		}
        
        System.out.println("new filter:");
        for (Map.Entry<String, State> entry : new_filter.entrySet()) {
		      System.out.println(entry.getKey() + ": " + entry.getValue() + "\n");
		}*/
      
        
        automa.setStates(states);	
        
	    return automa;
	    
	}

	private static boolean containsMap(Map<State, Map<String, State>> newh, Map<String, State> in) {
        for (Map<String, State> a : newh.values()) {
            if (a.equals(in)) {
                return true;
            }
        }
        return false;
    }
}

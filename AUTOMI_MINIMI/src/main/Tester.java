package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import automi.Automa;
import automiconsensus.AutomaConsensus;
import automiconsensus.StateConsensus;
import operazioni.SimpleNFAGeneralized_NewVersion;
import operazioni.SimpleNFAHamming;

public class Tester {

	public static void main(String[] args) {
		/*
		//algoritmo 4.2.1 di Tobias Marschall - testing
		System.out.println("Applicazione alle stringhe generalizzate: ");
		SimpleNFAGeneralized prova = new SimpleNFAGeneralized();
		ArrayList<String> alfabeto = new ArrayList<String>();
		alfabeto.add("a");
		alfabeto.add("b");
		alfabeto.add("c");
		ArrayList<String> stringhe = new ArrayList<String>();
		//stringhe.add("a#bc");
		//stringhe.add("ab#b");
		stringhe.add("bc#ac#ab");
		stringhe.add("a#b#abc");
		stringhe.add("c#bc#ac");
		Automa a= prova.compute(alfabeto, stringhe);
		System.out.println("Transition Map: ");
		for (Entry<automi.State, Map<String, automi.State>> entry : a.getTransitionMap().entrySet()) {
		      System.out.println(entry.getKey() + ": " + entry.getValue() + "\n");
		}
		
		//algoritmo applicato alle stringhe consenso
		System.out.println("Applicazione alle stringhe consenso: ");
		SimpleNFAHamming prova1 = new SimpleNFAHamming();
		ArrayList<Character> alf = new ArrayList<Character>();
		alf.add('A');
		alf.add('B');
		alf.add('C');
		alf.add('D');
		String str = "ADC";
        int dmax = 2;
		AutomaConsensus a1= prova1.compute(str, dmax, alf);
		System.out.println("Transition Map: ");
		
		for (StateConsensus state : a1.getStates()) {
            System.out.println("State (Level: " + state.getLevel() + ", Errors: " + state.getError() +  ", self-transition: " + state.isSelftransition() + "):");
            for (Entry<String, StateConsensus> entry : state.getMapTransition().entrySet()) {
                String input = entry.getKey();
                StateConsensus next = entry.getValue();
                System.out.println("  Input: " + input + " -> Next State (Level: " + next.getLevel() + ", Errors: " + next.getError() + ")");
            }
        }
		*/
		SimpleNFAGeneralized_NewVersion prova2 = new SimpleNFAGeneralized_NewVersion();
		ArrayList<String> alfabeto = new ArrayList<String>();
		alfabeto.add("a");
		alfabeto.add("b");
		alfabeto.add("c");
		alfabeto.add("3");
		ArrayList<String> stringhe2 = new ArrayList<String>();
		stringhe2.add("bc#ac#ab");
		stringhe2.add("a#b#abc");
		stringhe2.add("c#bc#ac");
		Automa a= prova2.compute(alfabeto, stringhe2);
		System.out.println("Transition Map: ");
		for (Entry<automi.State, Map<String, automi.State>> entry : a.getTransitionMap().entrySet()) {
		      System.out.println(entry.getKey() + ": " + entry.getValue() + "\n");
		}
    }
}

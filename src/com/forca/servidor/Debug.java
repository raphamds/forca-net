package com.forca.servidor;

import java.util.Vector;

public class Debug {
	
	public static Vector letrasDigitadas;
	
	public static void main(String[] args){
		letrasDigitadas = new Vector();
		letrasDigitadas.addElement("a");
		letrasDigitadas.addElement("v");
		System.out.println(letraSaiu('v'));
	}
	
	public static boolean letraSaiu(char letra){
    	boolean result = false;
		for(int x=0; x<letrasDigitadas.size(); x++){
			String lt = (String)letrasDigitadas.elementAt(x);
			System.out.println("comparando "+lt+" com "+letra);
			if(lt.charAt(0) == letra) return true;
		}
		System.out.println(letra + " nao tem");
    	return result;
    }
}

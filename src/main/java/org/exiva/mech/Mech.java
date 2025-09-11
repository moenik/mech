package org.exiva.mech;

import org.exiva.mech.actions.TypeAction;
import org.exiva.mech.interfaces.IAction;

public class Mech {
	public static void main(String[] args) {
		try {
			System.out.println("batata");
			IAction a = new TypeAction("typooo..",	"The irish\n");
			IAction b = new TypeAction("typooo2..",	"The polish\n");
			IAction c = new TypeAction("typooo2..",	"The british\n");
			
			
			a.addNextActions(b);
			b.addNextActions(c);
			c.addNextActions(a);
			Thread.sleep(3001);
			a.startAction();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
/*
 * 
 */

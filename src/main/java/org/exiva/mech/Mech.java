package org.exiva.mech;

import java.awt.AWTException;

import org.exiva.mech.actions.Action;
import org.exiva.mech.ux.JMech;

public class Mech {
	public static void main(String[] args) throws AWTException {
		
		Action a = new Action("BATATA", "Default");
		Action b = new Action("AWAYS_STOPPED", "Default");
		a.addNextActions(b);
		
		//a.startAction();
		
		
		new JMech();
//		try {
//			System.out.println("batata");
//			IAction a = new TypeAction("typooo..",	"The irish\n");
//			IAction b = new TypeAction("typooo2..",	"The polish\n");
//			IAction c = new TypeAction("typooo2..",	"The british\n");
//			
//			
//			a.addNextActions(b);
//			b.addNextActions(c);
//			c.addNextActions(a);
//			Thread.sleep(3001);
//			a.startAction();
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
	}
}
/*
 * 
 */

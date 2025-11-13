package org.exiva.mech;

import java.awt.AWTException;

import org.exiva.mech.ux.JMech;

/**
 * Main Class
 */
public class Mech {
	public static void main(String[] args) throws AWTException {
		
//		Cache c = Cache.getInstance(); // Initialize cache
//		for(int i=0; i<1000; i++) {
//			c.put(Words.randomAdjectiveNoum(null), new CacheObject(CacheObjectType.STRING, Words.randomAdjectiveNoum(null)));
//		}
		new JMech();
	}
}

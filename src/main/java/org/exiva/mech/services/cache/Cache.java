package org.exiva.mech.services.cache;

import java.util.HashMap;

/**
 * Cache singleton class, stores CacheObject instances with unique string keys
 */
public class Cache {

	private static Cache instance = null;
	
	/**
	 * Get singleton instance
	 * @return Cache instance
	 */
	public static Cache getInstance() {
		if(instance==null) {
			instance = new Cache();
		}
		return instance;
	}	
	
	private HashMap<String, CacheObject> cacheMap;
	/**
	 * Private constructor for singleton
	 */
	private Cache() {
		this.cacheMap = new HashMap<>();
	}
	
	/**
	 * Add object to cache
	 * @param key - unique key
	 * @param obj - {@link CacheObject} to store
	 */
	public void put(String key, CacheObject obj) {
		this.cacheMap.put(key, obj);
	}
	
	/**
	 * Get object from cache
	 * @param key - unique key
	 * @return {@link CacheObject} or null if not found
	 */
	public CacheObject get(String key) {
		return this.cacheMap.get(key);
	}
	
	/**
	 * Remove object from cache
	 * @param key - unique key
	 */
	public void remove(String key) {
		this.cacheMap.remove(key);
	}
	
	/**
	 * Get the entire cache map
	 * @return {@link HashMap} of cache
	 */
	public HashMap<String, CacheObject> getCacheMap() {
		return this.cacheMap;
	}
	
	/**
	 * Clear the cache
	 */
	public void clear() {
		this.cacheMap.clear();
	}

	public Object[][] toTableArray() {
		this.getCacheMap();
		Object[][] tableArray = new Object[this.cacheMap.size()][3];
		for(int i=0; i<this.cacheMap.size(); i++) {
			String key = (String) this.cacheMap.keySet().toArray()[i];
			CacheObject co = this.cacheMap.get(key);
			tableArray[i][0] = key;
			tableArray[i][1] = co.getType().toString();
			tableArray[i][2] = co.getValue().toString();
		}
		return tableArray;
	}
}

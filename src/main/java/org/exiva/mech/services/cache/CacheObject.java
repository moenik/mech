package org.exiva.mech.services.cache;

/**
 * CacheObject class to hold cached objects with their type
 */
public class CacheObject {
	
	private CacheObjectType type;
	private Object value;
	
	/**
	 * Constructor for CacheObject
	 * @param type - {@link CacheObjectType}
	 * @param value - Object value
	 */
	public CacheObject(CacheObjectType type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Get CacheObject type
	 * @return {@link CacheObjectType}
	 */
	public CacheObjectType getType() {
		return type;
	}
	
	/**
	 * Get CacheObject value
	 * @return Object value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Get value as String
	 * @param obj - CacheObject
	 * @return String value
	 */
	public static String getValueAsString(CacheObject obj) {
		if(obj.getType() != CacheObjectType.STRING) {
			throw new IllegalArgumentException("CacheObject is not of type STRING");
		}
		return (String) obj.getValue();
	}
}

package usp.ime.line.ivprog.model.utils;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;

public class IVPMapping extends DomainObject {
	private HashMap mapping;

	public IVPMapping() {
		super("IVPMapping", "IVPMapping");
		mapping = new HashMap();
	}

	public Object getObject(String key) {
		return mapping.get(key);
	}

	public void addToMap(String key, Object o) {
		mapping.put(key, o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.assignment.model.DomainObject#equals(ilm.framework.assignment
	 * .model.DomainObject)
	 */
	public boolean equals(DomainObject o) {
		return false;
	}
}

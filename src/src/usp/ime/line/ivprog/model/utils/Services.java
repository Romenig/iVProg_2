package usp.ime.line.ivprog.model.utils;

import java.util.HashMap;

import usp.ime.line.ivprog.components.dnd.DnDMouseAdapter;

public class Services {
    
	private DnDMouseAdapter mL = null;
	private static Services  instance;
    
    private Services() {
        mL = new DnDMouseAdapter();
    }
    
    public static Services getService() {
        if (instance != null) {
            return instance;
        } else {
            instance = new Services();
        }
        return instance;
    }

    /**
	 * @return the mL
	 */
	public DnDMouseAdapter getML() {
		return mL;
	}

	/**
	 * @param mL the mL to set
	 */
	public void setML(DnDMouseAdapter mL) {
		this.mL = mL;
	}
}

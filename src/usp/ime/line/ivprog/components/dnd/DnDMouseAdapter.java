/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.components.dnd;

import java.awt.Cursor;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class DnDMouseAdapter extends MouseAdapter implements MouseMotionListener {

	private String currentProtocol = INTERACTION_PROTOCOL_DND;
	public static final String INTERACTION_PROTOCOL_DND = "drag&drop";
	public static final String INTERACTION_PROTOCOL_CNP = "cut&paste";
	public static final String INTERACTION_PROTOCOL_CNP_AND_DND = "both";

	private boolean isHolding = false;
	private String holdingComponent = "";
	private JComponent lastEnteredComponent = null;
	private int lastYOnLastEnteredComponent = 0;
	private JComponent mainComponent = null;

	// --------------------------------------------------------------------------------
	// MouseListener
	public void mouseClicked(MouseEvent event) {
		if (currentProtocol.equals(INTERACTION_PROTOCOL_CNP)) {
			if (!isHolding) {
				getComponent(event.getSource());
			} else {
				if (event.getSource() instanceof TargetPanel) {
					dropComponent((TargetPanel) event.getSource(), event.getY());
				}
			}
		}
	}

	public void mouseEntered(MouseEvent event) {
		if (currentProtocol.equals(INTERACTION_PROTOCOL_DND)) {
			if (event.getSource() instanceof TargetPanel) {
				lastEnteredComponent = (JComponent) event.getComponent();
				lastYOnLastEnteredComponent = event.getY();
				System.out.println("Y " + lastYOnLastEnteredComponent);
				event.consume();
			}
		}
	}

	public void mouseReleased(MouseEvent event) {
		if (currentProtocol.equals(INTERACTION_PROTOCOL_DND)) {
			if (lastEnteredComponent instanceof TargetPanel && isHolding) {
				dropComponent((TargetPanel) lastEnteredComponent, lastYOnLastEnteredComponent);
			}
		}
	}

	public void mouseExited(MouseEvent event) {

	}

	public void mousePressed(MouseEvent event) {
	}

	// --------------------------------------------------------------------------------
	// Motion Listener
	public void mouseDragged(MouseEvent event) {
		if (currentProtocol.equals(INTERACTION_PROTOCOL_DND)) {
			if (!isHolding) {
				getComponent(event.getSource());
			}
		}
	}

	public void mouseMoved(MouseEvent event) {

	}

	// ---------------------------------------------------------------------------------------
	// end
	public String getProtocol() {
		return currentProtocol;
	}

	public void setProtocol(String protocol) {
		this.currentProtocol = protocol;
	}

	private void getComponent(Object object) {
		if (object instanceof GripArea) {
			isHolding = true;
			holdingComponent = ((GripArea) object).getModelID();
		}
	}

	private void dropComponent(TargetPanel target, int dropY) {

	}
}

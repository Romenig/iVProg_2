/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.view.components.dnd;

import java.awt.Cursor;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.code.IfElse;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

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
		String origin = ((DataObject) Services.getService().getModelMapping().get(holdingComponent)).getParentID();
		String destiny = target.getContainer();
		DataObject holdingParent = ((DataObject) Services.getService().getModelMapping().get(origin));
		String originContext = (holdingParent instanceof IfElse) ? ((IfElse) holdingParent).getChildContext(holdingComponent) : "";
		String destinyContext = ("".equals(target.getContext())) ? "" : target.getContext();
		JComponent holdingJComponent = (JComponent) Services.getService().getViewMapping().getObject(holdingComponent);
		if (holdingJComponent.isAncestorOf(target)) {
			Services.getService().getController().printError(ResourceBundleIVP.getString("Error.dropCodeInsideItSelf"));
		} else {
			Services.getService()
			        .getController()
			        .moveChild(holdingComponent, origin, target.getContainer(), originContext, destinyContext,
			                target.getDropIndex(dropY, holdingJComponent));
		}
		holdingComponent = "";
		isHolding = false;
		lastEnteredComponent = null;
		lastYOnLastEnteredComponent = 0;
		Services.getService().getController().changeCursor(Cursor.DEFAULT_CURSOR);
	}
}

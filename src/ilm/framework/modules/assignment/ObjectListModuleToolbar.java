package ilm.framework.modules.assignment;

import ilm.framework.modules.IlmModuleToolbar;

import java.util.Observable;

import javax.swing.JButton;

import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ObjectListModuleToolbar extends IlmModuleToolbar {

	private static final long serialVersionUID = 1L;
	private ObjectListModuleGUI _window;
	private JButton button;

	public ObjectListModuleToolbar() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		button = makeButton("objectlist", ResourceBundleIVP.getString("objectBtn.Text"), ResourceBundleIVP.getString("objectBtn.AltText"),
		        ResourceBundleIVP.getString("objectBtn.AltText"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showWindow();
			}
		});
		add(button);

		_window = new ObjectListModuleGUI();
	}

	private void showWindow() {
		_window.setVisible(true);
	}

	public void update(Observable o, Object arg) {
		if (_window != null) {
			_window.update(o, arg);
		}
	}

}

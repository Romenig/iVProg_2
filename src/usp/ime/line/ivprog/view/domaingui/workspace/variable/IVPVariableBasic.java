package usp.ime.line.ivprog.view.domaingui.workspace.variable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.listeners.IValueListener;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;
import usp.ime.line.ivprog.view.domaingui.IDomainObjectUI;
import usp.ime.line.ivprog.view.domaingui.editinplace.EditBoolean;
import usp.ime.line.ivprog.view.domaingui.editinplace.EditInPlace;
import usp.ime.line.ivprog.view.domaingui.utils.IconButtonUI;
import usp.ime.line.ivprog.view.domaingui.utils.RoundedJPanel;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

import java.awt.Color;

public class IVPVariableBasic extends RoundedJPanel implements IDomainObjectUI {

	private JPanel valueContainer;
	private JLabel equalLabel;
	private EditInPlace name;
	private EditInPlace value;
	private EditBoolean booleanValue;
	private JLabel valueLabel;
	private JPanel optionsContainer;
	private JButton configBtn;
	private JButton excludeBtn;
	protected String modelScopeID;
	protected String currentModelID;
	protected String parentModelID;
	private String context;
	private JPopupMenu configMenu;
	private String variableType;

	public IVPVariableBasic(String id, String scope) {
		this.modelScopeID = scope;
		setBackgroundColor(FlatUIColors.MAIN_BG);
		variableType = IVPValue.INTEGER_TYPE;
		initialization();
		setModelID(id);
	}

	private void initialization() {
		initLayout();
		initName();
		initEqualLabel();
		initValueContainer();
		initBooleanValueContainer();
		initOptionsContainer();
		initBtns();
		initConfigMenu();
		changeVariableType();
	}

	private void initLayout() {
		setOpaque(false);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(2);
		flowLayout.setVgap(2);
		flowLayout.setAlignment(FlowLayout.LEFT);
		setLayout(flowLayout);
	}

	private void initOptionsContainer() {
		optionsContainer = new JPanel();
		optionsContainer.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) optionsContainer.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(optionsContainer);
	}

	private void initBtns() {
		initConfigBtn();
		initDeleteBtn();
	}

	private void initDeleteBtn() {
		Action action = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().deleteVariable(modelScopeID, currentModelID);
			}
		};
		action.putValue(Action.SMALL_ICON,
		        new ImageIcon(IVPVariableBasic.class.getResource("/usp/ime/line/resources/icons/varDelete2.png")));
		action.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("IVPVariableBasic.remove.tip"));
		excludeBtn = new JButton(action);
		excludeBtn.setUI(new IconButtonUI());
		optionsContainer.add(excludeBtn);
	}

	private void initConfigBtn() {
		configBtn = new JButton(new ImageIcon(IVPVariableBasic.class.getResource("/usp/ime/line/resources/icons/varConfig2.png")));
		configBtn.setUI(new IconButtonUI());
		configBtn.addActionListener(new ConfigBtnActionListener());
		optionsContainer.add(configBtn);
	}

	private void initConfigMenu() {
		configMenu = new JPopupMenu();
		ActionListener al = new ConfigTypeActionListener();
		JMenuItem menuItemInteira = new JMenuItem(ResourceBundleIVP.getString("IVPVariableBasic.config.integer"));
		configMenu.add(menuItemInteira);
		menuItemInteira.addActionListener(al);
		JMenuItem menuItemReal = new JMenuItem(ResourceBundleIVP.getString("IVPVariableBasic.config.double"));
		configMenu.add(menuItemReal);
		menuItemReal.addActionListener(al);
		JMenuItem menuItemString = new JMenuItem(ResourceBundleIVP.getString("IVPVariableBasic.config.string"));
		configMenu.add(menuItemString);
		menuItemString.addActionListener(al);
		JMenuItem menuItemBoolean = new JMenuItem(ResourceBundleIVP.getString("IVPVariableBasic.config.boolean"));
		configMenu.add(menuItemBoolean);
		menuItemBoolean.addActionListener(al);
	}

	private void initValueContainer() {
		value = new EditInPlace();
		value.setCurrentPattern(EditInPlace.PATTERN_VARIABLE_VALUE_INTEGER);
		value.setValue("1");
		value.setValueListener(new IValueListener() {
			public void valueChanged(String value) {
				Services.getService().getController().changeVariableInitialValue(currentModelID, value);
			}
		});
		add(value);
	}

	private void initBooleanValueContainer() {
		booleanValue = new EditBoolean();
		booleanValue.setValue("true");
		booleanValue.setValueListener(new IValueListener() {
			public void valueChanged(String value) {
				Services.getService().getController().changeVariableInitialValue(currentModelID, value);
			}
		});
		add(booleanValue);
	}

	private void initName() {
		name = new EditInPlace();
		name.setValueListener(new IValueListener() {
			public void valueChanged(String value) {
				if (Services.getService().getController().validateVariableName(modelScopeID, value)) {
					Services.getService().getController().changeVariableName(currentModelID, value);
				} else {
					IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(currentModelID);
					if (!v.getVariableName().equals(value)) {
						Services.getService().getController()
						        .printError(ResourceBundleIVP.getString("Error.sameVariableName") + " " + value);
					} else {
						name.resetTextField();
					}
				}

			}
		});
		name.setCurrentPattern(EditInPlace.PATTERN_VARIABLE_NAME);
		add(name);
	}

	private void initEqualLabel() {
		equalLabel = new JLabel("=");
		add(equalLabel);
	}

	public void setVariableType(String type) {
		variableType = type;
		changeVariableType();
	}

	public void setVariableName(String name) {
		this.name.setValue(name);
	}

	public void setVariableValue(String value) {
		if (this.variableType.equals(IVPValue.BOOLEAN_TYPE)) {
			this.booleanValue.setValue(value);
		} else {
			this.value.setValue(value);
		}
	}

	private void changeVariableType() {
		if (variableType.equals(IVPValue.INTEGER_TYPE)) {
			value.setVisible(true);
			value.setCurrentPattern(EditInPlace.PATTERN_VARIABLE_VALUE_INTEGER);
			value.setValue(IVPValue.DEFAULT_INTEGER);
			booleanValue.setVisible(false);
		} else if (variableType.equals(IVPValue.DOUBLE_TYPE)) {
			value.setVisible(true);
			value.setCurrentPattern(EditInPlace.PATTERN_VARIABLE_VALUE_DOUBLE);
			value.setValue(IVPValue.DEFAULT_DOUBLE);
			booleanValue.setVisible(false);
		} else if (variableType.equals(IVPValue.STRING_TYPE)) {
			value.setVisible(true);
			value.setCurrentPattern(EditInPlace.PATTERN_VARIABLE_VALUE_STRING);
			value.setValue(IVPValue.DEFAULT_STRING);
			booleanValue.setVisible(false);
		} else if (variableType.equals(IVPValue.BOOLEAN_TYPE)) {
			value.setVisible(false);
			booleanValue.setVisible(true);
			booleanValue.setValue(IVPValue.DEFAULT_BOOLEAN);
		}
	}

	private class ConfigBtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			configMenu.show(configBtn, 0, configBtn.getHeight());
			configMenu.requestFocus();
		}
	}

	private class ConfigTypeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals(ResourceBundleIVP.getString("IVPVariableBasic.config.integer"))) {
				Services.getService().getController().changeVariableType(modelScopeID, currentModelID, IVPValue.INTEGER_TYPE);
				changeVariableType();
			} else if (command.equals(ResourceBundleIVP.getString("IVPVariableBasic.config.double"))) {
				Services.getService().getController().changeVariableType(modelScopeID, currentModelID, IVPValue.DOUBLE_TYPE);
				changeVariableType();
			} else if (command.equals(ResourceBundleIVP.getString("IVPVariableBasic.config.string"))) {
				Services.getService().getController().changeVariableType(modelScopeID, currentModelID, IVPValue.STRING_TYPE);
				changeVariableType();
			} else if (command.equals(ResourceBundleIVP.getString("IVPVariableBasic.config.boolean"))) {
				Services.getService().getController().changeVariableType(modelScopeID, currentModelID, IVPValue.BOOLEAN_TYPE);
				changeVariableType();
			}
		}
	}

	public String getModelID() {
		return currentModelID;
	}

	public String getModelParent() {
		return parentModelID;
	}

	public String getModelScope() {
		return modelScopeID;
	}

	public void setModelID(String id) {
		currentModelID = id;
		changeVariableType();
	}

	public void setModelParent(String id) {
		parentModelID = id;
	}

	public void setModelScope(String id) {
		modelScopeID = id;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void setBackgroundColor(Color bg) {
		super.setBackgroundColor(bg);
		revalidate();
		repaint();
	}

	public boolean isContentSet() {
		return true;
	}

	public void lockDownCode() {
	}
}

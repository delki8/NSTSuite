package com.ebay.tool.thinmodelgen.gui.checkeditor.builder.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import com.ebay.jsonpath.JsonPathExecutor;
import com.ebay.tool.thinmodelgen.jsonschema.type.JsonBaseType;

@SuppressWarnings("serial")
public class BooleanComponent extends BaseComponent {

  public BooleanComponent(JsonBaseType saveInstance, String jsonPath, String labelName, String labelDescription, JsonPathExecutor jsonPathExecutor, Method setterMethod, Method getterMethod) {
    super(saveInstance, jsonPath, labelName, labelDescription, jsonPathExecutor, setterMethod, getterMethod);

    this.setLayout(new GridBagLayout());

    GridBagConstraints labelConstraints = new GridBagConstraints();
    labelConstraints.fill = GridBagConstraints.HORIZONTAL;
    labelConstraints.gridx = 0;
    labelConstraints.gridy = 0;
    labelConstraints.weightx = 5.0;
    labelConstraints.weighty = 1.0;
    labelConstraints.insets = new Insets(2, 2, 2, 2);

    this.add(label, labelConstraints);

    GridBagConstraints integerTextFieldConstraints = new GridBagConstraints();
    integerTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
    integerTextFieldConstraints.gridx = 1;
    integerTextFieldConstraints.gridy = 0;
    integerTextFieldConstraints.weightx = 5.0;
    integerTextFieldConstraints.weighty = 1.0;
    integerTextFieldConstraints.insets = new Insets(2, 2, 2, 2);

    String[] values = {"Not applied","true","false"};
    JComboBox<String> stateSelection = new JComboBox<>(values);
    stateSelection.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        String selectedOption = (String) stateSelection.getSelectedItem();
        Boolean booleanValue = parseStringAsBoolean(selectedOption);

        try {
          setterMethod.invoke(jsonPathExecutor, booleanValue);
          saveInstance.updateCheckForPath(jsonPath, jsonPathExecutor);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
          e1.printStackTrace();
        }
      }
    });

    Object getValue = null;

    try {
      getValue = getterMethod.invoke(jsonPathExecutor);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }

    if (getValue != null && getValue instanceof Boolean) {
      Boolean value = (Boolean) getValue;
      ComboBoxModel<String> model = stateSelection.getModel();
      for (int i = 0; i < model.getSize(); i++) {

        Boolean modelBooleanValue = parseStringAsBoolean(model.getElementAt(i));
        if (value.equals(modelBooleanValue)) {
          stateSelection.setSelectedIndex(i);
        }
      }
    }

    this.add(stateSelection, integerTextFieldConstraints);
  }

  private Boolean parseStringAsBoolean(String value) {

    if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
      return Boolean.parseBoolean(value);
    }
    return null;


  }
}

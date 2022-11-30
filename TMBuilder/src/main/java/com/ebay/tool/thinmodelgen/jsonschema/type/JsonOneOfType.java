package com.ebay.tool.thinmodelgen.jsonschema.type;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JsonOneOfType extends JsonBaseType {

  /**
   * Create a new OneOf instance with the node name.
   * @param name Node name.
   */
  public JsonOneOfType(String name) {
    super(name, name, "oneOf");
  }

  @Override
  public JsonType getJsonType() {
    return JsonType.POLYMORPHIC;
  }

  @Override
  public Component getCheckEditorComponent(String jsonPath) {

    JPanel panel = new JPanel();
    BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
    panel.setLayout(boxLayout);
    panel.add(new JLabel("OneOf nodes are not applicable."));
    return panel;
  }
}

package org.amse.fedotov.noplag.ui.split;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Layer extends JPanel {
	
	private Layer myParentLayer;
	private JComponent myContent;
	
	public Layer(Layer parent) {
		super(new BorderLayout());
		myParentLayer = parent;
	}
	
	public Layer getParentLayer() {
		return myParentLayer;
	}
	
	public void setContent(JComponent content) {
		removeAll();
		myContent = content;
		add(content);
		validate();
		repaint();
	}
	
	public JComponent getContent() {
		return myContent;
	}
	
	public void setParentLayer(Layer layer) {
		myParentLayer = layer;
	}

}

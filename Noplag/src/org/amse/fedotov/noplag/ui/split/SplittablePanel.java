package org.amse.fedotov.noplag.ui.split;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.amse.fedotov.noplag.ui.ContentPanel;

@SuppressWarnings("serial")
public class SplittablePanel extends JPanel {
	
	private static final int DIVIDER_SIZE = 3;
	
	private Layer myLayer;
	
	public SplittablePanel(Layer layer) {
		myLayer = layer;
	}
	
	public void split(int splitDirection) {
		Layer parentLayer = myLayer;
		
		myLayer = new Layer(parentLayer);
		Layer secondLayer = new Layer(parentLayer);
		ContentPanel secondPanel = new ContentPanel(secondLayer);
		
		JSplitPane splitPane = new JSplitPane(splitDirection, myLayer, secondLayer);
		splitPane.setDividerSize(DIVIDER_SIZE);
		splitPane.setResizeWeight(1.0 / 2);

		myLayer.setContent(this);
		secondLayer.setContent(secondPanel);
		parentLayer.setContent(splitPane);
	}
	
	public void close() {
		if (myLayer.getParentLayer() != null) {
			Layer layer = myLayer.getParentLayer(); 
			JSplitPane splitPane = (JSplitPane) layer.getContent();
			Layer l;
			if (splitPane.getLeftComponent() != myLayer) {
				l = (Layer) splitPane.getLeftComponent();
			} else {
				l = (Layer) splitPane.getRightComponent();
			}
			layer.setContent(l.getContent());
			fixLayer(l.getContent(), layer);
			myLayer = null;
		}
	}
	
	private void fixLayer(JComponent content, Layer parentLayer) {
		if (content instanceof SplittablePanel) {
			SplittablePanel splittablePanel = (SplittablePanel) content;
			splittablePanel.myLayer = parentLayer;
		} else {
			JSplitPane splitPane = (JSplitPane) content;
			Layer left = (Layer) splitPane.getLeftComponent();
			left.setParentLayer(parentLayer);
			Layer right = (Layer) splitPane.getRightComponent();
			right.setParentLayer(parentLayer);
		}
	}
}

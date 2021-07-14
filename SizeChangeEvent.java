package pathfinding;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class SizeChangeEvent implements ChangeListener {
	private JSlider sizeSlider;
	private JLabel sizeValueLabel;
	private Grid grid;
	private DijkstraAlgorithm dijkstra;
	
	public SizeChangeEvent(JSlider isizeSlider, JLabel isizeValueLabel, Grid igrid, DijkstraAlgorithm idijkstra) {
		sizeSlider = isizeSlider;
		sizeValueLabel = isizeValueLabel;
		grid = igrid;
		dijkstra = idijkstra;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		dijkstra.setRunning(false);
		
		int value = sizeSlider.getValue();
		
		value = (int)(Math.round(value / 10.0) * 10);
		
		sizeValueLabel.setText(value + "x" + value);
		
		grid.resizeGrid(value, value);
	}

}

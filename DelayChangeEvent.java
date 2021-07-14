package pathfinding;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DelayChangeEvent implements ChangeListener {
	
	private JSlider delaySlider;
	private JLabel delayValueLabel;
	private DijkstraAlgorithm dijkstra;
	private AStarAlgorithm aStar;

	public DelayChangeEvent(JSlider idelaySlider, JLabel idelayValueLabel, DijkstraAlgorithm idijkstra, AStarAlgorithm iaStar) {
		delaySlider = idelaySlider;
		delayValueLabel = idelayValueLabel;
		dijkstra = idijkstra;
		aStar = iaStar;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		int value = delaySlider.getValue();
		
		dijkstra.setDelay(value);
		aStar.setDelay(value);
		
		delayValueLabel.setText(value + "ms");
	}

}

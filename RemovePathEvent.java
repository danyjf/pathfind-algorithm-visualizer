package pathfinding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePathEvent implements ActionListener {

	private Grid grid;
	private DijkstraAlgorithm dijkstra;
	
	public RemovePathEvent(Grid igrid, DijkstraAlgorithm idijkstra) {
		grid = igrid;
		dijkstra = idijkstra;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dijkstra.setRunning(false);
		grid.removePath();
	}

}

package pathfinding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveWallsEvent implements ActionListener {

	private Grid grid;
	
	public RemoveWallsEvent(Grid igrid) {
		grid = igrid;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		grid.removeWalls();
	}

}

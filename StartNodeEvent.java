package pathfinding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartNodeEvent implements ActionListener {

	Grid grid;
	
	public StartNodeEvent(Grid igrid) {
		grid = igrid;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		grid.setStartPositionable(true);
		grid.setEndPositionable(false);
		grid.setWallPositionable(false);
		grid.setErasable(false);
	}

}

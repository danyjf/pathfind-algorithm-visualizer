package pathfinding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallNodeEvent implements ActionListener {

	Grid grid;
	
	public WallNodeEvent(Grid igrid) {
		grid = igrid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		grid.setStartPositionable(false);
		grid.setEndPositionable(false);
		grid.setWallPositionable(true);
		grid.setErasable(false);
	}

}

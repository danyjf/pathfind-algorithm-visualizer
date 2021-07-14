package pathfinding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EraseNodeEvent implements ActionListener {

	Grid grid;
	
	public EraseNodeEvent(Grid igrid) {
		grid = igrid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		grid.setStartPositionable(false);
		grid.setEndPositionable(false);
		grid.setWallPositionable(false);
		grid.setErasable(true);
	}

}

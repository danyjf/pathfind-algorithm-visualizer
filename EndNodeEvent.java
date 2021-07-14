package pathfinding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndNodeEvent implements ActionListener {
	
	Grid grid;
	
	public EndNodeEvent(Grid igrid) {
		grid = igrid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		grid.setStartPositionable(false);
		grid.setEndPositionable(true);
		grid.setWallPositionable(false);
		grid.setErasable(false);
	}

}

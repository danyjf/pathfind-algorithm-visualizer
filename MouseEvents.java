package pathfinding;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MouseEvents implements MouseListener {
	
	private Grid grid;
	private boolean mouseDown;
	private JPanel gridPanel;
	
	public MouseEvents(Grid igrid, JPanel igridPanel) {
		grid = igrid;
		mouseDown = false;
		gridPanel = igridPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point mousePos = new Point(e.getX(), e.getY());
		
		int row = (int)(mousePos.y/grid.getCellHeight());
		int col = (int)(mousePos.x/grid.getCellWidth());
		
		if(grid.isStartPositionable()) {
			if(grid.getStartCell() == null) {
				grid.setStartCell(grid.getCells()[row][col]);
				grid.getStartCell().setColor(Color.GREEN);
				grid.getStartCell().setStartCell(true);
			}else {
				grid.getStartCell().setColor(Color.WHITE);
				grid.getStartCell().setStartCell(false);
				grid.setStartCell(grid.getCells()[row][col]);
				grid.getStartCell().setColor(Color.GREEN);
				grid.getStartCell().setStartCell(true);
			}
		}else if(grid.isEndPositionable()) {
			if(grid.getEndCell() == null) {
				grid.setEndCell(grid.getCells()[row][col]);
				grid.getEndCell().setColor(Color.RED);
				grid.getEndCell().setEndCell(true);
			}else {
				grid.getEndCell().setColor(Color.WHITE);
				grid.getEndCell().setEndCell(false);
				grid.setEndCell(grid.getCells()[row][col]);
				grid.getEndCell().setColor(Color.RED);
				grid.getEndCell().setEndCell(true);
			}
		}
		
		grid.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(grid.isWallPositionable()) {
			mouseDown = true;
			
			placeWall(e);
		}else if(grid.isErasable()) {
			mouseDown = true;
			
			removeWall(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
	}
	
	private void placeWall(MouseEvent e) {
		new Thread() {
            public void run() {
            	do {		
        			Point mousePos = MouseInfo.getPointerInfo().getLocation();
        			SwingUtilities.convertPointFromScreen(mousePos, gridPanel);
        			
        			int row = (int)(mousePos.y/grid.getCellHeight());
        			int col = (int)(mousePos.x/grid.getCellWidth());
        			
        			Cell currentCell = grid.getCells()[row][col];
        			
        			if(currentCell != grid.getStartCell() && currentCell != grid.getEndCell()) {
        				currentCell.setColor(Color.BLACK);
        				currentCell.setWall(true);
        			}
        			
        			grid.repaint();
        		} while(mouseDown);
            }
        }.start();
	}
	
	private void removeWall(MouseEvent e) {
		new Thread() {
            public void run() {
            	do {		
        			Point mousePos = MouseInfo.getPointerInfo().getLocation();
        			SwingUtilities.convertPointFromScreen(mousePos, gridPanel);
        			
        			int row = (int)(mousePos.y/grid.getCellHeight());
        			int col = (int)(mousePos.x/grid.getCellWidth());
        			
        			Cell currentCell = grid.getCells()[row][col];
        			
        			if(currentCell != grid.getStartCell() && currentCell != grid.getEndCell()) {
        				currentCell.setColor(Color.WHITE);
        				currentCell.setWall(false);
        			}
        			
        			grid.repaint();
        		} while(mouseDown);
            }
        }.start();
	}
	
}

package pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Grid extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows;
	private int cols;
	private int width;
	private int height;
	private int cellWidth;
	private int cellHeight;
	private Cell[][] cells;
	private boolean isStartPositionable;
	private boolean isEndPositionable;
	private boolean isWallPositionable;
	private boolean isErasable;
	private Cell startCell;
	private Cell endCell;
	JPanel gridPanel;
	
	public Grid(int irows, int icols, JPanel igridPanel) {
		gridPanel = igridPanel;
		
		rows = irows;
		cols = icols;
		cells = new Cell[rows][cols];
		width = 900;
		height = 900;
		
		cellWidth = width / cols;
		cellHeight = height / rows;
		buildGrid();
		
		isStartPositionable = true;
		isEndPositionable = false;
		isWallPositionable = false;
		isErasable = false;
		
		addMouseListener(new MouseEvents(this, gridPanel));
	}

	public void buildGrid() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				cells[row][col] = new Cell(new Point(col*cellWidth, row*cellHeight), cellWidth, cellHeight, row, col);
			}
		}
	}
	
	public void resizeGrid(int irows, int icols) {
		rows = irows;
		cols = icols;
		
		cells = new Cell[rows][cols];
		
		cellWidth = width / cols;
		cellHeight = height / rows;
		
		buildGrid();
		
		repaint();
	}
	
	public void removeWalls() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(cells[row][col] != startCell && cells[row][col] != endCell && cells[row][col].isWall()) {
					cells[row][col].setColor(Color.WHITE);
					cells[row][col].setStartCell(false);
					cells[row][col].setEndCell(false);
					cells[row][col].setWall(false);
					cells[row][col].setfScore(Double.MAX_VALUE);
					cells[row][col].setgScore(Double.MAX_VALUE);
				}
			}
		}
		
		repaint();
	}
	
	public void removePath() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(cells[row][col] != startCell && cells[row][col] != endCell && !cells[row][col].isWall()) {
					cells[row][col].setColor(Color.WHITE);
					cells[row][col].setStartCell(false);
					cells[row][col].setEndCell(false);
					cells[row][col].setWall(false);
					cells[row][col].setfScore(Double.MAX_VALUE);
					cells[row][col].setgScore(Double.MAX_VALUE);
				}
			}
		}
		
		repaint();
	}
	
	public int getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}

	public int getCellHeight() {
		return cellHeight;
	}

	public void setCellHeight(int cellHeight) {
		this.cellHeight = cellHeight;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public boolean isStartPositionable() {
		return isStartPositionable;
	}

	public void setStartPositionable(boolean isStartPositionable) {
		this.isStartPositionable = isStartPositionable;
	}

	public boolean isEndPositionable() {
		return isEndPositionable;
	}

	public void setEndPositionable(boolean isEndPositionable) {
		this.isEndPositionable = isEndPositionable;
	}

	public boolean isWallPositionable() {
		return isWallPositionable;
	}

	public void setWallPositionable(boolean isWallPositionable) {
		this.isWallPositionable = isWallPositionable;
	}
	
	public boolean isErasable() {
		return isErasable;
	}

	public void setErasable(boolean isErasable) {
		this.isErasable = isErasable;
	}

	public Cell getStartCell() {
		return startCell;
	}

	public void setStartCell(Cell startCell) {
		this.startCell = startCell;
	}

	public Cell getEndCell() {
		return endCell;
	}

	public void setEndCell(Cell endCell) {
		this.endCell = endCell;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				cells[row][col].paintComponent(g);
			}
		}
	}
}

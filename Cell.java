package pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

public class Cell extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point position;
	private int width;
	private int height;
	private Color color;
	private boolean isStartCell;
	private boolean isEndCell;
	private boolean isWall;
	private int row;
	private int col;
	private double gScore;
	private double fScore;
	
	//test
	private boolean isCornerNeighbour;
	
	public Cell(Point iposition, int iwidth, int iheight, int irow, int icol) {
		position = iposition;
		width = iwidth;
		height = iheight;
		color = Color.WHITE;
		isStartCell = false;
		isEndCell = false;
		isWall = false;
		isCornerNeighbour = false;
		row = irow;
		col = icol;
		gScore = Double.MAX_VALUE;
		fScore = Double.MAX_VALUE;
	}

	public double getgScore() {
		return gScore;
	}

	public void setgScore(double gScore) {
		this.gScore = gScore;
	}

	public double getfScore() {
		return fScore;
	}

	public void setfScore(double fScore) {
		this.fScore = fScore;
	}

	public boolean isCornerNeighbour() {
		return isCornerNeighbour;
	}

	public void setCornerNeighbour(boolean isCornerNeighbour) {
		this.isCornerNeighbour = isCornerNeighbour;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isStartCell() {
		return isStartCell;
	}

	public void setStartCell(boolean isStartCell) {
		this.isStartCell = isStartCell;
	}

	public boolean isEndCell() {
		return isEndCell;
	}

	public void setEndCell(boolean isEndCell) {
		this.isEndCell = isEndCell;
	}

	public boolean isWall() {
		return isWall;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}

	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(position.x, position.y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(position.x, position.y, width, height);
	}

	@Override
	public String toString() {
		return "(" + col + ", " + row + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}

package pathfinding;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarAlgorithm extends Algorithm {
	
	public AStarAlgorithm(String ialgorithmName, Grid igrid) {
		super(ialgorithmName, igrid);
	}
	
	public void findPath() {
		Cell[][] cellsGrid = super.getGrid().getCells();
		Cell start = null;
		Cell goal = null;
		Cell current;
		PriorityQueue<Cell> openSet = new PriorityQueue<Cell>((c1, c2) -> Double.compare(c1.getfScore(), c2.getfScore()));
		List<Cell> neighbours = new ArrayList<Cell>();
		Map<Cell, Cell> cameFrom = new HashMap<Cell, Cell>();
		double tentativeGScore;
		int row;
		int col;
		
		for(Cell[] rows : cellsGrid) {
			for(Cell cell : rows) {
				if(cell.isStartCell()) {
					start = cell;
				}
				if(cell.isEndCell()) {
					goal = cell;
				}
			}
		}
		
		openSet.add(start);
		
		start.setgScore(0);
		
		start.setfScore(heuristics(start, goal));
		
		while(openSet.size() != 0) {
			current = openSet.remove();
			
			if(current == goal) {
				reconstructPath(cameFrom, current);
				break;
			}
			
			if(!current.isStartCell() && !current.isEndCell()) {
				current.setColor(Color.BLUE);
				//super.getGrid().paintImmediately(0, 0, 900, 900);
				super.getGrid().repaint();
			}
			
			//Encontrar linha e coluna da minDistanceCell			
			row = current.getRow();
			col = current.getCol();
			
			neighbours.clear();
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					//Se cell ja foi visitada ou cell e uma parede entao nao adicionar a grupo de cells vizinhas
					if(row+i >= 0 && row+i < cellsGrid.length && col+j >= 0 && col+j < cellsGrid[row+i].length) {
						if(cellsGrid[row+i][col+j].getColor() != Color.BLUE && !cellsGrid[row+i][col+j].isWall()) {
							neighbours.add(cellsGrid[row+i][col+j]);
							
							if(Math.abs(i) == Math.abs(j)) {
								cellsGrid[row+i][col+j].setCornerNeighbour(true);
							}else {
								cellsGrid[row+i][col+j].setCornerNeighbour(false);
							}
							
							if(!cellsGrid[row+i][col+j].isStartCell() && !cellsGrid[row+i][col+j].isEndCell()) {
								cellsGrid[row+i][col+j].setColor(Color.ORANGE);
								//super.getGrid().paintImmediately(0, 0, 900, 900);
							}
						}
					}
				}
			}
			
			for(Cell neighbour : neighbours) {
				if(neighbour.isCornerNeighbour()) {
					tentativeGScore = current.getgScore() + 10;
				}else {
					tentativeGScore = current.getgScore() + 1;
				}
				
				if(tentativeGScore < neighbour.getgScore()) {
					cameFrom.put(neighbour, current);
					
					neighbour.setgScore(tentativeGScore);
					neighbour.setfScore(neighbour.getgScore() + heuristics(neighbour, goal));
					
					if(!openSet.contains(neighbour)) {
						openSet.add(neighbour);
					}
				}
			}
			
			try {
				Thread.sleep(super.getDelay());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public double heuristics(Cell currentCell, Cell goal) {
		return Math.pow(goal.getRow() - currentCell.getRow(), 2) + Math.pow(goal.getCol() - currentCell.getCol(), 2);
	}
	
	public void reconstructPath(Map<Cell, Cell> cameFrom, Cell current) {
		List<Cell> totalPath = new ArrayList<Cell>();
		Set<Cell> cameFromKeys = cameFrom.keySet();
		
		totalPath.add(current);
		
		while(cameFromKeys.contains(current)) {		
			if(!current.isStartCell() && !current.isEndCell()) {
				current.setColor(Color.YELLOW);
			}
			current = cameFrom.get(current);
			totalPath.add(0, current);
		}
		
		super.getGrid().repaint();
	}
}

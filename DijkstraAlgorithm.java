package pathfinding;

import java.util.List;

import java.awt.Color;
import java.util.ArrayList;

public class DijkstraAlgorithm extends Algorithm {
	
	public DijkstraAlgorithm(String ialgorithmName, Grid igrid) {
		super(ialgorithmName, igrid);
	}

	public void findPath() {
		Cell[][] cellsGrid = super.getGrid().getCells();
		List<Cell> allCells = new ArrayList<Cell>();
		List<Cell> unvisitedCells = new ArrayList<Cell>();
		List<Double> distance = new ArrayList<Double>();
		List<Cell> previous = new ArrayList<Cell>();
		Cell minDistanceCell;
		int row = 0;
		int col = 0;
		List<Cell> neighbours = new ArrayList<Cell>();
		double newDistance;
		double neighbourDistance;
		Cell endCell = null;
		Cell startCell = null;
		List<Cell> path = new ArrayList<Cell>();
		
		for(Cell[] rows : cellsGrid) {
			for(Cell cell : rows) {
				//Colocar todas as cells nao visitadas em uma lista
				unvisitedCells.add(cell);
				previous.add(null);
				
				//Inicializar todos as cells com uma distancia de infinito menos a cell inicial
				if(cell.isStartCell()) {
					distance.add(0.0);
					startCell = cell;
				}else {
					distance.add(Double.MAX_VALUE);
				}
				
				if(cell.isEndCell()) {
					endCell = cell;
				}
			}
		}
		
		//Fazer copia da lista de todas as cells
		allCells.addAll(unvisitedCells);
		
		while(unvisitedCells.size() != 0) {	
			if(!super.isRunning()) {
				break;
			}
			
			//Encontrar cell com o menor valor de distancia
			double min = Double.MAX_VALUE;
			minDistanceCell = null;
			for(int i = 0; i < allCells.size(); i++) {
				if(distance.get(i) < min && unvisitedCells.contains(allCells.get(i))) {
					min = distance.get(i);
					minDistanceCell = allCells.get(i);
				}
			}
			
			if(minDistanceCell == null) {
				System.out.println("Path not found");
				break;
			}
			
			if(!minDistanceCell.isStartCell() && !minDistanceCell.isEndCell()) {
				minDistanceCell.setColor(Color.BLUE);
				//super.getGrid().paintImmediately(0, 0, 900, 900);
				super.getGrid().repaint();
			}
			
			//Remover cell com menor valor de distancia da lista de cells nao visitadas
			unvisitedCells.remove(minDistanceCell);
			
			//Se encontrou endCell, break 
			if(minDistanceCell.equals(endCell)) {
				break;
			}
			
			//Encontrar linha e coluna da minDistanceCell			
			row = minDistanceCell.getRow();
			col = minDistanceCell.getCol();
			
			//Encontrar cells vizinhas de minDistanceCell
			neighbours.clear();
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					//Se cell ja foi visitada ou cell e uma parede entao nao adicionar a grupo de cells vizinhas
					if(row+i >= 0 && row+i < cellsGrid.length && col+j >= 0 && col+j < cellsGrid[row+i].length) {
						if(unvisitedCells.contains(cellsGrid[row+i][col+j]) && !cellsGrid[row+i][col+j].isWall()) {
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
					newDistance = distance.get(allCells.indexOf(minDistanceCell)) + Math.sqrt(2);
				}else {
					newDistance = distance.get(allCells.indexOf(minDistanceCell)) + 1.0;
				}
				neighbourDistance = distance.get(allCells.indexOf(neighbour));
				
				if(newDistance < neighbourDistance) {
					distance.set(allCells.indexOf(neighbour), newDistance);
					previous.set(allCells.indexOf(neighbour), minDistanceCell);
				}
			}
			
			try {
				Thread.sleep(super.getDelay());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(previous.get(allCells.indexOf(endCell)) != null || endCell.equals(startCell)) {
			while(endCell != null) {
				if(!endCell.isStartCell() && !endCell.isEndCell()) {
					endCell.setColor(Color.YELLOW);
				}
				path.add(0, endCell);
				endCell = previous.get(allCells.indexOf(endCell));
			}
			super.getGrid().repaint();
		}
	}
}

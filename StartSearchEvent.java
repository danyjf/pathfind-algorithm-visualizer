package pathfinding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartSearchEvent implements ActionListener {
	
	private Algorithm algorithm;
	private String selectedAlgorithm;
	private DijkstraAlgorithm dijkstra;
	private AStarAlgorithm aStar;
	
	public StartSearchEvent(Algorithm ialgorithm, DijkstraAlgorithm idijkstra, AStarAlgorithm iaStar) {
		algorithm = ialgorithm;
		selectedAlgorithm = algorithm.getAlgorithmName();
		dijkstra = idijkstra;
		aStar = iaStar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		selectedAlgorithm = algorithm.getAlgorithmName();
		
		if(selectedAlgorithm.equals("Dijkstra algorithm")) {
			Runnable r = new Runnable() {
		         public void run() {
		        	 dijkstra.setRunning(true);
		        	 dijkstra.findPath();
		         }
		     };

		     new Thread(r).start();
		     
		}else if(selectedAlgorithm.equals("A* algorithm")) {
			Runnable r = new Runnable() {
		         public void run() {
		        	 aStar.setRunning(true);
		        	 aStar.findPath();
		         }
		     };

		     new Thread(r).start();
		}else if(selectedAlgorithm.equals("D* algorithm")) {
			System.out.println(selectedAlgorithm);
		}
	}

}

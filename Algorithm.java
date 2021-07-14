package pathfinding;

public class Algorithm {

	private String algorithmName;
	private Grid grid;
	private boolean isRunning;
	private int delay;
	
	public Algorithm(String ialgorithmName, Grid igrid) {
		algorithmName = ialgorithmName;
		grid = igrid;
		delay = 30;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}

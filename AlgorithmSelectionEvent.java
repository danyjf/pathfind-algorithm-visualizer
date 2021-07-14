package pathfinding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class AlgorithmSelectionEvent implements ActionListener {
	
	private JComboBox<String> algorithmList;
	private Algorithm algorithm;
	
	public AlgorithmSelectionEvent(JComboBox<String> ialgorithmList, Algorithm ialgorithm) {
		algorithm = ialgorithm;
		algorithmList = ialgorithmList;
		algorithm.setAlgorithmName("Dijkstra algorithm");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		algorithm.setAlgorithmName((String)algorithmList.getSelectedItem());
	}
}

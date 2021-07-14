package pathfinding;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class GUI {

	public static void main(String[] args) {
		final int SIZE_MIN = 10;
		final int SIZE_MAX = 100;
		final int SIZE_INIT = 20;
		
		final int DELAY_MIN = 5;
		final int DELAY_MAX = 150;
		final int DELAY_INIT = 30;
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JPanel optionsPanel = new JPanel();
		JPanel gridPanel = new JPanel();
		JPanel sizePanel = new JPanel();
		JLabel sizeLabel = new JLabel("Size: ");
		JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, SIZE_MIN, SIZE_MAX, SIZE_INIT);
		JLabel sizeValueLabel = new JLabel("20x20");
		JPanel delayPanel = new JPanel();
		JLabel delayLabel = new JLabel("Delay: ");
		JSlider delaySlider = new JSlider(JSlider.HORIZONTAL, DELAY_MIN, DELAY_MAX, DELAY_INIT);
		JLabel delayValueLabel = new JLabel("30ms");
		JButton startSearchButton = new JButton("Start Search");
		JButton startButton = new JButton("Start Node");
		JButton endButton = new JButton("End Node");
		JButton wallButton = new JButton("Wall");
		JButton eraserButton = new JButton("Eraser");
		JButton removeWallsButton = new JButton("Remove Walls");
		JButton removePathButton = new JButton("Remove Path");
		String[] algorithmStrings = {"Dijkstra algorithm", "A* algorithm"};
		JComboBox<String> algorithmList = new JComboBox<String>(algorithmStrings);
		Grid grid = new Grid(20, 20, gridPanel);
		Algorithm algorithm = new Algorithm("Dijkstra algorithm", grid);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm("Dijkstra algorithm", grid);
		AStarAlgorithm aStar = new AStarAlgorithm("A* algorithm", grid);
		
		startSearchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startSearchButton.setMaximumSize(new Dimension(125, 40));
		startSearchButton.setPreferredSize(new Dimension(125, 40));
		startSearchButton.addActionListener(new StartSearchEvent(algorithm, dijkstra, aStar));
		
		sizeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		sizeSlider.setMaximumSize(new Dimension(150, 30));
		sizeSlider.setPreferredSize(new Dimension(150, 30));
		sizeSlider.addChangeListener(new SizeChangeEvent(sizeSlider, sizeValueLabel, grid, dijkstra));
		
		sizePanel.setMaximumSize(new Dimension(300, 30));
		sizePanel.setPreferredSize(new Dimension(300, 30));
		sizePanel.add(sizeLabel);
		sizePanel.add(sizeSlider);
		sizePanel.add(sizeValueLabel);
		
		delaySlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		delaySlider.setMaximumSize(new Dimension(150, 30));
		delaySlider.setPreferredSize(new Dimension(150, 30));
		delaySlider.addChangeListener(new DelayChangeEvent(delaySlider, delayValueLabel, dijkstra, aStar));
		
		delayPanel.setMaximumSize(new Dimension(300, 30));
		delayPanel.setPreferredSize(new Dimension(300, 30));
		delayPanel.add(delayLabel);
		delayPanel.add(delaySlider);
		delayPanel.add(delayValueLabel);
		
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setMaximumSize(new Dimension(120, 30));
		startButton.setPreferredSize(new Dimension(120, 30));
		startButton.addActionListener(new StartNodeEvent(grid));
		
		endButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		endButton.setMaximumSize(new Dimension(120, 30));
		endButton.setPreferredSize(new Dimension(120, 30));
		endButton.addActionListener(new EndNodeEvent(grid));
		
		wallButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		wallButton.setMaximumSize(new Dimension(120, 30));
		wallButton.setPreferredSize(new Dimension(120, 30));
		wallButton.addActionListener(new WallNodeEvent(grid));
		
		eraserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		eraserButton.setMaximumSize(new Dimension(120, 30));
		eraserButton.setPreferredSize(new Dimension(120, 30));
		eraserButton.addActionListener(new EraseNodeEvent(grid));
		
		removeWallsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		removeWallsButton.setMaximumSize(new Dimension(120, 30));
		removeWallsButton.setPreferredSize(new Dimension(120, 30));
		removeWallsButton.addActionListener(new RemoveWallsEvent(grid));
		
		removePathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		removePathButton.setMaximumSize(new Dimension(120, 30));
		removePathButton.setPreferredSize(new Dimension(120, 30));
		removePathButton.addActionListener(new RemovePathEvent(grid, dijkstra));
		
		algorithmList.setAlignmentX(Component.CENTER_ALIGNMENT);
		algorithmList.setMaximumSize(new Dimension(150, 30));
		algorithmList.setPreferredSize(new Dimension(150, 30));
		algorithmList.addActionListener(new AlgorithmSelectionEvent(algorithmList, algorithm));
		
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		optionsPanel.setPreferredSize(new Dimension(300, 900));
		optionsPanel.add(Box.createVerticalGlue());
		optionsPanel.add(startSearchButton);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(sizePanel);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(delayPanel);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(startButton);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(endButton);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(wallButton);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(eraserButton);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(removeWallsButton);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(removePathButton);
		optionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		optionsPanel.add(algorithmList);
		optionsPanel.add(Box.createVerticalGlue());

		gridPanel.setLayout(new BorderLayout());
		gridPanel.add(grid);
		
		panel.setLayout(new BorderLayout());
		panel.add(optionsPanel, BorderLayout.LINE_START);
		panel.add(gridPanel);
		
		frame.add(panel);
		frame.setSize(1207, 936);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pathfinding");
		frame.setResizable(false);
		frame.setVisible(true);
	}

}

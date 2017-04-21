package org.dchmyga.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.MessageFormat;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.dchmyga.data.DataHolder;
import org.dchmyga.data.DataReader;

public class GraphsWindow {

	private final JFrame frame;
	private final ChartPanelWithScrollBar mainPanel;
	private final JMenuBar menuBar;

	public GraphsWindow() {
		frame = new JFrame("RenixGraphs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		menuBar = initMenu();
		frame.add(menuBar, BorderLayout.NORTH);
		mainPanel = new ChartPanelWithScrollBar();
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.pack();
	}

	private JMenuBar initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Open...");
		menuItem.addActionListener(new MenuFileListener());
		menu.add(menuItem);
		menuBar.add(menu);
		return menuBar;
	}

	public void show() {
		frame.setVisible(true);
	}

	private class MenuFileListener implements ActionListener {

		final JFileChooser fc = new JFileChooser();

		@Override
		public void actionPerformed(ActionEvent e) {
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				readData(file);
			}
		}

		private void readData(File file) {
			if (!file.exists() || !file.canRead()) {
				JOptionPane.showMessageDialog(frame, "Cannot read file!");
			}
			DataReader reader = new DataReader();
			try {
				DataHolder dataHolder = reader.readData(file.getAbsolutePath());
				mainPanel.setDataHolder(dataHolder);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame,
						MessageFormat.format("An error occured while reading data. {0}", e.getMessage()));
			}
		}

	}

}

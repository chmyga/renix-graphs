package org.dchmyga.ui;

import java.io.IOException;

import org.dchmyga.data.DataReader;

public class MainClass {

	public static void main(String[] args) throws IOException {
		GraphsWindow window = new GraphsWindow();
		DataReader reader = new DataReader();
		window.show();
	}

}

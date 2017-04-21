package org.dchmyga.data.parser;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class DataParserTest {

	@Test
	public void test() throws IOException {
		DataParser r = new DataParser("D:/temp/TMP5.$$$");
		r.startReading();
		int line = 0;
		int linesCount = 0;
		while (true) {
			try {
				List<Integer> lineData = r.readLine();
				// if (lineData.size() < 33 || lineData.size() > 33) {
				// System.out.print(line++ + ":");
				// System.out.println(lineData);
				// }
				// if (lineData.get(31) != 0xff || lineData.get(32) != 0x00) {
				// System.out.print(linesCount + ": ");
				// printLine(lineData);
				// }
				// if (linesCount == 2131) {
				// printLine(lineData);
				// }
				// System.out.print(linesCount + ": ");
				// printLine(lineData);
				linesCount++;
			} catch (EOFException e) {
				break;
			}
		}
		r.close();
		System.out.println("Read " + linesCount + " lines of data.");
	}

	private void printLine(List<Integer> lineData) {
		for (Integer i : lineData) {
			System.out.print(Integer.toHexString(i) + " ");
		}
		System.out.println();
	}

}

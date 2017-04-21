package org.dchmyga.data.parser;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataParser {
	private final String fileName;

	private FileInputStream fis;

	public DataParser(String fileName) {
		this.fileName = fileName;
	}

	public void startReading() throws IOException {
		if (fis == null) {
			fis = new FileInputStream(new File(fileName));
		}
		String startTime = readTime(fis);
		System.out.println(startTime);
		String endTime = readTime(fis);
		System.out.println(endTime);
		readLineRaw();
	}

	private static String readTime(FileInputStream dis) throws IOException {
		String result = "";
		for (int i = 0; i < 8; i++) {
			result += (char) dis.read();
		}
		return result;
	}

	public void close() throws IOException {
		if (fis != null) {
			fis.close();
		}
	}

	public List<Integer> readLine() throws IOException {
		List<Integer> result = new ArrayList<Integer>();
		while (result.size() != 33) {
			result = readLineRaw();
			if (result.size() != 33) {
				System.out.println("skipped data: ");
				printLine(result);
			}
		}
		return result;
	}

	private void printLine(List<Integer> lineData) {
		for (Integer i : lineData) {
			System.out.print(Integer.toHexString(i) + " ");
		}
		System.out.println();
	}

	private List<Integer> readLineRaw() throws IOException {
		List<Integer> result = new ArrayList<Integer>();
		boolean lineFinished = false;
		int prevHexValue = -1;
		boolean ffSkipped = false;
		while (!lineFinished) {
			int valueFromHex = getIntFromHex(fis.read());
			if (ffSkipped) {
				ffSkipped = false;
			} else if (prevHexValue == 0xff && valueFromHex == 0xff) {
				ffSkipped = true;
				continue;
			}
			result.add(valueFromHex);
			if (prevHexValue == 255 && valueFromHex == 0 && result.size() > 32) {
				lineFinished = true;
			}
			prevHexValue = valueFromHex;
		}
		return result;
	}

	private int getIntFromHex(int value) throws IOException {
		if (value == -1) {
			throw new EOFException();
		}
		String hexValue = Integer.toHexString(value);
		int valueFromHex = Integer.parseInt(hexValue, 16);
		return valueFromHex;
	}
}

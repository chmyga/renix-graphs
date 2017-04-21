package org.dchmyga.data;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.dchmyga.data.parser.DataParser;

public class DataReader {

	public DataHolder readData(String fileName) throws IOException {
		// FileFormat ff = createDefaultFileFormat();
		DataHolder dataHolder = new DataHolder(Arrays.asList(TransformerField.values()));
		DataParser r = new DataParser(fileName);
		r.startReading();
		try {
			while (true) {
				List<Integer> lineData = r.readLine();
				dataHolder.addData(lineData);
			}
		} catch (EOFException e) {
			System.out.println(
					"File " + fileName + " was read successfully. lines number " + dataHolder.getValuesCount());
		} finally {
			r.close();
		}
		return dataHolder;
	}

}

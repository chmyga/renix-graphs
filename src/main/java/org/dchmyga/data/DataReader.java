package org.dchmyga.data;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

import org.dchmyga.data.format.Field;
import org.dchmyga.data.format.FileFormat;
import org.dchmyga.data.format.PossibleFields;
import org.dchmyga.data.parser.DataParser;

public class DataReader {

	public DataHolder readData(String fileName) throws IOException {
		FileFormat ff = createDefaultFileFormat();
		DataHolder dataHolder = new DataHolder(ff);
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

	private FileFormat createDefaultFileFormat() {
		FileFormat ff = new FileFormat();
		for (Field f : PossibleFields.values()) {
			ff.addField(f.getPosition(), f.getName());
		}
		return ff;
	}

}

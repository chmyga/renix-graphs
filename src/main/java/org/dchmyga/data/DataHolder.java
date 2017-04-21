package org.dchmyga.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dchmyga.data.format.Field;
import org.dchmyga.data.format.FileFormat;

public class DataHolder {

	private FileFormat format;
	private Map<Field, List<Integer>> valuesByFields = new HashMap<>();
	private int valuesCount = 0;

	public DataHolder(FileFormat format) {
		this.format = format;
	}

	public void addData(List<Integer> values) {
		for (Field f : format.getFields()) {
			List<Integer> valuesForField = valuesByFields.get(f);
			if (valuesForField == null) {
				valuesForField = new ArrayList<>();
				valuesByFields.put(f, valuesForField);
			}
			valuesForField.add(values.get(f.getPosition() - 1));
		}
		valuesCount++;
	}

	public int getValuesCount() {
		return valuesCount;
	}

	public List<Integer> getDataForFieldName(String fieldName) {
		return valuesByFields.get(format.getFieldByName(fieldName));
	}

	public List<Integer> getDataForField(Field f) {
		return valuesByFields.get(f);
	}

	public Map<Field, List<Integer>> getDataForFieldsByNames(Collection<String> names) {
		Map<Field, List<Integer>> data = new HashMap<>();
		for (String name : names) {
			Field f = format.getFieldByName(name);
			data.put(f, valuesByFields.get(f));
		}
		return data;
	}

	public Map<Field, List<Integer>> getDataForFields(Collection<Field> fields, int start, int end) {
		Map<Field, List<Integer>> data = new HashMap<>();
		for (Field field : fields) {
			List<Integer> values = new ArrayList<>();
			for (int i = start; i < end && i < valuesByFields.get(field).size(); i++) {
				values.add(valuesByFields.get(field).get(i));
			}
			data.put(field, values);
		}
		return data;
	}

	public Collection<Field> getPossibleFields() {
		return valuesByFields.keySet();
	}

}

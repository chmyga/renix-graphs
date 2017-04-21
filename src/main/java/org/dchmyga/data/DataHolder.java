package org.dchmyga.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dchmyga.data.format.Field;

public class DataHolder {

	private Map<String, List<Number>> valuesByFields = new HashMap<>();
	private final List<TransformerField> fields;
	private int valuesCount = 0;

	public DataHolder(List<TransformerField> fields) {
		this.fields = fields;
	}

	public void addData(List<Integer> values) {
		for (TransformerField f : fields) {
			List<Number> valuesForField = valuesByFields.get(f.getName());
			if (valuesForField == null) {
				valuesForField = new ArrayList<>();
				valuesByFields.put(f.getName(), valuesForField);
			}
			valuesForField.add(f.calculate(values));
		}
		valuesCount++;
	}

	public int getValuesCount() {
		return valuesCount;
	}

	public List<Number> getDataForField(Field f) {
		return valuesByFields.get(f.getName());
	}

	public Map<String, List<Number>> getDataForFieldsByNames(Collection<String> names) {
		Map<String, List<Number>> data = new HashMap<>();
		for (String name : names) {
			data.put(name, valuesByFields.get(name));
		}
		return data;
	}

	public <T extends Field> Map<T, List<Number>> getDataForFields(Collection<T> fields, int start, int end) {
		Map<T, List<Number>> data = new HashMap<>();
		for (T field : fields) {
			List<Number> values = new ArrayList<>();
			for (int i = start; i < end && i < valuesByFields.get(field.getName()).size(); i++) {
				values.add(valuesByFields.get(field.getName()).get(i));
			}
			data.put(field, values);
		}
		return data;
	}

}

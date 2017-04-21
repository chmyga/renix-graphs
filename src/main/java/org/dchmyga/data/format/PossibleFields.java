package org.dchmyga.data.format;

import java.util.ArrayList;
import java.util.List;

public enum PossibleFields implements Field {

	FIELD1(1, "Field 1"),
	FIELD2(2, "Field 2"),
	FIELD3(3, "Field 1"),
	FIELD4(4, "Field 1"),
	FIELD5(5, "Field 1"),
	FIELD6(6, "Field 1"),
	FIELD7(7, "Field 1"),
	FIELD8(8, "Field 1"),
	FIELD9(9, "Field 1"),
	FIELD10(10, "Field 1"),
	FIELD11(11, "Field 1"),
	FIELD12(12, "Field 1"),
	FIELD13(13, "Field 1"),
	FIELD14(14, "Field 1"),
	FIELD15(15, "Field 1"),
	FIELD16(16, "Field 1"),
	FIELD17(17, "Field 1"),
	FIELD18(18, "Field 1"),
	FIELD19(19, "Field 1"),
	FIELD20(20, "Field 1"),
	FIELD21(21, "Field 1"),
	FIELD22(22, "Field 1"),
	FIELD23(23, "Field 1"),
	FIELD24(24, "Field 1"),
	FIELD25(25, "Field 1"),
	FIELD26(26, "Field 1"),
	FIELD27(27, "Field 1"),
	FIELD28(28, "Field 1"),
	FIELD29(29, "Field 1"),
	FIELD30(30, "Field 1"),
	FIELD31(31, "Field 1"),
	FIELD32(32, "Field 1");

	private final int position;
	private final String name;

	private static List<Field> defaultGraphFields;

	private PossibleFields(int position, String name) {
		this.position = position;
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public String getName() {
		return "Field " + position;
	}

	public static List<Field> getDefaultGraphFields() {
		if (defaultGraphFields == null) {
			defaultGraphFields = new ArrayList<>();
			for (Field field : values()) {
				defaultGraphFields.add(new FileFormat.FieldImpl(field.getPosition(), field.getName()));
			}
		}
		return defaultGraphFields;
	}

}

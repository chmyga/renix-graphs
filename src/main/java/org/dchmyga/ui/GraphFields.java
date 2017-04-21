package org.dchmyga.ui;

import java.util.ArrayList;
import java.util.List;

import org.dchmyga.common.Constants;
import org.dchmyga.data.format.Field;

public enum GraphFields implements Field {

	MAP(Constants.MAP_NAME, "Map"),
	CTS(Constants.CTS_NAME, "ECT"),
	IAT(Constants.IAT_NAME, "MAT"),
	O2(Constants.LAMBDA_NAME, "O2"),
	RPM(Constants.RPM_NAME, "x10 RPM"),
	TPS(Constants.TPS_NAME, "TPS"),
	SPARK_ADV(Constants.SPARK_ADV_NAME, "ADV"),
	INJ_PULSE(Constants.INJ_PULSE_NAME, "PW_mS"),
	KNOCK(Constants.KNOCK_NAME, "KNOCK"),
	AC_REQUEST(Constants.AC_REQUEST_NAME, "SYNC");

	private final String name;
	private final String title;

	private GraphFields(String name, String title) {
		this.name = name;
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public static List<GraphFields> valuesAsList() {
		List<GraphFields> fields = new ArrayList<>();
		for (GraphFields field : values()) {
			fields.add(field);
		}
		return fields;
	}

	// 4) uchar MAP;
	// 5) uchar ECT_F; /*Engine Coolant Temperature */
	// 6) uchar MAT_F; /*Manifold Air Temperature */ температура поступающего
	// воздуха
	// 8) uchar O2_ADC; /* Exhaust Oxygen Sensor */
	// 9) uchar RPMLow; /* */ обороты
	// 13) uchar TPS_ADC; //Throtle Position Sensor ADC - педаль
	// 14) uchar ADV; //угол опережения зажигания
	// 20) uchar PW_MS; //PW mS = Value / 25 (Math) длительность открытия
	// формунок
	// 28) uchar KNOCK; датчик дитонации

}

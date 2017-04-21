package org.dchmyga.data;

import java.util.List;

import org.dchmyga.common.Constants;
import org.dchmyga.data.format.Field;

public enum TransformerField implements Field {

	MAP(Constants.MAP_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(3) / 2.7 + 11;
		}

	},
	CTS(Constants.CTS_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(4) / 1.6 - 40;
		}

	},
	IAT(Constants.IAT_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(4) / 1.6 - 40;
		}

	},
	VOLTS(Constants.VOLTS_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(6) / 16.24;
		}

	},
	LAMBDA(Constants.LAMBDA_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(7) / 51.2;
		}

	},
	RPM(Constants.RPM_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			int value = (values.get(9) << 8) | values.get(8);
			int result = 0;
			if (value != 0) {
				result = 19850000 / value;
				if (result > 7000) {
					result = 7000;
				}
			}
			return result / 10;
		}

	},
	TPS(Constants.TPS_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(12) / 2.55;
		}

	},
	SPARK_ADV(Constants.SPARK_ADV_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(13);
		}

	},
	BARO(Constants.BARO_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(16);
		}

	},
	EXHAUST(Constants.EXHAUST_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(18);
		}

	},
	INJ_PULSE(Constants.INJ_PULSE_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(19) / 7.79;
		}

	},
	FUEL_SYNC(Constants.FUEL_SYNC_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(21);
		}

	},
	ST_FUEL(Constants.ST_FUEL_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(24);
		}

	},
	LT_FUEL(Constants.LT_FUEL_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(26);
		}

	},
	KNOCK(Constants.KNOCK_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return values.get(27);
		}

	},
	AC_REQUEST(Constants.AC_REQUEST_NAME) {

		@Override
		public Number calculate(List<Integer> values) {
			return ((values.get(29) >> 2) & 1) * 10;
		}

	},

	;

	private final String name;

	private TransformerField(String name) {
		this.name = name;
	}

	public abstract Number calculate(List<Integer> values);

	public String getName() {
		return name;
	}

}

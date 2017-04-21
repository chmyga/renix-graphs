package org.dchmyga.data.format;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileFormat {
	
	private Map<String, Field> fields = new HashMap<>();
	
	public void addField(int position, String name) {
		fields.put(name, new FieldImpl(position, name));
	}
	
	public Collection<Field> getFields() {
		return fields.values();
	}
	
	public Field getFieldByName(String name) {
		return fields.get(name);
	}

	public static class FieldImpl implements Field {
		
		private final int position;
		private final String name;
		
		public FieldImpl(int position, String name) {
			this.position = position;
			this.name = name;
		}

		public int getPosition() {
			return position;
		}

		public String getName() {
			return name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + position;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FieldImpl other = (FieldImpl) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (position != other.position)
				return false;
			return true;
		}
		
	}
	
}

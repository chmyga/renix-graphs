package org.dchmyga.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FieldsSelectionPanel extends JPanel {

	private static final long serialVersionUID = 8439645652887597315L;

	private final JTable table = new JTable();

	public FieldsSelectionPanel() {
		super();
	}

	public void init(Collection<GraphFields> fields) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Boolean.class;
				} else if (columnIndex == 1) {
					return GraphFields.class;
				}
				return super.getColumnClass(columnIndex);
			}
		};
		Object[][] data = new Object[fields.size()][2];
		int i = 0;
		for (GraphFields field : fields) {
			data[i][0] = true;
			data[i][1] = field;
			i++;
		}
		model.setDataVector(data, new Object[] { "", "Field name" });
		table.setModel(model);
		model.setColumnCount(2);
		table.setDefaultRenderer(GraphFields.class, new GraphFieldTableCellRenderer());
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		table.setPreferredSize(new Dimension(400, 300));
		table.setMinimumSize(new Dimension(400, 300));
		JScrollPane pane = new JScrollPane(table);
		this.add(pane);
	}

	public List<GraphFields> getSelectedFields() {
		List<GraphFields> fields = new ArrayList<>();
		for (int i = 0; i < table.getRowCount(); i++) {
			if ((Boolean) table.getValueAt(i, 0)) {
				fields.add((GraphFields) table.getValueAt(i, 1));
			}
		}
		return fields;
	}

	private static class GraphFieldTableCellRenderer extends DefaultTableCellRenderer {

		public GraphFieldTableCellRenderer() {
			super();
		}

		protected void setValue(Object value) {
			GraphFields field = (GraphFields) value;
			setText((value == null) ? "" : field.getTitle());
		}

	}

}

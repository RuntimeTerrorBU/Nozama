package controllers;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * The ButtonColumn class provides a renderer and an editor that looks like a
 * JButton. The renderer and editor will then be used for a specified column in
 * the table. The TableModel will contain the String to be displayed on the
 * button.
 *
 * The button can be invoked by a mouse click or by pressing the space bar when
 * the cell has focus. Optionally a mnemonic can be set to invoke the button.
 * When the button is invoked the provided Action is invoked. The source of the
 * Action will be the table. The action command will contain the model row
 * number of the button that was clicked.
 *
 * @author - Ashley Bickham, Joshua Hunter, Austin Lehman, Tyler Ross
 * @version 1.0 (Apr 27, 2021)
 */
public class ButtonColumn extends AbstractCellEditor
		implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	private int mnemonic;
	private Border originalBorder;
	private Border focusBorder;

	private JButton renderButton;
	private JButton editButton;
	private Object editorValue;
	private boolean isButtonColumnEditor;

	/**
	 * Create the ButtonColumn to be used as a renderer and editor. The renderer and
	 * editor will automatically be installed on the TableColumn of the specified
	 * column.
	 *
	 * @param table, JTable containing the button renderer/editor
	 * @param action, Action to be invoked when the button is invoked
	 * @param column, integer of the column to which the button renderer/editor is added
	 */
	public ButtonColumn(JTable table, Action action, int column) {
		this.table = table;
		this.action = action;

		renderButton = new JButton();
		editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);
		originalBorder = editButton.getBorder();
		setFocusBorder(new LineBorder(Color.BLUE));

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
		table.addMouseListener(this);
	}

	/**
	 * Get border of the focus
	 *
	 * @return Border of the focus
	 */
	public Border getFocusBorder() {
		return focusBorder;
	}

	/**
	 * Set the focus border
	 *
	 * @param focusBorder, Border object to set
	 */
	public void setFocusBorder(Border focusBorder) {
		this.focusBorder = focusBorder;
		editButton.setBorder(focusBorder);
	}

	/**
	 * Get mnemonic of the function
	 *
	 * @return int representing the mnemonic of the buttons
	 */
	public int getMnemonic() {
		return mnemonic;
	}

	/**
	 * Set the mnemonic of the function
	 *
	 * @param mnemonic, int mnemonic to set
	 */
	public void setMnemonic(int mnemonic) {
		this.mnemonic = mnemonic;
		renderButton.setMnemonic(mnemonic);
		editButton.setMnemonic(mnemonic);
	}

	@Override
	/**
	 * Get the Table Cell Editor Component of the table
	 *
	 * @param table, JTable representing the table with the buttons
	 * @param value, Object representing the value of the table cell
	 * @param isSelected, boolean true if the button has been selected, false otherwise
	 * @return Component representing the edit button
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value == null) {
			editButton.setText("");
			editButton.setIcon(null);
		} else if (value instanceof Icon) {
			editButton.setText("");
			editButton.setIcon((Icon) value);
		} else {
			editButton.setText(value.toString());
			editButton.setIcon(null);
		}

		this.editorValue = value;
		return editButton;
	}

	@Override
	/**
	 * Get the cell editor value
	 *
	 * @return Object that represents the value of the editor
	 */
	public Object getCellEditorValue() {
		return editorValue;
	}

	/**
	 * Get the TableCellRenderer Component of the buttons
	 * 
	 * @param table, JTablerepresenting the table with the buttons
	 * @param value, Object representing the value of the table cell
	 * @param isSelected, boolean true if the button has been selected, false otherwise
	 * @param hasFocus, boolean true if there is a focus within the table, false otherwise
	 * @param row, int representing the rows of the table
	 * @param column, int representing the columns of the table
	 * @return Component of the table cell renderer
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(table.getSelectionBackground());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		if (hasFocus) {
			renderButton.setBorder(focusBorder);
		} else {
			renderButton.setBorder(originalBorder);
		}

		// renderButton.setText( (value == null) ? "" : value.toString() );
		if (value == null) {
			renderButton.setText("");
			renderButton.setIcon(null);
		} else if (value instanceof Icon) {
			renderButton.setText("");
			renderButton.setIcon((Icon) value);
		} else {
			renderButton.setText(value.toString());
			renderButton.setIcon(null);
		}

		return renderButton;
	}

	/**
	 * When a button has been pressed, stop editing and invoke the custom Action
	 *
	 * @param e, ActionEvent representing when a button has been clicked
	 */
	public void actionPerformed(ActionEvent e) {
		int row = table.convertRowIndexToModel(table.getEditingRow());
		fireEditingStopped();

		// Invoke the Action
		ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + row);
		action.actionPerformed(event);
	}

	/**
	 * When the mouse is pressed the editor is invoked. If you then then drag the
	 * mouse to another cell before releasing it, the editor is still active. Make
	 * sure editing is stopped when the mouse is released.
	 *
	 * @param e, MouseEvent representing when a button has been clicked
	 */
	public void mousePressed(MouseEvent e) {
		if (table.isEditing() && table.getCellEditor() == this)
			isButtonColumnEditor = true;
	}

	/**
	 * When the mouse is released the editing is stopped and the action completed
	 *
	 * @param e, MouseEvent representing when a button has been clicked
	 */
	public void mouseReleased(MouseEvent e) {
		if (isButtonColumnEditor && table.isEditing())
			table.getCellEditor().stopCellEditing();

		isButtonColumnEditor = false;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}

package bll.util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Helper class with one method that creates a table from the database entries
 */
public class TableUtil {
    /**
     * Created a JTable for given list of objects
     *
     * @param myList
     * @return
     */
    public static JTable createTable(ArrayList<?> myList) {
        int tableSize = myList.get(0).getClass().getDeclaredFields().length;
        String[] columnNames = new String[tableSize];
        int columnIndex = 0;
        for (java.lang.reflect.Field currentField : myList.get(0).getClass().getDeclaredFields()) {
            currentField.setAccessible(true);
            try {
                columnNames[columnIndex] = currentField.getName();
                columnIndex++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        DefaultTableModel myModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// all cells false
            }
        };
        myModel.setColumnIdentifiers(columnNames);
        for (Object o : myList) {
            Object[] obj = new Object[tableSize];
            int col = 0;
            for (java.lang.reflect.Field currentField : o.getClass().getDeclaredFields()) {
                currentField.setAccessible(true);
                try {
                    obj[col] = currentField.get(o);
                    col++;
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            myModel.addRow(obj);
        }
        return new JTable(myModel);
    }
}

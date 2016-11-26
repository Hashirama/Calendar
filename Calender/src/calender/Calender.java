/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.GregorianCalendar;
/**
 *
 * @author Hash
 */
public class Calender {
    static JButton jbut;
    static JFrame jframe;
    static Container container;
    static JPanel jpanel;
    static JLabel month;
    static JTable table;
    static DefaultTableModel model;
    static JScrollPane scroll;
    static JTable jtable;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        month = new JLabel("November");
        model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        table = new JTable(model);
        scroll = new JScrollPane(table);
        
        jframe = new JFrame("Calendar");
        jframe.setSize(360, 375);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jpanel = new JPanel(null);
        jpanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
        
        
        container = jframe.getContentPane();
        container.setLayout(null);
        container.add(jpanel);
        
        jpanel.add(month);
        jpanel.add(scroll);
        jpanel.setBounds(15, 15, 320, 320);
        month.setBounds(120, 20, 100, 25);
        scroll.setBounds(10,50,300,250);
        
        
        String[] days = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        for (int i = 0; i != 7; ++i) {
            model.addColumn(days[i]);
        }
        
        table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        table.getTableHeader().setResizingAllowed(false);
	table.getTableHeader().setReorderingAllowed(false);
        
        table.setRowHeight(38);
        model.setColumnCount(7);
        model.setRowCount(6);
        fill(10, 2016);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }
    public static void fill(int month, int year) {
        int daysInMonth, firstDayOfWeek;
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        daysInMonth = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	firstDayOfWeek = cal.getFirstDayOfWeek();

        int cnt = 1;
        for(int j = 0; j != 6; ++j) {
            for(int i = firstDayOfWeek - 1; i != 7 && cnt != daysInMonth +1; ++i) {
                model.setValueAt(cnt, j, i);
                if(cnt == 10) {
                    table.setDefaultRenderer(table.getColumnClass(0), new renderer());
                }
                ++cnt;
            }
            firstDayOfWeek = 1;
        }
                
    }
static class renderer extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        setBackground(table.getBackground());
        if (value != null){
            if (Integer.parseInt(value.toString()) == 10){ //Today
                setBackground(Color.LIGHT_GRAY);
            }
        }
        setBorder(null);
        setForeground(Color.black);
        return this;  
        }
   }
}
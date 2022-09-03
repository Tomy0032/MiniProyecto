package InterfazGrafica;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Persona;
import DAO.DAOPersona;


public class btnMostrar {

public void mostrar() {
		
		JFrame frame = new JFrame("Personas y roles");
		frame.setBounds(200, 100, 200, 100);
		
		DefaultTableModel modelo= new DefaultTableModel();
		final JTable table = new JTable(modelo);
		
		final String[] columnNames = {"NOMBRE","APELLIDO","ROL"};
		
		for(int column = 0; column < columnNames.length; column++){
			
			modelo.addColumn(columnNames[column]);
			
		}
			
			Object [] fila = new Object[columnNames.length]; 
			
					LinkedList<Persona> todasPersonas = DAOPersona.findAll();
					
					for (int i=0;i<todasPersonas.size();i++){
						
						String nombre = todasPersonas.get(i).getNombre1();
						String apellido = todasPersonas.get(i).getApellido1();
						int idRol = todasPersonas.get(i).getIdRol();
						
					
						fila[0] = nombre;
						fila[1] = apellido;
						fila[2] = idRol;
					
						modelo.addRow(fila); 
					}
					
					table.setPreferredScrollableViewportSize(new Dimension(500, 200));
				
					JScrollPane scrollPane = new JScrollPane(table);
					
					JPanel panel = new JPanel();
					panel.add(scrollPane);	
					frame.add(panel);
					frame.pack();
					frame.setVisible(true);

					
		}
}

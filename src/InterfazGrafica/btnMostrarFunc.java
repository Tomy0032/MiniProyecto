package InterfazGrafica;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.RolFuncionalidad;
import DAO.DAORolFuncionalidad;

public class btnMostrarFunc extends JFrame {
	 
		
		public void mostrar() {
			
			JFrame frame = new JFrame("Funcionalidades");
			frame.setBounds(EXIT_ON_CLOSE, ABORT, 200, 100);
			
			DefaultTableModel modelo= new DefaultTableModel();
			final JTable table = new JTable(modelo);
			
			final String[] columnNames = {"ROL","FUNCIONALIDAD"};
			
			for(int column = 0; column < columnNames.length; column++){
				
				modelo.addColumn(columnNames[column]);
				
			}
				
				Object [] fila = new Object[columnNames.length]; 
				
						LinkedList<RolFuncionalidad> todasFuncionalidades = DAORolFuncionalidad.findAll();
						
						for (int i=0;i<todasFuncionalidades.size();i++){
							
							String nombreRol = todasFuncionalidades.get(i).getNombreRol();
							String nombreFuncionalidad = todasFuncionalidades.get(i).getNombreFuncionalidad();
							
						
							fila[0] = nombreRol;
							fila[1] = nombreFuncionalidad;
							
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



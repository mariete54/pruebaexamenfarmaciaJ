package com.hibernate.gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.MedicamentoDAO;
import com.hibernate.model.Medicamento;
import com.hibernate.model.Formato;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class App {

	private JFrame frame;
	MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
	private JTextField txtFecha;
	private JTextField txtNombre;
	private JTextField txtId;
	JComboBox cbFormato = new JComboBox();
	JCheckBox chStock = new JCheckBox("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	void actualizarTabla(DefaultTableModel modelMedicamento, DefaultTableModel modelSinStock) {
		modelMedicamento.setRowCount(0);
		List<Medicamento> medicamentos = medicamentoDAO.selectAllMedicamentos();
		for (Medicamento m : medicamentos) {
			Object[] row = new Object[5];
			row[0] = m.getIdmedicamento();
			row[1] = m.getNombre();
			row[2] = m.getFormato();
			row[3] = m.getFechacaducidad();
			row[4] = m.isStock();
			modelMedicamento.addRow(row);
		}

		modelSinStock.setRowCount(0);
		List<Medicamento> sinstock = medicamentoDAO.selectAllSinStock();
		for (Medicamento m : sinstock) {
			Object[] row = new Object[5];
			row[0] = m.getIdmedicamento();
			row[1] = m.getNombre();
			row[2] = m.getFormato();
			row[3] = m.getFechacaducidad();
			row[4] = m.isStock();
			modelSinStock.addRow(row);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		DefaultTableModel modelMedicamento = new DefaultTableModel();
		modelMedicamento.addColumn("Id");
		modelMedicamento.addColumn("Nombre");
		modelMedicamento.addColumn("Formato");
		modelMedicamento.addColumn("fecha Caducidad");
		modelMedicamento.addColumn("Stock");

		List<Medicamento> medicamentos = medicamentoDAO.selectAllMedicamentos();
		for (Medicamento m : medicamentos) {
			Object[] row = new Object[5];
			row[0] = m.getIdmedicamento();
			row[1] = m.getNombre();
			row[2] = m.getFormato();
			row[3] = m.getFechacaducidad();
			row[4] = m.isStock();
			modelMedicamento.addRow(row);
		}

		JTable tableMedicamento = new JTable(modelMedicamento);
		tableMedicamento.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableMedicamento.setBounds(63, 12, 398, 125);
		frame.getContentPane().add(tableMedicamento);
		JScrollPane scrollPaneMedicamento = new JScrollPane(tableMedicamento);
		scrollPaneMedicamento.setBounds(32, 24, 356, 167);
		frame.getContentPane().add(scrollPaneMedicamento);
		tableMedicamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableMedicamento.getSelectedRow();
				TableModel model = tableMedicamento.getModel();
				txtId.setText(model.getValueAt(index, 0).toString());
				txtNombre.setText(model.getValueAt(index, 1).toString());
				cbFormato.setSelectedItem(model.getValueAt(index, 2));
				txtFecha.setText(model.getValueAt(index, 3).toString());
				if (model.getValueAt(index, 4).toString() == "false") {
					chStock.setSelected(false);
				} else {
					chStock.setSelected(true);
				}

			}
		});

		DefaultTableModel modelSinStock = new DefaultTableModel();
		modelSinStock.addColumn("Id");
		modelSinStock.addColumn("Nombre");
		modelSinStock.addColumn("Formato");
		modelSinStock.addColumn("fecha Caducidad");
		modelSinStock.addColumn("Stock");

		List<Medicamento> sinstock = medicamentoDAO.selectAllSinStock();
		for (Medicamento m : sinstock) {
			Object[] row = new Object[5];
			row[0] = m.getIdmedicamento();
			row[1] = m.getNombre();
			row[2] = m.getFormato();
			row[3] = m.getFechacaducidad();
			row[4] = m.isStock();
			modelSinStock.addRow(row);
		}

		JTable tableSinStock = new JTable(modelSinStock);
		tableSinStock.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableSinStock.setBounds(63, 12, 398, 125);
		frame.getContentPane().add(tableSinStock);
		JScrollPane scrollPaneSinStock = new JScrollPane(tableSinStock);
		scrollPaneSinStock.setBounds(465, 24, 363, 167);
		frame.getContentPane().add(scrollPaneSinStock);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(32, 233, 60, 17);
		frame.getContentPane().add(lblId);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(32, 262, 60, 17);
		frame.getContentPane().add(lblNombre);

		JLabel lblFormato = new JLabel("Formato:");
		lblFormato.setBounds(32, 291, 60, 17);
		frame.getContentPane().add(lblFormato);

		JLabel lblFechaCaducidad = new JLabel("Fecha Caducidad:");
		lblFechaCaducidad.setBounds(32, 319, 111, 17);
		frame.getContentPane().add(lblFechaCaducidad);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(32, 348, 60, 17);
		frame.getContentPane().add(lblStock);

		txtFecha = new JTextField();
		txtFecha.setBounds(161, 317, 114, 21);
		frame.getContentPane().add(txtFecha);
		txtFecha.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(161, 260, 114, 21);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(161, 231, 114, 21);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);

		chStock.setBounds(161, 344, 114, 25);
		frame.getContentPane().add(chStock);
		cbFormato.setModel(new DefaultComboBoxModel(Formato.values()));

		cbFormato.setBounds(161, 286, 114, 26);
		frame.getContentPane().add(cbFormato);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean errores = false;
				if (txtNombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nombre vacio", "Error", JOptionPane.ERROR_MESSAGE);
					errores = true;
				}
				if (!txtFecha.getText().matches("(ENE|FEB|MAR|ABR|MAY|JUN|JUL|AGO|SEP|OCT|NOV|DIC)(27|28|29)")) {
					JOptionPane.showMessageDialog(null, "Fecha incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
					errores = true;
				}
				if (!errores) {
					Medicamento m = new Medicamento(txtNombre.getText(), (Formato) cbFormato.getSelectedItem(),
							txtFecha.getText(), chStock.isSelected());
					medicamentoDAO.insertMedicamento(m);
					JOptionPane.showMessageDialog(null, "Medicamento añadido");
					actualizarTabla(modelMedicamento, modelSinStock);
				}

			}
		});
		btnGuardar.setBounds(12, 396, 105, 27);
		frame.getContentPane().add(btnGuardar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean errores = false;
				if (txtNombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nombre vacio", "Error", JOptionPane.ERROR_MESSAGE);
					errores = true;
				}
				if (!txtFecha.getText().matches("(ENE|FEB|MAR|ABR|MAY|JUN|JUL|AGO|SEP|OCT|NOV|DIC)(27|28|29)")) {
					JOptionPane.showMessageDialog(null, "Fecha incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
					errores = true;
				}
				if (!errores) {
				Medicamento m = medicamentoDAO.selectMedicamento(Integer.parseInt(txtId.getText()));
				m.setNombre(txtNombre.getText());
				m.setFormato((Formato) cbFormato.getSelectedItem());
				m.setFechacaducidad(txtFecha.getText());
				m.setStock(chStock.isSelected());
				medicamentoDAO.updateMedicamento(m);
				JOptionPane.showMessageDialog(null, "Medicamento actualizado");
				actualizarTabla(modelMedicamento, modelSinStock);
			}
			}
		});
		btnActualizar.setBounds(129, 396, 105, 27);
		frame.getContentPane().add(btnActualizar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				medicamentoDAO.deleteMedicamento(Integer.parseInt(txtId.getText()));
				JOptionPane.showMessageDialog(null, "Medicamento eliminado");
				actualizarTabla(modelMedicamento, modelSinStock);
			}
		});
		btnBorrar.setBounds(254, 396, 105, 27);
		frame.getContentPane().add(btnBorrar);

	}
}

package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;

import simulator.misc.Pair;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.Weather;

public class RoadsWeatherHistory extends JDialog{
	
	private JComboBox<Weather> cbWeather;
	private int estado;
	private WeatherHistoryModel e;
	private List<List<Pair<Road,Weather>>> list;
	
	public RoadsWeatherHistory(Frame parent) {
		super(parent,true); //Para que sea modal y no se pueda clicar fuera del cuadrado
		initGUI();
		list = new ArrayList<List<Pair<Road,Weather>>>();
			
	}
	private void initGUI() {
		cbWeather = new JComboBox<Weather>();
		
		this.setTitle("Roads Weather History");
		
		//main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
			//Label
			JPanel txtPanel = new JPanel();
			txtPanel.setLayout(new FlowLayout());
			JLabel txtVentana = new JLabel("Select a weather and press update.");
			txtVentana.setAlignmentX(CENTER_ALIGNMENT);
			txtPanel.add(txtVentana);
			
		//secondary Panel
		JPanel secondaryPanel = new JPanel();
		secondaryPanel.setLayout(new FlowLayout());
		
			JLabel txtC02 = new JLabel("Weather");
			cbWeather.setModel(new DefaultComboBoxModel<Weather>());
			cbWeather.setPreferredSize(new Dimension(80, 25));
			
		//button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());	
		
			//botonCancelar
			JButton botonCancel = new JButton("Cancel");
			botonCancel.setToolTipText("Cancelar la creacion de un nuevo evento");
			botonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}});
			//botonOk
			JButton botonOk = new JButton("update");
			botonOk.setToolTipText("Crea el nuevo evento");
			botonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				update();
			}});
			
		JPanel southPanel = new JPanel();
		secondaryPanel.setLayout(new BorderLayout());
		
		e = new WeatherHistoryModel();
		//e.setList(list);
		JTable table = new JTable(e);
		
		JScrollPane jsPane = new JScrollPane();
		jsPane.setPreferredSize(new Dimension(500,200));
		jsPane.setViewportView(table);
		southPanel.add(jsPane);
		
		
		//Añadir al buttonPanel
		buttonPanel.add(botonCancel);
		buttonPanel.add(botonOk);
			
		//Añadir al secundaryPanel
		secondaryPanel.add(txtC02);
		secondaryPanel.add(cbWeather);

		//Añadir al mainPanel
		mainPanel.add(txtPanel);
		mainPanel.add(secondaryPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(southPanel);
		
		
		//Inicializar Weather CB
		cbWeather.addItem(Weather.CLOUDY);
		cbWeather.addItem(Weather.RAINY);
		cbWeather.addItem(Weather.STORM);
		cbWeather.addItem(Weather.SUNNY);
		cbWeather.addItem(Weather.WINDY);
		
		//Añadir al JDialog
		this.setContentPane(mainPanel);
		this.pack();
		this.setResizable(false);
		this.setVisible(false); //No poner ser visible porque si no crea una ventana vacia extra
		this.setLocationRelativeTo(null); //Para que aparezca siempre en medio de la pantalla
	}
	private void cancel() {
		this.setVisible(false);
		this.estado =0;
	}
	private void update() {
		e.setList(list);
		e.fireTableDataChanged();
		this.estado =1;		
	}
	public int open(RoadMap roadMap) {
		List<Pair<Road, Weather>> l2 = new ArrayList<Pair<Road,Weather>>();
		if(roadMap !=null) {
			for(Road road : roadMap.getRoads()) {
				if(road.getWeather() == Weather.SUNNY) {
					Pair<Road, Weather> pair = new Pair<Road, Weather>(road, road.getWeather());
					l2.add(pair);
				}
			}	
		}	
		list.add(l2);
		
		return estado;
		
	}

}

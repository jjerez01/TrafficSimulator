package simulator.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;

public class SpeedTableModel extends AbstractTableModel implements TrafficSimObserver {
	
	
	private static final long serialVersionUID = 1L;
	private List<Vehicle> _listaVehiculos;
	private String _cols[] = { "Id", "medium speed"}; 
	private Map<Vehicle,List<Integer>> vmap;
	
	
	public SpeedTableModel(Controller ctrl) {		
		ctrl.addObserver(this);
		_listaVehiculos = new ArrayList<Vehicle>();
		vmap = new HashMap<Vehicle, List<Integer>>();
	}
	@Override
	public int getRowCount() {
		return _listaVehiculos.size();
	}
	@Override
	public int getColumnCount() {
		return _cols.length;
	}
	@Override
	public String getColumnName(int column) {
		return _cols[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = "";	
		Vehicle vehiculo =_listaVehiculos.get(rowIndex);
		
		switch (columnIndex) {
			case 0:
				s = vehiculo.getId();
				break;
			case 1:
				if(!vmap.containsKey(vehiculo)) {
					List<Integer> li = new ArrayList<Integer>();
					li.add(vehiculo.getSpeed());
					vmap.put(vehiculo, li);
				}
				else {
					List<Integer> li = vmap.get(vehiculo);
					li.add(vehiculo.getSpeed());
					vmap.replace(vehiculo, li);
				}
				
				double mean = 0.0;
				
				for(Integer i : vmap.get(vehiculo)) {
					mean += i;
				}
				
				mean /= vmap.get(vehiculo).size();
				
				s += Integer.toString((int) mean);
				break;
				
		}
	return s;
	}
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> listaEventos, int time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> listaEventos, int time) {
		_listaVehiculos =map.getVehicles();
		fireTableDataChanged();
		
	}
	@Override
	public void onEventAdded(RoadMap map, List<Event> listaEventos, Event e, int time) {
		_listaVehiculos =map.getVehicles();
		fireTableDataChanged();
		
	}
	@Override
	public void onReset(RoadMap map, List<Event> listaEventos, int time) {
		_listaVehiculos =map.getVehicles();
		fireTableDataChanged();
		
	}
	@Override
	public void onRegister(RoadMap map, List<Event> listaEventos, int time) {
		_listaVehiculos =map.getVehicles();
		fireTableDataChanged();
		
	}
	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}
}

package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import simulator.misc.Pair;
import simulator.model.Road;
import simulator.model.Vehicle;
import simulator.model.Weather;

public class WeatherHistoryModel extends AbstractTableModel {

	private List<List<Pair<Road, Weather>>> list;
	
	private String _cols[] = { "tick", "roads",};
	
	public WeatherHistoryModel() {
		list = new ArrayList<List<Pair<Road,Weather>>>();
	}
	
	public void setList(List<List<Pair<Road, Weather>>> list) {
		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return _cols.length;
	}

	@Override
	public int getRowCount() {

		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = "";	
		switch (columnIndex) {
			case 0:
				s = rowIndex + 1;
				break;
			case 1:
				s = "[";
				if(rowIndex != 0) {
					List<Pair<Road, Weather>> l = list.get(rowIndex);
					List<Road> ro = new ArrayList<Road>();
					for(Pair<Road, Weather> p : l) {
						ro.add(p.getFirst());
					}
					for(Road r : ro) {
						s += r.getId();
						if(ro.indexOf(r) < ro.size()-1) {
							s += ",";
						}
					}
				s += "]";
				
				}
				break;
			}
			return s;
		}

}

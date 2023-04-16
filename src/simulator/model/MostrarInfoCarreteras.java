package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import simulator.misc.Pair;

public class MostrarInfoCarreteras implements TrafficSimObserver{

	private Map<Integer,List<Pair<Road, Integer>>> hmap;
	
	public MostrarInfoCarreteras() {
		hmap = new HashMap<Integer,List<Pair<Road, Integer>>>();
	}
	
	public void mostrar() {
		for(Integer i : hmap.keySet()) {
			String s = i +": [";
			 for(Pair<Road,Integer> p : hmap.get(i)){
				 s += "( " + p.getFirst() + " , " + p.getSecond() + " )";
			 }
			 s += "]";
			 System.out.println(s);
		}
		
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> listaEventos, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> listaEventos, int time) {
		
		List<Pair<Road, Integer>> l = new ArrayList<Pair<Road,Integer>>();
		for(Road r: map.getRoads()) {
			if(!r.getVehicles().isEmpty()) {
				Pair<Road, Integer> p = new Pair<Road, Integer>(r, r.getVehicles().size());
				l.add(p);
			}
		}
		if(!l.isEmpty()) {
			hmap.put(time, l);
		}
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> listaEventos, Event e, int time) {
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> listaEventos, int time) {
		hmap = new HashMap<Integer,List<Pair<Road, Integer>>>();
	}

	@Override
	public void onRegister(RoadMap map, List<Event> listaEventos, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}
	
}

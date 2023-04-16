package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetContClassEvent extends Event {
	
	private List<Pair<String,Integer>> cs;
	
	public SetContClassEvent(int time, List<Pair<String,Integer>> cs) {
		super(time);
		this.cs = cs;
		if(cs == null) {
			throw new IllegalArgumentException("weather list is null");
		}
	}


	@Override
	void execute(RoadMap map) {
		for(Pair<String,Integer> c : cs) {
			
			if(map.getVehicle(c.getFirst()) == null) {
				throw new IllegalArgumentException("no road to introduce contamination");
			}
			else {
				map.getVehicle(c.getFirst()).setGradoContaminacion(c.getSecond());
			}
		}		
	}


	@Override
	public String toString() {
		String ret = "Change CO2 class : [";
		for(Pair<String,Integer> c : cs) {
			ret += "(" + c.getFirst()+ ", " + c.getSecond() + "),";
		}
		ret += "]";
		return ret;
	}
}



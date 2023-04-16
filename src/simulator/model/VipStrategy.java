package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class VipStrategy implements DequeuingStrategy {

	private int limit;
	
	public VipStrategy(int limit) {
		this.limit = limit;
	}
	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> list = new ArrayList<Vehicle>();
		int aux  = limit;
		for(Vehicle v : q) {
			if(v.getId().endsWith("_vip") && aux > 0) {
				list.add(v);
				aux--;
			}
		}
		
		for(Vehicle v : q) {
			if(!list.contains(v) && aux > 0) {
				list.add(v);
				aux--;
			}
		}
		
		
		return list;
	}

}

package simulator.model;

import java.util.List;

public class VipLSStrategy implements LightSwitchingStrategy{

	private int timeSlot;
	private boolean isVip;
	private String vipTag;
	
	public VipLSStrategy(int timeSlot, String vipTag) {
		this.timeSlot = timeSlot;
		this.vipTag = vipTag;
	}
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		
		
		
		if(roads.isEmpty()) {
			return -1;
		}
		if(currGreen == -1) {

			return 0;
		}
		if(currTime-lastSwitchingTime <this.timeSlot) {
			return currGreen;
		}
		else {
			int currGreenVip = currGreen+1;
			while(currGreenVip != currGreen) {
				for(Vehicle v : qs.get(currGreenVip)) {
					if(v.getId().endsWith(vipTag)) {
						currGreen = currGreenVip;
						return currGreenVip;
					}
				}
				currGreenVip++;
				currGreenVip = currGreenVip % roads.size();
			}
			
			return (currGreen+1) % roads.size();
		}
	
		
	}
	
}

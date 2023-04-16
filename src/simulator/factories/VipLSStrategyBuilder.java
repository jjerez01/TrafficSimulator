package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.VipLSStrategy;

public class VipLSStrategyBuilder extends Builder<LightSwitchingStrategy> {
	public VipLSStrategyBuilder() {
		super("vip_lss");
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		int timeSlot =  1;
		String id = "vip";
		if(data.has("viptag")) id = data.getString("viptag");
		if(data.has("timeslot")) timeSlot= data.getInt("timeslot");
		return new VipLSStrategy(timeSlot,id);
	}
}

package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.VipStrategy;

public class VipStrategyBuilder  extends Builder<DequeuingStrategy>{

	public VipStrategyBuilder(){
		super("vip_dqs");
	}

	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
		return new VipStrategy(data.getInt("limit"));
	}
}

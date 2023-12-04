package com.teammoeg.steampowered.create.flywheel;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.Create;
import com.teammoeg.steampowered.SteamPowered;

public class PartialModels {
    
    public static final PartialModel 
        FLYWHEEL = block("flywheel/wheel"),
        FLYWHEEL_UPPER_ROTATING = block("flywheel/upper_rotating_connector"),
        FLYWHEEL_LOWER_ROTATING = block("flywheel/lower_rotating_connector"),
        FLYWHEEL_UPPER_SLIDING = block("flywheel/upper_sliding_connector"),
        FLYWHEEL_LOWER_SLIDING = block("flywheel/lower_sliding_connector"),
        FURNACE_GENERATOR_FRAME = createBlock("furnace_engine/frame");

    private static PartialModel block(String path) {
		return new PartialModel(SteamPowered.rl("block/" + path));
	}

    private static PartialModel createBlock(String path) {
		return new PartialModel(Create.asResource("block/" + path));
	}

    public static void init() {
		// init static fields
	}
}

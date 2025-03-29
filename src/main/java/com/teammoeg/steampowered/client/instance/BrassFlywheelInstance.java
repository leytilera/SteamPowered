package com.teammoeg.steampowered.client.instance;

import com.teammoeg.steampowered.block.SPBlockPartials;
import com.teammoeg.steampowered.oldcreatestuff.OldFlywheelBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;

public class BrassFlywheelInstance extends AbstractSPFlywheelInstance {
    public BrassFlywheelInstance(VisualizationContext modelManager, OldFlywheelBlockEntity tile, float d) {
        super(modelManager, tile, d);
    }

    @Override
    protected PartialModel getWheelModel() {
        return SPBlockPartials.BRASS_FLYWHEEL;
    }

    @Override
    protected PartialModel getUpperSlidingModel() {
        return SPBlockPartials.BRASS_FLYWHEEL_UPPER_SLIDING;
    }

    @Override
    protected PartialModel getLowerSlidingModel() {
        return SPBlockPartials.BRASS_FLYWHEEL_LOWER_SLIDING;
    }

    @Override
    protected PartialModel getUpperRotatingModel() {
        return SPBlockPartials.BRASS_FLYWHEEL_UPPER_ROTATING;
    }

    @Override
    protected PartialModel getLowerRotatingModel() {
        return SPBlockPartials.BRASS_FLYWHEEL_LOWER_ROTATING;
    }
}

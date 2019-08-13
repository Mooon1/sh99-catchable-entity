package sh99.catchable_entity.Item;

import sh99.catchable_entity.Item.Ball.HyperBall;
import sh99.catchable_entity.Item.Ball.MineBall;
import sh99.catchable_entity.Item.Ball.SuperBall;

public enum Catchables {

    MINEBALL(new MineBall()),
    SUPERBALL(new SuperBall()),
    HYPERBALL(new HyperBall());

    private final Catchable catchable;

    Catchables(final Catchable catchable){
        this.catchable = catchable;
    }

    public Catchable getCatchable() {
        return catchable;
    }
}
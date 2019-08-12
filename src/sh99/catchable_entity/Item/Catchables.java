package sh99.catchable_entity.Item;

import sh99.catchable_entity.Item.Ball.MineBall;

public enum Catchables {

    MINEBALL(new MineBall());

    private final Catchable catchable;

    Catchables(final Catchable catchable){
        this.catchable = catchable;
    }

    public Catchable getCatchable() {
        return catchable;
    }
}
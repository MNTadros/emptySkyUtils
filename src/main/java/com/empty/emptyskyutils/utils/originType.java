package com.empty.emptyskyutils.utils;

public enum originType {
    FARMER("Farmer", new originEffect[] {
            new originEffect(effectType.PERMANENT_HASTE, 1)
    }),
    MINER("Miner", new originEffect[] {
            new originEffect(effectType.PERMANENT_HASTE, 1)
    }),
    RANCHER("Rancher", new originEffect[] {
            new originEffect(effectType.PERMANENT_LUCK, 0),
            new originEffect(effectType.MINING_SPEED, 1)
    }),
    TRADER("Trader", new originEffect[] {
            new originEffect(effectType.MINING_SPEED, 0),
            new originEffect(effectType.PHOENIX,1)
    });

    private final String name;
    private final originEffect[] effects;

    originType(String name, originEffect[] effects) {
        this.name = name;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public originEffect[] getEffects() {
        return effects;
    }
}

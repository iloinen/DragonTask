package main.models;

public enum Rarity {

    /*0*/ UNDEFINED,
    /*1*/ COMMON,
    /*2*/ RARE,
    /*3*/ VERY_RARE,
    /*4*/ EPIC,
    /*5*/ LEGEND,
    /*6*/ HEROIC;

    public static Rarity getByValue(int value) {
        try {
            return values()[value];
        } catch (Exception e) {
            return defaultRarity();
        }
    }

    private static Rarity defaultRarity() {
        return Rarity.UNDEFINED;
    }

}

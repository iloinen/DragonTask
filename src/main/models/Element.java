package main.models;

public enum Element {

    /*0*/  UNDEFINED,
    /*1*/  TERRA,
    /*2*/  FLAME,
    /*3*/  SEA,
    /*4*/  NATURE,
    /*5*/  ELECTRIC,
    /*6*/  ICE,
    /*7*/  METAL,
    /*8*/  DARK,
    /*9*/  LIGHT,
    /*10*/ WAR,
    /*11*/ PURE,
    /*12*/ LEGEND,
    /*13*/ PRIMAL,
    /*14*/ WIND,
    /*15*/ TIME,

    /*16*/ HAPPY,
    /*17*/ CHAOS,
    /*18*/ MAGIC,
    /*19*/ SOUL,
    /*20*/ BEAUTY,
    /*21*/ DREAM;

    public static Element[] getAllByValues(String[] indexes) {
        Element[] elements = new Element[indexes.length];

        for (int i = 0; i < indexes.length; i++) {
            int index = Integer.parseInt(indexes[i].trim());
            elements[i] = getByValue(index);
        }

        return elements;
    }

    public static Element getByValue(int value) {
        try {
            return values()[value];
        } catch (Exception e) {
            return defaultElement();
        }
    }

    private static Element defaultElement() {
        return Element.UNDEFINED;
    }

}

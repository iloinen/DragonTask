package main.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ModelsTest {

    @Test
    void baseDragonToString_ifNoIdAndNoElements() {
        BaseDragon dragon = new BaseDragon("Kiscica", 1, "Leírás");
        String expected = "Kiscica (COMMON)\n\t//no elements//\n\tLeírás";

        assertEquals(expected, dragon.toString());
    }

    @Test
    void baseDragonToString_ifNoElements() {
        BaseDragon dragon = new BaseDragon("Kiscica", 1, "Leírás");
        dragon.setId(1);
        String expected = "1. Kiscica (COMMON)\n\t//no elements//\n\tLeírás";

        assertEquals(expected, dragon.toString());
    }

    @Test
    void baseDragonToString_ifAllData() {
        String[] elements = {"1", "3"};
        BaseDragon dragon = new BaseDragon(1, "Kiscica", 1, "Leírás", elements);
        String expected = "1. Kiscica (COMMON)\n\t[TERRA, SEA]\n\tLeírás";

        assertEquals(expected, dragon.toString());
    }

    @Test
    void rarity_getByValue_ifValidValues() {
        Rarity undefined = Rarity.getByValue(0);
        assertEquals(Rarity.UNDEFINED, undefined);

        Rarity common = Rarity.getByValue(1);
        assertEquals(Rarity.COMMON, common);

        Rarity epic = Rarity.getByValue(4);
        assertEquals(Rarity.EPIC, epic);

        Rarity heroic = Rarity.getByValue(6);
        assertEquals(Rarity.HEROIC, heroic);
    }

    @Test
    void rarity_getByValue_ifInvalidValues() {
        Rarity invalid1 = Rarity.getByValue(-1);
        assertEquals(Rarity.UNDEFINED, invalid1);

        Rarity invalid2 = Rarity.getByValue(7);
        assertEquals(Rarity.UNDEFINED, invalid2);
    }

    @Test
    void element_getByValue_ifValidValues() {
        Element undefined = Element.getByValue(0);
        assertEquals(Element.UNDEFINED, undefined);

        Element terra = Element.getByValue(1);
        assertEquals(Element.TERRA, terra);

        Element war = Element.getByValue(10);
        assertEquals(Element.WAR, war);

        Element dream = Element.getByValue(21);
        assertEquals(Element.DREAM, dream);
    }

    @Test
    void element_getByValue_ifInvalidValues() {
        Element invalid1 = Element.getByValue(-1);
        assertEquals(Element.UNDEFINED, invalid1);

        Element invalid2 = Element.getByValue(22);
        assertEquals(Element.UNDEFINED, invalid2);
    }

    @Test
    void element_getAllByValues_ifValidValues() {
        String[] values = {"1"};
        String[] expected = {"TERRA"};
        Element[] elements = Element.getAllByValues(values);
        assertEquals(Arrays.toString(expected), Arrays.toString(elements));

        values = new String[]{"15"};
        expected = new String[]{"TIME"};
        elements = Element.getAllByValues(values);
        assertEquals(Arrays.toString(expected), Arrays.toString(elements));
    }

    @Test
    void element_getAllByValues_rightOrder() {
        String[] values = {"10", "-1"};
        String[] expected = {"WAR", "UNDEFINED"};
        Element[] elements = Element.getAllByValues(values);
        assertEquals(Arrays.toString(expected), Arrays.toString(elements));

        values = new String[]{"15", "2", "9"};
        expected = new String[]{"TIME", "FLAME", "LIGHT"};
        elements = Element.getAllByValues(values);
        assertEquals(Arrays.toString(expected), Arrays.toString(elements));
    }

}
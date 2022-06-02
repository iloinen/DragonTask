package main.services;

import main.models.BaseDragon;
import main.models.Element;
import main.models.Rarity;
import main.models.UserDragon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void countNoNamedUserDragons_whenAllNamed() {
        List<UserDragon> userDragons = new ArrayList<>();
        userDragons.add(new UserDragon(null, "Name", 0, 0));
        userDragons.add(new UserDragon(null, "Name", 0, 0));
        userDragons.add(new UserDragon(null, "Name", 0, 0));

        assertEquals(0, Task.countNoNamedUserDragons(userDragons));
    }

    @Test
    void countNoNamedUserDragons_whenOneUnnamed() {
        List<UserDragon> userDragons = new ArrayList<>();
        userDragons.add(new UserDragon());
        userDragons.add(new UserDragon(null, "Name", 0, 0));
        userDragons.add(new UserDragon(null, "Name", 0, 0));

        assertEquals(1, Task.countNoNamedUserDragons(userDragons));
    }

    @Test
    void countNoNamedUserDragons_whenAllUnnamed() {
        List<UserDragon> userDragons = new ArrayList<>();
        userDragons.add(new UserDragon());
        userDragons.add(new UserDragon());
        userDragons.add(new UserDragon());

        assertEquals(3, Task.countNoNamedUserDragons(userDragons));
    }

    @Test
    void collectNotOwnedDragons_whenAllOwned() {
        List<BaseDragon> bases = generateBases(3);
        List<UserDragon> users = convertBaseToUser(bases.get(0), bases.get(1), bases.get(2));

        assertEquals(0, Task.collectNotOwnedDragons(bases, users).size());
    }

    @Test
    void collectNotOwnedDragons_whenNoneOwned() {
        List<BaseDragon> bases = generateBases(3);
        List<UserDragon> users = convertBaseToUser();

        assertEquals(bases.size(), Task.collectNotOwnedDragons(bases, users).size());
    }

    @Test
    void collectNotOwnedDragons_whenOneOwned() {
        List<BaseDragon> bases = generateBases(4);
        List<UserDragon> users = convertBaseToUser(bases.get(2));

        List<BaseDragon> notOwned = Task.collectNotOwnedDragons(bases, users);

        assertEquals(3, notOwned.size());
        assertContains(notOwned, bases.get(0).getId());
        assertContains(notOwned, bases.get(1).getId());
        assertContains(notOwned, bases.get(3).getId());
    }

    @Test
    void collectNotOwnedDragons_whenDragonsNotInIDOrder() {
        List<BaseDragon> bases = new ArrayList<>();
        bases.add(new BaseDragon(10));
        bases.add(new BaseDragon(12));
        bases.add(new BaseDragon(8));
        bases.add(new BaseDragon(6));

        List<UserDragon> users = convertBaseToUser(bases.get(3), bases.get(2));

        List<BaseDragon> notOwned = Task.collectNotOwnedDragons(bases, users);

        assertEquals(2, notOwned.size());
        assertContains(notOwned, bases.get(0).getId());
        assertContains(notOwned, bases.get(1).getId());
    }

    @Test
    void collectWithElements_whenNoResult() {
        List<BaseDragon> bases = generateBases(3, Element.TERRA);
        List<UserDragon> userDragons = convertBaseToUser(bases.get(0), bases.get(1), bases.get(2));

        List<UserDragon> flameElement = Task.collectWithElement(userDragons, Element.FLAME);
        assertEquals(0, flameElement.size());
    }

    @Test
    void collectWithElements_whenAllResult() {
        List<BaseDragon> bases = generateBases(3, Element.FLAME);
        List<UserDragon> userDragons = convertBaseToUser(bases.get(0), bases.get(1), bases.get(2));

        List<UserDragon> flameElement = Task.collectWithElement(userDragons, Element.FLAME);
        assertEquals(3, flameElement.size());
    }

    @Test
    void collectWithElements_whenNotTheFirstElement() {
        List<BaseDragon> bases = generateBases(3, Element.TERRA, Element.FLAME);
        List<UserDragon> userDragons = convertBaseToUser(bases.get(0), bases.get(1), bases.get(2));

        List<UserDragon> flameElement = Task.collectWithElement(userDragons, Element.FLAME);
        assertEquals(3, flameElement.size());
    }

    @Test
    void collectWithElements_whenOneResult() {
        List<BaseDragon> bases = generateBases(3, Element.TERRA, Element.FLAME);
        BaseDragon baseDragon = new BaseDragon(111);
        baseDragon.setElements(new Element[]{Element.SEA, Element.NATURE, Element.ELECTRIC});
        bases.add(2, baseDragon);

        List<UserDragon> userDragons = convertBaseToUser(bases.get(0), bases.get(1), bases.get(2), bases.get(3));

        List<UserDragon> electricElement = Task.collectWithElement(userDragons, Element.ELECTRIC);
        assertEquals(1, electricElement.size());
        assertEquals(111, electricElement.get(0).getBaseDragon().getId());
    }

    @Test
    void collectWithFirstElement_whenNoResult() {
        List<BaseDragon> bases = generateBases(3, Element.TERRA, Element.FLAME);
        List<UserDragon> userDragons = convertBaseToUser(bases);

        List<UserDragon> flameFirst = Task.collectWithFirstElement(userDragons, Element.FLAME);
        assertEquals(0, flameFirst.size());
    }

    @Test
    void collectWithFirstElement_whenOneResult() {
        List<BaseDragon> bases = generateBases(3, Element.TERRA, Element.MAGIC);
        BaseDragon base = new BaseDragon(111);
        base.setElements(new Element[]{Element.MAGIC, Element.TERRA});
        bases.add(2, base);

        List<UserDragon> userDragons = convertBaseToUser(bases);

        List<UserDragon> magicFirst = Task.collectWithFirstElement(userDragons, Element.MAGIC);
        assertEquals(1, magicFirst.size());
        assertEquals(111, magicFirst.get(0).getBaseDragon().getId());
    }

    @Test
    void collectWithFirstElement_whenAllResult() {
        List<BaseDragon> bases = generateBases(3, Element.TIME, Element.MAGIC, Element.BEAUTY);
        List<UserDragon> userDragons = convertBaseToUser(bases);

        List<UserDragon> timeFirst = Task.collectWithFirstElement(userDragons, Element.TIME);
        assertEquals(3, timeFirst.size());
    }

    @Test
    void collectBasesByRarity_whenOneOccurrence() {
        List<BaseDragon> bases = generateBases(6);

        Map<Rarity, List<BaseDragon>> basesByRarity = Task.collectBasesByRarity(bases);

        assertEquals(6, basesByRarity.size());
    }

    @Test
    void collectBasesByRarity_whenCommonExistsTwice() {
        List<BaseDragon> bases = generateBases(7);

        Map<Rarity, List<BaseDragon>> basesByRarity = Task.collectBasesByRarity(bases);

        assertEquals(6, basesByRarity.size());
        assertEquals(2, basesByRarity.get(Rarity.COMMON).size());
        assertEquals(1, basesByRarity.get(Rarity.RARE).size());
    }

    @Test
    void collectBasesByRarity_whenOnlyLegends() {
        List<BaseDragon> bases = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            bases.add(new BaseDragon(Rarity.LEGEND));
        }

        Map<Rarity, List<BaseDragon>> basesByRarity = Task.collectBasesByRarity(bases);

        assertEquals(1, basesByRarity.size());
        assertEquals(3, basesByRarity.get(Rarity.LEGEND).size());
    }

    @Test
    void countBasesByRarity_whenOneOccurrence() {
        List<BaseDragon> bases = generateBases(6);

        Map<Rarity, Integer> basesByRarity = Task.countBasesByRarity(bases);

        assertEquals(6, basesByRarity.size());
    }

    @Test
    void countBasesByRarity_whenCommonExistsTwice() {
        List<BaseDragon> bases = generateBases(7);

        Map<Rarity, Integer> basesByRarity = Task.countBasesByRarity(bases);

        assertEquals(6, basesByRarity.size());
        assertEquals(2, basesByRarity.get(Rarity.COMMON));
        assertEquals(1, basesByRarity.get(Rarity.RARE));
    }

    @Test
    void countBasesByRarity_whenOnlyLegends() {
        List<BaseDragon> bases = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            bases.add(new BaseDragon(Rarity.LEGEND));
        }

        Map<Rarity, Integer> basesByRarity = Task.countBasesByRarity(bases);

        assertEquals(1, basesByRarity.size());
        assertEquals(3, basesByRarity.get(Rarity.LEGEND));
    }

    private void assertContains(List<BaseDragon> bases, int expectedId) {
        String expected = "Result does contain dragon with id " + expectedId;
        String result = "Result does not contain dragon with id " + expectedId;

        for (BaseDragon base : bases) {
            if (base.getId() == expectedId) {
                result = result.replace(" not", "");
                break;
            }
        }

        assertEquals(expected, result);
    }

    private List<BaseDragon> generateBases(int size, Element... elements) {
        List<BaseDragon> baseDragons = new ArrayList<>();

        int rarity = 1;

        for (int i = 0; i < size; i++) {
            BaseDragon baseDragon = new BaseDragon(i + 1);

            if (elements.length > 0) {
                baseDragon.setElements(elements);
            }

            if (rarity > 6) {
                rarity = 1;
            }

            baseDragon.setRarity(Rarity.getByValue(rarity));

            rarity++;

            baseDragons.add(baseDragon);
        }

        return baseDragons;
    }

    private List<UserDragon> convertBaseToUser(BaseDragon... baseDragons) {
        List<UserDragon> userDragons = new ArrayList<>();

        for (BaseDragon baseDragon : baseDragons) {
            userDragons.add(new UserDragon(baseDragon));
        }

        return userDragons;
    }

    private List<UserDragon> convertBaseToUser(List<BaseDragon> baseDragons) {
        List<UserDragon> userDragons = new ArrayList<>();

        for (BaseDragon baseDragon : baseDragons) {
            userDragons.add(new UserDragon(baseDragon));
        }

        return userDragons;
    }

}
package main.services;

import main.models.BaseDragon;
import main.models.Element;
import main.models.Rarity;
import main.models.UserDragon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {

    /**
     * Task 1. - no-named UserDragons
     */
    public static int countNoNamedUserDragons(List<UserDragon> dragons) {
        int counter = 0;

        for (UserDragon dragon : dragons) {
            if (dragon.getName() == null) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Task 2. - not owned BaseDragons.
     */
    public static List<BaseDragon> collectNotOwnedDragons(List<BaseDragon> bases, List<UserDragon> userDragons) {
        List<BaseDragon> notOwnedBases = new ArrayList<>();

        for (BaseDragon base : bases) {
            boolean isIn = false;

            for (UserDragon userDragon : userDragons) {
                if (base.getId() == userDragon.getBaseDragon().getId()) {
                    isIn = true;
                    break;
                }
            }

            if (!isIn) {
                notOwnedBases.add(base);
            }
        }

        return notOwnedBases;
    }

    /**
     * Task 3. - UserDragons with given Element
     */
    public static List<UserDragon> collectWithElement(List<UserDragon> userDragons, Element element) {
        List<UserDragon> withElement = new ArrayList<>();

        for (UserDragon userDragon : userDragons) {
            for (Element elem : userDragon.getBaseDragon().getElements()) {
                if (elem == element) {
                    withElement.add(userDragon);
                    break;
                }
            }
        }

        return withElement;
    }

    /**
     * Task 4. - UserDragons with first Element equals given Element
     */
    public static List<UserDragon> collectWithFirstElement(List<UserDragon> userDragons, Element element) {
        List<UserDragon> withElement = new ArrayList<>();

        for (UserDragon userDragon : userDragons) {
            if (userDragon.getBaseDragon().getElements()[0] == element) {
                withElement.add(userDragon);
            }
        }

        return withElement;
    }

    /**
     * Task 5. - BaseDragons collected by Rarity
     */
    public static Map<Rarity, List<BaseDragon>> collectBasesByRarity(List<BaseDragon> bases) {
        Map<Rarity, List<BaseDragon>> byRarity = new HashMap<>();

        for (BaseDragon base : bases) {
            byRarity.putIfAbsent(base.getRarity(), new ArrayList<>());
            byRarity.get(base.getRarity()).add(base);
        }

        return byRarity;
    }

    /**
     * Task 6. - BaseDragons counted by Rarity
     */
    public static Map<Rarity, Integer> countBasesByRarity(List<BaseDragon> bases) {
        Map<Rarity, Integer> countsByRarity = new HashMap<>();

        for (BaseDragon base : bases) {
            Rarity rarity = base.getRarity();

            countsByRarity.putIfAbsent(rarity, 0);
            int increased = countsByRarity.get(rarity) + 1;
            countsByRarity.put(rarity, increased);
        }

        return countsByRarity;
    }

    /**
     * Task 7. - UserDragons at their max level
     */
    public static int userDragonsAtMaxLevel(List<UserDragon> userDragons) {
        int counter = 0;

        for (UserDragon userDragon : userDragons) {
            if (isAtMaxLevel(userDragon)) {
                counter++;
            }
        }

        return counter;
    }

    public static boolean isAtMaxLevel(UserDragon dragon) {
        return dragon.getLevel() == countMaxLevel(dragon.getStar());
    }

    public static int countMaxLevel(int star) {
        int max = 40 + star * 5;

        if (star == 5) {
            max += 5;
        }

        return max;
    }

}

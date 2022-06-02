package main;

import main.models.BaseDragon;
import main.models.Element;
import main.models.Rarity;
import main.models.UserDragon;
import main.services.DataHandler;
import main.services.Task;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        boolean successRead = DataHandler.loadFiles(DataHandler.BASE_PATH, DataHandler.USER_DRAGON_PATH);

        if (successRead) {
            System.out.println("1. --------------------------");
            int unnamed = Task.countNoNamedUserDragons(DataHandler.getUserDragons());
            System.out.printf("Unnamed dragons count: %d%n", unnamed);

            System.out.println("2. --------------------------");
            List<BaseDragon> notOwnedBases = Task.collectNotOwnedDragons(DataHandler.getBaseDragons(), DataHandler.getUserDragons());
            System.out.printf("Not owned bases count: %d%n", notOwnedBases.size());

            System.out.println("3. --------------------------");
            List<UserDragon> dragonsWithPrimal = Task.collectWithElement(DataHandler.getUserDragons(), Element.PRIMAL);
            System.out.printf("UserDragons count with Primal element: %d%n", dragonsWithPrimal.size());

            List<UserDragon> dragonsWithHappy = Task.collectWithElement(DataHandler.getUserDragons(), Element.HAPPY);
            System.out.printf("UserDragons count with Happy element: %d%n", dragonsWithHappy.size());

            System.out.println("4. --------------------------");
            List<UserDragon> dragonsWithFirstTerra = Task.collectWithFirstElement(DataHandler.getUserDragons(), Element.TERRA);
            System.out.printf("UserDragons count with first Terra element: %d%n", dragonsWithFirstTerra.size());

            List<UserDragon> dragonsWithFirstLegend = Task.collectWithFirstElement(DataHandler.getUserDragons(), Element.LEGEND);
            System.out.printf("UserDragons count with first Legend element: %d%n", dragonsWithFirstLegend.size());

            System.out.println("5. --------------------------");
            Map<Rarity, List<BaseDragon>> dragonListByRarity = Task.collectBasesByRarity(DataHandler.getBaseDragons());
            for (Rarity key : dragonListByRarity.keySet()) {
                System.out.printf("%s rarity count: %d%n", key, dragonListByRarity.get(key).size());
            }

            System.out.println("6. --------------------------");
            Map<Rarity, Integer> dragonCountByRarity = Task.countBasesByRarity(DataHandler.getBaseDragons());
            for (Rarity key : dragonCountByRarity.keySet()) {
                System.out.printf("%s rarity count: %d%n", key, dragonCountByRarity.get(key));
            }

            System.out.println("7. --------------------------");
            int maxLevelCount = Task.userDragonsAtMaxLevel(DataHandler.getUserDragons());
            System.out.printf("User dragons count at max level: %d%n", maxLevelCount);

        } else {
            System.out.println("Error: reading files");
        }
    }

}

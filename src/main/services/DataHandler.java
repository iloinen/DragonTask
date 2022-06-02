package main.services;

import main.models.BaseDragon;
import main.models.UserDragon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandler {

    public static final String BASE_PATH = "files/dragons.txt";
    public static final String USER_DRAGON_PATH = "files/user_dragons.txt";

    private static final String SEPARATOR = "\\|";
    private static final String ELEMENT_SEPARATOR = ",";

    private static List<BaseDragon> baseDragons;
    private static List<UserDragon> userDragons;

    public static List<BaseDragon> getBaseDragons() {
        return baseDragons;
    }

    public static List<UserDragon> getUserDragons() {
        return userDragons;
    }

    public static boolean loadFiles(String baseDragonPath, String userDragonPath) {
        baseDragons = new ArrayList<>();
        userDragons = new ArrayList<>();

        try {
            loadBaseDragons(baseDragonPath);
            loadUserDragonPath(userDragonPath);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void loadBaseDragons(String baseDragonPath) throws IOException {
        Scanner sc = new Scanner(new File(baseDragonPath));

        while (sc.hasNextLine()) {
            String lineFromFile = sc.nextLine();
            String[] splitLine = lineFromFile.split(SEPARATOR);
            String[] elements = splitLine[splitLine.length - 1].split(ELEMENT_SEPARATOR);

            BaseDragon baseDragon = createBaseDragon(splitLine, elements);

            baseDragons.add(baseDragon);
        }

        sc.close();
    }

    private static BaseDragon createBaseDragon(String[] lineParts, String[] elements) {
        int id = Integer.parseInt(lineParts[0]);
        int rarity = Integer.parseInt(lineParts[2]);

        return new BaseDragon(id, lineParts[1], rarity, lineParts[3], elements);
    }

    private static void loadUserDragonPath(String userDragonPath) throws IOException {
        Scanner sc = new Scanner(new File(userDragonPath));

        while (sc.hasNextLine()) {
            String lineFromFile = sc.nextLine();
            String[] split = lineFromFile.split(SEPARATOR);

            BaseDragon base = findBaseById(Integer.parseInt(split[0]));

            if (base != null) {
                UserDragon user = createUserDragon(base, split);

                userDragons.add(user);
            }
        }

        sc.close();
    }

    private static UserDragon createUserDragon(BaseDragon base, String[] lineParts) {
        String name = (lineParts[1].isBlank() ? null : lineParts[1]);

        int level = Integer.parseInt(lineParts[2]);
        int star = Integer.parseInt(lineParts[3]);

        return new UserDragon(base, name, level, star);
    }

    private static BaseDragon findBaseById(int id) {
        for (BaseDragon base : baseDragons) {
            if (base.getId() == id) {
                return base;
            }
        }

        return null;
    }

}

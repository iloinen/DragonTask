package main.models;

import java.util.Arrays;

public class BaseDragon {

    private int id;
    private String name;
    private Rarity rarity;
    private String description;
    private Element[] elements;

    public BaseDragon(int id) {
        this.id = id;
    }

    public BaseDragon(Rarity rarity) {
        this.rarity = rarity;
    }

    public BaseDragon(String name, int rarity, String description) {
        this.name = name;
        this.rarity = Rarity.getByValue(rarity);
        this.description = description;
    }

    public BaseDragon(int id, String name, int rarity, String description, String[] elements) {
        this(name, rarity, description);
        this.id = id;
        this.elements = Element.getAllByValues(elements);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return idToString() +
                name + " (" +
                rarity + ")\n\t" +
                elementsToString() + "\n\t" +
                description;
    }

    private String idToString() {
        if (id == 0) {
            return "";
        }

        return id + ". ";
    }

    private String elementsToString() {
        if (elements == null) {
            return "//no elements//";
        }
        return Arrays.toString(elements);
    }

}

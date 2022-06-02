package main.models;

public class UserDragon {

    private BaseDragon baseDragon;
    private String name;
    private int level;
    private int star;

    public UserDragon() {}

    public UserDragon(BaseDragon baseDragon) {
        this.baseDragon = baseDragon;
    }

    public UserDragon(BaseDragon baseDragon, String name, int level, int star) {
        this.baseDragon = baseDragon;
        this.name = name;
        this.level = level;
        this.star = star;
    }

    public BaseDragon getBaseDragon() {
        return baseDragon;
    }

    public void setBaseDragon(BaseDragon baseDragon) {
        this.baseDragon = baseDragon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

}

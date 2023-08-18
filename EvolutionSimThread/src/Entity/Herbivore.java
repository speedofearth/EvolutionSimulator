package Entity;

import island.Island;
import island.Location;

public class Herbivore extends Animal {
    public Herbivore(Island island, Location location) {
        super(island, location);
    }

    public void eat() {
    }

    public Animal breed() {
        return null;
    }

    public static void main(String[] args) {

    }
}

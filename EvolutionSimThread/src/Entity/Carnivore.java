package Entity;

import island.Island;
import island.Location;

import java.util.ArrayList;
import java.util.List;

public class Carnivore extends Animal {
    protected List<Animal> eatenAnimals;

    public Carnivore(Island island, Location location) {
        super(island, location);
        this.eatenAnimals = new ArrayList<>();
    }

    public void eat() {

    }

    @Override
    public Animal breed() {
        return null;
    }

    public static void main(String[] args) {

    }
}

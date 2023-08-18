package Entity;

import island.Island;
import island.Location;

import java.util.Iterator;
import java.util.List;

public class Rabbit extends Herbivore {
    public Rabbit(Island island, Location location) {
        super(island, location);
        this.weight = 2;
        this.foodToFullyFeed = .45;
    }

    @Override
    public void eat() {
        List<Plant> plants = location.getPlants();
        Iterator<Plant> itr = plants.iterator();
        while(itr.hasNext()) {
            Plant plant = itr.next();
            hunger -= plant.getNutrition();
            messages.add(this.getClass() + " ate " + "Plant");
            itr.remove();
            break;
        }
    }

    @Override
    public synchronized Animal breed() {
        if (location.getAnimal(this.getClass()) != null) {
//            Animal baby = createBaby();
//            location.addAnimal(baby);
            messages.add(this.getClass() + "successfully bred!\nWelcome a new " + this.getClass() + " to the world!");
        return new Rabbit(island, location);
        }
            return null;
    }

    public static void main(String[] args) {

    }
}

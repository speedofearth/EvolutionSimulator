package Entity;

import island.Island;
import island.Location;

import java.util.Iterator;
import java.util.List;

public class Caterpillar extends Herbivore {
    public Caterpillar(Island island, Location location) {
        super(island, location);
        this.weight = 0.01;
        this.foodToFullyFeed = 0;
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

    public static void main(String[] args) {

    }
}

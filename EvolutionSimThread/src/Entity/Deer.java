package Entity;

import island.Island;
import island.Location;

import java.util.Iterator;
import java.util.List;

public class Deer extends Herbivore {
    public Deer(Island island, Location location) {
        super(island, location);
        this.weight = 300;
        this.foodToFullyFeed = 50;
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

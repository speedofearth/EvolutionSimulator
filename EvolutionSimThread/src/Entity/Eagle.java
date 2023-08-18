package Entity;

import island.Island;
import island.Location;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Carnivore {
    public Eagle(Island island, Location location) {
        super(island, location);
        this.weight = 6;
        this.foodToFullyFeed = 1;
    }

    @Override
    public void eat() {
        List<Animal> animals = location.getAnimals();
        Iterator<Animal> itr = animals.iterator();
        while (itr.hasNext()) {
            Animal prey = itr.next();
            if (prey instanceof Rabbit) {
                double chance = ThreadLocalRandom.current().nextDouble();
                if (chance >= 0.9) {
                    prey.killed();
                    hunger -= prey.getNutrition();
                    messages.add(this.getClass() + " ate " + prey.getClass());
                    break;
                }
            }
        }
    }

    @Override
    public synchronized Animal breed() {
        if (location.getAnimal(this.getClass()) != null && !isDead()) {
            messages.add(this.getClass() + "successfully bred!\nWelcome a new " + this.getClass() + " to the world!");
            return new Eagle(island, location);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}

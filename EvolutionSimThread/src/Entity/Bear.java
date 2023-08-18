package Entity;

import island.Island;
import island.Location;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Carnivore {
    public Bear(Island island, Location location) {
        super(island, location);
        this.weight = 500;
        this.foodToFullyFeed = 80;
    }

    @Override
    public void eat() {
        List<Animal> animals = location.getAnimals();
        Iterator<Animal> itr = animals.iterator();
        while (itr.hasNext()) {
            Animal prey = itr.next();
            if (prey instanceof Boa) {
                double chance = ThreadLocalRandom.current().nextDouble();
                if (chance >= 0.8) {
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
            return new Bear(island, location);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}

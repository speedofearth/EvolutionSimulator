package island;

import Entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Location {
    private Island island;
    private int x;
    private int y;
    private List<Plant> plants;
    private List<Animal> animals;

    public Location(Island island, int x, int y) {
        this.island = island;
        this.x = x;
        this.y = y;
        this.plants = new ArrayList<>();
        this.animals = new ArrayList<>();
        populate();
    }

    public synchronized int getX() {
        return this.x;
    }

    public synchronized int getY() {
        return this.y;
    }

    public synchronized  void addPlant(Plant plant) {
        plants.add(plant);
    }

    public synchronized  void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public synchronized  List<Plant> getPlants() {
        return plants;
    }

    public synchronized  void addAnimal(Animal animal){
        animals.add(animal);
    }

    public synchronized  void removeAnimal(Animal animal){
        animals.remove(animal);
    }

    public synchronized  List<Animal> getAnimals() {
        return animals;
    }

    public synchronized  Animal getAnimal(Class<? extends Animal> animalClass) {
        for (Animal animal: animals) {
            if (animal.getClass().equals(animalClass)) {
                return animal;
            }
        }
        return null;
    }

    public synchronized  int getAnimalCount(Class<? extends Animal> animalClass) {
        int count = 0;
        for (Animal animal: animals) {
            if (animal.getClass().equals(animalClass)) {
                count += 1;
            }
        }
        return count;
    }

    private synchronized  void populate() {
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(100); i++) {
            plants.add(new Plant());
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(30); i++) {
            animals.add(new Rabbit(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(30); i++) {
            animals.add(new Wolf(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Boa(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Fox(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Bear(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Eagle(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Horse(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Deer(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Mouse(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Goat(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Sheep(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Boar(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Buffalo(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Duck(island, this));
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5); i++) {
            animals.add(new Caterpillar(island, this));
        }
    }

    public synchronized  List<Animal> updateMovement() {
        List<Animal> moving = new ArrayList<>();
        Iterator<Animal> itr = animals.iterator();
        while(itr.hasNext()) {
            Animal mover = itr.next();
            moving.add(mover.move());
            itr.remove();
        }
        return moving;
    }

    public synchronized  List<Animal> updateDead() {
        List<Animal> dead = new ArrayList<>();
        Iterator<Animal> itr = animals.iterator();
        while(itr.hasNext()) {
            Animal animal = itr.next();
            if(animal.isDead()) {
                dead.add(animal);
                itr.remove();
            }
        }
        return dead;
    }

    public synchronized void breedAllAnimals() {
        List<Animal> newBornAnimals = new ArrayList<>();
        for (int i = 0; i < animals.size(); i+=2) {
            Animal animal = animals.get(i);
            if (!animal.isDead())
                newBornAnimals.add(animal.breed());
        }
        animals.addAll(newBornAnimals);
    }

    public synchronized  int getNumberWolf() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Wolf) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberRabbit() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Rabbit) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberBoa() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Boa) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberFox() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Fox) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberBear() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Bear) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberEagle() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Eagle) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberHorse() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Horse) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberDeer() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Deer) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberMouse() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Mouse) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberGoat() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Goat) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberSheep() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Sheep) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberBoar() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Boar) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberBuffalo() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Buffalo) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberDuck() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Duck) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberCaterpillar() {
        int count = 0;
        for (Animal animal: animals) {
            if (animal instanceof Caterpillar) {
                count++;
            }
        }
        return count;
    }

    public synchronized  int getNumberPlants() {
        return plants.size();
    }

    public synchronized  String toString() {
        StringBuilder allMessages = new StringBuilder();
        for (Animal animal : this.animals) {
            for (String message: animal.getMessages()) {
                allMessages.append(message).append("\n");
                allMessages.append(" ");
            }
            animal.clearMessages();
        }
        return allMessages.toString().trim();
    }

    public synchronized  String getLocationCoords() {
        return "\nLocation: " + getX() + "," + getY();
    }

    public static void main(String[] args) {

    }
}

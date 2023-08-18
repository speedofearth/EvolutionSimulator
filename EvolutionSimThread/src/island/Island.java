package island;

import Entity.Animal;
import Entity.Carnivore;
import Entity.Herbivore;
import Entity.Plant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;
    private final ScheduledExecutorService executor;
    private List<Animal> shifting;
    private List<Animal> deadAnimals;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++) {
                locations[i][j] = new Location(this,i,j);
            }
        }
        this.executor = Executors.newScheduledThreadPool(1);
        this.shifting = new ArrayList<>();
        this.deadAnimals = new ArrayList<>();
    }

    public void startSimulation() throws InterruptedException {

        this.executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                growPlants();
            }
        }, 0, 3, TimeUnit.SECONDS);

        this.executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                animalActions();
            }
        }, 0, 3, TimeUnit.SECONDS);

        this.executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                displayStatistics();
            }
        }, 0, 3, TimeUnit.SECONDS);
    }


    private void growPlants() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Location location = getLocation(i,j);
                for (int k = 0; k < 50; k++) {
                    if (location.getPlants().size() < 200) {
                        location.addPlant(new Plant());
                    }
                }
            }
        }
    }

    private void animalActions() {
        //Phase 1 for movement and Dead Animal Updating
        /* Enabled movement */
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Location location = getLocation(i,j);
                this.deadAnimals.addAll(location.updateDead());
                this.shifting.addAll(location.updateMovement());
//                this.shifting.addAll(location.updateBabies());
            }
        }

        //Phase 2
        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++) {
                Location location = getLocation(i,j);

                //Moving Iterator -->
                /* Enabled movement */
                Iterator<Animal> itr = shifting.iterator();
                while (itr.hasNext()) {
                    Animal ent = itr.next();
                    if (location.getX() == ent.getX() && location.getY() == ent.getY()) {
                        location.addAnimal(ent);
                        itr.remove();
                    }
                }
                /* Enabled movement */

                //Eating Iterator -->
                /* */
                for (Animal animal: location.getAnimals()) {
                    //Increase Hunger every iteration
                    animal.increaseHunger();

                    //Herbivore Eating
                    if (animal instanceof Herbivore) {
                        if (!animal.isDead()) {
                            animal.eat();
                        }
                    }
                    //Carnivores Eating
                    if (animal instanceof Carnivore) {
                        if (!animal.isDead()) {
                            animal.eat();
                        }
                    }
                }
                //<-- Eating Iterator End
            }
        }

        // Disabled Breeding - MAJOR BUG
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                Location location = getLocation(i,j);
//                location.breedAllAnimals();
//            }
//        }
    }

    private void displayStatistics() {

        int wolfCount = 0;
        int BoaCount = 0;
        int FoxCount = 0;
        int BearCount = 0;
        int EagleCount = 0;

        int horseCount = 0;
        int deerCount = 0;
        int mouseCount = 0;
        int goatCount = 0;
        int sheepCount = 0;
        int boarCount = 0;
        int buffaloCount = 0;
        int duckCount = 0;
        int caterpillarCount = 0;
        int rabbitCount = 0;

        int plantCount = 0;

        System.out.println("---Animal Actions---");
        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++) {
                wolfCount += locations[i][j].getNumberWolf();
                BoaCount += locations[i][j].getNumberBoa();
                FoxCount += locations[i][j].getNumberFox();
                BearCount += locations[i][j].getNumberBear();
                EagleCount += locations[i][j].getNumberEagle();

                horseCount += locations[i][j].getNumberHorse();
                deerCount += locations[i][j].getNumberDeer();
                mouseCount += locations[i][j].getNumberMouse();
                goatCount += locations[i][j].getNumberGoat();
                sheepCount += locations[i][j].getNumberSheep();
                boarCount += locations[i][j].getNumberBoar();
                buffaloCount += locations[i][j].getNumberBuffalo();
                duckCount += locations[i][j].getNumberDuck();
                caterpillarCount += locations[i][j].getNumberCaterpillar();

                rabbitCount += locations[i][j].getNumberRabbit();

                plantCount += locations[i][j].getNumberPlants();
                System.out.println(locations[i][j].getLocationCoords());
                System.out.println(locations[i][j].toString());
            }
        }
        System.out.println("---Animal Actions---");
        System.out.println();
        System.out.println("---Global Statistics---");
        System.out.println();
        System.out.println("Dead Animals:" + deadAnimals.size());
        System.out.println("Total Plants:" + plantCount);
        System.out.println("Total Wolves:" + wolfCount);
        System.out.println("Total Boa:" + BoaCount);
        System.out.println("Total Fox:" + BearCount);
        System.out.println("Total Bear:" + FoxCount);
        System.out.println("Total Eagle:" + EagleCount);

        System.out.println("Total Horse:" + horseCount);
        System.out.println("Total Deer:" + deerCount);
        System.out.println("Total Mouse:" + mouseCount);
        System.out.println("Total Goat:" + goatCount);
        System.out.println("Total Sheep:" + sheepCount);
        System.out.println("Total Boar:" + boarCount);
        System.out.println("Total Buffalo:" + buffaloCount);
        System.out.println("Total Duck:" + duckCount);
        System.out.println("Total Caterpillar:" + caterpillarCount);
        System.out.println("Total Rabbits:" + rabbitCount);

    }

    public void shutdown() {
        executor.shutdown();
    }

    public Location getLocation(int x, int y) {
        Location location = null;
        for (int i = 0; i < width; i++) {
            for (int j =0; j < height; j++) {
                if (i == x && j == y) {
                    location = locations[i][j];
                }
            }
        }
        return location;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public static void main(String[] args) {

    }
}

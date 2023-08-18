package Entity;

import island.Island;
import island.Location;

import java.util.ArrayList;
import java.util.List;

enum Direction {
    NORTH, SOUTH, EAST, WEST
}

public abstract class Animal {
    protected Island island;
    protected Location location;
    protected int x;
    protected int y;
    protected int movementSpeed;
    protected double weight;
    protected double foodToFullyFeed;
    protected double hunger;
    protected double nutrition;
    protected final double NUTRITION_PER_WEIGHT = 0.5;
    protected boolean killed;
    protected List<String> messages;

    public Animal(Island island, Location location) {
        this.island = island;
        this.location = location;
        this.x = this.location.getX();
        this.y = this.location.getY();
        this.hunger = 0;
        this.nutrition = NUTRITION_PER_WEIGHT * weight;
        this.killed = false;
        this.messages = new ArrayList<>();
    }

    public synchronized  int getX() {
        return x;
    }

    public synchronized  int getY() {
        return y;
    }

    public synchronized Animal move() {
        int initialX = x;
        int initialY = y;
        Direction direction = Direction.values()[(int) (Math.random() * Direction.values().length)];
        switch (direction) {
            case NORTH:
                y-=1;
                break;
            case SOUTH:
                y += 1;
                break;
            case EAST:
                x += 1;
                break;
            case WEST:
                x -= 1;
                break;
        }

        if (x < 0) {
            x = 0;
        } else if ( x >= island.getWidth()) {
            x = island.getWidth() -1;
        }

        if (y < 0) {
            y = 0;
        } else if ( y >= island.getHeight()) {
            y = island.getHeight() - 1;
        }
        int newX = x;
        int newY = y;
        messages.add(this.getClass() + " moved from " + initialX + "," + initialY + " to " + newX + "," + newY);
        return this;
    }

    public synchronized  void increaseHunger() {
        hunger += (foodToFullyFeed / 2);
    }

    public synchronized  boolean isDead() {
        if (killed) {
            messages.add(this.getClass() + " got killed!");
            return true;
        }

        boolean starved = hunger >= foodToFullyFeed * 2;
        if (starved) {
            messages.add(this.getClass() + " starved to death!");
            return true;
        }
        return false;
    }

    public synchronized  void killed() {
        this.killed = true;
    }

    public synchronized  double getHunger() {
        return hunger;
    }

    public synchronized  double getNutrition() {
        return nutrition;
    }

    public synchronized  List<String> getMessages() {
        return messages;
    }

    public synchronized  void clearMessages() {
        messages.clear();
    }

    public abstract void eat();
    public abstract Animal breed();

//    public void breed() {
////        System.out.println("Breed called!");
////        int count = location.getAnimalCount(this.getClass());
////        if (count >= 2 || count % 2 == 0) {
////            Animal baby = createBaby();
////            location.addAnimal(baby);
////            messages.add(this.getClass() + "successfully bred!\nWelcome a new " + this.getClass() + " to the world!");
////        }
////        if (location.getAnimal(this.getClass()) != null) {
//            Animal baby = createBaby();
//            location.addAnimal(baby);
//            messages.add(this.getClass() + "successfully bred!\nWelcome a new " + baby.getClass() + " to the world!");
////        }
//    }

    public static void main(String[] args) {

    }
}

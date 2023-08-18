package Entity;

public class Plant {
    private final double nutrition;

    public Plant() {
        this.nutrition = .2;
    }

    public synchronized double getNutrition() {
        return nutrition;
    }

    public static void main(String[] args) {

    }
}

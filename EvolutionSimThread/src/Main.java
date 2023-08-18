import island.Island;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Evolution Simulation!");
        System.out.println("Developed by Soi, Submitted on August 18, 2023");
        System.out.println("Please enter 2-100 for Island Height");
        int height = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter 2-100 for Island Width");
        int width = Integer.parseInt(scan.nextLine());
        System.out.println("Please type exit at any time to stop the simulation");
        Island island = new Island(height,width);
        island.startSimulation();
        String input = scan.nextLine();
        if(input.equals("exit")) {
            island.shutdown();
        }

    }
}

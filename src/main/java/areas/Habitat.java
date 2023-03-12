package areas;

import animals.*;

import java.util.ArrayList;

abstract public class Habitat extends Area {

    private ArrayList<Animal> habitat = new ArrayList<>();
    private int capacity;

    public Habitat(int space) {
        this.capacity = space;
    }

    public void releaseAnimal(Animal anm) {
        habitat.add(anm);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getHabOccupancy() {
        return habitat.size();
    }

    public Animal getAnimal(int spot) {
        return habitat.get(spot);
    }

}

package zoo;

import areas.Entrance;
import zoo.*;

import animals.Animal;
import areas.*;
import dataStructures.ICashCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Zoo implements IZoo {

    private final HashMap<Integer, IArea> zoo = new HashMap<>();
    private int iDCount = 0;

    public Zoo() {
        zoo.put(0, new Entrance());

    }

    // Basic Section

    public int iDGenerator() {
        iDCount += 1;
        return iDCount;
    }

    //Checks to ensure that the given area doesn't already exist, and then puts it into the HashMap zoo
    @Override
    public int addArea(IArea area) {
        if (zoo.containsValue(area)) {
            return -1;
        } else {
            int id = iDGenerator();
            zoo.put(id, area);
            return id;
        }
    }

    /* Checks to ensure that the given area already exists and that it's not the entrance
    Then, it removes the parameter area */
    @Override
    public boolean removeArea(int areaId) {
        if (zoo.get(areaId) == null || areaId == 0){
            return false;
        } else {
            zoo.remove(areaId);
            return true;
        }
    }

    @Override
    public IArea getArea(int areaId) {
        return zoo.get(areaId);
    }

    /* The addAnimal method:
     - Checks if the areaID belongs to a habitat, and subsequently casts the area if true
     - Checks if the animal and the area match,
     - Checks if the habitat is full,
     - And then loops through the habitat, checking if each inhabitant is compatible
     If a return statement of 1-4 doesn't break the code, then an animal is added
     */
    @Override
    public byte addAnimal(int areaId, Animal animal) {

        Habitat habitat;
        if (Habitat.class.isAssignableFrom(getArea(areaId).getClass())) {

            habitat = (Habitat) getArea(areaId);

        } else {

            return Codes.NOT_A_HABITAT;

        }

        if (!animal.canLiveIn(getArea(areaId))) {

            return Codes.WRONG_HABITAT;

        } else if (habitat.getHabOccupancy() == habitat.getCapacity()) {

            return Codes.HABITAT_FULL;

        } else {

            for (int i = 0; i < habitat.getHabOccupancy(); i++) {
                if (!animal.isCompatibleWith(habitat.getAnimal(i))){

                    return Codes.INCOMPATIBLE_INHABITANTS;

                }
                }

            habitat.releaseAnimal(animal);
            return Codes.ANIMAL_ADDED;
            }
        }

        // Intermediate Section


    @Override
    public void connectAreas(int fromAreaId, int toAreaId) {
        if (fromAreaId != toAreaId){
            getArea(fromAreaId).addAdjacentArea(toAreaId);
        }
    }

    /* The method loops through areaIds
    until the path is either complete or impossible
    by checking if the subsequent area is in the current area's adjacentAreas ArrayList.
    Only returns true if the path is possible */
    @Override
    public boolean isPathAllowed(ArrayList<Integer> areaIds) {
            for (int i = 0; i < areaIds.size()-1; i++) {
                if (!getArea(areaIds.get(i)).getAdjacentAreas().contains(areaIds.get(i+1))) {
                    return false;
                }
            }
            return true;
        }

    /* Uses isPathAllowed to check if the given path is valid, and return null if not.
    If the path is valid, then the method loops through the zoo's areas,
    and checks if the area is a habitat.
    If true, then the method loops through each animal of the habitat.
    Each animal seen is added to the Arraylist sights, which is returned at the end of the method */
    @Override
    public ArrayList<String> visit(ArrayList<Integer> areaIdsVisited) {
        if (isPathAllowed(areaIdsVisited)) {
            ArrayList<String> sights = new ArrayList<>();
            Habitat habitat;
            for (int i = 0; i < areaIdsVisited.size(); i++) {
                if (getArea(i) instanceof Habitat){
                    habitat = (Habitat) getArea(i);
                    for (int n = 0; n < habitat.getHabOccupancy(); n++) {
                        sights.add(String.valueOf(habitat.getAnimal(n).getNickname()));
                    }
                }
            }
            return sights;
        } else {
            return null;
        }
    }

    @Override
    public Set<Integer> findUnreachableAreas() {
        return null;
    }

    // Advanced Section

    @Override
    public void setEntranceFee(int pounds, int pence) {

    }

    @Override
    public void setCashSupply(ICashCount coins) {

    }

    @Override
    public ICashCount getCashSupply() {
        return null;
    }

    @Override
    public ICashCount payEntranceFee(ICashCount cashInserted) {
        return null;
    }
}

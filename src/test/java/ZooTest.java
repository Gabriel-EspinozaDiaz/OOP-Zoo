import animals.*;
import areas.*;
//import dataStructures.CashCount;
import dataStructures.ICashCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoo.Codes;
import zoo.Zoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ZooTest {
    Zoo myZoo;
    Cage cage, unreachable; // unreachable is for the test of unreachable areas
    Aquarium aquarium;
    Enclosure enclosure;
    PicnicArea picnicArea;
    int idCage, idAquarium, idPicnic, idEnclosure, idUnreachable;
    Shark shark;
    Seal seal;
    Starfish starfish;
    Buzzard buzzard;



    @BeforeEach
    void setUp() {
        myZoo = new Zoo();
        cage = new Cage(60);
        unreachable = new Cage(5);
        aquarium = new Aquarium(3);
        enclosure = new Enclosure(15);
        picnicArea = new PicnicArea();
        idCage = myZoo.addArea(cage);
        idUnreachable = myZoo.addArea(unreachable);
        idAquarium = myZoo.addArea(aquarium);
        idPicnic = myZoo.addArea(picnicArea);
        idEnclosure = myZoo.addArea(enclosure);
        shark = new Shark("shark");
        seal = new Seal("seal");
        starfish = new Starfish("starfish");
        buzzard = new Buzzard("buzzard");

    }


    @org.junit.jupiter.api.Test
    void constructedWithEntrance() {
        assertInstanceOf(Entrance.class, myZoo.getArea(0));
//        assertInstanceOf(Aquarium.class, zoo.getArea(0));
    }

    @org.junit.jupiter.api.Test
    void addArea() {
        assertInstanceOf(Cage.class, myZoo.getArea(idCage));
        assertInstanceOf(Aquarium.class, myZoo.getArea(idAquarium));

    }


    @org.junit.jupiter.api.Test
    void removeArea() {
        Boolean isRemovedTrue = myZoo.removeArea(idCage);
        Boolean isRemovedFalse = myZoo.removeArea(idCage);
        assertFalse(isRemovedFalse);
        assertTrue(isRemovedTrue);
    }

    @org.junit.jupiter.api.Test
    void getArea() {
    }


    @org.junit.jupiter.api.Test
    void addAnimal() {
        byte animalAdded = myZoo.addAnimal(idAquarium, seal); // empty habitat should accept supportedAnimal
        Habitat aquariumHabitat = (Habitat) myZoo.getArea(idAquarium);
        //assertEquals(new ArrayList<Animal>(Arrays.asList(seal)), aquariumHabitat.getAnimals());
        byte animalAdded1 = myZoo.addAnimal(idAquarium, starfish); // compatible animal should be added
        //assertEquals(new ArrayList<Animal>(Arrays.asList(seal, starfish)), aquariumHabitat.getAnimals());
        byte animalAdded2 = myZoo.addAnimal(idAquarium, shark); // incompatible animal should not be added
        assertEquals(Codes.INCOMPATIBLE_INHABITANTS, animalAdded2);
        //assertEquals(new ArrayList<Animal>(Arrays.asList(seal, starfish)), aquariumHabitat.getAnimals());
        //assertNotEquals(new ArrayList<Animal>(Arrays.asList(seal, starfish, shark)), aquariumHabitat.getAnimals());
        byte animalAdded3 = myZoo.addAnimal(idPicnic, shark); // should not be able to add animal to picnic area
        assertEquals(Codes.NOT_A_HABITAT, animalAdded3);

    }

    @org.junit.jupiter.api.Test
    void connectAreas() {
        myZoo.connectAreas(idAquarium, idCage);
        myZoo.connectAreas(0, idAquarium);
        myZoo.connectAreas(0, idCage);
        assertEquals(new ArrayList<Integer>(Arrays.asList(idCage)), myZoo.getArea(idAquarium).getAdjacentAreas());
        assertNotEquals(new ArrayList<Integer>(Arrays.asList(idAquarium)), myZoo.getArea(idCage).getAdjacentAreas());
        myZoo.connectAreas(idAquarium, idCage); // does not duplicate edges
        assertEquals(new ArrayList<Integer>(Arrays.asList(idCage)), myZoo.getArea(idAquarium).getAdjacentAreas());
        myZoo.connectAreas(idAquarium, 0);
        assertEquals(new ArrayList<Integer>(Arrays.asList(idCage, 0)), myZoo.getArea(idAquarium).getAdjacentAreas());


    }

    @org.junit.jupiter.api.Test
    void isPathAllowed() {
        /*myZoo.connectAreas(idAquarium, idCage);
        myZoo.connectAreas(0, idAquarium);
        myZoo.connectAreas(0, idCage);
        myZoo.connectAreas(idCage, 0);
        assertTrue(myZoo.isPathAllowed(new ArrayList<Integer>(Arrays.asList(0, idAquarium))));
        assertTrue(myZoo.isPathAllowed(new ArrayList<Integer>(Arrays.asList(0, idAquarium, idCage))));
        assertTrue(myZoo.isPathAllowed(new ArrayList<Integer>(Arrays.asList(0, idAquarium, idCage, 0))));
        assertFalse(myZoo.isPathAllowed(new ArrayList<Integer>(Arrays.asList(0, idCage, idAquarium))));*/
        myZoo.connectAreas(idAquarium, idCage);
        myZoo.connectAreas(0, idAquarium);
        myZoo.connectAreas(0, idCage);
        myZoo.connectAreas(idCage, idAquarium);
        assertTrue(myZoo.isPathAllowed(new ArrayList<Integer>(Arrays.asList(0, idCage, idAquarium, idCage))));
        assertTrue(myZoo.isPathAllowed(new ArrayList<Integer>(Arrays.asList(0, idCage, idAquarium, idCage, idAquarium))));


    }

    @org.junit.jupiter.api.Test
    void visit() {
        myZoo.connectAreas(idAquarium, idCage);
        myZoo.connectAreas(0, idAquarium);
        myZoo.connectAreas(0, idCage);
        myZoo.connectAreas(idCage, idAquarium);
        myZoo.addAnimal(idAquarium, shark);
        myZoo.addAnimal(idAquarium, starfish);
        myZoo.addAnimal(idCage, buzzard);
        ArrayList<String> visitedAreas = myZoo.visit(new ArrayList<Integer>(Arrays.asList(0, idCage, idAquarium, idCage)));
        assertEquals(new ArrayList<String>(Arrays.asList("buzzard", "shark", "starfish", "buzzard")), visitedAreas);
        ArrayList<String> visitedAreas1 = myZoo.visit(new ArrayList<Integer>(Arrays.asList(0, idCage, idAquarium, idCage, idAquarium)));
        assertNotEquals(new ArrayList<String>(Arrays.asList("buzzard", "shark", "starfish", "shark", "starfish")), visitedAreas1);

    }

    @org.junit.jupiter.api.Test
    void findUnreachableAreas() {
        myZoo.connectAreas(idAquarium, idCage);
        myZoo.connectAreas(0, idAquarium);
        myZoo.connectAreas(0, idCage);
        myZoo.connectAreas(idAquarium, idPicnic);
        myZoo.connectAreas(idUnreachable, idEnclosure); // disconnected part of graph
        assertEquals(new HashSet<Integer>(Arrays.asList(idUnreachable, idEnclosure)), myZoo.findUnreachableAreas());
        myZoo.connectAreas(idPicnic, idEnclosure); // unreachable should still be unreachable
        assertEquals(new HashSet<Integer>(Arrays.asList(idUnreachable)), myZoo.findUnreachableAreas());
        myZoo.connectAreas(idEnclosure, idUnreachable); // unreachable should be connected now
        assertEquals(new HashSet<Integer>(), myZoo.findUnreachableAreas());
    }
/*

    @Test
    void payEntranceFee() {
        myZoo.setEntranceFee(17,20);
        ICashCount money = new CashCount(0,1,2,0,0,0,0,0);
        ICashCount money2 = new CashCount();
        ICashCount money3 = new CashCount(1000,0,0,0,0,0,0,0);
        ICashCount change = new CashCount(0,0,0,0,2,0,4,0);
        ICashCount cashSupply = new CashCount(0,10,0,0,7, 0,10, 20);
        money2.setNrNotes_10pounds(1);
        money2.setNrNotes_5pounds(1);
        money2.setNrCoins_2pounds(1);
        money2.setNrCoins_20p(1);
        myZoo.setCashSupply(cashSupply);
        assertEquals(CashCount.iCashCountToInt(change), CashCount.iCashCountToInt(myZoo.payEntranceFee(money)));
        assertEquals(change.getNrCoins_1pound(), myZoo.payEntranceFee(money).getNrCoins_1pound());
        assertEquals(change.getNrCoins_20p(), myZoo.payEntranceFee(money).getNrCoins_20p());
        assertEquals(change.getNrCoins_1pound(), myZoo.payEntranceFee(money).getNrCoins_1pound());
        myZoo.setCashSupply(cashSupply);
        assertEquals(0, CashCount.iCashCountToInt(myZoo.payEntranceFee(money2)));
    } */
}
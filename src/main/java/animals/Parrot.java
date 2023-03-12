package animals;

import areas.Cage;
import areas.IArea;

public class Parrot extends Animal {

    public Parrot(String nickname) {
        super(nickname);
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        return animal.getClass().equals(Parrot.class);
    }

    @Override
    public boolean canLiveIn(IArea area) {
        return area.getClass().equals(Cage.class);
    }
}

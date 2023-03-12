package animals;

import areas.Cage;
import areas.IArea;

public class Buzzard extends Animal {

    public Buzzard(String nickname) {
        super(nickname);
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        return animal.getClass().equals(Buzzard.class);
    }

    @Override
    public boolean canLiveIn(IArea area) {
        return area.getClass().equals(Cage.class);
    }
}

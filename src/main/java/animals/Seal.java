package animals;

import areas.Aquarium;
import areas.IArea;

public class Seal extends Animal {

    public Seal(String nickname) {
        super(nickname);
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        return !animal.getClass().equals(Shark.class);
    }

    @Override
    public boolean canLiveIn(IArea area) {
        return area.getClass().equals(Aquarium.class);
    }
}

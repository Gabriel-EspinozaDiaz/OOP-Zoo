package animals;

import areas.Aquarium;
import areas.IArea;

public class Shark extends Animal {

    public Shark(String nickname) {
        super(nickname);
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        return !animal.getClass().equals(Seal.class);
    }

    @Override
    public boolean canLiveIn(IArea area) {
        return area.getClass().equals(Aquarium.class);
    }
}

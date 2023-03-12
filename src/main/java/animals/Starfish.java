package animals;

import areas.Aquarium;
import areas.IArea;

public class Starfish extends Animal {

    public Starfish(String nickname) {
        super(nickname);
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        return true;
    }

    @Override
    public boolean canLiveIn(IArea area) {
        return area.getClass().equals(Aquarium.class);
    }
}

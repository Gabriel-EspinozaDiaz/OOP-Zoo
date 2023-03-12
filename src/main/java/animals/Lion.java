package animals;

import areas.Enclosure;
import areas.IArea;

public class Lion extends Animal{

    public Lion(String nickname) {
        super(nickname);
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        return animal.getClass().equals(Lion.class);
    }

    @Override
    public boolean canLiveIn(IArea area) {
        return area.getClass().equals(Enclosure.class);
    }
}

package animals;

import areas.Enclosure;
import areas.IArea;

public class Zebra extends Animal {

    public Zebra(String nickname) {
        super(nickname);
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        if (animal.getClass() == Lion.class){
           return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean canLiveIn(IArea area) {
        return area.getClass().equals(Enclosure.class);
    }
}

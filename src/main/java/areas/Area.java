package areas;

import java.util.ArrayList;

abstract public class Area implements IArea{
    private ArrayList<Integer> adjacentAreas = new ArrayList<>();

    public ArrayList<Integer> getAdjacentAreas() {
        return adjacentAreas;
    }

    public void addAdjacentArea(int areaId) {
        if (!adjacentAreas.contains(areaId)) {
            adjacentAreas.add(Integer.parseInt(String.valueOf(areaId)));
        }

    }

    public void removeAdjacentArea(int areaId) {
        adjacentAreas.remove(Integer.parseInt(String.valueOf(areaId)));
    }

}

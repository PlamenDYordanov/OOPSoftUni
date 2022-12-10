package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation{
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {

        for (Discoverer discoverer : discoverers) {
                for (String exhibit : spot.getExhibits()) {
                    while (discoverer.canDig()){
                        discoverer.dig();
                        discoverer.getMuseum().getExhibits().add(exhibit);
                        spot.getExhibits().remove(exhibit);
                }
            }
        }

    }
}

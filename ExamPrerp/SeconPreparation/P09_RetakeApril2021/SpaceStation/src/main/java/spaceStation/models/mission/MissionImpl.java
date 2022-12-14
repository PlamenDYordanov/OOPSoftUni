package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.Iterator;

public class MissionImpl implements  Mission{
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Iterator<String> planetItems = planet.getItems().iterator();
        for (Astronaut astronaut : astronauts) {
            while (astronaut.canBreath() && planetItems.hasNext()) {
                astronaut.breath();
                astronaut.getBag().getItems().add(planetItems.next());
                planetItems.remove();
            }
        }
    }
}

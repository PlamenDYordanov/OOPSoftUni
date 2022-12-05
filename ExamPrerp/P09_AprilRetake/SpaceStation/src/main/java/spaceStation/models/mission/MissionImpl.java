package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.Iterator;

public class MissionImpl implements Mission{
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Iterator<String> items = planet.getItems().iterator();
        for (Astronaut astronaut : astronauts) {
            while (astronaut.canBreath() && items.hasNext())
            if (astronaut.canBreath()) {
                astronaut.breath();
                astronaut.getBag().getItems().add(items.next());
                items.remove();
                }
            }
        }
    }

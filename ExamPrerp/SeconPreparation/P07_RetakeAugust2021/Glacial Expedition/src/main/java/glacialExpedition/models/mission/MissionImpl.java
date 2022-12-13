package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.Iterator;

public class MissionImpl implements  Mission{
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Iterator<String> exhibits = state.getExhibits().iterator();
        for (Explorer explorer : explorers) {
            while (explorer.canSearch() && exhibits.hasNext()) {
                explorer.search();
                explorer.getSuitcase().getExhibits().add(exhibits.next());
                exhibits.remove();
            }
        }
    }
}

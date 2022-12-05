package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.Iterator;

public class MissionImpl implements Mission{

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Iterator<String> exhibit = state.getExhibits().iterator();
        for (Explorer explorer : explorers) {
            while (explorer.canSearch() && exhibit.hasNext() ) {
                while (exhibit.hasNext()) {
                    explorer.search();
                    explorer.getSuitcase().getExhibits().add(exhibit.next());
                    exhibit.remove();
                    if (!explorer.canSearch()) {
                        break;
                    }
                }
              /*  for (String exhibit : state.getExhibits()) {
                    explorer.search();
                    explorer.getSuitcase().getExhibits().add(exhibit);
                    if (!explorer.canSearch()) {
                        break;
                    }
                }*/
            }
        }
    }
}

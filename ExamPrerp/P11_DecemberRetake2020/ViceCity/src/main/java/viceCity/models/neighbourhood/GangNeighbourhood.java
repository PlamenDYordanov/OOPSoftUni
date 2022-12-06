package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;
import java.util.Iterator;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        Iterator<Player> player = civilPlayers.iterator();
        Player curPlayer = player.next();
        boolean isItFinish = false;
        for (Gun gun : mainPlayer.getGunRepository().getModels()) {
            if (isItFinish) {
                break;
            }
            while (gun.canFire()) {
                int takeLifePoints = gun.fire();
                curPlayer.takeLifePoints(takeLifePoints);
                if (!curPlayer.isAlive()) {
                    if (player.hasNext()) {
                        curPlayer = player.next();
                    } else {
                        isItFinish =true;
                        break;
                    }
                }
            }
        }
        for (Player civilPlayer : civilPlayers) {
            if (!mainPlayer.isAlive()) {
                break;
            }
            for (Gun gun : civilPlayer.getGunRepository().getModels()) {
                while (gun.canFire() && mainPlayer.isAlive()) {
                    int takeLifePoints = gun.fire();
                    mainPlayer.takeLifePoints(takeLifePoints);

                }
            }
        }
    }
}

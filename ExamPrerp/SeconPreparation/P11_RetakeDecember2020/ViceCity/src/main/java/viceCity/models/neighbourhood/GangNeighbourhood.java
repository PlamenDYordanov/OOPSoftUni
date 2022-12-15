package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood{
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        for (Gun gun : mainPlayer.getGunRepository().getModels()) {
            for (Player civilPlayer : civilPlayers) {
                if (!gun.canFire()) {
                    break;
                }
                while (civilPlayer.isAlive() && gun.canFire()) {
                    civilPlayer.takeLifePoints( gun.fire());
                }
            }
        }
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                for (Gun civilGun : civilPlayer.getGunRepository().getModels()) {
                    if (!civilGun.canFire()) {
                        break;
                    }
                    while (civilGun.canFire() && mainPlayer.isAlive()) {
                        mainPlayer.takeLifePoints(civilGun.fire());
                        if (!mainPlayer.isAlive()) {
                            return;
                        }
                    }
                }
            }
        }
    }
}

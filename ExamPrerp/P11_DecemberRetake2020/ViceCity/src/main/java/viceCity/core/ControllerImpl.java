package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.GunRepository;

import java.util.*;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private GunRepository gunRepository;
    private Map<String, Player> players;
    private Player mainPlayer;

    public ControllerImpl() {
        this.gunRepository = new GunRepository();
        this.players = new LinkedHashMap<>();
        mainPlayer = new MainPlayer();
    }

    @Override
    public String addPlayer(String name) {
        Player civilPlayer = new CivilPlayer(name);
        players.put(name, civilPlayer);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return String.format(GUN_TYPE_INVALID);
        }
        gunRepository.add(gun);
        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (gunRepository.getModels().isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        } else {
            Gun gun = gunRepository.getModels().iterator().next();
            if (name.equals("Vercetti")) {
                mainPlayer.getGunRepository().add(gun);
                gunRepository.remove(gun);
                return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
            }
            for (Player player : players.values()) {
                if (player.getName().equals(name)) {
                    player.getGunRepository().add(gun);
                    gunRepository.remove(gun);
                    return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());
                }
            }
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }
    }

    @Override
    public String fight() {
        StringBuilder output = new StringBuilder();
        boolean isEverythingIsOk = true;
        for (Player player : players.values()) {
            if (!player.getGunRepository().getModels().isEmpty()) {
                isEverythingIsOk = false;
                break;
            }
        }
        if (mainPlayer.getGunRepository().getModels().isEmpty() && isEverythingIsOk) {
            return FIGHT_HOT_HAPPENED;
        }
        GangNeighbourhood gangNeighbourhood = new GangNeighbourhood();
        gangNeighbourhood.action(mainPlayer, players.values());
        long killedPlayers = players.values().stream().filter(player -> !player.isAlive()).count();
        long alivePlayers = players.values().stream().filter(player -> player.isAlive()).count();
        output.append(FIGHT_HAPPENED).append(System.lineSeparator());
        output.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints())).append(System.lineSeparator());
        output.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, killedPlayers)).append(System.lineSeparator());
        output.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, alivePlayers));
        return output.toString().trim();
    }
}

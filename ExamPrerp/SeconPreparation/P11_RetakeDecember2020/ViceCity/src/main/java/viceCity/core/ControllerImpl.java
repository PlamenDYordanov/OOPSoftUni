package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.*;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private ArrayDeque<Gun> gunRepository;
    private Map<String, Player> players;
    private Player mainPlayer;

    public ControllerImpl() {
        this.gunRepository = new ArrayDeque<>();
        this.players = new LinkedHashMap<>();
        this.mainPlayer = new MainPlayer();
    }

    @Override
    public String addPlayer(String name) {
        Player civilPlayer = new CivilPlayer(name);
        this.players.put(name, civilPlayer);
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
                return GUN_TYPE_INVALID;
        }
        gunRepository.offer(gun);
        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
       if (gunRepository.isEmpty()) {
           return GUN_QUEUE_IS_EMPTY;
       }
        if (name.equals("Vercetti")) {
            Gun gun = gunRepository.poll();
            mainPlayer.getGunRepository().add(gun);
           return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
       }
        Player currentPlayer = players.values().stream().filter(player -> player.getName().equals(name)).findFirst().orElse(null);
        if (currentPlayer == null) {
            return String.format(CIVIL_PLAYER_DOES_NOT_EXIST);
        }
        Gun gun = gunRepository.poll();
        currentPlayer.getGunRepository().add(gun);
        return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), currentPlayer.getName());
    }

    @Override
    public String fight() {
        boolean isEverythingIsOk = mainPlayer.getGunRepository().getModels().isEmpty();
        for (Player player : players.values()) {
            if(!player.getGunRepository().getModels().isEmpty()) {
                isEverythingIsOk = false;
            }
        }
        if (isEverythingIsOk) {
            return FIGHT_HOT_HAPPENED;
        }
        StringBuilder output = new StringBuilder();
            Neighbourhood gangNeighbourhood = new GangNeighbourhood();
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

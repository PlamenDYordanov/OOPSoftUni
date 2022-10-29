package Encapsulation.Exercise.P05_FootballGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
            this.name = name;


    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
            this.players.add(player);

    }

    public void removePlayer(String name) {
        boolean isExist = this.players.removeIf(player -> player.getName().equals(name));
        if (!isExist) {
            System.out.printf("Player %s is not in %s team.%n", name, getName());
        }
    }

    public double getRating() {
        return (players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0));
    }
}

package P05_FootballGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        Map<String, Team> teamMap = new LinkedHashMap<>();
        
        while (!command.equals("END")) {

            try {
                String[] tokens = command.split(";");
                String teamName = tokens[1];
                switch (tokens[0]) {
                    case "Team":
                        String name = tokens[1];
                        Team team = new Team(name);
                        teamMap.put(name, team);
                        break;
                    case "Add":
                        if (!teamMap.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            String playerName = tokens[2];
                            int endurance = Integer.parseInt(tokens[3]);
                            int sprint = Integer.parseInt(tokens[4]);
                            int dribble = Integer.parseInt(tokens[5]);
                            int passing = Integer.parseInt(tokens[6]);
                            int shooting = Integer.parseInt(tokens[7]);
                            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                            teamMap.get(teamName).addPlayer(player);
                        }
                        break;
                    case "Remove":
                        String playerName = tokens[2];
                        teamMap.get(teamName).removePlayer(playerName);
                        break;
                    case "Rating":
                        if (!teamMap.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            double sum = teamMap.get(teamName).getRating();
                            System.out.printf("%s - %d%n", teamName, Math.round(sum));
                        }
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            command = scanner.nextLine();
        }
    }
}

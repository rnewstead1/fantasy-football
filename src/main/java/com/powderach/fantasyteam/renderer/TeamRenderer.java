package com.powderach.fantasyteam.renderer;

import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.PlayerCostCalculator;
import com.powderach.fantasyteam.PlayerName;
import com.powderach.fantasyteam.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class TeamRenderer {
    private final Team team;
    private final PlayerCostCalculator calculator;

    public TeamRenderer(Team team, PlayerCostCalculator calculator) {
        this.team = team;
        this.calculator = calculator;
    }

    public JSONObject renderToJson() {
        JSONObject teamObject = new JSONObject();
        JSONArray teamArray = new JSONArray();
        for (Player player : team.allPlayers()) {
            JSONObject playerObject = new JSONObject();
            PlayerName playerName = player.name();
            playerObject.put("player_name", playerName.firstName() + " " +  playerName.surname());
            playerObject.put("position", player.position().display());
            playerObject.put("points", player.totalPoints());
            playerObject.put("selected_by", player.selectedBy());
            playerObject.put("price", player.cost());
            playerObject.put("team", player.team());
            teamArray.add(playerObject);
        }
        JSONObject costObject = new JSONObject();
        costObject.put("cost", teamCost());
        teamArray.add(costObject);
        teamObject.put("team", teamArray);
        return teamObject;
    }

    public String renderToString() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Player> goalkeepers = team.goalkeepers();
        List<Player> defenders = team.defenders();
        List<Player> midfielders = team.midfielders();
        List<Player> forwards = team.forwards();

        stringBuilder.append("TEAM\n");
        stringBuilder.append("Goalkeepers\n").append(render(goalkeepers)).append("\n");
        stringBuilder.append("Defenders\n").append(render(defenders)).append("\n");
        stringBuilder.append("Midfielders\n").append(render(midfielders)).append("\n");
        stringBuilder.append("Forwards\n").append(render(forwards)).append("\n");
        stringBuilder.append("Cost: £").append(teamCost());

        return stringBuilder.toString();
    }

    private int teamCost() {
        return calculator.calculateCost(team.allPlayers());
    }

    private String render(List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Player player : players) {
            PlayerName playerName = player.name();
            stringBuilder
                    .append(playerName.firstName())
                    .append(" ")
                    .append(playerName.surname())
                    .append(" ")
                    .append(player.team())
                    .append(" ")
                    .append("£")
                    .append(player.cost())
                    .append(" ")
                    .append(player.selectedBy())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}

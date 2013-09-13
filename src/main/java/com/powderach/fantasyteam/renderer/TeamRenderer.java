package com.powderach.fantasyteam.renderer;

import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.PlayerCostCalculator;
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
            playerObject.put("player_name", player.name());
            playerObject.put("position", player.position());
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
            stringBuilder
                    .append(player.get("first_name"))
                    .append(" ")
                    .append(player.get("surname"))
                    .append(" ")
                    .append(player.get("team"))
                    .append(" ")
                    .append("£")
                    .append(player.get("cost"))
                    .append(" ")
                    .append(player.get("selected_by"))
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}

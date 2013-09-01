package com.powderach.fantasyteam.renderer;

import com.powderach.fantasyteam.Player;
import com.powderach.fantasyteam.PlayerCostCalculator;
import com.powderach.fantasyteam.Team;

import java.util.List;

public class TeamRenderer {
    private final Team team;
    private final PlayerCostCalculator calculator;

    public TeamRenderer(Team team, PlayerCostCalculator calculator) {
        this.team = team;
        this.calculator = calculator;
    }

    public String render() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Player> goalkeepers = team.goalkeepers();
        List<Player> defenders = team.defenders();
        List<Player> midfielders = team.midfielders();
        List<Player> forwards = team.forwards();
        int cost = calculator.calculateCost(goalkeepers, defenders, midfielders, forwards);

        stringBuilder.append("TEAM\n");
        stringBuilder.append("Goalkeepers\n").append(render(goalkeepers)).append("\n");
        stringBuilder.append("Defenders\n").append(render(defenders)).append("\n");
        stringBuilder.append("Midfielders\n").append(render(midfielders)).append("\n");
        stringBuilder.append("Forwards\n").append(render(forwards)).append("\n");
        stringBuilder.append("Cost: £").append(cost);

        return stringBuilder.toString();
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

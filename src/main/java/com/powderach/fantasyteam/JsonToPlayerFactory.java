package com.powderach.fantasyteam;

import org.json.simple.JSONObject;

public class JsonToPlayerFactory {
    public Player createFrom(JSONObject jsonObject) {
        String firstName = String.valueOf(jsonObject.get("first_name"));
        String secondName = String.valueOf(jsonObject.get("second_name"));
        String team = String.valueOf(jsonObject.get("team_name"));
        String position = String.valueOf(jsonObject.get("type_name"));
        int lastSeasonPoints = (int) jsonObject.get("last_season_points");
        int price = (int) jsonObject.get("now_price");
        Double selectedBy = Double.parseDouble(String.valueOf(jsonObject.get("selected_by")));

        return new Player(firstName, secondName, team, position, lastSeasonPoints, price, selectedBy);
    }

}

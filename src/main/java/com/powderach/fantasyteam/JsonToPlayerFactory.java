package com.powderach.fantasyteam;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.sun.istack.internal.Nullable;
import org.json.simple.JSONObject;

public class JsonToPlayerFactory {
    public Optional<Player> createFrom(Optional<JSONObject> jsonObject) {
        return jsonObject.transform(new JsonObjectToPlayer());
    }

    private static class JsonObjectToPlayer implements Function<JSONObject, Player> {
        @Override
        public Player apply(@Nullable JSONObject jsonObject) {
            String firstName = String.valueOf(jsonObject.get("first_name"));
            String secondName = String.valueOf(jsonObject.get("second_name"));
            String team = String.valueOf(jsonObject.get("team_name"));
            Position position = Position.from(String.valueOf(jsonObject.get("type_name")));
            long lastSeasonPoints = (long) jsonObject.get("last_season_points");
            long cost = (long) jsonObject.get("now_cost");
            Double selectedBy = Double.parseDouble(String.valueOf(jsonObject.get("selected_by")));

            return new Player(firstName, secondName, team, position, lastSeasonPoints, cost, selectedBy);
        }
    }
}

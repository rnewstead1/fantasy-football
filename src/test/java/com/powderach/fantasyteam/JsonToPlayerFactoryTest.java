package com.powderach.fantasyteam;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonToPlayerFactoryTest {

    private String firstName;
    private String secondName;
    private String team;
    private String position;
    private int lastSeasonPoints;
    private int price;
    private Double selectedBy;

    @Before
    public void setUp() throws Exception {
        firstName = "Diego";
        secondName = "Lugano";
        team = "West Brom";
        position = "Defender";
        lastSeasonPoints = 0;
        price = 50;
        selectedBy = 0.3;
    }

    @Test
    public void createsPlayerFromJsonObject() throws Exception {
        Player player = new JsonToPlayerFactory().createFrom(createJsonObject());

        Player expectedPlayer = new Player(firstName, secondName, team, position, lastSeasonPoints, price, selectedBy);

        assertThat(player, is(expectedPlayer));
    }

    @SuppressWarnings("unchecked")
    private JSONObject createJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first_name", firstName);
        jsonObject.put("second_name", secondName);
        jsonObject.put("team_name", team);
        jsonObject.put("type_name", position);
        jsonObject.put("last_season_points", lastSeasonPoints);
        jsonObject.put("now_price", price);
        jsonObject.put("selected_by", selectedBy);

        return jsonObject;
    }

}

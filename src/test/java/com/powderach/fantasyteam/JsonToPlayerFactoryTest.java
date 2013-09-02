package com.powderach.fantasyteam;

import com.google.common.base.Optional;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonToPlayerFactoryTest {

    private String firstName;
    private String secondName;
    private String team;
    private Position position;
    private long cost;
    private Double selectedBy;
    private JsonToPlayerFactory jsonToPlayerFactory;
    private int points;

    @Before
    public void setUp() throws Exception {
        firstName = "Diego";
        secondName = "Lugano";
        team = "West Brom";
        position = Position.defender;
        cost = 50;
        selectedBy = 0.3;
        jsonToPlayerFactory = new JsonToPlayerFactory();
        points = 5;
    }

    @Test
    public void createsPlayerFromJsonObject() throws Exception {
        Optional<Player> player = jsonToPlayerFactory.createFrom(createJsonObject());

        Player expectedPlayer = new Player(firstName, secondName, team, position, cost, selectedBy, points);

        assertThat(player.get(), is(expectedPlayer));
    }

    @Test
    public void createsAbsentPlayerFromAbsentJsonObject() throws Exception {
        Optional<Player> player = jsonToPlayerFactory.createFrom(Optional.<JSONObject>absent());

        assertThat(player.isPresent(), is(false));
    }

    @SuppressWarnings("unchecked")
    private Optional<JSONObject> createJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first_name", firstName);
        jsonObject.put("second_name", secondName);
        jsonObject.put("team_name", team);
        jsonObject.put("type_name", position.display());
        jsonObject.put("now_cost", cost);
        jsonObject.put("selected_by", selectedBy);
        jsonObject.put("total_points", points);

        return Optional.of(jsonObject);
    }

}

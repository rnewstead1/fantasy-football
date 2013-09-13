package com.powderach.fantasyteam.renderer;

import com.powderach.fantasyteam.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static com.powderach.fantasyteam.Position.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TeamRendererTest {

    private TeamRenderer teamRenderer;

    @Before
    public void setUp() throws Exception {
        Team team = new TeamBuilder().with(asList(new Player("David", "James", "", goalkeeper, 5, 7.5, 6))).build();
        teamRenderer = new TeamRenderer(team, new PlayerCostCalculator());
    }

    @Test
    public void rendersToJson() throws Exception {
        JSONObject jsonObject = teamRenderer.renderToJson();

        assertThat(jsonObject, is(expected()));
    }

    private JSONObject expected() {
        JSONObject teamWrapper = new JSONObject();
        JSONArray teamArray = new JSONArray();
        JSONObject player = new JSONObject();
        player.put("player_name", "David James");
        player.put("position", goalkeeper.display());
        teamArray.add(player);
        JSONObject cost = new JSONObject();
        cost.put("cost", 5);
        teamArray.add(cost);
        teamWrapper.put("team", teamArray);

        return teamWrapper;
    }
}

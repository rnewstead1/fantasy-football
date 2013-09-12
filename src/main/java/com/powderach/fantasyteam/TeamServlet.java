package com.powderach.fantasyteam;

import com.mongodb.util.JSON;
import com.powderach.fantasyteam.store.TeamStore;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.powderach.fantasyteam.store.MongoClientConnector.collectionFor;

public class TeamServlet extends HttpServlet {
    private final TeamStore teamStore;

    public TeamServlet() {
        teamStore = new TeamStore(collectionFor("playerdb", "team"));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            String content = createJsonObject().toJSONString();
            Team team = teamStore.retrieve();
            String content = JSON.serialize(team);
            writeToResponse(response, content, 200);
        } catch (RuntimeException e) {
            writeToResponse(response, e.getMessage(), 400);
        }
    }

    public String contextPath() {
        return "/team/*";
    }

    private void writeToResponse(HttpServletResponse response, String content, int status) throws IOException {
        response.setStatus(status);
        response.setContentLength(content.length());
        response.setContentType("application/json");
        response.getWriter().write(content);
    }

    private JSONObject createJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first_name", "Ryan");
        jsonObject.put("second_name", "Giggs");
        jsonObject.put("team_name", "Manchester United");
        jsonObject.put("type_name", Position.forward.display());
        jsonObject.put("now_cost", 8);
        jsonObject.put("selected_by", 3.2);
        jsonObject.put("total_points", 67);

        return jsonObject;
    }
}
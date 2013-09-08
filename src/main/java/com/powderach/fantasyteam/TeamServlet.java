package com.powderach.fantasyteam;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String content = createJsonObject().toJSONString();
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
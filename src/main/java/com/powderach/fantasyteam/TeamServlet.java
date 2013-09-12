package com.powderach.fantasyteam;

import com.mongodb.util.JSON;
import com.powderach.fantasyteam.store.TeamStore;

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
            Team team = teamStore.retrieve();
            String content = JSON.serialize(team);
            writeToResponse(response, content, 200);
        } catch (RuntimeException e) {
            writeToResponse(response, e.getMessage(), 400);
        }
    }

    public String contextPath() {
        return "/api/team/*";
    }

    private void writeToResponse(HttpServletResponse response, String content, int status) throws IOException {
        response.setStatus(status);
        response.setContentLength(content.length());
        response.setContentType("application/json");
        response.getWriter().write(content);
    }

}
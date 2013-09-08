package com.powderach.fantasyteam;

import org.eclipse.jetty.server.Server;

public class FantasyTeam {
    private final Server server;

    public static void main(String[] args) throws Exception {
        new FantasyTeam().start();
    }

    private FantasyTeam() {
        server = new Server(8080);

        ServletWrapper servletWrapper = new ServletWrapper("/fantasy-team");
        servletWrapper.staticResources("src/main/javascript/");

        servletWrapper.add(new TeamServlet());

        server.setHandler(servletWrapper.asHandler());
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

}

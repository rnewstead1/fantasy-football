package com.powderach.fantasyteam;

public class TeamSelection {
    private final PlayerSelectionFactory playerSelectionFactory;

    public TeamSelection(PlayerSelectionFactory playerSelectionFactory) {
        this.playerSelectionFactory = playerSelectionFactory;
    }

    public Team select() {
        Team team = new Team();
        team.addGoalkeepers(playerSelectionFactory.mostSelectedGoalkeepers(1));
        team.addForwards(playerSelectionFactory.mostSelectedForwards(2));
        team.addMidfielders(playerSelectionFactory.mostSelectedMidfielders(2));
        team.addDefenders(playerSelectionFactory.mostSelectedDefenders(2));

        team.addMidfielders(playerSelectionFactory.cheapestMidfieldersInMostMostPopular(3, 15));
        team.addDefenders(playerSelectionFactory.cheapestDefendersInMostPopular(3, 15));
        team.addForwards(playerSelectionFactory.cheapestForwardInMostPopular(1, 20));
        team.addGoalkeepers(playerSelectionFactory.cheapestGoalkeeperInMostPopular(1, 20));

        return team;
    }

}

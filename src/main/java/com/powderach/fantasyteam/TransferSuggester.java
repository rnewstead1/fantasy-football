package com.powderach.fantasyteam;

import com.powderach.fantasyteam.store.PlayerSelector;

import java.util.List;

public class TransferSuggester {
    private final PlayerSelector playerSelector;

    public TransferSuggester(PlayerSelector playerSelector) {
        this.playerSelector = playerSelector;
    }

    public List<Player> suggestFor(Player currentPlayer) {
        return playerSelector.playersOfTheSamePositionAs(currentPlayer);
    }

}

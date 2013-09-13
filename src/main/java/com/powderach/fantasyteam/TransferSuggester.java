package com.powderach.fantasyteam;

import com.google.common.base.Predicate;
import com.powderach.fantasyteam.store.PlayerSelector;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Collections2.filter;

public class TransferSuggester {
    private final PlayerSelector playerSelector;

    public TransferSuggester(PlayerSelector playerSelector) {
        this.playerSelector = playerSelector;
    }

    public Collection<Player> suggestFor(final Player currentPlayer) {
        final Long currentPlayerCost = currentPlayer.cost();

        List<Player> playersOfTheSamePosition = playerSelector.playersOfTheSamePositionAs(currentPlayer);
        return filter(playersOfTheSamePosition, new Predicate<Player>() {
            @Override
            public boolean apply(Player player) {
                return player.cost() <= currentPlayerCost;
            }
        });
    }

}

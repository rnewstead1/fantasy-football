package com.powderach.fantasyteam;

import com.powderach.fantasyteam.store.PlayerSelector;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.powderach.fantasyteam.Position.goalkeeper;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransferSuggesterTest {

    private TransferSuggester transferSuggester;
    private PlayerSelector playerSelector;
    private Player currentPlayer;

    @Before
    public void setUp() throws Exception {
        playerSelector = mock(PlayerSelector.class);
        transferSuggester = new TransferSuggester(playerSelector);
        currentPlayer = new Player("Simon", "Mignolet", "Liverpool", goalkeeper, 50, 0.5, 160);
    }

    @Test
    public void suggestsTransferOfPlayerOfSamePriceAndMorePoints() throws Exception {
        Player suggestedPlayer = new Player("Luke", "Moore", "Swansea", goalkeeper, 50, 0.5, 170);
        when(playerSelector.playersOfTheSamePositionAs(currentPlayer)).thenReturn(asList(suggestedPlayer));

        Collection<Player> transfers = transferSuggester.suggestFor(currentPlayer);

        assertThat(transfers, hasItems(suggestedPlayer));
    }

    @Test
    public void doesNotSuggestMoreExpensivePlayers() throws Exception {
        Player moreExpensivePlayer = new Player("Luke", "Moore", "Swansea", goalkeeper, 70, 0.5, 160);
        when(playerSelector.playersOfTheSamePositionAs(currentPlayer)).thenReturn(asList(moreExpensivePlayer));

        Collection<Player> transfers = transferSuggester.suggestFor(currentPlayer);

        assertThat(transfers, not(hasItem(moreExpensivePlayer)));
    }

    @Test
    public void doesNotSuggestPlayerWithLessPoints() throws Exception {
        Player playerWithLessPoints = new Player("Luke", "Moore", "Swansea", goalkeeper, 50, 0.5, 60);
        when(playerSelector.playersOfTheSamePositionAs(currentPlayer)).thenReturn(asList(playerWithLessPoints));

        Collection<Player> transfers = transferSuggester.suggestFor(currentPlayer);

        assertThat(transfers, not(hasItem(playerWithLessPoints)));
    }
}

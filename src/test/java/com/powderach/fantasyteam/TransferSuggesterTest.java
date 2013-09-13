package com.powderach.fantasyteam;

import com.powderach.fantasyteam.store.PlayerSelector;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.powderach.fantasyteam.Position.goalkeeper;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransferSuggesterTest {

    private TransferSuggester transferSuggester;
    private PlayerSelector playerSelector;

    @Before
    public void setUp() throws Exception {
        playerSelector = mock(PlayerSelector.class);
        transferSuggester = new TransferSuggester(playerSelector);
    }

    @Test
    public void suggestsTransfers() throws Exception {
        Player currentPlayer = new Player("Simon", "Mignolet", "Liverpool", goalkeeper, 50, 0.5, 6);
        Player suggestedPlayer = new Player("Luke", "Moore", "Swansea", goalkeeper, 50, 0.5, 6);
        when(playerSelector.playersOfTheSamePositionAs(currentPlayer)).thenReturn(asList(suggestedPlayer));

        List<Player> transfers = transferSuggester.suggestFor(currentPlayer);

        assertThat(transfers, is(asList(suggestedPlayer)));
    }
}

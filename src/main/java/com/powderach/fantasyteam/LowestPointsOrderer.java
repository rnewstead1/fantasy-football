package com.powderach.fantasyteam;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;

import java.util.Map;

public class LowestPointsOrderer {

    public Map<PlayerName, Long> sortByLowestPoints(Map<PlayerName, Long> currentPlayers) {

        Ordering<Map.Entry<PlayerName, Long>> entryOrdering = Ordering.natural()
                .onResultOf(new Function<Map.Entry<PlayerName, Long>, Long>() {
                    public Long apply(Map.Entry<PlayerName, Long> entry) {
                        return entry.getValue();
                    }
                });

        ImmutableMap.Builder<PlayerName, Long> builder = ImmutableMap.builder();
        for (Map.Entry<PlayerName, Long> entry :
                entryOrdering.sortedCopy(currentPlayers.entrySet())) {
            builder.put(entry.getKey(), entry.getValue());
        }

        return builder.build();
    }
}

package com.powderach.fantasyteam.runner;

import com.powderach.fantasyteam.JsonReader;
import com.powderach.fantasyteam.JsonToPlayerFactory;
import com.powderach.fantasyteam.store.DataCollection;

public class DataCollectionRunner {
    public static void main(String[] args) {
        DataCollection dataCollection = new DataCollection(new JsonReader(), new JsonToPlayerFactory());

        dataCollection.getDataUpToPlayerNumber(532);
    }

}

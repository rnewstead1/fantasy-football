package com.powderach.fantasyteam;

import com.google.common.base.Optional;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonReaderTest {

    private JsonReader jsonReader;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File file;
    private String url;

    @Before
    public void setUp() throws Exception {
        jsonReader = new JsonReader();
        file = folder.newFile("jsonFile.txt");
        url = file.toURI().toURL().toExternalForm();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void createsJsonObjectFromUrl() throws Exception {
        JSONObject expected = new JSONObject();
        expected.put("name", "value");

        String jsonString = "{\n" +
                "    \"name\": \"value\", \n" +
                "}";
        writeContentToFile(jsonString, file);
        Optional<JSONObject> jsonObject = jsonReader.readJsonFromUrl(url);

        assertThat(jsonObject.get(), is(expected));
    }

    @Test
    public void createsAbsentJsonObjectIfExceptionIsThrownDuringCreation() throws Exception {
        Optional<JSONObject> jsonObject = jsonReader.readJsonFromUrl(url);

        assertThat(jsonObject.isPresent(), is(false));
    }

    private void writeContentToFile(String content, File file) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(content);
        output.close();
    }
}

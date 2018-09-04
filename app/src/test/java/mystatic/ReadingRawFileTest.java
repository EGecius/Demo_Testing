package mystatic;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

public class ReadingRawFileTest {


    private static final String EXPECTED_JSON_CONTENT = "{\"name\": \"Egis\"}";

    @Test
    public void readJsonFile() {
        String resourceAsString = getResourceAsString("/remote_config.json");
        assertThat(resourceAsString).isEqualTo(EXPECTED_JSON_CONTENT);
    }


    @SuppressWarnings("SameParameterValue")
    private String getResourceAsString(String resource) {
        try {
            return IOUtils.toString(this.getClass().getResourceAsStream(resource), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("error reading file");
    }
}

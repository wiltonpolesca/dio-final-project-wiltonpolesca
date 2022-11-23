package dio.projeto.polesca.parking.helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CSVLoaderHelp {
    private static final String COMMA_DELIMITER = ";";

    public List<List<String>> loadCSV(String resourcePath) throws FileNotFoundException, IOException {
        List<List<String>> records = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream stream = classLoader.getResourceAsStream(resourcePath);

        try (var br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }
}

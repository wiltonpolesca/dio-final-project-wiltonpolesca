package dio.projeto.polesca.parking.mockData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dio.projeto.polesca.parking.helpers.CSVLoaderHelp;
import dio.projeto.polesca.parking.helpers.UUIDHelper;
import dio.projeto.polesca.parking.models.Parking;
import dio.projeto.polesca.parking.models.VehicleBrand;
import dio.projeto.polesca.parking.models.VehicleModel;

public class ParkingMock {
    private static Map<Integer, VehicleBrand> brandMap = new HashMap<>();
    private static List<VehicleModel> modelMap = new ArrayList<>();
    private static List<String> colors = Arrays.asList("Red", "Grey", "Purple", "Pink", "White", "Black");

    static CSVLoaderHelp csvLoader = new CSVLoaderHelp();

    static {
        try {
            LoadBrand();
            LoadModels();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void LoadBrand() throws FileNotFoundException, IOException {
        var data = csvLoader.loadCSV("brand-vehicles.csv");
        var count = 0;
        for (var list : data) {
            if (count != 0) {
                var brand = new VehicleBrand(Integer.parseInt(list.get(0)), list.get(1));
                brandMap.put(brand.getId(), brand);
            }
            count++;
        }
    }

    private static void LoadModels() throws FileNotFoundException, IOException {
        var data = csvLoader.loadCSV("model-vehicles.csv");
        var count = 0;
        for (var list : data) {
            if (count != 0) {
                var brand = brandMap.get(Integer.parseInt(list.get(1)));
                if (brand == null) {
                    System.out.println(list.get(1));
                }
                var model = new VehicleModel(Integer.parseInt(list.get(0)), brand, list.get(2));
                modelMap.add(model);
            }
            count++;
        }
    }

    public static List<Parking> getParkins(Integer quantity) {
        var parkings = new ArrayList<Parking>();

        while (parkings.size() < quantity) {
            var modelIndex = getRandom(0, modelMap.size());
            var colorIndex = getRandom(0, colors.size());
            var model = modelMap.get(modelIndex);
            parkings.add(new Parking(UUIDHelper.getUUID(), getAlphaNumericString(8),
                    model.getBrand().getName() + " " + model.getName(), colors.get(colorIndex),
                    getAlphaNumericString(2).toUpperCase(), LocalDateTime.now()));
        }

        return parkings;
    }

    private static Integer getRandom(Integer min, Integer max) {
        var r = new Random();
        return r.nextInt(max - min) + min;
    }

    private static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}

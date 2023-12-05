package Day_5;

import java.io.File;
import java.util.*;

public class Day5Part1 {
    private static final File FILE = new File("src/main/resources/garden.txt");
    private static final List<String> INPUT_LINES = new ArrayList<>();
    private static final List<Long> SEEDS = new ArrayList<>();
    private static final HashMap<Long, Long> SEED_TO_SOIL = new HashMap<>();
    private static final HashMap<Long, Long> SOIL_TO_FERTILISER = new HashMap<>();
    private static final HashMap<Long, Long> FERTILISER_TO_WATER = new HashMap<>();
    private static final HashMap<Long, Long> WATER_TO_LIGHT = new HashMap<>();
    private static final HashMap<Long, Long> LIGHT_TO_TEMPERATURE = new HashMap<>();
    private static final HashMap<Long, Long> TEMPERATURE_TO_HUMIDITY = new HashMap<>();
    private static final HashMap<Long, Long> HUMIDITY_TO_LOCATION = new HashMap<>();

    public static void main(String[] args) {
        importData();
        extractImportedDataToCollections();
        List<Long> locations = getLocations();
        long lowestNumber =  locations.stream().min(Long::compare).orElseThrow();
        System.out.println("The lowest location number is: " + lowestNumber);
    }

    private static List<Long> getLocations() {
        List<Long> locations = new ArrayList<>();
        for (long seedNumber : SEEDS) {
            long soilNumber = SEED_TO_SOIL.getOrDefault(seedNumber, seedNumber);
            long fertiliserNumber = SOIL_TO_FERTILISER.getOrDefault(soilNumber, soilNumber);
            long waterNumber = FERTILISER_TO_WATER.getOrDefault(fertiliserNumber, fertiliserNumber);
            long lightNumber = WATER_TO_LIGHT.getOrDefault(waterNumber, waterNumber);
            long temperatureNumber = LIGHT_TO_TEMPERATURE.getOrDefault(lightNumber, lightNumber);
            long humidityNumber = TEMPERATURE_TO_HUMIDITY.getOrDefault(temperatureNumber, temperatureNumber);
            locations.add(HUMIDITY_TO_LOCATION.getOrDefault(humidityNumber, humidityNumber));
        }
        return locations;
    }


    private static void importData() {
        try (Scanner scanner = new Scanner(FILE)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                INPUT_LINES.add(line);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void extractImportedDataToCollections(){
        boolean seadToSoilMapReadyForMapping = false;
        boolean soilToFertiliserMapReadyForMapping = false;
        boolean fertiliserToWaterMapReadyForMapping = false;
        boolean waterToLightMapReadyForMapping = false;
        boolean lightToTemperatureMapReadyForMapping = false;
        boolean temperatureToHumidityMapReadyForMapping = false;
        boolean humidityToLocationMapReadyForMapping = false;

        for(String line : INPUT_LINES){
            if(line.contains("seeds")) extractSeedNumbers(line);

            if(line.contains("seed-to-soil map")) {
                seadToSoilMapReadyForMapping = true;
            }
            if(line.contains("soil-to-fertilizer map")) {
                soilToFertiliserMapReadyForMapping = true;
                seadToSoilMapReadyForMapping = false;
            }
            if(line.contains("fertilizer-to-water map")) {
                fertiliserToWaterMapReadyForMapping = true;
                soilToFertiliserMapReadyForMapping = false;
            }
            if(line.contains("water-to-light map")) {
                waterToLightMapReadyForMapping = true;
                fertiliserToWaterMapReadyForMapping = false;
            }
            if(line.contains("light-to-temperature map")) {
                lightToTemperatureMapReadyForMapping = true;
                waterToLightMapReadyForMapping = false;
            }
            if(line.contains("temperature-to-humidity map")) {
                temperatureToHumidityMapReadyForMapping = true;
                lightToTemperatureMapReadyForMapping = false;
            }
            if(line.contains("humidity-to-location map")) {
                humidityToLocationMapReadyForMapping = true;
                temperatureToHumidityMapReadyForMapping = false;
            }

            if(seadToSoilMapReadyForMapping && line.matches("\\d+.*")){
                mapSeedToSoil(line);
            }

            if(soilToFertiliserMapReadyForMapping && line.matches("\\d+.*")){
                mapSoilToFertliser(line);
            }

            if(fertiliserToWaterMapReadyForMapping && line.matches("\\d+.*")){
                mapFertiliserToWater(line);
            }

            if(waterToLightMapReadyForMapping && line.matches("\\d+.*")){
                mapWaterToLight(line);
            }

            if(lightToTemperatureMapReadyForMapping && line.matches("\\d+.*")){
                mapLightToTemperature(line);
            }

            if(temperatureToHumidityMapReadyForMapping && line.matches("\\d+.*")){
                mapTemperatureToHumidity(line);
            }
            if(humidityToLocationMapReadyForMapping && line.matches("\\d+.*")){
                mapHumidityToLocation(line);
            }
        }
    }

    private static void mapHumidityToLocation(String line) {
        String [] numbers = line.split(" ");
        long humidityStartNumber = Long.parseLong(numbers[1]);
        long locationStartNumber = Long.parseLong(numbers[0]);
        long duration = Long.parseLong(numbers[2]);

        for(int mappingNumber = 0; mappingNumber < duration; mappingNumber++){
            HUMIDITY_TO_LOCATION.put(humidityStartNumber++, locationStartNumber++);
        }
    }

    private static void mapTemperatureToHumidity(String line) {
        String[] numbers = line.split(" ");
        long temperatureStartNumber = Long.parseLong(numbers[1]);
        long humidityStartNumber = Long.parseLong(numbers[0]);
        long duration = Long.parseLong(numbers[2]);

        for (int mappingNumber = 0; mappingNumber < duration; mappingNumber++) {
            TEMPERATURE_TO_HUMIDITY.put(temperatureStartNumber++, humidityStartNumber++);
        }
    }

    private static void mapLightToTemperature(String line) {
        String[] numbers = line.split(" ");
        long lightStartNumber = Long.parseLong(numbers[1]);
        long temperatureStartNumber = Long.parseLong(numbers[0]);
        long duration = Long.parseLong(numbers[2]);

        for (int mappingNumber = 0; mappingNumber < duration; mappingNumber++) {
            LIGHT_TO_TEMPERATURE.put(lightStartNumber++, temperatureStartNumber++);
        }
    }

    private static void mapWaterToLight(String line) {
        String[] numbers = line.split(" ");
        long waterStartNumber = Long.parseLong(numbers[1]);
        long lightStartNumber = Long.parseLong(numbers[0]);
        long duration = Long.parseLong(numbers[2]);

        for (int mappingNumber = 0; mappingNumber < duration; mappingNumber++) {
            WATER_TO_LIGHT.put(waterStartNumber++, lightStartNumber++);
        }
    }

    private static void mapFertiliserToWater(String line) {
        String[] numbers = line.split(" ");
        long fertiliserStartNumber = Long.parseLong(numbers[1]);
        long waterStartNumber = Long.parseLong(numbers[0]);
        long duration = Long.parseLong(numbers[2]);

        for (int mappingNumber = 0; mappingNumber < duration; mappingNumber++) {
            FERTILISER_TO_WATER.put(fertiliserStartNumber++, waterStartNumber++);
        }
    }

    private static void mapSoilToFertliser(String line) {
        String[] numbers = line.split(" ");
        long soilStartNumber = Long.parseLong(numbers[1]);
        long fertiliserStartNumber = Long.parseLong(numbers[0]);
        long duration = Long.parseLong(numbers[2]);

        for (int mappingNumber = 0; mappingNumber < duration; mappingNumber++) {
            SOIL_TO_FERTILISER.put(soilStartNumber++, fertiliserStartNumber++);
        }
    }

    private static void mapSeedToSoil(String line) {
        String[] numbers = line.split(" ");
        long seedStartNumber = Long.parseLong(numbers[1]);
        long soilStartNumber = Long.parseLong(numbers[0]);
        long duration = Long.parseLong(numbers[2]);

        for (int mappingNumber = 0; mappingNumber < duration; mappingNumber++) {
            SEED_TO_SOIL.put(seedStartNumber++, soilStartNumber++);
        }
    }

    private static void extractSeedNumbers(String line) {
        String[] numbers = line.split(" ");
        Arrays.stream(numbers)
                .filter(number -> number.matches("\\d+"))
                .forEach(number -> SEEDS.add(Long.parseLong(number)));
    }
}

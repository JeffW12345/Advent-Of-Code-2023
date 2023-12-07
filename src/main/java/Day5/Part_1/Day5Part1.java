package Day5.Part_1;

import java.io.File;
import java.util.*;

public class Day5Part1 {
    private static final File FILE = new File("src/main/resources/garden.txt");
    private static final List<String> INPUT_LINES = new ArrayList<>();
    private static final List<Long> SEEDS = new ArrayList<>();
    private static final List<Mapping> SEED_TO_SOIL = new ArrayList<>();
    private static final List<Mapping> SOIL_TO_FERTILISER = new ArrayList<>();
    private static final List<Mapping> FERTILISER_TO_WATER = new ArrayList<>();
    private static final List<Mapping> WATER_TO_LIGHT = new ArrayList<>();
    private static final List<Mapping> LIGHT_TO_TEMPERATURE = new ArrayList<>();
    private static final List<Mapping> TEMPERATURE_TO_HUMIDITY = new ArrayList<>();
    private static final List<Mapping> HUMIDITY_TO_LOCATION = new ArrayList<>();

    public static void main(String[] args) {
        importData();
        extractImportedDataToCollections();
        List<Long> locations = getLocations();
        long lowestNumber = locations.stream().min(Long::compare).orElseThrow();
        System.out.println("The lowest location number is: " + lowestNumber);
    }

    private static List<Long> getLocations() {
        List<Long> locations = new ArrayList<>();
        for (long seedNumber : SEEDS) {
            long soilNumber = calculateValuesFromKeys(SEED_TO_SOIL, seedNumber);
            long fertiliserNumber = calculateValuesFromKeys(SOIL_TO_FERTILISER, soilNumber);
            long waterNumber = calculateValuesFromKeys(FERTILISER_TO_WATER, fertiliserNumber);
            long lightNumber = calculateValuesFromKeys(WATER_TO_LIGHT, waterNumber);
            long temperatureNumber = calculateValuesFromKeys(LIGHT_TO_TEMPERATURE, lightNumber);
            long humidityNumber = calculateValuesFromKeys(TEMPERATURE_TO_HUMIDITY, temperatureNumber);
            locations.add(calculateValuesFromKeys(HUMIDITY_TO_LOCATION, humidityNumber));
        }
        return locations;
    }

    private static long calculateValuesFromKeys(List<Mapping> mappings, long inputValue) {
        for (Mapping mapping : mappings) {
            if (mapping.withinRange(inputValue)) {
                return mapping.calculateValueFromKey(inputValue);
            }
        }
        return inputValue;
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

    private static void extractImportedDataToCollections() {
        boolean seedToSoilMapReadyForMapping = false;
        boolean soilToFertiliserMapReadyForMapping = false;
        boolean fertiliserToWaterMapReadyForMapping = false;
        boolean waterToLightMapReadyForMapping = false;
        boolean lightToTemperatureMapReadyForMapping = false;
        boolean temperatureToHumidityMapReadyForMapping = false;
        boolean humidityToLocationMapReadyForMapping = false;

        for (String line : INPUT_LINES) {
            if (line.contains("seeds")) extractSeedNumbers(line);

            if (line.contains("seed-to-soil map")) {
                seedToSoilMapReadyForMapping = true;
            }
            if (line.contains("soil-to-fertilizer map")) {
                soilToFertiliserMapReadyForMapping = true;
                seedToSoilMapReadyForMapping = false;
            }
            if (line.contains("fertilizer-to-water map")) {
                fertiliserToWaterMapReadyForMapping = true;
                soilToFertiliserMapReadyForMapping = false;
            }
            if (line.contains("water-to-light map")) {
                waterToLightMapReadyForMapping = true;
                fertiliserToWaterMapReadyForMapping = false;
            }
            if (line.contains("light-to-temperature map")) {
                lightToTemperatureMapReadyForMapping = true;
                waterToLightMapReadyForMapping = false;
            }
            if (line.contains("temperature-to-humidity map")) {
                temperatureToHumidityMapReadyForMapping = true;
                lightToTemperatureMapReadyForMapping = false;
            }
            if (line.contains("humidity-to-location map")) {
                humidityToLocationMapReadyForMapping = true;
                temperatureToHumidityMapReadyForMapping = false;
            }

            if (seedToSoilMapReadyForMapping && line.matches("\\d+.*")) {
                mapSeedToSoil(line);
            }

            if (soilToFertiliserMapReadyForMapping && line.matches("\\d+.*")) {
                mapSoilToFertliser(line);
            }

            if (fertiliserToWaterMapReadyForMapping && line.matches("\\d+.*")) {
                mapFertiliserToWater(line);
            }

            if (waterToLightMapReadyForMapping && line.matches("\\d+.*")) {
                mapWaterToLight(line);
            }

            if (lightToTemperatureMapReadyForMapping && line.matches("\\d+.*")) {
                mapLightToTemperature(line);
            }

            if (temperatureToHumidityMapReadyForMapping && line.matches("\\d+.*")) {
                mapTemperatureToHumidity(line);
            }
            if (humidityToLocationMapReadyForMapping && line.matches("\\d+.*")) {
                mapHumidityToLocation(line);
            }
        }
    }

    private static void mapHumidityToLocation(String line) {
        String[] numbers = line.split(" ");

        Mapping mapping = new Mapping(
                Long.parseLong(numbers[1]),
                Long.parseLong(numbers[0]),
                Long.parseLong(numbers[2])
        );

        HUMIDITY_TO_LOCATION.add(mapping);
    }

    private static void mapTemperatureToHumidity(String line) {
        String[] numbers = line.split(" ");

        Mapping mapping = new Mapping(
                Long.parseLong(numbers[1]),
                Long.parseLong(numbers[0]),
                Long.parseLong(numbers[2])
        );

        TEMPERATURE_TO_HUMIDITY.add(mapping);
    }

    private static void mapLightToTemperature(String line) {
        String[] numbers = line.split(" ");

        Mapping mapping = new Mapping(
                Long.parseLong(numbers[1]),
                Long.parseLong(numbers[0]),
                Long.parseLong(numbers[2])
        );

        LIGHT_TO_TEMPERATURE.add(mapping);
    }

    private static void mapWaterToLight(String line) {
        String[] numbers = line.split(" ");

        Mapping mapping = new Mapping(
                Long.parseLong(numbers[1]),
                Long.parseLong(numbers[0]),
                Long.parseLong(numbers[2])
        );

        WATER_TO_LIGHT.add(mapping);
    }

    private static void mapFertiliserToWater(String line) {
        String[] numbers = line.split(" ");

        Mapping mapping = new Mapping(
                Long.parseLong(numbers[1]),
                Long.parseLong(numbers[0]),
                Long.parseLong(numbers[2])
        );

        FERTILISER_TO_WATER.add(mapping);
    }

    private static void mapSoilToFertliser(String line) {
        String[] numbers = line.split(" ");

        Mapping mapping = new Mapping(
                Long.parseLong(numbers[1]),
                Long.parseLong(numbers[0]),
                Long.parseLong(numbers[2])
        );

        SOIL_TO_FERTILISER.add(mapping);
    }

    private static void mapSeedToSoil(String line) {
        String[] numbers = line.split(" ");

        Mapping mapping = new Mapping(
                Long.parseLong(numbers[1]),
                Long.parseLong(numbers[0]),
                Long.parseLong(numbers[2])
        );

        SEED_TO_SOIL.add(mapping);
    }

    private static void extractSeedNumbers(String line) {
        String[] numbers = line.split(" ");
        Arrays.stream(numbers)
                .filter(number -> number.matches("\\d+"))
                .forEach(number -> SEEDS.add(Long.parseLong(number)));
    }
}

package org.example;

import database.DatabaseConnection;
import database.DatabaseSetup;
import dto.CarModelDTO;
import repository.CarModelRepository;
import Service.CarModelService;
import Service.CarModelServiceImpl;
import Service.CarService;
import mapper.CarMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Zadanie4 {

    public static void main(String[] args) {
        CarModelService carModelService = new CarModelServiceImpl();
        CarMapper carMapper = CarMapper.INSTANCE;

        String filePath = "src\\main\\resources\\cars.csv";
        carModelService.load(filePath);

        System.out.println("Список всех автомобилей марки Lexus:");
        List<CarModelDTO> toyotaCars = carModelService.getAllCarDTOs("Lexus");
        if (toyotaCars.isEmpty()) {
            System.out.println("Автомобили марки Lexus не найдены.");
        } else {
            toyotaCars.forEach(System.out::println);
        }

        System.out.println("\nПоиск автомобиля по ID:");
        CarModelDTO carToFind = new CarModelDTO(5, null, null, null, null); // ID = 3
        Optional<CarModelDTO> foundCar = carModelService.findCarById(carToFind);
        foundCar.ifPresentOrElse(
                car -> System.out.println("Автомобиль найден: " + car),
                () -> System.out.println("Автомобиль с таким идентификатором не найден.")
        );

        System.out.println("\nГруппировка моделей автомобилей марки BMW:");
        Map<String, Integer> bmwModelsCount = carModelService.getCarModelGroupByModel("BMW");
        bmwModelsCount.forEach((model, count) ->
                System.out.println("Модель: " + model + ", Количество: " + count)
        );

        System.out.println("\nУникальные марки автомобилей:");
        Set<String> uniqueCarBrands = carModelService.getUniqueBrands();
        uniqueCarBrands.forEach(System.out::println);

        String targetBrand = "Toyota";
        System.out.println("\n Список моделей для марки " + targetBrand + ":");
        List<String> toyotaModels = carModelService.findModelsByBrand(targetBrand);
        if (toyotaModels.isEmpty()) {
            System.out.println("Для марки " + targetBrand + " модели отсутствуют.");
        } else {
            toyotaModels.forEach(System.out::println);
        }

        System.out.println("\n Группировка автомобилей по маркам:");
        Map<String, Integer> brandGrouping = carModelService.groupByBrand();
        brandGrouping.forEach((brand, count) ->
                System.out.println("Марка: " + brand + ", Количество: " + count)
        );
    }
}


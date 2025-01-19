package org.example;

import database.DatabaseSetup;
import entity.CarEntity;
import entity.CarModelEntity;
import entity.DealershipEntity;
import repository.CarModelRepository;
import repository.CarRepository;
import repository.DealershipRepository;

import java.util.List;
import java.util.Optional;

public class Zadanie7 {
    public static void main(String[] args) {

        // Настройка базы данных перед выполнением операций
        DatabaseSetup.setupDatabase();

        // Инициализация репозиториев
        CarModelRepository carModelRepository = new CarModelRepository();
        CarRepository carRepository = new CarRepository();
        DealershipRepository dealershipRepository = new DealershipRepository();

        // Создание и сохранение новой модели автомобиля
        CarModelEntity toyotaModel = new CarModelEntity(1, "Toyota", "Camry", "Japan", "JP");
        carModelRepository.create(toyotaModel);
        System.out.println("Добавлена модель автомобиля: " + toyotaModel.getModel());

        // Создание и сохранение дилерского центра
        DealershipEntity dealership = new DealershipEntity("GRAND_AUTO");
        dealershipRepository.create(dealership);
        System.out.println("Дилерский центр добавлен: " + dealership.getName());

        // Создание и сохранение нового автомобиля
        CarEntity newCar = new CarEntity(1, 1, "GRAND_AUTO", "new", "Sedan", "Red", 20000.00);
        carRepository.create(newCar);
        System.out.println("Добавлен автомобиль: " + newCar.getConfiguration());

        // Получение и отображение всех автомобилей
        List<CarEntity> cars = carRepository.findAll();
        System.out.println("\nСписок всех автомобилей:");
        cars.forEach(System.out::println);

        // Получение и отображение всех моделей автомобилей
        List<CarModelEntity> carModels = carModelRepository.findAll();
        System.out.println("\nСписок всех моделей автомобилей:");
        carModels.forEach(System.out::println);

        // Поиск автомобиля по ID
        int searchCarId = 1;
        System.out.println("\nПоиск автомобиля с ID: " + searchCarId);
        Optional<CarEntity> foundCar = carRepository.findById(searchCarId);
        foundCar.ifPresentOrElse(
                car -> System.out.println("Найденный автомобиль: " + car),
                () -> System.out.println("Автомобиль с ID " + searchCarId + " не найден.")
        );

        // Обновление данных найденного автомобиля
        foundCar.ifPresent(car -> {
            car.setColor("Blue");
            car.setPrice(21000.00);
            carRepository.update(car);
            System.out.println("\nАвтомобиль обновлён: " + car);
        });

        // Удаление автомобиля по ID
        int deleteCarId = 1;
        System.out.println("\nУдаление автомобиля с ID: " + deleteCarId);
        carRepository.delete(deleteCarId);
        Optional<CarEntity> checkDeletedCar = carRepository.findById(deleteCarId);
        if (checkDeletedCar.isEmpty()) {
            System.out.println("Автомобиль с ID " + deleteCarId + " успешно удалён.");
        }

        // Удаление дилерского центра
        String dealershipToDelete = "GRAND_AUTO";
        System.out.println("\nУдаление дилерского центра: " + dealershipToDelete);
        dealershipRepository.delete(dealershipToDelete);
        System.out.println("Дилерский центр " + dealershipToDelete + " удалён.");
    }
}

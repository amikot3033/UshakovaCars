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
            // Настройка базы данных
            DatabaseSetup.setupDatabase();

            // Инициализация репозиториев
            CarModelRepository carModelRepository = new CarModelRepository();
            CarRepository carRepository = new CarRepository();
            DealershipRepository dealershipRepository = new DealershipRepository();

            // Выполнение операций
            addCarModel(carModelRepository);
            addDealership(dealershipRepository);
            addCar(carRepository);

            displayAllCars(carRepository);
            displayAllCarModels(carModelRepository);

            findAndUpdateCar(carRepository, 1);
            deleteCar(carRepository, 1);

            deleteDealership(dealershipRepository, "GRAND_AUTO");
        }

        private static void addCarModel(CarModelRepository carModelRepository) {
            CarModelEntity toyotaModel = new CarModelEntity(1, "Mercedes", "MERCEDES-BENZ GLЕ 400", "Германия", "DE");
            carModelRepository.create(toyotaModel);
            System.out.println("Добавлена модель автомобиля: " + toyotaModel.getModel());
        }

        private static void addDealership(DealershipRepository dealershipRepository) {
            DealershipEntity dealership = new DealershipEntity("GRAND_AUTO");
            dealershipRepository.create(dealership);
            System.out.println("Дилерский центр добавлен: " + dealership.getName());
        }

        private static void addCar(CarRepository carRepository) {
            CarEntity newCar = new CarEntity(1, 1, "GRAND_AUTO", "new", "Sedan", "Red", 20000.00);
            carRepository.create(newCar);
            System.out.println("Добавлен автомобиль: " + newCar.getConfiguration());
        }

        private static void displayAllCars(CarRepository carRepository) {
            List<CarEntity> cars = carRepository.findAll();
            System.out.println("\nСписок всех автомобилей:");
            cars.forEach(car -> System.out.println("ID: " + car.getId() +
                    ", Модель: " + car.getConfiguration() +
                    ", Цвет: " + car.getColor() +
                    ", Цена: " + car.getPrice()));
        }

        private static void displayAllCarModels(CarModelRepository carModelRepository) {
            List<CarModelEntity> carModels = carModelRepository.findAll();
            System.out.println("\nСписок всех моделей автомобилей:");
            carModels.forEach(model -> System.out.println("ID: " + model.getId() +
                    ", Бренд: " + model.getBrand() +
                    ", Модель: " + model.getModel() +
                    ", Страна: " + model.getCountryOrigin()));
        }

        private static void findAndUpdateCar(CarRepository carRepository, int carId) {
            System.out.println("\nПоиск автомобиля с ID: " + carId);
            Optional<CarEntity> foundCar = carRepository.findById(carId);
            foundCar.ifPresentOrElse(
                    car -> {
                        System.out.println("Найденный автомобиль: " + car);
                        car.setColor("Blue");
                        car.setPrice(21000.00);
                        carRepository.update(car);
                        System.out.println("\nАвтомобиль обновлён: " + car);
                    },
                    () -> System.out.println("Автомобиль с ID " + carId + " не найден.")
            );
        }

        private static void deleteCar(CarRepository carRepository, int carId) {
            System.out.println("\nУдаление автомобиля с ID: " + carId);
            carRepository.delete(carId);
            Optional<CarEntity> checkDeletedCar = carRepository.findById(carId);
            if (checkDeletedCar.isEmpty()) {
                System.out.println("Автомобиль с ID " + carId + " успешно удалён.");
            }
        }

        private static void deleteDealership(DealershipRepository dealershipRepository, String dealershipName) {
            System.out.println("\nУдаление дилерского центра: " + dealershipName);
            dealershipRepository.delete(dealershipName);
            System.out.println("Дилерский центр " + dealershipName + " удалён.");
        }
}

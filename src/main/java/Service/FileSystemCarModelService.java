import dto.CarModelDTO;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileSystemCarModelService {

    public void loadCars(String filename) {
        try {
            BufferedReader fp = new BufferedReader(new FileReader(filename));
            String[] columns = fp.readLine().split(";");
            System.out.println("Headers: " + String.join(", ", columns));

            while (fp.ready()) {
                String[] car = fp.readLine().split(";");
                if (car.length >= 4) {
                    CarModelDTO carModel = new CarModelDTO(car[0], car[1], car[2], car[3]);
                    this.cars.add(carModel);
                } else {
                    System.out.println("Invalid row: " + String.join(", ", car));
                }
            }

            fp.close();
        } catch (Exception var6) {
            Exception e = var6;
            e.printStackTrace();
        }
    }
}
import java.util.ArrayList;

public class Race {
    ArrayList<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        if (cars.isEmpty() || cars.get(0).speed > car.speed) {
            cars.add(car);
            return;
        }

        cars.add(0, car);
    }

    public String getWinnerName() {
        return cars.get(0).name;
    }
}

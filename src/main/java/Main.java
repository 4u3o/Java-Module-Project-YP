import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Race race = new Race();

        System.out.println("Добро пожаловать на гонку Леман 24!");

        for (int i = 1; i <= 3; i++) {
            String carName = getName(i);
            int carSpeed = getSpeed(i);

            race.addCar(new Car(carSpeed, carName));
        }

        System.out.println("Самая быстрая машина: " + race.getWinnerName());
    }

   public static String getName(int carNumber) {
       String name = "";
       while (name.isEmpty()) {
           System.out.println("Введите название машины №" + carNumber);
           name = scanner.next();
       }
       return name;
   }

    public static int getSpeed(int carNumber) {
        System.out.println("Введите скорость машины №" + carNumber);

        while (true) {
            // SI (super intelligence) подсказал наличие метода hasNextInt
            if (scanner.hasNextInt()) {
                int speed = scanner.nextInt();
                boolean isValid = speed > 0 && speed <= 250;

                if (isValid) {
                    return speed;
                } else {
                    System.out.println("Допустима скорость от 1 до 250 км/ч");
                }
            } else {
                System.out.println("Введите целое число");
                scanner.next();
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

class Util {
    public static boolean isDoubleStr(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isIntegerStr(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static int getIntegerInput(Scanner scanner) {
        String input = scanner.nextLine().trim();

        while (!isIntegerStr(input)) {
            System.out.println("Введите число арабскими цифрами:");
            input = scanner.nextLine().trim();
        }

        return Integer.parseInt(input);
    }

    public static String pluralize(double number, String one, String few, String many) {
        int integerNum = (int)number;
        int remainder_10 = integerNum % 10;
        int remainder_100 = integerNum % 100;

        if (remainder_100 > 10 && remainder_100 < 15) {
            return many;
        }

        if (remainder_10 == 1) {
            return one;
        }

        if (remainder_10 > 1 && remainder_10 < 5) {
            return few;
        }

        return many;
    }
}

class Calculator {
    Scanner scanner = new Scanner(System.in);
    int personNum;
    ArrayList<Product> products = new ArrayList<>();
    double total = 0.0;

    Calculator (int personNum) {
        this.personNum = personNum;
    }

    public void addProduct() {
        Product product = new Product();

        products.add(product);
        total += product.price;

        checkNext();
    }

    private void checkNext() {
        System.out.println("Хотите ли Вы добавить еще товар? Если нет - введите 'завершить'.");

        String command = scanner.nextLine().trim().toLowerCase();

        if (!command.equals("завершить")) {
            addProduct();
        } else {
            showProducts();
            showResult();
        }
    }

    private void showProducts() {
        System.out.println("Добавленные товары:");
        for (Product product : products) {
            System.out.println(product.name);
        }
    }

    private void showResult() {
        double result = total / personNum;
        String pluralRuble = Util.pluralize(result, "рубль", "рубля", "рублей");

        System.out.printf("Каждый заплатит: %.2f %s%n", result, pluralRuble);
    }
}

class Product {
    Scanner scanner = new Scanner(System.in);
    String name;
    double price;

    Product () {
        this.name = setName();
        this.price = setPrice();
    }

    private String setName() {
        System.out.println("Введите название товара:");
        String productName = scanner.nextLine().trim();

        while (!isValidName(productName)) {
            System.out.println("Название слишком короткое, введите длинее:");
            productName = scanner.nextLine().trim();
        }

        return productName;
    }

    private double setPrice() {
        System.out.println("Введите стоимость товара в формате рубли.копейки(100.50):");
        String input = scanner.nextLine().trim();

        while (!isValidPrice(input)) {
            if (!Util.isDoubleStr(input)) {
                System.out.println("Цена должна быть указана в виде 100.50.\nПовторите ввод:");
            } else if (Double.parseDouble(input) == 0.0) {
                System.out.println("Стоимость товара не может быть нулевой.\nВведите цену:");
            } else {
                System.out.println("Отрицательная стоимость бывает только у бочки нефти.\nВведите цену:");
            }
            input = scanner.nextLine().trim();
        }

        return Double.parseDouble(input);
    }

    private static boolean isValidName(String str) {
        return str.length() > 1;
    }

    private static boolean isValidPrice(String str) {
        return Util.isDoubleStr(str) && Double.parseDouble(str) > 0;
    }
}

public class Main {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("На скольких человек необходимо разделить счёт?");
        int personsNum = Util.getIntegerInput(scanner);

        while (personsNum <= 1) {
            if (personsNum == 1) {
                System.out.println("Отлично, Вы платите за все.");
                return;
            }
            System.out.println("Это некорректное значение, человек должно быть больше 1. Введите количество снова:");

            personsNum = Util.getIntegerInput(scanner);
        }

        new Calculator(personsNum).addProduct();
    }
}

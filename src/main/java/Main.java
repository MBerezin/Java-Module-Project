import java.util.Locale;
import java.util.Scanner;

public class Main {

    static final String END_PRODUCTS_INPUT = "ЗАВЕРШИТЬ";

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner scanner = new Scanner(System.in);

        int peopleCounter = countPeople(scanner);

        Calculator calculator = new Calculator(peopleCounter);

        addProducts(scanner, calculator);

        showDivision(calculator);

    }

    private static int countPeople(Scanner scanner){

        int peopleCounter;

        System.out.println("На скольких человек необходимо разделить счёт?");

        while (true){

            if (scanner.hasNextInt()){
                peopleCounter = scanner.nextInt();
                if (peopleCounter == 1){
                    System.out.println("В этом случае нет смысла ничего считать и делить. Пожалуйста введите корректное значение для подсчёта.");
                } else if(peopleCounter < 1){
                    System.out.println("Количество человек меньше 1. Пожалуйста введите корректное значение для подсчёта.");
                } else {
                    break;
                }
            } else {
                System.out.println("Пожалуйста введите корректное значение для подсчёта.");
                scanner.next();
            }

        }

        return peopleCounter;
    }

    private static void addProducts(Scanner scanner, Calculator calculator){

        double productPrice;
        String productName;
        Product product;

        System.out.println("\nПожалуйста введите название товара.");
        while (true){

            productName = scanner.next();

            if (productName.equalsIgnoreCase(END_PRODUCTS_INPUT)){
                break;
            } else {
                System.out.println("Пожалуйста введите стоимость товара в формате: 'рубли.копейки' [10.45, 11.40].");
                while (true){
                    if (scanner.hasNextDouble()){
                        productPrice = scanner.nextDouble();
                        if (productPrice >= 0){
                            break;
                        } else {
                            System.out.println("Стоимость товара не может быть отрицательной. Пожалуйста введите корректную стоимость товара.");
                        }

                    } else {
                        System.out.println("Пожалуйста введите корректную стоимость товара.");
                        scanner.next();
                    }
                }
            }

            product = new Product(productPrice, productName);
            calculator.addProduct(product);

            System.out.println("Добавить ещё один товар?");

        }
    }

    private static void showDivision(Calculator calculator){
        calculator.showAddedProducts();
        calculator.divide();
    }

}

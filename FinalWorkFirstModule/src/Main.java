import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String option;
        String in;
        String out;
        String key;

        do {
            System.out.println(" Приветсвую тебя конспиролог! \n Выберите режим работы шифрования: \n 1 - зашифровать  \n 2 - расшифровать");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Введите путь и название файла для шифрования:");
                    in = scanner.nextLine();
                    System.out.println("Введите путь и название файла для шифрования текста:");
                    out = scanner.nextLine();
                    System.out.println("Введите ключ шифрования: ");
                    key = scanner.nextLine();

                    cipher(in, out, key);
                    break;
                case "2":
                    System.out.println("Введите путь и название файла для расшифровки:");
                    in = scanner.nextLine();
                    System.out.println("Введите путь и название файла с зашифрованным текстом:");
                    out = scanner.nextLine();
                    System.out.println("Введите ключ шифрования: ");
                    key = scanner.nextLine();

                    encipher(in, out, key);
                    break;
                default:
                    System.out.println("Сделайте правильный выбор!");
            }
        } while (!option.equals("1") || !option.equals("2"));


        System.out.println("Наслаждаемся!");
    }


    public static void cipher(String inputFileName, String outputFileName, String key) {
        try (
                FileInputStream in = new FileInputStream(inputFileName);
                FileOutputStream out = new FileOutputStream(outputFileName);
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            byte[] keyData = key.getBytes();
            int n;
            do {
                n = in.read(buffer);
                if (n <= 0) {
                    break;
                }
                for (int i = 0; i < n; i++) {
                    buffer[i] += keyData[i % keyData.length];
                }
                out.write(buffer, 0, n);
            } while (n > 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void encipher(String inputFileName, String outputFileName, String key) {
        try (
                FileInputStream in = new FileInputStream(inputFileName);
                FileOutputStream out = new FileOutputStream(outputFileName);
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            byte[] keyData = key.getBytes();
            int n;
            do {
                n = in.read(buffer);
                if (n <= 0) {       //проверка что бы не выйти за границы
                    break;
                }
                for (int i = 0; i < n; i++) {
                    buffer[i] -= keyData[i % keyData.length];
                }
                out.write(buffer, 0, n);
            } while (n > 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
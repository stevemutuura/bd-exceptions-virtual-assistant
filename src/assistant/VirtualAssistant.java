package assistant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VirtualAssistant {

    public enum KeywordEnum {
        SUM,
        PRODUCT,
        LARGEST,
        YELL,
        TIME,
        PLAY,
        ORDER
    }

    private String name;

    public VirtualAssistant(String name) {
        this.name = name;
    }

    public void processInput(String input, Scanner scanner) throws AssistantNotImplementedException, AssistantIncorrectNameException, InputMismatchException {
        if (!input.startsWith(name)) {
            throw new AssistantIncorrectNameException();
        }
        String keyword = input.substring(name.length() + 1);
        KeywordEnum key = KeywordEnum.valueOf(keyword.toUpperCase());

        switch (key) {
            case SUM:
                double sum = sum(waitForNextLine("Type the numbers separated by a space to sum:", scanner));
                System.out.println("The sum was " + sum);
                break;
            case PRODUCT:
                double product = product(waitForNextLine("Type the numbers separated by a space to multiply:", scanner));
                System.out.println("The product was " + product);
                break;
            case LARGEST:
                double largest = largest(waitForNextLine("Type each number separated by a space:", scanner));
                System.out.println("The largest value was " + largest);
                break;
            case YELL:
                String phrase = waitForNextLine("What should I yell?", scanner);
                System.out.println(phrase.toUpperCase() + "!");
                break;
            case TIME:
                System.out.println("The time is " + getTime());
                break;
            default:
                throw new AssistantNotImplementedException();
        }
    }

    public double sum(String values) {
        Scanner scanner = new Scanner(values);
        double sum = 0.0;
        while (scanner.hasNext()) {
            sum += scanner.nextDouble();
        }
        return sum;
    }

    public double product(String values) {
        Scanner scanner = new Scanner(values);
        double prod = 1.0;
        while (scanner.hasNext()) {
            prod *= scanner.nextDouble();
        }
        return prod;
    }

    public double largest(String values) {
        Scanner scanner = new Scanner(values);
        double largest = scanner.nextDouble();
        while (scanner.hasNext()) {
            double currentValue = scanner.nextDouble();
            if (currentValue > largest) {
                largest = currentValue;
            }
        }
        return largest;
    }

    public String getTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }

    /**
     * Places the virtual assistant into a run mode, where it is listening for the
     * next typed in text. The assistant will end when 'quit' is entered as an input.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = waitForNextLine("How may I help you? Type 'quit' to end assistance.", scanner);

        while (!input.equalsIgnoreCase("quit")) {
            try {
                processInput(input, scanner);
            } catch (AssistantIncorrectNameException e) {
                System.out.println(e.getMessage());
            } catch (AssistantNotImplementedException e) {
                System.out.println(e.getMessage());
            } catch(IllegalArgumentException e) {
                System.out.println("This keyword is unknown by the assistant.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid value typed in.");
            }

            System.out.println();
            input = waitForNextLine("How may I help you? Type 'quit' to end assistance.", scanner);

        }

    }

    /**
     * Gets the next input from the keyboard.
     * @param scanner the opened Scanner capable of inputting text
     * @return the next line of text the user inputted
     */
    public String waitForNextLine(String prompt, Scanner scanner) {
        System.out.println(prompt);
        return scanner.nextLine();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

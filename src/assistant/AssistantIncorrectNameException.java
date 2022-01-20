package assistant;

public class AssistantIncorrectNameException extends Exception {
    @Override
    public String getMessage() {
        return "The assistant's name was not typed first.";
    }
}

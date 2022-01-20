package assistant;

public class AssistantNotImplementedException extends Exception {
    @Override
    public String getMessage() {
        return "This keyword is still in development.";
    }
}

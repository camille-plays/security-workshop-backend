package wise.wisewomenhackathon.Exceptions;

public class BalanceLimitReachedException extends RuntimeException {
    public BalanceLimitReachedException(String message) {
        super(message);
    }
}

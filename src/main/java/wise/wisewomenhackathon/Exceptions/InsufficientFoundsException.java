package wise.wisewomenhackathon.Exceptions;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InsufficientFoundsException extends RuntimeException {

    public InsufficientFoundsException(String message) {
        super(message);
    }
}

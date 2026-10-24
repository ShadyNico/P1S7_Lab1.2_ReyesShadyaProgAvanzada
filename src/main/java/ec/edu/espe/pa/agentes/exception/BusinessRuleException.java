package ec.edu.espe.pa.agentes.exception;

// 422 Unprocessable Entity
public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}

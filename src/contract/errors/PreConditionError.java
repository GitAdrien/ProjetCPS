package contract.errors;

public class PreConditionError extends Error {
	private static final long serialVersionUID = 7424829264328494178L;

	public PreConditionError(String message) {
		super(message);
	}
	
}

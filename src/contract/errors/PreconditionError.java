package contract.errors;

public class PreconditionError extends Error {
	private static final long serialVersionUID = 7424829264328494178L;

	public PreconditionError(String message) {
		super(message);
	}
	
}

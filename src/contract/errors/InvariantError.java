package contract.errors;

public class InvariantError extends Error {
	private static final long serialVersionUID = -2778647751698756558L;

	public InvariantError(String message) {
		super("Invariant error : " + message);
	}
	
}

package contract.errors;

public class ContractError extends Error {
	private static final long serialVersionUID = 6617782425662361157L;

	public ContractError(String message) {
		super(message);
	}
	
}

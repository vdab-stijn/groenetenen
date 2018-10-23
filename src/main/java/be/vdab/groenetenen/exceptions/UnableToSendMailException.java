package be.vdab.groenetenen.exceptions;

public class UnableToSendMailException extends RuntimeException {

	/** Implements Serializable */
	private static final long serialVersionUID = 80211790160757470L;

	public UnableToSendMailException() { super(); }
	public UnableToSendMailException(final String message) { super(message); }
	public UnableToSendMailException(
			final String message, final Throwable throwable) {
		super(message, throwable);
	}
}

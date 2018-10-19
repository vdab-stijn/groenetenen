package be.vdab.groenetenen.web.forms;

import javax.validation.constraints.NotNull;

import be.vdab.groenetenen.constraints.FromToPostalCode;
import be.vdab.groenetenen.constraints.PostalCode;

@FromToPostalCode
public class FromToPostalCodeForm {

	@NotNull
	@PostalCode
	private Integer from;
	@NotNull
	@PostalCode
	private Integer to;
	
	public void setFrom(final Integer from) {
		this.from = from;
	}
	public Integer getFrom() {
		return from;
	}
	
	public void setTo(final Integer to) {
		this.to = to;
	}
	public Integer getTo() {
		return to;
	}
}

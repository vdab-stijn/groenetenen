package be.vdab.groenetenen.web.forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class FromToPostalCodeForm {

	@NotNull
	@Range(min = 1000, max = 9999)
	private Integer from;
	@NotNull
	@Range(min = 1000, max = 9999)
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

package be.vdab.groenetenen.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

@Embeddable
public class Address implements Serializable {

	/** Implements Serializable */
	private static final long serialVersionUID = 80211790160757470L;

	@NotBlank
	@SafeHtml
	@Column(name = "straat")
	private String street;
	@NotBlank
	@SafeHtml
	@Column(name = "huisNr")
	private String number;
	@NotNull
	@Range(min = 1000, max = 9999)
	@Column(name = "postcode")
	private int postalCode;
	@NotBlank
	@SafeHtml
	@Column(name = "gemeente")
	private String municipality;
	
	protected Address() {}
	public Address(
			final String street,
			final String number,
			final int postalCode,
			final String municipality) {
		this.street = street;
		this.number = number;
		this.postalCode = postalCode;
		this.municipality = municipality;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getNumber() { 
		return number;
	}
	
	public int getPostalCode() {
		return postalCode;
	}
	
	public String getMunicipality() {
		return municipality;
	}
}

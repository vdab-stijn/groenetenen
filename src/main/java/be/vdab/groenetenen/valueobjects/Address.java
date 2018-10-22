package be.vdab.groenetenen.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.hibernate.validator.constraints.SafeHtml;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import be.vdab.groenetenen.constraints.PostalCode;

@Embeddable
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
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
	@PostalCode
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

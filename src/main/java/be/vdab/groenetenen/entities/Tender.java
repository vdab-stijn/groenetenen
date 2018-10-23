package be.vdab.groenetenen.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import be.vdab.groenetenen.adapters.LocalDateAdapter;

@Entity
@Table(name = "offertes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tender implements Serializable {

	/** Implements Serializable */
	private static final long serialVersionUID = 80211790160757470L;

	public interface Step1 {}
	public interface Step2 {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(groups = Step1.class)
	@SafeHtml(groups = Step1.class)
	@Column(name = "familienaam")
	private String firstName;
	@NotBlank(groups = Step1.class)
	@SafeHtml(groups = Step1.class)
	@Column(name = "voornaam")
	private String lastName;
	@NotBlank(groups = Step1.class)
	@SafeHtml(groups = Step1.class)
	@Column(name = "emailAdres")
	private String emailAddress;
	@NotNull(groups = Step2.class)
	@Positive(groups = Step2.class)
	@NumberFormat
	@Column(name = "oppervlakte")
	private Integer area;
	@DateTimeFormat(style = "S-")
	@Column(name = "aangemaakt")
	@XmlJavaTypeAdapter(value= LocalDateAdapter.class)
	private LocalDate dateCreated = LocalDate.now();
	
	public Tender() {}
	
	public long getId() {
		return id;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setArea(final Integer area) {
		this.area = area;
	}
	public Integer getArea() {
		return area;
	}
	
	public void setDateCreated(final LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}
	public LocalDate getDateCreated() {
		return dateCreated;
	}
}

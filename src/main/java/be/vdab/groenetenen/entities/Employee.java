package be.vdab.groenetenen.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "werknemers")
@NamedEntityGraph(name = Employee.WITH_BRANCH,
	attributeNodes = @NamedAttributeNode("branch")
)
public class Employee implements Serializable {

	/** Implements Serializable */
	private static final long serialVersionUID = 80211790160757470L;
	
	// Entity graph
	public static final String WITH_BRANCH = "Employee.withBranch";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@SafeHtml
	@Column(name = "voornaam")
	private String firstName;
	@NotBlank
	@SafeHtml
	@Column(name = "familienaam")
	private String lastName;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "filiaalId")
	@NotNull
	private Branch branch;
	@NotNull
	@PositiveOrZero
	@NumberFormat(style = Style.NUMBER)
	@Digits(integer = 10, fraction = 2)
	@Column(name = "wedde")
	private BigDecimal wages;
	@Column(name = "rijksregisterNr", unique = true)
	private long socialSecurityNumber;
	
	protected Employee() {}
	public Employee(
			final String firstName,
			final String lastName,
			final Branch branch,
			final BigDecimal wages,
			final long socialSecurityNumber) {
		setFirstName(firstName);
		setLastName(lastName);
		setBranch(branch);
		setWages(wages);
		setSocialSecurityNumber(socialSecurityNumber);
	}
	
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
	
	public void setBranch(final Branch branch) {
		this.branch = branch;
	}
	public Branch getBranch() {
		return branch;
	}
	
	public void setWages(final BigDecimal wages) {
		this.wages = wages;
	}
	public BigDecimal getWages() {
		return wages;
	}
	
	public void setSocialSecurityNumber(final long socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public long getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
		
		if (object == null || !(object instanceof Employee)) return false;
		
		final Employee employee = (Employee)object;
		
		return socialSecurityNumber == employee.socialSecurityNumber;
	}
	
	@Override
	public int hashCode() {
		return 37 + (int)(socialSecurityNumber ^ (socialSecurityNumber >>> 32));
	}
	
}

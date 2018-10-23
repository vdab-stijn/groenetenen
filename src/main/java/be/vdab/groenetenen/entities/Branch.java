package be.vdab.groenetenen.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.vdab.groenetenen.adapters.LocalDateAdapter;
import be.vdab.groenetenen.valueobjects.Address;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "filialen")
public class Branch implements Serializable {

	/** Implements Serializable */
	private static final long serialVersionUID = 80211790160757470L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@SafeHtml
	@Column(name = "naam")
	private String name;
	@Column(name = "hoofdFiliaal")
	private boolean headBranch;
	@NumberFormat(style = Style.NUMBER)
	@NotNull
	@PositiveOrZero
	@Digits(integer = 10, fraction = 2)
	@Column(name = "waardeGebouw")
	private BigDecimal buildingValue;
	@DateTimeFormat(style = "S-")
	@NotNull
	@Column(name = "ingebruikname")
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDate dateCommissioning;
	@Valid
	@Embedded
	private Address address;
	@Version
	@Column(name = "versie")
	private long version;
	@OneToMany(mappedBy = "branch")
	@XmlTransient
	@JsonIgnore
	private Set<Employee> employees;
	
	public Branch() {}
	public Branch(
			final String name,
			final boolean headBranch,
			final BigDecimal buildingValue,
			final LocalDate dateCommissioning,
			final Address address) {
		setName(name);
		setHeadBranch(headBranch);
		setBuildingValue(buildingValue);
		setDateCommissioning(dateCommissioning);
		setAddress(address);
	}
	
	public long getId() {
		return id;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setHeadBranch(final boolean headBranch) {
		this.headBranch = headBranch;
	}
	public boolean isHeadBranch() {
		return headBranch;
	}
	
	public void setBuildingValue(final BigDecimal buildingValue) {
		this.buildingValue = buildingValue;
	}
	public BigDecimal getBuildingValue() {
		return buildingValue;
	}
	
	public void setDateCommissioning(final LocalDate dateCommissioning) {
		this.dateCommissioning = dateCommissioning;
	}
	public LocalDate getDateCommissioning() {
		return dateCommissioning;
	}
	
	public void setAddress(final Address address) {
		this.address = address;
	}
	public Address getAddress() {
		return address;
	}
	
	public Set<Employee> getEmployees() {
		return Collections.unmodifiableSet(employees);
	}
	
	public void depreciate() {
		buildingValue = BigDecimal.ZERO;
	}
}

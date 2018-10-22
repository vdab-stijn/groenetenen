package be.vdab.groenetenen.restservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import be.vdab.groenetenen.entities.Branch;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class BranchIdName {

	@XmlAttribute
	private long id;
	@XmlAttribute
	private String name;
	
	public BranchIdName() {}
	public BranchIdName(final Branch branch) {
		this.id = branch.getId();
		this.name = branch.getName();
	}
}

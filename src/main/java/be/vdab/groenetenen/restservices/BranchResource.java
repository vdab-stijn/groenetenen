package be.vdab.groenetenen.restservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import be.vdab.groenetenen.entities.Branch;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class BranchResource extends ResourceSupport {

	@SuppressWarnings("unused")
	private Branch branch;
	
	public BranchResource() {}
	public BranchResource(final Branch branch, final EntityLinks entityLinks) {
		this.branch = branch;
		
		this.add(entityLinks.linkToSingleResource(
				Branch.class, branch.getId()));
		this.add(entityLinks.linkForSingleResource(
				Branch.class, branch.getId())
				.slash("employees").withRel("employees"));
	}
}

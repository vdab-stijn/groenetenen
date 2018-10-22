package be.vdab.groenetenen.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import be.vdab.groenetenen.entities.Branch;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class BranchesResource extends ResourceSupport {

	@XmlElement(name = "branch")
	@JsonProperty("branches")
	private final List<BranchIdName> branchesIdName = new ArrayList<>();
	
	public BranchesResource() {}
	public BranchesResource(
			final Iterable<Branch> branches,
			final EntityLinks entityLinks) {
		for (final Branch branch : branches) {
			this.branchesIdName.add(new BranchIdName(branch));
			
			this.add(entityLinks.linkToSingleResource(
					Branch.class, branch.getId())
					.withRel("detail." + branch.getId()));
		}
		
		this.add(entityLinks.linkToCollectionResource(Branch.class));
	}
}

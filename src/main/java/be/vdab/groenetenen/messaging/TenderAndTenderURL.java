package be.vdab.groenetenen.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import be.vdab.groenetenen.entities.Tender;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TenderAndTenderURL {

	private Tender tender;
	private String tenderURL;
	
	protected TenderAndTenderURL() {}
	public TenderAndTenderURL(final Tender tender, final String tenderURL) {
		this.tender = tender;
		this.tenderURL = tenderURL;
	}
	
	public Tender getTender() {
		return tender;
	}
	
	public String getTenderURL() {
		return tenderURL;
	}
}

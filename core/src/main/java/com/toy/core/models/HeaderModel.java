package com.toy.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = { SlingHttpServletRequest.class },defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ValueMapValue
	private String logoimagepath;
	
	@ValueMapValue
	private String logolink;
	
	@ChildResource
	private List<Resource> headerlinks;
	
	private List<LinkDetail> headerlinkDetails = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		logger.info("Inside POST construct method");
		for(Resource headerItem:headerlinks) {
			ValueMap headerItemVm = headerItem.getValueMap();
			String linkTitle = headerItemVm.get("linktitle", "");
			String linkPath = headerItemVm.get("linkpath", "");
			LinkDetail detail = new LinkDetail();
			detail.setLinkTitle(linkTitle);
			detail.setLinkPath(linkPath);
			headerlinkDetails.add(detail);
		}
		
	}
	
	public String getLogoImagePath() {
		return logoimagepath;
	}
	
	public String getLogoLink() {
		return logolink;
	}
	
	public List<LinkDetail> getHeaderlinks(){
		return headerlinkDetails;
	}
}

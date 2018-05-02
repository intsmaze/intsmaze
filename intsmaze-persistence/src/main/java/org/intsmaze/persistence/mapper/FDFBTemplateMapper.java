package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBTemplate;

public interface FDFBTemplateMapper {

	List selectAllByPage(FDFBTemplate user);

	int getAllTemplatesCount(FDFBTemplate user);

	public FDFBTemplate getTemplateStrByType(String type);
}
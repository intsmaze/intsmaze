package org.intsmaze.persistence.mapper;

import java.util.List;

import org.intsmaze.persistence.pojos.FDFBBimsSysMessage;

public interface FDFBBimsSysMessageMapper {
	List<FDFBBimsSysMessage> selectMessagesByUseridAndStatus(
			String userid, String status, String messagetype);
}
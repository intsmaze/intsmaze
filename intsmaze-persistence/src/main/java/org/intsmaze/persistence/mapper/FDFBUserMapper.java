/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.persistence.mapper;

import java.util.List;
import java.util.Map;
import org.intsmaze.persistence.pojos.FDFBUser;

public interface FDFBUserMapper {
    
    FDFBUser selectByUsernameAndPassword(FDFBUser user);
    
    List selectAllByPage(FDFBUser user);
    
    List selectAll();
    
    int getAllUsersCount(FDFBUser user);
    
    List getUsersByRoleidAndDepid(String roleid, String depid);
    
    int getUserCountByUsername(String username);
    
    List getUserByUsername(String username);

	Map getUserOrgParentInfo(Map date);

	List<Map> getFirstOrgInfo(Map date);

	Map getOrgInfo(Map date);
	
	Map getOrganInfo(String seqno);
	
	List getAllUserWithOrgByPage(FDFBUser user);
	
	int getAllUserWithCount(FDFBUser user);
}
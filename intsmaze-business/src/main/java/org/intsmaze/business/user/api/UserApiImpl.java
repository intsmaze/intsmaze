package org.intsmaze.business.user.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.intsmaze.business.Interfaces.IUserApi;
import org.intsmaze.business.user.service.EmpPostService;
import org.intsmaze.business.user.service.IDepartmentService;
import org.intsmaze.business.user.service.IPermitService;
import org.intsmaze.business.user.service.IRoleService;
import org.intsmaze.business.user.service.IUserProfileService;
import org.intsmaze.business.user.service.IUserRoleService;
import org.intsmaze.business.user.service.IUserService;
import org.intsmaze.business.user.service.OrganizationService;
import org.intsmaze.business.user.service.PostUnitService;
import org.intsmaze.business.user.service.UnitService;
import org.intsmaze.business.user.vo.FDFBDepartmentVo;
import org.intsmaze.business.user.vo.FDFBRoleVo;
import org.intsmaze.business.user.vo.FDFBUserProfileVo;
import org.intsmaze.business.user.vo.FDFBUserRoleVo;
import org.intsmaze.business.user.vo.FDFBUserVo;
import org.intsmaze.business.vo.FDFBPermitVo;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.StringUtil;
import org.intsmaze.core.util.Util;
import org.intsmaze.persistence.pojos.EmpPost;
import org.intsmaze.persistence.pojos.FDFBUser;
import org.intsmaze.persistence.pojos.Organization;
import org.intsmaze.persistence.pojos.PostUnit;
import org.intsmaze.persistence.pojos.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class UserApiImpl implements IUserApi {

	@Autowired
	private IUserService userService;

	@Autowired
	private IPermitService permitService;

	@Autowired
	private IUserProfileService userProfileService;

	@Autowired
	private IDepartmentService depService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	SessionLocaleResolver resolver;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private EmpPostService empPostService;
	
	@Autowired
	private PostUnitService postUnitService;
	
	@Autowired
	private OrganizationService organizationService;
	
	public void saveMqInfo(JSONObject mqInfoJson){
		
		List<Organization> orgList = new ArrayList<Organization>();
		List<FDFBUser> userList = new ArrayList<FDFBUser>();
		List<Unit> unitList = new ArrayList<Unit>();
		List<PostUnit> postUnitList = new ArrayList<PostUnit>();
		List<EmpPost> empPostList = new ArrayList<EmpPost>();
		
		JSONArray orgData = mqInfoJson.getJSONArray("orgData");
		
		for(int i=0; i<orgData.size(); i++){
			
			JSONObject orgDataJson = orgData.getJSONObject(i);
			
			String orgInnerCoding = orgDataJson.getString("org_inner_coding");
			String orgNo = orgDataJson.getString("org_no");
			String orgName = orgDataJson.getString("org_name");
			String parentCompnayId = orgDataJson.getString("parent_compnay_id");
			String pOrgInncoding = orgDataJson.getString("p_org_inncoding");
			String status = orgDataJson.getString("status");
			String newOrgNo = orgDataJson.getString("new_org_no");
			
			Organization org = new Organization();
			
			org.setOrgInnerCoding(orgInnerCoding);
			org.setOrgNo(orgNo);
			org.setOrgName(orgName);
			org.setParentCompnayId(parentCompnayId);
			org.setpOrgInncoding(pOrgInncoding);
			org.setStatus(status);
			org.setNewOrgNo(newOrgNo);
			
			orgList.add(org);
			
		}
		
		organizationService.saveOrgList(orgList);
		
		JSONArray unitData = mqInfoJson.getJSONArray("unitData");
		
		for(int i=0 ; i<unitData.size(); i++){
			
			JSONObject unitDataJson = unitData.getJSONObject(i);
			
			String unitInnerCoding = unitDataJson.getString("unit_inner_coding");
			String orgId = unitDataJson.getString("fk_org_id");
			String status = unitDataJson.getString("status");
			
			Unit unit = new Unit();
			
			unit.setUnitInnerCoding(unitInnerCoding);
			unit.setOrgId(orgId);
			unit.setStatus(status);
			
			unitList.add(unit);
			
		}
		
		unitService.saveUnitList(unitList);
		
		JSONArray postUnitData = mqInfoJson.getJSONArray("postUnitData");
		
		for(int i=0; i<postUnitData.size(); i++){
			
			JSONObject postUnitDataJson = postUnitData.getJSONObject(i);
			
			String postInnerCoding = postUnitDataJson.getString("post_inner_coding");
			String unitInnerCoding = postUnitDataJson.getString("unit_inner_coding");
			String status = postUnitDataJson.getString("status");
			
			PostUnit postUnit = new PostUnit();
			
			postUnit.setPostInnerCoding(postInnerCoding);
			postUnit.setUnitInnerCoding(unitInnerCoding);
			postUnit.setStatus(status);
			
			postUnitList.add(postUnit);	
			
		}
		
		postUnitService.savePostUnitList(postUnitList);
		
		JSONArray empPostData = mqInfoJson.getJSONArray("empPostData");
		
		for(int i=0; i<empPostData.size(); i++){
			
			JSONObject empPostDataJson = empPostData.getJSONObject(i);
			
			String empInnerCoding = empPostDataJson.getString("emp_inner_coding");
			String postInnerCoding = empPostDataJson.getString("post_inner_coding");
			String status = empPostDataJson.getString("status");
			String mainPost = empPostDataJson.getString("main_post");
			
			EmpPost empPost = new EmpPost();
			
			empPost.setEmpInnerCoding(empInnerCoding);
			empPost.setPostInnerCoding(postInnerCoding);
			empPost.setMainPost(mainPost);
			empPost.setStatus(status);
			
			empPostList.add(empPost);
			
		}
		
		empPostService.saveEmpPostList(empPostList);
		
		JSONArray empData = mqInfoJson.getJSONArray("empData");
		
		for(int i=0; i<empData.size(); i++){
			
			JSONObject empDataJson = empData.getJSONObject(i);
			
			String empInnerCoding = empDataJson.getString("emp_inner_coding");
			String name = empDataJson.getString("emp_name");
			String email = empDataJson.getString("email");
			String status = empDataJson.getString("status");
			String partyId = empDataJson.getString("fk_accountid");
			String sex = empDataJson.getString("sex");
			String empNo = empDataJson.getString("emp_no");
			
			FDFBUser user = new FDFBUser();
			
			user.setEmpInnerCoding(empInnerCoding);
			user.setName(name);
			user.setEmail(email);
			user.setStatus(status);
			user.setId(partyId);
			
			//单点的女
			if("0".equals(sex))
				//系统的女
				user.setXb("2");
			//单点的男
			if("1".equals(sex))
				//系统的男
				user.setXb("1");
			
			user.setEmpNo(empNo);
			
			userList.add(user);
			
		}
		
		userService.saveUserList(userList);
		
	}
	
	 
	public Map getUserById(String id){
		
		FDFBUserVo user = new FDFBUserVo();
		
		user.setId(id);
		
		List list = userService.selectAllByPage(user);
		
		user = (FDFBUserVo)list.get(0);
		
		if (user != null) {
			
			Map orgMap = userService.getOrganInfo(user.getSeqno());
			
			String roleIds = "";
			List userRoles = userRoleService.getRolesByUserid(user.getSeqno());

			Map map = new HashMap();
			map.put(Constant.USER_NAME_SESSION, user.getUsername());
			map.put(Constant.USER_XM_SESSION, user.getName());
			map.put(Constant.USER_DEPARTMENT_SESSION, user.getDepartmentid());
			map.put(Constant.USER_ROLE_SESSION, FDFBUserRoleVo.convertRoleListToRoleIds(userRoles));
			map.put(Constant.USER_XB_SESSION, user.getXb());
			map.put(Constant.USER_ID_SESSION, user.getSeqno());
			
			if(orgMap != null){
				map.put(Constant.USER_ORG_INNER_CODING_SESSION, orgMap.get("org_inner_coding"));
				map.put(Constant.USER_ORG_NAME_SESSION, orgMap.get("org_name"));
				map.put(Constant.USER_NEW_ORG_NO_SESSION, orgMap.get("new_org_no"));
			}
			

			FDFBUserProfileVo upv = userProfileService
					.getUserProfileByUserid(user.getSeqno());
			if (upv != null) {
				map.put(Constant.USER_STYLE_SESSION,
						"".equals(StringUtil.isNullString(upv.getUserStyle())) ? Constant.DEFAULT_USER_STYPE
								: StringUtil.isNullString(upv.getUserStyle()));
				map.put(Constant.USER_HOMEPAGE_SESSION,
						"".equals(StringUtil.isNullString(upv.getUserHomepage())) ? Constant.DEFAULT_USER_HOMEPAGE
								: StringUtil.isNullString(upv.getUserHomepage()));
			} else {
				map.put(Constant.USER_HOMEPAGE_SESSION,
						Constant.DEFAULT_USER_HOMEPAGE);
				map.put(Constant.USER_STYLE_SESSION,
						Constant.DEFAULT_USER_STYPE);
			}
			return map;
		}
		
		return null;
		
		
	}
	
	 
	public Map checkUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		FDFBUserVo user = new FDFBUserVo();
		user.setUsername(username);
		user.setPassword(password);
		user = userService.login(user);
		if (user != null) {
			String roleIds = "";
			List userRoles = userRoleService.getRolesByUserid(user.getSeqno());
			Map orgMap = userService.getOrganInfo(user.getSeqno());
			
			Map map = new HashMap();
			map.put(Constant.USER_NAME_SESSION, user.getUsername());
			map.put(Constant.USER_XM_SESSION, user.getName());
			map.put(Constant.USER_DEPARTMENT_SESSION, user.getDepartmentid());
			map.put(Constant.USER_ROLE_SESSION, FDFBUserRoleVo.convertRoleListToRoleIds(userRoles));
			map.put(Constant.USER_XB_SESSION, user.getXb());
			map.put(Constant.USER_ID_SESSION, user.getSeqno());
			
			if(orgMap != null){
				map.put(Constant.USER_ORG_INNER_CODING_SESSION, orgMap.get("org_inner_coding"));
				map.put(Constant.USER_ORG_NAME_SESSION, orgMap.get("org_name"));
				map.put(Constant.USER_NEW_ORG_NO_SESSION, orgMap.get("new_org_no"));
			}

			FDFBUserProfileVo upv = userProfileService
					.getUserProfileByUserid(user.getSeqno());
			if (upv != null) {
				map.put(Constant.USER_STYLE_SESSION,
						"".equals(StringUtil.isNullString(upv.getUserStyle())) ? Constant.DEFAULT_USER_STYPE
								: StringUtil.isNullString(upv.getUserStyle()));
				map.put(Constant.USER_HOMEPAGE_SESSION,
						"".equals(StringUtil.isNullString(upv.getUserHomepage())) ? Constant.DEFAULT_USER_HOMEPAGE
								: StringUtil.isNullString(upv.getUserHomepage()));
			} else {
				map.put(Constant.USER_HOMEPAGE_SESSION,
						Constant.DEFAULT_USER_HOMEPAGE);
				map.put(Constant.USER_STYLE_SESSION,
						Constant.DEFAULT_USER_STYPE);
			}
			return map;
		}

		return null;
	}

	 
	public String getPermitByPermitTypeAndRole(String permitType,
			String roleId, String orderByColumn, String displayType,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		StringBuffer menuSb = new StringBuffer("");
		List<FDFBPermitVo> rootMenu = null;
		if (Constant.INIT_TYPE_ALL.equals(displayType)) {
			rootMenu = permitService.getRootPermit(permitType, orderByColumn,
					null);
		} else {
			rootMenu = permitService.getRootPermit(permitType, orderByColumn,
					roleId);
		}
		if (rootMenu == null || rootMenu.size() < 1) {
			return "";
		}
		for (int i = 0, n = rootMenu.size(); i < n; i++) {
			FDFBPermitVo pv = rootMenu.get(i);
			if (Constant.YES.equals(pv.getIsleaf())) {
				if (Constant.INIT_TYPE_ALL.equals(displayType)) {
					menuSb.append(
							"<li><input id='" + pv.getSeqno()
									+ "' type='checkbox' value='"
									+ pv.getSeqno() + "' />&nbsp;<span>")
							// <i class='glyphicon-leaf'></i>
							.append(Util.getLocaleName(pv.getPermitname(),
									request, resolver))
							.append("</span><a href='#'/></li>");
				} else {
					menuSb.append(
							"<li id='" + pv.getSeqno()
									+ "'><a onclick='changeCurrentMenuId(\""
									+ pv.getSeqno() + "\")' href='")
							.append(request.getContextPath() + "/"
									+ pv.getPermitresource())
							.append("'><i class='fa fa-fw fa-dashboard '></i> ")
							.append(Util.getLocaleName(pv.getPermitname(),
									request, resolver)).append("</a></li>");
				}

			} else {
				int permitLevel = 2;
				if (Constant.INIT_TYPE_ALL.equals(displayType)) {
					menuSb.append(
							"<li><input id='" + pv.getSeqno()
									+ "' type='checkbox' value='"
									+ pv.getSeqno() + "' />&nbsp;<span> ")
							// <i class='glyphicon-minus-sign'></i>
							.append(Util.getLocaleName(pv.getPermitname(),
									request, resolver))
							.append("</span><a href='#'/><ul>");
				} else {
					menuSb.append(
							"<li id='"
									+ pv.getSeqno()
									+ "'><a href='#'><i class='fa fa-fw fa-sitemap '></i> ")
							.append(Util.getLocaleName(pv.getPermitname(),
									request, resolver))
							.append("<span class='fa arrow'></span></a><ul class='nav nav-second-level'>");
				}

				menuSb.append(initPermit(pv, permitLevel, "", request, roleId,
						displayType));
				menuSb.append("</ul></li>");
			}
		}
		return menuSb.toString();
	}

	// nav-third-level
	private String initPermit(FDFBPermitVo pv, int permitLevel, String initStr,
			HttpServletRequest request, String roleId, String displayType) {
		StringBuffer menuSb = new StringBuffer(initStr);
		List<FDFBPermitVo> nextLevelPv = null;

		if (Constant.INIT_TYPE_ALL.equals(displayType)) {
			nextLevelPv = permitService.getPermitByParentSeqno(pv.getSeqno(),
					Constant.PERMITTYPE_MENU, "permitorder", null);
		} else {
			nextLevelPv = permitService.getPermitByParentSeqno(pv.getSeqno(),
					Constant.PERMITTYPE_MENU, "permitorder", roleId);
		}
		if (nextLevelPv == null || nextLevelPv.size() < 1) {
			return "";
		}
		for (int i = 0, n = nextLevelPv.size(); i < n; i++) {
			FDFBPermitVo pvTmp = nextLevelPv.get(i);
			if (Constant.YES.equals(pvTmp.getIsleaf())) {
				if (Constant.INIT_TYPE_ALL.equals(displayType)) {
					menuSb.append(
							"<li><input id='" + pvTmp.getSeqno()
									+ "' type='checkbox' value='"
									+ pvTmp.getSeqno() + "' />&nbsp;<span>")
							// <i class='glyphicon-leaf'></i>
							.append(Util.getLocaleName(pvTmp.getPermitname(),
									request, resolver))
							.append("</span><a href='#'/></li>");
				} else {
					menuSb.append(
							"<li id='" + pvTmp.getSeqno()
									+ "'><a onclick='changeCurrentMenuId(\""
									+ pvTmp.getSeqno() + "\")' href='")
							.append(request.getContextPath() + "/"
									+ pvTmp.getPermitresource())
							.append("'>")
							.append(Util.getLocaleName(pvTmp.getPermitname(),
									request, resolver)).append("</a></li>");
				}

			} else {
				permitLevel++;
				if (Constant.INIT_TYPE_ALL.equals(displayType)) {
					menuSb.append(
							"<li><input id='" + pvTmp.getSeqno()
									+ "' type='checkbox' value='"
									+ pvTmp.getSeqno() + "' />&nbsp;<span>")
							// <i class='glyphicon-minus-sign'></i>
							.append(Util.getLocaleName(pvTmp.getPermitname(),
									request, resolver))
							.append("</span><a href='#'/><ul>");
				} else {
					menuSb.append(
							"<li id='" + pvTmp.getSeqno() + "'><a href='#'>")
							.append(Util.getLocaleName(pvTmp.getPermitname(),
									request, resolver))
							.append("<span class='fa arrow'></span></a><ul class='nav ")
							.append(getLevelStr(permitLevel)).append("'>");
				}

				menuSb.append(initPermit(pvTmp, permitLevel, "", request,
						roleId, displayType));
				menuSb.append("</ul></li>");
				permitLevel--;
			}
		}

		return menuSb.toString();
	}

	/**
	 * 将对应数字转义成菜单的class
	 * 
	 * @param permitLevel
	 * @return
	 */
	private String getLevelStr(int permitLevel) {
		if (permitLevel == 2) {
			return "nav-second-level";
		} else if (permitLevel == 3) {
			return "nav-third-level";
		} else if (permitLevel == 4) {
			return "nav-fourth-level";
		} else {
			return "";
		}
	}

	 
	public Map getDepMap() {
		// TODO Auto-generated method stub
		List list = depService.selectAll();
		if (list != null) {
			Map map = new HashMap(list.size());
			for (int i = 0, n = list.size(); i < n; i++) {
				FDFBDepartmentVo dmv = (FDFBDepartmentVo) list.get(i);
				map.put(dmv.getSeqno(), dmv.getDepname());
			}
			return map;
		}
		return new HashMap(0);
	}

	 
	public Map getRoleMap() {
		// TODO Auto-generated method stub
		List list = roleService.selectAll();
		if (list != null) {
			Map map = new HashMap(list.size());
			for (int i = 0, n = list.size(); i < n; i++) {
				FDFBRoleVo dmv = (FDFBRoleVo) list.get(i);
				map.put(dmv.getSeqno(), dmv.getRolename());
			}
			return map;
		}
		return new HashMap(0);
	}

	 
	public String getRoleNameByRoleids(String roleid) {
		// TODO Auto-generated method stub
		if(roleid==null || "".equals(roleid))
		{
			return "";
		}
		List list = roleService.selectRolesByRoleIds(roleid);
		if (list == null) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer("");
			for (int i = 0, n = list.size(); i < n; i++) {
				FDFBRoleVo roleVo = (FDFBRoleVo) list.get(i);
				sb.append(roleVo.getRolename()).append(",");
			}
			String result = sb.toString();
			if(result.endsWith(","))
			{
				result = result.substring(0, result.length() - 1);
			}
			return result;
		}
	}

	 
	public String getDepNameByDepid(String depid) {
		// TODO Auto-generated method stub
		FDFBDepartmentVo depVo = depService.selectByPrimaryKey(StringUtil.isNullString(depid));
		if (depVo == null || "".equals(StringUtil.isNullString(depVo.getSeqno()))) {
			return "";
		} else {
			return StringUtil.isNullString(depVo.getDepname());
		}
	}

	 
	public String getRoleNameByUserid(String userid) {
		// TODO Auto-generated method stub
		List list = userRoleService.getRolesByUserid(userid);
		String roleIds = FDFBUserRoleVo.convertRoleListToRoleIds(list);
		return getRoleNameByRoleids(roleIds);
	}

	 
	public Map getUserMapByRoleidAndDepid(String roleid, String depid) {
		// TODO Auto-generated method stub
		List list = userService.getUsersByRoleidAndDepid(roleid, depid);
		if (list != null) {
			Map map = new HashMap(list.size());
			for (int i = 0, n = list.size(); i < n; i++) {
				FDFBUserVo dmv = (FDFBUserVo) list.get(i);
				map.put(dmv.getSeqno(), dmv.getName());
			}
			return map;
		}
		return new HashMap(0);
	}

	 
	public boolean hasRole(String userid, String roleid) {
		// TODO Auto-generated method stub
		if("".equals(StringUtil.isNullString(userid)) || "".equals(StringUtil.isNullString(roleid)))
		{
			return false;
		}
		else
		{
			int count = userRoleService.getCountByUseridAndRoleid(userid, roleid);
			if(count > 0)
			{
				return true;
			}
			else
				return false;
		}
	}

	 
	public String getUsernameBySeqno(String seqno) {
		// TODO Auto-generated method stub
		FDFBUserVo user = userService.selectByPrimaryKey(seqno);
		if(user==null)
		{
			return "";
		}
		else
		{
			return StringUtil.isNullString(user.getUsername());
		}
	}

	 
	public boolean ChangePassword(String userid, String newpassword) {
		// TODO Auto-generated method stub
		FDFBUserVo user = userService.selectByPrimaryKey(userid);
		user.setPassword(newpassword);
		int count = userService.updateUser(user);
		if(count > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	 
	public String getUserXmBySeqno(String seqno) {
		// TODO Auto-generated method stub
		FDFBUserVo user = userService.selectByPrimaryKey(seqno);
		if(user==null || "".equals(StringUtil.isNullString(user.getSeqno())))
		{
			return "";
		}
		else
		{
			return StringUtil.isNullString(user.getName());
		}
	}

	 
	public String getUserDepBySeqno(String seqno) {
		// TODO Auto-generated method stub
		FDFBUserVo user = userService.selectByPrimaryKey(seqno);
		if(user==null || "".equals(StringUtil.isNullString(user.getSeqno())))
		{
			return "";
		}
		else
		{
			return StringUtil.isNullString(user.getDepartmentid());
		}
	}

	 
	public Map getAllMaintainerAndSgry() {
		List list = userService.getUsersByRoleidAndDepid("1bf5dd31-adda-4766-b7f0-38d1a59e7b8c", "807edee7-bac5-4a2c-bf99-bf359c6eddbe");
		list.addAll(userService.getUsersByRoleidAndDepid("1bf5dd31-adda-4766-b7f0-38d1a59e7b8c", "aa9bf103-a432-4189-bd57-cf1462ed0118"));
		list.addAll(userService.getUsersByRoleidAndDepid("c8a93d8d-ff4d-4eb0-839f-fcfe7d2e7fe0", "15d08b9c-a330-4166-accc-3764ccf5082c"));
		if (list != null) {
			Map map = new HashMap(list.size());
			for (int i = 0, n = list.size(); i < n; i++) {
				FDFBUserVo dmv = (FDFBUserVo) list.get(i);
				map.put(dmv.getSeqno(), dmv.getName());
			}
			return map;
		}
		return new HashMap(0);
	}

	 
	public String getUserXmByUserid(String userid) {
		// TODO Auto-generated method stub
		List list = userService.getUserByUsername(userid);
		if(list == null || list.size() < 1)
		{
			return "";
		}
		else
		{
			FDFBUserVo user = (FDFBUserVo)list.get(0);
			return user.getName();
		}
	}

}

package com.wechat.manage.service.system.intf;

import com.wechat.manage.pojo.system.entity.OrganizationInfo;
import com.wechat.manage.pojo.system.vo.ReturnDto;
import com.wechat.manage.vo.DataTableResult;
import java.util.List;
import java.util.Map;

/**
 * Created by XS on 2016/12/28.
 */
public interface GroupInfoService {

      //接口提供集团列表
      List<OrganizationInfo> getOrganizationInfos(Map<String, Object> paramMap);


      public DataTableResult findByPage(Map<String, Object> paramMap) throws Exception;


      //判断是否存在
      boolean isExist(String name, String pad);

      //保存集团信息
      ReturnDto save(String organizationName, String organizationCode, String chkObjs, String userName);

      boolean modify(OrganizationInfo organizationInfo);


}

package com.avalon.ms.dao.service;

import java.util.List;

import com.avalon.ms.dao.entity.MsRole;

public interface MsRoleService {

   int insert(MsRole record);

   List<MsRole> selectAll();
}

package com.example.mitomi.foreground.passport.mapper;


import com.example.mitomi.foreground.passport.pojo.entity.AdminRole;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleMapper {

    /**
     * 插入管理员与角色关联数据
     * @param adminRole
     * @return
     */
    int adminInsertRole(AdminRole adminRole);
}

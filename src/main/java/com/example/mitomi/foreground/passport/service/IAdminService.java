package com.example.mitomi.foreground.passport.service;


import com.example.mitomi.foreground.passport.pojo.dto.AdminInsertDTO;
import com.example.mitomi.foreground.passport.pojo.dto.AdminLoginDTO;
import com.example.mitomi.foreground.passport.pojo.vo.AdminListItemVO;

import java.util.List;

/**
 * 管理员业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public interface IAdminService {

    /**
     * 添加管理员
     *
     * @param adminAddNewDTO 管理员数据
     */
    void adminInsertDTO(AdminInsertDTO adminAddNewDTO);

    /**
     * 处理登录业务
     * @param adminLoginDTO 管理员登录信息
     */
    String adminLogin(AdminLoginDTO adminLoginDTO);

    void adminDeleteById(Long id);

    /**
     * 查询管理员列表
     *
     * @return 管理员列表的集合
     */
    List<AdminListItemVO>  adminList();

    void adminUpdateById(Long id, String nickname);


}

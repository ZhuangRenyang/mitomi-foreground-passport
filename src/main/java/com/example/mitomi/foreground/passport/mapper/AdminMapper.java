package com.example.mitomi.foreground.passport.mapper;


import com.example.mitomi.foreground.passport.pojo.entity.Admin;
import com.example.mitomi.foreground.passport.pojo.vo.AdminListItemVO;
import com.example.mitomi.foreground.passport.pojo.vo.AdminLoginVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface AdminMapper {

    //添加
    /**
     * 插入用户数据
     *
     * @param admin 用户数据
     * @return 受影响的行数，成功插入数据，返回值为1
     */
    int adminInsert(Admin admin);


    //删除
    /**
     * 根据用户id删除用户数据
     *
     * @param id 期望删除的用户数据的id
     * @return 受影响的行数，当删除成功时，返回1，如果无此id对应的数据，则返回0
     */
    int adminDeleteById(Long id);


    //修改
    int adminUpdateAdmin(Admin admin);

    //查询
    /**
     * 根据管理员用户名统计此用户名对应的管理员数据的数量
     *
     * @param username 管理员用户名
     * @return 此名称对应的管理员数据的数量
     */
    int adminCountByUsername(String username);//用户唯一

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<AdminListItemVO> adminList();

    /**
     * 根据id查询用户详情
     *
     * @param id 用户id
     * @return 用户详情
     */
    AdminListItemVO adminGetById(Long id);

    /**
     * 根据用户名查询查询管理员的登录信息
     * @param username 用户名
     * @return 管理员登录信息
     */
    AdminLoginVo adminGetByUsername(String username);

}

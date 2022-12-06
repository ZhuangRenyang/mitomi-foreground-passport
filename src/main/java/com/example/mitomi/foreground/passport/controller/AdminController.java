package com.example.mitomi.foreground.passport.controller;

import com.example.mitomi.foreground.passport.pojo.dto.AdminInsertDTO;
import com.example.mitomi.foreground.passport.pojo.dto.AdminLoginDTO;
import com.example.mitomi.foreground.passport.pojo.vo.AdminListItemVO;
import com.example.mitomi.foreground.passport.security.LoginPrincipal;
import com.example.mitomi.foreground.passport.service.IAdminService;
import com.example.mitomi.foreground.passport.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 管理员控制器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@RestController
@Api(tags = "用户管理")
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    public AdminController() {
        log.info("AdminController:用户控制器执行了");
    }

    // http://localhost:9081/admins/add-new
    @ApiOperation("添加管理员")
    @ApiOperationSupport(order = 10)
    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult addNew(@RequestBody @Valid AdminInsertDTO adminAddNewDTO) {
        log.info("接收到要添加的管理员账号,{}", adminAddNewDTO);
        //需要调用业务层的方法
        adminService.adminInsertDTO(adminAddNewDTO);
        return JsonResult.ok();
    }


    @ApiOperation("删除用户")
    @ApiOperationSupport(order = 30)
    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/ams/admin/delete')")
    public JsonResult delete(@PathVariable("id") Long id) {
        log.info("接收到删除用户的请求,参数id:{}", id);
        adminService.adminDeleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("修改昵称")
    @ApiOperationSupport(order = 40)
    @PostMapping("/{id:[0-9]+}/update/{nickname}")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult update(@PathVariable("id") Long id, @PathVariable("nickname") String nickname) {
        log.info("接收到修改用户的请求,参数id:{},参数名称:{}", id, nickname);
        adminService.adminUpdateById(id, nickname);
        return JsonResult.ok();
    }


    // http://localhost:9081/admins
    @ApiOperation("用户列表")
    @ApiOperationSupport(order = 20)
    @GetMapping("")
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    public JsonResult list(@AuthenticationPrincipal LoginPrincipal principal) {
        log.debug("接收到查询管理员列表的请求");

        Long id = principal.getId();
        String username = principal.getUsername();
        log.debug("认证信息中:id:{},用户名:{}",id,username);
        List<AdminListItemVO> admins = adminService.adminList();
        return JsonResult.ok(admins);
    }

    // http://localhost:9081/admins
    @ApiOperation("管理员登录")
    @ApiOperationSupport(order = 50)
    @PostMapping("/login")
    public JsonResult login(@RequestBody AdminLoginDTO adminLoginDTO) {
// 请求 ==> Controller ==> Service ==> SecurityConfiguration类方法{AuthenticationManager()} ==> UserDetailsServiceImpl ==> Mapper
        log.debug("接收到用户登录请求:{}", adminLoginDTO);
        //需要调用业务层的方法
        String jwt = adminService.adminLogin(adminLoginDTO);
        return JsonResult.ok(jwt);
    }
}

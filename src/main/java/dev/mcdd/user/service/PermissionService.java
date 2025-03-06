package dev.mcdd.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.mcdd.user.entity.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
	List<Permission> loadPermissionsByRoleId(Long roleId);
}

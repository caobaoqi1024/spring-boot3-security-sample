package dev.mcdd.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.mcdd.user.entity.Role;

public interface RoleService extends IService<Role> {
	Role loadRoleByUserId(Long userId);
}

package dev.mcdd.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.mcdd.user.entity.Permission;
import dev.mcdd.user.entity.Role;
import dev.mcdd.exception.RoleNotFoundException;
import dev.mcdd.user.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private final RoleMapper roleMapper;
	private final PermissionService permissionService;

	@Override
	public Role loadRoleByUserId(Long userId) {
		Role role = roleMapper.findRoleByUserId(userId);
		if (role == null) {
			throw new RoleNotFoundException(String.format("Role not found for user %s", userId));
		}
		Long roleId = role.getId();
		List<Permission> permissions = permissionService.loadPermissionsByRoleId(roleId);
		return Role.builder()
			.id(roleId)
			.name(role.getName())
			.code(role.getCode())
			.permissions(permissions)
			.build();
	}
}

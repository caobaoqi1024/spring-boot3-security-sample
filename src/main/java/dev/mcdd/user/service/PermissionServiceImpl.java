package dev.mcdd.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.mcdd.exception.PermissionNotFoundException;
import dev.mcdd.user.entity.Permission;
import dev.mcdd.user.mapper.PermissionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	private final PermissionMapper permissionMapper;

	@Override
	public List<Permission> loadPermissionsByRoleId(Long roleId) {
		List<Permission> permissions = permissionMapper.findPermissionsByRoleId(roleId);
		if (permissions.isEmpty()) {
			throw new PermissionNotFoundException(String.format("Permission not found for role %s", roleId));
		}
		return permissions;
	}
}

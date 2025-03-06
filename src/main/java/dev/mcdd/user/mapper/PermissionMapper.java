package dev.mcdd.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.mcdd.user.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
	@Select("SELECT * FROM permission WHERE id IN (SELECT permission_id FROM role_permission_map WHERE role_id=${roleId})")
	List<Permission> findPermissionsByRoleId(Long roleId);
}

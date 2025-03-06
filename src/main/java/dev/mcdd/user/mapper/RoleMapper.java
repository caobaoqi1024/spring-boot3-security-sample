package dev.mcdd.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.mcdd.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
	@Select("SELECT * FROM role WHERE id IN (SELECT role_id FROM user_role_map WHERE user_id=#{userId})")
	Role findRoleByUserId(Long userId);
}

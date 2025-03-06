package dev.mcdd.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.mcdd.user.entity.Role;
import dev.mcdd.user.entity.User;
import dev.mcdd.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private final RoleService roleService;

	@Override
	public User findUserByUserId(Long userId) {
		User user = this.isUserExistWithUserId(userId);
		Role role = roleService.loadRoleByUserId(userId);
		user.setRole(role);
		return user;
	}

	private User isUserExistWithUserId(Long userId){
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(User::getId, userId);
		return this.getOneOpt(wrapper).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id %s", userId)));
	}


}

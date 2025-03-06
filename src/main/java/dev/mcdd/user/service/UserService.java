package dev.mcdd.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.mcdd.user.entity.User;

public interface UserService extends IService<User> {
	User findUserByUserId(Long userId);
}

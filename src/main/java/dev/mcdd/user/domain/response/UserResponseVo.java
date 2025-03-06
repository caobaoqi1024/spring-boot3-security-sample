package dev.mcdd.user.domain.response;

import dev.mcdd.user.entity.Role;

import java.time.LocalDateTime;

public record UserResponseVo (
	String username,
	String email,
	Role role,
	LocalDateTime createTime,
	LocalDateTime updateTime
){
}

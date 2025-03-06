package dev.mcdd.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.mcdd.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}

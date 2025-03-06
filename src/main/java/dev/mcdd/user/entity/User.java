package dev.mcdd.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import dev.mcdd.base.BaseEntity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("`user`")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

	@TableId(type = IdType.AUTO)
	private Long id;
	private String username;
	private String email;
	private String password;

	@TableField(exist = false)
	private Role role;

}

package dev.mcdd.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@TableName("`role`")
@AllArgsConstructor
public class Role implements Serializable {

	private Long id;
	private String code;
	private String name;

	@TableField(exist = false)
	private List<Permission> permissions;

	public Role(Long id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}
}

package com.jiangy.xiaohashu.auth.model.vo.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @Description 修改用户密码
 * @Author jiangy
 * @Date 2026/2/3 12:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePasswordReqVO {
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}

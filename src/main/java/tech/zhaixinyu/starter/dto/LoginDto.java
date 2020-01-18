package tech.zhaixinyu.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Project: backend-restful-starter
 * Package: tech.zhaixinyu.starter.dto
 * ClassName: LoginDto
 * Description: Description
 * Created by @author Xinyu on 1/18/20 20:25
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull(message = "username not null")
    @NotBlank(message = "username not blank")
    private String username;
    @NotNull(message = "password not null")
    @NotBlank(message = "password not blank")
    private String password;
}

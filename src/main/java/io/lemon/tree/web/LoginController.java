package io.lemon.tree.web;

import io.lemon.tree.common.LemonTreeResponse;
import io.lemon.tree.validate.Login;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public LemonTreeResponse login(@RequestBody @Valid Login login) {
        return LemonTreeResponse.success();
    }

}

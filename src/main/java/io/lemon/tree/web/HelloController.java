package io.lemon.tree.web;

import io.lemon.tree.common.LemonTreeResponse;
import io.lemon.tree.service.LemonTreeService;
import io.lemon.tree.validate.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Api(value = "/home", tags = "首页欢迎接口")
@RestController
public class HelloController {

    @Autowired
    private LemonTreeService lemonTreeService;

    @ApiOperation(value = "welcome")
    @GetMapping("/welcome")
    public String hello() throws IOException {
        lemonTreeService.getHelloMessage();
        return "Hello Lemon Tree Project";
    }

    @ApiOperation(value = "hello")
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @ApiOperation(value = "greeting")
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @ApiOperation(value = "ask")
    @PostMapping("/ask")
    public String ask(@RequestBody @Validated User user) {
        return String.format("Hello %s!", user.getName());
    }

    @ApiOperation(value = "bye")
    @GetMapping("/bye/{name}/{email}")
    public String bye(@PathVariable("name") String name, @PathVariable("email") String email) {
        return "Bye " + name + " " + email;
    }

    @ApiOperation(value = "common")
    @PostMapping("/common")
    public LemonTreeResponse common(@Valid @RequestBody User user) {
        return LemonTreeResponse.success();
    }

    @ApiOperation(value = "uploadPicture")
    @PostMapping("/uploadPicture")
    public LemonTreeResponse uploadPicture(@RequestPart("file") MultipartFile file) {
        return LemonTreeResponse.success();
    }

    @ApiOperation(value = "uploadWaterMarkPicture")
    @PostMapping(value = "/uploadWaterMarkPicture")
    public LemonTreeResponse pressText(@RequestPart(value = "file", required = true) MultipartFile file,
                                       @RequestParam(value = "name", required = true) String name) {
        return LemonTreeResponse.success();
    }

}

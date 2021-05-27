package io.lemon.tree.validate;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Login {

    @NotBlank(message = "账户不能为空")
    private String account;

    @NotBlank(message = "随机字符串")
    private String nonceStr;

    @NotBlank(message = "当前的时间戳")
    private String timestamp;

    /**
     * 待签名的报文为Collection.sort(appId、appSecret、nonceStr、timestamp)
     */
    @NotBlank(message = "签名不能为空")
    private String signature;

}

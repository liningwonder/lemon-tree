## Lemon Tree Project

As you know, lemon tree is a lovely song.


Any Question, please Contact liningwonder@163.com


### http://springfox.github.io/springfox/

/swagger-ui/index.html


### Lemon Tree Identity and Access Management


https://metrics.dropwizard.io/



FAPI金融级别API

使用AppId和AppPwd（AppSecret）获取frontToken、backendToken

报文中包含appId、随机字符串nonceStr、时间戳timestamp
待签名的报文为sort(appId、appSecret、nonceStr、timestamp) 生成backendToken (过期时间为2小时)

使用授权码appId、authCode、backendToken、grantType换取 accessToken 和openId

使用appId、backendToken、openId、accessToken获取用户信息


Access Key ID - AppID
Secret Access Key - AppSecret






公有云API的认证方式

Token认证
AK/SK认证

HMAC预先生成一个 access key（AK） 和 secure key（SK），然后通过签名的方式完成认证请求，这种方式可以避免传输 secure key，且大多数情况下签名只允许使用一次，避免了重放攻击。

判断用户请求中是否包含Authorization认证字符串。如果包含认证字符串，则执行下一步操作。
基于HTTP请求信息，使用相同的算法，生成Signature字符串。


（1）系统应该支持多种认证方式
（2）系统应该支持多种签名算法（金融领域）
（3）


账户

用户

用户组




身份认证、权限分配、访问控制

AK 和 SK 时一组对称

AK Access Key ID 访问密钥ID与私有访问密钥关联的唯一标识符，访问密钥ID和私有访问密钥一起使用，对请求进行加密签名
SK Secret Access Key 与访问密钥ID结合使用的密钥，对请求进行加密签名，可标识发送方，并防止请求被修改。

客户端：
　　　　1. 构建http请求（包含 access key）；
　　　　2. 使用请求内容和 使用secret access key计算的签名(signature)；
　　　　3. 发送请求到服务端。

服务端：
　　　　1. 根据发送的access key 查找数据库得到对应的secret-key；
　　　　2. 使用同样的算法将请求内容和 secret-key一起计算签名（signature），与客户端步骤2相同；
　　　　3. 对比用户发送的签名和服务端计算的签名，两者相同则认证通过，否则失败。



AppId           - AppSecret
    |                  |
AccessKeyId(AK) - SecretAccessKey(SK)


(1)客户端IP限制, 单个，列表，掩码，段












生成签名的方式和APP认证相同，用AK代替APP认证中的AppKey，SK替换APP认证中的AppSecret，即可完成签名和请求

APP认证（推荐）
对于APP认证的API，您必须提供有效的AppKey、AppSecret才能够生成认证签名。


APP认证（推荐）
支持简易认证和非简易认证两种。
AppId  AppSecret
非简易认证：通过集成应用的Key和Secret认证调用请求。
简易认证：通过AppCode认证调用请求。
APP认证支持对API进行IP地址方式的访问权限控制。

IAM认证
支持Token认证和AK/SK认证两种。
Token认证：通过Token认证调用请求。Token认证无需使用SDK签名，优先使用Token认证。
AK/SK认证：通过AK（Access Key ID）/SK（Secret Access Key）认证调用请求。其签名方式和APP认证相似。
IAM认证支持对API进行IP地址方式和帐号名方式的访问权限控制。



使用Token认证方式完成认证鉴权时，用户首先需要获取token，在调用接口时增加“X-XXX-Token”到业务接口请求消息头中。
流程

    发送请求，获取IAM的Endpoint及消息体中的区域名称。
    获取Token。请求响应成功后在响应消息头中包含的“X-YYY-Token”的值即为Token值。
    调用业务接口，在请求消息头中增加“X-XXX-Token”，取值为2中获取的Token。
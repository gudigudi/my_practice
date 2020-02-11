#language: en
@smoke
@e2e
Scenario Outline: 使用手机号和密码登陆
Given 用户进入登陆页
When 用户输入手机号 <phone>
And 用户输入 <password>
And 用户点击登陆按钮
Then 用户能看到提示 <toast>

Examples: 合法账户信息
| phone       | password | toast |
| 11111111111 | 123456   | 登陆成功  |

Examples: 非法账户信息
| phone        | password | toast       |
| 111111       | 123456   | 手机号有误，请重新确认 |
| 121212112121 | 123456   | 手机号有误，请重新确认 |


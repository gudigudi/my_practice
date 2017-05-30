### user management ###
1. add new user 
```
useradd <option> username 
```
**option**:
* -c comment -c comment 指定一段注释性描述。
* -d 目录 指定用户主目录，如果此目录不存在，则同时使用-m选项，可以创建主目录。
* -g 用户组 指定用户所属的用户组。
* -G 用户组，用户组 指定用户所属的附加组。
* -s Shell文件 指定用户的登录Shell。
* -u 用户号 指定用户的用户号，如果同时有-o选项，则可以重复使用其他用户的标识号。



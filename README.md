config-demo提供了对于配置迁移规范的参考实现，对于保留在配置文件的配置和迁移到数据库的配置提供统一管理服务。代码层面配置数据来源于数据库查询和原有的configProperties bean。使用该配置管理服务后，需要把原先代码中注入的configProperties改为configCacheService

# config-demo
1. config-demo代码结构
配置所需的相关代码已标明，按需添加到项目下即可




2. 数据库执行初始化脚本
脚本见项目init.sql

3. spring dao层配置
参考config-demo\src\main\resources\config\ds\config目录下applicationContext-db.xml和sqlMapConfig.xml。具体按自己模块的两个表的位置决定

4. 复制mapper和dao层接口
复制config-demo\src\main\resources\config\mappers\config目录到项目的mapper文件夹下
复制config-demo\src\main\java\com\demo\config\www\dao\config目录到项目的dao层接口目录下

5. 复制缓存服务和配置业务逻辑代码
复制config-demo\src\main\java\com\demo\config\www\service\cache、config-demo\src\main\java\com\demo\config\www\service\common和config-demo\src\main\java\com\demo\config\www\service\config三个目录，放到项目service层目录下，demo中service接口实现类在接口路径下，可按需调整

6. 复制controller
复制config-demo\src\main\java\com\demo\config\www\controller\data\config目录到项目的controller/data下

7. 配置spring容器启动完成的缓存初始化



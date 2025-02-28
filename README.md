# imageWebDemo
docker image project demo

## docker镜像打包步骤

### 导入基础镜像
```aiignore
docker import --platform  linux/amd64  -  jdk1.8:20241127  < /Users/jimmysong/workspace/国信2024/jdk1.8-20241127.tgz
```


### docker镜像打包演示
```aiignore
docker build --platform  linux/amd64 -t imagewebdemo:v20250116 .
``` 
执行成功后，镜像打包到本地的docker本地仓库中，多个镜像存储到docker的独立文件中

### 将其导出到本地文件中
```aiignore
docker save -o ./imagewebdemo-v20250116.tgz  imagewebdemo:v20250116 
```
- 注意一定是导出.tgz文件，而不是.tar.gz文件

### 访问url前缀
根据环境变量的${CONTEXT_PATH_PREFIX}值，添加到访问前缀。如果没有配置环境变量，使用默认值/demo-image-web。

# AndroidProject

* BUAA_Android大作业，实现一个外卖派送系统

## git相关操作
* 首先下载[git](https://git-scm.com/)，然后绑定自己的`ssh-key`，[具体步骤见此](https://blog.csdn.net/shuang_waiwai/article/details/121108964)
* 之后将该文件`git clone`下来，然后就可以在本地进行修改、提交、拉取了

* 本地修改提交

```bash
git add .
git commit -m "xxx" //写一些有用的信息
git push //提交到远程分支
```

* 其他人修改并提交到远程后，本地拉取分支

```bash
git pull // 获取其他人修改的信息
```

* 切换分支

```bash
git checkout learning
```

* 注：本仓库的分支有两个，一个为main分支（用于代码编写时的提交），一个为learning分支（学习代码的分支）
* 其他操作见[git教程](https://www.liaoxuefeng.com/wiki/896043488029600)
* **以上操作均可以在`Android studio`中实现**

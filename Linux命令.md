# Linux命令总结

## 1、帮助命令(man、info)

* **man (manual的缩写)**
  * 举例：
    * man ls : 获取ls命令的帮助
    * man 1 ls :  获取第1篇章的ls命令的帮助，命令可以分为9章篇章，因为命令可能会重名，所以命令分篇章。
    * man -a passwd: 可以查看所有篇章的命令帮助
* **help**
  * 内部命令(shell（命令解释器）自带的命令）使用help
    * 举例
      * help cd：获取cd命令帮助
  * 外部命令
    * 举例
      * ls --help：获取ls命令帮助
  * 可以用**type命令**区分命令是内部还是外部命令
    * 举例
      * type ls
* **info**
  * 作为help命令的补充，比help命令更详细

## 2、文件相关命令

* **pwd 显示当前的操作目录（当前所在路径）**
* **ls 列出目录下的内容**
  * 举例
    * ls 列出当前路径下的内容
    * ls /root 列出root路径下的内容
    * ls /root / 显示出root目录下和根目录下的内容
  * ls命令参数选项详解
    * ls -l 显示文件的属性信息
    * ls -a 显示隐藏文件
    * ls -r 逆向（默认根据文件名）显示文件
    * ls -l -r -t 或 ls -lrt 按时间逆向显示文件（包括文件属性信息）
    * ls -R 递归显示，如果显示的文件夹还有内容，也一并显示子文件夹
* **cd 更改当前的操作目录**
  * . 表示当前目录
  * .. 表示上一层目录
  * cd ../ 或 cd .. 进入上一级的目录
  * cd - 回到上一次操作的目录
* **mkdir 创建目录命令**
  * 举例	
    * mkdir a 在当前路径下建立a文件夹
    * mkdir /root/a 在root目录下建立a目录
    * mkdir a b c 在当前目录下建立a、b、c三个目录
    * mkdir -p /a/b/c 在当前目录下建立多级目录，-p可以忽略已存在的目录
* **rmdir 删除目录（只能删除空白目录）**
* **rm 删除命令**
  * rm -r /a 删除a目录（带确认）
  * rm -r -f /a 删除a目录 （不带确认，慎用）
  * rm -r -f / a 删除根目录（相当于删除所有数据）和a目录（不带确认，慎用）。
* **touch 新建文件命令**
  * touch file 在当前目录下新建一个file文件

* **cp (copy的缩写)  复制命令**
  * cp 源文件的路径 目标目录
  * 举例
    * cp -r /root/a /tmp 把root下目录的a目录复制到tmp目录下
    * cp -p 复制的时候会保留源文件的时间
    * cp -a 复制的时候会保留原=源文件的时间、属主、权限
* **mv** **改名和移动命令**
  * mv filea fileb 把filea改名为fileb
  * mv filea /tmp 把filea移动到tmp目录下
  * mv filea /tmp/fileb 把filea移动到tmp目录下并改名为fileb
* **通配符的使用**
  * *、？
    * 举例：
      * ls file* 列出以file为前缀，匹配多个字符的文件，比如filea,fileab,fileabc
      * ls file? 列出以file为前缀，匹配一个字符的文件
* **cat 文本内容显示到终端**
* **head 查看文件开头**
  * head -5 filea.txt 查看开头前5行内容

* **tail 查看文件结尾**
  * -f 选项 文件内容更新后，同步显示更新后的信息

* **wc 统计文件内容信息**
  * -l 选项 查看文件多少行
* **more、less 分行显示文件内容**

> 打包命令是tar
>
> 压缩命令是gzip和bzip2
>
> 常见扩展名：.tar.gz和.tar.bz2和.tgz

* 

  * tar cf /tmp/etc-backup.tar /etc

  把etc目录打包到tmp目录下，再可以使用gzip或bzip2命令压缩

  * tar cjf /tmp/etc-backup.tar.bz2 /etc

  把etc目录打包到tmp目录下，并使用bzip2命令压缩

  * tar xf /tmp/etc-backup.tar -C /root

  解包到root目录下

  * tar zxf /tmp/etc-backup.tar.gz /etc

  解压缩到root目录下

## 3、Vim

* 四种模式
  * 正常模式（Normal-mode)
    * h、j、k、l  移动光标
    * yy 复制当前行
    * 3yy 复制当前行及下两行（共3行）
    * y$ 复制当前光标到当前行的结尾
    * dd 剪切当前行
    * 3dd 剪切当前行及下两行（共3行）
    * d$ 剪切当前光标到当前行结尾
    * u 撤销指令（可以多次按u键进行多次撤销）
    * Ctrl + r 重做上一次的撤销指令
    * x 单个字符的删除命令
    * r 单个字符的替换命令
    * p 粘贴
    * j 来到文本第一行
    * J 来到文本最后一行
    * 按行数 + 按Shift + j 或 J 来到指定行数
    * Shift + ^ 来到这一行的开头
    * Shift + $ 来到这一行的结尾
  * 插入模式（Insert-mode)
    * i键
    * I键 光标移动到当前行的开头
    * a键 光标移动到当前光标的下一位
    * A键 光标移动到当前行的末尾
    * o键 光标移动到当前光标的下一行，产生空行
    * O键 光标移动到当前光标的上一行，产生空行
  * 命令模式（Command-mode)
    * ：键
    * ：set nu 显示行数
    * ：w /root/a.txt 把文件保存到root路径的a.txt中
    * ：w 直接保存到Vim打开的源文件
    * ：q! 不保存修改退出
    * ：!ifconfig 临时执行ifconfig命令，查看ip地址并复制
    * /3 搜索3，n搜索下一个3，shift + n 搜索上一个3
    * :s/x/X 在当前行将x替换X
    * :%s/x/X 在文件中将x替换成X(只替换一个x)
    * :%s/x/X/g 在文件中将x替换成X(多次替换)
    * :3,5s/x/X/g 在3到5行将x替换成X（多次替换）
    * 
  * 可视模式（Visual-mode)
    * v 字符可视模式
    * V 行可视模式
    * Ctrl + v  块可视模式
      * 配合d和I使用

## 4、用户及文件

* useradd wilson
  * 新建一个wilson的用户
* id root
  * 查看root用户的属性
* root的家目录在/root下，wilson的家目录在/home/wilson下
* /etc/passwd和/etc/shadow 存放用户信息
* uid 用户的id，gid 用户的用户组id
* passwd wilson
  * 设置wilson用户的密码
* passwd
  * 设置当前用户的密码
* userdel wilson
  * 删除wilson用户 （家目录没有被删除）
* userdel -r wilson
  * 删除wilson用户，并删除它的家目录

* usermod -d /home/wilson01 wilson
  * 将用户的家目录改变为/home/wilson01
* chage 修改用户属性
* groupadd group1
  * 新建一个用户组group1
* gruopdel gruop1
  * 删除一个用户组group1
* su 切换用户
* su - 切换用户，同时切换到对应的家目录
* sudo 以其它用户身份执行命令
  * visudo 设置可以执行sudo的用户组
    * 在最后一行添加wilson ALL=/sbin/shutdown -C把shutdown -C的执行权限赋给wilson用户
    * 可以使用which shutdown显示shutdown的位置
* shutdown -h 30
  * root使用，30分后关闭服务器
* sudo shutdown -C
  * wilson用户停止服务器的关机
* **用户配置文件/etc/passwd**
  * 分为七个字段
    * user1:x：1001:1001::/home/user1:/bin/bash
      * user1 用户名
      * x表示是否需要密码登录
      * 1001 用户id
      * 1001 用户组id
      * ​          注释字段
      * /home/user1 用户的家目录位置
      * /bin/bash  用户使用的命令解释器
* **用户和用户密码相关/etc/shadow**
  * 第一个字段
    * 用户名
  * 第二个字段
    * 用户保存过的加密后的密码
* **用户组配置文件/etc/group**
  * mail：x：12:postfix
    * 四个字段
      * 用户组名
      * 是否需要密码验证
      * 用户组id
      * 其它组

* 文件属性

  * ![image-20220924163738272](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220924163738272.png)

    * 共十个字符

      * 第一个字符 表示文件类型

        * -普通文件

        * d目录文件
        * b块特殊文件
        * c字符特殊文件
        * l符合链接
        * f命令管道
        * s套接字文件

      * 后三个字符表示文件属主的权限

      * 再后三个字符表示文件属组的权限

      * 最后三个字符表示其它用户的权限

* 文件权限的表示方法

  * 字符表示
    * r 读
    * w 写
    * x 执行
  * 数字表示
    * r
    * w
    * x

* 目录权限的表示方法
  * x 进入目录
  * rx 显示目录内的文件名
  * wx 修改目录内的文件名
* chmod 修改文件、目录权限
  * chmod u+x /tmp/testfile
  * chmod 755 /tmp/testfile
* chown 更改属主、属组
* chgrp 可以单独更改属组，不常用

* ctrl + r 键 可以搜索之前执行的命令



## 5、网络管理

### 1、网络状态查看

1. net-tools

   * ifconfig
     * ![image-20220926193624057](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220926193624057.png)
     * 网卡命令规则受biosdevname和net.ifnames两个参数影响1
     * 编辑/etc/default/grup文件，增加biosdevname=0和net.ifnames=0
     * 更新grup
       * grup-mkconfig -o /boot/grub2/grup.cfg
       * 重启 reboot
   * ifconfig eth0 查看eth0网卡的配置
   * mii-tool etho 查看eth0网卡的物理连接状态

   * route （查看网关）
     * route -n （不解析主机名）
   * netstat

2. iproute2

   * ip
   * ss

### 2、网络配置

* ifconfig <接口> <IP地址> [netmask 子网掩码]
* ifup <接口>
* iddown <接口>

* 添加网关
  * route add default gw <网关ip>
  * route add -host <指定ip> gw <网关ip>
  * route add -net <指定网段> netmask <子网掩码> gw <网关ip>
* route del default gw 10.211.55.1
* route add default gw 10.211.55.1
* route add -host 10.0.0.1 gw 10.211.55.1
* route add -net 192.168.0.0 netmask 255.255.255.0 gw 10.211.55.3

### 3、网络故障排除

* ping  是否和目标主机畅通
  * ping www.baidu.com

* traceroute 追踪路由
  * traceroute -w 1 www.baidu.com (最多等待一秒)
* mtr 更详细的追踪路由信息
* nslookup  将域名解析为Ip地址
  * nslookup www.baidu.com

* telnet 是否和目标主机的目标端口畅通
  * telnet  www.baidu.com 80
  * ^]后输入quit退出
* tcpdump 抓取网络包
  * tcpdump -i any -n port 80
  * tcpdump -i any -n host 10.0.0.1 and port 80
  * tcpdump -i any -n host 10.0.0.1 and port 80 -w /tmp/file
* netstat
  * netstat -ntpl (ip显示、tcp、进程、tcp的状态)
* ss
  * ss -ntpl

### 4、网络服务管理

* 网络服务管理程序分为两种，分别为SysV和systemd
  * service network start | stop |restart
  * chkconfig -list network
  * systemctl list-unit-files NetworkManager.service
  * systemctl start|stop|restart NetworkManger
  * systemctl enable|disable NetworkManger

* service network status
* serviice network restart

* ifcfg-etho (ifcfg-网卡名)
* /etc/hosts

* chkconfig --list network
* chkconfig --level 2345 network off
* chkconfig --level 2345 network on
* systemctl disable NetworkManager
* systemctl enable NetworkManager
* **网卡配置文件**
  * /etc/sysconfig/network-scripets/ifcfg-*
  * hostname 查看主机名
  * hostname c7.test01 修改主机名（临时生效）
  * hostnamectl set-hostname  修改主机名（永久生效）
  * /**etc**/**hosts**，主机名的配置文件

## 6、软件安装

### 1、rpm和yum

* 包管理器是方便软件安装、卸载，解决软件依赖关系的重要工具
  * CentOS、RedHat使用yum包管理器，软件安装包格式为rpm
  * Debian、Ubuntu使用apt包管理器，软件安装包格式为deb
* ![image-20220926214824559](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220926214824559.png)
* rpm 命令常用参数
  * -q 查询软件包
  * -i 安装软件包
  * -e 卸载软件包
* /dev 设备文件
* /etc/sr0 光驱文件
* mount /dev/sr0 /mnt 挂载
* rpm -qa | more 查询系统中已安装所有安装包（分屏显示，按空格）
* rpm -q vim-common 查询vim-common是否安装
* rpm -i vim-enhanced-7.4.160-5.el7.x86_64.rpm 安装软件包
* rpm -e vim-common 卸载vim-common
* rpm包的问题
  * 需要自己解决依赖问题
  * 软件包来源不可靠
* 国内镜像：https://opsx.alibaba.com/mirror
* ![image-20220926234437567](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220926234437567.png)
* yum命令常用选项
  *  install 安装软件包
  * remove 卸载软件包
  * list| grouplist 查看软件包
  * update 升级软件包
* 二进制安装
* 源代码编译安装
  * wegt https://openresty.org/download/openresty-1.15.8.1.tar.gz
  * tar -zxf openresty-VERSION.tar.gz
  * cd openresty-VERSION/
  * ./configure --prefix=/usr/local/openresty
  * make -j2
  * make install

### 2、内核升级

* rpm 格式内核
  * 查看内核版本
    * uname -r
  * 升级内核版本
    * yum install kernek-3.10.0
  * 升级已安装的其它软件包和补丁
    * yum update
* 源代码编译安装内核
  * 安装依赖包
    * yum install gcc-c++ make ncurses-devel openssl-devel elfutils-libelf-devel
    * 下载并解压缩内核
      * https://www.kernel.org
      * tar xvf linux-5.1.10.tar.xz -C /usr/src/kernels
    * 配置内核编译参数
      * cd /usr/src/kernels/linux-5.1.10/
      * make menuconfig | allyesconfig | allnoconfig
    * 使用当前系统内核配置
      * cp /boot/config-kernelversion.platform /usr/src/kernels/linux-5.1.10/.config
    * 查看CPU
      * lscpu
    * 编译
      * make -j2 all
    * 安装内核
      * make modules_install
      *  make install

### 3、grup

* grup是内核启动时涉及到的一个引导软件

* grup配置文件
  * /etc/default/grup
  * /etc/grup.d/
  * /boot/grup2/grup.cfg
  * grup2-mkconfig -o /boot/grup2/grup.cfg

* grub2-set-default 1 设置默认引导内核
* 使用grup重置root密码

## 7、进程管理

* 进程的概念和进程查看
  * 进程是运行中的程序，从程序开始运行到终止的整个生命周期是可管理的
  * C程序的启动是从main函数开始的
    * int main(int agrc, char *argv[])
    * 正常终止和异常终止
  
* 进程查看命令
  * ps 
    * ps -e
    * ps -e | more
    * ps -ef
    * ps -eLf
  * pstree
  * top
    * 输出结果参数解释：
      * up 32 min, 2 users
        * 最近一次开机到现在32分钟，有2个用户
      * load average 0.02 0.04 0.05
        * 平均负载，值为1表示满负载，5分钟的平均负载0.02，10分钟的平均负载为0.04，15分钟为0.05
    * top -p 18746
      * 只查PID为18746的进程运行
  * 进程是树形结构，和权限联系紧密

* 进程控制

  * 举例：a.sh

    * ```shell
      #!/bin/bash
      echo $$
      while :
      do
      	:
      done
      ```

    * 

  * 调整优先级

    * nice 范围[-20, 19]，值越小优先级越高，抢占资源越多
      * nice -n 10 ./a.sh （对还运行的进程赋给初始优先级）
    * renice 重新设置优先级
      * renice -n 15 19314 (对已运行的进程进行调整)

  * 进程的作业控制
    * jobs
    * & 符号
    * ./a.sh &
      *  后台运行进程（终端可以继续输入命令）
    * jobs后输入fg + 对应进程编号
      * 可以将进程调回前台
    * jobs后输入bg + 对应进程编号
      * 可以将进程调回后台
    * ctrl + z 将进程调回后台并停止

* 进程间通信

  * ![image-20220927135307643](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220927135307643.png)
  * kill -l 查看所有信号
  * kill -9 4123 将编号为9的信号发给PID为4123的进程（杀死进程4123）

* 守护进程

  * 占用目录是根目录，不需要终端可以启动，启动时打印一些内容到文件中

* 使用nohub与&符合配合运行一个命令

  * nohup命令使进程忽略hangup(挂起)信号

* nohup tail -f /var/log/messages &

  * 这个进程在后台运行，即使终端关闭无影响

* /proc/2423

  * 进程为2423的相关信息

  * /proc/2423/cwd 进程运行的目录

  * /proc/2423/fd

* 使用screen命令

  * screen 进入screen 环境
  * ctrl + a d 退出（detached）screen环境
  * screen -ls 查看screen的会话
  * screen -r sessionid 恢复会话

* 服务管理工具systemctl

  * 服务（提供常见功能的守护进程）集中管理工具
    * service
    * systemctl

* ![image-20220927152005882](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220927152005882.png)

* getenforce 查看SELinux的运行状态
  * /etc/selinux/config
*  ps -Z
* ls -Z
* id -Z

## 8、内存和磁盘管理

### 1、内存和磁盘使用率查看

* 内存查看
  * free
    * -m 以Mb显示
    * -g 以Gb显示
  * top
* 磁盘查看
  * fdisk -l
  * ls -l /dev/sd?
  * ls -l /dev/sd??
  * parted -l
  * df -h
  * du 查看文件实际占用空间大小
  * ls 查看文件记录的文件大小

### 2、ext4文件系统

* Linux支持多种文件系统，有
  * ext4
  * xfs
  * NTFS（需安装额外软件）
* ext4文件系统基本结构比较复杂
  * 超级块
  * 超级块副本
  * i节点(inode)
    * ls -i 显示文件的i节点编号
  * 数据块(datablock)
  * rm 实际上删除i节点和文件名的链接，这样删除速度是一样的。
  * getfacl afile
  * setfacl -m u:user1:r afile (赋予权限)
  * setfacl -x u:user1:r afile（收回权限）
  * setfacl -m u:user2:rw afile

### 3、磁盘配额的使用

![image-20220927165346083](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220927165346083.png)



### 4、磁盘的分区与挂载

* 常用命令
  * fdisk
    * fdisk + 要分区的硬盘
    * fdisk /dev/sdc 对sdc硬盘分区
    * mkfs.ext4 /dev/sdc1 对分区使用
    * mkdir /mnt/sdc1 创建一个目录
    * mount -t auto /dev/sdc1 /mnt/sdc1 将分区挂载到目录下
    * 修改/etc/fstab配置文件(永久生效)
      * /dev/sdc1/ /mnt/sdc1 ext4 defaults 0 0 
  * mkfs
  * parted （硬盘大于2T时使用）
  * mount
* 配置文件
  * /etc/fstab

### 5、交换分区（虚拟内存）的查看与创建

* 增加交换分区的大小
  * mkswap /dev/sdd1
  * swap /dev/sdd1
  * swapoff /dev/sdd1
* 文件方式
  * dd if=/dev/zero bs=4M count=1024 of=/swapfile
  * chmod 600 /swapfile
  * mkswap /swapfile
  * swapon /swapfile
  * 修改/etc/fstab配置文件(永久生效)
    * /swapfile swap swap defaults 0(要不要备份) 0(开机时磁盘自检的顺序问题) 

### 6、软件RAID的使用

![image-20220927192559217](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220927192559217.png)

* mdadm -C /dev/md0 -a yes -l1 -n2 /dev/sdb1 /dev/sdc1(创建磁盘阵列，同意,，RAID1级别，两个分区)
* mdadm -D /dev/md0 查看
* dd if=/dev/zero of=/dev/sdc bs=4M count=1 进行破坏
* echo DEVICE /dev/sd[b,c]1 >> /etc/mdadm.conf (写入配置文件)
* mdamd -Evs >> /tec/mdamd.conf （写入配置文件）
* mkfs.xfs /dev/md0

### 7、逻辑卷管理

## 9、Shell

### 1、什么是shell

* Shell是命令解释器，用于解释用户对操作系统的操作
* 比如用户输入ls命令，ls命令交给文件管理系统（内核态），文件系统处理好后将结果返回给用户。
* cat /etc/shells
* Linux启动过程
  * BIOS 基本输入输出系统
  * MBR 硬盘的引导记录
  * BootLoader(grup)  启动和引导内核的一种工具
  * kernel
  * systemd
  * 系统初始化
  * shell

* 开机时按F2进入BIOS
* dd if=/dev/sda of=mbr.bin bs=512 count=1
* hexdump -C mbr.bin （以十六进制查看）
* /boot/grup/grup.cfg
* grup2-editenv list
* Centos6使用init
* Centos7使用systemd

* Shell脚本格式

  * ![image-20220927200006333](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220927200006333.png)

  * 新建shell脚本1.sh

    * ```shell
      #!/bin/bash
      cd /var
      ls
      pwd
      du -sh
      du -sh *
      ```

  * 赋给权限 chmod u+rx 1.sh

  * 执行 bash 1.sh 或 ./1.sh

* shell执行命令
  * bash ./filename.sh (创建一个子进程执行)
  * ./filename.sh （创建一个子进程执行，必须有可执行权限）
  * source ./filename.sh
  * filename.sh（当前进程执行）
  * . ./filename.sh （当前进程执行）
* 内建命令不需要创建子进程，只会对当前Shell生效

### 2、管道和重定向

* 管道和信号一样，是进程通信的方式之一

* 匿名管道（管道符）是Shell编程经常用到的通信工具

* 管道符 |

  * 将前一个命令执行的结果传递给后面的命令
    * ps | cat
    * echo 123 | ps

* 管道符会创建两个子进程，将第一个命令的输出和第二个命令的输入建立连接。

* 重定向符号

  * 输入重定向符号  "<"

    * read  var < /path/to/a/file

  * 输出重定向符号

    * ">" 输出到文件（向清空文件内容）
    * ">>" （追加到文件末尾）
    * "2>" （执行错误，错误会输出到文件）
    * "&>" （无论执行正确、错误，全部输出到文件）

  * wc -l < /etc/passwd 统计passwd有多少行

  * read var2 < a.txt 把a.txt读入到变量名var2

  * echo $var2 输出变量var2到终端

  * nocmd 2> c.txt 执行nocmd命令的错误写入到c.txt

  * ```shell
    #!/bin/bash
    cat > /root/a.sh <<EOF
    echo "hello bash"
    EOF
    ```

### 3、变量

* 变量名的命名规则
  * 字母、数字、下划线
  * 不以数字开头
* 变量赋值
  * 变量名=变量值 **（等号左右不许出现空格）**
    * a=123
  * 使用let为变量赋值
    * let a=10+20
  * 将命令复制给变量
    * l=ls
  * 将命令结果赋值给变量，使用$()或``
    * letc=$(ls -l /etc)
  * 变量值有空格等特殊字符可以包含在""或''中（用引号括起来）
* 变量的引用
  * ${变量名}称作对变量的引用
  * echo ${变量名}查看变量的值
  * ${变量名} 在部分情况下可省略为$变量名
* 变量的默认作用范围（当前Shell）

* 变量的导出
  * export
* 变量的删除
  * unset

* 环境变量

  * env | more 查看所有系统环境变量
  * $PATH 命令的搜索路径
  * set | more 查看更多变量
    * 预定义变量：$$、$0、$?等
    * 位置变量：$1、$2、...、$9、**${10}等**
  * $? 上一条命令是否执行成功
  * $$ 显示当前进程的PID
  * $0 显示当前进程的名称

  ```she
  #!/bin/bash
  # $1 $2... $9 ${10}
  post1=${1-_} #如果位置变量为空，则赋值下划线，否则赋值为${1}
  post2=${2-_}
  ```

* 环境变量配置文件

  * /etc/profile
  * /etc/profile.d/
  * ~/.bash_profile
  * ~/.bashrc
  * /etc/bashrc

### 4、数组

* 定义数组
  * IPTS=(10.0.0.1 10.0.0.2 10.0.0.3)
* 显示数组的所有元素
  * echo ${IPTS[@]}
* 显示数组元素个数
  * echo ${#IPTS[@]}
* 显示数组的第一个元素
  * echo ${IPTS[0]}

### 5、转义与引用

* 特殊字符 （一个字符不仅有字面意义，还有元意）
  * "#"注释
  * ";"分号
  * "\\"转义符号
  * 双引号和单引号 
* 单个字符的转义符号
  * \n \r \t 单个字母的转义
  * \\& \\" \\\ 单个非字母的转义
* 常用的引用符号
  * " 双引号
    * 会解释变量名
      * 比如 echo "$var" 会输出变量var的值，但echo  '$var' 会输出$var
  * '  单引号
  * ` 反引号

### 6、运算符

* 赋值运算符
  * =赋值运算符，用于算数赋值和字符串赋值
  * 使用unset取消为变量的赋值
  * =还可以作为测试操作符
* 基本运算符
  * +
  * -
  * *
  * /
  * **
  * %
* 使用expr 进行运算
  * expr 4 + 5 （**只支持整数，+号两边有空格**）
  * num1 =\`expr 4 + 5\`
* 数字常量的使用方法
  * let "变量名 = 变量值"
  * 变量值使用0开头为八进制
  * 变量值使用0x开头为十六进制
* 双圆括号是命令let的简化
  * ((a=10))
  * ((a++))
  * echo $((10+20))

### 7、特殊符号大全

* 引号
  * ' 完全引用
  * " 不完全引用
  * ` 执行命令
* 括号
  * () (()) $() 园括号
    * 单独使用园括号会产生一个子shell
      * (xyz=123)
    * 数组初始化 IPS={ip1 ip2 ip3}
  * [] [[]] 方括号
    * 单独使用方括号是测试(test)或数组元素功能
    * 两个方括号表示测试表达式
  * <> 尖括号 重定向符号
  * {} 花括号
    * 输出范围 echo {0..9}
    * 文件复制 cp /etc/passwd{,.bak}

* 运算符号和逻辑符号
  * \+ - * / %
  * \> < =
  * && || !
* 转义符号
  * \ 转义某字符
    * \n 普通字符转义之后有不同功能
    * \\' 特殊字符转义之后，当作普通字符来使用
* 其它符号
  * \# 注释符
  * ; 命令分隔符
    * case语句的分隔符要转义;;
  * : 空指令
  * . 和 source命令相同
  * ~ 家目录
  * , 分割目录
  * \* 通配符
  * ? 条件测试 或 通配符
  * $ 取值符号
  * | 管道符号
  * & 后台运行
  * -空格

### 8、测试和判断

* 退出程序命令

  * exit
  * exit 10 返回10给Shell，返回值非0位不正常退出
  * $? 判断当前Shell前一个进程是否正常退出

* 测试命令test

  * test命令用于检测文件或者比较值
  * test可以测试
    * 文件测试
    * 整数比较测试
      * -gt 大于
      * -lt 小于
      * -eq 相等
      * -ge 大于等于
      * -le 小于等于
    * 字符串测试(**区分大小写**)
      * ["abc" = "ABC"]
  * test测试语句可以简化为[]符号
  * []符号还有扩展写法[[]]支持&&、||、<、>

* man test 获取test命令的帮助

* test -f /etc/passwd 判断文件(**shell脚本中0表示真，非0值表示假**)

* if-then 语句的基本用法

  * if [测试条件成立] 或 命令返回值是否为0

    then 执行相应命令

    fi 结束

  * 举例

    * ```shell
      #!/bin/bash
      if [ $UID = 0 ]
      then 
      	echo " root user "
      fi
      ```

    * ```she
      #!/bin/bash
      if pwd
      then
      	echo " pwd running "
      fi
      ```

* if-then-else语句

  * ![image-20220928212436480](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220928212436480.png)

  * ```shell
    #!/bin/bash
    if [ $USER = root ] ;then
    	echo "user root"
    	echo $UID
    else
    	echo "other user"
    	echo $UID
    fi
    ```

* if-elif-else语句

  * ![image-20220928213005950](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220928213005950.png)

  * ```shell
    #!/bin/bash
    if [ $USER = root ] ;then
    	echo "root"
    elif [ $USER = user1 ] ;then
    	echo "user1"
    else
    	echo "other user"
    fi
    ```

* 嵌套if的使用

  * ![image-20220928213351283](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220928213351283.png)

  * 

    ```shell
    #!/bin/bash
    if [ $USER = root ] ;then 
    	echo "root user"
    	if [ -x /tmp/10.sh] ;then
    		/tem/10.sh
    	fi
    else
    	echo "other user"
    fi
    ```

* case语句

  * ![image-20220928213939474](C:\Users\wcx\AppData\Roaming\Typora\typora-user-images\image-20220928213939474.png)

  * ```shell
    #!/bin/bash
    case "$1" in
    	"start"|"START")
    	echo $0 start......
    	;;
    	"stop")
    	echo $0 stop......
    	;;
    	"restart"|"reload")
    	echo $0 restart......
    	;;
    	*)
    	echo "Usage: $0 {start|stop|restart}”
    	;;
    esac
    ```

### 9、循环语句

* for 循环的语法

  for 参数 in 列表

  do 执行的命令

  done 封闭一个循环

* 使用反引号或$()方式执行命令，命令的结果当作列表进行处理

* 列表中包含多个变量，变量用空格分隔

* 对文本处理，要使用文本查看命令取出文本内容

  * 默认逐行处理，如果文本出现空格会当作多行处理

```shell
#!/bin/bash
for i in {1..9}
do
	echo $i;
	echo hello;
done
for filename in `ls *.mp3`
do
	mv $filename $(basename $filename .mp3).mp4
done
```

* C语言风格的for

  for((变量初始化; 循环判断条件;变量变化))

  do

  ​	循环执行的命令

  done

```shell
#!/bin/bash
for (( i=1; i<=10; i++))
do
	echo $i
done
```

* while循环

  while test测试是否成立

  do

  ​	命令

  done

* ```shell
  #!/bin/bash
  while [$a -lt 10]
  do
  	((a++))
  	echo $a
  done
  ```

* until循环与while循环相反，测试条件为假执行，否则终止

### 10、break、continue的使用

* ```shell
  #!/bin/bash
  for sc_name in /etc/profile.d/*.sh
  do
  	if [ -x $sc_name ] ; then
  		. $sc_name
  	fi
  	#echo $sc_name
  done
  ```

* ```shell
  #!/bin/bash
  for num in {1..9}
  do
  	if [ $num -eq 5 ] ; then
  	 	#continue
  		break
  	fi
  	echo $num
  done
  ```

* 命令行参数可以使用$1 $2 .. ${10} ... $n 进行读取
* $0 代表脚本名称
* $* 和 $@ 代表所有位置参数
* $# 代表位置参数的数量

```shell
#!/bin/bash
# help display help help
for pos in $*
do
	if [ "$pos" = "help" ]; then
		echo $pos $pos
	fi
done

while [ $# -ge 1 ]
do
	if [ "$1" = "help"]; then
		echo $1 $1
	fi
	# shift参数左移命令
	shift
done
```

### 11、函数

* 自定义函数

  function fname(){

  命令

  }

* 函数的执行

  * fname

* ```shell
  #!/bin/bash
  function cdls() {
  cd /var
  ls
  }
  ```

* 函数作用范围的变量

  * local 变量名

* 函数的参数

  * $1 $2 ... $n

* ```shell
  #!/bin/bash
  cdls() {
  cd $1
  ls
  }
  ```

* `cdls /tmp` 执行cdls函数

* ```shell
  #!/bin/bash
  checkpid() {
  	local i
  	
  	for i in $* ; do
  		[-d "/proc/$i"] && return 0
  	done
  	return 1
  }
  ```

* 系统的自建函数库

  * /etc/init.d/functions
  * 使用source函数脚本文件"导入"函数
    * source /etc/init.d/functions

* 脚本控制

  * 脚本优先级分配

    * 使用nice和renice调整脚本优先级

    * 避免出现“不可控”的死循环

      * 死循环导致CPU占用过高
      * 死循环导致死机

    * **ulimit -a 查看当前系统的限制**

    * fork炸弹：大量创建子进程占用系统资源

      `func() { func | func$}; func`

### 12、信号

* 捕获信号脚本的编写
  * kill 默认会发送15号信号给应用程序
  * ctrl +  c 发送2号信号给应用程序
  * 9号信号不能被阻塞

* ```shell
  #!/bin/bash
  # signal demo
  trap "echo sig 15" 15
  trap "echo sig 2" 2
  echo $$
  while :
  do
  	:
  done
  ```

### 13、计划任务

* 一次性计划任务
  * date 显示当前系统的运行时间
  * at 18:31创建一个在18:31执行的任务，ctrl+d退出
  * at中没有PATH变量，命令最后写上命令的完整路径
  * atq 可以查询任务是否被执行
* 周期性计划任务
  * cron
    * 配置方式
      * crontab -e
        * `15 18 7 7 1-5 /usr/bin/date >> /tmp/date.txt`
          * 7月7日是工作日的话18时15分执行
        * 0 3 3 3 * /usr/bin/date >> /tmp/date.txt
          * 3月3日3点整运行
    * 查看现有的计划任务
      * crontab -l
    * 配置格式
      * 分钟 小时 日期 月份 星期 执行的命令
      * 注意命令的路径问题

* 如果计算机不能按照预期时间执行
  * anacontab 延时计划执行
  * flock 锁文件

## 10、文本操作

* 正则表达式与文本搜索
  * 元字符
  * 扩展元字符
  * 文件的查找命令find
  * 文本内容的过滤（查找）grep
* 正则表达式的匹配方式
  * 字符串 Do one thind at a time, and do well.
  * 匹配字符 an
* 元字符
  * .匹配除换行符外的任意单个字符
  * \* 匹配任意一个跟在它前面的字符
  * [] 匹配方括号中的字符类中的任意一个
  * ^ 匹配开头
  * $ 匹配结尾
  * \ 转义后面的特殊字符
    * 在Shell中搭配双引号，防止Shell又解释一遍
    * 举例：grep "\\." /root/anaconda-ks.cfg
* grep 查找文本中含有关键字的一行
  * grep password /root/anaconda-ks.cfg
* 扩展元字符
  * \+ 匹配前面的正则表达式至少出现一次
  * ？ 匹配前面的正则表达式出现零次或一次
  * | 匹配它前面或后面的正则表达式

* 文件查找命令 find
  * find 路径 查找条件 [补充条件]
* find /etc -name pass*
  * 使用通配符查找etc目录下pass开头的文件名的文件
* find /etc -regex .*wd
  * 使用正则表达式（元字符）匹配以wd结尾的文件名的文件
* 

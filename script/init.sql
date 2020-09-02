drop table if exists blog_config;
create table `blog_config`
(
    id                int primary key auto_increment,
    name              varchar(50) comment '博客名称',
    file_path         varchar(255) not null unique comment '博客路径',
    source_path       varchar(255) comment '源文件路径',
    source_remote_url varchar(255) comment '源文件远程仓库地址',
    remote_url        varchar(255) comment '远程仓库地址',
    initialized       tinyint default 0 comment '是否被初始化',
    create_time       datetime comment '创建时间',
    update_time       datetime comment '更新时间'
);
create table source
(
    id          int primary key auto_increment,
    file_path   varchar(255) not null unique comment '文件路径',
    remote_url  varchar(255) comment '远程资源地址',
    type        varchar(10)  not null comment '资源类型',
    initialized tinyint default 0 comment '是否被初始化',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间'
)
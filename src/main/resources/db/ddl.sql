create table if not exists demo.lc_app
(
    id               bigint auto_increment comment '主键'
    primary key,
    app_code         varchar(128) null comment '应用code',
    app_name         varchar(255) null comment '应用名称',
    app_name_en      varchar(255) null comment '英文',
    app_remark       varchar(512) null comment '备注',
    is_deleted       char         null comment '逻辑删除 y，n',
    creator          varchar(64)  null comment '创建人',
    modifier         varchar(64)  null comment '修改人',
    create_time      datetime(6)  null comment '创建时间',
    last_update_time datetime(6)  null comment '最近更新时间'
    );

create table if not exists demo.lc_page
(
    id               bigint       not null comment '主键',
    page_code        varchar(128) null comment '应用code',
    page_name        varchar(255) null comment '应用名称',
    page_name_en     varchar(255) null comment '英文',
    app_code         varchar(128) null comment '归属应用code',
    app_remark       varchar(512) null comment '备注',
    page_schema      longtext     null comment '页面动态schema',
    page_assets      mediumtext   null comment '依赖资源管理',
    is_deleted       char         null comment '逻辑删除 y，n',
    creator          varchar(64)  null comment '创建人',
    modifier         varchar(64)  null comment '修改人',
    create_time      datetime(6)  null comment '创建时间',
    last_update_time datetime(6)  null comment '最近更新时间'
    );


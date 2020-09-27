CREATE TABLE `audit_log`
(
    `id`       bigint(20) NOT NULL auto_increment,
    `username` varchar(64) COMMENT '用户名',
    `path`     varchar(64) COMMENT '路径',
    `method`   varchar(64) COMMENT '方法',
    `status`   tinyint(4) COMMENT '状态',
    `create_time`   datetime COMMENT '创建时间',
    `update_time`   datetime COMMENT '修改时间',
    PRIMARY KEY (`id`)
);

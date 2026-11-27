CREATE DATABASE `data`;

CREATE TABLE `data`.`t_user`
(
    `id`             bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `rednote_id`     varchar(15)     NOT NULL COMMENT '蜜蜂存号(唯一凭证)',
    `phone`          varchar(16)     NOT NULL COMMENT '手机号',
    `password`       varchar(64)              DEFAULT NULL COMMENT '密码',
    `avatar`         varchar(128)    NOT NULL DEFAULT '' COMMENT '头像',
    `nickname`       varchar(32)     NOT NULL COMMENT '昵称',
    `gender`         tinyint                  DEFAULT '0' COMMENT '性别(0-未知,1-女,2-男)',
    `birthday`       date                     DEFAULT NULL COMMENT '生日',
    `background_img` varchar(120)             DEFAULT NULL COMMENT '背景图',
    `status`         tinyint         NOT NULL DEFAULT 1 COMMENT '账号状态(0-禁用,1-正常,2-注销,3-待审核)',
    `introduction`   varchar(128)             DEFAULT NULL COMMENT '个人简介',
    `create_time`    datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`     bit(1)          NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0-未删除,1-已删除)',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_rednote_id` (`rednote_id`),
    UNIQUE KEY `uk_phone` (`phone`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户表';


CREATE TABLE `data`.`t_role`
(
    `id`          bigint unsigned                        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_name`   varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名',
    `role_key`    varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色唯一标识',
    `status`      tinyint                                NOT NULL DEFAULT '0' COMMENT '状态(0：启用 1：禁用)',
    `sort`        int unsigned                           NOT NULL DEFAULT 0 COMMENT '管理系统中的显示顺序',
    `remark`      varchar(255) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '备注',
    `create_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  bit(1)                                 NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_role_key` (`role_key`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色表';


CREATE TABLE `data`.`t_permission`
(
    `id`             bigint unsigned                         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `parent_id`      bigint unsigned                         NOT NULL DEFAULT '0' COMMENT '父ID',
    `name`           varchar(16) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '权限名称',
    `type`           tinyint unsigned                        NOT NULL COMMENT '类型(1：目录 2：菜单 3：按钮)',
    `menu_url`       varchar(32) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '菜单路由',
    `menu_icon`      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
    `sort`           int unsigned                            NOT NULL DEFAULT 0 COMMENT '管理系统中的显示顺序',
    `permission_key` varchar(64) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '权限标识',
    `status`         tinyint unsigned                        NOT NULL DEFAULT '0' COMMENT '状态(0：启用；1：禁用)',
    `create_time`    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`     bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='权限表';


CREATE TABLE `data`.`t_user_role_rel`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     bigint unsigned NOT NULL COMMENT '用户ID',
    `role_id`     bigint unsigned NOT NULL COMMENT '角色ID',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  bit(1)          NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户角色表';


CREATE TABLE `data`.`t_role_permission_rel`
(
    `id`            bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_id`       bigint unsigned NOT NULL COMMENT '角色ID',
    `permission_id` bigint unsigned NOT NULL COMMENT '权限ID',
    `create_time`   datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`    bit(1)          NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户权限表';


INSERT INTO `data`.`t_permission` (`id`, `parent_id`, `name`, `type`, `menu_url`, `menu_icon`, `sort`, `permission_key`,
                                   `status`, `create_time`, `update_time`, `is_deleted`)
VALUES (1, 0, '发布笔记', 3, '', '', 1, 'app:note:publish', 0, now(), now(), b'0');
INSERT INTO `data`.`t_permission` (`id`, `parent_id`, `name`, `type`, `menu_url`, `menu_icon`, `sort`, `permission_key`,
                                   `status`, `create_time`, `update_time`, `is_deleted`)
VALUES (2, 0, '发布评论', 3, '', '', 2, 'app:comment:publish', 0, now(), now(), b'0');
INSERT INTO `data`.`t_role` (`id`, `role_name`, `role_key`, `status`, `sort`, `remark`, `create_time`, `update_time`,
                             `is_deleted`)
VALUES (1, '普通用户', 'common_user', 0, 1, '', now(), now(), b'0');
INSERT INTO `data`.`t_role_permission_rel` (`id`, `role_id`, `permission_id`, `create_time`, `update_time`,
                                            `is_deleted`)
VALUES (1, 1, 1, now(), now(), b'0');
INSERT INTO `data`.`t_role_permission_rel` (`id`, `role_id`, `permission_id`, `create_time`, `update_time`,
                                            `is_deleted`)
VALUES (2, 1, 2, now(), now(), b'0');


CREATE DATABASE `leaf`;
CREATE TABLE `leaf`.`leaf_alloc`
(
    `biz_tag`     varchar(128) NOT NULL DEFAULT '',
    `max_id`      bigint(20)   NOT NULL DEFAULT '1',
    `step`        int(11)      NOT NULL,
    `description` varchar(256)          DEFAULT NULL,
    `update_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`biz_tag`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='分布式ID生成表';
insert into `leaf`.`leaf_alloc`(biz_tag, max_id, step, description)
values ('leaf-segment-test', 1, 2000, 'Test leaf Segment Mode Get Id');


INSERT INTO `leaf`.`leaf_alloc` (`biz_tag`, `max_id`, `step`, `description`, `update_time`)
VALUES ('leaf-segment-rednote-id', 1, 2000, '小红书 ID', now());

INSERT INTO `leaf`.`leaf_alloc` (`biz_tag`, `max_id`, `step`, `description`, `update_time`)
VALUES ('leaf-segment-user-id', 100, 2000, '用户 ID', now());


CREATE TABLE `data`.`t_channel`
(
    `id`          bigint(11) unsigned                                          NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '频道名称',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='频道表';

CREATE TABLE `data`.`t_topic`
(
    `id`          bigint(11) unsigned                                          NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '话题名称',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='话题表';

CREATE TABLE `data`.`t_channel_topic_rel`
(
    `id`          bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `channel_id`  bigint(11) unsigned NOT NULL COMMENT '频道ID',
    `topic_id`    bigint(11) unsigned NOT NULL COMMENT '话题ID',
    `create_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='频道-话题关联表';

CREATE TABLE `data`.`t_note`
(
    `id`               bigint(11) unsigned                                          NOT NULL COMMENT '主键ID',
    `title`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
    `is_content_empty` bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '内容是否为空(0：不为空 1：空)',
    `creator_id`       bigint(11) unsigned                                          NOT NULL COMMENT '发布者ID',
    `topic_id`         bigint(11) unsigned                                                   DEFAULT NULL COMMENT '话题ID',
    `topic_name`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '话题名称',
    `is_top`           bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否置顶(0：未置顶 1：置顶)',
    `type`             tinyint(2)                                                            DEFAULT '0' COMMENT '类型(0：图文 1：视频)',
    `img_uris`         varchar(660) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '笔记图片链接(逗号隔开)',
    `video_uri`        varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '视频链接',
    `visible`          tinyint(2)                                                            DEFAULT '0' COMMENT '可见范围(0：公开,所有人可见 1：仅对自己可见)',
    `create_time`      datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`           tinyint(2)                                                   NOT NULL DEFAULT '0' COMMENT '状态(0：待审核 1：正常展示 2：被删除(逻辑删除) 3：被下架)',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_creator_id` (`creator_id`),
    KEY `idx_topic_id` (`topic_id`),
    KEY `idx_status` (`status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='笔记表';

ALTER table `data`.`t_note`
    add column `content_uuid` varchar(36) DEFAULT '' COMMENT '笔记内容UUID' after `is_content_empty`;

INSERT INTO `data`.`t_channel` (`name`, `create_time`, `update_time`, `is_deleted`)
VALUES ('美食', now(), now(), 0);
INSERT INTO `data`.`t_channel` (`name`, `create_time`, `update_time`, `is_deleted`)
VALUES ('娱乐', now(), now(), 0);
INSERT INTO `data`.`t_topic` (`name`, `create_time`, `update_time`, `is_deleted`)
VALUES ('高分美剧推荐', now(), now(), 0);
INSERT INTO `data`.`t_topic` (`name`, `create_time`, `update_time`, `is_deleted`)
VALUES ('下饭综艺推荐', now(), now(), 0);
INSERT INTO `data`.`t_channel_topic_rel` (`channel_id`, `topic_id`, `create_time`, `update_time`)
VALUES (2, 1, now(), now());
INSERT INTO `data`.`t_channel_topic_rel` (`channel_id`, `topic_id`, `create_time`, `update_time`)
VALUES (2, 2, now(), now());


CREATE TABLE `data`.`t_following`
(
    `id`                bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`           bigint unsigned NOT NULL COMMENT '用户ID',
    `following_user_id` bigint unsigned NOT NULL COMMENT '关注的用户ID',
    `create_time`       datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户关注表';


CREATE TABLE `data`.`t_fans`
(
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`      bigint unsigned NOT NULL COMMENT '用户ID',
    `fans_user_id` bigint unsigned NOT NULL COMMENT '粉丝的用户ID',
    `create_time`  datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户粉丝表';

ALTER TABLE `data`.`t_following`
    ADD UNIQUE uk_user_id_following_user_id (user_id, following_user_id);
ALTER TABLE `data`.`t_following`
DROP KEY `idx_user_id`;

ALTER TABLE `data`.`t_fans`
    ADD UNIQUE uk_user_id_fans_user_id (user_id, fans_user_id);
ALTER TABLE `data`.`t_fans`
DROP KEY `idx_user_id`;

# 技术服务
CREATE TABLE `data`.`t_note_like`
(
    `id`          bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     bigint(11)          NOT NULL COMMENT '用户ID',
    `note_id`     bigint(11)          NOT NULL COMMENT '笔记ID',
    `create_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `status`      tinyint(2)          NOT NULL DEFAULT '0' COMMENT '点赞状态(0：取消点赞 1：点赞)',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_id_note_id` (`user_id`, `note_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='笔记点赞表';

CREATE TABLE `data`.`t_note_collection`
(
    `id`          bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     bigint(11)          NOT NULL COMMENT '用户ID',
    `note_id`     bigint(11)          NOT NULL COMMENT '笔记ID',
    `create_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `status`      tinyint(2)          NOT NULL DEFAULT '0' COMMENT '收藏状态(0：取消收藏 1：收藏)',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_id_note_id` (`user_id`, `note_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='笔记收藏表';

CREATE TABLE `data`.`t_note_count`
(
    `id`            bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `note_id`       bigint(11) unsigned NOT NULL COMMENT '笔记ID',
    `like_total`    bigint(11) DEFAULT '0' COMMENT '获得点赞总数',
    `collect_total` bigint(11) DEFAULT '0' COMMENT '获得收藏总数',
    `comment_total` bigint(11) DEFAULT '0' COMMENT '被评论总数',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_note_id` (`note_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='笔记计数表';

CREATE TABLE `data`.`t_user_count`
(
    `id`              bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`         bigint(11) unsigned NOT NULL COMMENT '用户ID',
    `fans_total`      bigint(11) DEFAULT '0' COMMENT '粉丝总数',
    `following_total` bigint(11) DEFAULT '0' COMMENT '关注总数',
    `note_total`      bigint(11) DEFAULT '0' COMMENT '发布笔记总数',
    `like_total`      bigint(11) DEFAULT '0' COMMENT '获得点赞总数',
    `collect_total`   bigint(11) DEFAULT '0' COMMENT '获得收藏总数',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户计数表';


#
# XXL-JOB v2.4.1
# Copyright (c) 2015-present, xuxueli.

CREATE database if NOT EXISTS `xxl_job` default character set utf8mb4 collate utf8mb4_unicode_ci;
use `xxl_job`;

SET NAMES utf8mb4;

CREATE TABLE `xxl_job_info`
(
    `id`                        int(11)      NOT NULL AUTO_INCREMENT,
    `job_group`                 int(11)      NOT NULL COMMENT '执行器主键ID',
    `job_desc`                  varchar(255) NOT NULL,
    `add_time`                  datetime              DEFAULT NULL,
    `update_time`               datetime              DEFAULT NULL,
    `author`                    varchar(64)           DEFAULT NULL COMMENT '作者',
    `alarm_email`               varchar(255)          DEFAULT NULL COMMENT '报警邮件',
    `schedule_type`             varchar(50)  NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
    `schedule_conf`             varchar(128)          DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
    `misfire_strategy`          varchar(50)  NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
    `executor_route_strategy`   varchar(50)           DEFAULT NULL COMMENT '执行器路由策略',
    `executor_handler`          varchar(255)          DEFAULT NULL COMMENT '执行器任务handler',
    `executor_param`            varchar(512)          DEFAULT NULL COMMENT '执行器任务参数',
    `executor_block_strategy`   varchar(50)           DEFAULT NULL COMMENT '阻塞处理策略',
    `executor_timeout`          int(11)      NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
    `executor_fail_retry_count` int(11)      NOT NULL DEFAULT '0' COMMENT '失败重试次数',
    `glue_type`                 varchar(50)  NOT NULL COMMENT 'GLUE类型',
    `glue_source`               mediumtext COMMENT 'GLUE源代码',
    `glue_remark`               varchar(128)          DEFAULT NULL COMMENT 'GLUE备注',
    `glue_updatetime`           datetime              DEFAULT NULL COMMENT 'GLUE更新时间',
    `child_jobid`               varchar(255)          DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
    `trigger_status`            tinyint(4)   NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
    `trigger_last_time`         bigint(13)   NOT NULL DEFAULT '0' COMMENT '上次调度时间',
    `trigger_next_time`         bigint(13)   NOT NULL DEFAULT '0' COMMENT '下次调度时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `xxl_job_log`
(
    `id`                        bigint(20) NOT NULL AUTO_INCREMENT,
    `job_group`                 int(11)    NOT NULL COMMENT '执行器主键ID',
    `job_id`                    int(11)    NOT NULL COMMENT '任务，主键ID',
    `executor_address`          varchar(255)        DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
    `executor_handler`          varchar(255)        DEFAULT NULL COMMENT '执行器任务handler',
    `executor_param`            varchar(512)        DEFAULT NULL COMMENT '执行器任务参数',
    `executor_sharding_param`   varchar(20)         DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
    `executor_fail_retry_count` int(11)    NOT NULL DEFAULT '0' COMMENT '失败重试次数',
    `trigger_time`              datetime            DEFAULT NULL COMMENT '调度-时间',
    `trigger_code`              int(11)    NOT NULL COMMENT '调度-结果',
    `trigger_msg`               text COMMENT '调度-日志',
    `handle_time`               datetime            DEFAULT NULL COMMENT '执行-时间',
    `handle_code`               int(11)    NOT NULL COMMENT '执行-状态',
    `handle_msg`                text COMMENT '执行-日志',
    `alarm_status`              tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
    PRIMARY KEY (`id`),
    KEY `I_trigger_time` (`trigger_time`),
    KEY `I_handle_code` (`handle_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `xxl_job_log_report`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `trigger_day`   datetime         DEFAULT NULL COMMENT '调度-时间',
    `running_count` int(11) NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
    `suc_count`     int(11) NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
    `fail_count`    int(11) NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
    `update_time`   datetime         DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `xxl_job_logglue`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `job_id`      int(11)      NOT NULL COMMENT '任务，主键ID',
    `glue_type`   varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
    `glue_source` mediumtext COMMENT 'GLUE源代码',
    `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
    `add_time`    datetime    DEFAULT NULL,
    `update_time` datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `xxl_job_registry`
(
    `id`             int(11)      NOT NULL AUTO_INCREMENT,
    `registry_group` varchar(50)  NOT NULL,
    `registry_key`   varchar(255) NOT NULL,
    `registry_value` varchar(255) NOT NULL,
    `update_time`    datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `i_g_k_v` (`registry_group`, `registry_key`, `registry_value`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `xxl_job_group`
(
    `id`           int(11)     NOT NULL AUTO_INCREMENT,
    `app_name`     varchar(64) NOT NULL COMMENT '执行器AppName',
    `title`        varchar(12) NOT NULL COMMENT '执行器名称',
    `address_type` tinyint(4)  NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
    `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
    `update_time`  datetime             DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `xxl_job_user`
(
    `id`         int(11)     NOT NULL AUTO_INCREMENT,
    `username`   varchar(50) NOT NULL COMMENT '账号',
    `password`   varchar(50) NOT NULL COMMENT '密码',
    `role`       tinyint(4)  NOT NULL COMMENT '角色：0-普通用户、1-管理员',
    `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
    PRIMARY KEY (`id`),
    UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `xxl_job_lock`
(
    `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
    PRIMARY KEY (`lock_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `xxl_job_group`(`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`)
VALUES (1, 'xxl-job-executor-sample', '示例执行器', 0, NULL, '2018-11-03 22:21:31');
INSERT INTO `xxl_job_info`(`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`,
                           `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`,
                           `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`,
                           `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`,
                           `child_jobid`)
VALUES (1, 1, '测试任务1', '2018-11-03 22:21:31', '2018-11-03 22:21:31', 'XXL', '', 'CRON', '0 0 0 * * ? *',
        'DO_NOTHING', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化',
        '2018-11-03 22:21:31', '');
INSERT INTO `xxl_job_user`(`id`, `username`, `password`, `role`, `permission`)
VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);
INSERT INTO `xxl_job_lock` (`lock_name`)
VALUES ('schedule_lock');

commit;


# 校验是否开启binlog
show VARIABLES like 'log_bin';

CREATE USER canal IDENTIFIED BY 'canal';
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ;
FLUSH PRIVILEGES;


CREATE TABLE `data`.`t_comment`
(
    `id`               bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `note_id`          bigint(20) unsigned NOT NULL COMMENT '关联的笔记ID',
    `user_id`          bigint(20) unsigned NOT NULL COMMENT '发布者用户ID',
    `content_uuid`     varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '评论内容UUID',
    `is_content_empty` bit(1)              NOT NULL                                 DEFAULT b'0' COMMENT '内容是否为空(0：不为空 1：为空)',
    `image_url`        varchar(60)         NOT NULL                                 DEFAULT '' COMMENT '评论附加图片URL',
    `level`            tinyint(2)          NOT NULL                                 DEFAULT '1' COMMENT '级别(1：一级评论 2：二级评论)',
    `reply_total`      bigint(20) unsigned                                          DEFAULT 0 COMMENT '评论被回复次数，仅一级评论需要',
    `like_total`       bigint(20)                                                   DEFAULT 0 COMMENT '评论被点赞次数',
    `parent_id`        bigint(20) unsigned                                          DEFAULT 0 COMMENT '父ID (若是对笔记的评论，则此字段存储笔记ID; 若是二级评论，则此字段存储一级评论的ID)',
    `reply_comment_id` bigint(20) unsigned                                          DEFAULT 0 COMMENT '回复哪个的评论 (0表示是对笔记的评论，若是对他人评论的回复，则存储回复评论的ID)',
    `reply_user_id`    bigint(20) unsigned                                          DEFAULT 0 COMMENT '回复的哪个用户, 存储用户ID',
    `is_top`           tinyint(2)          NOT NULL                                 DEFAULT '0' COMMENT '是否置顶(0：不置顶 1：置顶)',
    `create_time`      datetime            NOT NULL                                 DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime            NOT NULL                                 DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_note_id` (`note_id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_parent_id` (`parent_id`) USING BTREE,
    KEY `idx_create_time` (`create_time`) USING BTREE,
    KEY `idx_reply_comment_id` (`reply_comment_id`) USING BTREE,
    KEY `idx_reply_user_id` (`reply_user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '评论表';

CREATE TABLE `t_comment_like`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     bigint          NOT NULL COMMENT '用户ID',
    `comment_id`  bigint          NOT NULL COMMENT '评论ID',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id_comment_id` (`user_id`, `comment_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='评论点赞表';

INSERT INTO `leaf`.`leaf_alloc` (`biz_tag`, `max_id`, `step`, `description`, `update_time`)
VALUES ('leaf-segment-comment-id', 1, 2000, '评论 ID', NOW());


alter table t_comment
    add column `child_comment_total` bigint(20) unsigned DEFAULT '0' COMMENT '二级评论总数（只有一级评论才需要统计）' after `reply_total`;
ALTER TABLE t_comment
    ADD COLUMN heat DECIMAL(10, 2) DEFAULT 0 COMMENT '评论热度' after `is_top`;
alter table t_comment
    add column first_reply_comment_id bigint(20) unsigned default 0 COMMENT '最早回复的评论ID (只有一级评论需要)' after `heat`;

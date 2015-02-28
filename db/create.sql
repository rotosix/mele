#用户信息表
create TABLE `secret`.`user_info_0` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
    `password` VARCHAR(255) NOT NULL COMMENT '对用户明文密码进行MD5加密后字符串作为密码',
    `mobilePhone` VARCHAR(255) NOT NULL COMMENT '手机号码，采用E164标准格式，如+8613521610473',
    `gender` INT NOT NULL COMMENT '用户性别，1：男；2：女',
    `pushToken` VARCHAR(255) NOT NULL COMMENT '消息推送pushToken',
    `extendInfo` VARCHAR(1024) NULL COMMENT '扩展信息，JSON格式',
    `status` INT NOT NULL COMMENT '用户状态，0：新注册；1：激活（至少3个以上好友）；2：被举报；3：被删除',
    `level` INT NOT NULL COMMENT '用户级别，0：新注册',
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_user_info_0_uid` USING BTREE (`uid` ASC)
)  ENGINE=MyISAM COMMENT='用户信息表';

#手机号码映射表
create TABLE `secret`.`user_phone_0` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `mobilePhone` VARCHAR(255) NOT NULL COMMENT '手机号码，采用E164标准格式，如+8613521610473',
    `uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_user_phone_0_mobilePhone` USING BTREE (`mobilePhone` ASC)
)  ENGINE=MyISAM COMMENT='用户手机号码映射表';

#朋友关系记录表
create TABLE `secret`.`user_contact_0` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
	`uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
	`num` INT NOT NULL COMMENT '朋友和友友总数',
	`friend` TEXT NULL COMMENT '朋友列表，数组形式',
	`fof` TEXT NULL COMMENT '友友列表，数组形式',
	`createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_user_contact_0_uid` USING BTREE (`uid` ASC)
)  ENGINE=MyISAM COMMENT='朋友关系记录表';


#手机通讯录记录表
create TABLE `secret`.`user_phonebook_0` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
    `mobilePhone` VARCHAR(255) NOT NULL COMMENT '手机号码，采用E164标准格式，如+8613521610473',
    `phonebook` TEXT NULL COMMENT '联系人信息，JSON格式',
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_user_phonebook_0_uid` USING BTREE (`uid` ASC)
)  ENGINE=MyISAM COMMENT='手机通讯录记录表';


#手机号码存放映射表（手机号码在哪些用户的手机上有存储）
create TABLE `secret`.`phone_locate_0` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `mobilePhone` VARCHAR(255) NOT NULL COMMENT '手机号码，采用E164标准格式，如+8613521610473',
    `uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_phone_locate_0_mobilePhone` USING BTREE (`mobilePhone` ASC)
)  ENGINE=MyISAM COMMENT='手机号码存放映射表';


#feed内容信息记录表
create TABLE `secret`.`feed_detail_0` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
    `gender` INT NOT NULL COMMENT '用户性别，1：男；2：女',
    `rootId` VARCHAR(255) NOT NULL COMMENT '原帖feedId',  
    `parentId` VARCHAR(255) NOT NULL COMMENT '父贴feedId',  
    `feedId` VARCHAR(255) NOT NULL COMMENT '帖子feedId',  
    `feedType` INT NOT NULL COMMENT '帖子类型，1：原帖；2：赞；3：评论',
    `secret` INT NOT NULL COMMENT '是否匿名，0：公开；1：匿名', 
    `content` VARCHAR(1024) NOT NULL COMMENT '帖子的内容，JSON格式', 
    `position` VARCHAR(255) NULL COMMENT 'feed发布的地理位置', 
    `status` INT NOT NULL COMMENT '帖子状态，0：正常；1：被举报；2：被删除', 
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_feed_detail_0_rootId` USING BTREE (`rootId` ASC)
)  ENGINE=MyISAM COMMENT='feed内容信息记录表';

#用户feed信息记录表
create TABLE `secret`.`feed_user_0` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
    `gender` INT NOT NULL COMMENT '用户性别，1：男；2：女',
    `rootId` VARCHAR(255) NOT NULL COMMENT '原帖feedId',  
    `parentId` VARCHAR(255) NOT NULL COMMENT '父贴feedId',  
    `feedId` VARCHAR(255) NOT NULL COMMENT '帖子feedId',  
    `feedType` INT NOT NULL COMMENT '帖子类型，1：原帖；2：赞；3：评论',
    `secret` INT NOT NULL COMMENT '是否匿名，0：公开；1：匿名', 
    `content` VARCHAR(1024) NOT NULL COMMENT '帖子的内容，JSON格式', 
    `position` VARCHAR(255) NULL COMMENT 'feed发布的地理位置', 
    `status` INT NOT NULL COMMENT '帖子状态，0：正常；1：被举报；2：被删除', 
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_feed_user_0_uid` USING BTREE (`uid` ASC)
)  ENGINE=MyISAM COMMENT='用户feed信息记录表';

#feed关系信息记录表
create TABLE `secret`.`feed_relation_0` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
    `gender` INT NOT NULL COMMENT '用户性别，1：男；2：女',
    `rootId` VARCHAR(255) NOT NULL COMMENT '原帖feedId',  
    `parentId` VARCHAR(255) NOT NULL COMMENT '父贴feedId',  
    `feedId` VARCHAR(255) NOT NULL COMMENT '帖子feedId',  
    `feedType` INT NOT NULL COMMENT '帖子类型，1：原帖；2：赞；3：评论',
    `secret` INT NOT NULL COMMENT '是否匿名，0：公开；1：匿名', 
    `content` VARCHAR(1024) NOT NULL COMMENT '帖子的内容，JSON格式', 
    `position` VARCHAR(255) NULL COMMENT 'feed发布的地理位置', 
    `status` INT NOT NULL COMMENT '帖子状态，0：正常；1：被举报；2：被删除', 
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_feed_relation_0_feedId` USING BTREE (`feedId` ASC)
)  ENGINE=MyISAM COMMENT='feed关系信息记录表';

#feed分享信息记录表
create TABLE `secret`.`feed_share_info_0` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `creator` VARCHAR(255) NOT NULL COMMENT '创建分享链接的用户标识',
    `feedId` VARCHAR(255) NOT NULL COMMENT '需要分享的feedId', 
    `sourceId` VARCHAR(255) NOT NULL COMMENT '分享渠道标识，0：短信；1：Email；2：Facebook；3：Twitter；4：Line；5：Mixi',
    `originLink` VARCHAR(255) NOT NULL COMMENT '原始链接',
    `shortLink` VARCHAR(255) NOT NULL COMMENT '短链接', 
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_feed_share_info_0_creator` USING BTREE (`creator` ASC)
)  ENGINE=MyISAM COMMENT='feed分享信息记录表';

#私信发送记录表
create TABLE `secret`.`secret_message_send_0` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `sender` VARCHAR(255) NOT NULL COMMENT '私信发送者用户标识',
    `receiver` VARCHAR(255) NOT NULL COMMENT '私信接收者用户标识',
    `messageType` INT NOT NULL COMMENT '私信消息类型，1：文本；2：语音；3：图片；4：视频；5：图文链接', 
    `content` VARCHAR(1024) NOT NULL COMMENT '消息内容，JSON格式',
    `status` INT NOT NULL COMMENT '私信状态，0：正常；1：被删除；2：被召回',
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_secret_message_send_0_sender` USING BTREE (`sender` ASC)
)  ENGINE=MyISAM COMMENT='私信发送记录表';

#私信接收记录表
create TABLE `secret`.`secret_message_receive_0` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `sender` VARCHAR(255) NOT NULL COMMENT '私信发送者用户标识',
    `receiver` VARCHAR(255) NOT NULL COMMENT '私信接收者用户标识',
    `messageType` INT NOT NULL COMMENT '私信消息类型，1：文本；2：语音；3：图片；4：视频；5：图文链接', 
    `content` VARCHAR(1024) NOT NULL COMMENT '消息内容，JSON格式',
    `status` INT NOT NULL COMMENT '私信状态，0：正常；1：被删除；2：被召回',
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_secret_message_receive_0_receiver` USING BTREE (`receiver` ASC)
)  ENGINE=MyISAM COMMENT='私信接收记录表';
    
#上传文件记录表
create TABLE `secret`.`upload_record_0` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
    `uid` VARCHAR(255) NOT NULL COMMENT '用户标识',
    `fileType` INT NOT NULL COMMENT '上传文件类型，1：普通数据文件；2：图片；3：语音；4：视频', 
    `module` VARCHAR(255) NULL COMMENT '上传的业务模块，"feedBackground"：feed背景',
    `description` VARCHAR(1024) NULL COMMENT '描述信息，便于服务器了解上传的原因。可以是客户端版本号加上其他说明信息。',
    `imageSize` VARCHAR(255) NULL COMMENT '上传的图片规格，格式1024*768', 
    `originFileName` VARCHAR(255) NOT NULL COMMENT '上传的文件名',
    `downloadUrl` VARCHAR(255) NOT NULL COMMENT '服务器下载地址url',
    `createTime` INT NOT NULL COMMENT '创建时间，单位秒',
    `updateTime` INT NOT NULL COMMENT '更新时间，单位秒',
    PRIMARY KEY (`id`),
    INDEX `idx_upload_record_0_uid` USING BTREE (`uid` ASC)
)  ENGINE=MyISAM COMMENT='上传文件记录表';
    

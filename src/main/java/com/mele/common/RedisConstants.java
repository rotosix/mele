package com.mele.common;


public interface RedisConstants {
	
	/**
	 * 增加操作
	 */
	public static final int OPER_ADD = 0;
	/**
	 * 删除操作
	 */
	public static final int OPER_DELETE = 1;
	/**
	 * 清空操作
	 */
	public static final int OPER_CLEAR= 2;

	 /**
     * 用户被禁止
     */
    public static final int USER_FORBIDDEN_STATUS= 7;

    /*
     * 碰碰小助手uid
     * 
     */
    public static final String PENGPENG_ASSISTANT_UID = "10001";
    public static final String PENGPENG_ASSISTANT_INVITECODE = "D89F3A";

    /**
     * message type
     */
    public static final String CHAT_MESSAGE_TYPE_POINT = "6";

    /**
	 * 默认的编码
	 */
	public static final String CHARACTER_ENCODING = "UTF-8";

	/**
	 * 1：男；2：女；0：未知
	 */
	public static final Integer GAME_PROFILE_MALE = 1;
	public static final Integer GAME_PROFILE_FEMALE = 2;
    public static final Integer GAME_PROFILE_UNMALE = 0;

    /**
     * 设备类型
     */
    public static final Integer DEVICE_TYPE_ANDROID = 1;
    public static final Integer DEVICE_TYPE_IOS = 2;
    public static final Integer DEVICE_TYPE_WINDOWS = 0;    

    public static final Integer ACCOUNT_CATEGORY_USER_NOACTIVE = 0;
    public static final Integer ACCOUNT_CATEGORY_USER_NORMAL = 1;
    public static final Integer ACCOUNT_CATEGORY_USER_FORBIDDEN = 7;
    public static final Integer ACCOUNT_CATEGORY_USER_PUBLIC = 4;
    public static final Integer ACCOUNT_CATEGORY_OFFICIAL_ADMIN_ASSISTANT = 100;
    public static final Integer ACCOUNT_CATEGORY_OFFICIAL_OPS_SYSTEM = 101;
    public static final Integer ACCOUNT_CATEGORY_OFFICIAL_OPS_ROBOT = 102;
    public static final Integer ACCOUNT_CATEGORY_OFFICIAL_OPS_PERSON = 103;
    public static final Integer ACCOUNT_CATEGORY_OFFICIAL_OPS_ACTIVITY = 104;
    public static final Integer ACCOUNT_CATEGORY_OFFICIAL_PUBLIC_JINGLING = 105;
    public static final Integer ACCOUNT_CATEGORY_OFFICIAL_PUBLIC_OTHER = 106;

	public static final String SC_UNDEFINED = "N000000"; //接口成功返回数据
	public static final String SC_SUCCESS_NODATA = "N000001"; //接口成功但是无数据
	public static final String SC_UNKNOW_ERROR = "E000000"; // 系统设计之外的未知错误
	public static final String SC_PARAMETER_INVALID = "E000002"; // 客户端请求参数无效
	public static final String SC_USERPROFILE_ISNEW = "N000003"; // 用户资料不需要拉取
    public static final String SC_SERVICE_NOT_AVAILABLE = "E000003"; // 服务不可用
    public static final String SC_DB_RUNTIME_EXCEPTION = "E000004"; // 数据库运行异常

	/**
	 * 上传
	 */
    public static final String SC_UPLOAD_CLOUD_STORE_FAIL = "E000100"; // 上传到云端失败
    public static final String SC_UPLOAD_SCALE_IMAGE_FAIL = "E000101"; // 图片上传到云端后缩放失败
    public static final String SC_UPLOAD_NOTIFY_OTHER_FAIL = "E000102"; // 上传成功后更新对应模块数据失败  

    /**
     * 账号
     */
    public static final String SC_ACCOUNT_MOBILE_PHONE_REPETITION = "E000200"; // 手机已经注册
    public static final String SC_ACCOUNT_WEIBO_REPETITION = "E000201"; // 微博已经绑定
    public static final String SC_ACCOUNT_QQ_REPETITION = "E000202"; // QQ已经绑定  
    public static final String SC_ACCOUNT_CREATE_FAIL = "E000203"; // 账号创建失败
    public static final String SC_ACCOUNT_USER_NOT_EXIST = "E000204"; // 用户不存在
    public static final String SC_ACCOUNT_USER_PASSWORD_NOTMATCH = "E000205"; // 账号或者密码错误  
    public static final String SC_ACCOUNT_CHANGE_PASSWORD_FAIL = "E000206"; // 修改密码失败  
    public static final String SC_ACCOUNT_FIND_PASSWORD_FAIL = "E000207"; // 找回密码失败
    public static final String SC_ACCOUTN_VERIFY_CODE_EXPIRE = "E000208"; // 验证码已经过期
    public static final String SC_ACCOUTN_VERIFY_CODE_NOTMATCH = "E000209"; // 验证码不匹配  
    public static final String SC_ACCOUTN_USER_TOKEN_NOTMATCH = "E000210"; // 用户token不匹配  
    public static final String SC_ACCOUTN_USER_IS_FORBIDDEN = "E000211"; // 用户被禁止 
    public static final String SC_ACCOUTN_USER_NAME_FORBIDDEN = "E000212"; // 用户名被禁止 
    public static final String SC_ACCOUNT_RENREN_REPETITION = "E000213"; // 微博已经绑定

    /**
     * 短信
     */
    public static final String SC_SMS_VERIFY_CODE_EXPIRE = "E000300"; // 验证码已经过期
    public static final String SC_SMS_VERIFY_CODE_NOTMATCH = "E000301"; // 验证码不匹配

    /**
     * 邀请码
     */
    public static final String SC_INVITE_USER_LEVEL_LOW = "E000400"; // 用户级别太低
    public static final String SC_INVITE_CODE_EXPIRE = "E000401"; // 邀请码已经过期
    public static final String SC_INVITE_CODE_NOTMATCH = "E000402"; // 邀请码不匹配
    public static final String SC_INVITE_CODE_USER_NOTACTIVE = "E000403"; // 用户未激活

    /**
     * 游戏
     */
    public static final String SC_GAME_CREATE_FAIL = "E000500"; // 用户级别太低
    public static final String SC_GAME_NOT_EXIST = "E000501"; // 游戏不存在
    public static final String SC_GAME_IS_OVER = "E000502"; // 游戏已经结束
    public static final String SC_GAME_IS_NEWEST = "E000503"; // 游戏题目已经是最新的
    public static final String SC_GAME_IS_FREQUENT= "E000504"; // 游戏创建次数频繁

    /**
     * 机器人
     */
    public static final String SC_ROBOT_IS_EXIST = "E000600"; // 该账号的机器人已经存在

    /**
     * 群
     */
    public static final String SC_GROUP_USEROVERLMIT = "E000701"; // 群用户数超过限制

    /**
     * 游戏档案
     */
    public static final String SC_GAME_PROFILE_IS_NEWEST = "E000801"; // 性格测试评分已经是最新的

    /**
     * feed
     */
    public static final String SC_FEED_GAME_PKED = "E000900"; // feed已经pk评论过

    /**
     * 预留给客户端使用
     */
    public static final String SC_CLIENT_RESERVED_START = "E001000"; // 客户端预留起始错误码
    public static final String SC_CLIENT_RESERVED_END = "E001099"; // 客户端预留结束错误码
 
    /**
     * 链接安全认证
     */
    public static final String SC_LINK_AUTH_CERATE_SIGN_FAIL = "E002000"; // 创建安全校验码失败
    public static final String SC_LINK_AUTH_VERIFY_SIGN_FAIL = "E0020001"; // 安全校验码认证失败
    public static final String SC_LINK_AUTH_PARSE_SIGN_FAIL = "E0020002"; // 安全校验码认证失败
    
    /**
     * 用户
     */
	public static final String SC_USER_NOTFOUND = "E100002"; // 用户不存在
	/**
     * 朋友
     */
	public static final String SC_CONTACTSID_NOTFOUND = "E200002"; // 用户不存在
	/**
	 *
	 */
	public static final String SC_GAME_MPT_REPEAT_SUBMIT="E002100";//连环题游戏   结果重复提交
		
	
}

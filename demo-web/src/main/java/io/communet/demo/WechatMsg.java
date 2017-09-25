package io.communet.demo;


import java.io.Serializable;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/7/28
 * <p>Version: 1.0
 */
public class WechatMsg extends MsgBase implements Serializable{
    private static final long serialVersionUID = -615160665998607887L;
    private String talker;//发言人
    private long createTime;//发送时间 1500954322000 13位
    private String content;//文本内容
    private String filePath;//文件路径，即当发送的是图片或视频或表情时，此文件在安卓手机端的路径
    private String fileUrl;// 文件URL,即当发送的文件在微信服务器上有URL，我们先传文件URL，目前自定义表情发送URL
    private int msgTye;// 0:系统消息（自定义），1:文本，或系统自带表情 ;  3: 图片 ; 34: 音频 ; 43: 视频  ; 47: 自定义表情或专辑表情
    private String chatRoomId;// 群ID

    public String getTalker() {
        return talker;
    }

    public void setTalker(String talker) {
        this.talker = talker;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getMsgTye() {
        return msgTye;
    }

    public void setMsgTye(int msgTye) {
        this.msgTye = msgTye;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}

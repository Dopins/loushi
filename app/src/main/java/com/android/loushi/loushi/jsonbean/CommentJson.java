package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class CommentJson  implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":31,"cDate":"2008-08-09 23:22:24","userID":0,"userInfo":{"nickname":"阿瑟斯","headImgUrl":"http://23.jpg"},"replyedID":22,"replyedInfo":{"nickname":"小刀","headImgUrl":"http://23.jpg"},"type":4,"pid":0,"pidImgUrl":"http://loushi.com/jsadj.png","content":"是的"},{"id":222,"cDate":"2008-08-09 23:22:24","userID":0,"userInfo":{"nickname":"艾奥瓦","headImgUrl":"http://23.jpg"},"replyedID":22,"replyedInfo":{"nickname":"小刀","headImgUrl":"http://23.jpg"},"type":4,"pid":0,"pidImgUrl":"http://loushi.com/jsadj.png","content":"呵呵"}]
     */

    private boolean state;
    private String code;
    private String return_info;
    /**
     * id : 31
     * cDate : 2008-08-09 23:22:24
     * userID : 0
     * userInfo : {"nickname":"阿瑟斯","headImgUrl":"http://23.jpg"}
     * replyedID : 22
     * replyedInfo : {"nickname":"小刀","headImgUrl":"http://23.jpg"}
     * type : 4
     * pid : 0
     * pidImgUrl : http://loushi.com/jsadj.png
     * content : 是的
     */

    private List<BodyBean> body;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String return_info) {
        this.return_info = return_info;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean  implements Serializable {
        private int id;
        private String cDate;
        private int userID;
        /**
         * nickname : 阿瑟斯
         * headImgUrl : http://23.jpg
         */

        private UserInfoBean userinfo;
        private int replyedID;
        /**
         * nickname : 小刀
         * headImgUrl : http://23.jpg
         */

        private ReplyedInfoBean replyedInfo;
        private int type;
        private int pid;
        private String pidImgUrl;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCDate() {
            return cDate;
        }

        public void setCDate(String cDate) {
            this.cDate = cDate;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public UserInfoBean getUserInfo() {
            return userinfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userinfo = userInfo;
        }

        public int getReplyedID() {
            return replyedID;
        }

        public void setReplyedID(int replyedID) {
            this.replyedID = replyedID;
        }

        public ReplyedInfoBean getReplyedInfo() {
            return replyedInfo;
        }

        public void setReplyedInfo(ReplyedInfoBean replyedInfo) {
            this.replyedInfo = replyedInfo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getPidImgUrl() {
            return pidImgUrl;
        }

        public void setPidImgUrl(String pidImgUrl) {
            this.pidImgUrl = pidImgUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public static class UserInfoBean {
            private String nickname;
            private String headImgUrl;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }
        }

        public static class ReplyedInfoBean  implements Serializable {
            private String nickname;
            private String headImgUrl;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }
        }
    }
}

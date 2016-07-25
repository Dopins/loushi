package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/24.
 */
public class UserInfoJson  implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : {"nickname":"昵称","mobilePhone":"110","email":"111@111.com","headImgUrl":"头像链接","schoolName":"","sex":0,"messageCount":7,"userID":22}
     */

    private int state;
    private String code;
    private String return_info;
    /**
     * nickname : 昵称
     * mobilePhone : 110
     * email : 111@111.com
     * headImgUrl : 头像链接
     * schoolName :
     * sex : 0
     * messageCount : 7
     * userID : 22
     */

    private BodyBean body;

    public int getState() {
        return state;
    }

    public void setState(int state) {
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

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean implements Serializable  {
        private String nickname;
        private String mobilePhone;
        private String email;
        private String headImgUrl;
        private String schoolName;
        private int sex;
        private int messageCount;
        private int userID;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadImgUrl() {
            return headImgUrl;
        }

        public void setHeadImgUrl(String headImgUrl) {
            this.headImgUrl = headImgUrl;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(int messageCount) {
            this.messageCount = messageCount;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }
    }
}

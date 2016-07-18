package com.android.loushi.loushi.json;

/**
 * Created by Administrator on 2016/4/21.
 */
public class UserInfoJson {

    /**
     * email :
     * headImgUrl : http://qzapp.qlogo.cn/qzapp/1105313206/77C68A32A7C9536F276BAB79BCF29F79/100
     * id : 9
     * messageCount : 0
     * mobilePhone : 13750065624
     * nickname :    idiot.
     * schoolName : 华工
     * sex : false
     * userID : 9
     */

    private BodyBean body;
    /**
     * body : {"email":"","headImgUrl":"http://qzapp.qlogo.cn/qzapp/1105313206/77C68A32A7C9536F276BAB79BCF29F79/100","id":9,"messageCount":0,"mobilePhone":"13750065624","nickname":"   idiot.","schoolName":"华工","sex":false,"userID":9}
     * code : null
     * return_info : null
     * state : true
     */

    private String code;
    private String return_info;
    private boolean state;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String return_info) {
        this.return_info = return_info;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public static class BodyBean {
        private String email;
        private String headImgUrl;
        private int id;
        private int messageCount;
        private String mobilePhone;
        private String nickname;
        private String schoolName;
        private boolean sex;
        private int userID;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(int messageCount) {
            this.messageCount = messageCount;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }
    }
}

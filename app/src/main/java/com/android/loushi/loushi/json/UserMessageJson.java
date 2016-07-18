package com.android.loushi.loushi.json;

import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public class UserMessageJson {

    /**
     * body : [{"cDate":"2016-05-09T22:37:58","comment":{"cDate":"2016-05-09T22:37:57","content":"不可以","id":11,"pid":2,"pidImgUrl":"http://119.29.187.58:8080/loushi/scene/1/main.jpeg","replyedID":32,"replyedInfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605092044029065574803.jpg","id":32,"messageCount":0,"mobilePhone":null,"nickname":"mtf","schoolName":null,"sex":false,"userID":0},"type":0,"userID":16,"userinfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605101208205030909506.jpg","id":16,"messageCount":0,"mobilePhone":null,"nickname":"何翔","schoolName":null,"sex":false,"userID":0}},"commentID":11,"id":3},{"cDate":"2016-05-09T22:37:58","comment":{"cDate":"2016-05-09T22:37:58","content":"不可以","id":12,"pid":2,"pidImgUrl":"http://119.29.187.58:8080/loushi/scene/1/main.jpeg","replyedID":32,"replyedInfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605092044029065574803.jpg","id":32,"messageCount":0,"mobilePhone":null,"nickname":"mtf","schoolName":null,"sex":false,"userID":0},"type":0,"userID":16,"userinfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605101208205030909506.jpg","id":16,"messageCount":0,"mobilePhone":null,"nickname":"何翔","schoolName":null,"sex":false,"userID":0}},"commentID":12,"id":4},{"cDate":"2016-05-09T22:37:58","comment":{"cDate":"2016-05-09T22:37:58","content":"不可以","id":13,"pid":2,"pidImgUrl":"http://119.29.187.58:8080/loushi/scene/1/main.jpeg","replyedID":32,"replyedInfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605092044029065574803.jpg","id":32,"messageCount":0,"mobilePhone":null,"nickname":"mtf","schoolName":null,"sex":false,"userID":0},"type":0,"userID":16,"userinfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605101208205030909506.jpg","id":16,"messageCount":0,"mobilePhone":null,"nickname":"何翔","schoolName":null,"sex":false,"userID":0}},"commentID":13,"id":5},{"cDate":"2016-05-09T22:37:57","comment":{"cDate":"2016-05-09T22:37:57","content":"不可以","id":10,"pid":2,"pidImgUrl":"http://119.29.187.58:8080/loushi/scene/1/main.jpeg","replyedID":32,"replyedInfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605092044029065574803.jpg","id":32,"messageCount":0,"mobilePhone":null,"nickname":"mtf","schoolName":null,"sex":false,"userID":0},"type":0,"userID":16,"userinfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605101208205030909506.jpg","id":16,"messageCount":0,"mobilePhone":null,"nickname":"何翔","schoolName":null,"sex":false,"userID":0}},"commentID":10,"id":1},{"cDate":"2016-05-09T22:37:57","comment":{"cDate":"2016-05-09T22:37:56","content":"不可以","id":9,"pid":2,"pidImgUrl":"http://119.29.187.58:8080/loushi/scene/1/main.jpeg","replyedID":32,"replyedInfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605092044029065574803.jpg","id":32,"messageCount":0,"mobilePhone":null,"nickname":"mtf","schoolName":null,"sex":false,"userID":0},"type":0,"userID":16,"userinfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605101208205030909506.jpg","id":16,"messageCount":0,"mobilePhone":null,"nickname":"何翔","schoolName":null,"sex":false,"userID":0}},"commentID":9,"id":2}]
     * code : null
     * return_info : null
     * state : true
     */

    private Object code;
    private Object return_info;
    private boolean state;
    /**
     * cDate : 2016-05-09T22:37:58
     * comment : {"cDate":"2016-05-09T22:37:57","content":"不可以","id":11,"pid":2,"pidImgUrl":"http://119.29.187.58:8080/loushi/scene/1/main.jpeg","replyedID":32,"replyedInfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605092044029065574803.jpg","id":32,"messageCount":0,"mobilePhone":null,"nickname":"mtf","schoolName":null,"sex":false,"userID":0},"type":0,"userID":16,"userinfo":{"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605101208205030909506.jpg","id":16,"messageCount":0,"mobilePhone":null,"nickname":"何翔","schoolName":null,"sex":false,"userID":0}}
     * commentID : 11
     * id : 3
     */

    private List<BodyBean> body;

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getReturn_info() {
        return return_info;
    }

    public void setReturn_info(Object return_info) {
        this.return_info = return_info;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        private String cDate;
        /**
         * cDate : 2016-05-09T22:37:57
         * content : 不可以
         * id : 11
         * pid : 2
         * pidImgUrl : http://119.29.187.58:8080/loushi/scene/1/main.jpeg
         * replyedID : 32
         * replyedInfo : {"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605092044029065574803.jpg","id":32,"messageCount":0,"mobilePhone":null,"nickname":"mtf","schoolName":null,"sex":false,"userID":0}
         * type : 0
         * userID : 16
         * userinfo : {"email":null,"headImgUrl":"http://119.29.187.58:8080/loushi/headImg/201605101208205030909506.jpg","id":16,"messageCount":0,"mobilePhone":null,"nickname":"何翔","schoolName":null,"sex":false,"userID":0}
         */

        private CommentBean comment;
        private int commentID;
        private int id;

        public String getCDate() {
            return cDate;
        }

        public void setCDate(String cDate) {
            this.cDate = cDate;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public int getCommentID() {
            return commentID;
        }

        public void setCommentID(int commentID) {
            this.commentID = commentID;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static class CommentBean {
            private String cDate;
            private String content;
            private int id;
            private int pid;
            private String pidImgUrl;
            private int replyedID;
            /**
             * email : null
             * headImgUrl : http://119.29.187.58:8080/loushi/headImg/201605092044029065574803.jpg
             * id : 32
             * messageCount : 0
             * mobilePhone : null
             * nickname : mtf
             * schoolName : null
             * sex : false
             * userID : 0
             */

            private ReplyedInfoBean replyedInfo;
            private int type;
            private int userID;
            /**
             * email : null
             * headImgUrl : http://119.29.187.58:8080/loushi/headImg/201605101208205030909506.jpg
             * id : 16
             * messageCount : 0
             * mobilePhone : null
             * nickname : 何翔
             * schoolName : null
             * sex : false
             * userID : 0
             */

            private UserinfoBean userinfo;

            public String getCDate() {
                return cDate;
            }

            public void setCDate(String cDate) {
                this.cDate = cDate;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
            }

            public UserinfoBean getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserinfoBean userinfo) {
                this.userinfo = userinfo;
            }

            public static class ReplyedInfoBean {
                private Object email;
                private String headImgUrl;
                private int id;
                private int messageCount;
                private Object mobilePhone;
                private String nickname;
                private Object schoolName;
                private boolean sex;
                private int userID;

                public Object getEmail() {
                    return email;
                }

                public void setEmail(Object email) {
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

                public Object getMobilePhone() {
                    return mobilePhone;
                }

                public void setMobilePhone(Object mobilePhone) {
                    this.mobilePhone = mobilePhone;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public Object getSchoolName() {
                    return schoolName;
                }

                public void setSchoolName(Object schoolName) {
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

            public static class UserinfoBean {
                private Object email;
                private String headImgUrl;
                private int id;
                private int messageCount;
                private Object mobilePhone;
                private String nickname;
                private Object schoolName;
                private boolean sex;
                private int userID;

                public Object getEmail() {
                    return email;
                }

                public void setEmail(Object email) {
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

                public Object getMobilePhone() {
                    return mobilePhone;
                }

                public void setMobilePhone(Object mobilePhone) {
                    this.mobilePhone = mobilePhone;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public Object getSchoolName() {
                    return schoolName;
                }

                public void setSchoolName(Object schoolName) {
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
    }
}

package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class UserMessageJson  implements Serializable {
    /**
     * state : 1
     * body : [{"id":3,"cDate":"2008-08-09 23:22:24","commentID":34,"comment":{"id":3,"cDate":"2008-08-09 23:22:24","userID":123,"userInfo":{"nickname":"菜刀","headImgUrl":"http://23.jpg"},"replyed_id":22,"replyedInfo":{"nickname":"小刀","headImgUrl":"http://23.jpg"},"type":2,"pid":12,"pidImgUrl":"http://loushi.com/jsadj.png","content":"剁死你"}},{"id":3,"cDate":"2008-08-0923: 22: 24","commentID":34,"comment":{"id":31,"cDate":"2008-08-0923: 22: 24","userID":0,"userInfo":{"nickname":"陋室小秘书","headImgUrl":"http: //23.jpg"},"replyed_id":22,"replyedInfo":{"nickname":"小刀","headImgUrl":"http: //23.jpg"},"type":4,"pid":0,"pidImgUrl":"http: //loushi.com/jsadj.png","content":"感谢您的建议"}}]
     */

    private boolean state;
    /**
     * id : 3
     * cDate : 2008-08-09 23:22:24
     * commentID : 34
     * comment : {"id":3,"cDate":"2008-08-09 23:22:24","userID":123,"userInfo":{"nickname":"菜刀","headImgUrl":"http://23.jpg"},"replyed_id":22,"replyedInfo":{"nickname":"小刀","headImgUrl":"http://23.jpg"},"type":2,"pid":12,"pidImgUrl":"http://loushi.com/jsadj.png","content":"剁死你"}
     */

    private List<BodyBean> body;

    public boolean getState() {
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

    public static class BodyBean  implements Serializable {
        private int id;
        private String cDate;
        private int commentID;
        /**
         * id : 3
         * cDate : 2008-08-09 23:22:24
         * userID : 123
         * userInfo : {"nickname":"菜刀","headImgUrl":"http://23.jpg"}
         * replyed_id : 22
         * replyedInfo : {"nickname":"小刀","headImgUrl":"http://23.jpg"}
         * type : 2
         * pid : 12
         * pidImgUrl : http://loushi.com/jsadj.png
         * content : 剁死你
         */

        private CommentBean comment;

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

        public int getCommentID() {
            return commentID;
        }

        public void setCommentID(int commentID) {
            this.commentID = commentID;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public static class CommentBean  implements Serializable {
            private int id;
            private String cDate;
            private int userID;
            /**
             * nickname : 菜刀
             * headImgUrl : http://23.jpg
             */

            private UserInfoBean userinfo;
            private int replyed_id;
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

            public int getReplyed_id() {
                return replyed_id;
            }

            public void setReplyed_id(int replyed_id) {
                this.replyed_id = replyed_id;
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

            public static class UserInfoBean implements Serializable{
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

                @Override
                public String toString() {
                    return "UserInfoBean{" +
                            "nickname='" + nickname + '\'' +
                            ", headImgUrl='" + headImgUrl + '\'' +
                            '}';
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

                @Override
                public String toString() {
                    return "ReplyedInfoBean{" +
                            "nickname='" + nickname + '\'' +
                            ", headImgUrl='" + headImgUrl + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "CommentBean{" +
                        "id=" + id +
                        ", cDate='" + cDate + '\'' +
                        ", userID=" + userID +
                        ", userInfo=" + userinfo +
                        ", replyed_id=" + replyed_id +
                        ", replyedInfo=" + replyedInfo +
                        ", type=" + type +
                        ", pid=" + pid +
                        ", pidImgUrl='" + pidImgUrl + '\'' +
                        ", content='" + content + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "BodyBean{" +
                    "id=" + id +
                    ", cDate='" + cDate + '\'' +
                    ", commentID=" + commentID +
                    ", comment=" + comment.toString() +
                    '}';
        }
    }
}

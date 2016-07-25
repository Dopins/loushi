package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class TopicContentJson  implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":21,"sort":0,"type":2,"content":"neirong","topicID":22,"topic":{"id":21,"name":"好枕头","groupID":22,"topicGroup":{"id":22,"name":"枕头"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}},{"id":22,"sort":1,"type":2,"content":"neirong","topicID":22,"topic":{"id":21,"name":"好枕头","groupID":22,"topicGroup":{"id":22,"name":"枕头"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}}]
     */

    private int state;
    private String code;
    private String return_info;
    /**
     * id : 21
     * sort : 0
     * type : 2
     * content : neirong
     * topicID : 22
     * topic : {"id":21,"name":"好枕头","groupID":22,"topicGroup":{"id":22,"name":"枕头"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}
     */

    private List<BodyBean> body;

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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean  implements Serializable {
        private int id;
        private int sort;
        private int type;
        private String content;
        private int topicID;
        /**
         * id : 21
         * name : 好枕头
         * groupID : 22
         * topicGroup : {"id":22,"name":"枕头"}
         * collectionNum : 333
         * collected : 0
         * forwordNum : 12
         * commentNum : 6
         * imgUrl : asd
         */

        private TopicBean topic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getTopicID() {
            return topicID;
        }

        public void setTopicID(int topicID) {
            this.topicID = topicID;
        }

        public TopicBean getTopic() {
            return topic;
        }

        public void setTopic(TopicBean topic) {
            this.topic = topic;
        }

        public static class TopicBean  implements Serializable {
            private int id;
            private String name;
            private int groupID;
            /**
             * id : 22
             * name : 枕头
             */

            private TopicGroupBean topicGroup;
            private int collectionNum;
            private int collected;
            private int forwordNum;
            private int commentNum;
            private String imgUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGroupID() {
                return groupID;
            }

            public void setGroupID(int groupID) {
                this.groupID = groupID;
            }

            public TopicGroupBean getTopicGroup() {
                return topicGroup;
            }

            public void setTopicGroup(TopicGroupBean topicGroup) {
                this.topicGroup = topicGroup;
            }

            public int getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(int collectionNum) {
                this.collectionNum = collectionNum;
            }

            public int getCollected() {
                return collected;
            }

            public void setCollected(int collected) {
                this.collected = collected;
            }

            public int getForwordNum() {
                return forwordNum;
            }

            public void setForwordNum(int forwordNum) {
                this.forwordNum = forwordNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public static class TopicGroupBean  implements Serializable {
                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}

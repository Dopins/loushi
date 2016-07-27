package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class UserCollectionsJson  implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":22,"type":2,"pid":23,"scene":{"id":21,"name":"海贼宿舍","label":"海贼王","groupID":22,"sceneGroup":{"id":22,"name":"动漫风"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"},"strategy":{"id":22,"name":"名称","collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"},"topic":{"id":21,"name":"好枕头","groupID":22,"topicGroup":{"id":22,"name":"枕头"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"},"goods":{"id":21,"name":"无脸人台灯","price":33.21,"introduction":"介绍","url":"导流链接","images":[{"id":21,"sort":0,"url":"图片链接","type":1,"pid":2},{"id":23,"sort":1,"url":"图片链接","type":1,"pid":2}],"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6}}]
     */

    private boolean state;
    private String code;
    private String return_info;
    /**
     * id : 22
     * type : 2
     * pid : 23
     * scene : {"id":21,"name":"海贼宿舍","label":"海贼王","groupID":22,"sceneGroup":{"id":22,"name":"动漫风"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}
     * strategy : {"id":22,"name":"名称","collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}
     * topic : {"id":21,"name":"好枕头","groupID":22,"topicGroup":{"id":22,"name":"枕头"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}
     * goods : {"id":21,"name":"无脸人台灯","price":33.21,"introduction":"介绍","url":"导流链接","images":[{"id":21,"sort":0,"url":"图片链接","type":1,"pid":2},{"id":23,"sort":1,"url":"图片链接","type":1,"pid":2}],"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6}
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
        private int type;
        private int pid;
        /**
         * id : 21
         * name : 海贼宿舍
         * label : 海贼王
         * groupID : 22
         * sceneGroup : {"id":22,"name":"动漫风"}
         * collectionNum : 333
         * collected : 0
         * forwordNum : 12
         * commentNum : 6
         * imgUrl : asd
         */

//        private SceneBean scene;
//        /**
//         * id : 22
//         * name : 名称
//         * collectionNum : 333
//         * collected : 0
//         * forwordNum : 12
//         * commentNum : 6
//         * imgUrl : asd
//         */
//
//        private StrategyBean strategy;
//        /**
//         * id : 21
//         * name : 好枕头
//         * groupID : 22
//         * topicGroup : {"id":22,"name":"枕头"}
//         * collectionNum : 333
//         * collected : 0
//         * forwordNum : 12
//         * commentNum : 6
//         * imgUrl : asd
//         */
//
//        private TopicBean topic;
        /**
         * id : 21
         * name : 无脸人台灯
         * price : 33.21
         * introduction : 介绍
         * url : 导流链接
         * images : [{"id":21,"sort":0,"url":"图片链接","type":1,"pid":2},{"id":23,"sort":1,"url":"图片链接","type":1,"pid":2}]
         * collectionNum : 333
         * collected : 0
         * forwordNum : 12
         * commentNum : 6
         */

        private GoodsBean goods;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

//        public SceneBean getScene() {
//            return scene;
//        }
//
//        public void setScene(SceneBean scene) {
//            this.scene = scene;
//        }
//
//        public StrategyBean getStrategy() {
//            return strategy;
//        }
//
//        public void setStrategy(StrategyBean strategy) {
//            this.strategy = strategy;
//        }
//
//        public TopicBean getTopic() {
//            return topic;
//        }
//
//        public void setTopic(TopicBean topic) {
//            this.topic = topic;
//        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

//        public static class SceneBean  implements Serializable {
//            private int id;
//            private String name;
//            private String label;
//            private int groupID;
//            /**
//             * id : 22
//             * name : 动漫风
//             */
//
//            private SceneGroupBean sceneGroup;
//            private int collectionNum;
//            private boolean collected;
//            private int forwordNum;
//            private int commentNum;
//            private String imgUrl;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getLabel() {
//                return label;
//            }
//
//            public void setLabel(String label) {
//                this.label = label;
//            }
//
//            public int getGroupID() {
//                return groupID;
//            }
//
//            public void setGroupID(int groupID) {
//                this.groupID = groupID;
//            }
//
//            public SceneGroupBean getSceneGroup() {
//                return sceneGroup;
//            }
//
//            public void setSceneGroup(SceneGroupBean sceneGroup) {
//                this.sceneGroup = sceneGroup;
//            }
//
//            public int getCollectionNum() {
//                return collectionNum;
//            }
//
//            public void setCollectionNum(int collectionNum) {
//                this.collectionNum = collectionNum;
//            }
//
//            public boolean getCollected() {
//                return collected;
//            }
//
//            public void setCollected(boolean collected) {
//                this.collected = collected;
//            }
//
//            public int getForwordNum() {
//                return forwordNum;
//            }
//
//            public void setForwordNum(int forwordNum) {
//                this.forwordNum = forwordNum;
//            }
//
//            public int getCommentNum() {
//                return commentNum;
//            }
//
//            public void setCommentNum(int commentNum) {
//                this.commentNum = commentNum;
//            }
//
//            public String getImgUrl() {
//                return imgUrl;
//            }
//
//            public void setImgUrl(String imgUrl) {
//                this.imgUrl = imgUrl;
//            }
//
//            public static class SceneGroupBean  implements Serializable {
//                private int id;
//                private String name;
//
//                public int getId() {
//                    return id;
//                }
//
//                public void setId(int id) {
//                    this.id = id;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//            }
//        }
//
//        public static class StrategyBean implements Serializable  {
//            private int id;
//            private String name;
//            private int collectionNum;
//            private boolean collected;
//            private int forwordNum;
//            private int commentNum;
//            private String imgUrl;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public int getCollectionNum() {
//                return collectionNum;
//            }
//
//            public void setCollectionNum(int collectionNum) {
//                this.collectionNum = collectionNum;
//            }
//
//            public boolean getCollected() {
//                return collected;
//            }
//
//            public void setCollected(boolean collected) {
//                this.collected = collected;
//            }
//
//            public int getForwordNum() {
//                return forwordNum;
//            }
//
//            public void setForwordNum(int forwordNum) {
//                this.forwordNum = forwordNum;
//            }
//
//            public int getCommentNum() {
//                return commentNum;
//            }
//
//            public void setCommentNum(int commentNum) {
//                this.commentNum = commentNum;
//            }
//
//            public String getImgUrl() {
//                return imgUrl;
//            }
//
//            public void setImgUrl(String imgUrl) {
//                this.imgUrl = imgUrl;
//            }
//        }
//
//        public static class TopicBean implements Serializable  {
//            private int id;
//            private String name;
//            private int groupID;
//            /**
//             * id : 22
//             * name : 枕头
//             */
//
//            private TopicGroupBean topicGroup;
//            private int collectionNum;
//            private boolean collected;
//            private int forwordNum;
//            private int commentNum;
//            private String imgUrl;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public int getGroupID() {
//                return groupID;
//            }
//
//            public void setGroupID(int groupID) {
//                this.groupID = groupID;
//            }
//
//            public TopicGroupBean getTopicGroup() {
//                return topicGroup;
//            }
//
//            public void setTopicGroup(TopicGroupBean topicGroup) {
//                this.topicGroup = topicGroup;
//            }
//
//            public int getCollectionNum() {
//                return collectionNum;
//            }
//
//            public void setCollectionNum(int collectionNum) {
//                this.collectionNum = collectionNum;
//            }
//
//            public boolean getCollected() {
//                return collected;
//            }
//
//            public void setCollected(boolean collected) {
//                this.collected = collected;
//            }
//
//            public int getForwordNum() {
//                return forwordNum;
//            }
//
//            public void setForwordNum(int forwordNum) {
//                this.forwordNum = forwordNum;
//            }
//
//            public int getCommentNum() {
//                return commentNum;
//            }
//
//            public void setCommentNum(int commentNum) {
//                this.commentNum = commentNum;
//            }
//
//            public String getImgUrl() {
//                return imgUrl;
//            }
//
//            public void setImgUrl(String imgUrl) {
//                this.imgUrl = imgUrl;
//            }
//
//            public static class TopicGroupBean  implements Serializable {
//                private int id;
//                private String name;
//
//                public int getId() {
//                    return id;
//                }
//
//                public void setId(int id) {
//                    this.id = id;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//            }
//        }

        public static class GoodsBean  implements Serializable {
            private int id;
            private String name;
            private double price;
            private String introduction;
            private String url;
            private int collectionNum;
            private int collected;
            private int forwordNum;
            private int commentNum;
            /**
             * id : 21
             * sort : 0
             * url : 图片链接
             * type : 1
             * pid : 2
             */

            private List<ImagesBean> images;

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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class ImagesBean  implements Serializable {
                private int id;
                private int sort;
                private String url;
                private int type;
                private int pid;

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

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
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
            }
        }
    }
}

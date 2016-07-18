package com.android.loushi.loushi.json;

import java.util.List;

/**
 * Created by Administrator on 2016/5/2.
 */
public class CollectionsJson {

    /**
     * body : [{"goods":{"collected":true,"collectionNum":1,"commentNum":0,"forwordNum":0,"id":8,"images":[{"id":29,"pid":8,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-1.png"},{"id":30,"pid":8,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-2.png"},{"id":31,"pid":8,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-3.png"},{"id":32,"pid":8,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-4.png"}],"introduction":"像蟑螂蛀虫这种讨厌的生物，真的是可怕又可恨呢~在衣橱里面放上这种樟脑球，就再也不用担心讨厌的虫子来破坏自己的衣橱了。像蟑螂蛀虫这种讨厌的生物，真的是可怕又可恨呢~在衣橱里面放上这种樟脑球，就再也不用担心讨厌的虫子来破坏自己的衣橱了。","name":"米马杂货防蛀天然樟木球","price":18.8,"url":"https://item.taobao.com/item.htm?id=44423444012&ali_trackid=2:mm_56503797_8596089_29498842:1461938647_255_618757706"},"id":72,"pid":8,"scene":{"collected":true,"collectionNum":5,"commentNum":86,"forwordNum":0,"groupID":1,"id":1,"imgUrl":"http://119.29.187.58:8080/loushi/scene/1/main.jpeg","label":"标签","name":"寝室改造前要认识到三部曲|||||防尘是要注意最好不要布置那些积累灰尘后很难清洗的东西，比如墙纸、墙布、桌布、挂件，以及通过一些方法尽量减少在不容易清洁的地方累灰。","sceneGroup":{"id":1,"name":"田园类","sceneNum":0}},"strategy":{"collected":true,"collectionNum":4,"commentNum":0,"forwordNum":0,"id":3,"imgUrl":"http://119.29.187.58:8080/loushi/image/strategy/3-qianghui.jpg|||http://mp.weixin.qq.com/s?__biz=MzI4MzE5ODU4OA==&mid=405998722&idx=1&sn=d1c916dbf5dba34505dcfbe429331b51","name":"生活小tips|处理墙灰篇"},"topic":{"collected":true,"collectionNum":11,"commentNum":13,"forwordNum":0,"groupID":1,"id":1,"imgUrl":"http://119.29.187.58:8080/loushi/image/topic/1-zhaopian.png","name":"关爱学生党，这些好物让宿舍更舒适","topicGroup":{"id":1,"imgUrl":"http://119.29.187.58:8080/loushi/image/topic/1-zhaopian.png","name":"学生党","topicNum":0}},"type":3,"userID":9}]
     * code : null
     * return_info : null
     * state : true
     */

    private Object code;
    private Object return_info;
    private boolean state;
    /**
     * goods : {"collected":true,"collectionNum":1,"commentNum":0,"forwordNum":0,"id":8,"images":[{"id":29,"pid":8,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-1.png"},{"id":30,"pid":8,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-2.png"},{"id":31,"pid":8,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-3.png"},{"id":32,"pid":8,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-4.png"}],"introduction":"像蟑螂蛀虫这种讨厌的生物，真的是可怕又可恨呢~在衣橱里面放上这种樟脑球，就再也不用担心讨厌的虫子来破坏自己的衣橱了。像蟑螂蛀虫这种讨厌的生物，真的是可怕又可恨呢~在衣橱里面放上这种樟脑球，就再也不用担心讨厌的虫子来破坏自己的衣橱了。","name":"米马杂货防蛀天然樟木球","price":18.8,"url":"https://item.taobao.com/item.htm?id=44423444012&ali_trackid=2:mm_56503797_8596089_29498842:1461938647_255_618757706"}
     * id : 72
     * pid : 8
     * scene : {"collected":true,"collectionNum":5,"commentNum":86,"forwordNum":0,"groupID":1,"id":1,"imgUrl":"http://119.29.187.58:8080/loushi/scene/1/main.jpeg","label":"标签","name":"寝室改造前要认识到三部曲|||||防尘是要注意最好不要布置那些积累灰尘后很难清洗的东西，比如墙纸、墙布、桌布、挂件，以及通过一些方法尽量减少在不容易清洁的地方累灰。","sceneGroup":{"id":1,"name":"田园类","sceneNum":0}}
     * strategy : {"collected":true,"collectionNum":4,"commentNum":0,"forwordNum":0,"id":3,"imgUrl":"http://119.29.187.58:8080/loushi/image/strategy/3-qianghui.jpg|||http://mp.weixin.qq.com/s?__biz=MzI4MzE5ODU4OA==&mid=405998722&idx=1&sn=d1c916dbf5dba34505dcfbe429331b51","name":"生活小tips|处理墙灰篇"}
     * topic : {"collected":true,"collectionNum":11,"commentNum":13,"forwordNum":0,"groupID":1,"id":1,"imgUrl":"http://119.29.187.58:8080/loushi/image/topic/1-zhaopian.png","name":"关爱学生党，这些好物让宿舍更舒适","topicGroup":{"id":1,"imgUrl":"http://119.29.187.58:8080/loushi/image/topic/1-zhaopian.png","name":"学生党","topicNum":0}}
     * type : 3
     * userID : 9
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
        /**
         * collected : true
         * collectionNum : 1
         * commentNum : 0
         * forwordNum : 0
         * id : 8
         * images : [{"id":29,"pid":8,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-1.png"},{"id":30,"pid":8,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-2.png"},{"id":31,"pid":8,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-3.png"},{"id":32,"pid":8,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/mu-4.png"}]
         * introduction : 像蟑螂蛀虫这种讨厌的生物，真的是可怕又可恨呢~在衣橱里面放上这种樟脑球，就再也不用担心讨厌的虫子来破坏自己的衣橱了。像蟑螂蛀虫这种讨厌的生物，真的是可怕又可恨呢~在衣橱里面放上这种樟脑球，就再也不用担心讨厌的虫子来破坏自己的衣橱了。
         * name : 米马杂货防蛀天然樟木球
         * price : 18.8
         * url : https://item.taobao.com/item.htm?id=44423444012&ali_trackid=2:mm_56503797_8596089_29498842:1461938647_255_618757706
         */

        private GoodsBean goods;
        private int id;
        private int pid;
        /**
         * collected : true
         * collectionNum : 5
         * commentNum : 86
         * forwordNum : 0
         * groupID : 1
         * id : 1
         * imgUrl : http://119.29.187.58:8080/loushi/scene/1/main.jpeg
         * label : 标签
         * name : 寝室改造前要认识到三部曲|||||防尘是要注意最好不要布置那些积累灰尘后很难清洗的东西，比如墙纸、墙布、桌布、挂件，以及通过一些方法尽量减少在不容易清洁的地方累灰。
         * sceneGroup : {"id":1,"name":"田园类","sceneNum":0}
         */

        private SceneBean scene;
        /**
         * collected : true
         * collectionNum : 4
         * commentNum : 0
         * forwordNum : 0
         * id : 3
         * imgUrl : http://119.29.187.58:8080/loushi/image/strategy/3-qianghui.jpg|||http://mp.weixin.qq.com/s?__biz=MzI4MzE5ODU4OA==&mid=405998722&idx=1&sn=d1c916dbf5dba34505dcfbe429331b51
         * name : 生活小tips|处理墙灰篇
         */

        private StrategyBean strategy;
        /**
         * collected : true
         * collectionNum : 11
         * commentNum : 13
         * forwordNum : 0
         * groupID : 1
         * id : 1
         * imgUrl : http://119.29.187.58:8080/loushi/image/topic/1-zhaopian.png
         * name : 关爱学生党，这些好物让宿舍更舒适
         * topicGroup : {"id":1,"imgUrl":"http://119.29.187.58:8080/loushi/image/topic/1-zhaopian.png","name":"学生党","topicNum":0}
         */

        private TopicBean topic;
        private int type;
        private int userID;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
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

        public SceneBean getScene() {
            return scene;
        }

        public void setScene(SceneBean scene) {
            this.scene = scene;
        }

        public StrategyBean getStrategy() {
            return strategy;
        }

        public void setStrategy(StrategyBean strategy) {
            this.strategy = strategy;
        }

        public TopicBean getTopic() {
            return topic;
        }

        public void setTopic(TopicBean topic) {
            this.topic = topic;
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

        public static class GoodsBean {
            private boolean collected;
            private int collectionNum;
            private int commentNum;
            private int forwordNum;
            private int id;
            private String introduction;
            private String name;
            private double price;
            private String url;
            /**
             * id : 29
             * pid : 8
             * sort : 0
             * type : 3
             * url : http://119.29.187.58:8080/loushi/image/goods/mu-1.png
             */

            private List<ImagesBean> images;

            public boolean isCollected() {
                return collected;
            }

            public void setCollected(boolean collected) {
                this.collected = collected;
            }

            public int getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(int collectionNum) {
                this.collectionNum = collectionNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public int getForwordNum() {
                return forwordNum;
            }

            public void setForwordNum(int forwordNum) {
                this.forwordNum = forwordNum;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class ImagesBean {
                private int id;
                private int pid;
                private int sort;
                private int type;
                private String url;

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

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }

        public static class SceneBean {
            private boolean collected;
            private int collectionNum;
            private int commentNum;
            private int forwordNum;
            private int groupID;
            private int id;
            private String imgUrl;
            private String label;
            private String name;
            /**
             * id : 1
             * name : 田园类
             * sceneNum : 0
             */

            private SceneGroupBean sceneGroup;

            public boolean isCollected() {
                return collected;
            }

            public void setCollected(boolean collected) {
                this.collected = collected;
            }

            public int getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(int collectionNum) {
                this.collectionNum = collectionNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public int getForwordNum() {
                return forwordNum;
            }

            public void setForwordNum(int forwordNum) {
                this.forwordNum = forwordNum;
            }

            public int getGroupID() {
                return groupID;
            }

            public void setGroupID(int groupID) {
                this.groupID = groupID;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public SceneGroupBean getSceneGroup() {
                return sceneGroup;
            }

            public void setSceneGroup(SceneGroupBean sceneGroup) {
                this.sceneGroup = sceneGroup;
            }

            public static class SceneGroupBean {
                private int id;
                private String name;
                private int sceneNum;

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

                public int getSceneNum() {
                    return sceneNum;
                }

                public void setSceneNum(int sceneNum) {
                    this.sceneNum = sceneNum;
                }
            }
        }

        public static class StrategyBean {
            private boolean collected;
            private int collectionNum;
            private int commentNum;
            private int forwordNum;
            private int id;
            private String imgUrl;
            private String name;

            public boolean isCollected() {
                return collected;
            }

            public void setCollected(boolean collected) {
                this.collected = collected;
            }

            public int getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(int collectionNum) {
                this.collectionNum = collectionNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public int getForwordNum() {
                return forwordNum;
            }

            public void setForwordNum(int forwordNum) {
                this.forwordNum = forwordNum;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class TopicBean {
            private boolean collected;
            private int collectionNum;
            private int commentNum;
            private int forwordNum;
            private int groupID;
            private int id;
            private String imgUrl;
            private String name;
            /**
             * id : 1
             * imgUrl : http://119.29.187.58:8080/loushi/image/topic/1-zhaopian.png
             * name : 学生党
             * topicNum : 0
             */

            private TopicGroupBean topicGroup;

            public boolean isCollected() {
                return collected;
            }

            public void setCollected(boolean collected) {
                this.collected = collected;
            }

            public int getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(int collectionNum) {
                this.collectionNum = collectionNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public int getForwordNum() {
                return forwordNum;
            }

            public void setForwordNum(int forwordNum) {
                this.forwordNum = forwordNum;
            }

            public int getGroupID() {
                return groupID;
            }

            public void setGroupID(int groupID) {
                this.groupID = groupID;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public TopicGroupBean getTopicGroup() {
                return topicGroup;
            }

            public void setTopicGroup(TopicGroupBean topicGroup) {
                this.topicGroup = topicGroup;
            }

            public static class TopicGroupBean {
                private int id;
                private String imgUrl;
                private String name;
                private int topicNum;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImgUrl() {
                    return imgUrl;
                }

                public void setImgUrl(String imgUrl) {
                    this.imgUrl = imgUrl;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getTopicNum() {
                    return topicNum;
                }

                public void setTopicNum(int topicNum) {
                    this.topicNum = topicNum;
                }
            }
        }
    }
}

package com.android.loushi.loushi.jsonbean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class UserCollectionsGood {
    /**
     * body : [{"goods":{"collected":true,"collectionNum":1,"commentNum":0,"forwordNum":0,"id":18,"images":[{"id":73,"pid":18,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong1.jpg"},{"id":74,"pid":18,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong2.jpg"},{"id":75,"pid":18,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong3.jpg"},{"id":76,"pid":18,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong4.jpg"}],"introduction":"似乎宿舍并不太需要钟，的确，现在的手机和手表都可以告诉你时间。<br/>但用学生党能接受的价格去买一种生活格调、一种地中海风情很值得，看到的那一眼，还以为自己穿越到了爱琴海。所以用这个充满魅力的钟，告诉自己，多留点时间爱自己吧。<br/>Stay a little bit more time to love yourself.","name":"地中海挂钟","price":50,"url":"https://item.taobao.com/item.htm?spm=a1z0k.7385993.1997994373.d4919385.l4HrPD&id=15626541132&_u=epvq1esa02e "},"id":16,"pid":18,"scene":null,"strategy":null,"topic":null,"type":3,"userID":48},{"goods":{"collected":true,"collectionNum":2,"commentNum":0,"forwordNum":0,"id":23,"images":[{"id":93,"pid":23,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051906penzai1.jpg"},{"id":94,"pid":23,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051906penzai2.jpg"}],"introduction":"绿色的盆栽是地中海不可或缺的一大元素，一抹绿意会给你制造的惊喜你难以想象。<br/>如果你觉得生活乏味，或是和朋友吵架了，或是失恋了，不如亲自种一个，养在身边。<br/>生活需要绿意。","name":"小清新印度黄麻布盆栽","price":25,"url":"https://item.taobao.com/item.htm?spm=a1z0k.7385997.1997989073.d4919141.0KxbFL&id=13063758178&_u=epvq1es7948"},"id":15,"pid":23,"scene":null,"strategy":null,"topic":null,"type":3,"userID":48},{"goods":{"collected":true,"collectionNum":2,"commentNum":0,"forwordNum":0,"id":19,"images":[{"id":77,"pid":19,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051902zhijinhe1.jpg"},{"id":78,"pid":19,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051902zhijinhe2.jpg"},{"id":79,"pid":19,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051902zhijinhe3.jpg"},{"id":80,"pid":19,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051902zhijinhe4.jpg"}],"introduction":"广外宿舍蓝色的基调似乎很有海洋风，而这个纸巾盒，有着一股清新自然的海洋风，渔网、海星、帆船、小鱼等海洋元素，完美点缀了我的课桌。","name":"地中海风格装饰纸巾盒","price":34.9,"url":"https://detail.tmall.com/item.htm?spm=a1z10.3-b.w4011-11713451638.247.EM3M2u&id=17330604287&rn=2606cecaf179ad8af03cd00fb814f2e5&abbucket=2"},"id":14,"pid":19,"scene":null,"strategy":null,"topic":null,"type":3,"userID":48}]
     * code : null
     * return_info : 0
     * state : true
     */

    private Object code;
    private String return_info;
    private boolean state;
    /**
     * goods : {"collected":true,"collectionNum":1,"commentNum":0,"forwordNum":0,"id":18,"images":[{"id":73,"pid":18,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong1.jpg"},{"id":74,"pid":18,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong2.jpg"},{"id":75,"pid":18,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong3.jpg"},{"id":76,"pid":18,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong4.jpg"}],"introduction":"似乎宿舍并不太需要钟，的确，现在的手机和手表都可以告诉你时间。<br/>但用学生党能接受的价格去买一种生活格调、一种地中海风情很值得，看到的那一眼，还以为自己穿越到了爱琴海。所以用这个充满魅力的钟，告诉自己，多留点时间爱自己吧。<br/>Stay a little bit more time to love yourself.","name":"地中海挂钟","price":50,"url":"https://item.taobao.com/item.htm?spm=a1z0k.7385993.1997994373.d4919385.l4HrPD&id=15626541132&_u=epvq1esa02e "}
     * id : 16
     * pid : 18
     * scene : null
     * strategy : null
     * topic : null
     * type : 3
     * userID : 48
     */

    private List<BodyBean> body;

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getReturn_info() {
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
         * id : 18
         * images : [{"id":73,"pid":18,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong1.jpg"},{"id":74,"pid":18,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong2.jpg"},{"id":75,"pid":18,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong3.jpg"},{"id":76,"pid":18,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong4.jpg"}]
         * introduction : 似乎宿舍并不太需要钟，的确，现在的手机和手表都可以告诉你时间。<br/>但用学生党能接受的价格去买一种生活格调、一种地中海风情很值得，看到的那一眼，还以为自己穿越到了爱琴海。所以用这个充满魅力的钟，告诉自己，多留点时间爱自己吧。<br/>Stay a little bit more time to love yourself.
         * name : 地中海挂钟
         * price : 50.0
         * url : https://item.taobao.com/item.htm?spm=a1z0k.7385993.1997994373.d4919385.l4HrPD&id=15626541132&_u=epvq1esa02e
         */

        private GoodsBean goods;
        private int id;
        private int pid;
        private Object scene;
        private Object strategy;
        private Object topic;
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

        public Object getScene() {
            return scene;
        }

        public void setScene(Object scene) {
            this.scene = scene;
        }

        public Object getStrategy() {
            return strategy;
        }

        public void setStrategy(Object strategy) {
            this.strategy = strategy;
        }

        public Object getTopic() {
            return topic;
        }

        public void setTopic(Object topic) {
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
             * id : 73
             * pid : 18
             * sort : 0
             * type : 3
             * url : http://119.29.187.58:8080/loushi/image/goods/2016051901guazhong1.jpg
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
    }
}

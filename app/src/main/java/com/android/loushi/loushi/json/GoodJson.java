package com.android.loushi.loushi.json;

import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
public class GoodJson {

    /**
     * collected : false
     * collectionNum : 0
     * commentNum : 1
     * forwordNum : 0
     * id : 1
     * images : [{"id":1,"pid":1,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/xiangkuang1.jpg"},{"id":2,"pid":1,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/xiangkuang2.jpg"},{"id":3,"pid":1,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/xiangkuang3.jpg"},{"id":4,"pid":1,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/xiangkuang4.jpg"}]
     * introduction : 新款相框摆台时尚创意组合，相框黄金比例 设计，经典6格5寸，彰显立体感与时尚感.品质保证，采用进口ABS新型环保材质和优质玻璃镜面，环保无污染 是您的婚房新房 软装搭配的首选。
     * name : 创意5寸6格相框摆台简约现代相架相框
     * price : 29.9
     * url : https://item.taobao.com/item.htm?spm=a230r.1.14.42.bXzsT6&id=520835764780&ns=1&abbucket=20#detail
     */

    private BodyBean body;
    /**
     * body : {"collected":false,"collectionNum":0,"commentNum":1,"forwordNum":0,"id":1,"images":[{"id":1,"pid":1,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/xiangkuang1.jpg"},{"id":2,"pid":1,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/xiangkuang2.jpg"},{"id":3,"pid":1,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/xiangkuang3.jpg"},{"id":4,"pid":1,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/xiangkuang4.jpg"}],"introduction":"新款相框摆台时尚创意组合，相框黄金比例 设计，经典6格5寸，彰显立体感与时尚感.品质保证，采用进口ABS新型环保材质和优质玻璃镜面，环保无污染 是您的婚房新房 软装搭配的首选。","name":"创意5寸6格相框摆台简约现代相架相框","price":29.9,"url":"https://item.taobao.com/item.htm?spm=a230r.1.14.42.bXzsT6&id=520835764780&ns=1&abbucket=20#detail"}
     * code : null
     * return_info : 获取成功！
     * state : true
     */

    private Object code;
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

    public static class BodyBean {
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
         * id : 1
         * pid : 1
         * sort : 0
         * type : 3
         * url : http://119.29.187.58:8080/loushi/image/goods/xiangkuang1.jpg
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

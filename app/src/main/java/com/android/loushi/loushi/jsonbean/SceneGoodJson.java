package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneGoodJson  implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":21,"name":"无脸人台灯","price":33.21,"introduction":"介绍","url":"导流链接","images":[{"id":21,"sort":0,"url":"图片链接","type":1,"pid":2},{"id":23,"sort":1,"url":"图片链接","type":1,"pid":2}],"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6}]
     */

    private int state;
    private String code;
    private String return_info;
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

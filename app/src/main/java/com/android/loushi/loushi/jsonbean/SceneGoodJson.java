package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneGoodJson  implements Serializable {

    /**
     * body : [{"browseNum":0,"collected":false,"collectionNum":0,"commentNum":0,"forwordNum":0,"id":46,"images":[{"id":176,"pid":46,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd001.png"},{"id":177,"pid":46,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd002.png"},{"id":178,"pid":46,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd003.png"},{"id":179,"pid":46,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd004.png"}],"introduction":"这款台灯最大的亮点就是可遥控，30米且可隔墙遥控，可实现色温调节。加入WIFI还能实现手机遥控。\r\n用它之后，就算自己是最后爬床的人，我也不怕了。\r\n","name":"折叠长臂旋转可调节光led台灯\r\n","price":149,"url":"https://detail.tmall.com/item.htm?id=13115848332&skuId=84062884005"},{"browseNum":0,"collected":false,"collectionNum":0,"commentNum":0,"forwordNum":0,"id":55,"images":[{"id":205,"pid":55,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506xldsmy001.png"},{"id":206,"pid":55,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506xldsmy002.png"},{"id":207,"pid":55,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506xldsmy003.png"},{"id":208,"pid":55,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506xldsmy004.png"}],"introduction":"我好像不喜欢在空空的墙面上安放相框。\r\n我倒是喜欢放一个简单精致的实木置物架，复古做旧的处理，精心打造出粗犷的质感，古朴素美，无论是和挂钟或是盆栽，搭配得都很成立。\r\n让人一见倾心。\r\n","name":"实木一字隔板","price":145,"url":"https://item.taobao.com/item.htm?spm=2013.1.0.0.nQWk7O&id=526207499364&scm=1007.11962.20365.100200300000000&pvid=42c83539-638e-4bb2-995a-6a66939ed3b9"},{"browseNum":0,"collected":false,"collectionNum":0,"commentNum":0,"forwordNum":0,"id":24,"images":[{"id":98,"pid":24,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506gyfyz001.jpg"},{"id":99,"pid":24,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506gyfyz002.jpg"},{"id":100,"pid":24,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506gyfyz003.jpg"},{"id":101,"pid":24,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506gyfyz004.jpg"}],"introduction":"这款铁艺与实木完美搭配的美式复古椅，简单却颇具设计感，有着很强的复古工业风以及美式色彩，用它来搭配我的工业风宿舍，相比纯木质的座椅更能带来令人惊喜的效果呢。\r\n坐在这把椅子上，无论是学习，或是喝咖啡，或是煲剧，似乎都很理所当然了。\r\n","name":"美式复古铁艺椅","price":110,"url":"https://item.taobao.com/item.htm?id=527282265663"}]
     * code : null
     * return_info : null
     * state : true
     */

    private Object code;
    private Object return_info;
    private boolean state;
    /**
     * browseNum : 0
     * collected : false
     * collectionNum : 0
     * commentNum : 0
     * forwordNum : 0
     * id : 46
     * images : [{"id":176,"pid":46,"sort":0,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd001.png"},{"id":177,"pid":46,"sort":1,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd002.png"},{"id":178,"pid":46,"sort":2,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd003.png"},{"id":179,"pid":46,"sort":3,"type":3,"url":"http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd004.png"}]
     * introduction : 这款台灯最大的亮点就是可遥控，30米且可隔墙遥控，可实现色温调节。加入WIFI还能实现手机遥控。
     用它之后，就算自己是最后爬床的人，我也不怕了。

     * name : 折叠长臂旋转可调节光led台灯

     * price : 149.0
     * url : https://detail.tmall.com/item.htm?id=13115848332&skuId=84062884005
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

    public static class BodyBean  implements Serializable{
        private int browseNum;
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
         * id : 176
         * pid : 46
         * sort : 0
         * type : 3
         * url : http://119.29.187.58:8080/loushi/image/goods/2016071506hgjyhdd001.png
         */

        private List<ImagesBean> images;

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

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

        public static class ImagesBean implements Serializable{
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

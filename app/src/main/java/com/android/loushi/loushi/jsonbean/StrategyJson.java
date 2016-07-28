package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class StrategyJson  implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":21,"wDate":"2016/07/29","name":"名称","collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"browseNum":7812,"imgUrl":"asd"},{"id":22,"wDate":"2016/07/29","name":"名称","collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"browseNum":7812,"imgUrl":"asd"}]
     */

    private boolean state;
    private String code;
    private String return_info;
    /**
     * id : 21
     * wDate : 2016/07/29
     * name : 名称
     * collectionNum : 333
     * collected : 0
     * forwordNum : 12
     * commentNum : 6
     * browseNum : 7812
     * imgUrl : asd
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
        private String wDate;
        private String name;
        private int collectionNum;
        private boolean collected;
        private int forwordNum;
        private int commentNum;
        private int browseNum;
        private String imgUrl;
        private String digest;

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWDate() {
            return wDate;
        }

        public void setWDate(String wDate) {
            this.wDate = wDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public boolean getCollected() {
            return collected;
        }

        public void setCollected(boolean collected) {
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

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}

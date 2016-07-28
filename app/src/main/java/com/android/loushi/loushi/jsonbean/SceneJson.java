package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneJson implements Serializable {

    @Override
    public String toString() {
        return "SceneJson{" +
                "code='" + code + '\'' +
                ", return_info='" + return_info + '\'' +
                ", state=" + state +
                ", body=" + body +
                '}';
    }

    /**
     * body : [{"collected":false,"collectionNum":1,"commentNum":0,"forwordNum":0,"groupID":3,"id":6,"imgUrl":"http://loushibucket-10042796.file.myqcloud.com/image/scene/guangwaiyj.jpg","label":"标签","name":"宜家风格:简约,清新,自然|||||宜家风格的特点是：简约, 清新,自然; 收纳性特别强；易搬动和自行拆装；兼具设计感和实用性， 符合年轻人对生活的品味要求。","sceneGroup":{"id":3,"name":"宜家风","sceneNum":0}}]
     * return_info : 获取成功！
     * state : true
     */
    private String code;
    private String return_info;
    private boolean state;
    /**
     * collected : false
     * collectionNum : 1
     * commentNum : 0
     * forwordNum : 0
     * groupID : 3
     * id : 6
     * imgUrl : http://loushibucket-10042796.file.myqcloud.com/image/scene/guangwaiyj.jpg
     * label : 标签
     * name : 宜家风格:简约,清新,自然|||||宜家风格的特点是：简约, 清新,自然; 收纳性特别强；易搬动和自行拆装；兼具设计感和实用性， 符合年轻人对生活的品味要求。
     * sceneGroup : {"id":3,"name":"宜家风","sceneNum":0}
     */

    private List<BodyBean> body;
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

    public static class BodyBean implements Serializable {
        private boolean collected;
        private int collectionNum;
        private int commentNum;
        private int forwordNum;
        private int groupID;
        private int id;
        private int browseNum;
        private String imgUrl;
        private String label;
        private String name;
        private String digest;

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        @Override
        public String toString() {
            return "BodyBean{" +
                    "collected=" + collected +
                    ", collectionNum=" + collectionNum +
                    ", commentNum=" + commentNum +
                    ", forwordNum=" + forwordNum +
                    ", groupID=" + groupID +
                    ", id=" + id +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", label='" + label + '\'' +
                    ", name='" + name + '\'' +
                    ", sceneGroup=" + sceneGroup +
                    '}';
        }

        /**
         * id : 3
         * name : 宜家风
         * sceneNum : 0
         */

        private SceneGroupBean sceneGroup;

        public boolean getCollected() {
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

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public void setSceneGroup(SceneGroupBean sceneGroup) {
            this.sceneGroup = sceneGroup;
        }

        public static class SceneGroupBean implements Serializable {
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
}

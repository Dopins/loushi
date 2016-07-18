package com.android.loushi.loushi.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/4/17.
 */
public class SceneJson implements Serializable {


    /**
     * body : [{"collected":true,"collectionNum":3,"commentNum":0,"forwordNum":0,"groupID":1,"id":1,"imgUrl":"http://img.51tietu.net/upload/2016-3/201603280724171722.jpg","label":"Scene测试数据1","name":"SceneName_测试数据1","sceneGroup":{"id":1,"name":"SceneGroup测试数据","sceneNum":0}},{"collected":true,"collectionNum":2,"commentNum":0,"forwordNum":0,"groupID":1,"id":2,"imgUrl":"http://img.51tietu.net/upload/2016-3/201603280724171722.jpg","label":"Scene测试数据2","name":"SceneName_测试数据2","sceneGroup":{"id":1,"name":"SceneGroup测试数据","sceneNum":0}}]
     * code : null
     * return_info : 获取成功！
     * state : true
     */

    private String code;
    private String return_info;
    private boolean state;
    /**
     * collected : true
     * collectionNum : 3
     * commentNum : 0
     * forwordNum : 0
     * groupID : 1
     * id : 1
     * imgUrl : http://img.51tietu.net/upload/2016-3/201603280724171722.jpg
     * label : Scene测试数据1
     * name : SceneName_测试数据1
     * sceneGroup : {"id":1,"name":"SceneGroup测试数据","sceneNum":0}
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

    public static class BodyBean implements Serializable {
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
         * name : SceneGroup测试数据
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
}

package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SceneGroupJson  implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":21,"name":"名称","sceneNum":52},{"id":221,"name":"名称","sceneNum":512}]
     */

    private int state;
    private String code;
    private String return_info;
    /**
     * id : 21
     * name : 名称
     * sceneNum : 52
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

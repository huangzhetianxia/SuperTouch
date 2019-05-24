package com.supertouch.loginregister.bean;

public class ResLoginBean {

    /**
     * resCode : 0
     * resMsg : 登录成功
     * data : {"user_id":1,"user_account":"18813974715","user_name":"王者000","user_sex":1,"user_identity":1,"user_head":"http://b-ssl.duitang.com/uploads/item/201902/04/20190204104319_gcngt.jpg"}
     */

    private int resCode;
    private String resMsg;
    private DataBean data;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 1
         * user_account : 18813974715
         * user_name : 王者000
         * user_sex : 1
         * user_identity : 1
         * user_head : http://b-ssl.duitang.com/uploads/item/201902/04/20190204104319_gcngt.jpg
         */

        private int user_id;
        private String user_account;
        private String user_name;
        private int user_sex;
        private int user_identity;
        private String user_head;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_account() {
            return user_account;
        }

        public void setUser_account(String user_account) {
            this.user_account = user_account;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(int user_sex) {
            this.user_sex = user_sex;
        }

        public int getUser_identity() {
            return user_identity;
        }

        public void setUser_identity(int user_identity) {
            this.user_identity = user_identity;
        }

        public String getUser_head() {
            return user_head;
        }

        public void setUser_head(String user_head) {
            this.user_head = user_head;
        }
    }
}

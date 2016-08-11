private void autoLogin() {
        //TODO 第三方登录？？
        if (!hasLogin())
            return;
        if(!CurrentAccount.isThird()) {
            String phone = CurrentAccount.getAccount();
            String password = CurrentAccount.getPassword();
            OkHttpUtils.post(UrlConstant.USERLOGINURL)
                    .params(KeyConstant.MOBILE_PHONE, phone)
                    .params(KeyConstant.PASSWORD, password)
                    .params(KeyConstant.ISTHIRD, "false")
                    .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                        @Override
                        public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                            if (userLoginJson.getState()) {
                                BaseActivity.user_id = userLoginJson.getBody() + "";
                                Log.e(TAG, "autoLogin 登录成功！");
                            } else {
                                Log.e(TAG, "autoLogin 登录失败！");
                            }
                        }
                    });
        }
        else{
            final String account=CurrentAccount.getAccount();
            String password=CurrentAccount.getPassword();
            final String type =CurrentAccount.Third_type;

            OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogin.action")
                    .params("account", account)
                    .params("type", type)
                    .params("token", password)
                    .params("isThird", "true")
                    .execute(new JsonCallback<UserLoginJson>(UserLoginJson.class) {
                        @Override
                        public void onResponse(boolean isFromCache, UserLoginJson userLoginJson, Request request, Response response) {
                            Log.e("splash", new Gson().toJson(userLoginJson));
                            if (userLoginJson.getState()) {
                                //CurrentAccount.setLoginOrNot(true);//登录成功，设置登录状态
                                String code = userLoginJson.getCode();
                                if (code != null && code.equals("3")) {
                                } else {
                                    BaseActivity.user_id = userLoginJson.getBody() + "";
                                    CurrentAccount.setUser_id(userLoginJson.getBody() + "");
                                }

                            } else {
                                CurrentAccount.setLoginOrNot(false);
                                Log.e("splashthirdlogin", "登录失败！");
                            }


                        }


                    });
        }

    }
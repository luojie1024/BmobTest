package com.bmobtest2;

import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends BaseActivity {

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);

                    //第一：默认初始化
//                    Bmob.initialize(this, "b8f0f2a625ab97c165fd63f2c949041c");
                    // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
                    //Bmob.initialize(this, "Your Application ID","bmob");

                    //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
                    BmobConfig config =new BmobConfig.Builder(this)
                    //设置appkey
                    .setApplicationId("b8f0f2a625ab97c165fd63f2c949041c")
                    //请求超时时间（单位为秒）：默认15s
                    .setConnectTimeout(30)
                    //文件分片上传时每片的大小（单位字节），默认512*1024
                    .setUploadBlockSize(1024*1024)
                    //文件的过期时间(单位为秒)：默认1800s
                    .setFileExpiration(2500)
                    .build();
                    Bmob.initialize(config);
                    adddata();
          }


          //添加一条数据
          public void adddata(){
                    Person p2 = new Person();
                    p2.setName("lucky");
                    p2.setAddress("北京海淀");
                    p2.save(new SaveListener<String>() {
                              @Override
                              public void done(String objectId,BmobException e) {
                                        if(e==null){
                                                  toast("添加数据成功，返回objectId为："+objectId);
                                        }else{
                                                  toast("创建数据失败：" + e.getMessage());
                                        }
                              }
                    });
          }
}

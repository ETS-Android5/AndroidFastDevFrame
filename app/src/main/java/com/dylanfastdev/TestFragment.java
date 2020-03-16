package com.dylanfastdev;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import java.util.Map;

import cn.bluemobi.dylan.base.BaseFragment;
import cn.bluemobi.dylan.base.utils.activitypermission.ActPermissionRequest;
import cn.bluemobi.dylan.base.utils.activityresult.ActResultRequest;
import cn.bluemobi.dylan.http.Http;
import cn.bluemobi.dylan.http.HttpCallBack;

/**
 * @author dylan
 * @date 2019/11/30
 */
public class TestFragment extends BaseFragment {
    private ImageView iv;

    @Override
    protected int setContentView() {
        return R.layout.customview;
    }

    @Override
    protected void initView(View view) {
        iv = view.findViewById(R.id.iv);
    }

    @Override
    protected void initData() {
        Http.with(mContext)
                .setObservable(
                        Http.getApiService(ApiService.class)
                                .test("13284", 10, 1))
                .setDataListener(new HttpCallBack() {
                    @Override
                    public void netOnSuccess(Map<String, Object> data) {
//                      List<Map<String,Object>> list= JsonParse.getList(data, "data");
//                      List<String> s= JsonParse.getList(data, "data");
//                        startActivity(new Intent(mContext,MainActivity.class));
//                        finish();
                    }

                    @Override
                    public void netOnFinish() {
                        super.netOnFinish();
                    }
                });
//        Glide.with(mContext).load("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=180092f702f431adbcd2443f730dcb92/f636afc379310a5519aea991b94543a9832610c8.jpg")
//                .into(iv);
//        new Handler().postDelayed(() -> getActivity().finish(),2*1000);


        String[] PERMISSIONS = new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.CAMERA,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION,
        };
        onRequestPermissionsResult(PERMISSIONS, new ActPermissionRequest.PermissionCheckCallBack() {
            @Override
            public void onSucceed() {
                Logger.d("同意");
            }

            @Override
            public void onReject(String... permission) {
                Logger.d("拒绝");
            }

            @Override
            public void onRejectAndNoAsk(String... permission) {
                Logger.d("不再询问");
            }
        });
        startActivityForResult(new Intent(mContext, TextActivity.class), new ActResultRequest.Callback() {
            @Override
            public void onActivityResult(int resultCode, Intent data) {

            }
        });
    }

    @Override
    public void addListener() {

    }

    @Override
    public void onClick(View v) {

    }
}

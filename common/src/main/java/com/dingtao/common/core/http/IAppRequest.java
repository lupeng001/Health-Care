package com.dingtao.common.core.http;


import com.dingtao.common.bean.CircleInfoBean;
import com.dingtao.common.bean.Department;
import com.dingtao.common.bean.DoctorBankCardById;
import com.dingtao.common.bean.DoctorIdCardInfo;
import com.dingtao.common.bean.DoctorMessage;
import com.dingtao.common.bean.FindSickBean;
import com.dingtao.common.bean.Jianyi;
import com.dingtao.common.bean.Login;
import com.dingtao.common.bean.MyAdoptedBean;
import com.dingtao.common.bean.QuiryRecordBean;
import com.dingtao.common.bean.ReadNotBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.SearchcircleBean;
import com.dingtao.common.bean.Sendmessage;
import com.dingtao.common.bean.XiTong;
import com.dingtao.common.bean.Zhicheng;

import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.LoginException;

import androidx.versionedparcelable.NonParcelField;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {
    //注册邮箱
    @FormUrlEncoded
    @POST("doctor/v1/sendEmailCode")
    Observable<Result> email(@Field("email") String email);
    //入驻
    @POST("doctor/v1/applyJoin")
    @Headers({ "doctor/Content-Type: application/json"})
    Observable<Result> ruzhu(@Body RequestBody ss);
    //登录
    @FormUrlEncoded
    @POST("doctor/v1/login")
    Observable<Result<Login>>login(@Field("email") String email,@Field("pwd") String pwd);
    //验证验证码
    @FormUrlEncoded
    @POST("doctor/v1/checkCode")
    Observable<Result> verify(@Field("email") String email,@Field("code") String code);
    //职称选择
    @GET("doctor/v1/findJobTitleList")
    Observable<Result<List<Zhicheng>>> zhicheng();
    //重置密码
    @FormUrlEncoded
    @PUT("doctor/v1/resetUserPwd")
    Observable<Result> resetUserPwd(@Field("email") String email,@Field("pwd1") String pwd1,@Field("pwd2") String pwd2);
// 上传证件照
    @POST("doctor/v1/uploadImagePic")
    Observable<Result> uploadImagePic();
    //问诊列表
    @GET("doctor/inquiry/verify/v1/findInquiryRecordList")
    Observable<Result<List<QuiryRecordBean>>> wenzhenliebiao(@Header("doctorId")String doctorId, @Header("sessionId")String sessionId);


    //被采纳意见
    @GET("doctor/verify/v1/findMyAdoptedCommentList")
    Observable<Result<List <Jianyi>>> jianyi(@Header("doctorId")Long doctorId, @Header("sessionId")String sessionId
            , @Query("page") int page,
                                             @Query("count") int count
                                             );
        //系统照片
    @GET("doctor/v1/findSystemImagePic")
    Observable<Result<List<XiTong>>> xitong(@Header("doctorId")Long doctorId, @Header("sessionId")String sessionId);
    //选择系统照片
    @FormUrlEncoded
    @POST("doctor/verify/v1/chooseImagePic")
    Observable<Result> xuanzexitong(@Header("doctorId")Long doctorId, @Header("sessionId")String sessionId
    ,@Field("imagePic")String imagePic
    );
    //查询医生信息
    @GET("doctor/verify/v1/findDoctorById")
    Observable<Result<DoctorMessage>> doctormessage(@Header("doctorId")Long doctorId, @Header("sessionId")String sessionId);

    //发送消息
    @FormUrlEncoded
    @POST("doctor/inquiry/verify/v1/pushTextMsg")
    Observable<Result> sendmessage(@Header("doctorId") Long userIds, @Header("sessionId") String sessionId
            ,@Field("inquiryId") int id
            ,@Field("content") String content
            ,@Field("type") int type
            ,@Field("userId") String userId
    );

    //查看历史纪律

    @GET("doctor/inquiry/verify/v1/findInquiryDetailsList")
    Observable<Result<List<Sendmessage>>> message(@Header("doctorId") Long userId, @Header("sessionId") String sessionId
            , @Query("inquiryId")int inquiryId
            , @Query("page")int page
            , @Query("count")int count
    );

    // 上传语音
    @Multipart
    @POST("doctor/inquiry/verify/v1/pushVoiceMsg")
    Observable<Result> uploadFile(
            @Header("doctorId") Long userIds, @Header("sessionId") String sessionId,
            @QueryMap HashMap<String, String> params, @Part MultipartBody.Part content);


    //绑定医生身份证信息
    @POST("doctor/verify/v1/bindDoctorIdCard")
    @Headers({ "Content-Type: application/json"})
    Observable<Result> bindDoctorIdCard(
            @Header("doctorId") Long userIds, @Header("sessionId") String sessionId,
            @Body RequestBody bindcard
//            @Field("doctorId") Long doctorId,
//            @Field("name") String name,
//            @Field("sex") String sex,
//            @Field("nation") String nation,
//            @Field("birthday") String birthday,
//            @Field("address") String address,
//            @Field("idNumber") String idNumber,
//            @Field("issueOffice") String issueOffice
    );
    //查询医生信息
    @GET("doctor/verify/v1/findDoctorIdCardInfo")
    Observable<Result<DoctorIdCardInfo>> DoctorIdCardInfo(@Header("doctorId") Long doctorId, @Header("sessionId") String sessionId);


    //绑定银行卡\

    @FormUrlEncoded
    @POST("doctor/verify/v1/bindDoctorBankCard")
    Observable<Result> bindDoctorBankCard(@Header("doctorId") Long doctorId, @Header("sessionId") String sessionId
                ,@Field("bankCardNumber") String bankCardNumber,
                                          @Field("bankName") String bankName,
                                          @Field("bankCardType") String bankCardType
    );


    //查询 医生银行卡信息
    @GET("doctor/verify/v1/findDoctorBankCardById")
    Observable<Result<DoctorBankCardById>> DoctorBankCardById(@Header("doctorId") Long doctorId, @Header("sessionId") String sessionId);

    //绑定Token
    @FormUrlEncoded
    @POST("doctor/verify/v1/addDoctorPushToken")
    Observable<Result> DoctorPushToken(@Header("doctorId") Long doctorId, @Header("sessionId") String sessionId,@Field("token") String token);

    //病友圈列表展示
    @GET("doctor/sickCircle/v1/findSickCircleList")
    Observable<Result<List<FindSickBean>>> getFindSick(@Query("departmentId") int departmentId, @Query("page") int page, @Query("count") int count);

    //查询病友圈详情
    @GET("doctor/sickCircle/v1/findSickCircleInfo")
    Observable<Result <CircleInfoBean>> getCircleInfo(@Query("sickCircleId") int sickCircleId);
    //查询科室列表
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<Department>>> getDepartment();
    //根据关键词查询病友圈
    @GET("doctor/sickCircle/v1/searchSickCircle")
    Observable<Result<List<SearchcircleBean>>> getSearchCircle(@Query("keyWord") String keyWord);

    //发表评论
    @FormUrlEncoded
    @POST("doctor/sickCircle/verify/v1/publishComment")
    Observable<Result> getPublish(@Header("doctorId") Long doctorId, @Header("sessionId") String sessionId,@Field("sickCircleId") int sickCircleId,@Field("content")String content);


    //查询医生未读消息数
    @GET("doctor/verify/v1/findDoctorNoticeReadNum")
    Observable<Result<List<ReadNotBean>>> getReadNum(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId);

    //修改消息状态为全部已读
    @PUT("doctor/verify/v1/modifyAllStatus")
    Observable<Result> getStatus(@Header("doctorId") String doctorId, @Header("sessionId") String sessionId);


    //我的被采纳的建议
    @GET("doctor/verify/v1/findMyAdoptedCommentList")
    Observable<Result<MyAdoptedBean>> getMyAdoptedlist(@Header("doctorId") Long doctorId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);
}

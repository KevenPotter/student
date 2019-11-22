package com.kevenpotter.student.result;

/**
 * @param <T> 指定泛型
 * @author KevenPotter
 * @compony https://github.com/KevenPotter/student
 * @date 2019-11-22 11:22:16
 * @description 返回结果集状态码集合
 */
public class ApiResult<T> {

    /*操作成功状态码的值*/
    public static final String CODE_SUCCESS_VALUE = "1";
    /*抛出异常状态码的值*/
    public static final String CODE_ERROR_VALUE = "-1";
    /*操作成功时返回的提示信息*/
    public static final String MSG_SUCCESS_VALUE = "success";
    /*抛出异常时返回的提示信息*/
    public static final String MSG_ERROR_VALUE = "error";
    /*状态码*/
    private String code;
    /*提示信息*/
    private String msg;
    /*结果集*/
    private T data;

    public static <T> ApiResult buildSuccess() {
        return buildSuccess(null);
    }

    public static <T> ApiResult buildSuccess(String code, String msg) {
        return build(code, msg, null);
    }

    public static <T> ApiResult buildSuccess(String code, String msg, Object data) {
        return build(code, msg, data);
    }


    /**
     * 创建结果集对象
     * 创建人：liudy
     * 创建时间：2017/4/19 0019-10:41
     *
     * @param data 向客户端展示的数据
     * @return ResultInfo
     */
    public static <T> ApiResult buildSuccess(T data) {
        return build(CODE_SUCCESS_VALUE, MSG_SUCCESS_VALUE, data);
    }

    /**
     * 创建结果集对象
     * 创建人：liudy
     * 创建时间：2017/5/2 0002-16:24
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return ResultInfo
     */
    public static <T> ApiResult buildFailure(String code, String msg) {
        return build(code, msg, null);
    }

    /**
     * 该方法功能详细描述：
     * 创建人：yupeng
     * 创建时间：2017/4/19 0019-10:43
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 向客户端展示的数据
     * @return ResultInfo
     */
    public static <T> ApiResult build(String code, String msg, T data) {
        ApiResult resultInfo = new ApiResult();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        if (data != null) {
            resultInfo.setData(data);
        }
        return resultInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

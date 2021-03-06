package ticketsystem.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import library.model.BaseInfo;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/9/8 11:21
 */

public class TicketInfo<T> extends BaseInfo implements Serializable{

    @SerializedName("result")
    public T data;

    @SerializedName("ret_code")
    public int ret_code;
}

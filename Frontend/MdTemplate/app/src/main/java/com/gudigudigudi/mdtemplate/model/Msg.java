package com.gudigudigudi.mdtemplate.model;

/**
 * Created by gu on 9/27/17.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息实体类
 */
@Getter
@AllArgsConstructor
public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    // 消息内容
    private String content;
    // 消息类型
    private int type;
}

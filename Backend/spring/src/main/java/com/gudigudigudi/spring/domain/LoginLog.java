package com.gudigudigudi.spring.domain;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {
    private int loginLogId;
    private int userId;
    private String ip;
    private Date loginDate;


}

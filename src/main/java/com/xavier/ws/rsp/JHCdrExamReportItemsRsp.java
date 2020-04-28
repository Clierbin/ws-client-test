package com.xavier.ws.rsp;


import lombok.Data;

import java.io.Serializable;

/**
 * The table 检查项目
 */
@Data
public class JHCdrExamReportItemsRsp implements Serializable {

    private static final long serialVersionUID = -4661894023590815708L;
    /**
     * costs 费用.
     */
    private Float costs;
    /**
     * orgId 机构id.
     */
    private Long orgId;
    /**
     * examNo 检查编号.
     */
    private String examNo;
    /**
     * examItem 检查项目.
     */
    private String examItem;
    /**
     * examApplyNo 申请号.
     */
    private String examApplyNo;
    /**
     * examItemCode 项目代码.
     */
    private String examItemCode;
    /**
     * examItemNo 项目序号.
     */
    private Integer examItemNo;
    /**
     * fileVisitType 就诊类型.
     */
    private Integer fileVisitType;
}

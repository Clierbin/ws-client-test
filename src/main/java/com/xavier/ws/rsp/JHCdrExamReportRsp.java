package com.xavier.ws.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class JHCdrExamReportRsp implements Serializable {
    /**
     * note 检查备注.
     */
    private String note;
    /**
     * examNo 申请序号.
     */
    private String examNo;
    /**
     * reqDept 申请科室.
     */
    private String reqDept;
    /**
     * urlText pacs检查图象url变动的内容.
     */
    private String urlText;
    /**
     * examType 检查类别 如：xa 表示（介入）.
     */
    private String examType;
    /**
     * examClass 检查号类别.
     */
    private String examClass;
    /**
     * patientId 患者id.
     */
    private String patientId;
    /**
     * examEmitId 放射号.
     */
    private String examEmitId;
    /**
     * examMethod 检查方法，介入（xa）时为手术名称，只有在结果类型为tx时有值.
     */
    private String examMethod;
    /**
     * impression 诊断印象.
     */
    private String impression;
    /**
     * isAbnormal 阳性1其他是阴性.
     */
    private String isAbnormal;
    /**
     * reportText 报告内容.
     */
    private String reportText;
    /**
     * description 诊断所见.
     */
    private String description;
    /**
     * examSubClass 检查子类.
     */
    private String examSubClass;
    /**
     * reqPhysician 开单医师.
     */
    private String reqPhysician;
    /**
     * resultStatus 申请结果状态，f=审核报告 审核  or d=取消审核报告 取消.如果为取消，则不显示.
     */
    private String resultStatus;
    /**
     * checkDoctName 审核医师.
     */
    private String checkDoctName;
    /**
     * executeDoctName 检查医师.
     */
    private String executeDoctName;
    /**
     * reportPhysician 报告医师.
     */
    private String reportPhysician;
    /**
     * criticalvaluesContent 危急值内容.
     */
    private String criticalvaluesContent;
    /**
     * visitId 住院次.
     */
    private Integer visitId;
    /**
     * fileVisitType 就诊类型.
     */
    private Integer fileVisitType;
    /**
     * isCriticalvalues 是否是危急值,0,否;1,是.
     */
    private Integer isCriticalvalues;
    /**
     * checkDate 审核时间.
     */
    private Date checkDate;
    /**
     * examDateTime 检查时间.
     */
    private Date examDateTime;
    /**
     * reportDateTime 报告时间.
     */
    private Date reportDateTime;
    /**
     * requestDoctDate 开单时间.
     */
    private Date requestDoctDate;
    /**
     * hisRegisterPk HIS就诊主键
     */
    private String hisRegisterPk;

    /**
     * examApplyNo 申请单号
     */
    private String examApplyNo;
}

package com.xavier.ws.api;

import com.alibaba.fastjson.JSONObject;
import com.xavier.ws.rsp.JHCdrExamReportItemsRsp;
import com.xavier.ws.rsp.JHCdrExamReportRsp;
import com.xavier.ws.send.ToWebSV;
import com.xavier.ws.util.OkHttpCli;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class TestApi {

	/*@Autowired
	private QueryOracle queryOracle;
	@Autowired
	private ToFile toFile;*/

	@Autowired
	@Qualifier(value = "toWeb")
	private ToWebSV toWebSV;

	@Autowired
    private OkHttpCli okHttpCli;

	@GetMapping(path = "test")
	public String test() {
		//toFile.date2File(queryOracle.test());
		return "OK";
	}

	@GetMapping(path = "websv/{xmlmesage}")
	public List<JHCdrExamReportRsp> websv(@PathVariable(value = "xmlmesage") String xmlmesage) throws DocumentException {
		System.out.println("GetIn");
		String webUrl = "http://172.31.68.232/ReportJiahe/CSHESBService.asmx";
		String methodName = "ReportListdxPID";
		System.out.println("Calling" + webUrl);
        List<JHCdrExamReportRsp> jhCdrExamReportRsps = toWebSV.callWebSV(webUrl, methodName, xmlmesage);
        System.out.println("Called");
//		return "Finished!Looking for console!";
		return jhCdrExamReportRsps;
	}
    @GetMapping(path = "websv2/{xmlmesage}")
    public List<JHCdrExamReportItemsRsp> websv2(@PathVariable(value = "xmlmesage") String xmlmesage) throws DocumentException {
        System.out.println("GetIn");
        String webUrl = "http://172.31.68.232/ReportJiahe/CSHESBService.asmx";
        String methodName = "ReportListdxexamNo";
        System.out.println("Calling" + webUrl);
        List<JHCdrExamReportItemsRsp> jhCdrExamReportRsps = toWebSV.callWebSV2(webUrl, methodName, xmlmesage);
        System.out.println("Called");
//		return "Finished!Looking for console!";
        return jhCdrExamReportRsps;
    }

    @PostMapping(path = "okhttp/{patientId}")
    public String okhttp(@PathVariable(value = "patientId") String patientId)  {
	    // 1.检验申请单服务地址
         String url="http://172.31.68.248/QY_EMR_Interface/Service/DaXingEmrReport.ashx?action=GetReportForm";
        // 2.检验申请项目
//        String url="http://172.31.68.248/QY_EMR_Interface/Service/DaXingEmrReport.ashx?action=GetReportItem";
        Map<String, String> params=new HashMap<>();
        params.put("patientId",patientId);
        params.put("hospitalNo","");
        params.put("visitId","");
        params.put("receiveDate", "");
        params.put(	"sectionNo", "");
        params.put(	"testTypeno","");
        String jsonParams = JSONObject.toJSONString(params);
        String post = okHttpCli.doPostJson(url, jsonParams);
        return post;
    }
}

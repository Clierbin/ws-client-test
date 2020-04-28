package com.xavier.ws.send;


import com.xavier.ws.util.CXmlUtil;
import com.xavier.ws.rsp.JHCdrExamReportRsp;
import com.xavier.ws.util.JHStringUtil;

import com.xavier.ws.rsp.JHCdrExamReportItemsRsp;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(value = "toWeb")
public class ToWebSV {

    public List<JHCdrExamReportRsp> callWebSV(String wsdUrl, String operationName, String... params) throws DocumentException {

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        //1、获取到读取对象
        Client client = dcf.createClient("http://172.31.68.232/ReportJiahe/CSHESBService.asmx?WSDL");
        //client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        List<JHCdrExamReportRsp> jhCdrExamReportRsps = new ArrayList<>();
        try {
            System.out.println("调用");
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(operationName, params);
            for (Object object : objects) {
                List<String> itemString = JHStringUtil.subString(new ArrayList<>(), object.toString(), "<item>", "</item>");
                for (String s : itemString) {
//                    String replace = s.replace("体检", "0"); // pace 改好之后删除
                    JHCdrExamReportRsp reportRsp = (JHCdrExamReportRsp) CXmlUtil.xmlStrToBean("<JHCdrExamReportRsp>"+s+"</JHCdrExamReportRsp>",
                            JHCdrExamReportRsp.class);
                    if (reportRsp.getUrlText()!=null){
                        reportRsp.setUrlText(reportRsp.getUrlText().replace("@","&"));
                    }
                    jhCdrExamReportRsps.add(reportRsp);
                }
            }
            return jhCdrExamReportRsps;
        } catch (Exception e) {
            e.printStackTrace();
            return jhCdrExamReportRsps;
        }


    }

    public static void getNodes(Element rootElement) {
        String name = rootElement.getName();
        System.out.println("节点名称：" + name);
        //获取节点属性
        List<Attribute> attributes = rootElement.attributes();
        for (Attribute attribute : attributes) {
            System.out.println("属性名称" + attribute.getName() + "---" + attribute.getText());
        }
        //获取值
        String value = rootElement.getTextTrim();
        if (!value.equals("")) {
            System.out.println("节点属性名称:" + value);
        }

        //判断是否还有下个节点
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();//拿到下个节点
            getNodes(next);
        }

    }

    private static String parseString2xml(String returnMsg) {
        String parseStr = null;

        try {
            Document doc = DocumentHelper.parseText(returnMsg);
            Element roots = doc.getRootElement();
            System.out.println("根节点 = [" + roots.getName() + "]");
            System.out.println("内容：" + roots.getText());
            parseStr = roots.getText();
            //只有根节点……
            Iterator elements = roots.elementIterator();
            while (elements.hasNext()) {
                Element child = (Element) elements.next();
                System.out.println("节点名称 = [" + child.getName() + "]" + "节点内容：" + child.getText());
                List subElemets = child.elements();
                for (int i = 0; i < subElemets.size(); i++) {
                    Element subChild = (Element) subElemets.get(i);
                    System.out.println("子节点名称：" + subChild.getName() + "子节点内容：" + subChild.getText());
                }

            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return parseStr;

    }

    public static void main(String[] args) throws Exception {
        String a = "<jhCdrExamReportDTO><success>true</success><message>成功</message><item><checkDate>3/19/2020 3:03:21 PM</checkDate><checkDoctName>刘雪晶</checkDoctName><createdDate>3/19/2020 2:00:29 PM</createdDate><criticalvaluesContent></criticalvaluesContent><description>\n" +
                "骨盆诸骨骨质未见明显异常，双髋关节、双侧骶髂关节间隙未见明显异常，双侧股骨头光滑。\n" +
                "\n" +
                "\n" +
                "骨盆未见明显异常，请结合临床。</description><examApplyNo>Z20200319282|</examApplyNo><examClass>CR</examClass><examDateTime>3/19/2020 1:59:38 PM</examDateTime><examEmitId>YF278742</examEmitId><examMethod></examMethod><examNo>YF278742</examNo><examSubClass>骨盆正位</examSubClass><examType>CR</examType><executeDoctName></executeDoctName><fileVisitType>0</fileVisitType><impression></impression><isAbnormal>0</isAbnormal><isCriticalvalues></isCriticalvalues><note></note><patientId>P0229894</patientId><hisRegisterPk>83299538-b47e-4b6d-9fc0-f056c2a1925b                                                                                            </hisRegisterPk><reportDateTime>3/19/2020 3:03:21 PM</reportDateTime><reportPhysician>刘雪晶</reportPhysician><reportText>\n" +
                "骨盆诸骨骨质未见明显异常，双髋关节、双侧骶髂关节间隙未见明显异常，双侧股骨头光滑。\n" +
                "\n" +
                "\n" +
                "骨盆未见明显异常，请结合临床。||</reportText><reqDept>外科</reqDept><reqPhysician>王建军</reqPhysician><requestDoctDate>3/19/2020 1:59:01 PM</requestDoctDate><resultStatus>审核报告</resultStatus><sendMessage>审核报告</sendMessage><urlText>http://172.31.68.237/?user_name=vue@password=vue123@dicom_ae_title=pacsFIR@accession_number=YF278742</urlText><visitId></visitId></item><item><checkDate>3/19/2020 3:03:21 PM</checkDate><checkDoctName>刘雪晶</checkDoctName><createdDate>3/19/2020 2:00:29 PM</createdDate><criticalvaluesContent></criticalvaluesContent><description>\\n\" +\n" +
                "                \"骨盆诸骨骨质未见明显异常，双髋关节、双侧骶髂关节间隙未见明显异常，双侧股骨头光滑。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"骨盆未见明显异常，请结合临床。</description><examApplyNo>Z20200319282|</examApplyNo><examClass>CR</examClass><examDateTime>3/19/2020 1:59:38 PM</examDateTime><examEmitId>YF278742</examEmitId><examMethod></examMethod><examNo>YF278742</examNo><examSubClass>骨盆正位</examSubClass><examType>CR</examType><executeDoctName></executeDoctName><fileVisitType>0</fileVisitType><impression></impression><isAbnormal>0</isAbnormal><isCriticalvalues></isCriticalvalues><note></note><patientId>P0229894</patientId><hisRegisterPk>83299538-b47e-4b6d-9fc0-f056c2a1925b                                                                                            </hisRegisterPk><reportDateTime>3/19/2020 3:03:21 PM</reportDateTime><reportPhysician>刘雪晶</reportPhysician><reportText>\\n\" +\n" +
                "                \"骨盆诸骨骨质未见明显异常，双髋关节、双侧骶髂关节间隙未见明显异常，双侧股骨头光滑。\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"骨盆未见明显异常，请结合临床。||</reportText><reqDept>外科</reqDept><reqPhysician>王建军</reqPhysician><requestDoctDate>3/19/2020 1:59:01 PM</requestDoctDate><resultStatus>审核报告</resultStatus><sendMessage>审核报告</sendMessage><urlText>http://172.31.68.237/?user_name=vue@password=vue123@dicom_ae_title=pacsFIR@accession_number=YF278742</urlText><visitId></visitId></item></jhCdrExamReportDTO>";


        List<String> itemString = JHStringUtil.subString(new ArrayList<>(), a, "<item>", "</item>");
        List<JHCdrExamReportRsp> jhCdrExamReportRsps = new ArrayList<>();
        for (String s : itemString) {
            JHCdrExamReportRsp reportRsp = (JHCdrExamReportRsp) CXmlUtil.xmlStrToBean("<JHCdrExamReportRsp>"+s+"</JHCdrExamReportRsp>",
                    JHCdrExamReportRsp.class);
            jhCdrExamReportRsps.add(reportRsp);
        }

        String b="<jhCdrExamReportDTO><success>true</success><message>成功</message>" +
                "<item><costs>0.00</costs><examApplyNo>Z20200319282|</examApplyNo><examItem>骨盆正位</examItem><examItemCode>XGPZW</examItemCode><examItemNo></examItemNo><examNo>YF278742</examNo><fileVisitType>0</fileVisitType></item>" +
                "<item><costs>0.00</costs><examApplyNo>Z20200319282|</examApplyNo><examItem>手正斜位（左）</examItem><examItemCode>XSZXWL</examItemCode><examItemNo></examItemNo><examNo>PGZ278770</examNo><fileVisitType>0</fileVisitType></item>" +
                "<item><costs>0.00</costs><examApplyNo>Z20200319282|</examApplyNo><examItem>腕正侧位（左）</examItem><examItemCode>XWZCWL</examItemCode><examItemNo></examItemNo><examNo>PGZ278770</examNo><fileVisitType>0</fileVisitType></item>" +
                "</jhCdrExamReportDTO>";

    }


    public List<JHCdrExamReportItemsRsp> callWebSV2(String webUrl, String methodName, String xmlmesage) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        //1、获取到读取对象
        Client client = dcf.createClient("http://172.31.68.232/ReportJiahe/CSHESBService.asmx?WSDL");
        //client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        List<JHCdrExamReportItemsRsp> jhCdrExamReportRsps = new ArrayList<>();
        try {
            System.out.println("调用");
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(methodName, xmlmesage);
            for (Object object : objects) {
                List<String> itemString = JHStringUtil.subString(new ArrayList<>(), object.toString(), "<item>", "</item>");
                for (String s : itemString) {
                    JHCdrExamReportItemsRsp reportRsp = (JHCdrExamReportItemsRsp) CXmlUtil.xmlStrToBean("<JHCdrExamReportItemsRsp>"+s+"</JHCdrExamReportItemsRsp>",
                            JHCdrExamReportItemsRsp.class);
                    jhCdrExamReportRsps.add(reportRsp);
                }
            }
            return jhCdrExamReportRsps;
        } catch (Exception e) {
            e.printStackTrace();
            return jhCdrExamReportRsps;
        }
    }
}

package io.pingzi.telecheck.domain;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class JxSendSmsTest {


    public    String gets(String phone) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>    <GetUserFromTelegramAndUpdate xmlns=\"http://tempuri.org/\">"
                + "<phone>" + phone
                + "</phone>    </GetUserFromTelegramAndUpdate>"
                + "</soap:Body></soap:Envelope>");
        return sb.toString();
    }

    public   String sendSms(String phone){
        try {
            String soaps = gets(phone);
            if (soaps == null) {
                return null;
            }
            String urlString = "http://10.1.1.3/CheckTelePhoneWebService/CheckTeleWebService.asmx";
            String soapActionString = "http://WebXml.com.cn/GetUserFromTelegramAndUpdate";
            URL url = new URL(urlString);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestProperty("Content-Length", Integer.toString(soaps.length()));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("soapActionString", soapActionString);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream os = httpConn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(soaps);
            osw.close();
            byte[] datas = readInputStream(httpConn.getInputStream());

            String result= new String(datas);
            //打印返回结果
            System.out.println(result);
            return result;
        } catch (Exception e) {
            System.out.println("result:error!");
        }
        return null;
    }



    /**
     * 从输入流中读取数据
     * @param //inStream
     * @return
     * @throws Exception
     */
   public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len = inStream.read(buffer)) !=-1 ){
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();//网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }





}

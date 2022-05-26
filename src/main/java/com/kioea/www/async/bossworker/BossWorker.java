package com.kioea.www.async.bossworker;

import com.kioea.www.urlutil.ConnectUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author sekift
 * @version 1.0
 * @time 2022年04月01日 上午10:03:43
 */
public class BossWorker {
    private static Logger logger =Logger.getLogger(ConnectUtil.class);

    ExecutorService boss = Executors.newFixedThreadPool(1);
    ExecutorService worker = Executors.newFixedThreadPool(10);

    private BlockingQueue<String> queue = new LinkedBlockingQueue<String>(20000);

    //全量推送
    public void pushAll(String str) throws InterruptedException {
        String appid = "wx1234566";
        //生成推送数据
        logger.info("进入boos");
        boss.submit(new Runnable() {
            @Override
            public void run() {
                pubTemplate(str);
            }
        });
        logger.info("退出boos");
        //进行推送
        logger.info("进入worker");
        while (true) {
            String s = queue.poll(5, TimeUnit.SECONDS);
            if (s == null) {
                worker.shutdown();
                while (!worker.isTerminated()) {
                    break;
                }
                break;
            }
            worker.submit(
                    new Runnable() {
                        @Override
                        public void run() {
                            push(s, appid);
                        }
                    }
            );
        }
        logger.info("退出worker");
    }

    private void pubTemplate(String str) {
        logger.info("全量推送开始str=" + str);
        int i = 0;
        int count = 200;
        int num = 200;
        int push_count = 0;
        while (num == count) {
            List<String> wxUsers = buildList(i, 200);
            num = wxUsers.size();
            i++;
            wxUsers.forEach(item -> {
                String s = item + "拼接的";
                try {
                    queue.put(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            push_count = push_count + num;
        }
    }

    private List<String> buildList(int i, int count) {
        List<String> list = new ArrayList<>();
        if (i < 10) {
            for (int j = 1; j <= count; j++) {
                list.add("构造" + j);
            }
        } else {
            list = new ArrayList<>();
        }
        return list;
    }

    //推送
    private boolean push(String s, String appid) {
        logger.info(Thread.currentThread().getName()+"推送开始appid=" + appid + ";s=" + s);
//        //TODO 为了防止过期每次都重新获取token 可优化
//        String accessToken = wxAccountDomain.getAccessToken(appid);
//        String push_url = WxUrl.wx_template_url.replace("ACCESS_TOKEN", accessToken);
//        boolean flag=false;
//        HttpEntity<String> entity = new HttpEntity<>(s, jsonHttpHeaders);
//        ResponseEntity<String> response = restTemplate.exchange(push_url, HttpMethod.POST, entity, String.class);
//        String body = response.getBody();
//        JsonElement jsonElement = JsonParser.parseString(body);
//        JsonObject respParam = jsonElement.getAsJsonObject();
//        Integer err_code = respParam.get("errcode").getAsInt();
//        String errmsg = respParam.get("errmsg").getAsString();
//        if(err_code==0){
//            flag=true;
//        }else{
//            //TODO 记录推送失败数据
//            LogUtil.logError("模板消息发送失败: "+ err_code+","+errmsg);
//            flag=false;
//        }
        return true;
    }
//
//    //记录全量推送日志
//    private void logs(WxTemplatePushLogs WxTemplatePushLogs) {
//        pushLogsMapper.insert(WxTemplatePushLogs);
//    }
//
//    private String buildTemplateString(String openid, WxTemplateVo wxTemplateVo) {
//        WxTemplate wxTemplate = new WxTemplate(wxTemplateVo.getTemplateId(),
//                wxTemplateVo.getTemplateParamList(),
//                wxTemplateVo.getUrl(),
//                wxTemplateVo.getMiniAppid(),
//                wxTemplateVo.getMiniPage());
//        wxTemplate.setToUser(openid);
//        String s = wxTemplate.toJSON();
//        return s;
//    }
}

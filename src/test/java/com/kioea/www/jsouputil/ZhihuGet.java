package com.kioea.www.jsouputil;

import java.util.HashMap;
import java.util.Map;

import com.kioea.www.urlutil.HttpUtil;

/**
 * 
 * @author:Administrator
 * @time:2015-9-30 下午03:05:06
 * @version:
 */
public class ZhihuGet {

	public static void main(String args[]){
//		System.out.println(JsoupUtil.getByAttrId("http://www.qiushibaike.com/textnew/page/2?s=4835917", "zh-question-title").text());
//		System.out.println(JsoupUtil.getByAttrClass("http://www.qiushibaike.com", "article block untagged mb15").text());
//		String str = JsoupUtil.getDocByConnect("https://appleid.apple.com/account/").toString();
//		try {
//			FileUtils.writeStringToFile(new File("D:\\img.txt"), str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		Map<String,Object> header = new HashMap<String, Object>();
		Map<String,String> params = new HashMap<String, String>();
		header.put("Accept", "application/json");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		header.put("Connection", "keep-alive");
		header.put("Content-Length", "16");
		header.put("Content-Type", "application/json; charset=UTF-8");
		header.put("Cookie", "xp_ci=3z2EoJLjzCHHz56XzBY2zs5PalAqG; dssid2=ea366318-2341-4fd9-9e13-5469b6a2386b; dssf=1; as_sfa=Mnxjbnxjbnx8emhfQ058Y29uc3VtZXJ8aW50ZXJuZXR8MHwwfDE=; optimizelySegments=%7B%22382310072%22%3A%22false%22%2C%22381740105%22%3A%22none%22%2C%22381100110%22%3A%22ff%22%2C%22382310071%22%3A%22referral%22%7D; optimizelyEndUserId=oeu1456278580074r0.965056927339509; optimizelyBuckets=%7B%224971350625%22%3A%224965541989%22%7D; s_fid=0C90174671B2962D-3DE23579BEF93B79; pxro=1; aid=539E0C574E246B58B9BA22324A68E27FB6ACA7A51B0DDFF001CC0D7CE90084ABAA8D3D6A30F86A9FDA3364359751B5F652E4A903AB8FF5024DBC1FFD5AFF9533774BBB370A41F71273F2A90FB3B96F79AF401522DD4CED357FE8AAE1D0B24478919E43E53A1634F9B59B8E9954B59BF80C267870B5C0823BBA85B64373E40D45FAC0EA91F17ED30BB4B05A3DEC99C47A541F44DBB3559E9FDB3D13EF7E9265571B2CDF297AADA7FF825D4238DFF148EEB05465493991931CA004E3C980F8426EF14A57E2F3C25379E9FD1FA0224744ECE9DE5EB46E23D4F1EF4F0A49133902BC90C11C3271F09C287F2F1E3F84DB371DB976BF2A16BDBA4F4ECE6029A527DED7; idclient=web; dslang=CN-ZH; site=CHN");
		header.put("Host", "appleid.apple.com");
		header.put("Referer", "https://appleid.apple.com/account/");
		header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:43.0) Gecko/20100101 Firefox/43.0");
		header.put("X-Apple-Api-Key", "cbf64fd6843ee630b463f358ea0b707b");
		header.put("X-Apple-I-FD-Client-Info", "{\"U\":\"Mozilla/5.0 (Windows NT 6.1; rv:43.0) Gecko/20100101 Firefox/43.0\",\"L\":\"zh-CN\",\"Z\":\"GMT+08:00\",\"V\":\"1.1\",\"F\":\"FWa44j1e3NlY5BSo9z4ofjb75PaK4Vpjt.gEngMQBTuX38.WUMnGWVQdg1kzDlSgyyIT1nj.rIN4WzcjftckcKyAd65hz74WySXvOxwaw4a8vSPz1ar6zdstlDJFW73E4QCwby91R4DQ1zjkD6myjaY2_GGEQIgwe98vDdYejftckuyPBDjaY2ftckZZLQ084akJ728_vSNKSHgSQeLaD.SAxN4t1VKWZWuxbuJjkWiJ7LDoA4pFtgMA2a3qPWd9Py.EKY.6ekcV.s0rMukAm4.f282pBzB0y4Kp0iJ7LDdVMNNXIgPjbl9YTCM_.g_RG8qCMnrgy4TxGdoy.Mvz4Nhy9qQtEwJ6wiySvtm0ad_GnmkqTN0ripjlRiwerbXh8bTg_RCQwMAj9htsfHOrye5rvR7lY6SFGY5BOgkLT0XxU..6lu\"}");
		header.put("X-Requested-With", "XMLHttpRequest");
		header.put("scnt", "f763777391d11485da929b34357b0c3b");
		
		
		params.put("type","IMAGE");
		String result = HttpUtil.post("https://appleid.apple.com/captcha", header,params, 5000, 50000, "utf-8");
		System.out.println(result);
		
	}
}

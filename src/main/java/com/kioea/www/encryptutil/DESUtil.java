package com.kioea.www.encryptutil;

import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.kioea.www.jsonutil.JsonUtils;
import com.kioea.www.stringutil.StringUtil;

public class DESUtil {
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish  

    private static final String _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    //keybyte为加密密钥，长度为24字节    
    //src为被加密的数据缓冲区（源）    
    public static byte[] encrypt(String keyString, byte[] src) {
        byte[] keybyte = keyString.getBytes();
        return encrypt(keybyte, src);
    }

    //keybyte为加密密钥，长度为24字节
    //src为加密后的缓冲区
    public static byte[] decrypt(String keyString, byte[] src) {
        byte[] keybyte = keyString.getBytes();
        return decrypt(keybyte, src);
    }

    //keybyte为加密密钥，长度为24字节    
    //src为被加密的数据缓冲区（源）    
    public static byte[] encrypt(byte[] keybyte, byte[] src) {
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //加密            
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    //keybyte为加密密钥，长度为24字节
    //src为加密后的缓冲区
    public static byte[] decrypt(byte[] keybyte, byte[] src) {
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    // 键盘与key code对照表
    public static final String[] KEY_CODE = {"",
            "", "", "", "", "", "", "", "backsapce", "tab", "",// 10
            "", "", "enter", "", "", "shift", "ctrl", "alt", "pause/break", "caps lock",// 20
            "", "", "", "", "", "", "escape", "", "", "",//30
            "", "空格", "page up", "page down", "end", "home", "left arrow", "up arrow", "right arrow",//40
            "down arrow", "", "", "", "", "insert", "delete", "",//48
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "", "", "", "", "", "", "",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "left window key", "right window key", "select key", "", "",
            "numpad 0", "numpad 1", "numpad 2", "numpad 3", "numpad 4", "numpad 5", "numpad 6", "numpad 7", "numpad 8", "numpad 9",
            "multiply(*)", "add(+)", "numpad enter", "subtract(-)", "decimal point(.)", "divide(/)",
            "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10", "f11", "f12",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "num lock", "scroll lock",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "搜索", "收藏", "浏览器", "静音", "音量减", "音量加", "", "", "", "停止", "邮件", "", "", "", "", "",
            "semi-colon(;:)", "equal sign(=+)", "comma(,<)", "dash(-_)", "period(.>)", "forward slash(/?)", "grave accent(`~)",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "",
            "open bracket([{)", "back slash(/|)", "close braket(]})", "single quote('\")"};

    /**
     * 解密JS加密的内容<br>
     * JS加密算法参见：<a href="https://blog.csdn.net/kuangshp128/article/details/75200077">Base64加解密的方法</a><br>
     * 算法于2019-4-1由前端指定，对用户操作行为的数据进行简单加密，本方法解密。
     *
     * @param input JS加密后的密文
     * @return 解密后明文
     */
    public static String jsDecrypt(String input) {
        if (StringUtil.isNullOrBlank(input)) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        char enc1, enc2, enc3, enc4;
        String chr1, chr2, chr3;
        int i = 0;
        //input = input.replace("", "");
        while (i < input.length()) {
            enc1 = (char) _keyStr.indexOf(input.charAt(i++));
            enc2 = (char) _keyStr.indexOf(input.charAt(i++));
            enc3 = (char) _keyStr.indexOf(input.charAt(i++));
            enc4 = (char) _keyStr.indexOf(input.charAt(i++));
            chr1 = String.valueOf((char) ((enc1 << 2) | (enc2 >> 4)));
            chr2 = String.valueOf((char) (((enc2 & 15) << 4) | (enc3 >> 2)));
            chr3 = String.valueOf((char) (((enc3 & 3) << 6) | enc4));
            output.append(String.valueOf(chr1));
            if (enc3 != 64) {
                output.append(String.valueOf(chr2));
            }
            if (enc4 != 64) {
                output.append(String.valueOf(chr3));
            }
        }
        return encodeUtf8(output).toString();
    }

    /**
     * JS加密
     */
    public static String jsEncrypt(String input) {
        if (StringUtil.isNullOrBlank(input)) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        int chr1 = 0, chr2 = 0, chr3 = 0, enc1 = 0, enc2 = 0, enc3 = 0, enc4 = 0;
        int i = 0;
        input = encodeUtf8(new StringBuilder(input)).toString();
        while (i < input.length()) {
            try {
                chr1 = (char) input.codePointAt(i++);
            } catch (Exception e) {
                chr1 = 0;
            }

            try {
                chr2 = (char) input.codePointAt(i++);
            } catch (Exception e) {
                chr2 = 0;
            }

            try {
                chr3 = (char) input.codePointAt(i++);
            } catch (Exception e) {
                chr3 = 0;
            }
            enc1 = (chr1 >> 2);
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (chr2 == 0) {
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = enc4 = 64;
            } else if (chr3 == 0) {
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = 64;
            }
            output.append(_keyStr.charAt(enc1)).append(_keyStr.charAt(enc2)).append(_keyStr.charAt(enc3)).append(
                    _keyStr.charAt(enc4));
        }
        return output.toString();
    }

    /**
     * utf-8处理
     *
     * @param str
     * @return
     */
    public static StringBuilder encodeUtf8(StringBuilder str) {
        // str = str.replace("\r\n", "\n");
        StringBuilder utfText = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < 128) {
                utfText.append(String.valueOf(c));
            } else if (c >= 128 && c < 2048) {
                utfText.append(String.valueOf(c >> 6));
                utfText.append(String.valueOf(c & 63));
            } else {
                utfText.append(String.valueOf(c >> 12));
                utfText.append(String.valueOf(((c >> 6) & 63) | 128));
                utfText.append(String.valueOf((c & 63) | 128));
            }
        }
        return utfText;
    }

    public static void main(String[] args) {
//    	for(int i=0;i<223;i++){
//    	    System.out.println(i+" "+KEY_CODE[i]);
//    	}
        String a = "fwfe.fwef";
        System.out.println(a);
        System.out.println(DESUtil.jsEncrypt(a));
        System.out.println(DESUtil.jsDecrypt("ZndmZS5md2Vm"));

//    	String ciphertext="eyJjIjpbeyJ4IjoiMWQ2IiwieSI6IjEzOCIsInQiOiIxNjlmZmJjMTc0OSJ9LHsieCI6IjFkNiIsInkiOiIxMzgiLCJ0IjoiMTY5ZmZiYzE3YTEifSx7IngiOiJjZCIsInkiOiIxMzkiLCJ0IjoiMTY5ZmZiYzE5NGEifSx7IngiOiJjZCIsInkiOiIxMzkiLCJ0IjoiMTY5ZmZiYzE5YTkifSx7IngiOiJjZCIsInkiOiIxM2MiLCJ0IjoiMTY5ZmZiYzFhMjEifSx7IngiOiJjZCIsInkiOiIxNTYiLCJ0IjoiMTY5ZmZiYzFhNTIifSx7IngiOiIxMGEiLCJ5IjoiMTFjIiwidCI6IjE2OWZmYmMxYzExIn0seyJ4IjoiMTBhIiwieSI6IjExYyIsInQiOiIxNjlmZmJjMWM2YSJ9LHsieCI6IjE1NiIsInkiOiJhMiIsInQiOiIxNjlmZmJjMWQ1NCJ9LHsieCI6IjE1YiIsInkiOiJhMyIsInQiOiIxNjlmZmJjMWRhMCJ9LHsieCI6Ijk5IiwieSI6IjExYiIsInQiOiIxNjlmZmJjMWYzOSJ9LHsieCI6Ijk4IiwieSI6IjExYyIsInQiOiIxNjlmZmJjMWY5OSJ9LHsieCI6ImRhIiwieSI6IjE3MyIsInQiOiIxNjlmZmJjMjA1OSJ9LHsieCI6ImRhIiwieSI6IjE3NCIsInQiOiIxNjlmZmJjMjBhYiJ9LHsieCI6IjFiNyIsInkiOiIxMzIiLCJ0IjoiMTY5ZmZiYzI1MzMifSx7IngiOiIxYjciLCJ5IjoiMTMyIiwidCI6IjE2OWZmYmMyNWEwIn0seyJ4IjoiMWQ2IiwieSI6IjEzNCIsInQiOiIxNjlmZmJjMzk2OSJ9LHsieCI6IjFkNyIsInkiOiIxMzQiLCJ0IjoiMTY5ZmZiYzM5ZTMifSx7IngiOiIxMTMiLCJ5IjoiMTEzIiwidCI6IjE2OWZmYmM0NmQxIn0seyJ4IjoiYTMiLCJ5IjoiMWFhIiwidCI6IjE2OWZmYmM0ODdhIn0seyJ4IjoiZDkiLCJ5IjoiYTgiLCJ0IjoiMTY5ZmZiYzQ5NDgifSx7IngiOiJkOSIsInkiOiJhOCIsInQiOiIxNjlmZmJjNDlmMSJ9LHsieCI6ImE5IiwieSI6ImVlIiwidCI6IjE2OWZmYmM0YjZjIn0seyJ4IjoiN2QiLCJ5IjoiMWY1IiwidCI6IjE2OWZmYmM0Y2IxIn0seyJ4IjoiY2UiLCJ5IjoiYmQiLCJ0IjoiMTY5ZmZiYzRkOTIifSx7IngiOiJkMyIsInkiOiJiYyIsInQiOiIxNjlmZmJjNGRmMyJ9LHsieCI6ImVkIiwieSI6IjE4MCIsInQiOiIxNjlmZmJjNGY3YSJ9LHsieCI6ImY3IiwieSI6IjE4NCIsInQiOiIxNjlmZmJjNGZkMiJ9LHsieCI6IjFlMyIsInkiOiIxODYiLCJ0IjoiMTY5ZmZiYzUxYWEifSx7IngiOiIxZTMiLCJ5IjoiMTg2IiwidCI6IjE2OWZmYmM1MjAyIn1dLCJrIjpbeyJrYyI6IjE0IiwidCI6IjE2OWZmYmMyOTIyIn0seyJrYyI6IjIwIiwidCI6IjE2OWZmYmMyZGQyIn0seyJrYyI6IjIwIiwidCI6IjE2OWZmYmMzMTA1In0seyJrYyI6IjkiLCJ0IjoiMTY5ZmZiYzM2MTMifSx7ImtjIjoiMTAiLCJ0IjoiMTY5ZmZiYzM2NWMifSx7ImtjIjoiMTQiLCJ0IjoiMTY5ZmZiYzM3YTAifSx7ImtjIjoiMTAiLCJ0IjoiMTY5ZmZiYzNiMzQifSx7ImtjIjoiMTQiLCJ0IjoiMTY5ZmZiYzNkMGQifSx7ImtjIjoiMTEiLCJ0IjoiMTY5ZmZiYzNkMGUifSx7ImtjIjoiMTQiLCJ0IjoiMTY5ZmZiYzQyYTgifV0sImN0IjoiMWUiLCJrdCI6IjE0IiwicyI6IjE2OWZmYmFhZDFmIn0=";
//    	String str = DESUtil.jsDecrypt(ciphertext);
//    	System.out.println(str);
        String str = "{\"c\":[{\"x\":\"1f3\",\"y\":\"165\",\"t\":\"16b693e50b9\"},{\"x\":\"1f3\",\"y\":\"165\",\"t\":\"16b693e5100\"},{\"x\":\"226\",\"y\":\"1a0\",\"t\":\"16b693e5508\"},{\"x\":\"226\",\"y\":\"1a0\",\"t\":\"16b693e5558\"},{\"x\":\"139\",\"y\":\"d2\",\"t\":\"16b693e5902\"},{\"x\":\"139\",\"y\":\"d2\",\"t\":\"16b693e5940\"},{\"x\":\"134\",\"y\":\"126\",\"t\":\"16b693e6333\"},{\"x\":\"134\",\"y\":\"126\",\"t\":\"16b693e6379\"},{\"x\":\"0\",\"y\":\"0\",\"t\":\"16b693e6c4a\"},{\"x\":\"0\",\"y\":\"0\",\"t\":\"16b693e6c56\"},{\"x\":\"0\",\"y\":\"0\",\"t\":\"16b693e7669\"},{\"x\":\"0\",\"y\":\"0\",\"t\":\"16b693e766c\"},{\"x\":\"1a6\",\"y\":\"190\",\"t\":\"16b693edeef\"},{\"x\":\"1a6\",\"y\":\"190\",\"t\":\"16b693edf2d\"}],\"k\":[{\"kc\":\"56\",\"t\":\"16b693e53da\"},{\"kc\":\"11\",\"t\":\"16b693e53db\"},{\"kc\":\"be\",\"t\":\"16b693e53dc\"},{\"kc\":\"0\",\"t\":\"16b693e6c6f\"},{\"kc\":\"0\",\"t\":\"16b693e7675\"},{\"kc\":\"9\",\"t\":\"16b693e9d22\"},{\"kc\":\"10\",\"t\":\"16b693e9d25\"},{\"kc\":\"10\",\"t\":\"16b693e9d27\"},{\"kc\":\"11\",\"t\":\"16b693e9d28\"},{\"kc\":\"11\",\"t\":\"16b693e9d29\"},{\"kc\":\"9\",\"t\":\"16b693e9d2a\"},{\"kc\":\"12\",\"t\":\"16b693e9d2b\"},{\"kc\":\"9\",\"t\":\"16b693e9d2c\"},{\"kc\":\"12\",\"t\":\"16b693e9d2e\"},{\"kc\":\"9\",\"t\":\"16b693e9d2f\"},{\"kc\":\"9\",\"t\":\"16b693e9dd2\"},{\"kc\":\"10\",\"t\":\"16b693e9dd3\"},{\"kc\":\"10\",\"t\":\"16b693e9dd4\"},{\"kc\":\"11\",\"t\":\"16b693e9dd5\"},{\"kc\":\"11\",\"t\":\"16b693e9dd6\"},{\"kc\":\"9\",\"t\":\"16b693e9dd7\"},{\"kc\":\"12\",\"t\":\"16b693e9dd8\"},{\"kc\":\"9\",\"t\":\"16b693e9dda\"},{\"kc\":\"12\",\"t\":\"16b693e9ddc\"},{\"kc\":\"9\",\"t\":\"16b693e9ddd\"},{\"kc\":\"9\",\"t\":\"16b693edc63\"},{\"kc\":\"10\",\"t\":\"16b693edc64\"},{\"kc\":\"10\",\"t\":\"16b693edc66\"},{\"kc\":\"11\",\"t\":\"16b693edc66\"},{\"kc\":\"11\",\"t\":\"16b693edc67\"},{\"kc\":\"9\",\"t\":\"16b693edc68\"},{\"kc\":\"12\",\"t\":\"16b693edc68\"},{\"kc\":\"9\",\"t\":\"16b693edc69\"},{\"kc\":\"12\",\"t\":\"16b693edc6a\"},{\"kc\":\"9\",\"t\":\"16b693edc6b\"},{\"kc\":\"9\",\"t\":\"16b693edcff\"},{\"kc\":\"10\",\"t\":\"16b693edd01\"},{\"kc\":\"10\",\"t\":\"16b693edd02\"},{\"kc\":\"11\",\"t\":\"16b693edd03\"},{\"kc\":\"11\",\"t\":\"16b693edd03\"},{\"kc\":\"9\",\"t\":\"16b693edd04\"},{\"kc\":\"12\",\"t\":\"16b693edd04\"},{\"kc\":\"9\",\"t\":\"16b693edd05\"},{\"kc\":\"12\",\"t\":\"16b693edd05\"},{\"kc\":\"9\",\"t\":\"16b693edd06\"}],\"ct\":\"e\",\"kt\":\"35\",\"s\":\"16b693e417d\"}";
        Map<String, Object> json = JsonUtils.toBean(str, Map.class);
        List<Map<String, String>> listC = (List<Map<String, String>>) json.get("c");
        List<Map<String, String>> listK = (List<Map<String, String>>) json.get("k");
        System.out.println(listC.size());
        System.out.println(listK.size());


        for (Map<String, String> mapC : listC) {
            System.out.println(Long.parseLong(mapC.get("x"), 16) + " " + Long.parseLong(mapC.get("y"), 16) + " " + Long.parseLong(mapC.get("t"), 16));
        }

        for (Map<String, String> mapK : listK) {
            System.out.println(Long.parseLong(mapK.get("kc"), 16) + " " + KEY_CODE[(int) Long.parseLong(mapK.get("kc"), 16)] + " " + Long.parseLong(mapK.get("t"), 16));
        }

        System.out.println(Long.parseLong((String) json.get("ct"), 16));
        System.out.println(Long.parseLong((String) json.get("kt"), 16));
        System.out.println(Long.parseLong((String) json.get("s"), 16));
    }
}

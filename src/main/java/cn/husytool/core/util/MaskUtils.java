package cn.husytool.core.util;

/**
 * 数据脱敏工具
 *
 * @author husy
 * @date 2020/6/16
 */
public class MaskUtils {
    /**
     * 手机号码前三后四脱敏
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (mobile == null || mobile.length() == 0) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 固定电话脱敏，保留后四位
     * @param phone
     * @return
     */
    public static String maskPhone(String phone) {
        if (phone == null || phone.length() == 0) {
            return phone;
        }
        return new String(new char[phone.length() - 10]).replace("\0", "*") + phone.substring(phone.length() - 4);
    }

    /**
     * 身份证前三后四脱敏
     *
     * @param idCard
     * @return
     */
    public static String maskIdCard(String idCard) {
        if (idCard == null || idCard.length() < 8) {
            return idCard;
        }
        return idCard.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    /**
     * 护照前2后3位脱敏，护照一般为8或9位
     *
     * @param id
     * @return
     */
    public static String maskPassport(String id) {
        if (id == null || id.length() < 8) {
            return id;
        }
        return id.substring(0, 2) + new String(new char[id.length() - 5]).replace("\0", "*") + id.substring(id.length() - 3);
    }

    /**
     * 银行账户脱敏前六后四
     *
     * @param id
     * @return
     */
    public static String maskBankAccount(String id) {
        if (id == null || id.length() < 16) {
            return id;
        }
        return id.substring(0, 6) + new String(new char[id.length() - 10]).replace("\0", "*") + id.substring(id.length() - 4);
    }

    /**
     * 邮箱脱敏，邮箱第一个字符和’@‘之后的原文显示，第一个字符之后’@‘之前的，显示为’****’
     * @param url
     * @return
     */
    public static String maskEmail(String url){
        if (url == null || url.length() < 16) {
            return url;
        }
        return url.replaceAll("(^\\w)[^@]*(@.*$)", "$1****$2");
    }

    /**
     * 密码脱敏全部显示为'*'
     * @param password
     * @return
     */
    public static String maskPassword(String password){
        if (password == null) {
            return password;
        }
        return password.replaceAll(".","*");
    }

    /**
     * 中文姓名脱敏，保留姓氏
     * @param name
     * @return
     */
    public static String maskChinaName(String name){
        if (name == null) {
            return name;
        }
        if (name.length()<4){
            return name.substring(0, 1)+ new String(new char[name.length() - 1]).replace("\0", "*");
        }else {
            return name.substring(0, 2)+ new String(new char[name.length() - 2]).replace("\0", "*");
        }
    }
}

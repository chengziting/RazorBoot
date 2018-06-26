package com.chengziting.razor.utils;

/**
 * Created by user on 2018/4/26.
 */
public class StringUtils {
    public static String replaceIfNullOrEmpty(String src,String replace){
        if(src != null && !"".equals(src.trim())){
            return src;
        }
        return replace;
    }

    public static boolean isNullOrEmpty(String src){
        if(src == null) return true;
        if("".equals(src.trim())) return true;
        return false;
    }

    public static String removeStr(String source,String target,int position){
        if(isNullOrEmpty(source)) return source;
        String tmp = source;
        switch (position){
            case 1:
                if(source.startsWith(target)){
                    tmp = source.substring(target.length(),source.length());
                }
                break;
            case 2:
                if(source.endsWith(target)){
                    tmp = source.substring(0,source.length() - target.length());
                }
                break;
            case 0:
                tmp = removeStr(source,target,1);
                tmp = removeStr(tmp,target,2);
                break;
        }

        return tmp;
    }

}

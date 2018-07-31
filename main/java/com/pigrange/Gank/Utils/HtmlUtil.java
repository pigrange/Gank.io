package com.pigrange.Gank.Utils;

import com.pigrange.Gank.Model.ZhiHuNews;

import java.util.List;

public class HtmlUtil {

    private static String addCss(String css) {
        return "<link rel=\"stylesheet\"  href=\"" + css + "\">";
    }

    private static String addJs(String js) {
        return "<script src=\"" + js + "\"></script>";
    }

    private static String addHeader(ZhiHuNews zhInfoBean) {
        String header = "<head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"><meta name=\"apple-itunes-app\" content=\"app-id=639087967, app-argument=zhihudaily://story/9582980\"><meta name=\"viewport\" content=\"user-scalable=no, width=device-width\">";
        List<String> css = zhInfoBean.getCss();
        if (css.size() > 0) {
            for (String cs : css) {
                header = header + addCss(cs);
            }
        }

        List<String> js = zhInfoBean.getJs();
        if (js.size() > 0) {
            for (String j : js) {
                header = header + addJs(j);
            }
        }
        header = header + "</head>";
        return header;
    }

    public static String getHtml(ZhiHuNews zhInfoBean) {
        String html = "<html>";

        String body = zhInfoBean.getBody().replace("<div class=\"img-place-holder\"></div>","");
        html = html + addHeader(zhInfoBean) + "<body>" + body + "</body></html>";


        return html;
    }
}


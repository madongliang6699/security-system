package com.security.multisupport.utils;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.google.common.collect.Lists;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
//import org.slf4j.//logger;
//import org.slf4j.//loggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipOutputStream;


/**
 * 文件操作工具类
 * 实现文件的创建、删除、复制、压缩、解压以及目录的创建、删除、复制、压缩解压等功能
 *
 * @author wangxuekai
 * @File FileUtil.java
 * @Date 2018/1/11
 * @Time 上午11:11
 * @Encoding UTF-8
 * @Description
 */
public class FileUtil extends FileUtils {

//    private static //logger //logger = //loggerFactory.get//logger(FileUtil.class);

    // region 静态变量维护

    /**
     * 文件后缀名获取正则文本
     */
    private static final String FILE_TYPE_STR = "(?<=\\w{0,50}.)\\w+(?=$)";
    /**
     * 文件后缀名获取正则对象
     */
    private static final Pattern FILE_TYPE_PAT = Pattern.compile(FILE_TYPE_STR);

    /**
     * 文件名获取正则文本
     */
    private static final String FILE_NAME_STR = "(?<=^)\\w+(?=\\.\\w{0,10}$)";
    /**
     * 文件名获取正则对象
     */
    private static final Pattern FILE_NAME_PAT = Pattern.compile(FILE_NAME_STR);

    /**
     * 循环读取文件大小(byte)
     */
    private static final int FILE_READ_SIZE = 1024;

    /**
     * 循环读取文件起始偏移位置
     */
    private static final int FILE_READ_START = 0;

    /**
     * 上传文件所有文件类型匹配
     */
    private static final String ALL_FILE_TYPE = "*";

    /**
     * Linux系统文件目录分隔符
     */
    private static final String LINUX_FILE_SEPARATOR = "/";

    /**
     * Windows系统文件目录分隔符
     */
    private static final String WINDOWS_FILE_SEPARATOR = "\\";

    /**
     * 下载文件响应头Content-Type映射Map
     */
    private static final HashMap<String, String> CONTENT_TYPE_MAP = new HashMap<>(340);

    /**
     * 默认字符集
     */
    public static final String DEFAULT_CHARSET = StringUtil.CHARSET_NAME;

    /**
     * 装载Http响应文件数据
     */
    static {
        CONTENT_TYPE_MAP.put("*", "application/octet-stream");
        CONTENT_TYPE_MAP.put("acp", "audio/x-mei-aac");
        CONTENT_TYPE_MAP.put("aif", "audio/aiff");
        CONTENT_TYPE_MAP.put("aiff", "audio/aiff");
        CONTENT_TYPE_MAP.put("asa", "text/asa");
        CONTENT_TYPE_MAP.put("asp", "text/asp");
        CONTENT_TYPE_MAP.put("au", "audio/basic");
        CONTENT_TYPE_MAP.put("awf", "application/vnd.adobe.workflow");
        CONTENT_TYPE_MAP.put("bmp", "application/x-bmp");
        CONTENT_TYPE_MAP.put("c4t", "application/x-c4t");
        CONTENT_TYPE_MAP.put("cal", "application/x-cals");
        CONTENT_TYPE_MAP.put("cdf", "application/x-netcdf");
        CONTENT_TYPE_MAP.put("cel", "application/x-cel");
        CONTENT_TYPE_MAP.put("cg4", "application/x-g4");
        CONTENT_TYPE_MAP.put("cit", "application/x-cit");
        CONTENT_TYPE_MAP.put("cml", "text/xml");
        CONTENT_TYPE_MAP.put("cmx", "application/x-cmx");
        CONTENT_TYPE_MAP.put("crl", "application/pkix-crl");
        CONTENT_TYPE_MAP.put("csi", "application/x-csi");
        CONTENT_TYPE_MAP.put("cut", "application/x-cut");
        CONTENT_TYPE_MAP.put("dbm", "application/x-dbm");
        CONTENT_TYPE_MAP.put("dcd", "text/xml");
        CONTENT_TYPE_MAP.put("der", "application/x-x509-ca-cert");
        CONTENT_TYPE_MAP.put("dib", "application/x-dib");
        CONTENT_TYPE_MAP.put("doc", "application/msword");
        CONTENT_TYPE_MAP.put("docx", "application/msword");
        CONTENT_TYPE_MAP.put("drw", "application/x-drw");
        CONTENT_TYPE_MAP.put("dwg", "application/x-dwg");
        CONTENT_TYPE_MAP.put("dxf", "application/x-dxf");
        CONTENT_TYPE_MAP.put("emf", "application/x-emf");
        CONTENT_TYPE_MAP.put("ent", "text/xml");
        CONTENT_TYPE_MAP.put("etd", "application/x-ebx");
        CONTENT_TYPE_MAP.put("fax", "image/fax");
        CONTENT_TYPE_MAP.put("fif", "application/fractals");
        CONTENT_TYPE_MAP.put("frm", "application/x-frm");
        CONTENT_TYPE_MAP.put("gbr", "application/x-gbr");
        CONTENT_TYPE_MAP.put("gif", "image/gif");
        CONTENT_TYPE_MAP.put("gp4", "application/x-gp4");
        CONTENT_TYPE_MAP.put("hmr", "application/x-hmr");
        CONTENT_TYPE_MAP.put("hpl", "application/x-hpl");
        CONTENT_TYPE_MAP.put("hrf", "application/x-hrf");
        CONTENT_TYPE_MAP.put("htc", "text/x-component");
        CONTENT_TYPE_MAP.put("html", "text/html");
        CONTENT_TYPE_MAP.put("htx", "text/html");
        CONTENT_TYPE_MAP.put("iff", "application/x-iff");
        CONTENT_TYPE_MAP.put("igs", "application/x-igs");
        CONTENT_TYPE_MAP.put("img", "application/x-img");
        CONTENT_TYPE_MAP.put("isp", "application/x-internet-signup");
        CONTENT_TYPE_MAP.put("java", "java/*");
        CONTENT_TYPE_MAP.put("jpeg", "image/jpeg");
        CONTENT_TYPE_MAP.put("jsp", "text/html");
        CONTENT_TYPE_MAP.put("lar", "application/x-laplayer-reg");
        CONTENT_TYPE_MAP.put("lavs", "audio/x-liquid-secure");
        CONTENT_TYPE_MAP.put("lmsff", "audio/x-la-lms");
        CONTENT_TYPE_MAP.put("ltr", "application/x-ltr");
        CONTENT_TYPE_MAP.put("m2v", "video/x-mpeg");
        CONTENT_TYPE_MAP.put("m4e", "video/mpeg4");
        CONTENT_TYPE_MAP.put("man", "application/x-troff-man");
        CONTENT_TYPE_MAP.put("mfp", "application/x-shockwave-flash");
        CONTENT_TYPE_MAP.put("mhtml", "message/rfc822");
        CONTENT_TYPE_MAP.put("mid", "audio/mid");
        CONTENT_TYPE_MAP.put("mil", "application/x-mil");
        CONTENT_TYPE_MAP.put("mnd", "audio/x-musicnet-download");
        CONTENT_TYPE_MAP.put("mocha", "application/x-javascript");
        CONTENT_TYPE_MAP.put("mp1", "audio/mp1");
        CONTENT_TYPE_MAP.put("mp2v", "video/mpeg");
        CONTENT_TYPE_MAP.put("mp4", "video/mpeg4");
        CONTENT_TYPE_MAP.put("mpd", "application/vnd.ms-project");
        CONTENT_TYPE_MAP.put("mpeg", "video/mpg");
        CONTENT_TYPE_MAP.put("mpga", "audio/rn-mpeg");
        CONTENT_TYPE_MAP.put("mps", "video/x-mpeg");
        CONTENT_TYPE_MAP.put("mpv", "video/mpg");
        CONTENT_TYPE_MAP.put("mpw", "application/vnd.ms-project");
        CONTENT_TYPE_MAP.put("mtx", "text/xml");
        CONTENT_TYPE_MAP.put("net", "image/pnetvue");
        CONTENT_TYPE_MAP.put("nws", "message/rfc822");
        CONTENT_TYPE_MAP.put("out", "application/x-out");
        CONTENT_TYPE_MAP.put("p12", "application/x-pkcs12");
        CONTENT_TYPE_MAP.put("p7c", "application/pkcs7-mime");
        CONTENT_TYPE_MAP.put("p7r", "application/x-pkcs7-certreqresp");
        CONTENT_TYPE_MAP.put("pc5", "application/x-pc5");
        CONTENT_TYPE_MAP.put("pcl", "application/x-pcl");
        CONTENT_TYPE_MAP.put("pdx", "application/vnd.adobe.pdx");
        CONTENT_TYPE_MAP.put("pgl", "application/x-pgl");
        CONTENT_TYPE_MAP.put("pko", "application/vnd.ms-pki.pko");
        CONTENT_TYPE_MAP.put("plg", "text/html");
        CONTENT_TYPE_MAP.put("plt", "application/x-plt");
        CONTENT_TYPE_MAP.put("ppa", "application/vnd.ms-powerpoint");
        CONTENT_TYPE_MAP.put("pps", "application/vnd.ms-powerpoint");
        CONTENT_TYPE_MAP.put("prf", "application/pics-rules");
        CONTENT_TYPE_MAP.put("prt", "application/x-prt");
        CONTENT_TYPE_MAP.put("pwz", "application/vnd.ms-powerpoint");
        CONTENT_TYPE_MAP.put("ra", "audio/vnd.rn-realaudio");
        CONTENT_TYPE_MAP.put("ras", "application/x-ras");
        CONTENT_TYPE_MAP.put("rdf", "text/xml");
        CONTENT_TYPE_MAP.put("red", "application/x-red");
        CONTENT_TYPE_MAP.put("rjs", "application/vnd.rn-realsystem-rjs");
        CONTENT_TYPE_MAP.put("rlc", "application/x-rlc");
        CONTENT_TYPE_MAP.put("rm", "application/vnd.rn-realmedia");
        CONTENT_TYPE_MAP.put("rmi", "audio/mid");
        CONTENT_TYPE_MAP.put("rmm", "audio/x-pn-realaudio");
        CONTENT_TYPE_MAP.put("rms", "application/vnd.rn-realmedia-secure");
        CONTENT_TYPE_MAP.put("rmx", "application/vnd.rn-realsystem-rmx");
        CONTENT_TYPE_MAP.put("rp", "image/vnd.rn-realpix");
        CONTENT_TYPE_MAP.put("rsml", "application/vnd.rn-rsml");
        CONTENT_TYPE_MAP.put("rv", "video/vnd.rn-realvideo");
        CONTENT_TYPE_MAP.put("sat", "application/x-sat");
        CONTENT_TYPE_MAP.put("sdw", "application/x-sdw");
        CONTENT_TYPE_MAP.put("slb", "application/x-slb");
        CONTENT_TYPE_MAP.put("slk", "drawing/x-slk");
        CONTENT_TYPE_MAP.put("smil", "application/smil");
        CONTENT_TYPE_MAP.put("snd", "audio/basic");
        CONTENT_TYPE_MAP.put("sor", "text/plain");
        CONTENT_TYPE_MAP.put("spl", "application/futuresplash");
        CONTENT_TYPE_MAP.put("ssm", "application/streamingmedia");
        CONTENT_TYPE_MAP.put("stl", "application/vnd.ms-pki.stl");
        CONTENT_TYPE_MAP.put("sty", "application/x-sty");
        CONTENT_TYPE_MAP.put("swf", "application/x-shockwave-flash");
        CONTENT_TYPE_MAP.put("tg4", "application/x-tg4");
        CONTENT_TYPE_MAP.put("tif", "image/tiff");
        CONTENT_TYPE_MAP.put("tiff", "image/tiff");
        CONTENT_TYPE_MAP.put("top", "drawing/x-top");
        CONTENT_TYPE_MAP.put("tsd", "text/xml");
        CONTENT_TYPE_MAP.put("uin", "application/x-icq");
        CONTENT_TYPE_MAP.put("vcf", "text/x-vcard");
        CONTENT_TYPE_MAP.put("vdx", "application/vnd.visio");
        CONTENT_TYPE_MAP.put("vpg", "application/x-vpeg005");
        CONTENT_TYPE_MAP.put("vsw", "application/vnd.visio");
        CONTENT_TYPE_MAP.put("vtx", "application/vnd.visio");
        CONTENT_TYPE_MAP.put("wav", "audio/wav");
        CONTENT_TYPE_MAP.put("wb1", "application/x-wb1");
        CONTENT_TYPE_MAP.put("wb3", "application/x-wb3");
        CONTENT_TYPE_MAP.put("wiz", "application/msword");
        CONTENT_TYPE_MAP.put("wk4", "application/x-wk4");
        CONTENT_TYPE_MAP.put("wks", "application/x-wks");
        CONTENT_TYPE_MAP.put("wma", "audio/x-ms-wma");
        CONTENT_TYPE_MAP.put("wmf", "application/x-wmf");
        CONTENT_TYPE_MAP.put("wmv", "video/x-ms-wmv");
        CONTENT_TYPE_MAP.put("wmz", "application/x-ms-wmz");
        CONTENT_TYPE_MAP.put("wpd", "application/x-wpd");
        CONTENT_TYPE_MAP.put("wpl", "application/vnd.ms-wpl");
        CONTENT_TYPE_MAP.put("wr1", "application/x-wr1");
        CONTENT_TYPE_MAP.put("wrk", "application/x-wrk");
        CONTENT_TYPE_MAP.put("ws2", "application/x-ws");
        CONTENT_TYPE_MAP.put("wsdl", "text/xml");
        CONTENT_TYPE_MAP.put("xdp", "application/vnd.adobe.xdp");
        CONTENT_TYPE_MAP.put("xfd", "application/vnd.adobe.xfd");
        CONTENT_TYPE_MAP.put("xhtml", "text/html");
        CONTENT_TYPE_MAP.put("xml", "text/xml");
        CONTENT_TYPE_MAP.put("xq", "text/xml");
        CONTENT_TYPE_MAP.put("xquery", "text/xml");
        CONTENT_TYPE_MAP.put("xsl", "text/xml");
        CONTENT_TYPE_MAP.put("xwd", "application/x-xwd");
        CONTENT_TYPE_MAP.put("sis", "application/vnd.symbian.install");
        CONTENT_TYPE_MAP.put("x_t", "application/x-x_t");
        CONTENT_TYPE_MAP.put("apk", "application/vnd.android.package-archive");
        CONTENT_TYPE_MAP.put("301", "application/x-301");
        CONTENT_TYPE_MAP.put("906", "application/x-906");
        CONTENT_TYPE_MAP.put("a11", "application/x-a11");
        CONTENT_TYPE_MAP.put("ai", "application/postscript");
        CONTENT_TYPE_MAP.put("aifc", "audio/aiff");
        CONTENT_TYPE_MAP.put("anv", "application/x-anv");
        CONTENT_TYPE_MAP.put("asf", "video/x-ms-asf");
        CONTENT_TYPE_MAP.put("asx", "video/x-ms-asf");
        CONTENT_TYPE_MAP.put("avi", "video/avi");
        CONTENT_TYPE_MAP.put("biz", "text/xml");
        CONTENT_TYPE_MAP.put("bot", "application/x-bot");
        CONTENT_TYPE_MAP.put("c90", "application/x-c90");
        CONTENT_TYPE_MAP.put("cat", "application/vnd.ms-pki.seccat");
        CONTENT_TYPE_MAP.put("cdr", "application/x-cdr");
        CONTENT_TYPE_MAP.put("cer", "application/x-x509-ca-cert");
        CONTENT_TYPE_MAP.put("cgm", "application/x-cgm");
        CONTENT_TYPE_MAP.put("class", "java/*");
        CONTENT_TYPE_MAP.put("cmp", "application/x-cmp");
        CONTENT_TYPE_MAP.put("cot", "application/x-cot");
        CONTENT_TYPE_MAP.put("crt", "application/x-x509-ca-cert");
        CONTENT_TYPE_MAP.put("css", "text/css");
        CONTENT_TYPE_MAP.put("dbf", "application/x-dbf");
        CONTENT_TYPE_MAP.put("dbx", "application/x-dbx");
        CONTENT_TYPE_MAP.put("dcx", "application/x-dcx");
        CONTENT_TYPE_MAP.put("dgn", "application/x-dgn");
        CONTENT_TYPE_MAP.put("dll", "application/x-msdownload");
        CONTENT_TYPE_MAP.put("dot", "application/msword");
        CONTENT_TYPE_MAP.put("dtd", "text/xml");
        CONTENT_TYPE_MAP.put("dwf", "application/x-dwf");
        CONTENT_TYPE_MAP.put("dxb", "application/x-dxb");
        CONTENT_TYPE_MAP.put("edn", "application/vnd.adobe.edn");
        CONTENT_TYPE_MAP.put("eml", "message/rfc822");
        CONTENT_TYPE_MAP.put("epi", "application/x-epi");
        CONTENT_TYPE_MAP.put("eps", "application/postscript");
        CONTENT_TYPE_MAP.put("exe", "application/x-msdownload");
        CONTENT_TYPE_MAP.put("fdf", "application/vnd.fdf");
        CONTENT_TYPE_MAP.put("fo", "text/xml");
        CONTENT_TYPE_MAP.put("g4", "application/x-g4");
        CONTENT_TYPE_MAP.put("gl2", "application/x-gl2");
        CONTENT_TYPE_MAP.put("hgl", "application/x-hgl");
        CONTENT_TYPE_MAP.put("hpg", "application/x-hpgl");
        CONTENT_TYPE_MAP.put("hqx", "application/mac-binhex40");
        CONTENT_TYPE_MAP.put("hta", "application/hta");
        CONTENT_TYPE_MAP.put("htm", "text/html");
        CONTENT_TYPE_MAP.put("htt", "text/webviewhtml");
        CONTENT_TYPE_MAP.put("icb", "application/x-icb");
        CONTENT_TYPE_MAP.put("ico", "application/x-ico");
        CONTENT_TYPE_MAP.put("ig4", "application/x-g4");
        CONTENT_TYPE_MAP.put("iii", "application/x-iphone");
        CONTENT_TYPE_MAP.put("ins", "application/x-internet-signup");
        CONTENT_TYPE_MAP.put("IVF", "video/x-ivf");
        CONTENT_TYPE_MAP.put("jfif", "image/jpeg");
        CONTENT_TYPE_MAP.put("jpe", "application/x-jpe");
        CONTENT_TYPE_MAP.put("jpg", "image/jpeg");
        CONTENT_TYPE_MAP.put("js", "application/x-javascript");
        CONTENT_TYPE_MAP.put("la1", "audio/x-liquid-file");
        CONTENT_TYPE_MAP.put("latex", "application/x-latex");
        CONTENT_TYPE_MAP.put("lbm", "application/x-lbm");
        CONTENT_TYPE_MAP.put("ls", "application/x-javascript");
        CONTENT_TYPE_MAP.put("m1v", "video/x-mpeg");
        CONTENT_TYPE_MAP.put("m3u", "audio/mpegurl");
        CONTENT_TYPE_MAP.put("mac", "application/x-mac");
        CONTENT_TYPE_MAP.put("math", "text/xml");
        CONTENT_TYPE_MAP.put("mdb", "application/x-mdb");
        CONTENT_TYPE_MAP.put("mht", "message/rfc822");
        CONTENT_TYPE_MAP.put("mi", "application/x-mi");
        CONTENT_TYPE_MAP.put("midi", "audio/mid");
        CONTENT_TYPE_MAP.put("mml", "text/xml");
        CONTENT_TYPE_MAP.put("mns", "audio/x-musicnet-stream");
        CONTENT_TYPE_MAP.put("movie", "video/x-sgi-movie");
        CONTENT_TYPE_MAP.put("mp2", "audio/mp2");
        CONTENT_TYPE_MAP.put("mp3", "audio/mp3");
        CONTENT_TYPE_MAP.put("mpa", "video/x-mpg");
        CONTENT_TYPE_MAP.put("mpe", "video/x-mpeg");
        CONTENT_TYPE_MAP.put("mpg", "video/mpg");
        CONTENT_TYPE_MAP.put("mpp", "application/vnd.ms-project");
        CONTENT_TYPE_MAP.put("mpt", "application/vnd.ms-project");
        CONTENT_TYPE_MAP.put("mpv2", "video/mpeg");
        CONTENT_TYPE_MAP.put("mpx", "application/vnd.ms-project");
        CONTENT_TYPE_MAP.put("mxp", "application/x-mmxp");
        CONTENT_TYPE_MAP.put("nrf", "application/x-nrf");
        CONTENT_TYPE_MAP.put("odc", "text/x-ms-odc");
        CONTENT_TYPE_MAP.put("p10", "application/pkcs10");
        CONTENT_TYPE_MAP.put("p7b", "application/x-pkcs7-certificates");
        CONTENT_TYPE_MAP.put("p7m", "application/pkcs7-mime");
        CONTENT_TYPE_MAP.put("p7s", "application/pkcs7-signature");
        CONTENT_TYPE_MAP.put("pci", "application/x-pci");
        CONTENT_TYPE_MAP.put("pcx", "application/x-pcx");
        CONTENT_TYPE_MAP.put("pdf", "application/pdf");
        CONTENT_TYPE_MAP.put("pfx", "application/x-pkcs12");
        CONTENT_TYPE_MAP.put("pic", "application/x-pic");
        CONTENT_TYPE_MAP.put("pl", "application/x-perl");
        CONTENT_TYPE_MAP.put("pls", "audio/scpls");
        CONTENT_TYPE_MAP.put("png", "image/png");
        CONTENT_TYPE_MAP.put("pot", "application/vnd.ms-powerpoint");
        CONTENT_TYPE_MAP.put("ppm", "application/x-ppm");
        CONTENT_TYPE_MAP.put("ppt", "application/vnd.ms-powerpoint");
        CONTENT_TYPE_MAP.put("pptx", "application/vnd.ms-powerpoint");
        CONTENT_TYPE_MAP.put("pr", "application/x-pr");
        CONTENT_TYPE_MAP.put("prn", "application/x-prn");
        CONTENT_TYPE_MAP.put("ps", "application/x-ps");
        CONTENT_TYPE_MAP.put("ptn", "application/x-ptn");
        CONTENT_TYPE_MAP.put("r3t", "text/vnd.rn-realtext3d");
        CONTENT_TYPE_MAP.put("ram", "audio/x-pn-realaudio");
        CONTENT_TYPE_MAP.put("rat", "application/rat-file");
        CONTENT_TYPE_MAP.put("rec", "application/vnd.rn-recording");
        CONTENT_TYPE_MAP.put("rgb", "application/x-rgb");
        CONTENT_TYPE_MAP.put("rjt", "application/vnd.rn-realsystem-rjt");
        CONTENT_TYPE_MAP.put("rle", "application/x-rle");
        CONTENT_TYPE_MAP.put("rmf", "application/vnd.adobe.rmf");
        CONTENT_TYPE_MAP.put("rmj", "application/vnd.rn-realsystem-rmj");
        CONTENT_TYPE_MAP.put("rmp", "application/vnd.rn-rn_music_package");
        CONTENT_TYPE_MAP.put("rmvb", "application/vnd.rn-realmedia-vbr");
        CONTENT_TYPE_MAP.put("rnx", "application/vnd.rn-realplayer");
        CONTENT_TYPE_MAP.put("rpm", "audio/x-pn-realaudio-plugin");
        CONTENT_TYPE_MAP.put("rt", "text/vnd.rn-realtext");
        CONTENT_TYPE_MAP.put("rtf", "application/x-rtf");
        CONTENT_TYPE_MAP.put("sam", "application/x-sam");
        CONTENT_TYPE_MAP.put("sdp", "application/sdp");
        CONTENT_TYPE_MAP.put("sit", "application/x-stuffit");
        CONTENT_TYPE_MAP.put("sld", "application/x-sld");
        CONTENT_TYPE_MAP.put("smi", "application/smil");
        CONTENT_TYPE_MAP.put("smk", "application/x-smk");
        CONTENT_TYPE_MAP.put("sol", "text/plain");
        CONTENT_TYPE_MAP.put("spc", "application/x-pkcs7-certificates");
        CONTENT_TYPE_MAP.put("spp", "text/xml");
        CONTENT_TYPE_MAP.put("sst", "application/vnd.ms-pki.certstore");
        CONTENT_TYPE_MAP.put("stm", "text/html");
        CONTENT_TYPE_MAP.put("svg", "text/xml");
        CONTENT_TYPE_MAP.put("tdf", "application/x-tdf");
        CONTENT_TYPE_MAP.put("tga", "application/x-tga");
        CONTENT_TYPE_MAP.put("tld", "text/xml");
        CONTENT_TYPE_MAP.put("torrent", "application/x-bittorrent");
        CONTENT_TYPE_MAP.put("txt", "text/plain");
        CONTENT_TYPE_MAP.put("uls", "text/iuls");
        CONTENT_TYPE_MAP.put("vda", "application/x-vda");
        CONTENT_TYPE_MAP.put("vml", "text/xml");
        CONTENT_TYPE_MAP.put("vsd", "application/vnd.visio");
        CONTENT_TYPE_MAP.put("vss", "application/vnd.visio");
        CONTENT_TYPE_MAP.put("vst", "application/x-vst");
        CONTENT_TYPE_MAP.put("vsx", "application/vnd.visio");
        CONTENT_TYPE_MAP.put("vxml", "text/xml");
        CONTENT_TYPE_MAP.put("wax", "audio/x-ms-wax");
        CONTENT_TYPE_MAP.put("wb2", "application/x-wb2");
        CONTENT_TYPE_MAP.put("wbmp", "image/vnd.wap.wbmp");
        CONTENT_TYPE_MAP.put("wk3", "application/x-wk3");
        CONTENT_TYPE_MAP.put("wkq", "application/x-wkq");
        CONTENT_TYPE_MAP.put("wm", "video/x-ms-wm");
        CONTENT_TYPE_MAP.put("wmd", "application/x-ms-wmd");
        CONTENT_TYPE_MAP.put("wml", "text/vnd.wap.wml");
        CONTENT_TYPE_MAP.put("wmx", "video/x-ms-wmx");
        CONTENT_TYPE_MAP.put("wp6", "application/x-wp6");
        CONTENT_TYPE_MAP.put("wpg", "application/x-wpg");
        CONTENT_TYPE_MAP.put("wq1", "application/x-wq1");
        CONTENT_TYPE_MAP.put("wri", "application/x-wri");
        CONTENT_TYPE_MAP.put("ws", "application/x-ws");
        CONTENT_TYPE_MAP.put("wsc", "text/scriptlet");
        CONTENT_TYPE_MAP.put("wvx", "video/x-ms-wvx");
        CONTENT_TYPE_MAP.put("xdr", "text/xml");
        CONTENT_TYPE_MAP.put("xfdf", "application/vnd.adobe.xfdf");
        CONTENT_TYPE_MAP.put("xls", "application/vnd.ms-excel");
        CONTENT_TYPE_MAP.put("xlsx", "application/vnd.ms-excel");
        CONTENT_TYPE_MAP.put("xlw", "application/x-xlw");
        CONTENT_TYPE_MAP.put("xpl", "audio/scpls");
        CONTENT_TYPE_MAP.put("xql", "text/xml");
        CONTENT_TYPE_MAP.put("xsd", "text/xml");
        CONTENT_TYPE_MAP.put("xslt", "text/xml");
        CONTENT_TYPE_MAP.put("x_b", "application/x-x_b");
        CONTENT_TYPE_MAP.put("sisx", "application/vnd.symbian.install");
        CONTENT_TYPE_MAP.put("ipa", "application/vnd.iphone");
        CONTENT_TYPE_MAP.put("xap", "application/x-silverlight-app");
    }

    // endregion 静态变量维护

    // region 压缩与解压缩

    // region zip


    /**
     * ZIP JAR解压
     *
     * @param zipFileNmae 需要解压的ZIP,jar包
     * @param dir         目标文件夹
     * @return boolean
     */
    public static boolean unZipFiles(String zipFileNmae, String dir) {
        String dirs = dir;
        if (!dirs.endsWith(File.separator)) {
            dirs = dirs + File.separator;
        }
        try {
            // 根据ZIP文件创建ZipFile对象
            ZipFile zipFile = new ZipFile(new File(zipFileNmae), "GB2312");
            ZipEntry entry = null;
            String entryName = null;
            String descFileDir = null;
            byte[] buf = new byte[4096];
            int readByte = 0;
            // 获取ZIP文件里所有的entry
            @SuppressWarnings("rawtypes")
            Enumeration enums = zipFile.getEntries();
            // 遍历所有entry
            while (enums.hasMoreElements()) {
                entry = (ZipEntry) enums.nextElement();
                // 获得entry的名字
                entryName = entry.getName();
                descFileDir = dirs + entryName;
                if (entry.isDirectory()) {
                    // 如果entry是一个目录，则创建目录
                    new File(descFileDir).mkdirs();
                    continue;
                } else {
                    // 如果entry是一个文件，则创建父目录
                    new File(descFileDir).getParentFile().mkdirs();
                }
                File file = new File(descFileDir);
                // 打开文件输出流
                OutputStream os = new FileOutputStream(file);
                // 从ZipFile对象中打开entry的输入流
                InputStream is = zipFile.getInputStream(entry);
                //获取自己数组
                byte[] getData = readInputStream(is);
                os.write(getData);
                os.close();
                is.close();
            }
            zipFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ZIP JAR解压
     *
     * @param zipfile 需要解压的ZIP,jar包
     * @param dir     目标文件夹
     * @return boolean
     */
    public static boolean unZipFiles(File zipfile, String dir) {
        String dirs = dir;
        if (!dirs.endsWith(File.separator)) {
            dirs = dirs + File.separator;
        }
        try {
            // 根据ZIP文件创建ZipFile对象
            ZipFile zipFile = new ZipFile(zipfile, "GB2312");
            ZipEntry entry = null;
            String entryName = null;
            String descFileDir = null;
            byte[] buf = new byte[4096];
            int readByte = 0;
            // 获取ZIP文件里所有的entry
            @SuppressWarnings("rawtypes")
            Enumeration enums = zipFile.getEntries();
            // 遍历所有entry
            while (enums.hasMoreElements()) {
                entry = (ZipEntry) enums.nextElement();
                // 获得entry的名字
                entryName = entry.getName();
                descFileDir = dirs + entryName;
                if (entry.isDirectory()) {
                    // 如果entry是一个目录，则创建目录
                    new File(descFileDir).mkdirs();
                    continue;
                } else {
                    // 如果entry是一个文件，则创建父目录
                    new File(descFileDir).getParentFile().mkdirs();
                }
                File file = new File(descFileDir);
                // 打开文件输出流
                OutputStream os = new FileOutputStream(file);
                // 从ZipFile对象中打开entry的输入流
                InputStream is = zipFile.getInputStream(entry);
                byte[] data = readInputStream(is);
                os.write(data);
                os.close();
                is.close();
            }
            zipFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 压缩文件或目录
     *
     * @param srcDirName   压缩的根目录
     * @param fileName     根目录下的待压缩的文件名或文件夹名，其中*或StringUtil.EMPTY表示跟目录下的全部文件
     * @param descFileName 目标zip文件
     */
    public static void zipFiles(String srcDirName, String fileName,
                                String descFileName) {
        // 判断目录是否存在
        if (srcDirName == null) {
            //logger.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
            return;
        }
        File fileDir = new File(srcDirName);
        if (!fileDir.exists() || !fileDir.isDirectory()) {
            //logger.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
            return;
        }
        String dirPath = fileDir.getAbsolutePath();
        File descFile = new File(descFileName);
        try {
            ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(
                    descFile));
            if (ALL_FILE_TYPE.equals(fileName) || StringUtil.EMPTY.equals(fileName)) {
                FileUtil.zipDirectoryToZipFile(dirPath, fileDir, zouts);
            } else {
                File file = new File(fileDir, fileName);
                if (file.isFile()) {
                    FileUtil.zipFilesToZipFile(dirPath, file, zouts);
                } else {
                    FileUtil
                            .zipDirectoryToZipFile(dirPath, file, zouts);
                }
            }
            zouts.close();
            //logger.debug(descFileName + " 文件压缩成功!");
        } catch (Exception e) {
            //logger.debug("文件压缩失败：" + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 将目录压缩到ZIP输出流
     *
     * @param dirPath 目录路径
     * @param fileDir 文件信息
     * @param zouts   输出流
     */
    public static void zipDirectoryToZipFile(String dirPath, File fileDir, ZipOutputStream zouts) {
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            // 空的文件夹
            if (files.length == 0) {
                // 目录信息
                java.util.zip.ZipEntry entry = new java.util.zip.ZipEntry(getEntryName(dirPath, fileDir));
                try {
                    zouts.putNextEntry(entry);
                    zouts.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    // 如果是文件，则调用文件压缩方法
                    FileUtil
                            .zipFilesToZipFile(dirPath, files[i], zouts);
                } else {
                    // 如果是目录，则递归调用
                    FileUtil.zipDirectoryToZipFile(dirPath, files[i],
                            zouts);
                }
            }
        }
    }

    /**
     * 将文件压缩到ZIP输出流
     *
     * @param dirPath 目录路径
     * @param file    文件
     * @param zouts   输出流
     */
    public static void zipFilesToZipFile(String dirPath, File file, ZipOutputStream zouts) {
        FileInputStream fin = null;
        java.util.zip.ZipEntry entry = null;
        // 创建复制缓冲区
        byte[] buf = new byte[4096];
        int readByte = 0;
        if (file.isFile()) {
            try {
                // 创建一个文件输入流
                fin = new FileInputStream(file);
                // 创建一个ZipEntry
                entry = new java.util.zip.ZipEntry(getEntryName(dirPath, file));
                // 存储信息到压缩文件
                zouts.putNextEntry(entry);
                // 复制字节到压缩文件
                while ((readByte = fin.read(buf)) != -1) {
                    zouts.write(buf, 0, readByte);
                }
                zouts.closeEntry();
                fin.close();
                System.out
                        .println("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // endregion zip

    // region rar


    /**
     * RAR解压
     *
     * @param sourceRar 需要解压的RAR包
     * @param destDir   目标文件夹
     * @throws Exception
     */
    public static boolean unRarFiles(String sourceRar, String destDir) throws Exception {
        boolean zipResult = false;
        Archive a = null;
        FileOutputStream fos = null;
        try {
            a = new Archive(new File(sourceRar));
            FileHeader fh = a.nextFileHeader();

            while (fh != null) {
                if (!fh.isDirectory()) {
                    // 1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
                    String compressFileName = fh.getFileNameW().trim();
                    String destFileName = StringUtil.EMPTY;
                    String destDirName = StringUtil.EMPTY;
                    // 非windows系统
                    if ("/".equals(File.separator)) {
                        destFileName = destDir + "//" + compressFileName.replaceAll("\\\\", "/");
                        destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
                        // windows系统
                    } else {
                        destFileName = destDir + "//" + compressFileName.replaceAll("/", "\\\\");
                        destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
                    }
                    // 2创建文件夹
                    File dir = new File(destDirName);
                    if (!dir.exists() || !dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    // 3解压缩文件
                    fos = new FileOutputStream(new File(destFileName));
                    a.extractFile(fh, fos);
                    fos.close();
                    fos = null;
                }
                fh = a.nextFileHeader();
            }
            a.close();
            a = null;
            zipResult = true;
        } catch (Exception e) {
            throw new Exception("Rar格式解压失败！");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
                if (a != null) {
                    a.close();
                    a = null;
                }
            } catch (Exception e) {
                zipResult = false;
            }
        }
        return zipResult;
    }

    /**
     * RAR解压
     *
     * @param sourceRar 需要解压的RAR包
     * @param destDir   目标文件夹
     * @throws Exception
     */
    public static boolean unRarFiles(File sourceRar, String destDir) throws Exception {
        boolean zipResult = false;
        Archive a = null;
        FileOutputStream fos = null;
        try {
            a = new Archive(sourceRar);
            FileHeader fh = a.nextFileHeader();

            while (fh != null) {
                if (!fh.isDirectory()) {
                    // 1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
                    String compressFileName = fh.getFileNameW().trim();
                    String destFileName = StringUtil.EMPTY;
                    String destDirName = StringUtil.EMPTY;
                    // 非windows系统
                    if ("/".equals(File.separator)) {
                        destFileName = destDir + "//" + compressFileName.replaceAll("\\\\", "/");
                        destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
                        // windows系统
                    } else {
                        destFileName = destDir + "//" + compressFileName.replaceAll("/", "\\\\");
                        destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
                    }
                    // 2创建文件夹
                    File dir = new File(destDirName);
                    if (!dir.exists() || !dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    // 3解压缩文件
                    fos = new FileOutputStream(new File(destFileName));
                    a.extractFile(fh, fos);
                    fos.close();
                    fos = null;
                }
                fh = a.nextFileHeader();
            }
            a.close();
            a = null;
            zipResult = true;
        } catch (Exception e) {
            throw new Exception("Rar格式解压失败！");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
                if (a != null) {
                    a.close();
                    a = null;
                }
            } catch (Exception e) {
                zipResult = false;
            }
        }
        return zipResult;
    }

    // endregion rar

    // region gzip


    /**
     * 解压tar.gz 文件
     *
     * @param fileName  tar.gz tgz 文件对象
     * @param outputDir 要解压到某个指定的目录下
     * @throws IOException
     */
    public static boolean unTarGz(String fileName, String outputDir) throws IOException {
        boolean tmp = false;
        TarInputStream tarIn = null;
        File file = new File(fileName);
        try {
            tarIn = new TarInputStream(new GZIPInputStream(
                    new BufferedInputStream(new FileInputStream(file))),
                    FILE_READ_SIZE * 2);
            //创建输出目录
            createDirectory(outputDir, null);
            TarEntry entry = null;
            while ((entry = tarIn.getNextEntry()) != null) {

                if (entry.isDirectory()) {
                    //是目录
                    entry.getName();
                    createDirectory(outputDir, entry.getName());
                    //创建空目录
                } else {
                    //是文件
                    File tmpFile = new File(outputDir + "/" + entry.getName());
                    //创建输出目录
                    createDirectory(tmpFile.getParent() + "/", null);
                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(tmpFile);
                        int length = 0;

                        byte[] b = new byte[2048];

                        while ((length = tarIn.read(b)) != -1) {
                            out.write(b, 0, length);
                        }

                    } catch (IOException ex) {
                        throw ex;
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                }
            }
            tmp = true;
        } catch (IOException ex) {
            tmp = false;
            throw new IOException("解压归档文件出现异常", ex);
        } finally {
            try {
                if (tarIn != null) {
                    tarIn.close();
                }
            } catch (IOException ex) {
                tmp = false;
                throw new IOException("关闭tarFile出现异常", ex);

            }
        }
        return tmp;
    }

    /**
     * 解压tar.gz 文件
     *
     * @param file      tar.gz tgz 文件对象
     * @param outputDir 要解压到某个指定的目录下
     * @throws IOException
     */
    public static boolean unTarGz(File file, String outputDir) throws IOException {
        boolean tmp = false;
        TarInputStream tarIn = null;
        try {
            tarIn = new TarInputStream(new GZIPInputStream(
                    new BufferedInputStream(new FileInputStream(file))),
                    FILE_READ_SIZE * 2);
            //创建输出目录
            createDirectory(outputDir, null);
            TarEntry entry = null;
            while ((entry = tarIn.getNextEntry()) != null) {

                if (entry.isDirectory()) {
                    //是目录
                    entry.getName();
                    //创建空目录
                    createDirectory(outputDir, entry.getName());
                } else {
                    //是文件
                    File tmpFile = new File(outputDir + "/" + entry.getName());
                    //创建输出目录
                    createDirectory(tmpFile.getParent() + "/", null);
                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(tmpFile);
                        int length = 0;

                        byte[] b = new byte[2048];

                        while ((length = tarIn.read(b)) != -1) {
                            out.write(b, 0, length);
                        }

                    } catch (IOException ex) {
                        throw ex;
                    } finally {

                        if (out != null) {
                            out.close();
                        }
                    }
                }
            }
            tmp = true;
        } catch (IOException ex) {
            throw new IOException("解压归档文件出现异常", ex);
        } finally {
            try {
                if (tarIn != null) {
                    tarIn.close();
                }
            } catch (IOException ex) {
                throw new IOException("关闭tarFile出现异常", ex);
            }
        }
        return tmp;
    }

    // endregion gzip

    // region gzip2


    /**
     * GZIP2数据解压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] unGzip2(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 解压缩
        unGzip2(bais, baos);
        data = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return data;
    }

    /**
     * GZIP2文件解压缩
     *
     * @param file
     * @throws Exception
     */
    public static void unGzip2(File file) throws Exception {
        unGzip2(file, true);
    }

    /**
     * GZIP2文件解压缩
     *
     * @param file
     * @param delete 是否删除原始文件
     * @throws Exception
     */
    public static void unGzip2(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath().replace(".bz2",
                StringUtil.EMPTY));
        unGzip2(fis, fos);
        fis.close();
        fos.flush();
        fos.close();

        if (delete) {
            file.delete();
        }
    }

    /**
     * GZIP2数据解压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void unGzip2(InputStream is, OutputStream os)
            throws Exception {

        BZip2CompressorInputStream gis = new BZip2CompressorInputStream(is);

        int count;
        byte[] data = new byte[FILE_READ_SIZE];
        while ((count = gis.read(data, FILE_READ_START, FILE_READ_SIZE)) != -1) {
            os.write(data, FILE_READ_START, count);
        }

        gis.close();
    }

    /**
     * GZIP2文件解压缩
     *
     * @param path
     * @throws Exception
     */
    public static void gz2Compress(String path) throws Exception {
        gz2Compress(path, true);
    }

    /**
     * GZIP2文件解压缩
     *
     * @param path
     * @param delete 是否删除原始文件
     * @throws Exception
     */
    public static void gz2Compress(String path, boolean delete) throws Exception {
        File file = new File(path);
        unGzip2(file, delete);
    }

    // endregion gzip2


    // endregion 压缩与解压缩

    // region 文件夹操作

    /**
     * 构建目录
     *
     * @param outputDir
     * @param subDir
     */
    public static void createDirectory(String outputDir, String subDir) {
        File file = new File(outputDir);
        if (!(subDir == null || StringUtil.EMPTY.equals(subDir.trim()))) {
            //子目录不为空
            file = new File(outputDir + "/" + subDir);
        }
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }


    /**
     * 复制整个目录的内容，如果目标目录存在，则不覆盖
     *
     * @param srcDirName  源目录名
     * @param descDirName 目标目录名
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyDirectory(String srcDirName, String descDirName) {
        return FileUtil.copyDirectoryCover(srcDirName, descDirName,
                false);
    }

    /**
     * 复制整个目录的内容
     *
     * @param srcDirName  源目录名
     * @param descDirName 目标目录名
     * @param coverlay    如果目标目录存在，是否覆盖
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyDirectoryCover(String srcDirName,
                                             String descDirName, boolean coverlay) {
        File srcDir = new File(srcDirName);
        // 判断源目录是否存在
        if (!srcDir.exists()) {
            //logger.debug("复制目录失败，源目录 " + srcDirName + " 不存在!");
            return false;
        }
        // 判断源目录是否是目录
        else if (!srcDir.isDirectory()) {
            //logger.debug("复制目录失败，" + srcDirName + " 不是一个目录!");
            return false;
        }
        // 如果目标文件夹名不以文件分隔符结尾，自动添加文件分隔符
        String descDirNames = descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }
        File descDir = new File(descDirNames);
        // 如果目标文件夹存在
        if (descDir.exists()) {
            if (coverlay) {
                // 允许覆盖目标目录
                //logger.debug("目标目录已存在，准备删除!");
                if (!FileUtil.delFile(descDirNames)) {
                    //logger.debug("删除目录 " + descDirNames + " 失败!");
                    return false;
                }
            } else {
                //logger.debug("目标目录复制失败，目标目录 " + descDirNames + " 已存在!");
                return false;
            }
        } else {
            // 创建目标目录
            //logger.debug("目标目录不存在，准备创建!");
            if (!descDir.mkdirs()) {
                //logger.debug("创建目标目录失败!");
                return false;
            }

        }

        boolean flag = true;
        // 列出源目录下的所有文件名和子目录名
        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 如果是一个单个文件，则直接复制
            if (files[i].isFile()) {
                flag = FileUtil.copyFile(files[i].getAbsolutePath(),
                        descDirName + files[i].getName());
                // 如果拷贝文件失败，则退出循环
                if (!flag) {
                    break;
                }
            }
            // 如果是子目录，则继续复制目录
            if (files[i].isDirectory()) {
                flag = FileUtil.copyDirectory(files[i]
                        .getAbsolutePath(), descDirName + files[i].getName());
                // 如果拷贝目录失败，则退出循环
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            //logger.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 失败!");
            return false;
        }
        //logger.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 成功!");
        return true;

    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dirName 被删除的目录所在的文件路径
     * @return 如果目录删除成功，则返回true，否则返回false
     */
    public static boolean deleteDirectory(String dirName) {
        String dirNames = dirName;
        if (!dirNames.endsWith(File.separator)) {
            dirNames = dirNames + File.separator;
        }
        File dirFile = new File(dirNames);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            //logger.debug(dirNames + " 目录不存在!");
            return true;
        }
        boolean flag = true;
        // 列出全部文件及子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                // 如果删除文件失败，则退出循环
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                // 如果删除子目录失败，则退出循环
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            //logger.debug("删除目录失败!");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            //logger.debug("删除目录 " + dirName + " 成功!");
            return true;
        } else {
            //logger.debug("删除目录 " + dirName + " 失败!");
            return false;
        }

    }


    /**
     * 创建目录
     *
     * @param descDirName 目录名,包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createDirectory(String descDirName) {
        String descDirNames = descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }
        File descDir = new File(descDirNames);
        if (descDir.exists()) {
            //logger.debug("目录 " + descDirNames + " 已存在!");
            return false;
        }
        // 创建目录
        if (descDir.mkdirs()) {
            //logger.debug("目录 " + descDirNames + " 创建成功!");
            return true;
        } else {
            //logger.debug("目录 " + descDirNames + " 创建失败!");
            return false;
        }

    }

    /**
     * 获目录下的文件列表
     *
     * @param dirPath    搜索目录
     * @param searchDirs 是否是搜索目录
     * @return 文件列表
     */
    public static List<String> findChildrenList(String dirPath, boolean searchDirs) {
        if (StringUtil.isEmpty(dirPath)) {
            return null;
        }
        if (!isFileExist(dirPath)) {
            return null;
        }
        ;
        return findChildrenList(getFile(dirPath), searchDirs);
    }

    /**
     * 获目录下的文件列表
     *
     * @param dir        搜索目录
     * @param searchDirs 是否是搜索目录
     * @return 文件列表
     */
    public static List<String> findChildrenList(File dir, boolean searchDirs) {
        List<String> files = Lists.newArrayList();
        for (String subFiles : dir.list()) {
            File file = new File(dir + LINUX_FILE_SEPARATOR + subFiles);
            if ((searchDirs && file.isDirectory()) || (!searchDirs && !file.isDirectory())) {
                files.add(file.getName());
            }
        }
        return files;
    }
    // endregion 文件夹操作

    // region 下载文件


    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param savePath
     * @throws IOException
     */
    public static void downLoadFromUrl(String urlStr, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        String fileName = (String) conn.getHeaderField("Content-Disposition").subSequence(conn.getHeaderField("Content-Disposition").indexOf("=") + 1, conn.getHeaderField("Content-Disposition").length());
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        System.out.println("info:" + url + " download success");
    }


    /**
     * 下载文件名重新编码
     *
     * @param agent    HttpRequest对象中请求头(USER-AGENT)字段值
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(String agent, String fileName)
            throws UnsupportedEncodingException {
        String filename = fileName;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        } else if (agent.contains("Chrome")) {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    // endregion 下载文件

    // region 流管理


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[FILE_READ_SIZE];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, FILE_READ_START, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    /**
     * 输入流转为字符串
     *
     * @param inStream 输入流
     * @return 字符串
     * @throws IOException
     */
    public static String inputStream2String(InputStream inStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        StringBuffer buffer = new StringBuffer();
        String line = StringUtil.EMPTY;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }


    public static String readFileToString(String filePath, String charset) {
        if (StringUtil.isAnyEmpty(filePath)) {
            return null;
        }
        if (StringUtil.isAnyEmpty(charset)) {
            charset = DEFAULT_CHARSET;
        }
        File file = new File(filePath);
        if (!file.canRead()) {
            return null;
        }
        String tmpFileContext = null;
        try {
            tmpFileContext = org.apache.commons.io.FileUtils.readFileToString(file, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpFileContext;
    }

    public static String readFileToString(String filePath) {
        return readFileToString(filePath, null);
    }

    public static byte[] readFileToByteArray(String filePath) {
        if (StringUtil.isAnyEmpty(filePath)) {
            return null;
        }
        File file = new File(filePath);
        if (!file.canRead()) {
            return null;
        }
        byte[] tmpFileContext = null;
        try {
            tmpFileContext = org.apache.commons.io.FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpFileContext;
    }

    public static InputStream getFileStream(String fileName) {
        File file = new File(fileName);
        FileInputStream fileInputStream = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileInputStream;
    }

    public static InputStream getFileStream(URI url) {
        File file = new File(url);
        FileInputStream fileInputStream = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileInputStream;
    }
    // endregion 流管理

    // region 文件操作

    /**
     * 复制单个文件，如果目标文件存在，则不覆盖
     *
     * @param srcFileName  待复制的文件名
     * @param descFileName 目标文件名
     * @return 如果复制成功，则返回true，否则返回false
     */
    public static boolean copyFile(String srcFileName, String descFileName) {
        return FileUtil.copyFileCover(srcFileName, descFileName, false);
    }


    /**
     * 复制单个文件
     *
     * @param srcFileName  待复制的文件名
     * @param descFileName 目标文件名
     * @param coverlay     如果目标文件已存在，是否覆盖
     * @return 如果复制成功，则返回true，否则返回false
     */
    public static boolean copyFileCover(String srcFileName,
                                        String descFileName, boolean coverlay) {
        File srcFile = new File(srcFileName);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            //logger.debug("复制文件失败，源文件 " + srcFileName + " 不存在!");
            return false;
        }
        // 判断源文件是否是合法的文件
        else if (!srcFile.isFile()) {
            //logger.debug("复制文件失败，" + srcFileName + " 不是一个文件!");
            return false;
        }
        File descFile = new File(descFileName);
        // 判断目标文件是否存在
        if (descFile.exists()) {
            // 如果目标文件存在，并且允许覆盖
            if (coverlay) {
                //logger.debug("目标文件已存在，准备删除!");
                if (!FileUtil.delFile(descFileName)) {
                    //logger.debug("删除目标文件 " + descFileName + " 失败!");
                    return false;
                }
            } else {
                //logger.debug("复制文件失败，目标文件 " + descFileName + " 已存在!");
                return false;
            }
        } else {
            if (!descFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建目录
                //logger.debug("目标文件所在的目录不存在，创建目录!");
                // 创建目标文件所在的目录
                if (!descFile.getParentFile().mkdirs()) {
                    //logger.debug("创建目标文件所在的目录失败!");
                    return false;
                }
            }
        }
        // 准备复制文件
        // 读取的位数
        int readByte = 0;
        InputStream ins = null;
        OutputStream outs = null;
        try {
            // 打开源文件
            ins = new FileInputStream(srcFile);
            // 打开目标文件的输出流
            outs = new FileOutputStream(descFile);
            byte[] buf = new byte[FILE_READ_SIZE];
            // 一次读取1024个字节，当readByte为-1时表示文件已经读取完毕
            while ((readByte = ins.read(buf)) != -1) {
                // 将读取的字节流写入到输出流
                outs.write(buf, 0, readByte);
            }
            //logger.debug("复制单个文件 " + srcFileName + " 到" + descFileName
//                    + "成功!");
            return true;
        } catch (Exception e) {
            //logger.debug("复制文件失败：" + e.getMessage());
            return false;
        } finally {
            // 关闭输入输出流，首先关闭输出流，然后再关闭输入流
            if (outs != null) {
                try {
                    outs.close();
                } catch (IOException oute) {
                    oute.printStackTrace();
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException ine) {
                    ine.printStackTrace();
                }
            }
        }
    }


    /**
     * 删除文件，可以删除单个文件或文件夹
     *
     * @param fileName 被删除的文件名
     * @return 如果删除成功，则返回true，否是返回false
     */
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            //logger.debug(fileName + " 文件不存在!");
            return true;
        } else {
            if (file.isFile()) {
                return FileUtil.deleteFile(fileName);
            } else {
                return FileUtil.deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除的文件名
     * @return 如果删除成功，则返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                //logger.debug("删除文件 " + fileName + " 成功!");
                return true;
            } else {
                //logger.debug("删除文件 " + fileName + " 失败!");
                return false;
            }
        } else {
            //logger.debug(fileName + " 文件不存在!");
            return true;
        }
    }

    /**
     * 创建单个文件
     *
     * @param descFileName 文件名，包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (file.exists()) {
            //logger.debug("文件 " + descFileName + " 已存在!");
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            //logger.debug(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                //logger.debug("创建文件所在的目录失败!");
                return false;
            }
        }

        // 创建文件
        try {
            if (file.createNewFile()) {
                //logger.debug(descFileName + " 文件创建成功!");
                return true;
            } else {
                //logger.debug(descFileName + " 文件创建失败!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(descFileName + " 文件创建失败!");
            return false;
        }

    }

    /**
     * 写入文件
     */
    public static void writeToFile(String fileName, String content, boolean append) {
        try {
            FileUtil.write(new File(fileName), content, DEFAULT_CHARSET, append);
            //logger.debug("文件 " + fileName + " 写入成功!");
        } catch (IOException e) {
            //logger.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
        }
    }

    /**
     * 写入文件
     */
    public static void writeToFile(String fileName, String content, String encoding, boolean append) {
        try {
            FileUtil.write(new File(fileName), content, encoding, append);
            //logger.debug("文件 " + fileName + " 写入成功!");
        } catch (IOException e) {
            //logger.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
        }
    }


    /**
     * 判断文件是否存在
     *
     * @param pathName
     * @return
     */
    public static boolean isFileExist(String pathName) {
        if (!StringUtils.hasText(pathName)) {
            return false;
        }
        pathName = StringUtil.trimRight(pathName, "file://");
        try {
            File file = new File(pathName);
            return file.exists();
        } catch (Exception e) {
            //logger.error("error happened:{}", e.getMessage(), e);
            return false;
        }
    }


    /**
     * 判断文件是否存在
     *
     * @param uri
     * @return
     */
    public static boolean isFileExist(URI uri) {
        if (uri == null) {
            return false;
        }
        try {
            File file = new File(uri);
            return file.exists();
        } catch (Exception e) {
            //logger.error("error happened:{}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 合并目录下的文件
     *
     * @param sourceFilePath 源文件目录路径
     * @param destFileName   目标文件名称
     * @return 合并结果
     */
//    public static MicroObjectResp<Boolean> mergeFileSlice(String sourceFilePath, String destFileName) {
//        if (StringUtil.isAnyEmpty(sourceFilePath, destFileName)) {
//            return RespInfoUtil.getErrorMicroObjectRespInfo(false, "请确保源文件夹路径和目标文件不能为空字符串", "");
//        }
//        if (!isFileExist(sourceFilePath)) {
//            return RespInfoUtil.getErrorMicroObjectRespInfo(false, "请确保源文件夹路径存在", "");
//        }
//        if (isFileExist(destFileName)) {
//            return RespInfoUtil.getErrorMicroObjectRespInfo(false, "目标文件已存在，无法进行合并", "");
//        }
//        List<String> childrenList = findChildrenList(sourceFilePath, false);
//        if (CollectionUtil.listIsEmpty(childrenList)) {
//            return RespInfoUtil.getErrorMicroObjectRespInfo(false, "请确保源文件夹路径下存在待合并的文件", "");
//        }
//        List<String> childrenFileNameList = new LinkedList<>();
//        for(String tmp : childrenList){
//            childrenFileNameList.add(StringUtil.formatString("{}{}{}",sourceFilePath,File.separator,tmp));
//        }
//        Collections.sort(childrenFileNameList);
//        return mergeFileSlice(childrenFileNameList, destFileName);
//    }

    /**
     * 合并多个文件为一个文件
     *
     * @param sourceFileNameList 源文件列表
     * @param destFileName 目标文件名称
     * @return 合并结果
     */
//    public static MicroObjectResp<Boolean> mergeFileSlice(List<String> sourceFileNameList, String destFileName) {
//        if (CollectionUtil.listIsEmpty(sourceFileNameList)) {
//            return RespInfoUtil.getErrorMicroObjectRespInfo(false, "目标文件列表为空", "");
//        }
//        if (isFileExist(destFileName)) {
//            return RespInfoUtil.getErrorMicroObjectRespInfo(false, "目标文件已存在，无法进行合并", "");
//        }
//        for(String tmp : sourceFileNameList){
//            if(StringUtil.isEmpty(tmp)){
//                return RespInfoUtil.getErrorMicroObjectRespInfo(false, "目标文件列表中存在空文件名", "");
//            }
//            if (!isFileExist(tmp)) {
//                return RespInfoUtil.getErrorMicroObjectRespInfo(false, StringUtil.formatString("源文件:[{}]不存在",tmp), "");
//            }
//        }
//        boolean tmpDestFileCreated = createFile(destFileName);
//        if(!tmpDestFileCreated){
//            return RespInfoUtil.getErrorMicroObjectRespInfo(false, StringUtil.formatString("目标文件:[{}]创建失败",destFileName), "");
//        }
//        FileOutputStream tmpDestFileOutStream = null;
//        try {
//            tmpDestFileOutStream = openOutputStream(getFile(destFileName));
//            for(String tmp : sourceFileNameList){
//                tmpDestFileOutStream.write(readFileToByteArray(tmp));
//            }
//            tmpDestFileOutStream.close();
//            tmpDestFileOutStream = null;
//        } catch (IOException e) {
//            String tmpError = StringUtil.formatString("源文件列表：【{}】目标文件：{} 写入失败:{}",JsonUtil.obj2jsonStr(sourceFileNameList),destFileName,e.toString());
//            //logger.error(tmpError,e);
//            return RespInfoUtil.getErrorMicroObjectRespInfo(false, "目标文件合并失败", tmpError);
//        }
//        finally {
//            if(tmpDestFileOutStream != null){
//                try {
//                    tmpDestFileOutStream.close();
//                } catch (IOException e) {
//                    String tmpError = StringUtil.formatString("源文件列表：【{}】目标文件：{} 流释放失败:{}",JsonUtil.obj2jsonStr(sourceFileNameList),destFileName,e.toString());
//                    //logger.error(tmpError,e);
//                }
//            }
//        }
//        return RespInfoUtil.getSuccessMicroObjectRespInfo(true);
//    }
    // endregion 文件操作

    // region 文件属性

    /**
     * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
     *
     * @param dirPath 目录名
     * @param file    entry文件名
     * @return
     */
    private static String getEntryName(String dirPath, File file) {
        String dirPaths = dirPath;
        if (!dirPaths.endsWith(File.separator)) {
            dirPaths = dirPaths + File.separator;
        }
        String filePath = file.getAbsolutePath();
        // 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
        if (file.isDirectory()) {
            filePath += "/";
        }
        int index = filePath.indexOf(dirPaths);

        return filePath.substring(index + dirPaths.length());
    }

    /**
     * 根据“文件名的后缀”获取文件内容类型（而非根据File.getContentType()读取的文件类型）
     *
     * @param fileFullName 带验证的文件名(全名)
     * @return 返回文件类型
     */
    public static String getContentType(String fileFullName) {
        String contentType = "application/octet-stream";
        if (StringUtil.isEmpty(fileFullName)) {
            return contentType;
        }
        // 兼容调用方已经把文件后缀准备好的情况
        if (CONTENT_TYPE_MAP.containsKey(fileFullName)) {
            return CONTENT_TYPE_MAP.get(fileFullName);
        }
        String tmpFileType = fileFullName.toLowerCase();
        tmpFileType = RegularUtil.regSubstr(FILE_TYPE_PAT, tmpFileType);
        if (StringUtil.isEmpty(tmpFileType) || !CONTENT_TYPE_MAP.containsKey(tmpFileType)) {
            tmpFileType = "*";
        }
        return CONTENT_TYPE_MAP.get(tmpFileType);
    }


    /**
     * 修正路径，将 \\ 或 / 等替换为 File.separator
     *
     * @param path 待修正的路径
     * @return 修正后的路径
     */
    public static String path(String path) {
        String p = StringUtil.replace(path, "\\", LINUX_FILE_SEPARATOR);
        p = StringUtil.join(StringUtil.split(p, LINUX_FILE_SEPARATOR), LINUX_FILE_SEPARATOR);
        if (!StringUtil.startsWithAny(p, LINUX_FILE_SEPARATOR) && StringUtil.startsWithAny(path, WINDOWS_FILE_SEPARATOR, LINUX_FILE_SEPARATOR)) {
            p += LINUX_FILE_SEPARATOR;
        }
        if (!StringUtil.endsWithAny(p, LINUX_FILE_SEPARATOR) && StringUtil.endsWithAny(path, WINDOWS_FILE_SEPARATOR, LINUX_FILE_SEPARATOR)) {
            p = p + LINUX_FILE_SEPARATOR;
        }
        if (path != null && path.startsWith(LINUX_FILE_SEPARATOR)) {
            // linux下路径
            p = LINUX_FILE_SEPARATOR + p;
        }
        return p;
    }

    /**
     * 获取文件扩展名(返回小写)
     *
     * @param fileName 文件名
     * @return 例如：Test.jpg  返回：  jpg
     */
    public static String getFileExtension(String fileName) {
        return RegularUtil.regSubstr(FILE_TYPE_PAT, fileName);
    }


    /**
     * 获取文件名，不包含扩展名
     *
     * @param fileName 文件名
     * @return 例如：d:\files\Test.jpg  返回：d:\files\Test
     */
    public static String getFileNameWithoutExtension(String fileName) {
        return RegularUtil.regSubstr(FILE_NAME_PAT, fileName);
    }

    // endregion 文件属性


}

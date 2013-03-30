package com.xuan.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * 拼音工具类
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:56:17 $
 */
public abstract class SpellUtils {

    public final static HashMap<String, String> surnameMap = new HashMap<String, String>(415);

    private final static LinkedHashMap<String, Integer> spellMap = new LinkedHashMap<String, Integer>(400);;

    static {
        initialize();
    }

    private static void spellPut(String spell, int ascii) {
        spellMap.put(spell, ascii);
    }

    private static void surnamePut(String surname, String firstSpell) {
        surnameMap.put(surname, firstSpell);
    }

    private static void initialize() {
        spellPut("a", -20319);
        spellPut("ai", -20317);
        spellPut("an", -20304);
        spellPut("ang", -20295);
        spellPut("ao", -20292);
        spellPut("ba", -20283);
        spellPut("bai", -20265);
        spellPut("ban", -20257);
        spellPut("bang", -20242);
        spellPut("bao", -20230);
        spellPut("bei", -20051);
        spellPut("ben", -20036);
        spellPut("beng", -20032);
        spellPut("bi", -20026);
        spellPut("bian", -20002);
        spellPut("biao", -19990);
        spellPut("bie", -19986);
        spellPut("bin", -19982);
        spellPut("bing", -19976);
        spellPut("bo", -19805);
        spellPut("bu", -19784);
        spellPut("ca", -19775);
        spellPut("cai", -19774);
        spellPut("can", -19763);
        spellPut("cang", -19756);
        spellPut("cao", -19751);
        spellPut("ce", -19746);
        spellPut("ceng", -19741);
        spellPut("cha", -19739);
        spellPut("chai", -19728);
        spellPut("chan", -19725);
        spellPut("chang", -19715);
        spellPut("chao", -19540);
        spellPut("che", -19531);
        spellPut("chen", -19525);
        spellPut("cheng", -19515);
        spellPut("chi", -19500);
        spellPut("chong", -19484);
        spellPut("chou", -19479);
        spellPut("chu", -19467);
        spellPut("chuai", -19289);
        spellPut("chuan", -19288);
        spellPut("chuang", -19281);
        spellPut("chui", -19275);
        spellPut("chun", -19270);
        spellPut("chuo", -19263);
        spellPut("ci", -19261);
        spellPut("cong", -19249);
        spellPut("cou", -19243);
        spellPut("cu", -19242);
        spellPut("cuan", -19238);
        spellPut("cui", -19235);
        spellPut("cun", -19227);
        spellPut("cuo", -19224);
        spellPut("da", -19218);
        spellPut("dai", -19212);
        spellPut("dan", -19038);
        spellPut("dang", -19023);
        spellPut("dao", -19018);
        spellPut("de", -19006);
        spellPut("deng", -19003);
        spellPut("di", -18996);
        spellPut("dian", -18977);
        spellPut("diao", -18961);
        spellPut("die", -18952);
        spellPut("ding", -18783);
        spellPut("diu", -18774);
        spellPut("dong", -18773);
        spellPut("dou", -18763);
        spellPut("du", -18756);
        spellPut("duan", -18741);
        spellPut("dui", -18735);
        spellPut("dun", -18731);
        spellPut("duo", -18722);
        spellPut("e", -18710);
        spellPut("en", -18697);
        spellPut("er", -18696);
        spellPut("fa", -18526);
        spellPut("fan", -18518);
        spellPut("fang", -18501);
        spellPut("fei", -18490);
        spellPut("fen", -18478);
        spellPut("feng", -18463);
        spellPut("fo", -18448);
        spellPut("fou", -18447);
        spellPut("fu", -18446);
        spellPut("ga", -18239);
        spellPut("gai", -18237);
        spellPut("gan", -18231);
        spellPut("gang", -18220);
        spellPut("gao", -18211);
        spellPut("ge", -18201);
        spellPut("gei", -18184);
        spellPut("gen", -18183);
        spellPut("geng", -18181);
        spellPut("gong", -18012);
        spellPut("gou", -17997);
        spellPut("gu", -17988);
        spellPut("gua", -17970);
        spellPut("guai", -17964);
        spellPut("guan", -17961);
        spellPut("guang", -17950);
        spellPut("gui", -17947);
        spellPut("gun", -17931);
        spellPut("guo", -17928);
        spellPut("ha", -17922);
        spellPut("hai", -17759);
        spellPut("han", -17752);
        spellPut("hang", -17733);
        spellPut("hao", -17730);
        spellPut("he", -17721);
        spellPut("hei", -17703);
        spellPut("hen", -17701);
        spellPut("heng", -17697);
        spellPut("hong", -17692);
        spellPut("hou", -17683);
        spellPut("hu", -17676);
        spellPut("hua", -17496);
        spellPut("huai", -17487);
        spellPut("huan", -17482);
        spellPut("huang", -17468);
        spellPut("hui", -17454);
        spellPut("hun", -17433);
        spellPut("huo", -17427);
        spellPut("ji", -17417);
        spellPut("jia", -17202);
        spellPut("jian", -17185);
        spellPut("jiang", -16983);
        spellPut("jiao", -16970);
        spellPut("jie", -16942);
        spellPut("jin", -16915);
        spellPut("jing", -16733);
        spellPut("jiong", -16708);
        spellPut("jiu", -16706);
        spellPut("ju", -16689);
        spellPut("juan", -16664);
        spellPut("jue", -16657);
        spellPut("jun", -16647);
        spellPut("ka", -16474);
        spellPut("kai", -16470);
        spellPut("kan", -16465);
        spellPut("kang", -16459);
        spellPut("kao", -16452);
        spellPut("ke", -16448);
        spellPut("ken", -16433);
        spellPut("keng", -16429);
        spellPut("kong", -16427);
        spellPut("kou", -16423);
        spellPut("ku", -16419);
        spellPut("kua", -16412);
        spellPut("kuai", -16407);
        spellPut("kuan", -16403);
        spellPut("kuang", -16401);
        spellPut("kui", -16393);
        spellPut("kun", -16220);
        spellPut("kuo", -16216);
        spellPut("la", -16212);
        spellPut("lai", -16205);
        spellPut("lan", -16202);
        spellPut("lang", -16187);
        spellPut("lao", -16180);
        spellPut("le", -16171);
        spellPut("lei", -16169);
        spellPut("leng", -16158);
        spellPut("li", -16155);
        spellPut("lia", -15959);
        spellPut("lian", -15958);
        spellPut("liang", -15944);
        spellPut("liao", -15933);
        spellPut("lie", -15920);
        spellPut("lin", -15915);
        spellPut("ling", -15903);
        spellPut("liu", -15889);
        spellPut("long", -15878);
        spellPut("lou", -15707);
        spellPut("lu", -15701);
        spellPut("lv", -15681);
        spellPut("luan", -15667);
        spellPut("lue", -15661);
        spellPut("lun", -15659);
        spellPut("luo", -15652);
        spellPut("ma", -15640);
        spellPut("mai", -15631);
        spellPut("man", -15625);
        spellPut("mang", -15454);
        spellPut("mao", -15448);
        spellPut("me", -15436);
        spellPut("mei", -15435);
        spellPut("men", -15419);
        spellPut("meng", -15416);
        spellPut("mi", -15408);
        spellPut("mian", -15394);
        spellPut("miao", -15385);
        spellPut("mie", -15377);
        spellPut("min", -15375);
        spellPut("ming", -15369);
        spellPut("miu", -15363);
        spellPut("mo", -15362);
        spellPut("mou", -15183);
        spellPut("mu", -15180);
        spellPut("na", -15165);
        spellPut("nai", -15158);
        spellPut("nan", -15153);
        spellPut("nang", -15150);
        spellPut("nao", -15149);
        spellPut("ne", -15144);
        spellPut("nei", -15143);
        spellPut("nen", -15141);
        spellPut("neng", -15140);
        spellPut("ni", -15139);
        spellPut("nian", -15128);
        spellPut("niang", -15121);
        spellPut("niao", -15119);
        spellPut("nie", -15117);
        spellPut("nin", -15110);
        spellPut("ning", -15109);
        spellPut("niu", -14941);
        spellPut("nong", -14937);
        spellPut("nu", -14933);
        spellPut("nv", -14930);
        spellPut("nuan", -14929);
        spellPut("nue", -14928);
        spellPut("nuo", -14926);
        spellPut("o", -14922);
        spellPut("ou", -14921);
        spellPut("pa", -14914);
        spellPut("pai", -14908);
        spellPut("pan", -14902);
        spellPut("pang", -14894);
        spellPut("pao", -14889);
        spellPut("pei", -14882);
        spellPut("pen", -14873);
        spellPut("peng", -14871);
        spellPut("pi", -14857);
        spellPut("pian", -14678);
        spellPut("piao", -14674);
        spellPut("pie", -14670);
        spellPut("pin", -14668);
        spellPut("ping", -14663);
        spellPut("po", -14654);
        spellPut("pu", -14645);
        spellPut("qi", -14630);
        spellPut("qia", -14594);
        spellPut("qian", -14429);
        spellPut("qiang", -14407);
        spellPut("qiao", -14399);
        spellPut("qie", -14384);
        spellPut("qin", -14379);
        spellPut("qing", -14368);
        spellPut("qiong", -14355);
        spellPut("qiu", -14353);
        spellPut("qu", -14345);
        spellPut("quan", -14170);
        spellPut("que", -14159);
        spellPut("qun", -14151);
        spellPut("ran", -14149);
        spellPut("rang", -14145);
        spellPut("rao", -14140);
        spellPut("re", -14137);
        spellPut("ren", -14135);
        spellPut("reng", -14125);
        spellPut("ri", -14123);
        spellPut("rong", -14122);
        spellPut("rou", -14112);
        spellPut("ru", -14109);
        spellPut("ruan", -14099);
        spellPut("rui", -14097);
        spellPut("run", -14094);
        spellPut("ruo", -14092);
        spellPut("sa", -14090);
        spellPut("sai", -14087);
        spellPut("san", -14083);
        spellPut("sang", -13917);
        spellPut("sao", -13914);
        spellPut("se", -13910);
        spellPut("sen", -13907);
        spellPut("seng", -13906);
        spellPut("sha", -13905);
        spellPut("shai", -13896);
        spellPut("shan", -13894);
        spellPut("shang", -13878);
        spellPut("shao", -13870);
        spellPut("she", -13859);
        spellPut("shen", -13847);
        spellPut("sheng", -13831);
        spellPut("shi", -13658);
        spellPut("shou", -13611);
        spellPut("shu", -13601);
        spellPut("shua", -13406);
        spellPut("shuai", -13404);
        spellPut("shuan", -13400);
        spellPut("shuang", -13398);
        spellPut("shui", -13395);
        spellPut("shun", -13391);
        spellPut("shuo", -13387);
        spellPut("si", -13383);
        spellPut("song", -13367);
        spellPut("sou", -13359);
        spellPut("su", -13356);
        spellPut("suan", -13343);
        spellPut("sui", -13340);
        spellPut("sun", -13329);
        spellPut("suo", -13326);
        spellPut("ta", -13318);
        spellPut("tai", -13147);
        spellPut("tan", -13138);
        spellPut("tang", -13120);
        spellPut("tao", -13107);
        spellPut("te", -13096);
        spellPut("teng", -13095);
        spellPut("ti", -13091);
        spellPut("tian", -13076);
        spellPut("tiao", -13068);
        spellPut("tie", -13063);
        spellPut("ting", -13060);
        spellPut("tong", -12888);
        spellPut("tou", -12875);
        spellPut("tu", -12871);
        spellPut("tuan", -12860);
        spellPut("tui", -12858);
        spellPut("tun", -12852);
        spellPut("tuo", -12849);
        spellPut("wa", -12838);
        spellPut("wai", -12831);
        spellPut("wan", -12829);
        spellPut("wang", -12812);
        spellPut("wei", -12802);
        spellPut("wen", -12607);
        spellPut("weng", -12597);
        spellPut("wo", -12594);
        spellPut("wu", -12585);
        spellPut("xi", -12556);
        spellPut("xia", -12359);
        spellPut("xian", -12346);
        spellPut("xiang", -12320);
        spellPut("xiao", -12300);
        spellPut("xie", -12120);
        spellPut("xin", -12099);
        spellPut("xing", -12089);
        spellPut("xiong", -12074);
        spellPut("xiu", -12067);
        spellPut("xu", -12058);
        spellPut("xuan", -12039);
        spellPut("xue", -11867);
        spellPut("xun", -11861);
        spellPut("ya", -11847);
        spellPut("yan", -11831);
        spellPut("yang", -11798);
        spellPut("yao", -11781);
        spellPut("ye", -11604);
        spellPut("yi", -11589);
        spellPut("yin", -11536);
        spellPut("ying", -11358);
        spellPut("yo", -11340);
        spellPut("yong", -11339);
        spellPut("you", -11324);
        spellPut("yu", -11303);
        spellPut("yuan", -11097);
        spellPut("yue", -11077);
        spellPut("yun", -11067);
        spellPut("za", -11055);
        spellPut("zai", -11052);
        spellPut("zan", -11045);
        spellPut("zang", -11041);
        spellPut("zao", -11038);
        spellPut("ze", -11024);
        spellPut("zei", -11020);
        spellPut("zen", -11019);
        spellPut("zeng", -11018);
        spellPut("zha", -11014);
        spellPut("zhai", -10838);
        spellPut("zhan", -10832);
        spellPut("zhang", -10815);
        spellPut("zhao", -10800);
        spellPut("zhe", -10790);
        spellPut("zhen", -10780);
        spellPut("zheng", -10764);
        spellPut("zhi", -10587);
        spellPut("zhong", -10544);
        spellPut("zhou", -10533);
        spellPut("zhu", -10519);
        spellPut("zhua", -10331);
        spellPut("zhuai", -10329);
        spellPut("zhuan", -10328);
        spellPut("zhuang", -10322);
        spellPut("zhui", -10315);
        spellPut("zhun", -10309);
        spellPut("zhuo", -10307);
        spellPut("zi", -10296);
        spellPut("zong", -10281);
        spellPut("zou", -10274);
        spellPut("zu", -10270);
        spellPut("zuan", -10262);
        spellPut("zui", -10260);
        spellPut("zun", -10256);
        spellPut("zuo", -10254);

        surnamePut("隗", "y");
        surnamePut("牧", "m");
        surnamePut("杨", "y");
        surnamePut("扈", "h");
        surnamePut("国", "g");
        surnamePut("米", "m");
        surnamePut("左", "z");
        surnamePut("栾", "l");
        surnamePut("万", "w");
        surnamePut("孟", "m");
        surnamePut("禹", "y");
        surnamePut("武", "w");
        surnamePut("祖", "z");
        surnamePut("郗", "x");
        surnamePut("庾", "y");
        surnamePut("秋", "q");
        surnamePut("宫", "g");
        surnamePut("宁", "n");
        surnamePut("屠", "t");
        surnamePut("周", "z");
        surnamePut("曾", "z");
        surnamePut("衡", "h");
        surnamePut("郑", "z");
        surnamePut("顾", "g");
        surnamePut("空", "k");
        surnamePut("宇", "y");
        surnamePut("阙", "q");
        surnamePut("姬", "j");
        surnamePut("孙", "s");
        surnamePut("令", "l");
        surnamePut("桂", "g");
        surnamePut("曲", "q");
        surnamePut("弘", "h");
        surnamePut("苏", "s");
        surnamePut("骆", "l");
        surnamePut("尤", "y");
        surnamePut("奚", "x");
        surnamePut("雍", "y");
        surnamePut("陆", "l");
        surnamePut("寿", "s");
        surnamePut("隆", "l");
        surnamePut("颜", "y");
        surnamePut("邢", "x");
        surnamePut("别", "b");
        surnamePut("欧", "o");
        surnamePut("熊", "x");
        surnamePut("却", "q");
        surnamePut("关", "g");
        surnamePut("狄", "d");
        surnamePut("房", "f");
        surnamePut("张", "z");
        surnamePut("梁", "l");
        surnamePut("沃", "w");
        surnamePut("许", "x");
        surnamePut("马", "m");
        surnamePut("陈", "c");
        surnamePut("仰", "y");
        surnamePut("和", "h");
        surnamePut("龙", "l");
        surnamePut("蓝", "l");
        surnamePut("纪", "j");
        surnamePut("邰", "t");
        surnamePut("华", "h");
        surnamePut("滕", "t");
        surnamePut("仇", "q");
        surnamePut("平", "p");
        surnamePut("冷", "l");
        surnamePut("连", "l");
        surnamePut("董", "d");
        surnamePut("冯", "f");
        surnamePut("伍", "w");
        surnamePut("景", "j");
        surnamePut("巢", "c");
        surnamePut("羊", "y");
        surnamePut("穆", "m");
        surnamePut("伏", "f");
        surnamePut("钮", "n");
        surnamePut("申", "s");
        surnamePut("辛", "x");
        surnamePut("湛", "z");
        surnamePut("高", "g");
        surnamePut("喻", "y");
        surnamePut("皮", "p");
        surnamePut("水", "s");
        surnamePut("毕", "b");
        surnamePut("罗", "l");
        surnamePut("计", "j");
        surnamePut("鱼", "y");
        surnamePut("夏", "x");
        surnamePut("鄂", "e");
        surnamePut("路", "l");
        surnamePut("裴", "p");
        surnamePut("庞", "p");
        surnamePut("蓟", "j");
        surnamePut("敖", "a");
        surnamePut("赖", "l");
        surnamePut("屈", "q");
        surnamePut("邬", "w");
        surnamePut("巴", "b");
        surnamePut("谈", "t");
        surnamePut("滑", "h");
        surnamePut("苍", "c");
        surnamePut("皇", "h");
        surnamePut("终", "z");
        surnamePut("毛", "m");
        surnamePut("暨", "j");
        surnamePut("束", "s");
        surnamePut("糜", "m");
        surnamePut("冀", "j");
        surnamePut("应", "y");
        surnamePut("常", "c");
        surnamePut("耿", "g");
        surnamePut("柳", "l");
        surnamePut("匡", "k");
        surnamePut("缪", "m");
        surnamePut("雷", "l");
        surnamePut("胡", "h");
        surnamePut("俞", "y");
        surnamePut("莘", "s");
        surnamePut("艾", "a");
        surnamePut("双", "s");
        surnamePut("蔚", "w");
        surnamePut("汪", "w");
        surnamePut("金", "j");
        surnamePut("强", "q");
        surnamePut("史", "s");
        surnamePut("宣", "x");
        surnamePut("汤", "t");
        surnamePut("庄", "z");
        surnamePut("乐", "l");
        surnamePut("惠", "h");
        surnamePut("淳", "c");
        surnamePut("厍", "s");
        surnamePut("燕", "y");
        surnamePut("富", "f");
        surnamePut("姚", "y");
        surnamePut("刘", "l");
        surnamePut("满", "m");
        surnamePut("詹", "z");
        surnamePut("饶", "r");
        surnamePut("蒲", "p");
        surnamePut("司", "s");
        surnamePut("祝", "z");
        surnamePut("通", "t");
        surnamePut("韦", "w");
        surnamePut("璩", "q");
        surnamePut("褚", "c");
        surnamePut("池", "c");
        surnamePut("叶", "y");
        surnamePut("尚", "s");
        surnamePut("戴", "d");
        surnamePut("太", "t");
        surnamePut("郭", "g");
        surnamePut("甘", "g");
        surnamePut("赫", "h");
        surnamePut("胥", "x");
        surnamePut("郜", "g");
        surnamePut("车", "c");
        surnamePut("印", "y");
        surnamePut("翟", "d");
        surnamePut("郁", "y");
        surnamePut("窦", "d");
        surnamePut("益", "y");
        surnamePut("魏", "w");
        surnamePut("虞", "y");
        surnamePut("秦", "q");
        surnamePut("任", "r");
        surnamePut("费", "f");
        surnamePut("蒙", "m");
        surnamePut("逮", "d");
        surnamePut("卜", "b");
        surnamePut("广", "g");
        surnamePut("那", "n");
        surnamePut("邴", "b");
        surnamePut("弓", "g");
        surnamePut("后", "h");
        surnamePut("谢", "x");
        surnamePut("戚", "q");
        surnamePut("支", "z");
        surnamePut("游", "y");
        surnamePut("姜", "j");
        surnamePut("红", "h");
        surnamePut("韶", "s");
        surnamePut("浦", "p");
        surnamePut("杭", "h");
        surnamePut("童", "t");
        surnamePut("从", "c");
        surnamePut("古", "g");
        surnamePut("桑", "s");
        surnamePut("宗", "z");
        surnamePut("石", "s");
        surnamePut("刁", "d");
        surnamePut("符", "f");
        surnamePut("卢", "l");
        surnamePut("丁", "d");
        surnamePut("沈", "s");
        surnamePut("钟", "z");
        surnamePut("宋", "s");
        surnamePut("侯", "h");
        surnamePut("沙", "s");
        surnamePut("孔", "k");
        surnamePut("党", "d");
        surnamePut("包", "b");
        surnamePut("籍", "j");
        surnamePut("安", "a");
        surnamePut("班", "b");
        surnamePut("须", "x");
        surnamePut("诸", "z");
        surnamePut("赵", "z");
        surnamePut("裘", "q");
        surnamePut("康", "k");
        surnamePut("焦", "j");
        surnamePut("茹", "r");
        surnamePut("黎", "l");
        surnamePut("丰", "f");
        surnamePut("王", "w");
        surnamePut("羿", "y");
        surnamePut("步", "b");
        surnamePut("范", "f");
        surnamePut("长", "z");
        surnamePut("元", "y");
        surnamePut("劳", "l");
        surnamePut("鲁", "l");
        surnamePut("查", "c");
        surnamePut("蔺", "l");
        surnamePut("茅", "m");
        surnamePut("洪", "h");
        surnamePut("贾", "j");
        surnamePut("解", "j");
        surnamePut("勾", "g");
        surnamePut("田", "t");
        surnamePut("曹", "c");
        surnamePut("宓", "m");
        surnamePut("蒋", "j");
        surnamePut("阮", "r");
        surnamePut("桓", "h");
        surnamePut("鲍", "b");
        surnamePut("危", "w");
        surnamePut("林", "l");
        surnamePut("倪", "n");
        surnamePut("邓", "d");
        surnamePut("陶", "t");
        surnamePut("贺", "h");
        surnamePut("巫", "w");
        surnamePut("暴", "b");
        surnamePut("边", "b");
        surnamePut("程", "c");
        surnamePut("怀", "h");
        surnamePut("卞", "b");
        surnamePut("臧", "z");
        surnamePut("云", "y");
        surnamePut("傅", "f");
        surnamePut("宰", "z");
        surnamePut("东", "d");
        surnamePut("訾", "z");
        surnamePut("贲", "b");
        surnamePut("阚", "y");
        surnamePut("历", "l");
        surnamePut("彭", "p");
        surnamePut("酆", "f");
        surnamePut("荣", "r");
        surnamePut("幸", "x");
        surnamePut("吴", "w");
        surnamePut("白", "b");
        surnamePut("舒", "s");
        surnamePut("柯", "k");
        surnamePut("容", "r");
        surnamePut("祁", "q");
        surnamePut("乌", "w");
        surnamePut("唐", "t");
        surnamePut("甄", "z");
        surnamePut("黄", "h");
        surnamePut("李", "l");
        surnamePut("温", "w");
        surnamePut("潘", "p");
        surnamePut("薛", "x");
        surnamePut("溥", "p");
        surnamePut("逄", "p");
        surnamePut("嵇", "j");
        surnamePut("翁", "w");
        surnamePut("郦", "l");
        surnamePut("汲", "j");
        surnamePut("慎", "s");
        surnamePut("利", "l");
        surnamePut("项", "x");
        surnamePut("权", "q");
        surnamePut("章", "z");
        surnamePut("宿", "s");
        surnamePut("鞠", "j");
        surnamePut("巩", "g");
        surnamePut("莫", "m");
        surnamePut("席", "x");
        surnamePut("朱", "z");
        surnamePut("伊", "y");
        surnamePut("晁", "c");
        surnamePut("瞿", "q");
        surnamePut("充", "c");
        surnamePut("严", "y");
        surnamePut("习", "x");
        surnamePut("干", "g");
        surnamePut("慕", "m");
        surnamePut("井", "j");
        surnamePut("山", "s");
        surnamePut("咸", "x");
        surnamePut("芮", "r");
        surnamePut("娄", "l");
        surnamePut("阎", "y");
        surnamePut("家", "j");
        surnamePut("都", "d");
        surnamePut("钱", "q");
        surnamePut("尹", "y");
        surnamePut("蓬", "p");
        surnamePut("戈", "g");
        surnamePut("霍", "h");
        surnamePut("乜", "n");
        surnamePut("堵", "d");
        surnamePut("吕", "l");
        surnamePut("邵", "s");
        surnamePut("廉", "l");
        surnamePut("盛", "s");
        surnamePut("蔡", "c");
        surnamePut("文", "w");
        surnamePut("贝", "b");
        surnamePut("邹", "z");
        surnamePut("殳", "s");
        surnamePut("靳", "j");
        surnamePut("师", "s");
        surnamePut("禄", "l");
        surnamePut("钭", "d");
        surnamePut("濮", "p");
        surnamePut("相", "x");
        surnamePut("昌", "c");
        surnamePut("居", "j");
        surnamePut("季", "j");
        surnamePut("柴", "c");
        surnamePut("聂", "n");
        surnamePut("养", "y");
        surnamePut("储", "c");
        surnamePut("昝", "z");
        surnamePut("龚", "g");
        surnamePut("寇", "k");
        surnamePut("花", "h");
        surnamePut("单", "s");
        surnamePut("封", "f");
        surnamePut("全", "q");
        surnamePut("樊", "f");
        surnamePut("凌", "l");
        surnamePut("余", "y");
        surnamePut("段", "d");
        surnamePut("阳", "y");
        surnamePut("殷", "y");
        surnamePut("卓", "z");
        surnamePut("江", "j");
        surnamePut("毋", "w");
        surnamePut("麻", "m");
        surnamePut("郎", "l");
        surnamePut("农", "n");
        surnamePut("韩", "h");
        surnamePut("时", "s");
        surnamePut("杜", "d");
        surnamePut("徐", "x");
        surnamePut("于", "y");
        surnamePut("施", "s");
        surnamePut("柏", "b");
        surnamePut("谷", "g");
        surnamePut("梅", "m");
        surnamePut("夔", "k");
        surnamePut("荆", "j");
        surnamePut("越", "y");
        surnamePut("尉", "w");
        surnamePut("戎", "r");
        surnamePut("贡", "g");
        surnamePut("牛", "n");
        surnamePut("轩", "x");
        surnamePut("卫", "w");
        surnamePut("廖", "l");
        surnamePut("齐", "q");
        surnamePut("明", "m");
        surnamePut("扶", "f");
        surnamePut("谭", "t");
        surnamePut("公", "g");
        surnamePut("索", "s");
        surnamePut("晏", "y");
        surnamePut("竺", "z");
        surnamePut("简", "j");
        surnamePut("闻", "w");
        surnamePut("向", "x");
        surnamePut("蒯", "k");
        surnamePut("盍", "h");
        surnamePut("松", "s");
        surnamePut("仲", "z");
        surnamePut("上", "s");
        surnamePut("葛", "g");
        surnamePut("成", "c");
        surnamePut("能", "n");
        surnamePut("澹", "t");
        surnamePut("乔", "q");
        surnamePut("崔", "c");
        surnamePut("融", "r");
        surnamePut("荀", "x");
        surnamePut("岑", "c");
        surnamePut("方", "f");
        surnamePut("宦", "h");
        surnamePut("郝", "h");
        surnamePut("吉", "j");
        surnamePut("易", "y");
        surnamePut("闵", "m");
        surnamePut("凤", "f");
        surnamePut("萧", "x");
        surnamePut("何", "h");
        surnamePut("袁", "y");
        surnamePut("苗", "m");
        surnamePut("管", "g");
        surnamePut("邱", "q");
        surnamePut("冉", "r");
        surnamePut("苟", "g");
        surnamePut("来", "l");
        surnamePut("种", "z");

        surnamePut("阿", "a");
        surnamePut("兰", "l");
        surnamePut("尔", "e");
        surnamePut("纳", "n");
        surnamePut("鹏", "p");
        surnamePut("楼", "l");
        surnamePut("牟", "m");
        surnamePut("", "y");
    }

    /**
     * 获得单个汉字的Ascii.
     * 
     * @param cn
     *            char 汉字字符
     * @return int 错误返回 0,否则返回ascii
     */
    public static int getCnAscii(char cn) {
        byte[] bytes = null;
        try {
            bytes = (String.valueOf(cn)).getBytes("GBK");
        }
        catch (UnsupportedEncodingException e) {
            return 0;
        }

        if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误
            return 0;
        }

        if (bytes.length == 1) { // 英文字符
            return bytes[0];
        }

        if (bytes.length == 2) { // 中文字符
            int hightByte = 256 + bytes[0];
            int lowByte = 256 + bytes[1];

            int ascii = (256 * hightByte + lowByte) - 256 * 256;

            return ascii;
        }

        return 0; // 错误
    }

    /**
     * 根据ASCII码到SpellMap中查找对应的拼音
     * 
     * @param ascii
     *            int 字符对应的ASCII
     * @return String 拼音,首先判断ASCII是否>0& <160,如果是返回对应的字符,
     * 
     *         否则到SpellMap中查找,如果没有找到拼音,则返回null,如果找到则返回拼音.
     */
    public static String getSpellByAscii(int ascii) {
        if (ascii > 0 && ascii < 160) { // 单字符
            return String.valueOf((char) ascii);
        }

        if (ascii < -20319 || ascii > -10247) { // 不知道的字符
            return null;
        }

        Set<String> keySet = spellMap.keySet();
        Iterator<String> it = keySet.iterator();

        String spell0 = null;
        String spell = null;

        int asciiRang0 = -20319;
        int asciiRang;
        while (it.hasNext()) {
            spell = it.next();
            Object valObj = spellMap.get(spell);
            if (valObj instanceof Integer) {
                asciiRang = ((Integer) valObj).intValue();

                if (ascii >= asciiRang0 && ascii < asciiRang) { // 区间找到
                    return (spell0 == null) ? spell : spell0;
                }
                else {
                    spell0 = spell;
                    asciiRang0 = asciiRang;
                }
            }
        }

        return null;

    }

    /**
     * 返回字符串的全拼,是汉字转化为全拼,其它字符不进行转换
     * 
     * @param cnStr
     *            String 字符串
     * @return String 转换成全拼后的字符串
     */
    public static String getFullSpell(String cnStr) {
        if (Validators.isEmpty(cnStr)) {
            return cnStr;
        }

        char[] chars = cnStr.toCharArray();
        StringBuilder retuBuf = new StringBuilder();
        for (int i = 0, Len = chars.length; i < Len; i++) {
            int ascii = getCnAscii(chars[i]);
            if (ascii == 0) { // 取ascii时出错
                retuBuf.append(chars[i]);
            }
            else {
                String spell = getSpellByAscii(ascii);
                if (spell == null) {
                    retuBuf.append(chars[i]);
                }
                else {
                    retuBuf.append(spell);
                } // end of if spell == null
            } // end of if ascii <= -20400
        } // end of for

        return retuBuf.toString();
    }

    /**
     * 
     * 取得字符的中文拼音
     * 
     * @param cnChar
     * @return 字符的中文拼音
     */
    public static String getFullSpell(char cnChar) {
        return getFullSpell(new String(new char[] { cnChar }));
    }

    /**
     * 
     * 取得字符的中文拼音首字母
     * 
     * @param cnChar
     * @return 字符的中文拼音首字母
     */
    public static String getFirstSpell(char cnChar) {
        String str = getFullSpell(cnChar);

        if (Validators.isEmpty(str)) {
            return str;
        }

        return new String(new char[] { str.charAt(0) });
    }

    /**
     * 
     * 取得中文拼音首字母
     * 
     * @param cnStr
     * @return 中文拼音首字母
     */
    public static String getFirstSpell(String cnStr) {
        if (Validators.isEmpty(cnStr)) {
            return cnStr;
        }

        StringBuilder StringBuilder = new StringBuilder();
        for (int i = 0; i < cnStr.length(); i++) {
            StringBuilder.append(getFirstSpell(cnStr.charAt(i)));
        }

        return StringBuilder.toString();
    }

    /**
     * 
     * 取得中文的拼音缩写，其中第一个为全拼，后面为首字母
     * 
     * @param cnStr
     * @return 拼音缩写
     */
    public static String getAbbreviateSpell(String cnStr) {
        if (Validators.isEmpty(cnStr)) {
            return cnStr;
        }

        StringBuilder StringBuilder = new StringBuilder();
        for (int i = 0; i < cnStr.length(); i++) {
            if (i == 0) {
                StringBuilder.append(getFullSpell(cnStr.charAt(i)));
            }
            else {
                StringBuilder.append(getFirstSpell(cnStr.charAt(i)));
            }
        }
        return StringBuilder.toString();
    }

    /**
     * 取得姓的拼音首字母
     * 
     * @param cnName
     *            姓名
     * @return 姓的拼音首字母
     */
    public static String getSurnameFirstSpell(String cnName) {
        if (Validators.isEmpty(cnName)) {
            return cnName;
        }

        String surnameFirstSpell = surnameMap.get(cnName.substring(0, 1));
        return surnameFirstSpell;
    }

    /**
     * 取得一批姓名的姓的首字母字符串数组
     * 
     * @param cnNames
     *            姓名字符串数组
     * @return 首字母字符串数组
     */
    public static String[] getSurnameFirstSpellRange(String[] cnNames) {
        HashSet<String> surnameFirstSpellSet = new HashSet<String>();
        for (int i = 0; i < cnNames.length; i++) {
            String surnameFirstSpell = getSurnameFirstSpell(cnNames[i]);
            if (surnameFirstSpell != null) {
                surnameFirstSpellSet.add(surnameFirstSpell);
            }
        }

        ArrayList<String> surnameFirstSpellList = new ArrayList<String>(surnameFirstSpellSet);
        Collections.sort(surnameFirstSpellList);

        return ArrayUtils.toArray(surnameFirstSpellList);
    }

}

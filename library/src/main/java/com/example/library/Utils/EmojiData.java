package com.example.library.Utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;


import com.example.library.R;
import com.example.library.Utils.EmojiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 惠普 on 2018-03-16.
 */

public class EmojiData {


    public Map<String,Integer> initDatas(){
        Map<String,Integer> map=new HashMap<>();


        map.put("[呲牙]", R.drawable.f000);
        map.put("[调皮]", R.drawable.f001);
        map.put("[流汗]", R.drawable.f002);
        map.put("[偷笑]", R.drawable.f003);
        map.put("[再见]", R.drawable.f004);
        map.put("[敲打]", R.drawable.f005);
        map.put("[擦汗]", R.drawable.f006);
        map.put("[猪头]", R.drawable.f007);
        map.put("[玫瑰]", R.drawable.f008);
        map.put("[流泪]", R.drawable.f009);
        map.put("[大哭]", R.drawable.f010);
        map.put("[嘘]", R.drawable.f011);
        map.put("[酷]", R.drawable.f012);
        map.put("[抓狂]", R.drawable.f013);
        map.put("[委屈]", R.drawable.f014);
        map.put("[便便]", R.drawable.f015);
        map.put("[炸弹]", R.drawable.f016);
        map.put("[菜刀]", R.drawable.f017);
        map.put("[可爱]", R.drawable.f018);
        map.put("[色]", R.drawable.f019);
        map.put("[害羞]", R.drawable.f020);

        map.put("[得意]", R.drawable.f021);
        map.put("[吐]", R.drawable.f022);
        map.put("[微笑]", R.drawable.f023);
        map.put("[发怒]", R.drawable.f024);
        map.put("[尴尬]", R.drawable.f025);
        map.put("[惊恐]", R.drawable.f026);
        map.put("[冷汗]", R.drawable.f027);
        map.put("[爱心]", R.drawable.f028);
        map.put("[示爱]", R.drawable.f029);
        map.put("[白眼]", R.drawable.f030);
        map.put("[傲慢]", R.drawable.f031);
        map.put("[难过]", R.drawable.f032);
        map.put("[惊讶]", R.drawable.f033);
        map.put("[疑问]", R.drawable.f034);
        map.put("[睡]", R.drawable.f035);
        map.put("[亲亲]", R.drawable.f036);
        map.put("[憨笑]", R.drawable.f037);
        map.put("[爱情]", R.drawable.f038);
        map.put("[衰]", R.drawable.f039);
        map.put("[撇嘴]", R.drawable.f040);
        map.put("[阴险]", R.drawable.f041);

        map.put("[奋斗]", R.drawable.f042);
        map.put("[发呆]", R.drawable.f043);
        map.put("[右哼哼]", R.drawable.f044);
        map.put("[拥抱]", R.drawable.f045);
        map.put("[坏笑]", R.drawable.f046);
        map.put("[飞吻]", R.drawable.f047);
        map.put("[鄙视]", R.drawable.f048);
        map.put("[晕]", R.drawable.f049);
        map.put("[大兵]", R.drawable.f050);
        map.put("[可怜]", R.drawable.f051);
        map.put("[强]", R.drawable.f052);
        map.put("[弱]", R.drawable.f053);
        map.put("[握手]", R.drawable.f054);
        map.put("[胜利]", R.drawable.f055);
        map.put("[抱拳]", R.drawable.f056);
        map.put("[凋谢]", R.drawable.f057);
        map.put("[饭]", R.drawable.f058);
        map.put("[蛋糕]", R.drawable.f059);
        map.put("[西瓜]", R.drawable.f060);
        map.put("[啤酒]", R.drawable.f061);
        map.put("[飘虫]", R.drawable.f062);

        map.put("[勾引]", R.drawable.f063);
        map.put("[OK]", R.drawable.f064);
        map.put("[爱你]", R.drawable.f065);
        map.put("[咖啡]", R.drawable.f066);
        map.put("[钱]", R.drawable.f067);
        map.put("[月亮]", R.drawable.f068);
        map.put("[美女]", R.drawable.f069);
        map.put("[刀]", R.drawable.f070);
        map.put("[发抖]", R.drawable.f071);
        map.put("[差劲]", R.drawable.f072);
        map.put("[拳头]", R.drawable.f073);
        map.put("[心碎]", R.drawable.f074);
        map.put("[太阳]", R.drawable.f075);
        map.put("[礼物]", R.drawable.f076);
        map.put("[足球]", R.drawable.f077);
        map.put("[骷髅]", R.drawable.f078);
        map.put("[挥手]", R.drawable.f079);
        map.put("[闪电]", R.drawable.f080);
        map.put("[饥饿]", R.drawable.f081);
        map.put("[困]", R.drawable.f082);
        map.put("[咒骂]", R.drawable.f083);

        map.put("[折磨]", R.drawable.f084);
        map.put("[抠鼻]", R.drawable.f085);
        map.put("[鼓掌]", R.drawable.f086);
        map.put("[糗大了]", R.drawable.f087);
        map.put("[左哼哼]", R.drawable.f088);
        map.put("[哈欠]", R.drawable.f089);
        map.put("[快哭了]", R.drawable.f090);
        map.put("[吓]", R.drawable.f091);
        map.put("[篮球]", R.drawable.f092);
        map.put("[乒乓球]", R.drawable.f093);
        map.put("[NO]", R.drawable.f094);
        map.put("[跳跳]", R.drawable.f095);
        map.put("[怄火]", R.drawable.f096);
        map.put("[转圈]", R.drawable.f097);
        map.put("[磕头]", R.drawable.f098);
        map.put("[回头]", R.drawable.f099);
        map.put("[跳绳]", R.drawable.f100);
        map.put("[激动]", R.drawable.f101);
        map.put("[街舞]", R.drawable.f102);
        map.put("[献吻]", R.drawable.f103);
        map.put("[左太极]", R.drawable.f104);

        map.put("[右太极]", R.drawable.f105);
        map.put("[闭嘴]", R.drawable.f106);


        return map;
    }
    public List<Map<String,Integer>> jgzDatas(){
        List<Map<String,Integer>> data=new ArrayList<Map<String,Integer>>();

        for(int i = 0; i< EmojiUtils.jingguanzhang.length; i++){
            Map<String,Integer> map = new HashMap<String,Integer>();
            map.put(String.valueOf(i), EmojiUtils.jingguanzhang[i]);
            data.add(map);
        }

        return data;
    }
    /**
     * 展示表情到文本框中
     * @param context
     * @return
     */
    public SpannableString disPlayEmoji(Map<String,Integer> data,Context context){

        ImageSpan span = null;
        SpannableString spanStr = null;
        for (  Map.Entry<String, Integer> entry : data.entrySet()) {
            span = new ImageSpan(context, entry.getValue());
            spanStr = new SpannableString(entry.getKey());
        }
                    /*
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE --- 不包含两端start和end所在的端点        (a,b)
                    Spanned.SPAN_EXCLUSIVE_INCLUSIVE --- 不包含端start，但包含end所在的端点     (a,b]
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE --- 包含两端start，但不包含end所在的端点   [a,b)
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE --- 包含两端start和end所在的端点          [a,b]
                    */
        //图片,从哪里开始替换,替换结束的位置
        spanStr.setSpan(span, 0, spanStr.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanStr;
    }
}

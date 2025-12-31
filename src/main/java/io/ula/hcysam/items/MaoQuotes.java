package io.ula.hcysam.items;

import io.ula.hcysam.listeners.ServerTickListener;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitlesPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class MaoQuotes extends Item {
    private final String[] maoQuotes = {
            "领导我们事业的核心力量是中国共产党。指导我们思想的理论基础是马克思列宁主义。——《中华人民共和国第一届全国人民代表大会第一次会议开幕词》（一九五四年九月十五日）",
            "既要革命，就要有一个革命的党。没有一个革命的党，没有一个按照马克思列宁主义的革命理论和革命风格建立起来的革命党，就不可能领导工人阶级和广大人民群众战胜帝国主义及其走狗。——《全世界革命力量团结起来，反对帝国主义的侵略》（一九四八年十一月）",
            "我们应当相信群众，我们应当相信党，这是两条根本的原理。如果怀疑这两条原理，那就什么事情也做不成了。——《关于农业合作化问题》（一九五五年七月三十一日）",
            "阶级斗争，一些阶级胜利了，一些阶级消灭了。这就是历史，这就是几千年的文明史。——《丢掉幻想，准备斗争》（一九四九年八月十四日）",
            "革命不是请客吃饭，不是做文章，不是绘画绣花，不能那样雅致，那样从容不迫，文质彬彬，那样温良恭俭让。革命是暴动，是一个阶级推翻一个阶级的暴烈的行动。——《湖南农民运动考察报告》（一九二七年三月）",
            "谁是我们的敌人？谁是我们的朋友？这个问题是革命的首要问题。——《中国社会各阶级的分析》（一九二六年三月）",
            "帝国主义者和国内反动派决不甘心于他们的失败，他们还要作最后的挣扎。——《在中国人民政治协商会议第一届全体会议上的开幕词》（一九四九年九月二十一日）",
            "社会主义制度终究要代替资本主义制度，这是一个不以人们自己的意志为转移的客观规律。——《在苏联最高苏维埃庆祝伟大的十月社会主义革命四十周年会议上的讲话》（一九五七年十一月六日）",
            "人民，只有人民，才是创造世界历史的动力。——《论联合政府》（一九四五年四月二十四日）",
            "群众是真正的英雄，而我们自己则往往是幼稚可笑的，不了解这一点，就不能得到起码的知识。——《〈农村调查〉的序言和跋》（一九四一年三月、四月）",
            "一切反动派都是纸老虎。看起来，反动派的样子是可怕的，但是实际上并没有什么了不起的力量。——《和美国记者安娜·路易斯·斯特朗的谈话》（一九四六年八月）",
            "在战略上我们要藐视一切敌人，在战术上我们要重视一切敌人。——《在各国共产党和工人党莫斯科会议上的讲话》（一九五七年十一月十八日）",
            "下定决心，不怕牺牲，排除万难，去争取胜利。——《愚公移山》（一九四五年六月十一日）",
            "革命战争是群众的战争，只有动员群众才能进行战争，只有依靠群众才能进行战争。——《关心群众生活，注意工作方法》（一九三四年一月二十七日）",
            "没有一个人民的军队，便没有人民的一切。——《论联合政府》（一九四五年四月二十四日）",
            "政策和策略是党的生命，各级领导同志务必充分注意，万万不可粗心大意。——《关于情况的通报》（一九四八年三月二十日）",
            "从群众中来，到群众中去。——《关于领导方法的若干问题》（一九四三年六月一日）",
            "政治工作是一切经济工作的生命线。——《严重的教训》一文的按语（一九五五年）",
            "我们都是来自五湖四海，为了一个共同的革命目标，走到一起来了。——《为人民服务》（一九四四年九月八日）",
            "武器是战争的重要的因素，但不是决定的因素，决定的因素是人不是物。——《论持久战》（一九三八年五月）",
            "我们应该谦虚，谨慎，戒骄，戒躁，全心全意地为人民服务。——《两个中国之命运》（一九四五年四月二十三日）",
            "爱国主义的具体内容，看在什么样的历史条件之下来决定。——《中国共产党在民族战争中的地位》（一九三八年十月）",
            "这个军队具有一往无前的精神，它要压倒一切敌人，而决不被敌人所屈服。——《论联合政府》（一九四五年四月二十四日）",
            "要使全体干部和全体人民经常想到我国是一个社会主义的大国，但又是一个经济落后的穷国，这是一个很大的矛盾。——《关于正确处理人民内部矛盾的问题》（一九五七年二月二十七日）",
            "我们的方针要放在什么基点上？放在自己力量的基点上，叫做自力更生。——《抗日战争胜利后的时局和我们的方针》（一九四五年八月十三日）",
            "没有调查就没有发言权。——《〈农村调查〉的序言和跋》（一九四一年三月、四月）",
            "实事求是。——《改造我们的学习》（一九四一年五月）",
            "国家的统一，人民的团结，国内各民族的团结，这是我们的事业必定要胜利的基本保证。——《关于正确处理人民内部矛盾的问题》（一九五七年二月二十七日）",
            "批评和自我批评这个方法竟是一个很好的方法。——《关于正确处理人民内部矛盾的问题》（一九五七年二月二十七日）",
            "读书是学习，使用也是学习，而且是更重要的学习。——《中国革命战争的战略问题》（一九三六年十二月）"
    };
    public MaoQuotes(FabricItemSettings settings){
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide()&&ServerTickListener.getNullableServer()!=null){
            ServerTickListener.sendPlayerTitle(ServerTickListener.getNullableServer(),player, ClientboundSetTitlesPacket.Type.ACTIONBAR,new TextComponent(maoQuotes[new Random().nextInt(maoQuotes.length-1)]).withStyle(ChatFormatting.GOLD));
        }
        return InteractionResultHolder.success(player.getItemInHand(interactionHand));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        list.add(new TextComponent("“并不是要宣传什么毛派言论，只是觉得里面多少有些是正确的。”").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        list.add(new TextComponent("右键使用来获得指引").withStyle(ChatFormatting.DARK_AQUA));
    }
}

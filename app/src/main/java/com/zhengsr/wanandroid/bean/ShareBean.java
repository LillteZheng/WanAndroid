package com.zhengsr.wanandroid.bean;

import java.util.List;

/**
 * @auther by zhengshaorui on 2019/12/1
 * describe:
 */
public class ShareBean {

    /**
     * coinInfo : {"coinCount":2918,"level":30,"rank":5,"userId":2,"username":"x**oyang"}
     * shareArticles : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>RT，挺有意思的问题，为什么呢？<\/p>\r\n\r\n<p>这是星球里面一位朋友的提问，<a style=\"font-size:14px;\" href=\"https://wanandroid.com/blog/show/2701\">想加入可以点这里<\/a>。<\/p>\r\n","envelopePic":"","fresh":true,"id":10482,"link":"https://www.wanandroid.com/wenda/show/10482","niceDate":"15小时前","niceShareDate":"2019-11-26 00:10","origin":"","prefix":"","projectLink":"","publishTime":1575118168000,"selfVisible":0,"shareDate":1574698219000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | Activity启动流程中，大部分都是用Binder通讯，为啥跟Zygote通信的时候要用socket呢？","type":0,"userId":2,"visible":1,"zan":14},{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>上周几个小伙伴聊到一个比较有意思的问题，大家都知道浮点数相加，会丢失精度：<\/p>\r\n<p>看这个例子：<\/p>\r\n<pre><code>    public static void main(String[] args){\r\n        System.out.println(0.3f + 0.6f);\r\n        System.out.println(0.3 + 0.6);\r\n        System.out.println(0.9);\r\n    }\r\n<\/code><\/pre><pre><code>输出:\r\n\r\n0.90000004\r\n0.8999999999999999\r\n0.9\r\n<\/code><\/pre><p>问题是：<\/p>\r\n<p>精度为什么会丢失，计算机能存那么多数字，一个0.3+0.6怎么就丢精度了呢？<\/p>\r\n<p>附加题：为何 float 和 double 的计算输出结果差异还挺大的呢？<\/p>\r\n<blockquote>\r\n<p>jdk 1.8.0<\/p>\r\n<\/blockquote>","envelopePic":"","fresh":true,"id":10574,"link":"https://www.wanandroid.com/wenda/show/10574","niceDate":"15小时前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1575118164000,"selfVisible":0,"shareDate":1575113261000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问  精度到底是哪里丢失了？","type":1,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10572,"link":"https://www.jianshu.com/p/b153d63d60b3?utm_source=desktop&amp;utm_medium=timeline","niceDate":"20小时前","niceShareDate":"20小时前","origin":"","prefix":"","projectLink":"","publishTime":1575100188000,"selfVisible":0,"shareDate":1575100188000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 重学系列 资源的查找 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10570,"link":"https://juejin.im/post/5de12277e51d4532c42c5b0e","niceDate":"22小时前","niceShareDate":"22小时前","origin":"","prefix":"","projectLink":"","publishTime":1575094936000,"selfVisible":0,"shareDate":1575094936000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 重新认识 Android 图片适配 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10569,"link":"https://www.jianshu.com/p/056612535d9d","niceDate":"22小时前","niceShareDate":"22小时前","origin":"","prefix":"","projectLink":"","publishTime":1575094159000,"selfVisible":0,"shareDate":1575094159000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"教你如何实现一个将xml中的strings和colors硬编码写入到values中的插件 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10544,"link":"https://juejin.im/post/5dd22694e51d4561da085408","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574959112000,"selfVisible":0,"shareDate":1574949244000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"羞！扒开字节码，我竟发现这个.....","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10548,"link":"https://www.jianshu.com/p/248ef6db02c5","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574959100000,"selfVisible":0,"shareDate":1574958045000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"知乎 Android 客户端三方库敏感代码扫描机制 - FindDanger","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10543,"link":"https://juejin.im/post/5ddf85d25188251e7d26c288","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574949211000,"selfVisible":0,"shareDate":1574949211000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" Now in Android | 11 月刊 &middot; 2019 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":506,"chapterName":"compose","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10525,"link":"https://www.bilibili.com/video/av77091765/","niceDate":"2019-11-27 23:15","niceShareDate":"2019-11-27 20:32","origin":"","prefix":"","projectLink":"","publishTime":1574867704000,"selfVisible":0,"shareDate":1574857968000,"shareUser":"鸿洋","superChapterId":423,"superChapterName":"Jetpack","tags":[],"title":"理解 Jetpack Compose | ADS 中文字幕视频_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":74,"chapterName":"反编译","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10526,"link":"https://juejin.im/post/5ddd55426fb9a071a929d528","niceDate":"2019-11-27 23:14","niceShareDate":"2019-11-27 20:45","origin":"","prefix":"","projectLink":"","publishTime":1574867667000,"selfVisible":0,"shareDate":1574858737000,"shareUser":"鸿洋","superChapterId":160,"superChapterName":"热门专题","tags":[],"title":"因一纸设计稿，我把竞品APP扒得裤衩不剩(下)","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10502,"link":"https://www.jianshu.com/p/c61307e79ac2?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2019-11-26 23:52","niceShareDate":"2019-11-26 23:52","origin":"","prefix":"","projectLink":"","publishTime":1574783543000,"selfVisible":0,"shareDate":1574783543000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 之如何优化 UI 渲染（上） ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10501,"link":"https://www.jianshu.com/p/e8f0be9e1c15?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2019-11-26 23:52","niceShareDate":"2019-11-26 23:52","origin":"","prefix":"","projectLink":"","publishTime":1574783527000,"selfVisible":0,"shareDate":1574783527000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Activity Hook填坑过程中温故而知新 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10500,"link":"https://juejin.im/post/5ddc8e3ce51d4523617deaaf","niceDate":"2019-11-26 23:46","niceShareDate":"2019-11-26 23:46","origin":"","prefix":"","projectLink":"","publishTime":1574783192000,"selfVisible":0,"shareDate":1574783192000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" WebView开源库终极学习方案 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10484,"link":"https://www.jianshu.com/p/60ac9e073308","niceDate":"2019-11-26 00:25","niceShareDate":"2019-11-26 00:25","origin":"","prefix":"","projectLink":"","publishTime":1574699122000,"selfVisible":0,"shareDate":1574699122000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"APP中如何更好的使用弹窗？ ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10483,"link":"https://juejin.im/post/5ddb2b5b51882573461819e0","niceDate":"2019-11-26 00:21","niceShareDate":"2019-11-26 00:21","origin":"","prefix":"","projectLink":"","publishTime":1574698915000,"selfVisible":0,"shareDate":1574698915000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" Android10填坑适配指南，实际经验代码，拒绝翻译 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>很多时候，大家在讲解butterknife原理的时候，很多时候会跟别的 ioc 库做对比，会说：<\/p>\r\n<p>\u201cbutterknife性能更好，基于编译时注解，不需要反射\u201d<\/p>\r\n<p>那么，今天的问题是：<\/p>\r\n<ol>\r\n<li>butterknife 的原理是？<\/li>\r\n<li>butterknife 中有用到反射吗？<\/li>\r\n<li>如果用到，在哪里用到？<\/li>\r\n<\/ol>","envelopePic":"","fresh":false,"id":10427,"link":"https://www.wanandroid.com/wenda/show/10427","niceDate":"2019-11-26 00:12","niceShareDate":"2019-11-22 00:09","origin":"","prefix":"","projectLink":"","publishTime":1574698321000,"selfVisible":0,"shareDate":1574352578000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | butterknife 中真的没有反射吗？","type":0,"userId":2,"visible":1,"zan":17},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10452,"link":"https://juejin.im/post/5dd766e1e51d45233c7e857f#heading-13","niceDate":"2019-11-24 17:58","niceShareDate":"2019-11-24 17:58","origin":"","prefix":"","projectLink":"","publishTime":1574589519000,"selfVisible":0,"shareDate":1574589519000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 聊一聊关于Glide在面试中的那些事 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10451,"link":"https://www.jianshu.com/p/1dd77e56cc3c?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2019-11-24 17:56","niceShareDate":"2019-11-24 17:56","origin":"","prefix":"","projectLink":"","publishTime":1574589375000,"selfVisible":0,"shareDate":1574589375000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android中Https请求如何防止中间人攻击和Charles抓包原理 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10438,"link":"https://juejin.im/post/5dd7872d51882572ff406383#comment","niceDate":"2019-11-22 23:15","niceShareDate":"2019-11-22 23:15","origin":"","prefix":"","projectLink":"","publishTime":1574435718000,"selfVisible":0,"shareDate":1574435718000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 高质量App的架构设计与思考！ ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":93,"chapterName":"基础知识","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10424,"link":"https://juejin.im/post/5dd499a6f265da0bf21126cc#heading-13","niceDate":"2019-11-22 00:12","niceShareDate":"2019-11-21 23:48","origin":"","prefix":"","projectLink":"","publishTime":1574352769000,"selfVisible":0,"shareDate":1574351305000,"shareUser":"鸿洋","superChapterId":126,"superChapterName":"自定义控件","tags":[],"title":"「Android10源码分析」为什么复杂布局会产生卡顿？-- LayoutInflater详解","type":0,"userId":2,"visible":1,"zan":0}],"offset":0,"over":false,"pageCount":10,"size":20,"total":197}
     */

    private CoinInfoBean coinInfo;
    private ShareArticlesBean shareArticles;

    public CoinInfoBean getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfoBean coinInfo) {
        this.coinInfo = coinInfo;
    }

    public ShareArticlesBean getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(ShareArticlesBean shareArticles) {
        this.shareArticles = shareArticles;
    }


    public static class CoinInfoBean {
        /**
         * coinCount : 2918
         * level : 30
         * rank : 5
         * userId : 2
         * username : x**oyang
         */

        private int coinCount;
        private int level;
        private int rank;
        private int userId;
        private String username;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class ShareArticlesBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>RT，挺有意思的问题，为什么呢？<\/p>\r\n\r\n<p>这是星球里面一位朋友的提问，<a style=\"font-size:14px;\" href=\"https://wanandroid.com/blog/show/2701\">想加入可以点这里<\/a>。<\/p>\r\n","envelopePic":"","fresh":true,"id":10482,"link":"https://www.wanandroid.com/wenda/show/10482","niceDate":"15小时前","niceShareDate":"2019-11-26 00:10","origin":"","prefix":"","projectLink":"","publishTime":1575118168000,"selfVisible":0,"shareDate":1574698219000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | Activity启动流程中，大部分都是用Binder通讯，为啥跟Zygote通信的时候要用socket呢？","type":0,"userId":2,"visible":1,"zan":14},{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>上周几个小伙伴聊到一个比较有意思的问题，大家都知道浮点数相加，会丢失精度：<\/p>\r\n<p>看这个例子：<\/p>\r\n<pre><code>    public static void main(String[] args){\r\n        System.out.println(0.3f + 0.6f);\r\n        System.out.println(0.3 + 0.6);\r\n        System.out.println(0.9);\r\n    }\r\n<\/code><\/pre><pre><code>输出:\r\n\r\n0.90000004\r\n0.8999999999999999\r\n0.9\r\n<\/code><\/pre><p>问题是：<\/p>\r\n<p>精度为什么会丢失，计算机能存那么多数字，一个0.3+0.6怎么就丢精度了呢？<\/p>\r\n<p>附加题：为何 float 和 double 的计算输出结果差异还挺大的呢？<\/p>\r\n<blockquote>\r\n<p>jdk 1.8.0<\/p>\r\n<\/blockquote>","envelopePic":"","fresh":true,"id":10574,"link":"https://www.wanandroid.com/wenda/show/10574","niceDate":"15小时前","niceShareDate":"17小时前","origin":"","prefix":"","projectLink":"","publishTime":1575118164000,"selfVisible":0,"shareDate":1575113261000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问  精度到底是哪里丢失了？","type":1,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10572,"link":"https://www.jianshu.com/p/b153d63d60b3?utm_source=desktop&amp;utm_medium=timeline","niceDate":"20小时前","niceShareDate":"20小时前","origin":"","prefix":"","projectLink":"","publishTime":1575100188000,"selfVisible":0,"shareDate":1575100188000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 重学系列 资源的查找 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10570,"link":"https://juejin.im/post/5de12277e51d4532c42c5b0e","niceDate":"22小时前","niceShareDate":"22小时前","origin":"","prefix":"","projectLink":"","publishTime":1575094936000,"selfVisible":0,"shareDate":1575094936000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 重新认识 Android 图片适配 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":10569,"link":"https://www.jianshu.com/p/056612535d9d","niceDate":"22小时前","niceShareDate":"22小时前","origin":"","prefix":"","projectLink":"","publishTime":1575094159000,"selfVisible":0,"shareDate":1575094159000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"教你如何实现一个将xml中的strings和colors硬编码写入到values中的插件 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10544,"link":"https://juejin.im/post/5dd22694e51d4561da085408","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574959112000,"selfVisible":0,"shareDate":1574949244000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"羞！扒开字节码，我竟发现这个.....","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10548,"link":"https://www.jianshu.com/p/248ef6db02c5","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574959100000,"selfVisible":0,"shareDate":1574958045000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"知乎 Android 客户端三方库敏感代码扫描机制 - FindDanger","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10543,"link":"https://juejin.im/post/5ddf85d25188251e7d26c288","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1574949211000,"selfVisible":0,"shareDate":1574949211000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" Now in Android | 11 月刊 &middot; 2019 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":506,"chapterName":"compose","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10525,"link":"https://www.bilibili.com/video/av77091765/","niceDate":"2019-11-27 23:15","niceShareDate":"2019-11-27 20:32","origin":"","prefix":"","projectLink":"","publishTime":1574867704000,"selfVisible":0,"shareDate":1574857968000,"shareUser":"鸿洋","superChapterId":423,"superChapterName":"Jetpack","tags":[],"title":"理解 Jetpack Compose | ADS 中文字幕视频_哔哩哔哩 (゜-゜)つロ 干杯~-bilibili","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":74,"chapterName":"反编译","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10526,"link":"https://juejin.im/post/5ddd55426fb9a071a929d528","niceDate":"2019-11-27 23:14","niceShareDate":"2019-11-27 20:45","origin":"","prefix":"","projectLink":"","publishTime":1574867667000,"selfVisible":0,"shareDate":1574858737000,"shareUser":"鸿洋","superChapterId":160,"superChapterName":"热门专题","tags":[],"title":"因一纸设计稿，我把竞品APP扒得裤衩不剩(下)","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10502,"link":"https://www.jianshu.com/p/c61307e79ac2?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2019-11-26 23:52","niceShareDate":"2019-11-26 23:52","origin":"","prefix":"","projectLink":"","publishTime":1574783543000,"selfVisible":0,"shareDate":1574783543000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 之如何优化 UI 渲染（上） ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10501,"link":"https://www.jianshu.com/p/e8f0be9e1c15?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2019-11-26 23:52","niceShareDate":"2019-11-26 23:52","origin":"","prefix":"","projectLink":"","publishTime":1574783527000,"selfVisible":0,"shareDate":1574783527000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Activity Hook填坑过程中温故而知新 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10500,"link":"https://juejin.im/post/5ddc8e3ce51d4523617deaaf","niceDate":"2019-11-26 23:46","niceShareDate":"2019-11-26 23:46","origin":"","prefix":"","projectLink":"","publishTime":1574783192000,"selfVisible":0,"shareDate":1574783192000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" WebView开源库终极学习方案 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10484,"link":"https://www.jianshu.com/p/60ac9e073308","niceDate":"2019-11-26 00:25","niceShareDate":"2019-11-26 00:25","origin":"","prefix":"","projectLink":"","publishTime":1574699122000,"selfVisible":0,"shareDate":1574699122000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"APP中如何更好的使用弹窗？ ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10483,"link":"https://juejin.im/post/5ddb2b5b51882573461819e0","niceDate":"2019-11-26 00:21","niceShareDate":"2019-11-26 00:21","origin":"","prefix":"","projectLink":"","publishTime":1574698915000,"selfVisible":0,"shareDate":1574698915000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" Android10填坑适配指南，实际经验代码，拒绝翻译 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>很多时候，大家在讲解butterknife原理的时候，很多时候会跟别的 ioc 库做对比，会说：<\/p>\r\n<p>\u201cbutterknife性能更好，基于编译时注解，不需要反射\u201d<\/p>\r\n<p>那么，今天的问题是：<\/p>\r\n<ol>\r\n<li>butterknife 的原理是？<\/li>\r\n<li>butterknife 中有用到反射吗？<\/li>\r\n<li>如果用到，在哪里用到？<\/li>\r\n<\/ol>","envelopePic":"","fresh":false,"id":10427,"link":"https://www.wanandroid.com/wenda/show/10427","niceDate":"2019-11-26 00:12","niceShareDate":"2019-11-22 00:09","origin":"","prefix":"","projectLink":"","publishTime":1574698321000,"selfVisible":0,"shareDate":1574352578000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | butterknife 中真的没有反射吗？","type":0,"userId":2,"visible":1,"zan":17},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10452,"link":"https://juejin.im/post/5dd766e1e51d45233c7e857f#heading-13","niceDate":"2019-11-24 17:58","niceShareDate":"2019-11-24 17:58","origin":"","prefix":"","projectLink":"","publishTime":1574589519000,"selfVisible":0,"shareDate":1574589519000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 聊一聊关于Glide在面试中的那些事 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10451,"link":"https://www.jianshu.com/p/1dd77e56cc3c?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2019-11-24 17:56","niceShareDate":"2019-11-24 17:56","origin":"","prefix":"","projectLink":"","publishTime":1574589375000,"selfVisible":0,"shareDate":1574589375000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android中Https请求如何防止中间人攻击和Charles抓包原理 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10438,"link":"https://juejin.im/post/5dd7872d51882572ff406383#comment","niceDate":"2019-11-22 23:15","niceShareDate":"2019-11-22 23:15","origin":"","prefix":"","projectLink":"","publishTime":1574435718000,"selfVisible":0,"shareDate":1574435718000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":" 高质量App的架构设计与思考！ ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","chapterId":93,"chapterName":"基础知识","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":10424,"link":"https://juejin.im/post/5dd499a6f265da0bf21126cc#heading-13","niceDate":"2019-11-22 00:12","niceShareDate":"2019-11-21 23:48","origin":"","prefix":"","projectLink":"","publishTime":1574352769000,"selfVisible":0,"shareDate":1574351305000,"shareUser":"鸿洋","superChapterId":126,"superChapterName":"自定义控件","tags":[],"title":"「Android10源码分析」为什么复杂布局会产生卡顿？-- LayoutInflater详解","type":0,"userId":2,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 10
         * size : 20
         * total : 197
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<ArticleData> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ArticleData> getDatas() {
            return datas;
        }

        public void setDatas(List<ArticleData> datas) {
            this.datas = datas;
        }



    }

}

package com.philipli.smartzone.bean;

import java.util.List;

/**
 * Created by philipli on 2017/12/22.
 */

public class NewsDetail {


    private String body;
    private int replyCount;
    private String shareLink;
    private String digest;
    private ExtraCardBean extraCard;
    private String dkeys;
    private String ec;
    private String docid;
    private SourceinfoBean sourceinfo;
    private boolean picnews;
    private String title;
    private String tid;
    private String template;
    private int threadVote;
    private int threadAgainst;
    private String replyBoard;
    private String source;
    private boolean hasNext;
    private String voicecomment;
    private String ptime;
    private List<?> users;
    private List<?> ydbaike;
    private List<?> link;
    private List<ImgBean> img;
    private List<?> votes;
    private List<TopiclistNewsBean> topiclist_news;
    private List<TopiclistBean> topiclist;
    private List<AskbarBean> askbar;
    private List<AutoRecomendsBean> autoRecomends;
    private List<?> boboList;
    private List<HuatiBean> huati;

    public static class ExtraCardBean {

        private String title;
        private String extraurl;
        private List<ListBean> list;

        public static class ListBean {

            private String name;
            private String imgsrc;
            private String digest;
            private String url;

            public String getName() {
                return name;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public String getDigest() {
                return digest;
            }

            public String getUrl() {
                return url;
            }
        }

        public String getTitle() {
            return title;
        }

        public String getExtraurl() {
            return extraurl;
        }

        public List<ListBean> getList() {
            return list;
        }
    }

    public static class SourceinfoBean {

        private String alias;
        private String ename;
        private String tname;
        private String tid;

        public String getAlias() {
            return alias;
        }

        public String getEname() {
            return ename;
        }

        public String getTname() {
            return tname;
        }

        public String getTid() {
            return tid;
        }
    }

    public static class ImgBean {

        private String ref;
        private String pixel;
        private String alt;
        private String src;

        public String getRef() {
            return ref;
        }

        public String getPixel() {
            return pixel;
        }

        public String getAlt() {
            return alt;
        }

        public String getSrc() {
            return src;
        }
    }


    public static class TopiclistNewsBean {

        private boolean hasCover;
        private String subnum;
        private String alias;
        private String tname;
        private String ename;
        private String tid;
        private String cid;

        public boolean isHasCover() {
            return hasCover;
        }

        public String getSubnum() {
            return subnum;
        }

        public String getAlias() {
            return alias;
        }

        public String getTname() {
            return tname;
        }

        public String getEname() {
            return ename;
        }

        public String getTid() {
            return tid;
        }

        public String getCid() {
            return cid;
        }
    }

    public static class TopiclistBean {

        private boolean hasCover;
        private String subnum;
        private String alias;
        private String tname;
        private String ename;
        private String tid;
        private String cid;

        public boolean isHasCover() {
            return hasCover;
        }

        public String getSubnum() {
            return subnum;
        }

        public String getAlias() {
            return alias;
        }

        public String getTname() {
            return tname;
        }

        public String getEname() {
            return ename;
        }

        public String getTid() {
            return tid;
        }

        public String getCid() {
            return cid;
        }
    }

    public static class AskbarBean {
        private String title;
        private String headpicurl;
        private String alias;
        private String expertId;
        private String name;
        private int concernCount;

        public String getTitle() {
            return title;
        }

        public String getHeadpicurl() {
            return headpicurl;
        }

        public String getAlias() {
            return alias;
        }

        public String getExpertId() {
            return expertId;
        }

        public String getName() {
            return name;
        }

        public int getConcernCount() {
            return concernCount;
        }
    }

    public static class AutoRecomendsBean {

        private String name;
        private String imgsrc;
        private String chexi_id;
        private double youhui;
        private String youhuinew;
        private String url;

        public String getName() {
            return name;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public String getChexi_id() {
            return chexi_id;
        }

        public double getYouhui() {
            return youhui;
        }

        public String getYouhuinew() {
            return youhuinew;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class HuatiBean {

        private String topicId;
        private String topicName;

        public String getTopicId() {
            return topicId;
        }

        public String getTopicName() {
            return topicName;
        }
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public String getShareLink() {
        return shareLink;
    }

    public String getDigest() {
        return digest;
    }

    public ExtraCardBean getExtraCard() {
        return extraCard;
    }

    public String getDkeys() {
        return dkeys;
    }

    public String getEc() {
        return ec;
    }

    public String getDocid() {
        return docid;
    }

    public SourceinfoBean getSourceinfo() {
        return sourceinfo;
    }

    public boolean isPicnews() {
        return picnews;
    }

    public String getTitle() {
        return title;
    }

    public String getTid() {
        return tid;
    }

    public String getTemplate() {
        return template;
    }

    public int getThreadVote() {
        return threadVote;
    }

    public int getThreadAgainst() {
        return threadAgainst;
    }

    public String getReplyBoard() {
        return replyBoard;
    }

    public String getSource() {
        return source;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public String getVoicecomment() {
        return voicecomment;
    }

    public String getPtime() {
        return ptime;
    }

    public List<?> getUsers() {
        return users;
    }

    public List<?> getYdbaike() {
        return ydbaike;
    }

    public List<?> getLink() {
        return link;
    }

    public List<ImgBean> getImg() {
        return img;
    }

    public List<?> getVotes() {
        return votes;
    }

    public List<TopiclistNewsBean> getTopiclist_news() {
        return topiclist_news;
    }

    public List<TopiclistBean> getTopiclist() {
        return topiclist;
    }

    public List<AskbarBean> getAskbar() {
        return askbar;
    }

    public List<AutoRecomendsBean> getAutoRecomends() {
        return autoRecomends;
    }

    public List<?> getBoboList() {
        return boboList;
    }

    public List<HuatiBean> getHuati() {
        return huati;
    }
}

package com.philipli.smartzone.bean;

import com.philipli.smartzone.R;
import com.philipli.smartzone.network.ParamNames;

import java.util.List;

/**
 * Created by philipli on 2017/12/22.
 */

public class NewsSummary {

    private String postid;
    private boolean hasCover;
    private int hasHead;
    private int replyCount;
    private int hasImg;
    private String digest;
    private boolean hasIcon;
    private String docid;
    private String title;
    private int order;
    private int priority;
    private String lmodify;
    private String boardid;
    private String photosetID;
    private int imgsum;
    private String topic_background;
    private String template;
    private int votecount;
    private String skipID;
    private String alias;
    private String skipType;
    private String cid;
    private int hasAD;
    private String source;
    private String ename;
    private String tname;
    private String imgsrc;
    private String ptime;
    private List<AdsBean> ads;

    public String getPostid() {
        return postid;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public int getHasImg() {
        return hasImg;
    }

    public String getDigest() {
        return digest;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public String getDocid() {
        return docid;
    }

    public String getTitle() {
        return title;
    }

    public int getOrder() {
        return order;
    }

    public int getPriority() {
        return priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public int getImgsum() {
        return imgsum;
    }

    public String getTopic_background() {
        return topic_background;
    }

    public String getTemplate() {
        return template;
    }

    public int getVotecount() {
        return votecount;
    }

    public String getSkipID() {
        return skipID;
    }

    public String getAlias() {
        return alias;
    }

    public String getSkipType() {
        return skipType;
    }

    public String getCid() {
        return cid;
    }

    public int getHasAD() {
        return hasAD;
    }

    public String getSource() {
        return source;
    }

    public String getEname() {
        return ename;
    }

    public String getTname() {
        return tname;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    private List<ImgextraBean> imgextra;

    public static class AdsBean {
        /**
         * title : 直击云南边境扫雷 地雷炮弹摆满地
         * tag : photoset
         * imgsrc : http://cms-bucket.nosdn.127.net/e4c85b6a0db74b139b0c40572181883a20170208163525.jpeg
         * subtitle :
         * url : 00AP0001|2233612
         */

        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public String getTitle() {
            return title;
        }

        public String getTag() {
            return tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class ImgextraBean {
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/3e8167782a344c47886dd4b818274e3720170208161145.jpeg
         */

        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }

}
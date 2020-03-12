package com.gameboxlink.videoadsdk.beans;


import java.util.List;

public class ServerBean {


    /**
     * Version : 1
     * PlayMode : 2
     * PlayStart : 1
     * BaseResUrl : http://www.gameboxlink.com/sdk2/uibase.zip
     * GameResUrl : http://www.gameboxlink.com/sdk2/game-1.zip
     * GameCount : 11
     * ResBg : [{"bg1":"bg1.jpg"},{"bg2":"bg2.jpg"},{"bg3":"bg3.jpg"}]
     * ResTitle : title.png
     * ResClose : close.png
     * ResFrameVideo : frame-video.png
     * ResDownloadNormal : download-1.png
     * ResDownloadClick : download-2.png
     * ResBanner1 : banner-1.png
     * ResBanner2 : banner-2.png
     * GameList1 : [{"id":0,"button":"game-1.gif","video":"game-1.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":1,"button":"game-2.gif","video":"game-2.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":2,"button":"game-3.gif","video":"game-3.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":3,"button":"game-4.gif","video":"game-4.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":4,"button":"game-5.gif","video":"game-5.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":5,"button":"game-6.gif","video":"game-6.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":6,"button":"game-7.gif","video":"game-7.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"}]
     * GameList2 : [{"id":0,"button":"game-1.gif","video":"game-8.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":1,"button":"game-2.gif","video":"game-9.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":2,"button":"game-3.gif","video":"game-10.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":3,"button":"game-4.gif","video":"game-11.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":4,"button":"game-5.gif","video":"game-7.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":5,"button":"game-6.gif","video":"game-6.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"},{"id":6,"button":"game-7.gif","video":"game-5.mp4","gplink":"https://play.google.com/store/apps/details?id=com.plus901.SlidePuzzle"}]
     */

    public String Version;
    public String PlayMode;
    public String PlayStart;
    public String BaseResUrl;
    public String GameResUrl;
    public String GameCount;
    public String ResTitle;
    public String ResClose;
    public String ResFrameVideo;
    public String ResDownloadNormal;
    public String ResDownloadClick;
    public String ResBanner1;
    public String ResBanner2;
    public List<ResBgBean> ResBg;
    public List<GameList1Bean> GameList1;
    public List<GameList2Bean> GameList2;

    public static class ResBgBean {
       
        public String bg1;
        public String bg2;
        public String bg3;
    }

    public static class GameList1Bean {

        public int id;
        public String button;
        public String video;
        public String gplink;
    }

    public static class GameList2Bean {

        public int id;
        public String button;
        public String video;
        public String gplink;
    }
}

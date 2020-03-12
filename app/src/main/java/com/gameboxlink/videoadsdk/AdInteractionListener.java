package com.gameboxlink.videoadsdk;

public interface AdInteractionListener {

   void onConfigStatus(boolean succ,String msg);
   void onLoadUiBase(boolean succ,String filePath);

}

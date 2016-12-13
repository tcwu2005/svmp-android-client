/*
 Copyright 2013 The MITRE Corporation, All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this work except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package org.mitre.svmp.activities;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;
import org.itri.vmi.client.R;
import org.mitre.svmp.common.AppInfo;
import org.mitre.svmp.common.Utility;
import org.mitre.svmp.widgets.AppInfoArrayAdapter;

import java.util.List;

/**
 * This fragment is used in the AppList activity to display a list of remote apps that are available for a Connection
 * @author Joe Portner
 */
public class AppListFragment extends Fragment {
    protected AppList activity;
    protected List<AppInfo> appInfoList;
    protected GridView gridView;
    
    protected List<AppInfo> appInfoPoc;
    AppInfo[] appInfox;
    AppInfo[] appInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_list, container, false);

        activity = (AppList)getActivity();

        gridView = (GridView)view.findViewById(R.id.appList_gridView);
        populateLayout();

        // set title text
        ActionBar actionBar = activity.getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setSubtitle(activity.connectionInfo.getDescription());
        }

        // enable long-click on the GridView
        registerForContextMenu(gridView);

        return view;
    }

    protected void populateLayout() {
    	
        // get the list of AppInfo objects
        if ("favorites".equals(getTag())){
        	/*appInfoPoc = activity.dbHandler.getAppInfoList_All(activity.connectionID);
            appInfox = appInfoPoc.toArray(new AppInfo[appInfoPoc.size()]);
            int j=0;
            for(int i = 0;i < appInfox.length;i++){
            	//Log.i("appInfox",appInfox[i].getAppName());
            	if (appInfox[i].getAppName().equals("Phone") 
            			|| appInfox[i].getAppName().equals("People")
            			|| appInfox[i].getAppName().equals("Search")
            			|| appInfox[i].getAppName().equals("Settings")
            			|| appInfox[i].getAppName().equals("Speech Recorder")
            			|| appInfox[i].getAppName().equals("Calculator")
            			|| appInfox[i].getAppName().equals("Calendar")
            			|| appInfox[i].getAppName().equals("Calibration")
            			|| appInfox[i].getAppName().equals("Clock")
            			|| appInfox[i].getAppName().equals("Dev Tools")
            			|| appInfox[i].getAppName().equals("Downloads")
            			|| appInfox[i].getAppName().equals("File Manager")
            			|| appInfox[i].getAppName().equals("Google Settings")
            			|| appInfox[i].getAppName().equals("Messaging")
            			|| appInfox[i].getAppName().equals("News & Weather")
            			|| appInfox[i].getAppName().equals("RSS Reader")
            			|| appInfox[i].getAppName().equals("Superuser")
            			|| appInfox[i].getAppName().equals("Terminal Emulator")
            			|| appInfox[i].getAppName().equals("Voice Search")
            			|| appInfox[i].getAppName().equals("Music")
            			|| appInfox[i].getAppName().equals("Airbnb")
            			|| appInfox[i].getAppName().equals("ES File Explorer")
            			|| appInfox[i].getAppName().equals("Evernote")
            			|| appInfox[i].getAppName().equals("Facebook")
            			|| appInfox[i].getAppName().equals("Gallery")
            			|| appInfox[i].getAppName().equals("Gmail")
            			|| appInfox[i].getAppName().equals("Google+")
            			|| appInfox[i].getAppName().equals("KKBOX")
            			|| appInfox[i].getAppName().equals("Mo PTT")
            			|| appInfox[i].getAppName().equals("Notes")
            			|| appInfox[i].getAppName().equals("TaipeiMetro")
            			|| appInfox[i].getAppName().equals("TpWeather")
            			|| appInfox[i].getAppName().equals("comico")
            			|| appInfox[i].getAppName().equals("Math Tricks")
            			|| appInfox[i].getAppName().equals("北市法規")
            			|| appInfox[i].getAppName().equals("台灣蘋果日報")
            			|| appInfox[i].getAppName().equals("地震測報")
            			|| appInfox[i].getAppName().equals("臺北市房地產整合資訊")
            			|| appInfox[i].getAppName().equals("臺北市政信箱")
            			|| appInfox[i].getAppName().equals("行動水情")
            			|| appInfox[i].getAppName().equals("超注音(直購版)")
|| appInfox[i].getAppName().equals("Adobe Acrobat")
|| appInfox[i].getAppName().equals("Browser")
|| appInfox[i].getAppName().equals("Email")
|| appInfox[i].getAppName().equals("Google")
|| appInfox[i].getAppName().equals("Hangouts")
|| appInfox[i].getAppName().equals("Play Store")
|| appInfox[i].getAppName().equals("Polaris Office"))
            	{
            		appInfoPoc.remove(i-j);
            		j++;
            	}
            }
            appInfox = appInfoPoc.toArray(new AppInfo[appInfoPoc.size()]);
            for(int i = 0;i < appInfox.length;i++){
            	Log.i("appInfox",appInfox[i].getAppName());
            	}
            appInfo = appInfoPoc.toArray(new AppInfo[appInfoPoc.size()]);*/
           appInfoList = activity.dbHandler.getAppInfoList_Favorites(activity.connectionID);
           appInfo = appInfoList.toArray(new AppInfo[appInfoList.size()]);
        }else{
            appInfoList = activity.dbHandler.getAppInfoList_All(activity.connectionID);
            appInfo = appInfoList.toArray(new AppInfo[appInfoList.size()]);
        }
        // populate the items in the ListView
        gridView.setAdapter(new AppInfoArrayAdapter(activity,
        		appInfo)); // use app grid items
    }

    // Context Menu handles long-pressing (prompt allows user to edit or remove connections)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.appList_gridView){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            AppInfo appInfo = appInfoList.get(info.position);

            menu.setHeaderTitle(appInfo.getAppName());
            if (appInfo.isFavorite())
                menuAdd(menu, R.string.contextMenu_appList_removeFavorite);
            else
                menuAdd(menu, R.string.contextMenu_appList_addFavorite);//POC暫時拿掉()
            menuAdd(menu, R.string.contextMenu_appList_createShortcut);
            menuAdd(menu, R.string.contextMenu_appList_removeShortcut);
        }
    }

    private void menuAdd(ContextMenu menu, int resId) {
        menu.add(Menu.NONE, resId, Menu.NONE, resId);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        AppInfo appInfo = appInfoList.get(info.position);
       // AppInfo appInfo1 = AppInfo(); 

        switch(item.getItemId()){
            case R.string.contextMenu_appList_addFavorite:
            	 
               //Log.i("AppInfo", appInfo.getAppName());
                // add the app to favorites in the DB
                activity.dbHandler.updateAppInfo_Favorite(appInfo, true);
                // repopulate the layout of this tab and all other tabs
                activity.populateLayout();
                activity.toastShort(R.string.appList_toast_addedFavorite);
                break;
            case R.string.contextMenu_appList_removeFavorite:
                // remove the app from favorites in the DB
                activity.dbHandler.updateAppInfo_Favorite(appInfo, false);
                // repopulate the layout of this tab and all other tabs
                activity.populateLayout();
                activity.toastShort(R.string.appList_toast_removedFavorite);
                break;
            case R.string.contextMenu_appList_createShortcut: // Create shortcut
                Utility.createShortcut(activity, appInfo);
                activity.toastShort(R.string.appList_toast_createdShortcut);
                break;
            case R.string.contextMenu_appList_removeShortcut: // Remove shortcut
                Utility.removeShortcut(activity, appInfo);
                activity.toastShort(R.string.appList_toast_removedShortcuts);
                break;
        }
        return true;
    }
}
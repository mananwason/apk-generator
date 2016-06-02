package org.fossasia.openevent.api.processor;

import android.util.Log;

import org.fossasia.openevent.OpenEventApp;
import org.fossasia.openevent.api.protocol.SessionResponseList;
import org.fossasia.openevent.data.Session;
import org.fossasia.openevent.dbutils.DbContract;
import org.fossasia.openevent.dbutils.DbSingleton;
import org.fossasia.openevent.events.SessionDownloadEvent;
import org.fossasia.openevent.utils.CommonTaskLoop;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * User: MananWason
 * Date: 27-05-2015
 */
public class SessionListResponseProcessor implements Callback<SessionResponseList> {
    private static final String TAG = "Session";

    @Override
    public void onResponse(Call<SessionResponseList> call, final Response<SessionResponseList> response) {
        if (response.isSuccessful()) {
            CommonTaskLoop.getInstance().post(new Runnable() {

                @Override
                public void run() {
                    ArrayList<String> queries = new ArrayList<String>();
                    for (Session session : response.body().sessions) {
                        String query = session.generateSql();
                        queries.add(query);
                        Log.d(TAG, query);
                    }

                    DbSingleton dbSingleton = DbSingleton.getInstance();
                    dbSingleton.clearDatabase(DbContract.Sessions.TABLE_NAME);
                    dbSingleton.insertQueries(queries);
                    OpenEventApp.postEventOnUIThread(new SessionDownloadEvent(true));
                }


            });
        } else {
            OpenEventApp.getEventBus().post(new SessionDownloadEvent(false));
        }
    }

    @Override
    public void onFailure(Call<SessionResponseList> call, Throwable t) {
        OpenEventApp.getEventBus().post(new SessionDownloadEvent(false));
    }
}
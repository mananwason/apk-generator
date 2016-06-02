package org.fossasia.openevent;

import android.test.AndroidTestCase;

import org.fossasia.openevent.api.APIClient;
import org.fossasia.openevent.api.Urls;
import org.fossasia.openevent.api.protocol.EventResponseList;
import org.fossasia.openevent.api.protocol.MicrolocationResponseList;
import org.fossasia.openevent.api.protocol.SessionResponseList;
import org.fossasia.openevent.api.protocol.SpeakerResponseList;
import org.fossasia.openevent.api.protocol.SponsorResponseList;
import org.fossasia.openevent.api.protocol.TrackResponseList;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * User: mohit
 * Date: 25/5/15
 */
public class APITest extends AndroidTestCase {
    public void testSpeakerAPIResponse() throws Exception {
        APIClient client = new APIClient();
        final CountDownLatch latch = new CountDownLatch(1);
        client.getOpenEventAPI().getSpeakers(Urls.EVENT_ID).enqueue(new Callback<SpeakerResponseList>() {
            @Override
            public void onResponse(Call<SpeakerResponseList> call, Response<SpeakerResponseList> response) {
                if (response.isSuccessful()) {
                    assertNotNull(response.body().speakers);
                    // Assert that the list size > 0
                    assertTrue(response.body().speakers.size() > 0);
                    latch.countDown();
                } else {
                    fail("API Request Failed");
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<SpeakerResponseList> call, Throwable t) {
                fail("API Request Failed");
                latch.countDown();
            }
        });

        latch.await();
    }

    public void testSponsorAPIResponse() throws Exception {
        APIClient client = new APIClient();
        final CountDownLatch latch = new CountDownLatch(1);
        client.getOpenEventAPI().getSponsors(Urls.EVENT_ID).enqueue(new Callback<SponsorResponseList>() {
            @Override
            public void onResponse(Call<SponsorResponseList> call, Response<SponsorResponseList> response) {
                if (response.isSuccessful()) {
                    assertNotNull(response.body().sponsors);
                    // Assert that the list size > 0
                    assertTrue(response.body().sponsors.size() > 0);
                    latch.countDown();

                } else {
                    fail("API Request Failed");
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<SponsorResponseList> call, Throwable t) {
                fail("API Request Failed");
                latch.countDown();
            }
        });
        latch.await();
    }

    public void testEventAPIResponse() throws Exception {
        APIClient client = new APIClient();
        final CountDownLatch latch = new CountDownLatch(1);
        client.getOpenEventAPI().getEvents().enqueue(new Callback<EventResponseList>() {
            @Override
            public void onResponse(Call<EventResponseList> call, Response<EventResponseList> response) {
                if (response.isSuccessful()) {
                    assertNotNull(response.body().event);
                    assertTrue(response.body().event.size() > 0);
                    latch.countDown();
                } else {
                    fail("API Request Failed");
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<EventResponseList> call, Throwable t) {
                fail("API Request Failed");
                latch.countDown();
            }
        });
        latch.await();
    }

    public void testSessionAPIResponse() throws Exception {
        APIClient client = new APIClient();
        final CountDownLatch latch = new CountDownLatch(1);
        client.getOpenEventAPI().getSessions(Urls.EVENT_ID).enqueue(new Callback<SessionResponseList>() {
            @Override
            public void onResponse(Call<SessionResponseList> call, Response<SessionResponseList> response) {
                if (response.isSuccessful()) {
                    assertNotNull(response.body().sessions);
                    assertTrue(response.body().sessions.size() > 0);
                    latch.countDown();
                } else {
                    fail("API Request Failed");
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<SessionResponseList> call, Throwable t) {
                fail("API Request Failed");
                latch.countDown();
            }
        });
        latch.await();
    }

    public void testTrackAPIResponse() throws Exception {
        APIClient client = new APIClient();
        final CountDownLatch latch = new CountDownLatch(1);
        client.getOpenEventAPI().getTracks(Urls.EVENT_ID).enqueue(new Callback<TrackResponseList>() {
            @Override
            public void onResponse(Call<TrackResponseList> call, Response<TrackResponseList> response) {
                if (response.isSuccessful()) {
                    assertNotNull(response.body().tracks);
                    assertTrue(response.body().tracks.size() > 0);
                    latch.countDown();
                } else {
                    fail("API Request Failed");
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<TrackResponseList> call, Throwable t) {
                fail("API Request Failed");
                latch.countDown();
            }
        });
        latch.await();
    }

    public void testMicrolocationAPIResponse() throws Exception {
        APIClient client = new APIClient();
        final CountDownLatch latch = new CountDownLatch(1);
        client.getOpenEventAPI().getMicrolocations(Urls.EVENT_ID).enqueue(new Callback<MicrolocationResponseList>() {
            @Override
            public void onResponse(Call<MicrolocationResponseList> call, Response<MicrolocationResponseList> response) {
                if (response.isSuccessful()) {
                    assertNotNull(response.body().microlocations);
                    assertTrue(response.body().microlocations.size() > 0);
                    latch.countDown();
                } else {
                    fail("API Request Failed");
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<MicrolocationResponseList> call, Throwable t) {
                fail("API Request Failed");
                latch.countDown();
            }
        });
        latch.await();
    }
}

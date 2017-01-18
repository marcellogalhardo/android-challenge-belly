package br.com.marcellogalhardo.mobilelocation.nearby;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import br.com.marcellogalhardo.mobilelocation.R;
import br.com.marcellogalhardo.mobilelocation.util.MockWebServerUtil;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NearByActivityUiTest {

    private static final int MOCK_WEB_SERVER_PORT = 1234;

    @Rule
    public ActivityTestRule<NearByActivity> activityTestRule =
            new ActivityTestRule<>(NearByActivity.class, false, true);

    private MockWebServer server = new MockWebServer();

    @Before
    public void setup() throws IOException {
        server.start(MOCK_WEB_SERVER_PORT);
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    public void shouldShowError() throws IOException {
        server.enqueue(MockWebServerUtil.getGenericError());

        launchNearByActivity();
        waitMuch();

        onView(withId(R.id.error_layout))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowRetryWithSuccessWhenErrorHappens() throws IOException {
        NearByActivity activity = activityTestRule.getActivity();
        server.enqueue(MockWebServerUtil.getGenericError());
        server.enqueue(MockWebServerUtil.getLocationsFake(activity));

        launchNearByActivity();
        waitMuch();

        onView(withId(R.id.error_layout))
                .check(matches(isDisplayed()));

        waitLittle();

        onView(withId(R.id.retry_button))
                .perform(click());

        waitTooMuch();

        onView(withId(R.id.recycler_view_business))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowSuccess() throws IOException {
        NearByActivity activity = activityTestRule.getActivity();
        server.enqueue(MockWebServerUtil.getLocationsFake(activity));

        launchNearByActivity();
        waitMuch();

        onView(withId(R.id.recycler_view_business))
                .check(matches(isDisplayed()));
    }

    private void launchNearByActivity() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
    }

    private void waitLittle() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitMuch() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitTooMuch() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
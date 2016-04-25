package com.devIan; /**
 * Created by iansethcolin on 4/24/16.
 */

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener()
public class SessionListener implements HttpSessionListener, HttpSessionIdListener
{

    private SimpleDateFormat formatter =
            new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");


    @Override
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
        System.out.println(this.date() + ": Session " + se.getSession().getId() +
            " created.");
        SessionRegistry.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
        System.out.println(this.date() + ": Session " + se.getSession().getId() +
                " destroyed.");
        SessionRegistry.removeSession(se.getSession());
    }

    @Override
    public void sessionIdChanged(HttpSessionEvent e, String oldSessionId)
    {
        System.out.println(this.date() + ": Session Id " + oldSessionId +
            " changed to " + e.getSession().getId());
        SessionRegistry.updateSessionId(e.getSession(), oldSessionId);
    }

    private String date()
    {
        return this.formatter.format(new Date());
    }

}

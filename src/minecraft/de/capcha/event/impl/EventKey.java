package de.capcha.event.impl;

import de.capcha.event.Event;

public class EventKey extends Event {

    final int key;

    public EventKey(int key) {
         this.key = key;
    }

    public int getKey() {
        return key;
    }

}

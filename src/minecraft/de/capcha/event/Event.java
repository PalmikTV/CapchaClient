package de.capcha.event;

public class Event {

    private boolean cancelled;

    public boolean iscancelled () {
        return cancelled;
    }

        public void setCancelled(boolean cancelled) {
            this.cancelled = cancelled;
        }
}

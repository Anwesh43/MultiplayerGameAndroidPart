package com.anwesome.ui.eventbusmodule;

import com.squareup.otto.Bus;

/**
 * Created by anweshmishra on 03/01/17.
 */
public class BusUtil {
    private static Bus bus = new Bus();
    public static Bus getBus() {
        return bus;
    }
}

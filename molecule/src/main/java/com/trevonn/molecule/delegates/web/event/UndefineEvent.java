package com.trevonn.molecule.delegates.web.event;


import com.trevonn.molecule.util.log.AtomLogger;

/**
 * Created by Trevonn on 2018/2/9.
 */

public class UndefineEvent extends Event {

    @Override
    public String execute(String params) {
       AtomLogger.e("UndefineEvent",params);
        return null;
    }
}

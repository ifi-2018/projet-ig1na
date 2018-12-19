package com.ifi.tp.fights.bo;

import java.util.ArrayList;
import java.util.List;

public class FightLogs {
    private int id;

    private List<String> logs;

    public FightLogs() {
        this.logs = new ArrayList<>();
    }

    public void addLog(String log) {
        this.logs.add(log);
    }

    public List<String> getLogs() {
        return this.logs;
    }
}

package com.company;

import java.util.List;

public class Delete implements Command {
    private SchedulingConnection state;

    public Delete(SchedulingConnection state){
        this.state=state;
    }

    @Override
    public void executeAction(int id) {
             state.delete(id);

    }
}

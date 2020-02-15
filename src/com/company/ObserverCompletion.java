package com.company;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class ObserverCompletion implements Observer {

    private int completion;
    private GridPane grid;

    public ObserverCompletion(GridPane grid){
        this.completion=0;
        this.grid=grid;
    }

    @Override
    public void update(int cs) {
        this.completion=cs;

        for (int i = 0; i < completion; i++) {
            if (i % 2 == 0) {
                Label timeline = new Label(String.valueOf(i));
//                    timeline.setAlignment(Pos.CENTER);
                GridPane.setConstraints(timeline, i, 0);
                FadeTransition ft = new FadeTransition(Duration.millis(1500), timeline);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();

               grid.getChildren().add(timeline);

            }
        }


    }


}

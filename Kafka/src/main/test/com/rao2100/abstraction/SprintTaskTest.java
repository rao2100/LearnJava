package com.rao2100.abstraction;

import com.rao2100.nashorn.NashornCalculate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintTaskTest {

    @Test
    void sprintTaskDoAction() {
        BaseTask task = new RunTask();
        task.start();
        task = new JogTask();
        task.start();
        task = new SprintTask();
        task.start();

    }

}
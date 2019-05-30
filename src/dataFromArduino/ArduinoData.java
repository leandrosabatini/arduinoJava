/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataFromArduino;

/**
 *
 * @author leand
 */
public class ArduinoData {
    int led1;
    int led2;
    int led3;

    public ArduinoData() {
    }

    public ArduinoData(int led1, int led2, int led3) {
        this.led1 = led1;
        this.led2 = led2;
        this.led3 = led3;
    }
    
    public int getLed1() {
        return led1;
    }

    public void setLed1(int led1) {
        this.led1 = led1;
    }

    public int getLed2() {
        return led2;
    }

    public void setLed2(int led2) {
        this.led2 = led2;
    }

    public int getLed3() {
        return led3;
    }

    public void setLed3(int led3) {
        this.led3 = led3;
    }
}

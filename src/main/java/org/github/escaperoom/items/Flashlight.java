
// Katelynn Prater - 7/18/25
// Flashlight class for room 3 in future
package org.github.escaperoom.items;
import org.github.escaperoom.TextMethods;

public class Flashlight extends Item {
    private boolean isOn;
    private boolean hasBatteries;
    private boolean pointingAtMiasma;
    private final TextMethods txt = TextMethods.getInstance(); // singleton methods for all files

    public Flashlight() { //for room 3 when made
        super("Flashlight", "a dusty, plastic flashlight. It's been dinged up. It has no batteries.", true);
        isOn = false;
        hasBatteries = false;
        pointingAtMiasma = false;
    }
    // constructors and mutators
    public boolean isOn() {return isOn;}
    public void setOn(boolean isOn) {this.isOn = isOn;}
    public boolean hasBatteries() {return hasBatteries;}
    public void setBatteries(boolean hasBatteries) {this.hasBatteries = hasBatteries;}
    public boolean pointingAtMiasma() {return pointingAtMiasma;}
    public void setPointingAtMiasma(boolean pointingAtMiasma) {this.pointingAtMiasma = pointingAtMiasma;}

    @Override // abstract Item.java
    public void use() {
        if (!isOn || !hasBatteries) {
            turnOn();
        }
        else {
            turnOff();
        }
    }

    @Override
    public void introduce() {
        txt.typeWriterNormal("You find " + desc + " You decide to put it into your inventory for safe keeping.");
    }

    public void turnOn() {
        if (hasBatteries) {
            txt.typeWriterNormal("You press the button. It is surprisingly bright for such a battered flashlight. Its beam cuts through dust motes and seemingly disintegrates them.");
            setOn(true);
            if (Math.random() < 0.05) { //easter egg
                txt.typeWriterNormal("...You swear, just for a second, the beam flickered in a way that outlined something watching.");
            }
        }
        else {
            txt.typeWriterNormal("You try to press the button. It has no batteries. Obviously it won't turn on.");
        }
    } // end turnOn

    public void turnOff() {
        txt.typeWriterNormal("You decide to turn off the flashlight. The sudden drop in brightness makes you blink.");
        setOn(false);
        pointingAtMiasma = false;
    } 

    public boolean illuminatingMiasma() { //mechanic later, not relevant yet
        return (pointingAtMiasma && isOn);
    }

    public void pointAtMiasma() {
        pointingAtMiasma = true;
    }
} // end flashlight class

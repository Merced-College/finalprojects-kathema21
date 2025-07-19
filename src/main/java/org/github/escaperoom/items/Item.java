
package org.github.escaperoom.items;

public abstract class Item { 
    protected String name;
    protected String desc;
    protected boolean consumable; // there will be one item that doesn't disintegrate, differentiates that

    public Item(String name, String desc, boolean consumable) {
        this.name = name;
        this.desc = desc;
        this.consumable = consumable;
    }

    public String getName() {return name;}
    public String getDesc() {return desc;}
    public boolean isConsumable() {return consumable;}

    public abstract void use();
    
    public abstract void introduce();

    @Override //for dev
    public String toString() {
        return "name: " + name + ", desc: " + desc;
    }
} // end item class

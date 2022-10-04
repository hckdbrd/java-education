package com.knubisoft.tasks.algorithm;

import lombok.Data;

import java.util.List;
@Data
public class ModelRoot {

    public List<Item> items;

    public static class Batter {
        public String id;
        public String type;
    }

    @Data
    public static class Item {
        public int id;
        public String type;
        public String name;
        public double ppu;
        public Batters batters;
        public List<Topping> topping;
    }

    public static class Batters {
        public List<Batter> batter;
    }

    public static class Topping {
        public String id;
        public String type;
    }

}

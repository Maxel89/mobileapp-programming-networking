package com.example.networking;

public class Mountain {
    private String id;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    private int size;
    private int cost;
    private String link;
    private String image;

    public Mountain(String id, String name, String type, String company, String location, String category, int size, int cost, String link, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
        this.location = location;
        this.category = category;
        this.size = size;
        this.cost = cost;
        this.link = link;
        this.image = image;
    }


    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getCompany(){
        return company;
    }

    public String getLocation(){
        return location;
    }

    public String getCategory(){
        return category;
    }

    public int getSize(){
        return size;
    }

    public int getCost(){
        return cost;
    }

    public String getLink(){
        return link;
    }

    public String getImage(){
        return image;
    }

    public String toString(){
        return name;
    }
}

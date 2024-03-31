package com.example.Warehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "blanket")
public class Blanket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "article")
    @NotEmpty(message = "Необходимо указать артикул")
    private String article;

    @Column(name = "title")
    @NotEmpty(message = "Необходимо указать название")
    private String title;

    @Column(name = "material_filler")
    @NotEmpty(message = "Необходимо указать материал наполнителя")
    private String materialFiller;

    @Column(name = "material_covers")
    @NotEmpty(message = "Необходимо указать материал чехла")
    private String materialCovers;

    @Column(name = "color", length = 30)
    @NotEmpty(message = "Необходимо указать цвет")
    @Size(max = 30, message = "Название цвета не должно превышать 30 символов")
    private String color;

    public Blanket(int id, String article, String title, String materialFiller, String materialCovers, String color) {
        this.id = id;
        this.article = article;
        this.title = title;
        this.materialFiller = materialFiller;
        this.materialCovers = materialCovers;
        this.color = color;
    }

    public Blanket() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getArticle() { return article; }

    public void setArticle(String article) { this.article = article; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getMaterialFiller() { return materialFiller; }

    public void setMaterialFiller(String materialFiller) { this.materialFiller = materialFiller; }

    public String getMaterialCovers() { return materialCovers; }

    public void setMaterialCovers(String materialCovers) { this.materialCovers = materialCovers; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }
}
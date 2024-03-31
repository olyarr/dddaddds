package com.example.Warehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "pillow")
public class Pillow {

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

    public Pillow(int id, String article, String title, String materialSkeleton, String materialFiller) {
        this.id = id;
        this.article = article;
        this.title = title;
        this.materialFiller = materialSkeleton;
        this.materialCovers = materialFiller;
    }

    public Pillow() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getArticle() { return article; }

    public void setArticle(String article) { this.article = article; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getMaterialFiller() { return materialFiller; }

    public void setMaterialFiller(String materialSkeleton) { this.materialFiller = materialSkeleton; }

    public String getMaterialCovers() { return materialCovers; }

    public void setMaterialCovers(String materialCovers) { this.materialCovers = materialCovers; }
}
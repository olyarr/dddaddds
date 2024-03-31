package com.example.Warehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "bedlinen")
public class BedLinen {
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

    @ManyToOne
    @JoinColumn(name = "material_id")
    @NotNull(message = "Необходимо указать материал")
    private Material material;

    @Column(name = "color", length = 30)
    @NotEmpty(message = "Необходимо указать цвет")
    @Size(max = 30, message = "Название цвета не должно превышать 30 символов")
    private String color;

    public BedLinen(int id, String article, String name, Material material, String color) {
        this.id = id;
        this.article = article;
        this.title = name;
        this.material = material;
        this.color = color;
    }

    public BedLinen() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getArticle() { return article; }

    public void setArticle(String article) { this.article = article; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Material getMaterial() { return material; }

    public void setMaterial(Material material) { this.material = material; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }
}
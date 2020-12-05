package br.com.udemy.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"key", "author", "launchDate", "title", "price"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {
    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String author;
    private Date launchDate;
    private String title;
    private double price;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookVO bookVO = (BookVO) o;
        return Double.compare(bookVO.price, price) == 0 &&
                Objects.equals(key, bookVO.key) &&
                Objects.equals(author, bookVO.author) &&
                Objects.equals(launchDate, bookVO.launchDate) &&
                Objects.equals(title, bookVO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, author, launchDate, title, price);
    }
}

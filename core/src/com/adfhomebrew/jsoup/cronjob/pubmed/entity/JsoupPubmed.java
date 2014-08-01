package com.adfhomebrew.jsoup.cronjob.pubmed.entity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"jsoup_pubmed\"")
public class JsoupPubmed implements Serializable {
    private static final long serialVersionUID = 4145920814113663844L;
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "num_of_results")
    private int num_of_results;
    @Column(name = "scrape_time")
    private Timestamp scrape_time;
    @Column(name = "search_criteria")
    private String search_criteria;

    public JsoupPubmed() {
    }

    public JsoupPubmed(int num_of_results, String search_criteria) {
        this.num_of_results = num_of_results;
        this.search_criteria = search_criteria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_of_results() {
        return num_of_results;
    }

    public void setNum_of_results(int num_of_results) {
        this.num_of_results = num_of_results;
    }

    public Timestamp getScrape_time() {
        return scrape_time;
    }

    public void setScrape_time(Timestamp scrape_time) {
        this.scrape_time = scrape_time;
    }

    public String getSearch_criteria() {
        return search_criteria;
    }

    public void setSearch_criteria(String search_criteria) {
        this.search_criteria = search_criteria;
    }
}

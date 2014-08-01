package com.adfhomebrew.jsoup.cronjob.pubmed;

import com.adfhomebrew.jsoup.cronjob.pubmed.entity.JsoupPubmed;

import java.io.IOException;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PubMedSearchClient {
    private final String PUBMED_URL = "http://www.ncbi.nlm.nih.gov/pubmed/?term=";
    private final String PUBMED_RESULTS_SELECTOR = "h2.result_count";
    List<Serializable> persistList = new ArrayList();

    public static void main(String[] args) {
        new PubMedSearchClient();
    }

    public PubMedSearchClient() {
        PersistenceUtil util = new PersistenceUtil();
        addResults("brca1");
        addResults("p53");
        util.persist(persistList);
    }

    private void addResults(String criteria) {
        try {
            Document doc = Jsoup.connect((PUBMED_URL + criteria)).get();
            String result = doc.select(PUBMED_RESULTS_SELECTOR).first().text();
            int intResult=Integer.parseInt(result.replaceFirst(".*of ", ""));
            persistList.add(new JsoupPubmed(intResult, criteria));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

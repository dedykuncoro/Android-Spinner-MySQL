package com.dedykuncoro.spinner.data;

/**
 * Created by KUNCORO on 23/04/2017.
 */

public class Data {
    private String id, pendidikan;

    public Data() {
    }

    public Data(String id, String pendidikan) {
        this.id = id;
        this.pendidikan = pendidikan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

}

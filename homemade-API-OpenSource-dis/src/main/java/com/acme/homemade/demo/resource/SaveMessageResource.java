package com.acme.homemade.demo.resource;

import javax.validation.constraints.NotNull;

public class SaveMessageResource {
    @NotNull
    private String text;

    private String file;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}

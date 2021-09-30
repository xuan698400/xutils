package com.xuan.mix.domain.share.model;

/**
 * @author xuan
 * @since 2021/9/30
 */
public class Contact {

    private String content;

    private ContactType contactType;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

}

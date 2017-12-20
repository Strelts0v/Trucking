package com.itechart.trucking.service.mail;

public class Mail  {

    private String from;
    private String to;
    private String subject;
    private String text;
    private String fullName;
    private String age;
    private String color;


    public Mail() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mail mail = (Mail) o;

        if (from != null ? !from.equals(mail.from) : mail.from != null) return false;
        if (to != null ? !to.equals(mail.to) : mail.to != null) return false;
        if (subject != null ? !subject.equals(mail.subject) : mail.subject != null) return false;
        if (text != null ? !text.equals(mail.text) : mail.text != null) return false;
        if (fullName != null ? !fullName.equals(mail.fullName) : mail.fullName != null) return false;
        if (age != null ? !age.equals(mail.age) : mail.age != null) return false;
        return color != null ? color.equals(mail.color) : mail.color == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }


}

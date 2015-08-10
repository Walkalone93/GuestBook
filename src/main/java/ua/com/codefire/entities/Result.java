package ua.com.codefire.entities;

public class Result {

    public String login;
    public String record;
    public String date;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 41 * hash + (this.record != null ? this.record.hashCode() : 0);
        hash = 41 * hash + (this.date != null ? this.date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Result other = (Result) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.record == null) ? (other.record != null) : !this.record.equals(other.record)) {
            return false;
        }
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Result{" + "login=" + login + ", record=" + record + ", date=" + date + '}';
    }

}
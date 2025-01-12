package org.epf.hadoop.colfil2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UserPair implements WritableComparable<UserPair> {
    private Text user1;
    private Text user2;

    public UserPair() {
        this.user1 = new Text("");
        this.user2 = new Text("");
    }

    public UserPair(String user1, String user2) {
        if (user1.compareTo(user2) <= 0) {
            this.user1 = new Text(user1);
            this.user2 = new Text(user2);
        } else {
            this.user1 = new Text(user2);
            this.user2 = new Text(user1);
        }
    }

    public void set(String user1, String user2) {
        if (user1.compareTo(user2) <= 0) {
            this.user1.set(user1);
            this.user2.set(user2);
        } else {
            this.user1.set(user2);
            this.user2.set(user1);
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        user1.write(out);
        user2.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        user1.readFields(in);
        user2.readFields(in);
    }

    @Override
    public int compareTo(UserPair o) {
        int cmp = user1.compareTo(o.user1);
        if (cmp != 0) {
            return cmp;
        }
        return user2.compareTo(o.user2);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UserPair) {
            UserPair other = (UserPair) o;
            return user1.equals(other.user1) && user2.equals(other.user2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return user1.hashCode() * 163 + user2.hashCode();
    }

    @Override
    public String toString() {
        return user1 + "," + user2;
    }

    public String getFirstUser() {
        return user1.toString();
    }

    public String getSecondUser() {
        return user2.toString();
    }
}

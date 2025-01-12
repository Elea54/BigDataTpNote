package org.epf.hadoop.colfil2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CommonFriendsMapper extends Mapper<Object, Text, UserPair, Text> {
    private final UserPair userPair = new UserPair();
    private final Text friend = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] parts = value.toString().split("\t");
        if (parts.length != 2) {
            return;
        }

        String user = parts[0];
        String[] friends = parts[1].split(",");
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                userPair.set(friends[i], friends[j]);
                friend.set(user);
                context.write(userPair, friend);
            }
        }
    }
}

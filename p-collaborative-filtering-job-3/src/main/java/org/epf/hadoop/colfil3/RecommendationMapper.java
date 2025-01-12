package org.epf.hadoop.colfil3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RecommendationMapper extends Mapper<Object, Text, Text, Text> {
    private final Text userKey = new Text();
    private final Text recommendationValue = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] parts = value.toString().split("\t");
        if (parts.length != 2) {
            return;
        }
        String userPair = parts[0];
        String commonFriendsCount = parts[1];
        String[] users = userPair.split(",");
        String user1 = users[0];
        String user2 = users[1];
        userKey.set(user1);
        recommendationValue.set(user2 + "," + commonFriendsCount);
        context.write(userKey, recommendationValue);
        userKey.set(user2);
        recommendationValue.set(user1 + "," + commonFriendsCount);
        context.write(userKey, recommendationValue);
    }
}

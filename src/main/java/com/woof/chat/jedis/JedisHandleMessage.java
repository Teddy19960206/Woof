package com.woof.chat.jedis;

import java.util.List;
import java.util.Set;

import com.woof.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)


	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Jedis jedis = JedisUtil.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = JedisUtil.getResource();
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);

		jedis.close();
	}

	public static void saveMemberList(String user){
		String setKey = "memberList";
		Jedis jedis = JedisUtil.getResource();
		Double score = jedis.zscore(setKey, user); // 檢查用戶是否已經存在於集合中
		if (score == null) {
			// 用戶不存在，添加用戶
			double newScore = System.currentTimeMillis(); // 使用當前時間戳作為分數
			jedis.zadd(setKey, newScore, user);
		}
		// 如果用戶已存在，不進行任何操作
		jedis.close();
	}

	public static Set<String> getMemberList(){
		Jedis jedis = JedisUtil.getResource();
		Set<String> memberList = jedis.zrange("memberList", 0, -1);

//		// 輸出 zset 的所有成員
//		for (String member : memberList) {
//			System.out.println(member);
//		}
//
		jedis.close();
		return memberList;
	}
}

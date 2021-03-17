package com.brocels.springboot.enjeu.repository;

import java.util.List;

import com.brocels.springboot.enjeu.domain.Player;

public class PlayerRepository {

	private static int maximumPlayers = 10;
	
	
	public static int getMaximumPlayers() {
		return maximumPlayers;
	}

	public List<Player> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public Player findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

}

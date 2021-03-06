package com.brocels.springboot.enjeu.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.brocels.springboot.enjeu.domain.Player;

@Repository
public class PlayerRepository {

	private List<Player> players = new ArrayList<Player>();
	private static int index = 1; 

	public List<Player> getList() {
		return players;
	}

	public Player findById(Integer id) {
		
		for (Player player : players) {
			if (player.getId().equals(id)) {
				return player;
			}
		}
		return null;
	}

	public Player findByName(String name) {
		
		for (Player player : players) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

	public void addPlayer(Player player) {
		
		player.setId(index++);
		players.add(player);
	}

}

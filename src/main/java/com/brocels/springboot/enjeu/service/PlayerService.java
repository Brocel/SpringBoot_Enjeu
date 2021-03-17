package com.brocels.springboot.enjeu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brocels.springboot.enjeu.domain.Player;
import com.brocels.springboot.enjeu.exception.DuplicateNameException;
import com.brocels.springboot.enjeu.exception.PlayerlistFullException;
import com.brocels.springboot.enjeu.repository.PlayerRepository;

@Service
public class PlayerService {

	PlayerRepository playerRepository;
	
	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		super();
		this.playerRepository = playerRepository;
	}
	
	public List<Player> getPlayers() {
		
		List<Player> playersList = playerRepository.getList();
		
		return playersList;
	}
	
	public int getPlayersSize() {
		return playerRepository.getList().size();
	}

	public Player findPlayerById(Integer id) {
		return playerRepository.findById(id);
	}

	public void addOrUpdateWatchlistItem(Player player) throws DuplicateNameException, PlayerlistFullException {
		
		Player existingPlayer = findPlayerById(player.getId());
		
		if(existingPlayer == null) {
			
			if (getPlayersSize() >= playerRepository.getMaximumPlayers()) {
				throw new PlayerlistFullException();
			}
			if (playerRepository.findByName(player.getName()) != null) {
				throw new DuplicateNameException();
			}
			playerRepository.addPlayer(player);
		} else {
			existingPlayer.setName(player.getName());
			existingPlayer.setAge(player.getAge());
			existingPlayer.setCity(player.getCity());
			existingPlayer.setCountry(player.getCountry());
			existingPlayer.setAvatarUrl(player.getAvatarUrl());
			existingPlayer.seteMail(player.geteMail());
		}
		
	}

}

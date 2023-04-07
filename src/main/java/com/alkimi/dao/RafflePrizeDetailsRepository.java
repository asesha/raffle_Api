package com.alkimi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.Raffle;
import com.alkimi.entities.RafflePrizeDetails;
import com.alkimi.vo.PrizeDetails;
import com.alkimi.vo.Winners;


@Repository
public interface RafflePrizeDetailsRepository extends JpaRepository<RafflePrizeDetails, Integer> {

	List<RafflePrizeDetails> findByRaffleIdRaffleId(int raffleId);
	List<RafflePrizeDetails> findByRaffleId(Raffle raffleId);
	Optional<RafflePrizeDetails> findByRaffleIdAndPrizePositionAndPrizeIndex(Raffle raffleId,int prizePosition,int prizeIndex);
	
	@Query("select t.ticketId from RafflePrizeDetails t where t.raffleId = ?1")
    Optional<List<Integer>> getTicketNumbersByRaffleId(Raffle raffleId);
	
	@Query("select t.prizeIndex from RafflePrizeDetails t where t.raffleId = ?1 and t.prizePosition = ?2 order by t.prizeIndex")
    Optional<List<Integer>> getPrizeIndexByRaffleIdAndPrizePosition(Raffle raffleId,int prizePosition);
	
	@Query("select distinct t.prizePosition from RafflePrizeDetails t where t.raffleId = ?1 order by t.prizePosition")
    Optional<List<Integer>> getPrizePositionByRaffleId(Raffle raffleId);
	
	@Query(value ="select ap.prize_position prizePosition , ap.product_id_fk productId, p.product_name productName, \r\n"
			+ "ap.winner_id_fk winnerId, u.user_name winnerName from raffle_prize_details ap \r\n"
			+ "inner join product p \r\n"
			+ "on ap.product_id_fk = p.product_id \r\n"
			+ "inner join users u \r\n"
			+ "on ap.winner_id_fk = u.user_id where ap.raffle_id_fk = ? order by ap.prize_index", nativeQuery = true)
	List<Winners> getWinnerDetatils(int raffleId);
	
	@Query(value ="select apd.prize_position prizePosition, p.product_id productId, p.product_name productName, \r\n"
			+ "p.product_image productImage, p.product_video productVideo, a.raffle_name raffleName, \r\n"
			+ "a.start_date startDate, a.end_date endDate from RAFFLE_PRIZE_DETAILS apd \r\n"
			+ "inner join RAFFLE a\r\n"
			+ "on apd.RAFFLE_ID_FK = a.RAFFLE_ID\r\n"
			+ "inner join PRODUCT p\r\n"
			+ "on apd.PRODUCT_ID_FK = p.PRODUCT_ID where a.raffle_id = ? order by apd.prize_index", nativeQuery = true)
	List<PrizeDetails> getPrizeDetails(int raffleId);
}




package com.crossover.techtrial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crossover.techtrial.model.HourlyElectricity;

import antlr.collections.List;

/**
 * HourlyElectricity Repository is for all operations for HourlyElectricity.
 * 
 * @author Crossover
 */
@RestResource(exported = false)
public interface HourlyElectricityRepository extends PagingAndSortingRepository<HourlyElectricity, Long> {
	Page<HourlyElectricity> findAllByPanelIdOrderByReadingAtDesc(Long panelId, Pageable pageable);

@Query(value ="select panel_id,sum(generated_electricity),min(generated_electricity), max(generated_electricity),reading_at FROM hourly_electricity where panel_id:=panelId and reading_at < SYSDATE() group by panel_id,reading_at", nativeQuery = true)
List findAlllDailyElectricityFromYesterday(Long panelId);

}
package br.com.kvmedia.asgestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kvmedia.asgestor.entity.SitesEntity;

@Repository
public interface SitesRepository extends JpaRepository<SitesEntity, Integer> {

}

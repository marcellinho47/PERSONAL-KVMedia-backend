package br.com.kvmedia.asgestor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kvmedia.asgestor.entity.OperatorEntity;

@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, Integer> {

	public Optional<OperatorEntity> findByLogin(String login);
}

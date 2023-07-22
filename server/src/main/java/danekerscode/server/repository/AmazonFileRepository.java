package danekerscode.server.repository;

import danekerscode.server.model.AmazonFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazonFileRepository extends JpaRepository<AmazonFile,Integer> {
}

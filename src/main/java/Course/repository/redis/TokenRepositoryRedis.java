package Course.repository.redis;

import Course.entity.TokenEntity2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepositoryRedis extends CrudRepository<TokenEntity2,String> {

}

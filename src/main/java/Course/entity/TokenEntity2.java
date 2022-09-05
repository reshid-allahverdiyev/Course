package Course.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

@Data
@TypeAlias("SMAPP")
@RedisHash("SMAPP_HASH")
public class TokenEntity2 implements Serializable {

    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "username")
    private String username;

}
